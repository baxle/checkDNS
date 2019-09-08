package properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Trash {
    private static Map<String, Integer> map = new LinkedHashMap<>();
    Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();

    public static WebDriver driver;

   public static Integer get(String key){
        return map.get(key);
    }

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
        map.remove(name);
    }

public static void printMap(){
    System.out.println(map);
}




    }

