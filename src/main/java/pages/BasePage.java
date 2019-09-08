package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;
import properties.Trash;

public class BasePage {
    WebDriver driver;
    Wait wait;


    public BasePage() {
        this.driver =  Trash.driver;
        PageFactory.initElements(driver, this);
    }



}