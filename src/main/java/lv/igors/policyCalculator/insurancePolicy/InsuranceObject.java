package lv.igors.policyCalculator.insurancePolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InsuranceObject {
    String name;
    List<InsuranceSubObject> insuranceSubObjects = new ArrayList<>();

    public InsuranceObject(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InsuranceObjectsList{" +
                "name='" + name + '\'' +
                ", insuranceObjects=" + insuranceSubObjects +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsuranceObject that = (InsuranceObject) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(insuranceSubObjects, that.insuranceSubObjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, insuranceSubObjects);
    }
}
