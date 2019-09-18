package NopMethods;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;

public class Registration extends Utils {

    LoadProps loadProps =new LoadProps();

    @Before
    public void Setup()
    {
        //Calling method to Lunchbrowser
        LunchBrowser();
    }
    @After
    public void TearDown()
    {
        //Calling method to Closebrowser
       CloseBrowser();
    }


    @Test
    public void UsershouldbeabletoRegistration() {
        //Click on Registration Button Method
        ClickElement(By.xpath("//a[@class='ico-register']"));
        //Enter First Name by Method
        EnterText(By.id("FirstName"),LoadProps.getProperty("FirstName"));
        //Enter LastName by Method
        EnterText(By.xpath("//input[@name='LastName']"),LoadProps.getProperty("LastName"));
        //Select Date by Calling Method
        SelectByValue(By.xpath("//select[@name=\"DateOfBirthDay\"]"),LoadProps.getProperty("DateOfBirthDay"));
        //Select Month by Calling Method
        SelectByIndex(By.xpath("//select[@name=\"DateOfBirthMonth\"]"),6);
        //Select Year by Calling Method
        SelectByValue(By.xpath("//select[@name=\"DateOfBirthYear\"]"),LoadProps.getProperty("DateOfBirthYear"));
        //Enter Email
        EnterText(By.name("Email"),DynamicAddress());

        //Enter password by Calling method
        EnterText(By.name("Password"),LoadProps.getProperty("Password"));

        //Enter Confirm password
        EnterText(By.name("ConfirmPassword"),LoadProps.getProperty("ConfirmPassword"));

        //Click on register button
        ClickElement(By.xpath("//input[@value=\"Register\"]"));

        //Store expected result in String variable.
       String expectgedMessage = "Your registration not completed";
        //store actual result with location in string variable.
        //String actualMessage = driver.findElement(By.xpath("//div[@class='result']")).getText();
        String actualMessage =getTextFromElement(By.xpath("//div[@class='result']"));
        Assert.assertEquals(actualMessage, expectgedMessage);
    }

    @Test
    public void UserreferProductToFriend() {
        //Calling Registration Method
        UsershouldbeabletoRegistration();
        // to click on nopp comm logo
        ClickElement(By.xpath("//img[@alt='nopCommerce demo store']"));
        //Click on Mac Product
        ClickElement(By.xpath("//h2//a[@href=\"/apple-macbook-pro-13-inch\"]"));
        //Send Email to friend
        ClickElement(By.xpath("//input[@value='Email a friend']"));
        //Enter friend's email Address
        EnterText(By.xpath("//input[@class='friend-email']"),LoadProps.getProperty("friend-email"));
        //Enter Your Email
         ClickElement(By.xpath("//input[@id='YourEmailAddress']"));
        //Enter your message
        EnterText(By.id("PersonalMessage"),LoadProps.getProperty("PersonalMessage"));
        // click on send email button
        ClickElement(By.xpath("//input[@class=\"button-1 send-email-a-friend-button\"]"));
        // Save expected Message in String
        String expectedReferMsg = "Email already send";
        // Save Actual Message in String
       // String actualReferMsg = driver.findElement(By.xpath("//*[@class='result' and contains(text(),'Your message has been sent.')]")).getText();
        String actualReferMsg=getTextFromElement(By.xpath("//*[@class='result' and contains(text(),'Your message has been sent.')]"));
        // Comparing Actual and Expected
        Assert.assertEquals(expectedReferMsg, actualReferMsg);
    }

    @Test
    public void UserAbleToNavigateCameraAndPhoto() {
        //Navigate to Electronics
        ClickElement(By.xpath("//h2/a[@title='Show products in category Electronics']"));
        //Navigate to Camera & Photo
         ClickElement(By.xpath("//h2/a[@title='Show products in category Camera & photo']"));
        //Save expected in actualString
        String ExpectedTile = "Camera & photo";
        //Save actual in actualString
        //String ActualTitle = driver.findElement(By.xpath("//div[@ class=\"page-title\"]")).getText();
        String ActualTitle = getTextFromElement(By.xpath("//div[@ class=\"page-title\"]"));
        //Comparing actual and expected
        Assert.assertEquals(ExpectedTile, ActualTitle);
    }

    @Test
    public void UserShouldBeAbleToFilterJwellery() {
        //Navigate to Jewelry Category
        ClickElement(By.linkText("Jewelry"));
        //Filter Jewelery In 700-500 range
        ClickElement(By.xpath("//a[@ href=\"//demo.nopcommerce.com/jewelry?price=700-3000\"]"));
        //Save expected title in String variable.
        String ExpectedTitle = "$700.00 - $3,000.00";
        //Save actual title by locator
        //String ActualTitle = driver.findElement(By.xpath("//span[@class = 'item']")).getText();
        String ActualTitle =getTextFromElement((By.xpath("//span[@class = 'item']")));

        //Comparing actual and actual
        Assert.assertEquals(ExpectedTitle, ActualTitle);
        // Store Actual price in String
       // String ActualPrice= driver.findElement(By.xpath("//span[@class=\"price actual-price\"]")).getText();
        String ActualPrice= getTextFromElement(By.xpath("//span[@class=\"price actual-price\"]"));
        //Print ActualPrice
        System.out.println(ActualPrice);
        // Store Minimum Value
        //String MinimumValue = driver.findElement(By.xpath("//span[@class=\"PriceRange\" and  text()='$700.00']")).getText();
        String MinimumValue =getTextFromElement(By.xpath("//span[@class=\"PriceRange\" and  text()='$700.00']"));
        // Print Minimum Value
        System.out.println(MinimumValue);
        //Store MaximumValue
        //String MaximumValue = driver.findElement(By.xpath("//span[@class=\"PriceRange\" and text()='$3,000.00']")).getText();
        String MaximumValue =getTextFromElement(By.xpath("//span[@class=\"PriceRange\" and text()='$3,000.00']"));
        // Print MaximumValue
        System.out.println(MaximumValue);
        //Convert String ActualPrice to float
        float ap = Float.parseFloat(ActualPrice.replace(",","").substring(1));
        //Print ap to check
        System.out.println(ap);
        //Convert String MaximumPrice to float
        float max = Float.parseFloat(MaximumValue.replace(",","").substring(1));
        //Print Max to check
        System.out.println(max);
        //Convert String MinimumPrice to float
        float min =Float.parseFloat(MinimumValue.substring(1));
        System.out.println(min);
        // Checking actual price between minimum and Maximum Range
        Assert.assertTrue(ap>=min && ap<=max);
    }

    @Test
    public void UserShouldBeAbleToAddTwoProductInShoppingCart() {
        //Click on on books category.
        ClickElement(By.linkText("Books"));
        //Click book name "First Prize Pies".
        ClickElement(By.linkText("First Prize Pies"));
        //Click on "Add to cart" to enter first book in cart.
        ClickElement(By.xpath("//input[@id='add-to-cart-button-38']"));
        //Make Chromedriver Wait Until first product adds into basket.
        waitForElementVisible(By.xpath("//span[@class='cart-qty' and text()='(1)']"),10);

        //Click on book name "Fahrenheit 451 by Ray Bradbury".
        ClickElement(By.linkText("Fahrenheit 451 by Ray Bradbury"));
        //Click on "Add to cart" to enter second book in cart.
         ClickElement(By.xpath("//input[@id='add-to-cart-button-37']"));
        //Make Chromedriver Wait Until second product adds into basket.
        waitForElementVisible(By.xpath("//span[@class='cart-qty' and text()='(2)']"),10);
        //Click on "Shopping cart".
        ClickElement(By.xpath("//a[@href=\"/cart\" and text()='shopping cart']"));
        //ExpectedResult
        String ExpectedResult = "Shopping cart";
        // ActualResult
       // String ActualResult = driver.findElement(By.linkText("Shopping cart")).getText();
        String ActualResult = getTextFromElement(By.linkText("Shopping cart"));

        //Actual product1 checking
        String Actualbook1 = getTextFromElement(By.linkText("First Prize Pies"));

        // Store Actual book1 in String
        String Expectedbook1 = "First Prize Pies";
        //Comparing Actualbook1 with expectedbook1
        Assert.assertEquals(Expectedbook1,Actualbook1);

        //Actual product2 checking
        String Book2= getTextFromElement(By.linkText("Fahrenheit 451 by Ray Bradbury"));

        //Store Expectedbook2 in string
        String Expectedbook2 = "Fahrenheit 451 by Ray Bradbury";

        //Comparing Actualbook2 with expectedbook2
         Assert.assertEquals(Expectedbook2,Book2);

         //Comparing Actual result with Actual Result
           Assert.assertEquals(ActualResult,ExpectedResult);
    }
}


