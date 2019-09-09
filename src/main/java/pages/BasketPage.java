package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import properties.Trash;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.Assert.*;

public class BasketPage extends BasePage {

    /* By totalPrice = By.xpath(".//div[contains(@class, 'total-amount')]//*[contains(@class, 'item-price')]");*/
    // @FindBy(xpath = ".//div[contains(@class, 'total-amount')]//*[contains(@class, 'item-price')]")
    //@FindBy(xpath = ".//span[contains(@class, 'btn-cart-link__price')]")
    @FindBy(xpath = "//div[@class='buttons']//span[@class='btn-cart-link__price']")
    WebElement totalPrice;
    @FindBy(xpath = "//div[@class='radio radio_checked']//label[@class='radio__label'][contains(text(), '2 года')]")
    WebElement checkBoxElement;
    @FindBy(xpath = "//span[.='Вернуть удалённый товар']")
    WebElement deletedElement;


    private int oldValue;



    Function<? super WebDriver, Object> valueChanged = new ExpectedCondition<Object>() {
        @Override
        public Boolean apply(WebDriver webDriver) {
            return !(getPrice()!=(oldValue));
        }
    };


    public void checkTotalPriceIs() {
        String price = totalPrice.getText().replaceAll(" ", "");
      /*  System.out.println("String price" + price);
        System.out.println("summ"+Trash.sumAllPuts());*/


        assertEquals("Сумма в корзине не соотвествует ожидаемой", Trash.sumAllPuts(), Integer.parseInt(price));
    }

    public void checkWarranty() {
        assertFalse("Гарантия 2 года отсутствует", checkBoxElement.isSelected());
    }


    public void delete(int index) {


        String productDel = String.format("//div[@class='cart-list__products']/div[%d]//i[@class='remove-button__icon']", index);
        WebElement productItemD = driver.findElement(By.xpath(productDel));
        WebDriverWait wait = new WebDriverWait(driver, 30);

        String oldValue = totalPrice.getText();
        Function<? super WebDriver, Object> valueChanged = new ExpectedCondition<Object>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return !(totalPrice.getText().equals(oldValue));
            }
        };

        productItemD.click();
        wait.until(valueChanged);

    }

    public void add(int index, int count) throws InterruptedException {
        String productAdd = String.format("//div[@class='cart-list__products']/div[%d]//i[@class='count-buttons__icon-plus']", index);
        WebElement productItemA = driver.findElement(By.xpath(productAdd));
        WebDriverWait wait = new WebDriverWait(driver, 30);


        /*for (int i = 0; i < count; i++) {
            productItemA.click();
            Thread.sleep(2500);*/

        for (int i = 0; i < count; i++) {

            String oldValue = totalPrice.getText();
            Function<? super WebDriver, Object> valueChanged = new ExpectedCondition<Object>() {
                @Override
                public Boolean apply(WebDriver webDriver) {
                    return !(totalPrice.getText().equals(oldValue));
                }
            };


            productItemA.click();
            wait.until(valueChanged);

        }
    }

 /*   public void add(int index) throws InterruptedException {


        *//*for (int i = 0; i < count; i++) {
            productItemA.click();
            Thread.sleep(2500);*//*
           oldValue = getPrice();
        WebDriverWait wait = new WebDriverWait(driver, 30);
       // String productAdd = String.format("//div[@class='cart-list__products']/div[%d]//i[@class='count-buttons__icon-plus']", index);
        WebElement productItemA = driver.findElement(By.xpath("//div[@class='cart-list__products']/div[" +index+ "]//i[@class='count-buttons__icon-plus']"));
            productItemA.click();
            wait.until(valueChanged);

        }*/


    public void checkPSCost(int count) {
        String price = totalPrice.getText().replaceAll(" ", "");
       // System.out.println(Trash.sumAllPuts());
        assertEquals("Сумма в корзине не соотвествует ожидаемой", Trash.sumAllPuts() * count, Integer.parseInt(price));
    }

    public void returnDeletedElement() {

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(deletedElement));
        deletedElement.click();

    }


    public void checkEachPrice(int indexInBasket, String product, int countOfProducts) {


        int price = Trash.get(product) * countOfProducts;
        String priceFormat = (int) price / 1000 + " " + (int) price % 1000;
        ;
        // System.out.println("priceFormat=" + priceFormat);


        String prod = String.format("//span[.='%s']", priceFormat);

        String priceFromSite = driver.findElement(By.xpath(prod)).getText();
        // System.out.println("priceFromsite"+priceFromSite);


        assertEquals("Сумма за товар не соответствует ожидаемой", priceFormat, priceFromSite);
    }


    public int getPrice(){
        return Integer.parseInt(totalPrice.getText().replace(" ", ""));
    }
}
