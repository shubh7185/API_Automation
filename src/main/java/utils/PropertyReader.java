package utils;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public static String propertyReader(String filePath , String key) {
        String value = null;

//        InputStream is required while loading into properties

        try(InputStream input = new FileInputStream(filePath))
        {
            Properties prop = new Properties();
            prop.load(input);

            value = prop.getProperty(key);


        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return value;
    }

}
