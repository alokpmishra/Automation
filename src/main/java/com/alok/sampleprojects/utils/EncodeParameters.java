package com.alok.sampleprojects.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alokprakash.p on 6/2/2015.
 */
public class EncodeParameters {

    private static Map replaceWith = new HashMap();
    public EncodeParameters(){
        replaceWith.put("\"", "%22");
        replaceWith.put(" ", "%20");
    }
    public String getEncodedParams(String params){
        String temp = params;
        for(Object o: replaceWith.keySet()){
            temp = temp.replaceAll((String)o, (String)replaceWith.get(o));
        }
        return temp;

    }
}
