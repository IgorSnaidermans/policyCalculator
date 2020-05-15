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
    public BigDecimal calculate(InsurancePolicy policy) {
        List<InsuranceSubObject> insuranceSubObjectList =
                policy.getAllSubObjects();
        BigDecimal finalPremium =
                calculateFinalPremium(sumAllRiskInsuredCost(insuranceSubObjectList));
        return roundTo2Digits(finalPremium);
    }

    private BigDecimal calculateFinalPremium(Map<Risks, BigDecimal> allRiskInsuredCost) {
        BigDecimal finalPremium = new BigDecimal("0");
        for (Map.Entry<Risks, BigDecimal> riskCost : allRiskInsuredCost.entrySet()) {
            CoefficientMapperStrategy mapper = coefficientMapperStrategyPicker(riskCost.getKey());
            BigDecimal coefficient = mapper.map(riskCost.getValue());
            BigDecimal riskPremium = riskCost.getValue().multiply(coefficient);
            finalPremium = finalPremium.add(riskPremium);
        }
        return finalPremium;
    }

    private Map<Risks, BigDecimal> sumAllRiskInsuredCost(List<InsuranceSubObject> insuranceSubObjectList) {
        Map<Risks, BigDecimal> singleInsuredRiskCosts = new HashMap<>();

        insuranceSubObjectList
                .forEach(subObject -> subObject.getInsuredRisks()
                        .forEach(risk -> {
                            appendRiskCost(singleInsuredRiskCosts, subObject.getInsuredCost(), risk);
                        })
                );

        return singleInsuredRiskCosts;
    }


    private void appendRiskCost(Map<Risks, BigDecimal> singleInsuredRiskCosts,
                                BigDecimal subObjectCost, Risks risk) {
        if (!singleInsuredRiskCosts.containsKey(risk)) {
            singleInsuredRiskCosts.put(risk, subObjectCost);
        } else {
            BigDecimal insuredCost = singleInsuredRiskCosts.get(risk);
            insuredCost = insuredCost.add(subObjectCost);

            singleInsuredRiskCosts.replace(risk, insuredCost);
        }
    }

    private CoefficientMapperStrategy coefficientMapperStrategyPicker(Risks risk) {
        if (risk.equals(Risks.FIRE)) {
            return new FireCoefficientMapper();
        } else if (risk.equals(Risks.THEFT)) {
            return new TheftCoefficientMapper();
        } else {
            throw new RuntimeException("No mapper found for " + risk);
        }
    }

    private BigDecimal roundTo2Digits(BigDecimal value) {
        return value.setScale(2, RoundingMode.HALF_UP);
    }
}