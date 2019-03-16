package com.alok.sampleprojects.utils;

import com.google.gson.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.alok.sampleprojects.dto.DimSearchDTO;
import com.alok.sampleprojects.dto.DpgSearchDTO;
import com.alok.sampleprojects.dto.dpgsavedto.DpgSaveDTO;
import com.alok.sampleprojects.dto.dpvsavedeleteupdatedto.DpvSaveDTO;
import com.alok.sampleprojects.service.CentralAppServiceFactory;
import com.alok.sampleprojects.service.ICentralAppConnector;
import com.alok.sampleprojects.utilities.JsonParserHolder;
import com.alok.sampleprojects.utils.adapter.DPGroupAdapter;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Controller class for making the HTTP calls and for verification Mongo call methods
 * Created by alokprakash.p on 6/1/2015.
 */
public class APIController {

    public static Logger logger = Logger.getLogger(APIController.class);
    /**
     * Test configuration file path.
     */
    public static final String FILE_PATH = System.getProperty("user.home");

    /**
     * JSON parser instance.
     */
    private static final JsonParser PARSER = new JsonParser(null, null, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    ICentralAppConnector qwe;
    EncodeParameters encodeParameters = null;

    /**
     * To envoke the http calls
     */
    public APIController() {
        qwe = CentralAppServiceFactory.getApplicationConnector();
        encodeParameters = new EncodeParameters();
    }

    /**
     * Make HTTP Get call
     *
     * @param path
     * @param paramKey
     * @param paramValue
     * @param encoded
     * @return
     * @throws Exception
     */
    public String makeGetCall(String path, String paramKey, String paramValue, boolean encoded) throws Exception {
        String response = "";
        String encodedString = null;
        if (encoded) {
            encodedString = encodeParameters.getEncodedParams(paramValue);
        } else {
            encodedString = paramValue;
        }
        response = qwe.makeGetCall(path, paramKey + "=" + encodedString);
        return response;
    }

    /**
     * Make Get Call With the Encoded Parameters Values as true
     *
     * @param path
     * @param paramKey
     * @param paramValue
     * @return
     * @throws Exception
     */
    public String makeGetCall(String path, String paramKey, String paramValue) throws Exception {
        return (makeGetCall(path, paramKey, paramValue, true));
    }

    /**
     * Make HTTP Post Call with encoded
     *
     * @param path
     * @param paramKey
     * @param paramValue
     * @param encoded
     * @return
     * @throws Exception
     */
    public String makePostCall(String path, String paramKey, String paramValue, boolean encoded) throws Exception {
        String response = "";
        String encodedString = null;
        if (encoded) {
            encodedString = encodeParameters.getEncodedParams(paramValue);
        } else {
            encodedString = paramValue;
        }

        response = qwe.makeJSONPostCall(path + "?" + paramKey, encodedString);
        return response;
    }

    /**
     * Make HTTP delete call with encoded url as true
     *
     * @param path
     * @param paramKey
     * @param paramValue
     * @return
     * @throws Exception
     */
    public String makePostCall(String path, String paramKey, String paramValue) throws Exception {
        return (makePostCall(path, paramKey, paramValue, true));
    }

    /**
     * Make HTTP delete call
     *
     * @param path
     * @param paramValue
     * @param encoded
     * @return String response
     * @throws Exception
     */
    public String makeDeleteCall(String path, String paramValue, boolean encoded) throws Exception {
        String response = "";
        String encodedString = null;
        if (encoded) {
            encodedString = encodeParameters.getEncodedParams(paramValue);
        } else {
            encodedString = paramValue;
        }
        response = qwe.makeDeleteCall(path + "?" + "json=" + paramValue);
        return response;
    }

    public String makeDeleteCall(String path, String paramValue) throws Exception {
        return (makeDeleteCall(path, paramValue, true));
    }

    /**
     * Get the detail as the dto object from the mongo for Data point Group search api
     *
     * @param collectionName
     * @return
     * @throws Exception
     */
    public DpgSearchDTO getDpgSearchDTOFromMongo(String collectionName) throws Exception {
        DB db = null;
        DBCollection collection = null;
        Gson gson = null;
        JsonParser jsonParser = null;
        BasicDBObject query = null;
        BasicDBObject fields = null;
        DpgSearchDTO actualDBObject = null;
        DBObject dbObject = null;
        try {
            db = MongoConnection.getDatabase(PropertyUtils.getProperty("clientName"));
            collection = db.getCollection(collectionName);
            query = new BasicDBObject();
            query.append("_id", PropertyUtils.getProperty("groupName"));
            fields = new BasicDBObject();
            fields.put("_id", 1);
            fields.put("status", 1);
            fields.put("runType", 1);
            fields.put("dimension", 1);
            fields.put("dataPointDefs", 1);
            fields.put("isLocked", 1);
            dbObject = collection.findOne(query, fields);
            if (dbObject != null) {
                final GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(DpgSearchDTO.class, new DPGroupAdapter());
                gson = gsonBuilder.create();
                actualDBObject = gson.fromJson(gson.toJson(dbObject), DpgSearchDTO.class);
            }
            return actualDBObject;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Get the detail as the dto object from the mongo for Data point Search api
     *
     * @param collectionName
     * @param dataPointName
     * @return
     * @throws Exception
     */
    public DpgSearchDTO getDpSearchDTOFromMongo(String collectionName, String dataPointName) throws Exception {
        DB db = null;
        DBCollection collection = null;
        Gson gson = null;
        JsonParser jsonParser = null;
        BasicDBObject query = null;
        BasicDBObject fields = null;
        DpgSearchDTO actualDBObject = null;
        DBObject dbObject = null;
        try {
            db = MongoConnection.getDatabase(PropertyUtils.getProperty("clientName"));
            collection = db.getCollection(collectionName);
            query = new BasicDBObject();
            query.append("_id", PropertyUtils.getProperty("groupName"));
            query.append("dataPointDefs.name", dataPointName);
            fields = new BasicDBObject();
            fields.put("dataPointDefs.$", 1);
            dbObject = collection.findOne(query, fields);
            if (dbObject != null) {
                gson = new Gson();
                actualDBObject = gson.fromJson(gson.toJson(dbObject), DpgSearchDTO.class);
            }
            return actualDBObject;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Get the detail as the dto object from the mongo for Data point Dimension search api.
     *
     * @param clientName
     * @return
     * @throws Exception
     */
    public DimSearchDTO getDpDimSearchDTOFromMongo(String clientName) throws Exception {
        DB db = null;
        DBCollection collection = null;
        Gson gson = null;
        JsonParser jsonParser = null;
        BasicDBObject query = null;
        BasicDBObject fields = null;
        DimSearchDTO actualDBObject = null;
        DBObject dbObject = null;
        try {
            db = MongoConnection.getDatabase("Preferences");
            collection = db.getCollection("clients");
            query = new BasicDBObject();
            query.append("clientName", clientName);
            fields = new BasicDBObject();
            fields.put("dimension", 1);
            dbObject = collection.findOne(query, fields);
            if (dbObject != null) {
                gson = new Gson();
                actualDBObject = gson.fromJson(gson.toJson(dbObject), DimSearchDTO.class);
            }
            return actualDBObject;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Get the detail as the dto object from the mongo for Data point Group Save api.
     *
     * @param collectionName
     * @param groupName
     * @return
     * @throws Exception
     */
    public DpgSaveDTO getDpgSaveDTOFromMongo(String collectionName, String groupName) throws Exception {
        DB db = null;
        DBCollection collection = null;
        Gson gson = null;
        BasicDBObject query = null;
        BasicDBObject fields = null;
        DpgSaveDTO actualDBObject = null;
        DBObject dbObject = null;
        try {
            db = MongoConnection.getDatabase(PropertyUtils.getProperty("clientName"));
            collection = db.getCollection(collectionName);
            query = new BasicDBObject();
            query.append("_id", groupName);
            fields = new BasicDBObject();
            fields.put("_id", 1);
            fields.put("status", 1);
            fields.put("runType", 1);
            fields.put("dimension", 1);
            fields.put("isLocked", 1);
            fields.put("dataPointDefs", 1);
            dbObject = collection.findOne(query, fields);
            if (dbObject != null) {
                gson = new Gson();
                String jsonObject = gson.toJson(dbObject);
                actualDBObject = gson.fromJson(jsonObject, DpgSaveDTO.class);
            }
            return actualDBObject;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Get the detail as the dto object from the mongo for Data point Save api.
     *
     * @param collectionName
     * @param dataPointName
     * @param groupName
     * @return
     * @throws Exception
     */
    public DpgSaveDTO getDpSaveDTOFromMongo(String collectionName, String dataPointName, String groupName) throws Exception {
        DB db = null;
        DBCollection collection = null;
        Gson gson = null;
        JsonParser jsonParser = null;
        BasicDBObject query = null;
        BasicDBObject fields = null;
        DpgSaveDTO actualDBObject = null;
        DBObject dbObject = null;
        try {
            db = MongoConnection.getDatabase(PropertyUtils.getProperty("clientName"));
            collection = db.getCollection(collectionName);
            query = new BasicDBObject();
            query.append("_id", groupName);
            query.append("dataPointDefs.name", dataPointName);
            fields = new BasicDBObject();
            fields.put("dataPointDefs.$", 1);
            dbObject = collection.findOne(query, fields);
            if (dbObject != null) {
                gson = new Gson();
                actualDBObject = gson.fromJson(gson.toJson(dbObject), DpgSaveDTO.class);
            }
            return actualDBObject;
        } catch (Exception e) {
            throw e;
        }
    }

    public DpvSaveDTO getDpvSaveDTOFromMongo(String collectionName, String id) throws Exception {
        DB db = null;
        DBCollection collection = null;
        Gson gson = null;
        JsonParser jsonParser = null;
        BasicDBObject query = null;
        BasicDBObject fields = null;
        DpvSaveDTO actualDBObject = null;
        DBObject dbObject = null;
        try {
            db = MongoConnection.getDatabase(PropertyUtils.getProperty("clientName"));
            collection = db.getCollection(collectionName);
            query = new BasicDBObject();
            query.append("_id", id);
            fields = new BasicDBObject();
            fields.put("_id", 1);
            fields.put("name", 1);
            fields.put("codeName", 1);
            fields.put("shortDesc", 1);
            fields.put("totalRecordCount", 1);
            fields.put("matchingRecordCount", 1);
            fields.put("queueName", 1);
            fields.put("startTime", 1);
            fields.put("endTime", 1);
            dbObject = collection.findOne(query, fields);
            if (dbObject != null) {
                final GsonBuilder gsonBuilder = new GsonBuilder();
                gsonBuilder.registerTypeAdapter(DpgSearchDTO.class, new DPGroupAdapter());
                gson = gsonBuilder.create();
                actualDBObject = gson.fromJson(gson.toJson(dbObject), DpvSaveDTO.class);
            }
            return actualDBObject;
        } catch (Exception e) {
            throw e;
        }
    }

    public void removeItemFromArray(String dbName, String collectionName, String documentKey, String documentValue, String arrayObject, Map<String, String> arrayCodeNames) throws Exception {
        BasicDBObject query = null;
        BasicDBObject dpToDelete = null;
        BasicDBObject obj = null;
        DB db = null;
        DBCollection collection = null;
        try {
            query = new BasicDBObject(documentKey, documentValue);
            dpToDelete = new BasicDBObject();
            for (Map.Entry<String, String> entry : arrayCodeNames.entrySet()) {
                dpToDelete.append(entry.getKey(), entry.getValue());
            }
            obj = new BasicDBObject(arrayObject, dpToDelete);
            db = MongoConnection.getDatabase(dbName);
            collection = db.getCollection(collectionName);
            collection.update(query, new BasicDBObject("$pull", obj));
            collection.getCount();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Item is not removed", e);
        }
    }

    /**
     * @param fileName Name of the file to read.
     * @param klass    Class of the object to be created from the input.
     * @return Array of input arguments each of which is an array in itself.
     * @throws java.io.IOException When the configuration file can't be read.
     */
    public static Object[][] getInputDetails(final String fileName,
                                             final Class<?> klass) throws IOException {
        String inputData = "";
        try {
            final BufferedReader br = new BufferedReader(new FileReader(
                    FILE_PATH + fileName));
            while (br.ready()) {
                inputData = inputData + br.readLine();
            }
            br.close();
        } catch (IOException e) {
            logger.error("Error while reading from file " + FILE_PATH + fileName);
            throw e;
        }

        final JsonArray input = PARSER.parseArray(inputData);
        if (input == null) {
            logger.error("Input data not a json array");
            throw new IllegalArgumentException("Invalid test case configuration: " + fileName);
        }

        Object[] objects = PARSER.<Object[]>fromJson(inputData, klass);
        final int size = objects.length;
        Object[][] data = new Object[size][1];
        for (int i = 0; i < size; i++) {
            data[i][0] = objects[i];
        }
        return data;
    }


}
