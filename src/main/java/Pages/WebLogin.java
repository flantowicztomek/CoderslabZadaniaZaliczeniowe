package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WebLogin {
    @FindBy(className = "user-info")
    static WebElement SignIn;
    @FindBy(name = "email")
    static WebElement WebEmail;
    @FindBy(name = "password")
    static WebElement WebPassword;
    @FindBy(id = "submit-login")
    static WebElement WebLogIn;
    @FindBy(id = "address-link")
    static WebElement WebAddressLink;
    @FindBy(css = "div.form-group:nth-child(5) > div:nth-child(2) > input:nth-child(1)")
    static WebElement WebAlias;
    @FindBy(css= "div.form-group:nth-child(10) > div:nth-child(2) > input:nth-child(1)")
    static WebElement WebAddress;
    @FindBy(css = "div.form-group:nth-child(13) > div:nth-child(2) > input:nth-child(1)")
    static WebElement WebCity;
    @FindBy(css = "div.form-group:nth-child(12) > div:nth-child(2) > input:nth-child(1)")
    static WebElement WebZip;
    @FindBy(xpath = "//*[@id=\"content\"]/div/div/form/section/div[10]/div[1]/select/option[2]")
    static WebElement WebCountry;
    @FindBy(css="div.form-group:nth-child(15) > div:nth-child(2) > input:nth-child(1)")
    static WebElement WebPhone;
    @FindBy(xpath = "//*[@id=\"content\"]/div/div/form/footer/button")
    static WebElement WebSave;
    @FindBy(xpath = "/html/body/main/section/div/div/section/section/div[1]/article/div[1]/address")
    static WebElement WebAddCheck;
    @FindBy(xpath = "//*[@class=\"address\"]/div[1]/h4")
    static WebElement CheckAlias;
    @FindBy(xpath = "//*[@class=\"address\"]/div[1]/address")
    static WebElement CheckPerson;
    @FindBy(xpath = "/html/body/main/section/div/div/section/section/div[1]/article/div[2]/a[2]")
    static WebElement DeleteAddress;
    @FindBy(xpath = "//*[@id=\"notifications\"]/div/article/ul/li")
    static WebElement CheckDeleteAddress;



    public WebLogin(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public static void LoginUser(String Email, String Password){
        SignIn.click();
        WebEmail.sendKeys(Email);
        WebPassword.sendKeys(Password);
        WebLogIn.click();
        WebAddressLink.click();
    }
    public static void AddressAdd(String Alias, String Address, String City, String Zip, String phone) {
        WebAlias.sendKeys(Alias);
        WebAddress.sendKeys(Address);
        WebZip.sendKeys(Zip);
        WebCity.sendKeys(City);
        WebCountry.click();
        WebPhone.sendKeys(phone);
        WebSave.click();
        System.out.println("----------------------Sprawdzanie adresu------------------------");
        CheckInfoAboutAddedAddress(Alias, Address, City, Zip, phone);
    }

    public static Boolean CheckAddress(){
        Boolean AddresExist;
        if (WebAddCheck.isDisplayed()){
            System.out.println("Address is added");
            AddresExist = true;
            } else {
            System.out.println("No user address on page");
            AddresExist = false;
        }
        return AddresExist;
    }
    public static Boolean CheckInfoAboutAddedAddress(String Alias, String Address, String City, String Zip, String phone){
        Boolean ThisIsAddress;
        String AliasCheck = CheckAlias.getText();
        String[] Date = CheckPerson.getText().split("\n");
        if (AliasCheck.equals(Alias) && Date[1].equals(Address) && Date[2].equals(City) && Date[3].equals(Zip) && Date[5].equals(phone)){
            System.out.println("Check adress parameters - OK");
           ThisIsAddress = true;
           } else {
            System.out.println(" Check adress parameters - NOK");
            ThisIsAddress = false;
        }
        return ThisIsAddress;
    }
    public static void CheckAndDelete(){
        System.out.println("----------------------Dodawanie adresu-----------------------");
        CheckAddress();
        System.out.println("----------------------Usuwanie adresu------------------------");
        DeleteAddress.click();
        CheckDeleteAddress();

    }
    public static Boolean CheckDeleteAddress() {
        Boolean AddresExist;
        if (CheckDeleteAddress.isDisplayed()) {
            System.out.println("Address is deleted");
            AddresExist = true;
        } else {
            AddresExist = false;
        }
        return AddresExist;
    }

}



