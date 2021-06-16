package com.n26.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Roaa
 * This class is responsible for loading/parsing any Json file
 */
public class JsonParser {

    JSONObject jsonObj;
    JSONParser parser;
    HashMap<Object, Object> objectData;

    /**
     * Constructor to read the json file from the directory
     */
    public JsonParser(String fileName) {
        try {
            parser = new JSONParser();
            jsonObj = (JSONObject) parser.parse(new FileReader(System.getProperty("user.dir") + File.separator
                    + "resources" + File.separator + fileName + ".json"));
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param object Name to get the root that hold the parameters and values
     * @return Hashmap for keys and values for the object name sent
     */
    @SuppressWarnings("unchecked")
    public HashMap<Object, Object> getData(String object) {
        objectData = new HashMap<Object, Object>();
        objectData = ((HashMap<Object, Object>) jsonObj.get(object));
        return objectData;
    }

}
