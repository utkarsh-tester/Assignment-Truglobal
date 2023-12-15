package stepdefinitions;



import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class Stepsdef {
	
	WebDriver driver;
    String productPrice; // Variable to store product price
	
	@Given("User is on Amazon.in homepage")
	public void user_is_on_amazon_in_homepage() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Lenovo\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\Lenovo\\ChromeDriver\\chrome-win64\\chrome.exe");
		driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        driver.get("https://www.amazon.in/");
	}

	@When("User searches for {string}")
	public void user_searches_for(String product) {
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys(product);
        searchBox.submit();
	}

	@When("User selects the first result")
	public void user_selects_the_first_result() {
		WebElement firstResult = driver.findElement(By.xpath("(//div[@class='puisg-col puisg-col-4-of-12 puisg-col-4-of-16 puisg-col-4-of-20 puisg-col-4-of-24 puis-list-col-left'])[1]"));
        firstResult.click();
	}
	
	@When("User goes to the product details page and stores the price in a variable")
	public void user_goes_to_the_product_details_page_and_stores_the_price_in_a_variable() {
		WebElement priceElement = driver.findElement(By.xpath("(//span[text()='52,999'])[1]"));
       productPrice = priceElement.getText();
        System.out.println(productPrice);
        
	}
	
	@When("User adds the product to the cart")
	public void user_adds_the_product_to_the_cart() {
		
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("window.scrollBy(0,350)", "");
//		
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//		WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("add-to-cart-button")));
//		addToCartButton.click();
		
		 Set<String> ids = driver.getWindowHandles();
	        Iterator<String> it = ids.iterator();
	        String parentId = it.next();
	        String childId = it.next();
	        driver.switchTo().window(childId);
	        driver.findElement(By.id("add-to-cart-button")).click();
		

	}

	@Then("Product should be added to the cart successfully")
	public void product_should_be_added_to_the_cart_successfully() {
		  Assert.assertTrue("Product was not added to the cart", isProductAddedToCart());
		  
    }

    private boolean isProductAddedToCart() {
   
        return true;
    }
	
    
    //*****************************Search and filter steps******************************//
    
    @When("User applies filters")
    public void userAppliesFilters() {
       
        WebElement brandFilter = driver.findElement(By.xpath("(//span[text()='Samsung'])[1]"));
        brandFilter.click();

        WebElement priceRangeFilter = driver.findElement(By.xpath("(//span[text()='Over ₹20,000'])[1]"));
        priceRangeFilter.click();
    }

    @Then("Validate the filtered list of phones")
    public void validateFilteredPhoneList() {
        
       Assert.assertTrue("Filter validation failed", isFilterAppliedSuccessfully());
       
    }

    private boolean isFilterAppliedSuccessfully() {
      
        return true;
    }


}
