package lv.igors.policyCalculator.riskCoefficientMapper;

import lv.igors.policyCalculator.insurancePolicy.Risks;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


public class RiskCoefficientStrategyPickerTest {
    RiskCoefficientStrategyPicker strategyPicker;
    TheftCoefficientMapper theftCoefficientMapper;
    FireCoefficientMapper fireCoefficientMapper;
    List<CoefficientMapperStrategy> strategyList;

    @Before
    public void setup() {
        fireCoefficientMapper = new FireCoefficientMapper();
        theftCoefficientMapper = new TheftCoefficientMapper();
        strategyList = List.of(theftCoefficientMapper, fireCoefficientMapper);
        strategyPicker = new RiskCoefficientStrategyPicker(strategyList);
    }

    @Test
    public void pick_RiskTheft_shouldReturnExpectedObject() {
        CoefficientMapperStrategy expectedObject = theftCoefficientMapper;
        CoefficientMapperStrategy result = strategyPicker.pick(Risks.THEFT);

        assertEquals(expectedObject, result);
    }

    @Test
    public void pick_NonExistRisk_shouldThrowRunTimeException() {
        assertThrows(RuntimeException.class, () -> strategyPicker.pick(null));
    }
}
