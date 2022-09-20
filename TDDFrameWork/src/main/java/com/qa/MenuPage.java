package com.qa;

import com.qa.pages.SettingsPage;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class MenuPage extends BaseTest
{
	//@AndroidFindBy (xpath="//android.view.ViewGroup[@content-desc='test-Menu']")
	@AndroidFindBy(accessibility="test-Menu")
	private 
	MobileElement settingsBtn;
	
	public SettingsPage ClickSettingsBtn()
	{
		click(settingsBtn);
		return new SettingsPage();
		
	}


}
