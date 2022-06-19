package com.hepsiburada.app.home;

import com.hepsiburada.app.Base;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static java.time.Duration.ofMillis;

public class HomeTab extends Base {
    //logger
    public Logger logger = LoggerFactory.getLogger(getClass());

    //elements of page
    public String locationBtn = "com.pozitron.hepsiburada:id/textViewLocation";
    public String currentLocation = "com.pozitron.hepsiburada:id/textViewUseCurrentLocation";
    public String phoneLocation = "//android.widget.TextView[@text='Bursa']";
    public String kaydetBtn = "com.pozitron.hepsiburada:id/button";
    public String locationSavedPopup = "//android.widget.TextView[@text='Konumunuz kaydedildi.']";
    public String superFiyatTitle = "com.pozitron.hepsiburada:id/dod_title";
    public String superFiyatAllBtn = "com.pozitron.hepsiburada:id/dod_all";
    public String productWithMoreThanOneImage = "com.pozitron.hepsiburada:id/pi_product_list_item_image";

    //it will help to click product image which has more than one image in product detail page
    public String productWithMoreThanOneImageColumn = "//android.view.View[@resource-id='com.pozitron.hepsiburada:id/pi_product_list_item_image']" +
            "/preceding-sibling::androidx.viewpager.widget.ViewPager[@resource-id='com.pozitron.hepsiburada:id/vp_product_list_item_image']";

    public String productImage = "com.pozitron.hepsiburada:id/productImage";
    public String favBtn = "com.pozitron.hepsiburada:id/product_detail_favourites";
    public String email = "[sample]@[mail].com";
    public String password = "[sample-password]";
    public String usernameSection = "//android.widget.EditText[@resource-id='txtUserName']";
    public String loginBtn = "//android.widget.Button[@text='Giriş yap']";
    public String passwordSection = "//android.widget.EditText[@resource-id='txtPassword']";

    public String okButtonAfterLogin = "//android.widget.Button[@resource-id='android:id/button1' and @text='TAMAM']";
    public String productName = "com.pozitron.hepsiburada:id/productName";

    public void currentLocationIsLoaded(){
        waitForElement(phoneLocation);
        logger.info("Phone current location is loaded!");
    }

    public void isLocationPopUpDisplayed(){
        waitForElement(locationSavedPopup);
        logger.info("'Location is saved.' pop-up is displayed!");
    }

    public void scrollDownUntilProductWithMoreThanOneImageDisplayed() throws InterruptedException {
        Dimension d = appiumDriver.manage().window().getSize();
        int height = d.height;
        int width = d.width;

        int startX = (width * 61) / 100;
        int startY = (height * 50) / 100;
        int endX = (width * 61) / 100;
        int endY = (height * 17) / 100;

        for (int i = 0; i < 7; i++) {
            if (appiumDriver.findElements(By.id(productWithMoreThanOneImage)).size() > 0) {
                logger.info(productWithMoreThanOneImage + " element found...");
                Thread.sleep(1000);
                break;
            } else {
                logger.info(productWithMoreThanOneImage + " element not found yet, swiping..");
                TouchAction action = new TouchAction(appiumDriver);
                action.press(PointOption.point(startX, startY))
                        .waitAction(waitOptions(ofMillis(1000)))
                        .moveTo(PointOption.point(endX, endY))
                        .release().perform();
            }
        }
    }

    public void swipeLeftForProductImages(){
        TouchAction action = new TouchAction(appiumDriver);
        action.press(PointOption.point(945, 1111))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(PointOption.point(145, 1111))
                .release().perform();
        logger.info("Swiped to left.");
    }

    public void getProductName(){
        SpecDataStore.put("product-name",findElement(productName).getText());
    }

}
