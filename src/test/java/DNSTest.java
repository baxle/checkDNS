import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasketPage;
import pages.MainPage;
import pages.ProductPage;
import pages.ResultsPage;
import properties.TestProperties;
import properties.Trash;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;


@RunWith(Parameterized.class)
public class DNSTest {
    WebDriver driver;


    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", TestProperties.getInstance().getProperty("path.chrome"));
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(TestProperties.getInstance().getProperty("url"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Trash.driver = driver;

    }

    /* @After
     public void tearDown() throws Exception {
         driver.quit();
     }
 */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"playstation", "PlayStation 4 Slim Black 1 TB", "Detroit"},
        });
    }

    @Parameterized.Parameter
    public String consoleName;

    @Parameterized.Parameter(1)
    public String consoleFullName;

    @Parameterized.Parameter(2)
    public String gameName;


    @Test
    public void check() throws InterruptedException {
        MainPage mainPage = new MainPage();
        mainPage.search(consoleName);

        ResultsPage resultsPage = new ResultsPage();
        resultsPage.chooseProduct(consoleFullName);

        ProductPage productPage = new ProductPage();
        productPage.savePriceOfCurrentProduct(consoleName);

        productPage.addWarrantyTwoYears();

        productPage.savePriceOfCurrentProduct(consoleName+" Warranty2Years");

        productPage.addToBasket();

        mainPage.search(gameName);
        productPage.savePriceOfCurrentProduct(gameName);
        productPage.addToBasket();

        BasketPage basketPage = new BasketPage();
        basketPage.checkTotalPriceIs();

        productPage.goToBasket();

        basketPage.checkWarranty();

        basketPage.checkEachPrice(1, consoleName, 1);
        basketPage.checkEachPrice(2, gameName, 1);

        basketPage.delete(2);
        Trash.remove(gameName);



        basketPage.checkTotalPriceIs();

          basketPage.add(1, 2);


        basketPage.checkPSCost(3);

        basketPage.returnDeletedElement();


    }

}


