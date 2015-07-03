package com.tfsinc.ilabs.utils.adapter;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.tfsinc.ilabs.dto.DataPointDTO;
import com.tfsinc.ilabs.dto.DpgSearchDTO;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Adapter class for mapping id to the name filed for the data point group
 * Created by alokprakash.p on 6/8/2015.
 */
public class DPGroupAdapter implements JsonDeserializer<DpgSearchDTO> {

    @Override
    public DpgSearchDTO deserialize(JsonElement jsonElement, Type type1, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Gson gson = new Gson();
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        String name = jsonObject.get("_id") != null ? jsonObject.get("_id").getAsString() : null;
        String status = jsonObject.get("status") != null ? jsonObject.get("status").getAsString() : null;
        String runType = jsonObject.get("runType") != null ? jsonObject.get("runType").getAsString() : null;
        String type = jsonObject.get("type") != null ? jsonObject.get("type").getAsString() : null;
        JsonArray dimensionJsonArray = jsonObject.get("dimension") != null ? jsonObject.get("dimension").getAsJsonArray() : null;
        Type typeDetail = new TypeToken<List<String>>() {
        }.getType();
        List<String> dimension = gson.fromJson(dimensionJsonArray, typeDetail);
        Type dpTypeDetail = new TypeToken<List<DataPointDTO>>() {
        }.getType();
        List<DataPointDTO> dataPointDTOs = gson.fromJson(jsonObject.get("dataPointDefs").getAsJsonArray(), dpTypeDetail);

        DpgSearchDTO dpgSearchDTO = new DpgSearchDTO();
        dpgSearchDTO.setName(name);
        dpgSearchDTO.setRunType(runType);
        dpgSearchDTO.setStatus(status);
        dpgSearchDTO.setType(type);
        dpgSearchDTO.setDimension(dimension);
        dpgSearchDTO.setDataPointDefs(dataPointDTOs);
        return dpgSearchDTO;
    }
}
