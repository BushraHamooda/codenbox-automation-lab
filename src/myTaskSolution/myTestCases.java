package myTaskSolution;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.time.Duration;
import java.util.Random;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

public class myTestCases {
	
	String website="https://codenboxautomationlab.com/practice/";
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();


	
	
	@BeforeTest
	public void mySetup() {
		driver.get(website);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
	}
	
	@Test(enabled=false )
	public void radioButton() {
		WebElement radioButtonDiv = driver.findElement(By.id("radio-btn-example"));
		radioButtonDiv.findElements(By.tagName("input")).get(0).click();
		boolean ActaullResult=radioButtonDiv.findElements(By.tagName("input")).get(0).isSelected();
		boolean ExpectesResult = true;
		Assert.assertEquals(ActaullResult, ExpectesResult);
		
	}
	@Test(enabled=false )
	public void autocomplete() throws InterruptedException {
		String [] countries = {"jo","sy","ir"};
		int randomIndex=rand.nextInt(countries.length);
		String country =countries[randomIndex];
		
		driver.findElement(By.id("autocomplete")).sendKeys(country);
		Thread.sleep(1000);
        driver.findElement(By.id("autocomplete")).sendKeys(Keys.ARROW_DOWN,Keys.ENTER);
		Thread.sleep(3000);
		 
		 //ما حيعمل اشي وبهاي الحالة بعمل سكرين انه  اختار جد 
		 System.out.println( driver.findElement(By.id("autocomplete")).getText());

	}
	@Test(enabled=false)
	public void selectTag() throws InterruptedException {
		WebElement SelectTag=driver.findElement(By.id("dropdown-class-example"));
		Select mySelctor = new Select(SelectTag);
		mySelctor.selectByIndex(1);
		Thread.sleep(2000);
	    mySelctor.selectByVisibleText("Appium");
		Thread.sleep(2000);

		mySelctor.selectByValue("option3");
		Thread.sleep(2000);

		
		

	}
	@Test(enabled=false)
	public void cheakBox() {
		WebElement cheakBoxDiv = driver.findElement(By.id("checkbox-example"));

		List<WebElement> allCheckBoxes = cheakBoxDiv.findElements(By.tagName("input"));

		for (int i = 0; i < allCheckBoxes.size(); i++) {
			allCheckBoxes.get(i).click();
			Assert.assertEquals(allCheckBoxes.get(i).isSelected(), true);
		}
		
			
	
			
	}
	@Test(enabled=false)
	public void switchWindow () throws InterruptedException {
		WebElement openWindowButton = driver.findElement(By.id("openwindow"));
		openWindowButton.click();
		//all world use this 2 line when go from page to  another page 
		Set <String>handles =driver.getWindowHandles();
		List<String>allTabs =new ArrayList<>(handles);
		driver.switchTo().window(allTabs.get(1));
		Thread.sleep(2000);
		System.out.println(driver.getTitle());
		driver.switchTo().window(allTabs.get(0));
		Thread.sleep(2000);
		System.out.println(driver.getTitle());
	}
	@Test(enabled=false)
	public void  switchTab() throws InterruptedException {
		driver.findElement(By.id("opentab")).click();;
		//this 3line write everytime when open tap
		Set <String>handles =driver.getWindowHandles();
		List<String>allTabs =new ArrayList<>(handles);
		driver.switchTo().window(allTabs.get(1));
		Thread.sleep(2000);
		System.out.println(driver.getTitle());

	}
	@Test(enabled=false)
	public void alertAndConfarim () throws InterruptedException {
		// We use scroll so we can interact with elements that are not visible on the screen
		// and to avoid errors during execution.
		JavascriptExecutor js =(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		//****************************************
		String myName="Ahmad";
       
		driver.findElement(By.id("name")).sendKeys(myName);
		driver.findElement(By.id("alertbtn")).click();
        System.out.println(driver.switchTo().alert().getText());
        System.out.println(driver.switchTo().alert().getText().contains(myName));
       boolean ActuallValye= driver.switchTo().alert().getText().contains(myName);
       Assert.assertEquals(ActuallValye, true);
       


		driver.switchTo().alert().accept();
		//driver.findElement(By.id("name")).sendKeys("Bushra");
		//driver.findElement(By.id("confirmbtn")).click();
		//driver.switchTo().alert().dismiss();;
	}
	@Test(enabled=false)
	public void theTable() {

	    WebElement theTable = driver.findElement(By.id("product"));

	    List<WebElement> allData = theTable.findElements(By.tagName("td"));
		     System.out.println(allData.get(1).getText());

	   // for (int i = 0; i < allData.size(); i++) {
	   //     System.out.println(allData.get(i).getText());
	 //   }
	}
	@Test(enabled=false)
	public void hideAndShow() {
		WebElement hideButton = driver.findElement(By.id("hide-textbox"));
		WebElement showButton = driver.findElement(By.id("show-textbox"));
		WebElement textBox= driver.findElement(By.id("displayed-text"));
		hideButton.click();
		boolean ActuallResult=textBox.isDisplayed();
		boolean ExpectedResult = false;
		Assert.assertEquals(ActuallResult, ExpectedResult);
		// if i replace between hide to show
		showButton.click();
		boolean ActuallResult1=textBox.isDisplayed();
		boolean ExpectedResult1 = true;
		Assert.assertEquals(ActuallResult1, ExpectedResult1);

		
		
	}
	@Test(enabled=false)
	public void EnabledAndDisabled() {
		WebElement DisableButton= driver.findElement(By.id("disabled-button"));
		WebElement EnableButton= driver.findElement(By.id("enabled-button"));

		DisableButton.click();
		WebElement TextBox= driver.findElement(By.id("enabled-example-input"));
		boolean ActuallResult2=TextBox.isEnabled();
		boolean ExpectedResult2 = false;
		Assert.assertEquals(ActuallResult2, ExpectedResult2);
		// if i replace between disabled to senabled

		EnableButton.click();
		boolean ActuallResult3=TextBox.isEnabled();
		boolean ExpectedResult3 = true;
		Assert.assertEquals(ActuallResult3, ExpectedResult3);


		


	}
	@Test(enabled=false)
	public void MouseHover() throws InterruptedException {
		JavascriptExecutor js =(JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1700)");
		Thread.sleep(3000);
		WebElement TheHoverItem= driver.findElement(By.id("mousehover"));
		Actions action = new Actions(driver);
		action.moveToElement(TheHoverItem).build().perform();
		driver.findElement(By.linkText("Reload")).click();
		driver.findElement(By.linkText("Top")).click();

		
		
		
	}
	@Test(enabled=false)
	public void Calendar() throws IOException, InterruptedException {
	    Date mydate = new Date(); // التاريخ والوقت الحالي
	    String fileName = mydate.toString().replace(":", "-");
	    System.out.println(fileName);

	    driver.findElement(By.linkText("Booking Calendar")).click();
	    Set<String> handles = driver.getWindowHandles();
	    List<String> allTabs = new ArrayList<>(handles);
	    driver.switchTo().window(allTabs.get(1));

	    Thread.sleep(5000);

	    TakesScreenshot ts = (TakesScreenshot) driver;
	    File file = ts.getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("./screenshot/" + fileName + ".jpg"));
	}
	@Test
	public void Iframe () {
		// 4 use  for (switch to)--->1)move to new page (tap to tap )2)move from page to page(window to window) 3) move from page to iframe 4)alert (acceptce,dismmis,gitText)
		driver.switchTo().frame("courses-iframe");
		driver.findElement(By.cssSelector(".ct-mobile-meta-item.btn-nav-mobile.open-menu")).click();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
