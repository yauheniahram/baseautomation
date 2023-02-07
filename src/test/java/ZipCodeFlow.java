import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ZipCodeFlow {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");

        ChromeOptions options = new ChromeOptions();
        //        options.addArguments("--headless");

        driver = new ChromeDriver(options);
    }

    @Test
    public void validZipCode() {
        driver.get("https://sharelane.com/cgi-bin/register.py");
        WebElement zipCodeInput = driver.findElement(By.name("zip_code"));
        zipCodeInput.sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();

        WebElement registerButton = driver.findElement(By.cssSelector("input[value='Register']"));
        Assert.assertTrue(registerButton.isDisplayed(), "Register has not been opened (Register button is not displayed)");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}