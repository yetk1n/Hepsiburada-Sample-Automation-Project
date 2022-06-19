package com.hepsiburada.app.categories;

import com.hepsiburada.app.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CategoriesTab extends Base {
    public Logger logger = LoggerFactory.getLogger(getClass());


    //elements of page
    public String popularCategoriesBtn = "//android.widget.TextView[@text='Popüler Kategoriler']";
    public String popularCategoriesTitle = "com.pozitron.hepsiburada:id/textViewChildCategoryTitle";
    public String pageLayout = "com.pozitron.hepsiburada:id/layoutExpandable";
    public String parfumeSection = "//android.widget.TextView[@text='Parfüm']";
    public String yarinKapindaSection = "com.pozitron.hepsiburada:id/textViewTitle";
    public String yarinKapindaLocation = "com.pozitron.hepsiburada:id/textViewLocation";

    public void checkPopularCategoriesPage(){
        waitForElement(pageLayout);
        findElement(popularCategoriesTitle).isDisplayed();
        logger.info("Popular categories page is loaded successfully.");
    }
}
