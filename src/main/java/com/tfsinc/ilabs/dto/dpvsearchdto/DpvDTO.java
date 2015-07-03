package com.tfsinc.ilabs.dto.dpvsearchdto;

import java.util.List;

/**
 * Created by alokprakash.p on 6/9/2015.
 */
public class DpvDTO {
    private String status;
    private List<DpvSearchDTO> data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<DpvSearchDTO> getData() {
        return data;
    }

    public void setData(List<DpvSearchDTO> data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DpvDTO dpvDTO = (DpvDTO) o;

        if (data != null ? !data.equals(dpvDTO.data) : dpvDTO.data != null) return false;
        if (status != null ? !status.equals(dpvDTO.status) : dpvDTO.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = status != null ? status.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DpvDTO{" +
                "status='" + status + '\'' +
                ", data=" + data +
                '}';
    }
}
