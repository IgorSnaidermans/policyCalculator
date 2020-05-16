package lv.igors.policyCalculator.riskCoefficientMapper;

import lv.igors.policyCalculator.riskCoefficientMapper.constants.InsuredCostBoundaryConstants;
import lv.igors.policyCalculator.riskCoefficientMapper.constants.RiskCoefficientConstants;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public final class FireCoefficientMapper implements CoefficientMapperStrategy {
    private static FireCoefficientMapper coefficientMapper;

    private FireCoefficientMapper() {
    }

    public static CoefficientMapperStrategy getInstance() {
        if (coefficientMapper == null) {
            coefficientMapper = new FireCoefficientMapper();
        }
        return coefficientMapper;
    }

    @Override
    public BigDecimal map(BigDecimal insuredCost) {
        return (insuredCost.compareTo(InsuredCostBoundaryConstants.FIRE.getValue()) <= 0) ?
                RiskCoefficientConstants.FIRE_COST_LESS_THAN_BOUND.getValue() :
                RiskCoefficientConstants.FIRE_COST_MORE_THAN_BOUND.getValue();
    }
}
