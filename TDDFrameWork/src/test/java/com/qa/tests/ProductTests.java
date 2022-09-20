package com.qa.tests;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductDetailsPage;
import com.qa.pages.ProductsPage;
import com.qa.pages.SettingsPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class ProductTests extends BaseTest
{
	LoginPage loginPage;
	SettingsPage settingsPage;
	ProductsPage productspage;
	ProductDetailsPage productdetailsPage;
	InputStream datais;
	JSONObject loginUsers;
	
	 @BeforeClass
	  public void beforeClass() throws Exception 
	  {
		 try {
				String dataFileName= "data/loginUsers.json";
				datais=getClass().getClassLoader().getResourceAsStream(dataFileName);
				JSONTokener tokener = new JSONTokener(datais);
				loginUsers = new JSONObject(tokener);
			}catch(Exception e) {
				e.printStackTrace();
				throw e;
			}finally {
				if(datais !=null) {
					datais.close();
				}
			}
			closeApp();
			launchApp();
		  }

	  @AfterClass
	  public void afterClass() 
	  {
		  
	  }
	  @BeforeMethod
	  public void beforeMethod(Method m) 
	  {
		  loginPage=new LoginPage();
		  System.out.println("\n"+"***starting test:"+m.getName()+"********"+"\n");
		  productspage=loginPage.login(loginUsers.getJSONObject("validUser").getString("username"),
				  loginUsers.getJSONObject("validUser").getString("password"));
		  
	  }

	  @AfterMethod
	  public void afterMethod() 
	  {
		  settingsPage=productspage.ClickSettingsBtn();
		  loginPage=settingsPage.presslogoutBtn();
		  
	  }

	 /* @Test
	  public void validateProductOnProductsPage()
	  {
		  SoftAssert sa=new SoftAssert();
		  
		  String SLBTitle=productspage.getSLBTitle();
		  sa.assertEquals(SLBTitle,strings.get("products_page_slb_title"));
		  String SLBPrice=productspage.getSLBPrice();
		  sa.assertEquals(SLBPrice,strings.get("products_page_slb_price"));
		 
		  sa.assertAll();

	  }*/
	  @Test
	  public void validateProductOnProductDetailsPage() 
	  {
		  SoftAssert sa=new SoftAssert();
		  
		  productdetailsPage=productspage.ClickOnSLBTitle();
		  
		  String SLBTitle=productdetailsPage.getSLBTitle();
		  sa.assertEquals(SLBTitle,strings.get("product_details_page_slb_title"));
		  String SLBTxt=productdetailsPage.getSLBText();
		  sa.assertEquals(SLBTxt,strings.get("product_details_page_slb_Txt"));
 
          productdetailsPage.scrollToSLBPrice();
          String SLBPrice=productdetailsPage.getSLBPrice();
		 // String SLBPrice=productdetailsPage.scrollToSLBPriceAndGetSLBPrice();
		  sa.assertEquals(SLBPrice,strings.get("product_details_page_slb_price"));
		  
		  productspage=productdetailsPage.pressbackToProductsBtn();

		  sa.assertAll();

	  }
  
  

 

}
