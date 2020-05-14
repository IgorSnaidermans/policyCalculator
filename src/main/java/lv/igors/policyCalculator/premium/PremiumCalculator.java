package lv.igors.policyCalculator.premium;

import lv.igors.policyCalculator.coefficientMapper.CoefficientMapperStrategy;
import lv.igors.policyCalculator.coefficientMapper.FireCoefficientMapper;
import lv.igors.policyCalculator.coefficientMapper.TheftCoefficientMapper;
import lv.igors.policyCalculator.insurancePolicy.InsurancePolicy;
import lv.igors.policyCalculator.insurancePolicy.InsuranceSubObject;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

@Component
public class PremiumCalculator {
    CoefficientMapperStrategy coefficientMapper;


    public PremiumCalculator(FireCoefficientMapper coefficientMapper) {
        this.coefficientMapper = coefficientMapper;
    }

    public BigDecimal calculate(InsurancePolicy policy) {
        List<InsuranceSubObject> insuranceSubObjectList =
                policy.getInsuranceObjectList().get(0).getInsuranceSubObjectList();
        InsuranceSubObject subObject = insuranceSubObjectList.get(0);

        BigDecimal insuredCost = subObject.getInsuredCost();
        Risks risk = subObject.getInsuredRisks().get(0);


        if (risk.equals(Risks.FIRE)) {
            coefficientMapper = new FireCoefficientMapper();
        } else if (risk.equals(Risks.THEFT)) {
            coefficientMapper = new TheftCoefficientMapper();
        }


        return roundThreePrecision(insuredCost.multiply(coefficientMapper.map(risk, insuredCost)));
    }

    private BigDecimal roundThreePrecision(BigDecimal value) {
        MathContext roundPrecision = new MathContext(3);
        return value.round(roundPrecision);
    }
}
