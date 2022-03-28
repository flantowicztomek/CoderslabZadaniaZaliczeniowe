package Steps;

import Pages.ShopPage;
import Pages.WebLogin;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.Screenshot;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Zadanie2 {
    WebDriver driver;

    @Given("Can open chrome with {string}")
    public void canOpenChromeTo(String URL) {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(URL);
    }
    @When("Take {string} and {string} to login and add address {string}, {string}, {string}, {string}, {string}")
    public void takeAndToLoginAndAddAddress(String login, String password, String alias, String address, String city, String zip, String phone) {
        new WebLogin(driver);
        Pages.WebLogin.LoginUser(login, password);
        Pages.WebLogin.AddressAdd(alias, address, city, zip, phone);
    }
    @And("Buy sweater, check {string}, {string}, address")
    public void buySweaterCheckAddress(String size, String quantity) throws InterruptedException {
        new ShopPage(driver);
        ShopPage.GoShop(size, quantity);
    }

    @And("Pickup and payment")
    public void pickupAndPayment() throws IOException {
        ShopPage.WhoPayForThat();
        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
        ImageIO.write(screenshot.getImage(), "jpg", new File("/home/flantowi_t/screen/ElementScreenshot.jpg"));
    }

    @Then("Check user order and clean \\(delete address), user is logged in")
    public void checkUserOrderAndCleanDeleteOrderAndAddressUserIsLoggedIn() throws InterruptedException {
        ShopPage.Compare();
        driver.quit();
    }

}
