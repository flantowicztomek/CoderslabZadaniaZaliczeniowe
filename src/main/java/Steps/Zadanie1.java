package Steps;

import Pages.WebLogin;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;


public class Zadanie1 {
   WebDriver driver;
    @Given("Can open chrome to {string}")
    public void canOpenChromeTo(String URL) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(URL);
    }

    @When("Take {string} and {string} to login user and add address {string}, {string}, {string}, {string}, {string}")
    public void takeAndToLoginUserAndAddAddress(String login, String password, String alias, String address, String city, String zip, String phone) throws InterruptedException {
        WebLogin WebLogin = new WebLogin(driver);
        WebLogin.LoginUser(login, password);
        WebLogin.AddressAdd(alias, address, city, zip, phone);
    }

    @And("Check and delete Address") 
    public void checkAndDeleteAddress() {
        WebLogin WebLogin = new WebLogin(driver);
        WebLogin.CheckAndDelete();
    }
    @Then("User is logged in")
    public void userIsLoggedIn() {
        driver.quit();
    }


}
