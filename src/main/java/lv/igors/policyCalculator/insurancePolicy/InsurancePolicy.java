package lv.igors.policyCalculator.insurancePolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InsurancePolicy {
    String number;
    PolicyStatus policyStatus;
    List<InsuranceObject> insuranceObjectList = new ArrayList<>();

    private InsurancePolicy(Builder builder) {
        setNumber(builder.number);
        setPolicyStatus(builder.policyStatus);
    }

    public void addInsuranceObject(InsuranceObject object) {
        insuranceObjectList.add(object);
    }

    public List<InsuranceObject> getInsuranceObjectList() {
        return insuranceObjectList;
    }

    public void setInsuranceObjectList(List<InsuranceObject> insuranceObjectList) {
        this.insuranceObjectList = insuranceObjectList;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public PolicyStatus getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(PolicyStatus policyStatus) {
        this.policyStatus = policyStatus;
    }

    @Override
    public String toString() {
        return "InsurancePolicy{" +
                "number='" + number + '\'' +
                ", policyStatus=" + policyStatus +
                ", insuranceObjectList=" + insuranceObjectList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsurancePolicy that = (InsurancePolicy) o;
        return Objects.equals(number, that.number) &&
                policyStatus == that.policyStatus &&
                Objects.equals(insuranceObjectList, that.insuranceObjectList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, policyStatus, insuranceObjectList);
    }


    public static final class Builder {
        private String number;
        private PolicyStatus policyStatus;

        public Builder() {
        }

        public Builder number(String val) {
            number = val;
            return this;
        }

        public Builder policyStatus(PolicyStatus val) {
            policyStatus = val;
            return this;
        }

        public InsurancePolicy build() {
            return new InsurancePolicy(this);
        }
    }
}
