package com.tfsinc.ilabs.dto;

import java.util.List;

/**
 * created by alokprakash.p on 6/4/2015.
 */
public class DpgSearchDTO {

    private List<DataPointDTO> dataPointDefs;
    private List<String> dimension;
    private boolean isLocked;
    private String name;
    private String runType;
    private String status;
    private String type;
    private List<Object> uniqueOn;

    public List<DataPointDTO> getDataPointDefs() {
        return dataPointDefs;
    }

    public void setDataPointDefs(List<DataPointDTO> dataPointDefs) {
        this.dataPointDefs = dataPointDefs;
    }

    public List<String> getDimension() {
        return dimension;
    }

    public void setDimension(List<String> dimension) {
        this.dimension = dimension;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Object> getUniqueOn() {
        return uniqueOn;
    }

    public void setUniqueOn(List<Object> uniqueOn) {
        this.uniqueOn = uniqueOn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DpgSearchDTO that = (DpgSearchDTO) o;

        if (isLocked != that.isLocked) return false;
        if (dataPointDefs != null ? !dataPointDefs.equals(that.dataPointDefs) : that.dataPointDefs != null)
            return false;
        if (dimension != null ? !dimension.equals(that.dimension) : that.dimension != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (runType != null ? !runType.equals(that.runType) : that.runType != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (uniqueOn != null ? !uniqueOn.equals(that.uniqueOn) : that.uniqueOn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = dataPointDefs != null ? dataPointDefs.hashCode() : 0;
        result = 31 * result + (dimension != null ? dimension.hashCode() : 0);
        result = 31 * result + (isLocked ? 1 : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (runType != null ? runType.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (uniqueOn != null ? uniqueOn.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DpgSearchDTO{" +
                "dataPointDefs=" + dataPointDefs +
                ", dimension=" + dimension +
                ", isLocked=" + isLocked +
                ", name='" + name + '\'' +
                ", runType='" + runType + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                ", uniqueOn=" + uniqueOn +
                '}';
    }
}

