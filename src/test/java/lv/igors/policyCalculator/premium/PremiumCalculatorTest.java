package lv.igors.policyCalculator.premium;

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

    @Before
    public void setup() {
        policy = new InsurancePolicy.Builder().build();
        insuranceObject = new InsuranceObject("Object");
        insuranceSubObject = new InsuranceSubObject.Builder()
                .insuredCost(new BigDecimal("50"))
                .name("Object")
                .build();
        insuranceObject.addInsuranceSubObject(insuranceSubObject);
        insuranceSubObject.addRisk(Risks.FIRE);

        policy.addInsuranceObject(insuranceObject);
        premiumCalculator = new PremiumCalculator();
    }

    @Test
    public void calculate_PolicyWithOneObjectRiskFireCost50_ShouldReturnExpectedAmount() {
        BigDecimal expectedAmount = new BigDecimal("0.70");
        BigDecimal result = premiumCalculator.calculate(policy);

        assertEquals(expectedAmount, result);
    }
}
