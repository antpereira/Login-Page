package selenium;

import org.openqa.selenium.firefox.*;
import org.openqa.selenium.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import org.junit.*;

public class loginPage {				
    public static void main(String[] args) throws InterruptedException {									
    	
    	System.setProperty("webdriver.gecko.driver", "/Users/antpereira/eclipse-workspace/geckodriver");
    	
    	WebDriver driver = new FirefoxDriver();
    	
    	//Step 1: Navigate to the URL
    	driver.get("https://phptravels.com/demo/");
    	
    	//added a sleep to slow down things a bit.
    	//Thread.sleep(3000);
    	
    	WebElement title = driver.findElement(By.cssSelector("h2.wow"));
    	
    	// Step 2: Verify the title
    	Assert.assertEquals(title.getText(), "APPLICATION TEST DRIVE");
    	
    	// Step 3: Click on the link in the page to go to the next page
    	WebElement nextPage = driver.findElement(By.xpath("/html/body/div[3]/main/section[2]/div/div/div[2]/div/div/div[2]/div[2]/div/div[1]/div/a/small"));
    	
    	nextPage.click();
    	
    	// Get the handle of the current page to swtich control
    	ArrayList<String> browserTabs = new ArrayList<String> (driver.getWindowHandles());
    	
    	//switch to new tab
    	driver.switchTo().window(browserTabs.get(1));

    	// Added a wait to avoid the element not being found
    	driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);
    	
    	// Step4: Find the my account after navigating to the page
    	WebElement myAccount = driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[2]/ul/li[1]/a"));
    	myAccount.click();
    	
    	// Step4: Find the login from dropdown
    	WebElement login = driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[2]/ul/li[1]/ul/li[1]/a"));
    	login.click();
    	
        // Get the WebElement corresponding to the Email Address(TextField)		
        WebElement email = driver.findElement(By.name("username"));							

        // Get the WebElement corresponding to the Password Field		
        WebElement password = driver.findElement(By.name("password"));							
        
        // Send input for email and password
        email.sendKeys("user@phptravels.com");					
        password.sendKeys("demouser");					
        
        // Find the submit button		
        WebElement loginbutton = driver.findElement(By.xpath("/html/body/div[6]/div[1]/div[1]/form/button"));	
        loginbutton.click();
        
    	WebElement invoice = driver.findElement(By.xpath("/html/body/div[6]/div[2]/div[2]/div/div[2]/div/div[1]/div[2]/div[4]/a"));
    	invoice.click();
    	
    	ArrayList<String> browserTabs2 = new ArrayList<String> (driver.getWindowHandles());
    	driver.switchTo().window(browserTabs2.get(2));
    	
    	// Step 5: Assert text INVOICE
    	WebElement invoiceTestVerify = driver.findElement(By.xpath("/html/body/div[6]/div[2]/div/table/tbody/tr[2]/td/div[1]/table/tbody/tr/td/div[3]"));
    	Assert.assertEquals(invoiceTestVerify.getText(), "INVOICE");
    	
    	driver.quit();		  		
    }	
}