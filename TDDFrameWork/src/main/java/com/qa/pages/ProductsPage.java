package com.qa.pages;

import com.qa.MenuPage;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ProductsPage extends MenuPage
{
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-Toggle\"]/preceding-sibling::android.widget.TextView")
	//@AndroidFindBy(xpath="(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
	private 
	MobileElement ProductTitleTxt;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='test-Item title']")
	//@AndroidFindBy(xpath="(//android.widget.TextView[@content-desc=\"test-Item title\"])[1]")
	private 
	MobileElement SLBTitle;
	
	//@AndroidFindBy(xpath="(//android.widget.TextView[@content-desc=\"test-Price\"])[1]")
	@AndroidFindBy(xpath="//android.widget.TextView[@content-desc='test-Price']")
	private 
	MobileElement SLBPrice;
	
	public String getTitle()
	{
		String title=getText(ProductTitleTxt);
		System.out.println("title is -"+title);
		return title;
	}
	public String getSLBTitle()
	{
		String title=getText(SLBTitle);
		System.out.println("title is -"+title);
		return title;
	}
	
	public String getSLBPrice()
	{
		String price=getText(SLBPrice);
		System.out.println("price is -"+price);
		return price;
	}
	
	public ProductDetailsPage ClickOnSLBTitle()
	{
		System.out.println("navigate back to products page");
		click(SLBTitle);
		return new ProductDetailsPage();
	}


}
