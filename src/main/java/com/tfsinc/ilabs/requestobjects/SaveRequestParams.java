package com.tfsinc.ilabs.requestobjects;

import com.tfsinc.ilabs.dto.DimensionDTO;
import com.tfsinc.ilabs.dto.dpgsavedto.DpSaveDTO;
import com.tfsinc.ilabs.dto.dpgsavedto.DpgSaveDTO;
import com.tfsinc.ilabs.dto.dpvsavedeleteupdatedto.DpvSaveDTO;
import com.tfsinc.ilabs.dto.dpvsearchdto.DpvDTO;
import com.tfsinc.ilabs.dto.dpvsearchdto.DpvSearchDTO;
import com.tfsinc.ilabs.utils.PropertyUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class containing the request object for the Save/Delete/Update/Add URI
 * Created by alokprakash.p on 6/11/2015.
 */
public class SaveRequestParams {

    public static String getDpgSave(DpgSaveDTO dpgSaveDTO) throws Exception {
        String saveString = "{\"clientName\":\"" + PropertyUtils.getProperty("clientName") + "\",\"dpGroupDetails\":[{\"_id\":\"" + PropertyUtils.getProperty("saveGroupName") + "\",\"status\":\"" + dpgSaveDTO.getStatus() + "\",\"runType\":\"" + dpgSaveDTO.getRunType() + "\",\"dimension\":[\"" + getDimension(dpgSaveDTO) + "\"],\"isLocked\": " + dpgSaveDTO.isLocked() + ",\"dataPointDefs\":[{\"name\":\"" + dpgSaveDTO.getDataPointDefs().get(0).getName() + "\",\"status\":\"" + dpgSaveDTO.getDataPointDefs().get(0).getStatus() + "\",\"codeName\":\"" + dpgSaveDTO.getDataPointDefs().get(0).getCodeName() + "\",\"operator\":\"" + dpgSaveDTO.getDataPointDefs().get(0).getOperator() + "\",\"condition\":\"" + dpgSaveDTO.getDataPointDefs().get(0).getCondition() + "\",\"dataPointType\":\"" + dpgSaveDTO.getDataPointDefs().get(0).getDataPointType() + "\",\"isDiscoverable\":" + dpgSaveDTO.getDataPointDefs().get(0).isDiscoverable() + ",\"eventCode\":\"" + dpgSaveDTO.getDataPointDefs().get(0).getEventCode() + "\",\"isDerived\":" + dpgSaveDTO.getDataPointDefs().get(0).isDerived() + ",\"isMultiValue\":" + dpgSaveDTO.getDataPointDefs().get(0).isMultiValue() + ",\"dataPointDefVariables\":[{\"expVariable\":\"" + dpgSaveDTO.getDataPointDefs().get(0).getDataPointDefVariables().get(0).getExpVariable() + "\",\"jsonPath\":\"" + dpgSaveDTO.getDataPointDefs().get(0).getDataPointDefVariables().get(0).getJsonPath() + "\",\"dataType\":\"" + dpgSaveDTO.getDataPointDefs().get(0).getDataPointDefVariables().get(0).getDataType() + "\"},{\"expVariable\":\"" + dpgSaveDTO.getDataPointDefs().get(0).getDataPointDefVariables().get(1).getExpVariable() + "\",\"jsonPath\":\"" + dpgSaveDTO.getDataPointDefs().get(0).getDataPointDefVariables().get(1).getJsonPath() + "\",\"dataType\":\"" + dpgSaveDTO.getDataPointDefs().get(0).getDataPointDefVariables().get(1).getDataType() + "\"}]}]}]}";
        return saveString;
    }

    private static String getDimension(DpgSaveDTO dpgSaveDTO) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        for (String dimension : dpgSaveDTO.getDimension()) {
            stringBuilder.append("\"" + dimension + "\"");
            stringBuilder.append(",");
        }
        String dimensions = stringBuilder.length() > 0 ? stringBuilder.substring(1, stringBuilder.length() - 2) : "";
        return dimensions;
    }

    public static String getDPSave(DpSaveDTO dpSaveDTO) throws Exception {
        String saveString = "{\"clientName\":\"" + PropertyUtils.getProperty("clientName") + "\",\"dpGroupName\":\"" + PropertyUtils.getProperty("saveGroupName") + "\",\"dataPointDefs\":[{\"name\":\"" + dpSaveDTO.getName() + "\",\"status\":\"" + dpSaveDTO.getStatus() + "\",\"codeName\":\"" + dpSaveDTO.getCodeName() + "\",\"operator\":\"" + dpSaveDTO.getOperator() + "\",\"condition\":\"" + dpSaveDTO.getCondition() + "\",\"dataPointType\":\"" + dpSaveDTO.getDataPointType() + "\",\"isDiscoverable\":" + dpSaveDTO.isDiscoverable() + ",\"eventCode\":\"" + dpSaveDTO.getEventCode() + "\",\"isDerived\":" + dpSaveDTO.isDerived() + ",\"isMultiValue\":" + dpSaveDTO.isMultiValue() + ",\"dataPointDefVariables\":[{\"expVariable\":\"" + dpSaveDTO.getDataPointDefVariables().get(0).getExpVariable() + "\",\"jsonPath\": \"" + dpSaveDTO.getDataPointDefVariables().get(0).getJsonPath() + "\",\"dataType\":\"" + dpSaveDTO.getDataPointDefVariables().get(0).getDataType() + "\"},{\"expVariable\":\"" + dpSaveDTO.getDataPointDefVariables().get(1).getExpVariable() + "\",\"jsonPath\":\"" + dpSaveDTO.getDataPointDefVariables().get(1).getJsonPath() + "\",\"dataType\":\"" + dpSaveDTO.getDataPointDefVariables().get(1).getDataType() + "\"}]}]}";
        return saveString;
    }

    public static String getDPGDelete() throws Exception {
        String deleteDPGString = "{\"clientName\":\"" + PropertyUtils.getProperty("clientName") + "\",\"dpGroupName\":\"" + PropertyUtils.getProperty("saveGroupName") + "\"}";
        return deleteDPGString;
    }

    public static String getDPDelete(String dataPointName) throws Exception {
        String deleteDPString = "{\"clientName\":\"" + PropertyUtils.getProperty("clientName") + "\",\"dpGroupName\":\"" + PropertyUtils.getProperty("saveGroupName") + "\",\"dpName\":\"" + dataPointName + "\"}";
        return deleteDPString;
    }

    public static String getDPVSave(DpvSaveDTO dpvSaveDTO, String startTime, String endTime) throws Exception {
        String saveString = "{\"clientName\":\"" + PropertyUtils.getProperty("clientName") + "\",\"dpGroupName\":\"" + PropertyUtils.getProperty("groupName") + "\",\"dpDetails\":{\"name\":\"" + dpvSaveDTO.getName() + "\",\"codeName\":\"" + dpvSaveDTO.getCodeName() + "\",\"shortDesc\":\"" + dpvSaveDTO.getShortDesc() + "\",\"totalRecordCount\":" + dpvSaveDTO.getTotalRecordCount() + ",\"matchingRecordCount\":" + dpvSaveDTO.getMatchingRecordCount() + ",\"queueName\":\"" + dpvSaveDTO.getQueueName() + "\",\"startTime\":\"" + startTime + "\",\"endTime\":\"" + endTime + "\"}}";
        return saveString;
    }

    //While saving the data point value it save the dpv as string but while update is additive update so it expect as Long so it does not update
    public static String getDPVUpdate(DpvSaveDTO dpvSaveDTO, String startTime, String endTime) throws Exception {
        String saveString = "{\"clientName\":\"" + PropertyUtils.getProperty("clientName") + "\",\"dpGroupName\":\"" + PropertyUtils.getProperty("groupName") + "\",\"dimension\":\"overall\",\"dpDetails\":{\"codeName\":\"matrix1\",\"matchingRecordCount\":99123,\"startTime\":\"" + startTime + "\",\"endTime\":\"" + endTime + "\"}}";
        return saveString;
    }

    //Bug is raised for dim add
    public static String getDPDimADD(DimensionDTO dimensionDTO) throws Exception {
        String addString = "{\"clientName\":\"" + PropertyUtils.getProperty("clientName") + "\",\"dimension\":\"" + dimensionDTO.getName() + "\",\"paths\":[\"" + getDimPaths(dimensionDTO) + "\"]}";
        return addString;
    }

    private static String getDimPaths(DimensionDTO dimensionDTO) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        for (String path : dimensionDTO.getPaths()) {
            stringBuilder.append("\"" + path + "\"");
            stringBuilder.append(",");
        }
        String dimPaths = stringBuilder.length() > 0 ? stringBuilder.substring(1, stringBuilder.length() - 2) : "";
        return dimPaths;
    }
}
