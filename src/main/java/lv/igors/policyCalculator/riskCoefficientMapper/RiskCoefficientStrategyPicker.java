package lv.igors.policyCalculator.riskCoefficientMapper;

import lv.igors.policyCalculator.insurancePolicy.Risks;

public class RiskCoefficientStrategyPicker {
    private RiskCoefficientStrategyPicker() {
    }

    public static CoefficientMapperStrategy pick(Risks risk) {
        if (risk.equals(Risks.FIRE)) {
            return FireCoefficientMapper.getInstance();
        } else if (risk.equals(Risks.THEFT)) {
            return TheftCoefficientMapper.getInstance();
        } else {
            throw new RuntimeException("No mapper found for " + risk);
        }
    }
}
