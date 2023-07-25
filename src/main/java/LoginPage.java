import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {
    // initiate driver here
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    // find element
    @FindBy(name = "email")
    private WebElement EmailText;

    @FindBy(name = "password")
    private WebElement PasswordText;

    @FindBy(className = "button-input")
    private WebElement LoginButton;

    public void Login(String email, String password) throws InterruptedException {
        SendKeyForLoginFields(EmailText,email);
        SendKeyForLoginFields(PasswordText,password);
        ClickButton(LoginButton);

        Thread.sleep(3000);

        //valid login
        //Assert.assertTrue("LoginAssertion",driver.getCurrentUrl() == "http://transmission-dev.azurewebsites.net/");

    }
}
