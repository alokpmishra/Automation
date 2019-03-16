package com.alok.sampleprojects.tests;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.alok.sampleprojects.dto.DataPointDTO;
import com.alok.sampleprojects.dto.DimSearchDTO;
import com.alok.sampleprojects.dto.DimensionDTO;
import com.alok.sampleprojects.dto.DpgSearchDTO;
import com.alok.sampleprojects.dto.dpvsearchdto.DataDTO;
import com.alok.sampleprojects.dto.dpvsearchdto.DpvDTO;
import com.alok.sampleprojects.dto.dpvsearchdto.DpvSearchDTO;
import com.alok.sampleprojects.requestobjects.SearchRequestParams;
import com.alok.sampleprojects.utils.APIController;
import com.alok.sampleprojects.utils.PropertyUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by alokprakash.p on 6/2/2015.
 */
public class SearchApiTests {

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
     * Search API for data point group.
     *
     * @testcase Search API for data point group
     * @teststep Create the request object.
     * @teststep Get the response by making GET request convert into the dto object.
     * @teststep Get the data from the mongo and convert into the dto object.
     */
    @Test(groups = {"Search","Sanity"})
    public void testDpgSearch() throws Exception {
        try {
            String paramValue = SearchRequestParams.getDataPointGroup();
            String response = apiController.makeGetCall("dpg/search", "json", paramValue);
            DpgSearchDTO dpgSearchDTOActual = gson.fromJson(parser.parse(response).getAsJsonObject().get("dpg_search"), DpgSearchDTO.class);
            DpgSearchDTO expectedValue = apiController.getDpgSearchDTOFromMongo("data_point_group");
            assertEquals(dpgSearchDTOActual, expectedValue, "Expected and actual value does not match using data point group search api");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Search API for the data point
     *
     * @teststep Create the request object.
     * @teststep Get the response by making GET request convert into the dto object.
     * @teststep Get the data from the mongo and convert into the dto object.
     * @teststep Compare both expected and actual dto.
     */
    @Test(groups = {"Search","Sanity"})
    public void testDPSearch() throws Exception {
        try {
            String paramValueDPDefinition = SearchRequestParams.getDataPointDefinition();
            String response = apiController.makeGetCall("dp/search", "json", paramValueDPDefinition);
            DataPointDTO dataPointDTOActual = gson.fromJson(parser.parse(response).getAsJsonObject().get("dp_search"), DataPointDTO.class);
            DpgSearchDTO dpgSearchDTO = apiController.getDpSearchDTOFromMongo("data_point_group", "Matrix1");
            //Check for the size it should be 1
            assertEquals(dpgSearchDTO.getDataPointDefs().size(), 1, "More than one data point found");
            DataPointDTO expectedValue = dpgSearchDTO.getDataPointDefs().get(0);
            assertEquals(dataPointDTOActual, expectedValue, "Expected and actual value does not match using data point definition search api");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Search API for the data point dimension
     *
     * @teststep Create the request object.
     * @teststep Get the response by making GET request convert into the dto object.
     * @teststep Get the data from the mongo and convert into the dto object.
     * @teststep Compare both expected and actual dto.
     */
    @Test(groups = {"Search","Sanity"})
    public void testDPDimension() throws Exception {
        try {
            String paramValueDPDimension = SearchRequestParams.getDataPointDimension();
            String response = apiController.makeGetCall("dim/search", "json", paramValueDPDimension);
            List<String> dataPointDimActual = gson.fromJson(parser.parse(response).getAsJsonObject().get("dim_search"), List.class);
            DimSearchDTO dimSearchDTO = apiController.getDpDimSearchDTOFromMongo(PropertyUtils.getProperty("clientName"));
            List<String> expectedValue = new ArrayList<String>();
            List<DimensionDTO> dimensions = dimSearchDTO.getDimension();
            for (DimensionDTO dimName : dimensions) {
                expectedValue.add(dimName.getName());
            }
            assertEquals(dataPointDimActual, expectedValue, "Expected and actual value does not match using data point dimension search api");
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Search API for the data point values with dimension Overall and aggregate hourly
     *
     * @teststep Create the dto object (expected dto)
     * @teststep Create request object for Overall and aggregated as hourly with client timezone false
     * @teststep Get the response as part of the dto object(actual dto).
     * @teststep Compare both expected and actual dto.
     * @teststep Create request object for Overall and aggregated as hourly with client timezone true
     * @teststep Get the response as part of the dto object(actual dto).
     * @teststep Compare both expected and actual dto.
     */
    @Test(groups = {"Search","Sanity"})
    public void testDPValueOverAllHourlyWithWithoutTimeZone() throws Exception {
        DataDTO dataDTO = new DataDTO();
        dataDTO.setStartDate("05-05-2015 05:00");
        dataDTO.setEndDate("05-05-2015 06:00");
        dataDTO.setValue("202");

        DataDTO dataDTO1 = new DataDTO();
        dataDTO1.setStartDate("05-05-2015 06:00");
        dataDTO1.setEndDate("05-05-2015 07:00");
        dataDTO1.setValue("0");

        List<DataDTO> dataDTOs = new ArrayList<DataDTO>();
        dataDTOs.add(dataDTO);
        dataDTOs.add(dataDTO1);

        DpvSearchDTO dpvSearchDTO = new DpvSearchDTO();
        dpvSearchDTO.setGroupName("funnel_overall_overall_std");
        dpvSearchDTO.setClientName(PropertyUtils.getProperty("clientName"));
        dpvSearchDTO.setData(dataDTOs);
        dpvSearchDTO.setDimensionName("OverAll");
        dpvSearchDTO.setDpCode("hb");
        dpvSearchDTO.setDimensionValue("default");

        List<DpvSearchDTO> dpvSearchDTOs = new ArrayList<DpvSearchDTO>();
        dpvSearchDTOs.add(dpvSearchDTO);

        DpvDTO dpvDTOExpected = new DpvDTO();
        dpvDTOExpected.setStatus("Success");
        dpvDTOExpected.setData(dpvSearchDTOs);

        String paramValueDPValue = SearchRequestParams.getDataPointValueOverAll(SearchRequestParams.AggregateValue.HOURLY, dpvSearchDTO);
        String response = apiController.makeGetCall("dpv/search", "criteria", paramValueDPValue);
        DpvDTO dataPointValueActual = gson.fromJson(parser.parse(response).getAsJsonObject(), DpvDTO.class);

        //Without Time Zone
        assertEquals(dataPointValueActual, dpvDTOExpected, "Expected and actual value does not match using data point value Overall hourly search without time zone api");


        dataDTO.setValue("198");

        List<DataDTO> dataDTOTimeZone = new ArrayList<DataDTO>();
        dataDTOTimeZone.add(dataDTO);
        dataDTOTimeZone.add(dataDTO1);

        dpvSearchDTO.setData(dataDTOTimeZone);

        List<DpvSearchDTO> dpvSearchDTOTimeZone = new ArrayList<DpvSearchDTO>();
        dpvSearchDTOTimeZone.add(dpvSearchDTO);

        dpvDTOExpected.setData(dpvSearchDTOTimeZone);

        String paramValueDPValueWithTimeZone = SearchRequestParams.getDataPointValueOverAll(SearchRequestParams.AggregateValue.HOURLY, dpvSearchDTO, true);
        String responseWithTimeZone = apiController.makeGetCall("dpv/search", "criteria", paramValueDPValueWithTimeZone);
        DpvDTO dataPointValueActualWithTimeZone = gson.fromJson(parser.parse(responseWithTimeZone).getAsJsonObject(), DpvDTO.class);

        //With Time Zone
        assertEquals(dataPointValueActualWithTimeZone, dpvDTOExpected, "Expected and actual value does not match using data point value Overall Daily search with time zone api");
    }

    /**
     * Search API for the data point values with dimension Overall and aggregate Daily
     *
     * @teststep Create the dto object (expected dto)
     * @teststep Create request object for Overall and aggregated as daily with client timezone false
     * @teststep Get the response as part of the dto object(actual dto).
     * @teststep Compare both expected and actual dto.
     * TODO With the client timezone
     * @teststep Create request object for Overall and aggregated as daily with client timezone true
     * @teststep Get the response as part of the dto object(actual dto).
     * @teststep Compare both expected and actual dto.
     */
    @Test(groups = {"Search","Sanity"})
    public void testDPValueOverAllDailyWithWithoutTimeZone() throws Exception {
        DataDTO dataDTO = new DataDTO();
        dataDTO.setStartDate("05-05-2015");
        dataDTO.setEndDate("06-05-2015");
        dataDTO.setValue("300");

        DataDTO dataDTO1 = new DataDTO();
        dataDTO1.setStartDate("06-05-2015");
        dataDTO1.setEndDate("06-05-2015");
        dataDTO1.setValue("0");

        DataDTO dataDTO2 = new DataDTO();
        dataDTO2.setStartDate("05-05-2015");
        dataDTO2.setEndDate("06-05-2015");
        dataDTO2.setValue("135.71428571428572");


        List<DataDTO> dataDTOs = new ArrayList<DataDTO>();
        dataDTOs.add(dataDTO);
        dataDTOs.add(dataDTO1);

        List<DataDTO> dataDTOs1 = new ArrayList<DataDTO>();
        dataDTOs1.add(dataDTO2);
        dataDTOs1.add(dataDTO1);

        DpvSearchDTO dpvSearchDTO = new DpvSearchDTO();
        dpvSearchDTO.setGroupName("funnel_overall_overall_std");
        dpvSearchDTO.setClientName(PropertyUtils.getProperty("clientName"));
        dpvSearchDTO.setData(dataDTOs);
        dpvSearchDTO.setDimensionName("OverAll");
        dpvSearchDTO.setDpCode("hb");
        dpvSearchDTO.setDimensionValue("default");

        DpvSearchDTO dpvSearchDTO1 = new DpvSearchDTO();
        dpvSearchDTO1.setGroupName("funnel_overall_overall_std");
        dpvSearchDTO1.setClientName(PropertyUtils.getProperty("clientName"));
        dpvSearchDTO1.setData(dataDTOs1);
        dpvSearchDTO1.setDimensionName("OverAll");
        dpvSearchDTO1.setDpCode("connecrd");
        dpvSearchDTO1.setDimensionValue("default");

        List<DpvSearchDTO> dpvSearchDTOs = new ArrayList<DpvSearchDTO>();
        dpvSearchDTOs.add(dpvSearchDTO1);
        dpvSearchDTOs.add(dpvSearchDTO);


        DpvDTO dpvDTOExpected = new DpvDTO();
        dpvDTOExpected.setStatus("Success");
        dpvDTOExpected.setData(dpvSearchDTOs);

        String paramValueDPValue = SearchRequestParams.getDataPointValueOverAll(SearchRequestParams.AggregateValue.DAILY, dpvDTOExpected);
        String response = apiController.makeGetCall("dpv/search", "criteria", paramValueDPValue);
        DpvDTO dataPointValueActual = gson.fromJson(parser.parse(response).getAsJsonObject(), DpvDTO.class);

        //Without Time Zone
        assertEquals(dataPointValueActual, dpvDTOExpected, "Expected and actual value does not match using data point value Overall hourly search without time zone api");
    }

    /**
     * Search API for the data point values with dimension Overall and aggregate Weekly
     *
     * @teststep Create the dto object (expected dto)
     * @teststep Create request object for Overall and aggregated as daily with client timezone false
     * @teststep Get the response as part of the dto object(actual dto).
     * @teststep Compare both expected and actual dto.
     */
    @Test(groups = {"Search","Sanity"})
    public void testDPValueOverAllWeekly() throws Exception {
        DataDTO dataDTO = new DataDTO();
        dataDTO.setStartDate("27-04-2015");
        dataDTO.setEndDate("04-05-2015");
        dataDTO.setValue("162");

        List<DataDTO> dataDTOs = new ArrayList<DataDTO>();
        dataDTOs.add(dataDTO);

        DpvSearchDTO dpvSearchDTO = new DpvSearchDTO();
        dpvSearchDTO.setGroupName("funnel_overall_overall_std");
        dpvSearchDTO.setClientName(PropertyUtils.getProperty("clientName"));
        dpvSearchDTO.setData(dataDTOs);
        dpvSearchDTO.setDimensionName("OverAll");
        dpvSearchDTO.setDpCode("cc");
        dpvSearchDTO.setDimensionValue("default");

        List<DpvSearchDTO> dpvSearchDTOs = new ArrayList<DpvSearchDTO>();
        dpvSearchDTOs.add(dpvSearchDTO);

        DpvDTO dpvDTOExpected = new DpvDTO();
        dpvDTOExpected.setStatus("Success");
        dpvDTOExpected.setData(dpvSearchDTOs);

        String paramValueDPValue = SearchRequestParams.getDataPointValueOverAll(SearchRequestParams.AggregateValue.WEEKLY, dpvDTOExpected);
        String response = apiController.makeGetCall("dpv/search", "criteria", paramValueDPValue);
        DpvDTO dataPointValueActual = gson.fromJson(parser.parse(response).getAsJsonObject(), DpvDTO.class);

        //Without Time Zone
        assertEquals(dataPointValueActual, dpvDTOExpected, "Expected and actual value does not match using data point value Overall Weekly search without time zone api");
    }

    /**
     * Search API for the data point values with dimension Overall and aggregate Monthly
     *
     * @teststep Create the dto object (expected dto)
     * @teststep Create request object for Overall and aggregated as daily with client timezone false
     * @teststep Get the response as part of the dto object(actual dto).
     * @teststep Compare both expected and actual dto.
     */
    @Test(groups = {"Search","Sanity"})
    public void testDPValueOverAllMonthly() throws Exception {
        DataDTO dataDTO = new DataDTO();
        dataDTO.setStartDate("01-05-2015");
        dataDTO.setEndDate("01-06-2015");
        dataDTO.setValue("35");

        List<DataDTO> dataDTOs = new ArrayList<DataDTO>();
        dataDTOs.add(dataDTO);

        DpvSearchDTO dpvSearchDTO = new DpvSearchDTO();
        dpvSearchDTO.setGroupName("funnel_overall_overall_std");
        dpvSearchDTO.setClientName(PropertyUtils.getProperty("clientName"));
        dpvSearchDTO.setData(dataDTOs);
        dpvSearchDTO.setDimensionName("OverAll");
        dpvSearchDTO.setDpCode("hl");
        dpvSearchDTO.setDimensionValue("default");

        List<DpvSearchDTO> dpvSearchDTOs = new ArrayList<DpvSearchDTO>();
        dpvSearchDTOs.add(dpvSearchDTO);

        DpvDTO dpvDTOExpected = new DpvDTO();
        dpvDTOExpected.setStatus("Success");
        dpvDTOExpected.setData(dpvSearchDTOs);

        String paramValueDPValue = SearchRequestParams.getDataPointValueOverAll(SearchRequestParams.AggregateValue.MONTHLY, dpvDTOExpected);
        String response = apiController.makeGetCall("dpv/search", "criteria", paramValueDPValue);
        DpvDTO dataPointValueActual = gson.fromJson(parser.parse(response).getAsJsonObject(), DpvDTO.class);

        //Without Time Zone
        assertEquals(dataPointValueActual, dpvDTOExpected, "Expected and actual value does not match using data point value Overall Monthly search without time zone api");
    }

    /**
     * Search API for the data point values with dimension By Queue and aggregate Hourly
     *
     * @teststep Create the dto object (expected dto)
     * @teststep Create request object for Queue name as tpQueue and aggregated as Hourly with client timezone false
     * @teststep Get the response as part of the dto object(actual dto).
     * @teststep Compare both expected and actual dto.
     * @teststep Create request object for Queue name as tpQueue and aggregated as hourly with client timezone true
     * @teststep Get the response as part of the dto object(actual dto).
     * @teststep Compare both expected and actual dto.
     */
    @Test(groups = {"Search","Sanity"})
    public void testDPValueByQueueHourlyWithWithoutTimeZone() throws Exception {
        DataDTO dataDTO = new DataDTO();
        dataDTO.setStartDate("01-06-2015 08:00");
        dataDTO.setEndDate("01-06-2015 09:00");
        dataDTO.setValue("88");

        DataDTO dataDTO1 = new DataDTO();
        dataDTO1.setStartDate("01-06-2015 09:00");
        dataDTO1.setEndDate("01-06-2015 10:00");
        dataDTO1.setValue("0");

        List<DataDTO> dataDTOs = new ArrayList<DataDTO>();
        dataDTOs.add(dataDTO);
        dataDTOs.add(dataDTO1);

        DpvSearchDTO dpvSearchDTO = new DpvSearchDTO();
        dpvSearchDTO.setGroupName("funnel_overall_overall_std");
        dpvSearchDTO.setClientName(PropertyUtils.getProperty("clientName"));
        dpvSearchDTO.setData(dataDTOs);
        dpvSearchDTO.setDimensionName("tp");
        dpvSearchDTO.setDpCode("hl");
        dpvSearchDTO.setDimensionValue("tpQueue");

        List<DpvSearchDTO> dpvSearchDTOs = new ArrayList<DpvSearchDTO>();
        dpvSearchDTOs.add(dpvSearchDTO);

        DpvDTO dpvDTOExpected = new DpvDTO();
        dpvDTOExpected.setStatus("Success");
        dpvDTOExpected.setData(dpvSearchDTOs);

        String paramValueDPValue = SearchRequestParams.getDataPointValueByQueue(SearchRequestParams.AggregateValue.HOURLY, dpvDTOExpected);
        String response = apiController.makeGetCall("dpv/search", "criteria", paramValueDPValue);
        DpvDTO dataPointValueActual = gson.fromJson(parser.parse(response).getAsJsonObject(), DpvDTO.class);

        //Without Time Zone
        assertEquals(dataPointValueActual, dpvDTOExpected, "Expected and actual value does not match using data point value By Queue Hourly search without time zone api");


        dataDTO.setValue("888");

        List<DataDTO> dataDTOTimeZone = new ArrayList<DataDTO>();
        dataDTOTimeZone.add(dataDTO);
        dataDTOTimeZone.add(dataDTO1);

        dpvSearchDTO.setData(dataDTOTimeZone);

        List<DpvSearchDTO> dpvSearchDTOTimeZone = new ArrayList<DpvSearchDTO>();
        dpvSearchDTOTimeZone.add(dpvSearchDTO);

        dpvDTOExpected.setData(dpvSearchDTOTimeZone);

        String paramValueDPValueWithTimeZone = SearchRequestParams.getDataPointValueByQueue(SearchRequestParams.AggregateValue.HOURLY, dpvDTOExpected, true);
        String responseWithTimeZone = apiController.makeGetCall("dpv/search", "criteria", paramValueDPValueWithTimeZone);
        DpvDTO dataPointValueActualWithTimeZone = gson.fromJson(parser.parse(responseWithTimeZone).getAsJsonObject(), DpvDTO.class);

        //With Time Zone
        assertEquals(dataPointValueActualWithTimeZone, dpvDTOExpected, "Expected and actual value does not match using data point value By Queue Hourly search with time zone api");
    }
}

