package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/* Data is valid-login success- test pass- logout
 * Data is invalid ---login failed -test fail
 * Data is invalid ---login success -test fail -logout
 * Data is invalid---login failed -test pass
 */

public class TC003_LoginDDT extends BaseClass{

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="Datadriven")//getting data provider from different class
	public void verify_loginDDT(String email,String pwd,String exp)
	{
		logger.info("***** Starting TC003_LoginDDT *****");
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		hp.clickLogin();
		logger.info("Clicked On Login Link");
		LoginPage lp=new LoginPage(driver);
		logger.info("Providing Login Details");
		
		
		//LoginPage
		lp.setEmail(email);
		lp.setpasssword(pwd);
		lp.clickLogin();
		
		
		//Myaccount Page
		MyAccountPage mp=new MyAccountPage(driver);
		boolean targetpage=mp.isMyAccountPageExists();
		
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetpage==true)
			{
				mp.clickLogout();
				Assert.assertTrue(true);
			
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetpage==true)
			{
				mp.clickLogout();
				Assert.assertTrue(false);
			
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}catch(Exception e)
		{
		    logger.error("test failed..");
		      logger.debug("Debug logs..");
		      Assert.fail();
		}
		logger.info("***** Finished TC003_LoginDDT *****");
	}
}
