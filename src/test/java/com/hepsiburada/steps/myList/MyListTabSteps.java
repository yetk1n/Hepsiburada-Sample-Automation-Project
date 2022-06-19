package com.hepsiburada.steps.myList;

import com.hepsiburada.app.Base;
import com.hepsiburada.app.categories.CategoriesTab;
import com.hepsiburada.app.home.HomeTab;
import com.hepsiburada.app.myList.MyListTab;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyListTabSteps {
    private Logger logger = LoggerFactory.getLogger(getClass());

    //Hepsiburada Pages
    private Base base;
    private MyListTab myListTab;
    private HomeTab homeTab;


    //constructor
    public MyListTabSteps() {
        base = new Base();
        myListTab = new MyListTab();
        homeTab = new HomeTab();
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
