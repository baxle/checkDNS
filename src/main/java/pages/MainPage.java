package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MainPage extends BasePage{

  //  @FindBy(xpath = "//*[contains(@class, 'ui-autocomplete-input')]")
 // @FindBy(xpath = "//*[contains(@class, 'ui-input-search__input main-search-form__input ui-autocomplete-input')]")
  @FindBy(xpath = "//*[contains(@class, 'search-form__input ui-autocomplete-input')]")
    WebElement searchTextField;


    public void search(String text){
        searchTextField.clear();
        searchTextField.sendKeys(text+"\n");
    }

}
