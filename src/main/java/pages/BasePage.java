package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import properties.Trash;

public class BasePage {
   /* WebDriver driver;
    Wait wait;*/
     WebDriver driver;
    Wait<WebDriver> wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 50);
    }


    public BasePage() {
        this.driver =  Trash.driver;
        PageFactory.initElements(driver, this);
    }



}