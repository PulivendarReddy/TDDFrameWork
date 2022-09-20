package com.qa.pages;

import com.qa.BaseTest;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPage extends BaseTest
{
	@AndroidFindBy(accessibility="test-Username")
	private 
	MobileElement usernameTxtFld;
	
	@AndroidFindBy(accessibility="test-Password")
	private 
	MobileElement passwordTxtFld;
	
	@AndroidFindBy(accessibility="test-LOGIN")
	private 
	MobileElement loginBtn;
	
	@AndroidFindBy(xpath="//android.view.ViewGroup[@content-desc=\"test-Error message\"]/android.widget.TextView")
	private 
	MobileElement errTxt;
	
	
	public LoginPage enterUserName(String username)
	{
		
		clear(usernameTxtFld);
		System.out.println("login with"+username);
		sendKeys(usernameTxtFld,username);
		return this;
	}
	public LoginPage enterPassword(String password)
	{
		clear(passwordTxtFld);
		System.out.println("password is"+password);
		sendKeys(passwordTxtFld,password);
		return this;
	}
	public ProductsPage ClickLoginBtn()
	{
		System.out.println("click on login btn");
		click(loginBtn);
		return new ProductsPage();
	}
	public String getErrTxt()
	{
		String err=getText(errTxt);
		System.out.println("error text is -"+ err);
		return err;
	}
	public ProductsPage login(String username,String password)
	{
		enterUserName(username);
		enterPassword(password);
		return ClickLoginBtn();
		
	}

    
  

}
