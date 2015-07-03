package com.tfsinc.ilabs.dto.dpvsearchdto;

import java.util.List;

/**
 * Created by alokprakash.p on 6/9/2015.
 */
public class DpvSearchDTO {
    private String clientName;
    private String groupName;
    private String dimensionName;
    private String dimensionValue;
    private String dpCode;
    private List<DataDTO> Data;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDimensionName() {
        return dimensionName;
    }

    public void setDimensionName(String dimensionName) {
        this.dimensionName = dimensionName;
    }

    public String getDimensionValue() {
        return dimensionValue;
    }

    public void setDimensionValue(String dimensionValue) {
        this.dimensionValue = dimensionValue;
    }

    public String getDpCode() {
        return dpCode;
    }

    public void setDpCode(String dpCode) {
        this.dpCode = dpCode;
    }

    public List<DataDTO> getData() {
        return Data;
    }

    public void setData(List<DataDTO> data) {
        Data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DpvSearchDTO that = (DpvSearchDTO) o;

        if (Data != null ? !Data.equals(that.Data) : that.Data != null) return false;
        if (clientName != null ? !clientName.equals(that.clientName) : that.clientName != null) return false;
        if (dimensionName != null ? !dimensionName.equals(that.dimensionName) : that.dimensionName != null)
            return false;
        if (dpCode != null ? !dpCode.equals(that.dpCode) : that.dpCode != null) return false;
        if (dimensionValue != null ? !dimensionValue.equals(that.dimensionValue) : that.dimensionValue != null)
            return false;
        if (groupName != null ? !groupName.equals(that.groupName) : that.groupName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = clientName != null ? clientName.hashCode() : 0;
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        result = 31 * result + (dimensionName != null ? dimensionName.hashCode() : 0);
        result = 31 * result + (dimensionValue != null ? dimensionValue.hashCode() : 0);
        result = 31 * result + (dpCode != null ? dpCode.hashCode() : 0);
        result = 31 * result + (Data != null ? Data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DpvSearchDTO{" +
                "clientName='" + clientName + '\'' +
                ", groupName='" + groupName + '\'' +
                ", dimensionName='" + dimensionName + '\'' +
                ", dimensionValue='" + dimensionValue + '\'' +
                ", dpCode='" + dpCode + '\'' +
                ", Data=" + Data +
                '}';
    }
}
