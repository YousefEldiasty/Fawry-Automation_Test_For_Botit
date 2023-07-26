import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

public class ItemPage extends PageBase{
    public ItemPage(WebDriver driver) {
        super(driver);
    }
    JavascriptExecutor js = (JavascriptExecutor) driver;

    //find elements
    @FindBy(name = "en_name")
    private WebElement ItemEgNameField;
    @FindBy(name = "ar_name")
    private WebElement ArabicNameField;
    @FindBy(id = "discountPriceItemId")
    private WebElement DiscountField;
    @FindBy(id = "selectDiscountActiveAndNot")
    private  WebElement StatusElement;
    @FindBy(id = "mainCategory_select")
    private WebElement ChooseMenuCategorySelector;
    @FindBy(className = "up-image2")
    private WebElement UploadImage;
    @FindBy(name = "en_desc")
    private WebElement EnglishDescription;
    @FindBy(name = "ar_desc")
    private WebElement ArabicDescription;
    @FindBy(name = "mainPrice")
    private WebElement Price;

    // Modifiers
    @FindBy(className = "modifiers-link")
    private WebElement Modifiers;

    //Modifier page elements
    @FindBy(xpath = "//*[@id=\"popup1-branch\"]/div/div[2]/div/div[1]/ul/li[1]/input")
    private WebElement ModifiersEnField;
    @FindBy(xpath = "//*[@id=\"popup1-branch\"]/div/div[2]/div/div[1]/ul/li[2]/input")
    private WebElement ModifiersArField;
    @FindBy(xpath = "//*[@id=\"repaet-click2\"]/div[1]/div/ul/li[1]/input")
    private WebElement ModOption1En;
    @FindBy(xpath = "//*[@id=\"repaet-click2\"]/div[1]/div/ul/li[2]/input")
    private WebElement ModOption1Ar;
    @FindBy(xpath = "//*[@id=\"repaet-click2\"]/div[1]/div/ul/li[3]/input")
    private WebElement ModOption1Price;
    @FindBy(xpath = "//*[@id=\"appendTemplate2\"]/div/ul/li[1]/input")
    private WebElement ModOption2En;
    @FindBy(xpath = "//*[@id=\"appendTemplate2\"]/div/ul/li[2]/input")
    private WebElement ModOption2Ar;
    @FindBy(xpath = "//*[@id=\"appendTemplate2\"]/div/ul/li[3]/input")
    private WebElement ModOption2Price;
    @FindBy(id = "#Yessl2")
    private WebElement ApplyAllSizeYes;
    @FindBy(xpath = "//*[@id=\"popup1-branch\"]/div/div[2]/div/input[2]")
    private WebElement ModDone;

    //Item Variations : yes / no
    @FindBy(className = "show-variation")
    private WebElement VariationYes;
    /*
    @FindBy(className = "not-show-variation")
    private WebElement VariationsNo;
    @FindBy(id = "options_M_0")
    private WebElement ItemVariationsOption1Selector;
    @FindBy(id = "token-input-tag_0")
    private WebElement ItemVariationsOption1Field;
    */
    //Extended Preparation Time?
    @FindBy(id = "Nossss")
    private WebElement ExtendedPreparationTimeNo;
    /*
    @FindBy(id = "Yessss")
    private WebElement ExtendedPreparationTimeYes;
    @FindBy(name = "prepTimeMins")
    private WebElement AdditionalTime;
    */
    // save 2 button
    @FindBy(className = "add-more")
    private WebElement SaveAndAddMore;
    @FindBy(className = "exit-button")
    private WebElement SaveAndExit;

    @FindBy(className = "tab-label-2")
    private WebElement ItemsButton;
    @FindBy(className = "catalog-icon")
    private WebElement CatalogButton;
    @FindBy(id = "Grid")
    private WebElement table;
    //Automate method
    public void addItemWithDataProvider(String ITEMENNAME,String ITEMARNAME,String DISCOUNT,String STATUS,String MENUCATEGORY,String image,String ENDESCRIPTION,String ARDESCRIPTION,String PRICE) throws InterruptedException, AWTException {
        ItemEgNameField.sendKeys(ITEMENNAME);
        js.executeScript("arguments[0].value = arguments[1]",ArabicNameField,ITEMARNAME);
        DiscountField.sendKeys(DISCOUNT);
        //Thread.sleep(2000);
        Select Status = new Select(StatusElement);
        Status.selectByVisibleText(STATUS);
        //Dropdown category
        Select MenuCategory = new Select(ChooseMenuCategorySelector);
        MenuCategory.selectByVisibleText(MENUCATEGORY);
        //Upload image
        UploadImageMethodWithDataProvider(image);
        // Write Description
        EnglishDescription.sendKeys(ENDESCRIPTION);
        js.executeScript("arguments[0].value = arguments[1]",ArabicDescription,ARDESCRIPTION);
        // items variation
        js.executeScript("arguments[0].click();",VariationYes);
        // Enter Price
        Price.sendKeys(PRICE);
        //ExtendedPreparationTimeYes.click();
        ExtendedPreparationTimeNo.click();
            /*
        // Add modifiers
        js.executeScript("arguments[0].click();",Modifiers);
        Modifiers.click();
        Thread.sleep(7000);
        *//*
        ModifiersEnField.sendKeys("milk");
        ModifiersArField.sendKeys("حليب");
        ModOption1En.sendKeys("full");
        ModOption1Ar.sendKeys("كامل");
        ModOption1Price.sendKeys("2");
        ModOption2En.sendKeys("half");
        ModOption2Ar.sendKeys("نصف");
        ModOption2Price.sendKeys("4");
        ModDone.click();
        *//*
    */

        //Save Item
        SaveAndExit.click();
        Thread.sleep(2000);
    }
    //Automate method
    public void UploadImageMethodWithDataProvider(String image) throws InterruptedException, AWTException {
        String ImageName = image;
        String ImagePath = System.getProperty("user.dir")+"\\src\\uploads\\"+ImageName;
        UploadImage.click();
        Thread.sleep(2000);
        // Robot class
        Robot robot = new Robot();
        // Copy the path control c
        StringSelection selection = new StringSelection(ImagePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection,null);
        // get into EnterPath field
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        // past control v
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        // press okay by click Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    //Find the element by looping then change the price
    public void changeItemPricePages() throws InterruptedException {
        ClickButton(CatalogButton);
        Thread.sleep(4000);
        ClickButton(ItemsButton);
        Thread.sleep(2000);
        // loop on pages
        WebElement pageItem = driver.findElement(By.xpath("//div[@id= \"Grid_paginate\"]//span"));
        java.util.List<WebElement> pagesItem = pageItem.findElements(By.tagName("a"));
        int pI = pagesItem.size();
        System.out.println("number of pages  = " + pI);

        outerLoop:
        for (int i = 1; i <= pI; i++) {
            String PageXpath = "//div[@id= \"Grid_paginate\"]//span//a["+i+"]";
            WebElement CurrentPageItem = driver.findElement(By.xpath(PageXpath));
            //System.out.println(CurrentPage.getText());
            Thread.sleep(2000);
            CurrentPageItem.click();
            Thread.sleep(2000);
            List<WebElement> rowsElement = table.findElements(By.tagName("tr"));
            int rows = rowsElement.size();
            for (int r=1 ; r<= rows-1 ; r++)
            {
                String categoryXpath="//table[@id=\"Grid\"]//tbody//tr["+r+"]";
                String category = driver.findElement(By.xpath(categoryXpath+"//td[1]")).getText();
                WebElement EditButton = driver.findElement(By.xpath(categoryXpath+"//td[7]//div"));
                String newCat = "cafe";

                if(category.equals("co"))
                {
                    System.out.println("element exist in page =" + (r+1));
                    //System.out.println(" the category name that will edit is "+category);
                    // find edit elements
                    // edit method
                    Thread.sleep(2000);
                    ClickButton(EditButton);
                    Thread.sleep(2000);

                    // Edit Price
                    Price.clear();
                    Price.sendKeys("7");
                    ClickButton(SaveAndExit);
                    break outerLoop;
                }
            }
        }
    }


}


