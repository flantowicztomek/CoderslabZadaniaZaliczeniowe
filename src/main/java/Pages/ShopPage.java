package Pages;


import net.jodah.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class ShopPage {
    @FindBy(id = "category-3")
    static WebElement WebClothes;
    @FindBy(name = "s")
    static WebElement WebSearch;
    @FindBy(xpath = "//*[@id=\"search_widget\"]/form/button/i")
    static WebElement WebMagnifier;
    @FindBy(xpath = "//*[@id=\"js-product-list\"]/div[1]/article/div/div[1]/div/span[3]")
    static WebElement WebPromotion;
    @FindBy(xpath = "//*[@id=\"js-product-list\"]/div[1]/article/div/a/img")
    static WebElement WebSweater;
    @FindBy(name = "qty")
    static WebElement WebQuantity;
    @FindBy(id = "group_1")
    static WebElement WebSize;
    @FindBy(xpath = "//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button")
    static WebElement WebAddToCart;
    @FindBy(xpath = "//*[@id=\"blockcart-modal\"]")
    static WebElement WebCartPopUp;
    @FindBy(xpath = "//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a")
    static WebElement WebProceedCheckout;
    @FindBy(xpath = "//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a")
    static WebElement WebProceedToCheckout;
    @FindBy(name = "confirm-addresses")
    static WebElement WebConfirmAddresses;
    @FindBy(id = "delivery_option_1")
    static WebElement WebDeliveryOptions;
    @FindBy(xpath = "//*[@id=\"js-delivery\"]/button")
    static WebElement WebNextPage;
    @FindBy(id = "payment-option-1")
    static WebElement WebPayByCheck;
    @FindBy(xpath = "//*[@id=\"conditions_to_approve[terms-and-conditions]\"]")
    static WebElement WebIAgree;
    @FindBy(xpath = "//*[@id=\"payment-confirmation\"]/div[1]/button")
    static WebElement WebOrderSubmit;
    @FindBy(xpath = "//*[@id=\"order-items\"]/div/table/tbody/tr[3]/td[2]")
    static WebElement WebPrize;
    @FindBy(xpath = "//*[@id=\"order-details\"]/ul/li[1]")
    static WebElement WebOrderNumber;
    @FindBy(xpath = "//*[@id=\"history-link\"]/span/i")
    static WebElement WebOrderDetail;
    @FindBy(className = "account")
    static WebElement WebUserSetting;
    @FindBy(id = "addresses-link")
    static WebElement WebUserAddress;
    @FindBy(xpath = "/html/body/main/section/div/div/section/section/div[1]/article/div[2]/a[2]")
    static WebElement DeleteAddress;
    @FindBy(xpath = "//*[@id=\"main\"]/footer/a[2]")
    static WebElement ScriptGotoHome;
    @FindBy(xpath = "//*[@id=\"content\"]/table/tbody/tr[1]/th")
    static WebElement WebOrderTable;
    @FindBy(xpath = "//*[@id=\"content\"]/table/tbody/tr[1]/td[2]")
    static WebElement WebPriceTable;
    @FindBy(xpath = "//*[@id=\"content\"]/table/tbody/tr[1]/td[4]/span")
    static WebElement WebStatusTable;



    static WebDriver driver = null;

    public ShopPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public static void GoShop(String Size, String quantity) throws InterruptedException {
        WebClothes.click();
        String product = "Sweater";
        WebSearch.sendKeys(product);
        WebMagnifier.click();
        Assert.isTrue(CheckPromotion(), "No promotion");
        WebSweater.click();
        Select sizeSelect = new Select(WebSize);
        sizeSelect.selectByVisibleText(Size);
        WebQuantity.clear();
        WebQuantity.sendKeys(quantity);
        WebAddToCart.click();
        Thread.sleep(1000);
        Assert.isTrue(CheckPopUp(), "No elements on Cart");
    }

    public static void WhoPayForThat() {
        WebProceedCheckout.click();
        WebProceedToCheckout.click();
        WebConfirmAddresses.click();
        RadioButtonOrder();
        WebNextPage.click();
        WebPayByCheck.click();
        WebIAgree.click();
        WebOrderSubmit.click();
    }

    public static void Compare() throws InterruptedException {
        String Price = WebPrize.getText();
        String OrderNumber = WebOrderNumber.getText();
        WebUserSetting.click();
        WebOrderDetail.click();
        OrderCheck(OrderNumber, Price);
        WebUserSetting.click();
        WebUserAddress.click();
        DeleteAddress.click();
        ScriptGotoHome.click();
        Thread.sleep(2000);
    }

    public static Boolean CheckPromotion() {
        return WebPromotion.isDisplayed();
    }

    public static Boolean CheckPopUp() {
        return WebCartPopUp.isDisplayed();
    }

    public static void RadioButtonOrder() {
        if (!WebDeliveryOptions.isSelected()) {
            WebDeliveryOptions.click();
        }
    }

    public static void OrderCheck(String OrderNumber, String Price){
        String OrderNumberToCheck = OrderNumber.substring(17);
        System.out.println(OrderNumberToCheck + " " + Price);
        String Status = "Awaiting check payment";
        if(WebOrderTable.getText().equals(OrderNumberToCheck) && WebPriceTable.getText().equals(Price) && WebStatusTable.getText().equals(Status)) {
            System.out.println("Order check - OK");
        } else {
            System.out.println("Problems with order status");
        }

    }

}
