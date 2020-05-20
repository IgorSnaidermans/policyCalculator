package lv.igors.policyCalculator.riskCoefficientMapper;

import lv.igors.policyCalculator.insurancePolicy.Risks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class RiskCoefficientStrategyPicker {
    private List<CoefficientMapperStrategy> strategyList;

    @Autowired
    public RiskCoefficientStrategyPicker(List<CoefficientMapperStrategy> strategies) {
        this.strategyList = strategies;
    }

    public CoefficientMapperStrategy pick(Risks risk) {
        Optional<CoefficientMapperStrategy> optionalStrategy = strategyList.stream()
                .filter(s -> s.support(risk))
                .findFirst();

        return optionalStrategy.orElseThrow(()
                -> new RuntimeException("No coefficient mapper strategy for" + risk));
    }
}
