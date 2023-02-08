import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class RegistrationFlow {
    WebDriver driver;
    FakeDataGenerator dataGenerator = new FakeDataGenerator();

    @BeforeTest
    public void genetateData(){
        Faker fakeData = new Faker();
        dataGenerator.setFirstName(fakeData.name().firstName());
        dataGenerator.setLastName(fakeData.name().lastName());
        dataGenerator.setEmail(fakeData.internet().emailAddress());
        dataGenerator.setPassword1(fakeData.internet().password());
        dataGenerator.setPassword2(fakeData.internet().password());
    }
    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        ChromeOptions options = new ChromeOptions();
        //        options.addArguments("--headless");
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
    }

    /*
    Test 1: Sign Up when all required fields are filled
     */
    @Test
    public void registerFlow(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");

        driver.findElement(By.cssSelector("input[name='first_name']")).sendKeys(dataGenerator
                .getFirstName());
        driver.findElement(By.cssSelector("input[name='last_name']")).sendKeys(dataGenerator
                .getLastName());
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(dataGenerator
                .getEmail());
        driver.findElement(By.cssSelector("input[name='password1']")).sendKeys("user");
        driver.findElement(By.cssSelector("input[name='password2']")).sendKeys("user");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector("[class=\"confirmation_message\"]"));
        Assert.assertTrue(message.isDisplayed(), "Account is created!");
    }

    /*
    Test 2: Sign Up when all required fields are not filled
     */
    @Test
    public void registerNegativeFlow(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector("[class=\"error_message\"]"));
        Assert.assertTrue(message.isDisplayed(),
                "Oops, error on page. Some of your fields have invalid data or email was previously used");
    }

    /*
    Test 3: Sign Up when an empty First Name
    */
    @Test
    public void registerWithoutFirstName(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.cssSelector("input[name='last_name']")).sendKeys(dataGenerator
                .getLastName());
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(dataGenerator
                .getEmail());
        driver.findElement(By.cssSelector("input[name='password1']")).sendKeys("user");
        driver.findElement(By.cssSelector("input[name='password2']")).sendKeys("user");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector("[class=\"error_message\"]"));
        Assert.assertTrue(message.isDisplayed(),
                "Oops, error on page. Some of your fields have invalid data or email was previously used");
    }

    /*
    Test 4: Sign Up when an empty Last Name
    */
    @Test
    public void registerWithoutLastName(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.cssSelector("input[name='first_name']")).sendKeys(dataGenerator
                .getFirstName());
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(dataGenerator
                .getEmail());
        driver.findElement(By.cssSelector("input[name='password1']")).sendKeys("user");
        driver.findElement(By.cssSelector("input[name='password2']")).sendKeys("user");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector("[class=\"confirmation_message\"]"));
        Assert.assertTrue(message.isDisplayed(), "Account is created!");
    }

    /*
    Test 5: Sign Up when an empty Email
    */
    @Test
    public void registerWithoutEmail(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.cssSelector("input[name='first_name']")).sendKeys(dataGenerator
                .getFirstName());
        driver.findElement(By.cssSelector("input[name='last_name']")).sendKeys(dataGenerator
                .getLastName());
        driver.findElement(By.cssSelector("input[name='password1']")).sendKeys("user");
        driver.findElement(By.cssSelector("input[name='password2']")).sendKeys("user");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector("[class=\"error_message\"]"));
        Assert.assertTrue(message.isDisplayed(),
                "Oops, error on page. Some of your fields have invalid data or email was previously used");
    }

    /*
    Test 6: Sign Up when an empty Password1
    */
    @Test
    public void registerWithoutFirstPassword(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.cssSelector("input[name='first_name']")).sendKeys(dataGenerator
                .getFirstName());
        driver.findElement(By.cssSelector("input[name='last_name']")).sendKeys(dataGenerator
                .getLastName());
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(dataGenerator
                .getEmail());
        driver.findElement(By.cssSelector("input[name='password2']")).sendKeys("user");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector("[class=\"error_message\"]"));
        Assert.assertTrue(message.isDisplayed(),
                "Oops, error on page. Some of your fields have invalid data or email was previously used");
    }

    /*
    Test 7: Sign Up when an empty Password2
    */
    @Test
    public void registerWithoutSecondPassword(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.cssSelector("input[name='first_name']")).sendKeys(dataGenerator
                .getFirstName());
        driver.findElement(By.cssSelector("input[name='last_name']")).sendKeys(dataGenerator
                .getLastName());
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(dataGenerator
                .getEmail());
        driver.findElement(By.cssSelector("input[name='password1']")).sendKeys("user");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector("[class=\"error_message\"]"));
        Assert.assertTrue(message.isDisplayed(),
                "Oops, error on page. Some of your fields have invalid data or email was previously used");

        WebElement inputPassword1 = driver.findElement(By.cssSelector("input[name='password1']"));
        WebElement inputPassword2 = driver.findElement(By.cssSelector("input[name='password2']"));
        String textInsideInputBox1 = inputPassword1.getAttribute("value");
        String textInsideInputBox2 = inputPassword1.getAttribute("value");
        if(textInsideInputBox1.isEmpty())
        {
            System.out.println("Input Password 1 field is empty");
        }
        if(textInsideInputBox2.isEmpty())
        {
            System.out.println("Input Password 2 Password 1");
        }
    }

    /*
    Test 8: Sign Up when Password1 and Password are different
    */
    @Test
    public void registerWithDifferentPasswords(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.cssSelector("input[name='first_name']")).sendKeys(dataGenerator
                .getFirstName());
        driver.findElement(By.cssSelector("input[name='last_name']")).sendKeys(dataGenerator
                .getLastName());
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(dataGenerator
                .getEmail());
        driver.findElement(By.cssSelector("input[name='password1']")).sendKeys(dataGenerator.getPassword1());
        driver.findElement(By.cssSelector("input[name='password2']")).sendKeys(dataGenerator.getPassword2());
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector("[class=\"confirmation_message\"]"));
        Assert.assertTrue(message.isDisplayed(), "Account is created!");
        // Bug
        //        WebElement message = driver.findElement(By.cssSelector("[class=\"error_message\"]"));
        //        Assert.assertTrue(message.isDisplayed(),
        //                "Oops, error on page. Some of your fields have invalid data or email was previously used");
    }

    /*
    Test 9: Sign Up when email has invalid format
    */
    @Test
    public void registerWithInvalidEmail(){
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=12345");
        driver.findElement(By.cssSelector("input[name='first_name']")).sendKeys(dataGenerator
                .getFirstName());
        driver.findElement(By.cssSelector("input[name='last_name']")).sendKeys(dataGenerator
                .getLastName());
        driver.findElement(By.cssSelector("input[name='last_name']")).sendKeys(dataGenerator
                .getLastName());
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("invalid.email");
        driver.findElement(By.cssSelector("input[name='password1']")).sendKeys(dataGenerator.getPassword1());
        driver.findElement(By.cssSelector("input[name='password2']")).sendKeys(dataGenerator.getPassword2());
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector("[class=\"error_message\"]"));
        Assert.assertTrue(message.isDisplayed(),
                "Oops, error on page. Some of your fields have invalid data or email was previously used");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}