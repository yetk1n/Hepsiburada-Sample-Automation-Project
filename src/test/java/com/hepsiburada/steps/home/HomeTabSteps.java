package com.hepsiburada.steps.home;

import com.hepsiburada.app.Base;
import com.hepsiburada.app.categories.CategoriesTab;
import com.hepsiburada.app.home.HomeTab;
import com.hepsiburada.app.myCart.MyCartTab;
import com.hepsiburada.app.myList.MyListTab;
import com.hepsiburada.driver.Driver;
import com.thoughtworks.gauge.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeTabSteps extends Driver {
    private Logger logger = LoggerFactory.getLogger(getClass());

    //Hepsiburada Pages
    private Base base;
    private HomeTab homeTab;

    //constructor
    public HomeTabSteps() {
        base = new Base();
        homeTab = new HomeTab();
    }

    @Step("Go to the location section on home tab")
    public void goToHomeTab() throws InterruptedException {
        base.waitForElement(base.homeTab);
        base.goToTab("Anasayfam");
        logger.info("Home tab is loaded");
        base.waitForElement(homeTab.locationBtn);
        base.findElement(homeTab.locationBtn).click();
        logger.info("Location button is clicked");
    }

    @Step("Set the location")
    public void setLocation() {
        base.findElement(homeTab.currentLocation).click();
        homeTab.currentLocationIsLoaded();
        base.findElement(homeTab.kaydetBtn).click();
        logger.info("The location is saved successfully!");
    }

    @Step("Check the 'Location is saved.' pop-up")
    public void isLocationSaved() {
        homeTab.isLocationPopUpDisplayed();
    }

    @Step("Open the Hepsiburada")
    public void openApp() {
        base.launchHepsiburadaApp();
    }

    @Step("Go to the Super Fiyat Super Teklif section on home tab")
    public void goSuperFiyatSection(){
        base.waitForElement(homeTab.superFiyatTitle);
        base.findElement(homeTab.superFiyatAllBtn).click();
    }

    @Step("Select a product that has more than one picture in Super Fiyat Super Teklif page")
    public void selectProductOnSuperFiyatPage() throws InterruptedException {
        homeTab.scrollDownUntilProductWithMoreThanOneImageDisplayed();
        base.findElement(homeTab.productWithMoreThanOneImageColumn).click();
        homeTab.getProductName();
    }

    @Step("Open the images of the product and scroll")
    public void scrollRightProductImages(){
        base.findElement(homeTab.productImage).click();
        homeTab.swipeLeftForProductImages();
        base.waitForSeconds(2);
    }

    @Step("Click fav button on product details page")
    public void clickFavButton(){
        base.findElement(homeTab.favBtn).click();
    }

    @Step("Log in on the opened page")
    public void login(){
        base.waitForElement(homeTab.usernameSection);
        base.findElement(homeTab.usernameSection).sendKeys(homeTab.email);
        base.findElement(homeTab.loginBtn).click();
        base.waitForElement(homeTab.passwordSection);
        base.findElement(homeTab.passwordSection).sendKeys(homeTab.password);
        base.findElement(homeTab.loginBtn).click();

        //checking logged in or not
        base.waitForElement(homeTab.okButtonAfterLogin);
        logger.info("Successfully logged in.");
        base.waitForSeconds(1);
        base.findElement(homeTab.okButtonAfterLogin).click();
    }

}
