package lv.igors.policyCalculator.insurancePolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InsuranceObject {
    String name;
    List<InsuranceSubObject> insuranceSubObjectList = new ArrayList<>();

    public void addInsuranceSubObject(InsuranceSubObject insuranceSubObject) {
        insuranceSubObjectList.add(insuranceSubObject);
    }

    public InsuranceObject(String name) {
        this.name = name;
    }

    public List<InsuranceSubObject> getInsuranceSubObjectList() {
        return insuranceSubObjectList;
    }

    public void setInsuranceSubObjectList(List<InsuranceSubObject> insuranceSubObjectList) {
        this.insuranceSubObjectList = insuranceSubObjectList;
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
                ", insuranceObjectList=" + insuranceSubObjectList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsuranceObject that = (InsuranceObject) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(insuranceSubObjectList, that.insuranceSubObjectList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, insuranceSubObjectList);
    }
}
