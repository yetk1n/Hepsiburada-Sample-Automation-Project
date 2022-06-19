package com.hepsiburada.app;

import com.hepsiburada.driver.Driver;
import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.datastore.DataStore;
import io.appium.java_client.MobileElement;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Base extends Driver {
    //logger
    public Logger logger = LoggerFactory.getLogger(getClass());
    DataStore dataStore = new DataStore();

    public static MobileElement element;

    //elements of page
    public String homeTab = "com.pozitron.hepsiburada:id/nav_graph_home";
    public String categoriesTab = "com.pozitron.hepsiburada:id/nav_graph_category";
    public String cartTab = "com.pozitron.hepsiburada:id/nav_graph_cart";
    public String myListTab = "com.pozitron.hepsiburada:id/nav_graph_my_list";



    public void goToTab(String tabName){
        switch(tabName) {
            case "Anasayfam":
                appiumDriver.findElement(By.id(homeTab)).click();
                break;
            case "Kategorilerim":
                appiumDriver.findElement(By.id(categoriesTab)).click();
                break;
            case "Sepetim":
                appiumDriver.findElement(By.id(cartTab)).click();
                break;
            case "Listelerim":
                appiumDriver.findElement(By.id(myListTab)).click();
                break;
        }
        logger.info(tabName + " tab clicked");
    }

    public void launchHepsiburadaApp(){
        logger.info("Hepsiburada app launched!" );
    }


    //Waits until active driver sees the given element, timeout: 15 seconds
    public void waitForElement(String elementValue){
        long start = System.currentTimeMillis();
        Character first = elementValue.charAt(0);
        if(first.equals('/')){
            element = appiumDriver.findElement(By.xpath(elementValue));
        }
        else{
            element = appiumDriver.findElement(By.id(elementValue));
        }
        WebDriverWait wait = new WebDriverWait(appiumDriver, 15);
        try {
            logger.info("-----Waiting for element to be visible-----");
            wait.withTimeout(Duration.ofSeconds(15)).until(ExpectedConditions.visibilityOf(element));
            logger.info("Element " + element.getText() + " is visible");
            long finish = System.currentTimeMillis();
            long timeElapsed = (finish - start);
            logger.info("Element waiting time: " + timeElapsed + " milliseconds");
        } catch (Exception e) {
            long finish = System.currentTimeMillis();
            long timeElapsed = (finish - start);
            logger.info("Element waiting time: " + timeElapsed + " milliseconds");
            logger.info("Element " + element.getText() + " is not visible");
            Assertions.fail("Element " + element.getText() + " is not visible");
            Gauge.captureScreenshot();
            e.printStackTrace();
        }
    }

    public MobileElement findElement(String element){
        Character first = element.charAt(0);
        if(first.equals('/')){
            return appiumDriver.findElement(By.xpath(element));
        }
        else{
            return appiumDriver.findElement(By.id(element));
        }
    }

    public void waitForSeconds(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void goBack(){
        appiumDriver.navigate().back();
    }


}
