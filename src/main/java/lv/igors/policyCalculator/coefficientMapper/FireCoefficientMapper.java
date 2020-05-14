package lv.igors.policyCalculator.coefficientMapper;

import lv.igors.policyCalculator.coefficientMapper.constants.InsuredCostBoundaryConstants;
import lv.igors.policyCalculator.coefficientMapper.constants.RiskCoefficientConstants;
import lv.igors.policyCalculator.premium.Risks;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class FireCoefficientMapper implements CoefficientMapperStrategy {

    @Override
    public BigDecimal map(Risks risk, BigDecimal insuredCost) {
        if (risk.equals(Risks.FIRE) &&
                insuredCost.compareTo(InsuredCostBoundaryConstants.FIRE.getValue()) <= 0) {
            return RiskCoefficientConstants.FIRE_COST_MORE_THAN_BOUND.getValue();
        } else if (risk.equals(Risks.FIRE) &&
                insuredCost.compareTo(InsuredCostBoundaryConstants.FIRE.getValue()) > 0) {
            return RiskCoefficientConstants.FIRE_COST_LESS_THAN_BOUND.getValue();
        }

        return null;
    }
}
