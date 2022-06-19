package com.hepsiburada.steps;

import com.hepsiburada.app.Base;
import com.hepsiburada.app.categories.CategoriesTab;
import com.hepsiburada.app.home.HomeTab;
import com.hepsiburada.app.myList.MyListTab;
import com.hepsiburada.app.myCart.MyCartTab;
import com.hepsiburada.driver.Driver;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StepImpl extends Driver{
    private Logger logger = LoggerFactory.getLogger(getClass());

    //Hepsiburada Pages
    private Base base;
    private HomeTab homeTab;
    private CategoriesTab categoriesTab;
    private MyCartTab myCartTab;
    private MyListTab myListTab;

    //constructor
    public StepImpl() {
        base = new Base();
        homeTab = new HomeTab();
        categoriesTab = new CategoriesTab();
        myCartTab = new MyCartTab();
        myListTab = new MyListTab();
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

    @Step("Go to the Categories Tab")
    public void goCategoriesTab() {
        base.waitForElement(base.categoriesTab);
        base.goToTab("Kategorilerim");
    }

    @Step("Select any category and go to the list page")
    public void selectAnyCategory() {
        //Popular Categories is selected for this step.
        base.findElement(categoriesTab.popularCategoriesBtn).click();

        //Check if the page loaded successfully or not
        categoriesTab.checkPopularCategoriesPage();
        base.findElement(categoriesTab.parfumeSection).click();
    }

    @Step("Check the city at the 'Yarin Kapinda' section is the same with saved city")
    public void checkYarinKapinda() {
        base.waitForElement(categoriesTab.yarinKapindaSection);
        if(base.findElement(categoriesTab.yarinKapindaLocation).getText().equals("Bursa")){
            logger.info("City at the 'Yarin Kapinda' section is the same with saved city!");
        }
        else{
            logger.info("[***Error***] City at the 'Yarin Kapinda' section is not the same with saved city!");
        }
        base.waitForSeconds(3);
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

    @Step("Close the product image")
    public void closeProductImage(){
        base.goBack();

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

    @Step("Go to the my favorites page and check the products")
    public void goLikesPageAndCheck(){
        base.goBack();
        base.goToTab("Listelerim");
        base.waitForElement(myListTab.myFavorites);
        base.findElement(myListTab.myFavorites).click();
        base.findElement(myListTab.firstProduct).click();
        if(base.findElement(homeTab.productName).getText().equals(SpecDataStore.get("product-name"))){
            logger.info("The product on the 'My Favorites' page is the same with the favorited product.");
        }
        else{
            logger.info("The element cannot be found on the 'My Favorites' page.");
        }
        base.waitForSeconds(2);
    }

}
