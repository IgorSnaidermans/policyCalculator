package lv.igors.policyCalculator.premium;

import lv.igors.policyCalculator.coefficientMapper.CoefficientMapperStrategy;
import lv.igors.policyCalculator.coefficientMapper.FireCoefficientMapper;
import lv.igors.policyCalculator.coefficientMapper.TheftCoefficientMapper;
import lv.igors.policyCalculator.insurancePolicy.InsuranceObject;
import lv.igors.policyCalculator.insurancePolicy.InsurancePolicy;
import lv.igors.policyCalculator.insurancePolicy.InsuranceSubObject;
import lv.igors.policyCalculator.insurancePolicy.Risks;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PremiumCalculator {
    CoefficientMapperStrategy coefficientMapper;

    public BigDecimal calculate(InsurancePolicy policy) {
        List<InsuranceObject> insuranceSubObjectList =
                policy.getInsuranceObjectList();
        BigDecimal finalPremium = new BigDecimal("0");

        finalPremium = appendEveryRiskPremium(insuranceSubObjectList, finalPremium);
        return roundThreePrecision(finalPremium);
    }

    private BigDecimal appendEveryRiskPremium(List<InsuranceObject> insuranceSubObjectList, BigDecimal finalPremium) {
        HashMap<Risks, BigDecimal> singleInsuredRiskCosts = calculateRiskInsuredCost(insuranceSubObjectList);

        for (Map.Entry<Risks, BigDecimal> riskCost : singleInsuredRiskCosts.entrySet()) {
            coefficientMapperStrategyPicker(riskCost.getKey());
            BigDecimal coefficient = coefficientMapper.map(riskCost.getValue());
            BigDecimal riskPremium = riskCost.getValue().multiply(coefficient);
            finalPremium = finalPremium.add(riskPremium);
        }

        return finalPremium;
    }

    private HashMap<Risks, BigDecimal> calculateRiskInsuredCost(List<InsuranceObject> insuranceSubObjectList) {
        HashMap<Risks, BigDecimal> singleInsuredRiskCosts = new HashMap<>();

        for (InsuranceObject insuranceObject : insuranceSubObjectList) {
            for (InsuranceSubObject insuranceSubObject : insuranceObject.getInsuranceSubObjectList()) {
                for (Risks risk : insuranceSubObject.getInsuredRisks()) {
                    if (null == singleInsuredRiskCosts.get(risk)) {
                        singleInsuredRiskCosts.put(risk, insuranceSubObject.getInsuredCost());
                    } else {
                        BigDecimal insuredCost = singleInsuredRiskCosts.get(risk);
                        insuredCost = insuredCost.add(insuranceSubObject.getInsuredCost());

                        singleInsuredRiskCosts.replace(risk, insuredCost);
                    }
                }
            }
        }

        return singleInsuredRiskCosts;
    }

    private void coefficientMapperStrategyPicker(Risks risk) {
        if (risk.equals(Risks.FIRE)) {
            coefficientMapper = new FireCoefficientMapper();
        } else if (risk.equals(Risks.THEFT)) {
            coefficientMapper = new TheftCoefficientMapper();
        }
    }

    private BigDecimal roundThreePrecision(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}