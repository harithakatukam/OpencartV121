package pageObjects;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AccountRegistrationPage extends BasePage{

	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
		
	}

@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstname;
@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastname;
@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
@FindBy(xpath="//input[@id='input-telephone']") WebElement txtTelephone;
@FindBy(xpath="//input[@id='input-password']") WebElement txtPassword;
@FindBy(xpath="//input[@id='input-confirm']") WebElement txtConfirmPassword;
@FindBy(xpath="//input[@name='agree']") WebElement chkdPolicy;
@FindBy(xpath="//input[@value='Continue']") WebElement btncontinue;
@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgConfirmation;


public void setFirstname(String fname)
{
	txtFirstname.sendKeys(fname);
}
public void setLastname(String lname)
{
	txtLastname.sendKeys(lname);
}
public void setEmail(String email)
{
	txtEmail.sendKeys(email);
}
public void setTelephone(String tel)
{
	txtTelephone.sendKeys(tel);
}
public void setPassword(String pwd)
{
	txtPassword.sendKeys(pwd);
}
public void setConfirmPassword(String pwd)
{
	txtConfirmPassword.sendKeys(pwd);
}
public void setPrivacyPolicy()
{
	chkdPolicy.click();
}
public void clickContinue()
{
	//sol1
	btncontinue.click();
	
	//sol2
	//btncontinue.submit();
	
	//sol3
	//Actions act=new Actions(driver);
	//act.moveToElement(btncontinue).click().perform();
	
	//sol4
	//JavascriptExecutor js=(JavascriptExecutor)driver;
	//js.executeScript("arguments[0].click();", btncontinue);
	
	
	//sol5
	//btncontinue.sendKeys(Keys.RETURN);
	
	//sol6
	//WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	//wait.until(ExpectedConditions.elementToBeSelected(btncontinue));
}

public String getConfiramtionMsg()
{
	try {
		return(msgConfirmation.getText());
	}catch (Exception e)
	{
		return (e.getMessage());
	}
	
	
}


}
