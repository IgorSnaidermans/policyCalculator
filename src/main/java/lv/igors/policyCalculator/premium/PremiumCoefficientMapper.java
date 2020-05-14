package lv.igors.policyCalculator.premium;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PremiumCoefficientMapper {
    BigDecimal HUNDRED = new BigDecimal("100");

    public BigDecimal map(Risks risk, BigDecimal insuredCost) {
        if (risk.equals(Risks.FIRE) && insuredCost.compareTo(HUNDRED) < 0) {
            return RiskCoefficientConstants.FIRE_COST_MORE_THAN_100.getValue();
        } else if (risk.equals(Risks.FIRE) && insuredCost.compareTo(HUNDRED) > 0) {
            return RiskCoefficientConstants.FIRE_COST_LESS_THAN_100.getValue();
        }

        return null;
    }
}
