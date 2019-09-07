package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import properties.Trash;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class BasketPage extends BasePage{

   /* By totalPrice = By.xpath(".//div[contains(@class, 'total-amount')]//*[contains(@class, 'item-price')]");*/
  // @FindBy(xpath = ".//div[contains(@class, 'total-amount')]//*[contains(@class, 'item-price')]")
   //@FindBy(xpath = ".//span[contains(@class, 'btn-cart-link__price')]")
   @FindBy(xpath = "//div[@class='buttons']//span[@class='btn-cart-link__price']")
   WebElement totalPrice;
   @FindBy(xpath = "//div[@class='radio radio_checked']//label[@class='radio__label'][contains(text(), '2 года')]")
   WebElement checkBoxElement;


    public void checkTotalPriceIs(){
        String price = totalPrice.getText().replaceAll(" ", "");
      /*  System.out.println("String price" + price);
        System.out.println("summ"+Trash.sumAllPuts());*/

        assertEquals("Сумма в корзине не соотвествует ожидаемой", Trash.sumAllPuts(), Integer.parseInt(price));

    }

    public void checkWarranty(){
        assertFalse("Гарантия 2 года отсутствует", checkBoxElement.isSelected());
    }

  /*  public void checkProductsPrice(){
        double fullCost = 0;
        for (int i = 0; i < cost.length; i++) {
            fullCost += cost[i];
            String c = (int) cost[i] / 1000 + " " + (int) cost[i] % 1000;
            String product = String.format("//div[contains(@class, 'item-price')][1]//span[.='%s']", c);
            WebElement productItem = getDriver().findElement(By.xpath(product));
            assertEquals(c, productItem.getText());
        }
    }
*/

    public void delete(int index){


        String productDel = String.format("//div[@class='cart-list__products']/div[%d]//i[@class='remove-button__icon']", index);
        WebElement productItemD = driver.findElement(By.xpath(productDel));
     productItemD.click();


    }

    public void add(int index){
        String productAdd = String.format("//div[@class='cart-list__products']/div[%d]//i[@class='count-buttons__icon-plus']", index);
        WebElement productItemA = driver.findElement(By.xpath(productAdd));

       /* WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.pollingEvery(1, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.elementToBeClickable(productItemA));*/

        productItemA.click();




    }

}
