package util;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FunctionUtil {

    public static String screenshotPathName = "test_output/screenshots";

    /**
     *@description To remove the last allure results
     * @author he, ying
     */
    public static void emptyAllureResults(){
        File file = new File("test_output/allure-results");
        File[] contents = file.listFiles();
        if(contents.length>0){
            for (File f : contents){
                f.delete();
            }
            System.out.println("The last Allure reports is removed");
        }
    }



    /**
     * @description To remove the screenshot created by the last three days.
     * @author  he, ying
     */

    public static void clearScreenshot() {
        DateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
        Date today = new Date();
        Date fileDate = null;
        int bDay;
        File file = new File(screenshotPathName);
        File[] contents = file.listFiles();
        String fileName;
        String subFileName;
        if(contents.length>0){
            for(File f : contents){
                fileName = ((f.getName()).split("\\."))[1];
                if(fileName.length()!=0){
                    subFileName = fileName.substring(fileName.length()-14,fileName.length());
                    try {
                        fileDate = format.parse(subFileName);
                    } catch (ParseException e) {
                        System.out.println("The screenshot " +subFileName+"!!Cannot transfer to Date type");
                        continue;
                    }
                    bDay = (int)((today.getTime()-fileDate.getTime())/(24*60*60*1000));
                    if(bDay>3){
                        f.delete();
                        System.out.println("Screenshot "+f.getName()+" is removed");
                    }

                }
            }
        }
    }

}
