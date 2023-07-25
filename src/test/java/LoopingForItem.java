import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoopingForItem {
    protected WebDriver driver;

    // create objects from Pages
    PageBase pageBase;  //
    LoginPage loginPage ; // new LoginPage(driver);
    HomePage homePage ; // new HomePage(driver);
    ItemPage itemPage ; // new ItemPage(driver);

    @BeforeTest
    public void setup()
    {
        driver = new ChromeDriver();
        pageBase = new PageBase(driver);
        driver.navigate().to("http://transmission-dev.azurewebsites.net/login");
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void ValidLoginTest() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.Login("testbotitb2@dist.com","123456");
        Assert.assertEquals(driver.getCurrentUrl(),"http://transmission-dev.azurewebsites.net/home","first assertion");
    }
    @Test(priority = 2)
    public void Search() throws InterruptedException {
        itemPage = new ItemPage(driver);
        itemPage.changeItemPricePages();
    }

    /*
    @Test(priority = 3,dependsOnMethods = "ValidLoginTest")
    public void findMyCell() throws InterruptedException {
        homePage = new HomePage(driver);
        homePage.addDynamicWebTable();
    }
    */
    /*
    @AfterTest
    public void Quit()
    {
        driver.quit();
    }*/

}
