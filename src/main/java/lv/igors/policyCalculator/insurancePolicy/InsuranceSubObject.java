package lv.igors.policyCalculator.insurancePolicy;

import lv.igors.policyCalculator.premium.Risks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InsuranceSubObject {
    String name;
    BigDecimal insuredCost;
    List<Risks> insuredRisks = new ArrayList<>();

    private InsuranceSubObject(Builder builder) {
        setName(builder.name);
        setInsuredCost(builder.insuredCost);
    }

    public List<Risks> getInsuredRisks() {
        return insuredRisks;
    }

    public void setInsuredRisks(List<Risks> insuredRisks) {
        this.insuredRisks = insuredRisks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getInsuredCost() {
        return insuredCost;
    }

    public void setInsuredCost(BigDecimal insuredCost) {
        this.insuredCost = insuredCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsuranceSubObject that = (InsuranceSubObject) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(insuredCost, that.insuredCost) &&
                Objects.equals(insuredRisks, that.insuredRisks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, insuredCost, insuredRisks);
    }

    @Override
    public String toString() {
        return "InsuranceObject{" +
                "name='" + name + '\'' +
                ", insuredCost=" + insuredCost +
                ", insuredRisks=" + insuredRisks +
                '}';
    }

    public static final class Builder {
        private String name;
        private BigDecimal insuredCost;

        public Builder() {
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder insuredCost(BigDecimal val) {
            insuredCost = val;
            return this;
        }

        public InsuranceSubObject build() {
            return new InsuranceSubObject(this);
        }
    }
}
