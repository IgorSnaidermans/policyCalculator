package lv.igors.policyCalculator.premium;

import lv.igors.policyCalculator.coefficientMapper.FireCoefficientMapper;
import lv.igors.policyCalculator.insurancePolicy.InsuranceObject;
import lv.igors.policyCalculator.insurancePolicy.InsurancePolicy;
import lv.igors.policyCalculator.insurancePolicy.InsuranceSubObject;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PremiumCalculatorTest {
    PremiumCalculator premiumCalculator;
    InsurancePolicy policy;
    InsuranceObject insuranceObject;
    InsuranceSubObject insuranceSubObject;
    FireCoefficientMapper coefficientMapper = new FireCoefficientMapper();

    @Before
    public void setup() {
        policy = new InsurancePolicy.Builder().build();
        insuranceObject = new InsuranceObject("Object");
        insuranceSubObject = new InsuranceSubObject.Builder()
                .name("Object")
                .build();
        insuranceObject.addInsuranceSubObject(insuranceSubObject);
        insuranceSubObject.addRisk(Risks.FIRE);

        policy.addInsuranceObject(insuranceObject);
        premiumCalculator = new PremiumCalculator(coefficientMapper);
    }

    @Test
    public void calculate_PolicyWithOneObjectRiskFireCost99_ShouldReturnExpectedAmount() {
        insuranceSubObject.setInsuredCost(new BigDecimal("99"));

        BigDecimal expectedAmount = new BigDecimal("1.39");
        BigDecimal result = premiumCalculator.calculate(policy);

        assertEquals(expectedAmount, result);
    }

    @Test
    public void calculate_PolicyWithOneObjectRiskFireCost101_ShouldReturnExpectedAmount() {
        insuranceSubObject.setInsuredCost(new BigDecimal("101"));

        BigDecimal expectedAmount = new BigDecimal("2.42");
        BigDecimal result = premiumCalculator.calculate(policy);

        assertEquals(expectedAmount, result);
    }


}
