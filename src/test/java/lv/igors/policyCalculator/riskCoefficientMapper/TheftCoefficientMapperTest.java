package lv.igors.policyCalculator.riskCoefficientMapper;

import lv.igors.policyCalculator.insurancePolicy.Risks;
import lv.igors.policyCalculator.riskCoefficientMapper.constants.InsuredCostBoundaryConstants;
import lv.igors.policyCalculator.riskCoefficientMapper.constants.RiskCoefficientConstants;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;


public class TheftCoefficientMapperTest {
    TheftCoefficientMapper theftCoefficientMapper;

    @Before
    public void setUp() {
        theftCoefficientMapper = new TheftCoefficientMapper();
    }

    @Test
    public void map_LessThanBoundaryInput_ShouldReturnExpectedResult() {
        BigDecimal input = InsuredCostBoundaryConstants.THEFT.getValue()
                .subtract(new BigDecimal("0.000001"));
        BigDecimal expected = RiskCoefficientConstants.THEFT_COST_LESS_THAN_BOUND.getValue();
        BigDecimal result = theftCoefficientMapper.map(input);

        assertEquals(expected, result);
    }

    @Test
    public void map_MoreThanBoundaryInput_ShouldReturnExpectedResult() {
        BigDecimal input = InsuredCostBoundaryConstants.THEFT.getValue()
                .add(new BigDecimal("0.000001"));
        BigDecimal expected = RiskCoefficientConstants.THEFT_COST_EQUAL_MORE_THAN_BOUND.getValue();
        BigDecimal result = theftCoefficientMapper.map(input);

        assertEquals(expected, result);
    }

    @Test
    public void map_EqualBoundaryInput_ShouldReturnExpectedResult() {
        BigDecimal input = InsuredCostBoundaryConstants.THEFT.getValue();
        BigDecimal expected = RiskCoefficientConstants.THEFT_COST_EQUAL_MORE_THAN_BOUND.getValue();
        BigDecimal result = theftCoefficientMapper.map(input);

        assertEquals(expected, result);
    }

    @Test
    public void support_nonSupportedRisk_ShouldReturnFalse() {
        assertFalse(theftCoefficientMapper.support(Risks.FIRE));
    }

    @Test
    public void support_SupportedRisk_ShouldReturnFalse() {
        assertTrue(theftCoefficientMapper.support(Risks.THEFT));
    }
}
