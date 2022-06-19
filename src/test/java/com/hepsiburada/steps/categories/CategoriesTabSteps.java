package com.hepsiburada.steps.categories;

import com.hepsiburada.app.Base;
import com.hepsiburada.app.categories.CategoriesTab;
import com.hepsiburada.driver.Driver;
import com.thoughtworks.gauge.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoriesTabSteps extends Driver{
    private Logger logger = LoggerFactory.getLogger(getClass());

    //Hepsiburada Pages
    private Base base;
    private CategoriesTab categoriesTab;

    //constructor
    public CategoriesTabSteps() {
        base = new Base();
        categoriesTab = new CategoriesTab();
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


    @Step("Close the product image")
    public void closeProductImage(){
        base.goBack();

    }

}
