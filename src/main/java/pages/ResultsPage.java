package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ResultsPage extends BasePage {


    public void chooseProduct(String name){
        String productXpath = String.format(".//div[contains(@class,'catalog-item') and .//a[contains(text(),'%s')]]", name);
        WebElement productItem = driver.findElement(By.xpath(productXpath));
        productItem.findElement(By.xpath(".//a[contains(@class,'ui-link')]")).click();
    }
}
