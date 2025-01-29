package testCases;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	@Test(groups={"Sanity","Master"})
	public void verify_login() throws IOException
	{
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		logger.info("***** Starting TC002_LoginTest *****");
		try
		{
			//HomePage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		logger.info("Clicked on MyAccount Link");
		hp.clickLogin();
		logger.info("Clicked On Login Link");
		LoginPage lp=new LoginPage(driver);
		logger.info("Providing Login Details");
		//LoginPage
		lp.setEmail(p.getProperty("email"));
		lp.setpasssword(p.getProperty("password"));
		lp.clickLogin();
		//Myaccount Page
		MyAccountPage mp=new MyAccountPage(driver);
		logger.info("Validating MyAccount Page exists or not..");
		boolean targetpage=mp.isMyAccountPageExists();
		Assert.assertTrue(targetpage);  // Assert.assertEquals(targetpage, true, "Login failed");
	    }
        catch( Exception e)
        {
	      logger.error("test failed..");
	      logger.debug("Debug logs..");
	      Assert.fail();
         }
        logger.info("***** Finished TC002_LoginTest *****");
}
	
}


