package utils;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    public static String PropToJson(String name, Object obj, boolean HasQuotationMarks) {
        String res = "\"" + name + "\":";
        String value = obj.toString();
        if (HasQuotationMarks)
            value = "\"" + value + "\"";
        res += value;
        res += ",";
        return res;
    }
    // public static String[] Props(String json){
    //     boolean insideMark=false;
    //     boolean complete = false;
    //     String temp = "";

    //     json = json.substring(1, json.length()-1);

    //     List<String> res = new ArrayList<String>();
    //     for (int i = 0; i < json.length(); i++) {
    //         if(json.charAt(i)=='"'){
    //             insideMark = true;
    //         }
    //     }
    // }
}
