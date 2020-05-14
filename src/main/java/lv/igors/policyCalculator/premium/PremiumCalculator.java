package lv.igors.policyCalculator.premium;

import lv.igors.policyCalculator.coefficientMapper.CoefficientMapperStrategy;
import lv.igors.policyCalculator.coefficientMapper.FireCoefficientMapper;
import lv.igors.policyCalculator.coefficientMapper.TheftCoefficientMapper;
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
        List<InsuranceSubObject> insuranceSubObjectList =
                policy.getAllSubObjects();
        BigDecimal finalPremium = new BigDecimal("0");

        finalPremium = appendAllRiskPremium(insuranceSubObjectList, finalPremium);

        return scaleToTwoValues(finalPremium);
    }

    private BigDecimal appendAllRiskPremium(List<InsuranceSubObject> insuranceSubObjectList,
                                            BigDecimal finalPremium) {
        HashMap<Risks, BigDecimal> allRiskInsuredCost = sumAllRiskInsuredCost(insuranceSubObjectList);

        finalPremium = appendCalculatedPremium(finalPremium, allRiskInsuredCost);

        return finalPremium;
    }

    private HashMap<Risks, BigDecimal> sumAllRiskInsuredCost(List<InsuranceSubObject> insuranceSubObjectList) {
        HashMap<Risks, BigDecimal> singleInsuredRiskCosts = new HashMap<>();

        insuranceSubObjectList
                .forEach(subObject -> subObject.getInsuredRisks()
                        .forEach(risk -> {
                            appendRiskCost(singleInsuredRiskCosts, subObject.getInsuredCost(), risk);
                        })
                );

        return singleInsuredRiskCosts;
    }

    private void appendRiskCost(HashMap<Risks, BigDecimal> singleInsuredRiskCosts,
                                BigDecimal subObjectCost, Risks risk) {
        if (null == singleInsuredRiskCosts.get(risk)) {
            singleInsuredRiskCosts.put(risk, subObjectCost);
        } else {
            BigDecimal insuredCost = singleInsuredRiskCosts.get(risk);
            insuredCost = insuredCost.add(subObjectCost);

            singleInsuredRiskCosts.replace(risk, insuredCost);
        }
    }

    private BigDecimal appendCalculatedPremium(BigDecimal finalPremium,
                                               HashMap<Risks, BigDecimal> allRiskInsuredCost) {
        for (Map.Entry<Risks, BigDecimal> riskCost : allRiskInsuredCost.entrySet()) {
            coefficientMapperStrategyPicker(riskCost.getKey());
            BigDecimal coefficient = coefficientMapper.map(riskCost.getValue());
            BigDecimal riskPremium = riskCost.getValue().multiply(coefficient);
            finalPremium = finalPremium.add(riskPremium);
        }
        return finalPremium;
    }

    private void coefficientMapperStrategyPicker(Risks risk) {
        if (risk.equals(Risks.FIRE)) {
            coefficientMapper = new FireCoefficientMapper();
        } else if (risk.equals(Risks.THEFT)) {
            coefficientMapper = new TheftCoefficientMapper();
        }
    }

    private BigDecimal scaleToTwoValues(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}