package com.tfsinc.ilabs.requestobjects;

import com.tfsinc.ilabs.dto.dpvsearchdto.DpvDTO;
import com.tfsinc.ilabs.dto.dpvsearchdto.DpvSearchDTO;
import com.tfsinc.ilabs.utils.PropertyUtils;

/**
 * This Class contains the param values related to the search api
 * Created by alokprakash.p on 6/5/2015.
 */
public class SearchRequestParams {

    public enum AggregateValue {
        HOURLY("Hourly"), DAILY("Daily"), WEEKLY("Weekly"), MONTHLY("Monthly");
        private String value;

        AggregateValue(String value) {
            this.value = value;
        }

    }

    public static String getDataPointGroup() throws Exception {
        String searchString = "{\"clientName\":\"" + PropertyUtils.getProperty("clientName") + "\",\"dpGroupName\":\"" + PropertyUtils.getProperty("groupName") + "\"}";
        return searchString;
    }


    public static String getDataPointDefinition() throws Exception {
        String searchString = "{\"clientName\":\"" + PropertyUtils.getProperty("clientName") + "\",\"dpGroupName\":\"" + PropertyUtils.getProperty("groupName") + "\",\"dpName\":\"Matrix1\"}";
        return searchString;
    }

    public static String getDataPointDimension() throws Exception {
        String searchString = "{\"clientName\":\"" + PropertyUtils.getProperty("clientName") + "\"}";
        return searchString;
    }


    public static String getInvalidGroup() {
        String searchString = "{\"clientName\":\"Optusa\",\"dpGroupName\":\"groupMatrix\"}";
        return searchString;
    }

    public static String getDataPointValueOverAll(AggregateValue aggregateValue, DpvSearchDTO dpvSearchDTO, boolean isClientZone) throws Exception {
        String searchString = "{\"clientName\":[\"" + PropertyUtils.getProperty("clientName") + "\"],\"startTime\":\"" + dpvSearchDTO.getData().get(0).getStartDate() + "\",\"endTime\":\"" + dpvSearchDTO.getData().get(0).getEndDate() + "\",\"dpMetric\":[{\"groupName\":\"" + dpvSearchDTO.getGroupName() + "\",\"dimension\":[{\"name\":\"" + dpvSearchDTO.getDimensionName() + "\"}],\"dpCodes\":[\"" + dpvSearchDTO.getDpCode() + "\"]}],\"aggregate\":\"" + aggregateValue.value + "\",\"isClientTimeZone\":" + isClientZone + "}";
        return searchString;
    }

    public static String getDataPointValueOverAll(AggregateValue aggregateValue, DpvSearchDTO dpvSearchDTO) throws Exception {
        return (getDataPointValueOverAll(aggregateValue, dpvSearchDTO, false));
    }

    public static String getDataPointValueOverAll(AggregateValue aggregateValue, DpvDTO dpvDTO, boolean isClientZone) throws Exception {
        String searchString = "{\"clientName\":[\"" + PropertyUtils.getProperty("clientName") + "\"],\"startTime\":\"" + dpvDTO.getData().get(0).getData().get(0).getStartDate() + "\",\"endTime\":\"" + dpvDTO.getData().get(0).getData().get(0).getEndDate() + "\",\"dpMetric\":[{\"groupName\":\"" + dpvDTO.getData().get(0).getGroupName() + "\",\"dimension\":[{\"name\":\"" + dpvDTO.getData().get(0).getDimensionName() + "\"}],\"dpCodes\":[\"" + getDPCodes(dpvDTO) + "\"]}],\"aggregate\":\"" + aggregateValue.value + "\",\"isClientTimeZone\":" + isClientZone + "}";
        return searchString;
    }

    public static String getDataPointValueOverAll(AggregateValue aggregateValue, DpvDTO dpvDTO) throws Exception {
        return (getDataPointValueOverAll(aggregateValue, dpvDTO, false));
    }

    public static String getDataPointValueByQueue(AggregateValue aggregateValue, DpvDTO dpvDTO, boolean isClientZone) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        for (DpvSearchDTO dpvSearchDTO : dpvDTO.getData()) {
            stringBuilder.append("\"" + dpvSearchDTO.getDimensionValue() + "\"");
            stringBuilder.append(",");
        }
        String dimensionValue = stringBuilder.length() > 0 ? stringBuilder.substring(1, stringBuilder.length() - 2) : "";
        String searchString = "{\"clientName\":[\"" + PropertyUtils.getProperty("clientName") + "\"],\"startTime\":\"" + dpvDTO.getData().get(0).getData().get(0).getStartDate() + "\",\"endTime\":\"" + dpvDTO.getData().get(0).getData().get(0).getEndDate() + "\",\"dpMetric\":[{\"groupName\":\"" + dpvDTO.getData().get(0).getGroupName() + "\",\"dimension\":[{\"name\":\"" + dpvDTO.getData().get(0).getDimensionName() + "\",\"values\":[\"" + dimensionValue + "\"]}],\"dpCodes\":[\"" + getDPCodes(dpvDTO) + "\"]}],\"aggregate\":\"" + aggregateValue.value + "\",\"isClientTimeZone\":" + isClientZone + "}";
        return searchString;
    }

    public static String getDataPointValueByQueue(AggregateValue aggregateValue, DpvDTO dpvDTO) throws Exception {
        return (getDataPointValueByQueue(aggregateValue, dpvDTO, false));
    }

    private static String getDPCodes(DpvDTO dpvDTO) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        for (DpvSearchDTO dpvSearchDTO : dpvDTO.getData()) {
            stringBuilder.append("\"" + dpvSearchDTO.getDpCode() + "\"");
            stringBuilder.append(",");
        }
        String dpCodes = stringBuilder.length() > 0 ? stringBuilder.substring(1, stringBuilder.length() - 2) : "";
        return dpCodes;
    }


}
