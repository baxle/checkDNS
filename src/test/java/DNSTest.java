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

        //   mainPage.search(gameName);


        ProductPage productPage = new ProductPage();
        productPage.savePriceOfCurrentProduct(consoleName);

        productPage.addWarrantyTwoYears();
        productPage.savePriceOfCurrentProduct(consoleName);


        productPage.savePriceOfCurrentProduct(consoleName);
        productPage.addToBasket();


        mainPage.search(gameName);
        productPage.savePriceOfCurrentProduct(gameName);
        productPage.addToBasket();

        Thread.sleep(1500);

        BasketPage basketPage = new BasketPage();
        basketPage.checkTotalPriceIs();

        productPage.goToBasket();
        basketPage.checkWarranty();
        basketPage.delete(2);

        Thread.sleep(2000);
        basketPage.add(1);
        productPage.savePriceOfCurrentProduct(consoleName);
        System.out.println(Trash.sumAllPuts());

        Thread.sleep(2000);
        basketPage.add(1);
        productPage.savePriceOfCurrentProduct(consoleName);
        System.out.println(Trash.sumAllPuts());

    }

}


