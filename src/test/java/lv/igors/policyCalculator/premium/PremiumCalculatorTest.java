package lv.igors.policyCalculator.premium;

import lv.igors.policyCalculator.insurancePolicy.InsuranceObject;
import lv.igors.policyCalculator.insurancePolicy.InsurancePolicy;
import lv.igors.policyCalculator.insurancePolicy.InsuranceSubObject;
import lv.igors.policyCalculator.insurancePolicy.Risks;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PremiumCalculatorTest {
    PremiumCalculator premiumCalculator;
    InsurancePolicy policy;
    InsuranceObject insuranceObjectOne;
    InsuranceObject insuranceObjectTwo;
    InsuranceSubObject insuranceSubObjectOne;
    InsuranceSubObject insuranceSubObjectTwo;
    InsuranceSubObject insuranceSubObjectThree;
    InsuranceSubObject insuranceSubObjectFour;

    @Before
    public void setup() {
        policy = new InsurancePolicy.Builder().build();
        insuranceObjectOne = new InsuranceObject("Object");
        insuranceObjectTwo = new InsuranceObject("Object");
        insuranceSubObjectOne = new InsuranceSubObject.Builder()
                .name("Object")
                .build();
        insuranceSubObjectTwo = new InsuranceSubObject.Builder()
                .name("Object")
                .build();
        insuranceSubObjectThree = new InsuranceSubObject.Builder()
                .name("Object")
                .build();
        insuranceSubObjectFour = new InsuranceSubObject.Builder()
                .name("Object")
                .build();

        policy.addInsuranceObject(insuranceObjectOne);
        premiumCalculator = new PremiumCalculator();
    }

    @Test
    public void calculate_PolicyOneObjectRiskFireCost100_ShouldReturnExpectedAmount() {
        insuranceSubObjectOne.setInsuredCost(new BigDecimal("100"));
        insuranceSubObjectOne.addRisk(Risks.FIRE);
        insuranceObjectOne.addInsuranceSubObject(insuranceSubObjectOne);

        BigDecimal expectedAmount = new BigDecimal("1.40");
        BigDecimal result = premiumCalculator.calculate(policy);

        assertEquals(expectedAmount, result);
    }

    @Test
    public void calculate_PolicyOneObjectRiskFireCost101_ShouldReturnExpectedAmount() {
        insuranceSubObjectOne.setInsuredCost(new BigDecimal("101"));
        insuranceSubObjectOne.addRisk(Risks.FIRE);
        insuranceObjectOne.addInsuranceSubObject(insuranceSubObjectOne);

        BigDecimal expectedAmount = new BigDecimal("2.42");
        BigDecimal result = premiumCalculator.calculate(policy);

        assertEquals(expectedAmount, result);
    }

    @Test
    public void calculate_PolicyOneObjectRiskTheftCost15_ShouldReturnExpectedAmount() {
        insuranceSubObjectOne.setInsuredCost(new BigDecimal("15"));
        insuranceSubObjectOne.addRisk(Risks.THEFT);
        insuranceObjectOne.addInsuranceSubObject(insuranceSubObjectOne);

        BigDecimal expectedAmount = new BigDecimal("0.75");
        BigDecimal result = premiumCalculator.calculate(policy);

        assertEquals(expectedAmount, result);
    }

    @Test
    public void calculate_PolicyOneObjectRiskTheftCost14_ShouldReturnExpectedAmount() {
        insuranceSubObjectOne.setInsuredCost(new BigDecimal("14"));
        insuranceSubObjectOne.addRisk(Risks.THEFT);
        insuranceObjectOne.addInsuranceSubObject(insuranceSubObjectOne);

        BigDecimal expectedAmount = new BigDecimal("1.54");
        BigDecimal result = premiumCalculator.calculate(policy);

        assertEquals(expectedAmount, result);
    }

    @Test
    public void calculate_PolicyOneObjectTwoSubObjectsTwoRisks_ShouldReturnExpectedAmount() {
        insuranceSubObjectOne.setInsuredCost(new BigDecimal("100"));
        insuranceSubObjectOne.addRisk(Risks.FIRE);
        insuranceSubObjectTwo.setInsuredCost(new BigDecimal("8"));
        insuranceSubObjectTwo.addRisk(Risks.THEFT);
        insuranceObjectOne.addInsuranceSubObject(insuranceSubObjectOne);
        insuranceObjectOne.addInsuranceSubObject(insuranceSubObjectTwo);

        BigDecimal expectedAmount = new BigDecimal("2.28");
        BigDecimal result = premiumCalculator.calculate(policy);

        assertEquals(expectedAmount, result);
    }


    @Test
    public void calculate_PolicyTwoObjectsWithTwoSubObjects_ShouldReturnExpectedAmount() {
        insuranceSubObjectOne.setInsuredCost(new BigDecimal("250"));
        insuranceSubObjectOne.addRisk(Risks.FIRE);
        insuranceSubObjectTwo.setInsuredCost(new BigDecimal("60"));
        insuranceSubObjectTwo.addRisk(Risks.THEFT);
        insuranceObjectOne.addInsuranceSubObject(insuranceSubObjectOne);
        insuranceObjectOne.addInsuranceSubObject(insuranceSubObjectTwo);
        insuranceSubObjectThree.setInsuredCost(new BigDecimal("250"));
        insuranceSubObjectThree.addRisk(Risks.FIRE);
        insuranceSubObjectFour.setInsuredCost(new BigDecimal("42.51"));
        insuranceSubObjectFour.addRisk(Risks.THEFT);
        insuranceObjectTwo.addInsuranceSubObject(insuranceSubObjectThree);
        insuranceObjectTwo.addInsuranceSubObject(insuranceSubObjectFour);
        policy.addInsuranceObject(insuranceObjectTwo);

        BigDecimal expectedAmount = new BigDecimal("17.13");
        BigDecimal result = premiumCalculator.calculate(policy);

        assertEquals(expectedAmount, result);
    }
}
