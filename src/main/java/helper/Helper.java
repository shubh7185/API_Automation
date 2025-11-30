package helper;

import java.io.File;
import java.util.Date;

public class Helper {
    public static  void CreateFolder(String path)
    {
        File file = new File(path);
        if(!file.exists())
        {
            file.mkdir();
        }
        else {
            System.out.println("Folder already created");
        }
    }

    public static String TimeStamp()
    {
        Date now = new Date();
        String TimeStamp = now.toString().replace(":","-");
        return TimeStamp;
    }


}
