package com.alok.sampleprojects.utils;

import com.mongodb.*;
import org.apache.log4j.Logger;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Created by alokprakash.p on 3/17/2015.
 */
public class MongoConnection{

    /**
     * Root logger instance.
     */
    private static final Logger LOGGER = Logger.getLogger(MongoConnection.class);

    //
    private static final String PARAMETER_MONGO_URI = PropertyUtils.getProperty("MongoURI");

    /**
     * Mongo client instance.
     */
    private static MongoClient MONGO;

    /**
     * @return Instance of Mongo client.
     */
    public static synchronized final MongoClient getClientInstance() {
        if (MONGO == null) {
            LOGGER.warn("Mongo client hasn't been initialized yet.");
        }
        return MONGO;
    }

    /**
     * Initialize the Mongo Client
     *
     * @throws Exception
     */
    public static synchronized final void initialize()
            throws Exception {
        try {
            MONGO = new MongoClient(new MongoClientURI(PARAMETER_MONGO_URI));
        } catch (UnknownHostException e) {
            LOGGER.error("ERROR: Unable to connect to Mongo DB.", e);
            throw e;
        }
        LOGGER.info("Initialized Mongo client.");
    }

    /**
     * Closes the Mongo connection.
     */
    public static final synchronized void closeConnection() {
        if (MONGO != null) {
            MONGO.close();
            MONGO = null;
        }
    }

    /**
     * @param database   Name of database to query.
     * @param collection Name of collection to query.
     * @param query      Matching criteria for values.
     * @return DB cursor for fetched documents.
     */
    public static final DBCursor getDocuments(final String database,
                                              final String collection, final DBObject query) throws Exception {
        if (MONGO == null) {
            initialize();
        }
        if (database == null || database.length() == 0) {
            LOGGER.warn("Illegal database name.");
            return null;
        }

        if (collection == null || collection.length() == 0) {
            LOGGER.warn("Illegal collection name.");
            return null;
        }

        final DB db = getDatabase(database);
        if (db == null) {
            LOGGER.warn("Unable to find database: " + database);
            return null;
        }

        final DBCollection dbCollection = db.getCollection(collection);
        if (dbCollection == null) {
            LOGGER.warn("Unable to find collection: " + collection
                    + " in database: " + database);
            return null;
        }

        return dbCollection.find(query);
    }

    /**
     * @param database   Name of database to query.
     * @param collection Name of collection to query.
     * @return DB cursor for fetched documents.
     */
    public static final DBCursor getDocuments(final String database,
                                              final String collection) throws Exception {
        if (MONGO == null) {
            initialize();
        }
        if (database == null || database.length() == 0) {
            LOGGER.warn("Illegal database name.");
            return null;
        }

        if (collection == null || collection.length() == 0) {
            LOGGER.warn("Illegal collection name.");
            return null;
        }

        final DB db = getDatabase(database);
        if (db == null) {
            LOGGER.warn("Unable to find database: " + database);
            return null;
        }

        final DBCollection dbCollection = db.getCollection(collection);
        if (dbCollection == null) {
            LOGGER.warn("Unable to find collection: " + collection
                    + " in database: " + database);
            return null;
        }

        return dbCollection.find();
    }

    /**
     * @param database   Name of the database to query.
     * @param collection Name of the collection to query.
     * @param query      Matching criteria for values.
     * @param filterKeys Keys to be returned.
     * @return DB cursor for fetched documents.
     */
    public static final DBCursor getDocuments(final String database,
                                              final String collection, final DBObject query,
                                              final DBObject filterKeys) throws Exception {
        if (MONGO == null) {
            initialize();
        }
        if (database == null || database.length() == 0) {
            LOGGER.warn("Illegal database name.");
            return null;
        }

        if (collection == null || collection.length() == 0) {
            LOGGER.warn("Illegal collection name.");
            return null;
        }

        final DB db = getDatabase(database);
        if (db == null) {
            LOGGER.warn("Unable to find database: " + database);
            return null;
        }

        final DBCollection dbCollection = db.getCollection(collection);
        if (dbCollection == null) {
            LOGGER.warn("Unable to find collection: " + collection
                    + " in database: " + database);
            return null;
        }

        return dbCollection.find(query, filterKeys);
    }

    /**
     * @param database Name of the database.
     * @return Database reference object.
     */
    public static synchronized final DB getDatabase(final String database) throws Exception {
        if (MONGO == null) {
            initialize();
        }
        if (database == null || database.length() == 0) {
            return null;
        }
        LOGGER.info("Connecting to database: " + database);
        return MONGO.getDB(database);
    }

    /**
     * @param database   Name of the database.
     * @param collection Name of the collection.
     * @param data       Data to be inserted.
     */
    public static final void insertDocument(final String database,
                                            final String collection, final List<DBObject> data) throws Exception {
        if (MONGO == null) {
            initialize();
        }

        if (database == null || database.length() == 0) {
            LOGGER.warn("Illegal database name.");
            return;
        }

        if (collection == null || collection.length() == 0) {
            LOGGER.warn("Illegal collection name.");
            return;
        }

        final DB db = getDatabase(database);
        if (db == null) {
            LOGGER.warn("Unable to find database: " + database);
            return;
        }

        final DBCollection dbCollection = db.getCollection(collection);
        if (dbCollection == null) {
            LOGGER.warn("Unable to find collection: " + collection
                    + " in database: " + database);
            return;
        }

        dbCollection.insert(data);
    }

    /**
     * @param database   Name of the database.
     * @param collection Name of the collection.
     * @param data       Data to be inserted.
     */
    public static final void insertDocument(final String database,
                                            final String collection, final DBObject... data) throws Exception {
        if (MONGO == null) {
            initialize();
        }
        if (database == null || database.length() == 0) {
            LOGGER.warn("Illegal database name.");
            return;
        }

        if (collection == null || collection.length() == 0) {
            LOGGER.warn("Illegal collection name.");
            return;
        }

        final DB db = getDatabase(database);
        if (db == null) {
            LOGGER.warn("Unable to find database: " + database);
            return;
        }

        final DBCollection dbCollection = db.getCollection(collection);
        if (dbCollection == null) {
            LOGGER.warn("Unable to find collection: " + collection
                    + " in database: " + database);
            return;
        }

        dbCollection.insert(data);
    }

    /**
     * Remove the document from the mongo db specific to the configuration set in to the spirit-conf file
     *
     * @param documentId
     * @throws Exception
     */
    public static final synchronized void removeDocument(String collectionName, String documentId) throws Exception {
        BasicDBObject basicDBObject;
        try {
            if (MONGO == null) {
                initialize();
            }
            DB db = getDatabase(PropertyUtils.getProperty("ClientName"));
            if (db == null) {
                LOGGER.warn("Data Base Name is not set:");
                return;
            }
            DBCollection dbCollection = db.getCollection(collectionName);
            if (dbCollection == null) {
                LOGGER.warn("DB Collection name is not set");
                return;
            }
            basicDBObject = new BasicDBObject();
            basicDBObject.append(PropertyUtils.getProperty("groupName"), documentId);

            LOGGER.info("Get the DB object");
            dbCollection.remove(basicDBObject);
            LOGGER.info("Removed the selected object");
        } catch (Exception e) {
            throw new RuntimeException("Selected objected is not removed", e);
        }
    }

    /**
     * Remove the object from the array of the objects
     *
     * @param documentID
     * @param arrayObject
     * @param arrayCodeNames
     * @throws Exception
     */
    public static final synchronized void removeItemFromArray(String documentID, String arrayObject, Map<String, String> arrayCodeNames) throws Exception {
        BasicDBObject query = null;
        BasicDBObject dpToDelete = null;
        BasicDBObject obj = null;
        DB db = null;
        DBCollection collection = null;
        try {
            query = new BasicDBObject("_id", documentID);
            dpToDelete = new BasicDBObject();
            for (Map.Entry<String, String> entry : arrayCodeNames.entrySet()) {
                dpToDelete.append(entry.getKey(), entry.getValue());
            }
            obj = new BasicDBObject(arrayObject, dpToDelete);
            db = getDatabase(PropertyUtils.getProperty("ClientName"));
            collection = db.getCollection(PropertyUtils.getProperty("CollectionName"));
            collection.update(query, new BasicDBObject("$pull", obj));
            collection.getCount();
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Item is not removed", e);
        }
    }

    // Private constructor.
    private MongoConnection() {
    }

}
