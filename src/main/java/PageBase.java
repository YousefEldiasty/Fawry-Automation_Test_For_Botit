import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class PageBase {

    protected WebDriver driver;
    public PageBase(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public static void ClickButton(WebElement element)
    {
        element.click();
    }

    public static void SendKeyForLoginFields(WebElement element, String text)
    {
        element.sendKeys(text);
    }


}
