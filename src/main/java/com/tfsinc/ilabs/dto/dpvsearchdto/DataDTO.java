package com.tfsinc.ilabs.dto.dpvsearchdto;

/**
 * Created by alokprakash.p on 6/9/2015.
 */
public class DataDTO {
    private String startDate;
    private String endDate;
    private String value;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataDTO dataDTO = (DataDTO) o;

        if (endDate != null ? !endDate.equals(dataDTO.endDate) : dataDTO.endDate != null) return false;
        if (startDate != null ? !startDate.equals(dataDTO.startDate) : dataDTO.startDate != null) return false;
        if (value != null ? !value.equals(dataDTO.value) : dataDTO.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = startDate != null ? startDate.hashCode() : 0;
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DataDTO{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
