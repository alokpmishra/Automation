package com.alok.sampleprojects.dto;

/**
 * Created by alokprakash.p on 6/4/2015.
 */
public class DataPointDefVaraiblesDTO {
    private String expVariable;
    private String dpGroup;
    private String dpCode;

    public String getExpVariable() {
        return expVariable;
    }

    public void setExpVariable(String expVariable) {
        this.expVariable = expVariable;
    }

    public String getDpGroup() {
        return dpGroup;
    }

    public void setDpGroup(String dpGroup) {
        this.dpGroup = dpGroup;
    }

    public String getDpCode() {
        return dpCode;
    }

    public void setDpCode(String dpCode) {
        this.dpCode = dpCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataPointDefVaraiblesDTO that = (DataPointDefVaraiblesDTO) o;

        if (dpCode != null ? !dpCode.equals(that.dpCode) : that.dpCode != null) return false;
        if (dpGroup != null ? !dpGroup.equals(that.dpGroup) : that.dpGroup != null) return false;
        if (expVariable != null ? !expVariable.equals(that.expVariable) : that.expVariable != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = expVariable != null ? expVariable.hashCode() : 0;
        result = 31 * result + (dpGroup != null ? dpGroup.hashCode() : 0);
        result = 31 * result + (dpCode != null ? dpCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DataPointDefVaraiblesDTO{" +
                "expVariable='" + expVariable + '\'' +
                ", dpGroup='" + dpGroup + '\'' +
                ", dpCode='" + dpCode + '\'' +
                '}';
    }
}
