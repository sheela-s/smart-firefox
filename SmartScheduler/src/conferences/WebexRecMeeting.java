package conferences;

import static org.testng.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class WebexRecMeeting {
  private WebDriver driver;
  private String baseUrl;

  Logger logger = Logger.getLogger(WebexRecMeeting.class);

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
	PropertyConfigurator.configure("log4j.properties");
    driver = new FirefoxDriver();    
    baseUrl = "http://10.77.197.240/";
    Runtime.getRuntime().exec("C:\\Users\\shesadas\\Desktop\\AutoIt\\HandleAuthentication.exe");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  
  @Test
  public void open() throws Exception {

	  driver.get(baseUrl + "/tmsagent/tmsportal/#home");
	  Thread.sleep(1000);
	  driver.manage().window().maximize();
	  logger.info("Browser opened");
	  
	  String atitle = "TelePresence User Portal: 10.77.197.240";
	  String btitle = "Telepresence User Portal";
	  String ctitle = "The TMS User Portal is currently unavailable. Please try again later."; 
	
	  //get page title and store in btitle
	  String dtitle = driver.getTitle();	
	  
	  
	  WebElement element = driver.findElement(By.xpath("html/body/div"));
	  String strng = element.getText();	
   
	  if (dtitle.equals(atitle))
	  {	  	  
         create();    
         edit();
         delete();
      }	  
	  else if (dtitle.equals(btitle))
	  {
		  create();    
	      edit();
	      delete();
	  }
	  else if (ctitle.equals(strng))
	  {
		 logger.error("Server is restating");
		 org.testng.Assert.fail("Fail");
		 tearDown();
	  }
	  else  
      {	  
	     logger.error("Server not accessible");
	     org.testng.Assert.fail("Fail");
         tearDown();
      }	  
  }
  
  
  
  public void create() throws Exception {
  try{
		
    //click on open smart scheduler
    driver.findElement(By.linkText("Open Smart Scheduler")).click();
    Thread.sleep(1000);
    
    
    //click on new meeting
    driver.findElement(By.xpath("//div[3]/div/div/div/div/div[1]/div[1]/h2/button")).click();
    Thread.sleep(1000);
    
    
  //select start time
	  driver.findElement(By.xpath("html/body/div[3]/div/div/div[2]/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div[2]/input")).click();
    driver.findElement(By.xpath("html/body/ul/li[81]")).click();
    Thread.sleep(1000);
    

  	
    //select participants
    driver.findElement(By.xpath("//*[@id='SchedEdit-ParticipantsCollapse']/div/div/div/div[2]/div[1]/div/div/div[1]/input")).click();
    driver.findElement(By.name("search")).click();
    driver.findElement(By.name("search")).clear();
    driver.findElement(By.name("search")).sendKeys("a");
    Thread.sleep(1000);
     driver.findElement(By.xpath("//div[@id='SchedEdit-ParticipantsCollapse']/div/div/div/div[2]/div/div/div/div[2]/div/a/span[2]")).click();
    Thread.sleep(1000);
    
    
    
    //select dial-in participant
    driver.findElement(By.xpath(" //*[@id='SchedEdit-ParticipantsCollapse']/div/div/div/div[2]/div[1]/div/div/div[1]/div/button")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//*[@id='SchedEdit-ParticipantsCollapse']/div/div/div/div[2]/div[1]/div/div/div[1]/div/ul/li[1]/a")).click();
    
	
    //select recurrence pattern
  //select recurrence pattern
    driver.findElement(By.xpath("//*[@id='SchedEdit-ParticipantsCollapse']/div/div/div/div[2]/div[1]/div/div/div[1]/div/button")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//a[contains(@href, '#SchedEdit-RecurrenceCollapse')]")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//*[@id='SchedEdit-RecurrenceCollapse']/div/div/div[2]/div[2]/div[1]/div/button")).click();  
    Thread.sleep(1000);
    driver.findElement(By.linkText("Daily")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//*[@id='SchedEdit-RecurrenceCollapse']/div/div/div[2]/div[2]/div[2]/div/button")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//div[@id='SchedEdit-RecurrenceCollapse']/div/div/div[2]/div[2]/div[2]/div/div/ul/li[2]/a/span")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath("//*[@id='SchedEdit-RecurrenceCollapse']/div/div/div[2]/div[2]/div[3]/input")).click();
    Thread.sleep(1000);
    driver.findElement(By.xpath(" html/body/div[6]/div[1]/table/tbody/tr[6]/td[4]")).click();
    Thread.sleep(1000);
    
	  
  //add webex participant	  
  	driver.findElement(By.xpath("html/body/div[3]/div/div/div[2]/div[2]/div/div/ul/li[2]/a")).click();
  	Thread.sleep(1000);
  	driver.findElement(By.xpath("//*[@id='SchedEdit-WebExCollapse']/div/div/div[2]/div[2]/div[1]/label")).click();
  	Thread.sleep(1000);
  	
  	
  	
    //click save button
    driver.findElement(By.xpath("html/body/div[3]/div/div/div[3]/div/button[1]")).click();    
    logger.info("Created Rec webex Meeting");
    
    }
	catch (Exception e1){
		   logger.error("Meeting not created");
		   org.testng.Assert.fail("Fail");
	 	   driver.quit();
	}
    
    //back to home page
	driver.navigate().back();
	Thread.sleep(1000);
	
	  
  }

  
  
  
  
  public void edit() throws Exception {
   try{
      
	  driver.findElement(By.linkText("Open Smart Scheduler")).click();
      Thread.sleep(1000);
      
      //click on new meeting
      driver.findElement(By.xpath("html/body/div[3]/div/div/div/div/div[1]/div[2]/div/div[2]/div/div/a/div[2]/div")).click();
	    Thread.sleep(1000);
	    
		  
	  //select dial-in participant
	  driver.findElement(By.xpath(" //*[@id='SchedEdit-ParticipantsCollapse']/div/div/div/div[2]/div[1]/div/div/div[1]/div/button")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//*[@id='SchedEdit-ParticipantsCollapse']/div/div/div/div[2]/div[1]/div/div/div[1]/div/ul/li[2]/a")).click();  
      
	  
	  
	  //give webex password
	  driver.findElement(By.xpath("html/body/div[3]/div/div/div[2]/div[2]/div/div/ul/li[2]/a")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//*[@id='webex_password']")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//*[@id='webex_password']")).sendKeys("12345abc");
	  Thread.sleep(1000);
	  
	  
	  
	  //change title name
	  driver.findElement(By.xpath("//*[@id='title']")).click();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//*[@id='title']")).clear();
	  Thread.sleep(1000);
	  driver.findElement(By.xpath("//*[@id='title']")).sendKeys("webex rec meeting");
	  Thread.sleep(1000);
	  
	  

	  //click save button
	  driver.findElement(By.xpath("html/body/div[3]/div/div/div[3]/div/button[1]")).click();    
	  Thread.sleep(1000);
	  logger.info("Edited Rec webex Meeting");
	  
   }
   catch (Exception e2){
 	     logger.error("Meeting not edited");
 	     org.testng.Assert.fail("Fail");
 	     driver.quit();
   }
	  
	  //back to home page
	  driver.navigate().back();
	  Thread.sleep(1000);
      
    
  }
  
  
  public void delete() throws Exception {
	 try{
	    	
	    driver.get(baseUrl + "/tmsagent/tmsportal/#home");
	    driver.manage().window().maximize();
		 
	      
	    driver.findElement(By.linkText("Open Smart Scheduler")).click();
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("html/body/div[3]/div/div/div/div/div[1]/div[2]/div/div[2]/div/div/a/div[2]/div")).click();
	    
	    driver.findElement(By.xpath("html/body/div[3]/div/div/div[3]/div/button[3]")).click();
	    
	    driver.findElement(By.xpath("html/body/div[3]/div/div/div[3]/div/div/div[2]/div/div/div[1]/button")).click();
	    
	    logger.info("Deleted Rec webex Meeting");
	     
	   }
	   catch (Exception e3){
		    logger.error("Meeting not deleted");
		    org.testng.Assert.fail("Fail");
	 	    driver.quit();
	   }
	    //back to home page
		driver.navigate().back();
		Thread.sleep(1000);
	 
	
} 
  
  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
	 
    driver.quit();
    
  }

}

