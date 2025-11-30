package utils;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.IOException;

public class JsonReader {

    public static String getTestData(String input) throws IOException, ParseException {
        return String.valueOf(getJsonData().get(input)); // input is the key
    }

    public static JSONObject getJsonData() throws IOException, ParseException {
        File filename = new File("resources/TestData/testdata.json");
        String json = FileUtils.readFileToString(filename, "UTF-8");
        Object obj = new JSONParser().parse(json);
        JSONObject jsonObject = (JSONObject) obj; // This is org.json.simple.JSONObject
        return jsonObject;
    }
    public static JSONArray getJsonArray(String key) throws IOException, ParseException {
        JSONObject jsonObject = getJsonData();
        JSONArray jsonArray = (JSONArray) jsonObject.get(key);
        return jsonArray;


    }

    public static  Object getJsonArrayData(String key , int index) throws IOException, ParseException {
        JSONArray languages = getJsonArray(key);

        return languages.get(index);

    }

}

