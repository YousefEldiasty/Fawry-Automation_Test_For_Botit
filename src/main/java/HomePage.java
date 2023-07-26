import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends PageBase {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //find elements
    @FindBy(className = "catalog-icon")
    private WebElement CatalogButton;

    // category
    @FindBy(className = "tab-label-1")
    private WebElement CategoriesButton;
    @FindBy(xpath = "//div[@class=\"add-new-categoryin\"]")
    private WebElement AddNewCategoryButton;


    //Items
    @FindBy(className = "tab-label-2")
    private WebElement ItemsButton;
    @FindBy(className = "add-new-item")
    private WebElement AddNewItemButton;


    //Add New Category Fields
    @FindBy(id = "add_name_en")
    private WebElement CategoryNameEGField;
    @FindBy(id = "add_name_ar")
    private WebElement CategoryNameARField;
    @FindBy (xpath = "//input[@onclick=\"add_category()\"]")
    private WebElement DoneButtonForAddCategory;

    @FindBy(id = "itemContainerother")
    private WebElement table;

    public void AddNewCategory(String catEnName,String catArName) throws InterruptedException {
        Thread.sleep(2000);
        ClickButton(CatalogButton);
        // press on add new category button
        Thread.sleep(4000);
        ClickButton(AddNewCategoryButton);
        //enter new category
        CategoryNameEGField.sendKeys(catEnName);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1]",CategoryNameARField,catArName);
        ClickButton(DoneButtonForAddCategory);
        Thread.sleep(2000);
    }
    public void addDynamicWebTable() throws InterruptedException {
        ClickButton(CatalogButton);
        Thread.sleep(6000);
        List<WebElement> rowsElement = table.findElements(By.tagName("tr"));
        int rows = rowsElement.size();
        for (int r=1 ; r<= rows-1 ; r++)
        {
            String categoryXpath="//table[@id=\"itemContainerother\"]//tbody//tr["+r+"]";
            String category = driver.findElement(By.xpath(categoryXpath+"//td[1]")).getText();
            String itemCount = driver.findElement(By.xpath(categoryXpath+"//td[2]")).getText();
            WebElement EditButton = driver.findElement(By.xpath(categoryXpath+"//td[3]//div[@class=\"edit-action\"]"));
            String newCat = "cafeeeeeee";

            if(category.equals("cafe"))
            {
                //System.out.println(" the category name that will edit is "+category);
                // find edit elements
                WebElement EditCategoryEn = driver.findElement(By.xpath(categoryXpath+"//td[3]//input[@name=\"name_enedit\"]"));
                WebElement EditCategoryAr = driver.findElement(By.xpath(categoryXpath+"//td[3]//input[@name=\"name_aredit\"]"));
                WebElement DoneButton = driver.findElement(By.xpath(categoryXpath+"//td[3]//input[@class=\"submit-branch\"]"));
                // edit method
                ClickButton(EditButton);
                EditCategoryEn.clear();
                EditCategoryEn.sendKeys(newCat);
                EditCategoryAr.clear();
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].value = arguments[1]",EditCategoryAr,"قهوههههههههههه");
                ClickButton(DoneButton);
                // delete
                Thread.sleep(6000);
                WebElement DeleteButton = driver.findElement(By.xpath(categoryXpath+"//td[3]//div[@class=\"delete-action\"]"));
                WebElement RUSure = driver.findElement(By.xpath(categoryXpath+"//td[3]//input[@class=\"no-noti\"]"));
                ClickButton(DeleteButton);
                ClickButton(RUSure);
                break;
            }
        }
    }

    public void EditCategoryPages() throws InterruptedException {
        ClickButton(CatalogButton);
        Thread.sleep(6000);
        // loop on pages
        WebElement page = driver.findElement(By.xpath("//div[@id= \"itemContainerother_paginate\"]//span"));
        List<WebElement> pages = page.findElements(By.tagName("a"));
        int p = pages.size();
        System.out.println("number of pages  = " + p);
        for (int i = 1; i <= p; i++) {
            String PageXpath = "//div[@id= \"itemContainerother_paginate\"]//span//a["+i+"]";
            WebElement CurrentPage = driver.findElement(By.xpath(PageXpath));
            //System.out.println(CurrentPage.getText());
            Thread.sleep(2000);
            CurrentPage.click();
            Thread.sleep(2000);
            List<WebElement> rowsElement = table.findElements(By.tagName("tr"));
            int rows = rowsElement.size();
            for (int r=1 ; r<= rows-1 ; r++)
            {
                String categoryXpath="//table[@id=\"itemContainerother\"]//tbody//tr["+r+"]";
                String category = driver.findElement(By.xpath(categoryXpath+"//td[1]")).getText();
                String itemCount = driver.findElement(By.xpath(categoryXpath+"//td[2]")).getText();
                WebElement EditButton = driver.findElement(By.xpath(categoryXpath+"//td[3]//div[@class=\"edit-action\"]"));
                String newCat = "cafeeeeeee";

                if(category.equals("cafe"))
                {
                    //System.out.println(" the category name that will edit is "+category);
                    // find edit elements
                    WebElement EditCategoryEn = driver.findElement(By.xpath(categoryXpath+"//td[3]//input[@name=\"name_enedit\"]"));
                    WebElement EditCategoryAr = driver.findElement(By.xpath(categoryXpath+"//td[3]//input[@name=\"name_aredit\"]"));
                    WebElement DoneButton = driver.findElement(By.xpath(categoryXpath+"//td[3]//input[@class=\"submit-branch\"]"));
                    // edit method
                    ClickButton(EditButton);
                    EditCategoryEn.clear();
                    EditCategoryEn.sendKeys(newCat);
                    EditCategoryAr.clear();
                    JavascriptExecutor js = (JavascriptExecutor) driver;
                    js.executeScript("arguments[0].value = arguments[1]",EditCategoryAr,"قهوههههههههههه");
                    ClickButton(DoneButton);
                    // delete
                    Thread.sleep(6000);
                    WebElement DeleteButton = driver.findElement(By.xpath(categoryXpath+"//td[3]//div[@class=\"delete-action\"]"));
                    WebElement RUSure = driver.findElement(By.xpath(categoryXpath+"//td[3]//input[@class=\"no-noti\"]"));
                    ClickButton(DeleteButton);
                    ClickButton(RUSure);
                    break;
                }
           }
        }
    }
    
    public void AddNewItem() throws InterruptedException {
        Thread.sleep(2000);
        ClickButton(CatalogButton);
        Thread.sleep(5000);
        ClickButton(ItemsButton);
        Thread.sleep(2000);
        ClickButton(AddNewItemButton);
        Thread.sleep(2000);
    }

}
