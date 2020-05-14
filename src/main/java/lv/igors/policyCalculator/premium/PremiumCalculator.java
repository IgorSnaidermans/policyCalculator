package lv.igors.policyCalculator.premium;

import lv.igors.policyCalculator.insurancePolicy.InsurancePolicy;
import lv.igors.policyCalculator.insurancePolicy.InsuranceSubObject;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

@Component
public class PremiumCalculator {
    public BigDecimal calculate(InsurancePolicy policy) {
        List<InsuranceSubObject> insuranceSubObjectList =
                policy.getInsuranceObjectList().get(0).getInsuranceSubObjectList();
        InsuranceSubObject subObject = insuranceSubObjectList.get(0);

        BigDecimal insuredCost = subObject.getInsuredCost();
        Risks risk = subObject.getInsuredRisks().get(0);

        return roundTwoPrecision(insuredCost.multiply(premiumCoefficientMapper(risk, insuredCost)));
    }

    private BigDecimal roundTwoPrecision(BigDecimal value) {
        MathContext roundPrecision = new MathContext(3);
        return value.round(roundPrecision);
    }

    private BigDecimal premiumCoefficientMapper(Risks risk, BigDecimal insuredCost) {
        BigDecimal HUNDRED = new BigDecimal("100");
        BigDecimal LESS_THAN_100 = new BigDecimal("0.014");
        BigDecimal MORE_THAN_100 = new BigDecimal("0.024");

        if (risk.equals(Risks.FIRE) && insuredCost.compareTo(HUNDRED) < 0) {
            return LESS_THAN_100;
        } else if (risk.equals(Risks.FIRE) && insuredCost.compareTo(HUNDRED) > 0) {
            return MORE_THAN_100;
        }

        return null;
    }
}
