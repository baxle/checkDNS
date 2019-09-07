package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import properties.Trash;


public class ProductPage extends BasePage {
    private Select select;
    private Wait wait;
    private String oldPrice;
    private String newPrice;
   /* @FindBy(xpath = "//*[contains(@class, 'ui-input-search__input main-search-form__input ui-autocomplete-input')]")
    WebElement searchTextField;*/

    @FindBy(xpath = ".//span[contains(@class,'current-price-value')]")
    WebElement productPrice;
    @FindBy(xpath = ".//button[contains(@class,'btn-cart btn-lg')]")
    WebElement addToBasket;
 //   @FindBy(xpath = ".//span[contains(@class, 'btn-cart-link__cart')]")
 @FindBy(xpath = "//div[@class='buttons']//span[@class='btn-cart-link__price']")
    WebElement goToBasket;
    @FindBy(xpath = ".//div[contains(@class, 'desktop-selector')]//select[contains(@class, 'form-control select')]")
    WebElement addWarrantyTwoYears;



/*    By productPrice = By.xpath(".//span[contains(@class,'current-price-value')]");
    By addToBasket = By.xpath(".//button[contains(@class,'btn-cart btn-lg')]");
    By goToBasket = By.xpath(".//span[contains(@class, 'btn-cart-link__cart')]");
    By addWarrantyTwoYears = By.xpath(".//div[contains(@class, 'desktop-selector')]//select[contains(@class, 'form-control select')]");*/

    // By addWarrantyTwoYears = By.xpath(".//label[contains(@class, 'radio__label')][contains(text(), '2 года')]");


    // By addedToBasketFlag = By.id("WA_check_mark");

    public void savePriceOfCurrentProduct(String key) {
        String value = productPrice.getText();
        Trash.put(key, Integer.parseInt(value.replace(" ", "")));
    }

    public void addToBasket() {
        addToBasket.click();
       /* WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(addedToBasketFlag));*/
    }

    public void goToBasket() {

   goToBasket.click();
       // return new BasketPage();
    }

    public void addWarrantyTwoYears() {

        select = new Select(addWarrantyTwoYears);
         select.selectByVisibleText("2 года");


    }






}


