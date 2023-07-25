//import org.junit.Assert;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;

public class TestBase {
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

    // login successfully to get home page
    @Test(priority = 1)
    public void ValidLoginTest() throws InterruptedException {
        loginPage = new LoginPage(driver);
        loginPage.Login("testbotitb2@dist.com","123456");
        Assert.assertEquals(driver.getCurrentUrl(),"http://transmission-dev.azurewebsites.net/home","first assertion");
    }

    // add new category test
    @Test(priority = 2, dependsOnMethods = "ValidLoginTest",dataProvider = "dp")
    public void AddNewCategoryTest(String data) throws InterruptedException {
        homePage = new HomePage(driver);
        String category[] =data.split(",");
        //Thread.sleep(2000);
        homePage.AddNewCategory(category[0],category[1]);
    }

    // add new item test
    @Test(priority = 3 , dependsOnMethods = "ValidLoginTest",dataProvider = "itemDataProvider")
    public void AddNewItemTest(String data) throws InterruptedException, AWTException {
        String Item[] = data.split(",");
        homePage = new HomePage(driver);
        itemPage = new ItemPage(driver);
        homePage.AddNewItem();
        //itemPage.addItem();
        itemPage.addItemWithDataProvider(Item[0],Item[1],Item[2],Item[3],Item[4],Item[5],Item[6],Item[7],Item[8]);
    }

    // find my item while looping on all items
    @Test(priority = 4)
    public void LoopForItem() throws InterruptedException {
        itemPage = new ItemPage(driver);
        Thread.sleep(2000);
        itemPage.changeItemPricePages();
    }

    // find my new add category to edit and delete it
    @Test(priority = 5)
    public void findMyAddCategory() throws InterruptedException {
        homePage = new HomePage(driver);
        Thread.sleep(4000);
        homePage.EditCategoryPages();
    }
    ////////////////////////////////////////////////////////////////////
    // for add new category
    @DataProvider(name = "dp")
    public String[] readJson() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader("src/files/task.json");
        Object obj = jsonParser.parse(fileReader);
        //System.out.println(obj);
        JSONObject userLoginObject = (JSONObject) obj;
        JSONArray userLoginArray = (JSONArray) userLoginObject.get("NewCategory");
        //System.out.println(userLoginArray);
        String arr[] = new String[userLoginArray.size()];
        for (int i = 0 ; i < userLoginArray.size(); i++)
        {
            JSONObject users = (JSONObject) userLoginArray.get(i);
            //System.out.println(users);
            String CatEnName = (String) users.get("CategoryEnName");
            String CatArName = (String) users.get("CategoryArName");
            arr[i] = CatEnName+ "," +CatArName;
            //System.out.println(arr[i]);
        }
        return arr;
    }
    // for add new items
    @DataProvider(name = "itemDataProvider")
    public String[] readJsonFile() throws IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        FileReader fileReader = new FileReader("src/files/task.json");
        Object obj = jsonParser.parse(fileReader);
        //System.out.println(obj);
        JSONObject userLoginObject = (JSONObject) obj;
        JSONArray userLoginArray = (JSONArray) userLoginObject.get("NewItems");
        //System.out.println(userLoginArray);
        String arr[] = new String[userLoginArray.size()];
        for (int i = 0 ; i < userLoginArray.size(); i++)
        {
            JSONObject users = (JSONObject) userLoginArray.get(i);
            //System.out.println(users);
            String itemenname = (String) users.get("ITEMENNAME");
            String itemarname = (String) users.get("ITEMARNAME");
            String discount = (String) users.get("DISCOUNT");
            String status = (String) users.get("STATUS");
            String menucategory = (String) users.get("MENUCATEGORY");
            String image = (String) users.get("image");
            String endescription = (String) users.get("ENDESCRIPTION");
            String ardescription = (String) users.get("ARDESCRIPTION");
            String price = (String) users.get("PRICE");

            arr[i] = itemenname+ "," +itemarname+ "," +discount+ "," +status+ "," +menucategory+ "," +image+ "," +endescription+ "," +ardescription+ "," +price;
            //System.out.println(arr[i]);
        }
        return arr;
    }

/*
    @AfterTest
    public void Quit()
    {
        driver.quit();
    }
*/

}
