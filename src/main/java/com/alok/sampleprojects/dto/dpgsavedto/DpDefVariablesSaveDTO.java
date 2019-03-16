package com.alok.sampleprojects.dto.dpgsavedto;

/**
 * Created by alokprakash.p on 6/11/2015.
 */
public class DpDefVariablesSaveDTO {

    private String expVariable;
    private String jsonPath;
    private String dataType;

    public String getExpVariable() {
        return expVariable;
    }

    public void setExpVariable(String expVariable) {
        this.expVariable = expVariable;
    }

    public String getJsonPath() {
        return jsonPath;
    }

    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DpDefVariablesSaveDTO that = (DpDefVariablesSaveDTO) o;

        if (dataType != null ? !dataType.equals(that.dataType) : that.dataType != null) return false;
        if (expVariable != null ? !expVariable.equals(that.expVariable) : that.expVariable != null) return false;
        if (jsonPath != null ? !jsonPath.equals(that.jsonPath) : that.jsonPath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = expVariable != null ? expVariable.hashCode() : 0;
        result = 31 * result + (jsonPath != null ? jsonPath.hashCode() : 0);
        result = 31 * result + (dataType != null ? dataType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DpDefVariablesSaveDTO{" +
                "expVariable='" + expVariable + '\'' +
                ", jsonPath='" + jsonPath + '\'' +
                ", dataType='" + dataType + '\'' +
                '}';
    }
}
