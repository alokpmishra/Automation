package com.alok.sampleprojects.utils;

import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils
{
    private static Properties props = new Properties();

    static
    {
        String workingDir = System.getProperty("user.home");

        try
        {
            loadPropertyFile(workingDir + "/Spirit/" + "api-conf.properties");
        } catch (FileNotFoundException realCause)
        {
            Assert.fail("Unable to load file!", realCause);
        } catch (IOException realCause)
        {
            Assert.fail("Unable to load file!", realCause);
        }
    }

    public static void loadPropertyFile(String propertyFileName) throws FileNotFoundException, IOException
    {
        props.load(new FileInputStream(propertyFileName));
    }

    public static String getProperty(String propertyKey)
    {
        String propertyValue = props.getProperty(propertyKey.trim());

        if (propertyValue == null || propertyValue.trim().length() == 0)
        {
            // Log error message
        }

        return propertyValue;
    }

    public static void setProperty(String propertyKey, String value) throws FileNotFoundException, IOException
    {
        props.setProperty(propertyKey, value);
    }
}
