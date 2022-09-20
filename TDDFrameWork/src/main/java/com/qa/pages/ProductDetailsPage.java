package com.qa.pages;

import com.qa.MenuPage;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductDetailsPage extends MenuPage
{
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Sauce Labs Backpack']")
	private
	MobileElement SLBTitle;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc='test-Description']/android.widget.TextView[2]")
	private 
	MobileElement SLBText;
	
    @AndroidFindBy(accessibility="test-BACK TO PRODUCTS")
    private 
    MobileElement backToProductsBtn;
    
    @AndroidFindBy(accessibility="test-Price") 
    private 
    MobileElement SLBPrice;
    
  
    @AndroidFindBy(xpath="//android.widget.TextView[@text='ADD TO CART']") 
    private 
    MobileElement AddToCart;
    
    
    
public String getSLBTitle() 
{
	String title=getText(SLBTitle);
	System.out.println("title is -"+ title);
	return title;
}

public String getSLBText() 
{
	String txt=getText(SLBText);
	System.out.println("txt is -"+ txt);
	return txt;
}

public String getSLBPrice() 
{
	String price=getText(SLBPrice);
	System.out.println("price is -"+ price);
	return price;
}


/*public String scrollToSLBPriceAndGetSLBPrice() 
{
	return getText(scrollToElement());
	
}*/

public ProductDetailsPage scrollToSLBPrice()
{
	scrollToElement();
	return this;
	
}
public ProductsPage pressbackToProductsBtn() 
{
	System.out.println("navigate back to products page");
	click(backToProductsBtn);
	return new ProductsPage();
}


}
