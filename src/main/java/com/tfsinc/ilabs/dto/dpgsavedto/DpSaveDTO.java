package com.tfsinc.ilabs.dto.dpgsavedto;

import java.util.List;

/**
 * Created by alokprakash.p on 6/11/2015.
 */
public class DpSaveDTO {

    private String name;
    private String status;
    private String codeName;
    private String operator;
    private String condition;
    private String dataPointType;
    private boolean isDiscoverable;
    private String eventCode;
    private boolean isDerived;
    private boolean isMultiValue;
    private List<DpDefVariablesSaveDTO> dataPointDefVariables;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDataPointType() {
        return dataPointType;
    }

    public void setDataPointType(String dataPointType) {
        this.dataPointType = dataPointType;
    }

    public boolean isDiscoverable() {
        return isDiscoverable;
    }

    public void setDiscoverable(boolean isDiscoverable) {
        this.isDiscoverable = isDiscoverable;
    }

    public String getEventCode() {
        return eventCode;
    }

    public void setEventCode(String eventCode) {
        this.eventCode = eventCode;
    }

    public boolean isDerived() {
        return isDerived;
    }

    public void setDerived(boolean isDerived) {
        this.isDerived = isDerived;
    }

    public boolean isMultiValue() {
        return isMultiValue;
    }

    public void setMultiValue(boolean isMultiValue) {
        this.isMultiValue = isMultiValue;
    }

    public List<DpDefVariablesSaveDTO> getDataPointDefVariables() {
        return dataPointDefVariables;
    }

    public void setDataPointDefVariables(List<DpDefVariablesSaveDTO> dataPointDefVariables) {
        this.dataPointDefVariables = dataPointDefVariables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DpSaveDTO dpSaveDTO = (DpSaveDTO) o;

        if (isDerived != dpSaveDTO.isDerived) return false;
        if (isDiscoverable != dpSaveDTO.isDiscoverable) return false;
        if (isMultiValue != dpSaveDTO.isMultiValue) return false;
        if (codeName != null ? !codeName.equals(dpSaveDTO.codeName) : dpSaveDTO.codeName != null) return false;
        if (condition != null ? !condition.equals(dpSaveDTO.condition) : dpSaveDTO.condition != null) return false;
        if (dataPointDefVariables != null ? !dataPointDefVariables.equals(dpSaveDTO.dataPointDefVariables) : dpSaveDTO.dataPointDefVariables != null)
            return false;
        if (dataPointType != null ? !dataPointType.equals(dpSaveDTO.dataPointType) : dpSaveDTO.dataPointType != null)
            return false;
        if (eventCode != null ? !eventCode.equals(dpSaveDTO.eventCode) : dpSaveDTO.eventCode != null) return false;
        if (name != null ? !name.equals(dpSaveDTO.name) : dpSaveDTO.name != null) return false;
        if (operator != null ? !operator.equals(dpSaveDTO.operator) : dpSaveDTO.operator != null) return false;
        if (status != null ? !status.equals(dpSaveDTO.status) : dpSaveDTO.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (codeName != null ? codeName.hashCode() : 0);
        result = 31 * result + (operator != null ? operator.hashCode() : 0);
        result = 31 * result + (condition != null ? condition.hashCode() : 0);
        result = 31 * result + (dataPointType != null ? dataPointType.hashCode() : 0);
        result = 31 * result + (isDiscoverable ? 1 : 0);
        result = 31 * result + (eventCode != null ? eventCode.hashCode() : 0);
        result = 31 * result + (isDerived ? 1 : 0);
        result = 31 * result + (isMultiValue ? 1 : 0);
        result = 31 * result + (dataPointDefVariables != null ? dataPointDefVariables.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DpSaveDTO{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", codeName='" + codeName + '\'' +
                ", operator='" + operator + '\'' +
                ", condition='" + condition + '\'' +
                ", dataPointType='" + dataPointType + '\'' +
                ", isDiscoverable=" + isDiscoverable +
                ", eventCode='" + eventCode + '\'' +
                ", isDerived=" + isDerived +
                ", isMultiValue=" + isMultiValue +
                ", dataPointDefVariables=" + dataPointDefVariables +
                '}';
    }
}
