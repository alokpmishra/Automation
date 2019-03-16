package com.alok.sampleprojects.tests;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.alok.sampleprojects.dto.dpgsavedto.DpSaveDTO;
import com.alok.sampleprojects.dto.dpgsavedto.DpgSaveDTO;
import com.alok.sampleprojects.dto.dpvsavedeleteupdatedto.DpvSaveDTO;
import com.alok.sampleprojects.requestobjects.SaveRequestParams;
import com.alok.sampleprojects.utilities.InputProcessor;
import com.alok.sampleprojects.utils.APIController;
import com.alok.sampleprojects.utils.PropertyUtils;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Created by alokprakash.p on 6/11/2015.
 */
public class SaveDeleteApiTests {

    private static final Logger logger = Logger.getLogger(SaveDeleteApiTests.class);

    APIController apiController = null;
    Gson gson = null;
    JsonParser parser = null;

    @BeforeClass
    public void init() throws Exception {
        apiController = new APIController();
        gson = new Gson();
        parser = new JsonParser();
    }

    /**
     * Save API for data point group.
     *
     * @testcase Save API for data point group
     * @teststep Create the request object.
     * @teststep Get the response by making POST request convert into the dto object.
     * @teststep Get the data from the mongo and convert into the dto object.
     */
    @Test(dataProvider = "dpgSave", groups = {"Save","Sanity"},priority = 1)
    public void testDpgSave(DpgSaveDTO dpgSaveDTO) throws Exception {
        String paramValue = SaveRequestParams.getDpgSave(dpgSaveDTO);
        String response = apiController.makePostCall("dpg/save", "json", paramValue, false);
        DpgSaveDTO dpgSaveActualValue = apiController.getDpgSaveDTOFromMongo("data_point_group", PropertyUtils.getProperty("saveGroupName"));
        assertEquals(dpgSaveActualValue, dpgSaveDTO, "Expected and actual value does not match using data point group save api");
        assertEquals(response.toString(), "{\"dpg_save\":\"Success\"}", "Expected and actual value does not match for the response message");
    }

    /**
     * Save API for data point.
     *
     * @testcase Save API for data point
     * @teststep Create the request object.
     * @teststep Get the response by making POST request convert into the dto object.
     * @teststep Get the data from the mongo and convert into the dto object.
     */
    @Test(dataProvider = "dpSave", groups = {"Save","Sanity"},priority = 2)
    public void testDpSave(DpSaveDTO dpSaveDTO) throws Exception {
        String paramValue = SaveRequestParams.getDPSave(dpSaveDTO);
        String response = apiController.makePostCall("dp/save", "json", paramValue, false);
        DpgSaveDTO dpgSaveDTO = apiController.getDpSaveDTOFromMongo("data_point_group", dpSaveDTO.getName(), PropertyUtils.getProperty("saveGroupName"));
        //Check for the size it should be 1
        assertEquals(dpgSaveDTO.getDataPointDefs().size(), 1, "More than one data point found");
        DpSaveDTO dpSaveActualValue = dpgSaveDTO.getDataPointDefs().get(0);
        assertEquals(dpSaveActualValue, dpSaveDTO, "Expected and actual value does not match using data point definition save api");
        assertEquals(response.toString(), "{\"dp_save\":\"Success\"}", "Expected and actual value does not match for the response message");
    }

    /**
     * Delete API for data point.
     *
     * @testcase Delete API for data point
     * @teststep Create the request object.
     * @teststep Get the response by making POST request convert into the dto object.
     * @teststep Get the data from the mongo and convert into the dto object.
     */
    @Test(groups = {"Delete","Sanity"},priority = 3)
    public void testDpDelete() throws Exception {
        String paramValue = SaveRequestParams.getDPDelete(PropertyUtils.getProperty("deleteDPName"));
        String response = apiController.makeDeleteCall("dp/delete", paramValue);
        assertEquals(response.toString(), "{\"dp_delete\":\"Success\"}", "Expected and actual value does not match for the response message");
        DpgSaveDTO dpgSaveDTO = apiController.getDpSaveDTOFromMongo("data_point_group", PropertyUtils.getProperty("deleteDPName"), PropertyUtils.getProperty("saveGroupName"));
        assertEquals(null, dpgSaveDTO, "Data Point is found in the dto object");
        System.out.print(2);
    }

    /**
     * Delete API for data point group.
     *
     * @testcase Delete API for data point group
     * @teststep Create the request object.
     * @teststep Get the response by making POST request convert into the dto object.
     * @teststep Get the data from the mongo and convert into the dto object.
     */

    @Test(groups = {"Delete","Sanity"},priority = 4)
    public void testDpgDelete() throws Exception {
        String paramValue = SaveRequestParams.getDPGDelete();
        String response = apiController.makeDeleteCall("dpg/delete", paramValue);
        assertEquals(response.toString(), "{\"dpg_delete\":\"Success\"}", "Expected and actual value does not match for the response message");
        DpgSaveDTO dpgSaveDTO = apiController.getDpgSaveDTOFromMongo("data_point_group", PropertyUtils.getProperty("saveGroupName"));
        assertEquals(null, dpgSaveDTO, "Data Point group is found in the dto object");
    }

    /**
     * Save API for data point value.
     *
     * @testcase Save API for data point value
     * @teststep Create the request object.
     * @teststep Get the response by making POST request convert into the dto object.
     * @teststep Get the data from the mongo and convert into the dto object.
     */
    @Test(groups = {"Save","Sanity"},dataProvider = "dpvSave", priority = 5)
    public void testDpvSave(DpvSaveDTO dpvSaveDTO) throws Exception {
        String paramValue = SaveRequestParams.getDPVSave(dpvSaveDTO, "2015-05-05T05:00:00.000Z", "2015-05-05T06:00:00.000Z");
        String response = apiController.makePostCall("dpv/save", "json", paramValue, false);
        String collectionName = PropertyUtils.getProperty("groupName") + "_" + "hourly";
        DpvSaveDTO dpSaveDTOActual = apiController.getDpvSaveDTOFromMongo(collectionName, dpvSaveDTO.get_id());
        assertEquals(dpSaveDTOActual, dpvSaveDTO, "Expected and actual value does not match using data point value save api");
        assertEquals(response.toString(), "{\"dpv_save\":true}", "Expected and actual value does not match for the response message");
    }


    @DataProvider(name = "dpgSave")
    public Object[][] getDPGroupData() throws Exception {
        return apiController.getInputDetails("/spirit/api/dpgSave.txt", DpgSaveDTO[].class);
    }

    @DataProvider(name = "dpSave")
    public Object[][] getDPData() throws Exception {
        return apiController.getInputDetails("/spirit/api/dpSave.txt", DpSaveDTO[].class);
    }

    @DataProvider(name = "dpvSave")
    public Object[][] getDPVData() throws Exception {
        return apiController.getInputDetails("/spirit/api/dpvSave.txt", DpvSaveDTO[].class);
    }
}
