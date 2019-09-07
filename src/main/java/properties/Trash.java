package properties;

import org.openqa.selenium.WebDriver;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Trash {
    private static Map<String, Integer> map = new HashMap<>();
    Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();

    public static WebDriver driver;

  /*  public static String get(String key){
        return map.get(key);
    }*/

    public static void put(String key, int value){
        map.put(key, value);
    }

    public static int sumAllPuts(){
       int sum = 0;
        for (int i : map.values()) {
            sum += i;
        }
        return sum;
    }

    public static void remove(String name){

    }


}