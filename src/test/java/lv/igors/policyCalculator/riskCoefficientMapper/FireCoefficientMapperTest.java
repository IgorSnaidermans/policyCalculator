package lv.igors.policyCalculator.riskCoefficientMapper;

import lv.igors.policyCalculator.insurancePolicy.Risks;
import lv.igors.policyCalculator.riskCoefficientMapper.constants.InsuredCostBoundaryConstants;
import lv.igors.policyCalculator.riskCoefficientMapper.constants.RiskCoefficientConstants;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;


public class FireCoefficientMapperTest {
    FireCoefficientMapper fireCoefficientMapper;

    @Before
    public void setUp() {
        fireCoefficientMapper = new FireCoefficientMapper();
    }

    @Test
    public void map_LessThanBoundaryInput_ShouldReturnExpectedResult() {
        BigDecimal input = InsuredCostBoundaryConstants.FIRE.getValue()
                .subtract(new BigDecimal("0.000001"));
        BigDecimal expected = RiskCoefficientConstants.FIRE_COST_LESS_THAN_BOUND.getValue();
        BigDecimal result = fireCoefficientMapper.map(input);

        assertEquals(expected, result);
    }

    @Test
    public void map_MoreThanBoundaryInput_ShouldReturnExpectedResult() {
        BigDecimal input = InsuredCostBoundaryConstants.FIRE.getValue()
                .add(new BigDecimal("0.000001"));
        BigDecimal expected = RiskCoefficientConstants.FIRE_COST_MORE_THAN_BOUND.getValue();
        BigDecimal result = fireCoefficientMapper.map(input);

        assertEquals(expected, result);
    }

    @Test
    public void support_nonSupportedRisk_ShouldReturnFalse() {
        assertFalse(fireCoefficientMapper.support(Risks.THEFT));
    }

    @Test
    public void support_SupportedRisk_ShouldReturnFalse() {
        assertTrue(fireCoefficientMapper.support(Risks.FIRE));
    }
}
