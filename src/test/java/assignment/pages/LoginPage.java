package assignment.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import assignment.base.Base;

public class LoginPage extends Base {
   
	public LoginPage()
	{
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//img[@src='img/logo-big.png']")
    private WebElement imageLogo;

    @FindBy(xpath = "//input[@id='username'] ")
    private WebElement userName;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passWord;

    @FindBy(xpath = "//button[@id='log-in']")
    private WebElement loginBtn;
    
    @FindBy(xpath="/html[1]/body[1]/div[1]/div[1]/div[3]") 
    private WebElement errorMessage;
    
    @FindBy(xpath="//img[@src='img/twitter.png']")
    private WebElement twitterLogo;
    
    @FindBy(xpath="//img[@src='img/facebook.png']")
    private WebElement fbLogo;
    
    @FindBy(xpath="//div[@class='buttons-w']//div[2]")
    private WebElement inLogo;
    
    
    public boolean verifyfbLogo()
    {
    	return fbLogo.isDisplayed();
    }
    
    public boolean verifyTwitterLogo()
    {
    	return twitterLogo.isDisplayed();
    }

    public boolean verifyInLogo()
    {
    	return inLogo.isDisplayed();
    }

    
    
    public boolean logoPresent() {
        return imageLogo.isDisplayed();
    }
    
    public String verifyUrl()
    {
    	return driver.getCurrentUrl();
    }
    public String verifyTitle()
    {
    	return driver.getTitle();
    }
    
    public String verifyLogin() {
        userName.sendKeys("swapnil123");
        passWord.sendKeys("Swapnil@09");
        loginBtn.click();
        System.out.println(driver.getCurrentUrl());
        return driver.getCurrentUrl();
    }
    
    public String invalidLogin(String user, String pass) {
        userName.sendKeys(user);
        passWord.sendKeys(pass);
        loginBtn.click();
        return errorMessage.getText();
    }
}
