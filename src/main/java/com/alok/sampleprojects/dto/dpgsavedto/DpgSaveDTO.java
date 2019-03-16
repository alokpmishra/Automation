package com.alok.sampleprojects.dto.dpgsavedto;

import com.alok.sampleprojects.dto.DataPointDTO;

import java.util.List;

/**
 * Created by alokprakash.p on 6/11/2015.
 */
public class DpgSaveDTO {
    private String _id;
    private String status;
    private String runType;
    private List<String> dimension;
    private Boolean isLocked;
    private List<DpSaveDTO> dataPointDefs;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }

    public List<String> getDimension() {
        return dimension;
    }

    public void setDimension(List<String> dimension) {
        this.dimension = dimension;
    }

    public Boolean isLocked() {
        return isLocked;
    }

    public void setLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    public List<DpSaveDTO> getDataPointDefs() {
        return dataPointDefs;
    }

    public void setDataPointDefs(List<DpSaveDTO> dataPointDefs) {
        this.dataPointDefs = dataPointDefs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DpgSaveDTO that = (DpgSaveDTO) o;

        if (isLocked != that.isLocked) return false;
        if (_id != null ? !_id.equals(that._id) : that._id != null) return false;
        if (dataPointDefs != null ? !dataPointDefs.equals(that.dataPointDefs) : that.dataPointDefs != null)
            return false;
        if (dimension != null ? !dimension.equals(that.dimension) : that.dimension != null) return false;
        if (runType != null ? !runType.equals(that.runType) : that.runType != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = _id != null ? _id.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (runType != null ? runType.hashCode() : 0);
        result = 31 * result + (dimension != null ? dimension.hashCode() : 0);
        result = 31 * result + (isLocked ? 1 : 0);
        result = 31 * result + (dataPointDefs != null ? dataPointDefs.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DpgSaveDTO{" +
                "_id='" + _id + '\'' +
                ", status='" + status + '\'' +
                ", runType='" + runType + '\'' +
                ", dimension=" + dimension +
                ", isLocked=" + isLocked +
                ", dataPointDefs=" + dataPointDefs +
                '}';
    }
}
