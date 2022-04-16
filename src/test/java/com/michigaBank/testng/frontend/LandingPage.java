package com.michigaBank.testng.frontend;

import hooks.Apphooks;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Listeners(com.michiganBank.TestNgListeners.ListenersBlog.class)
public class LandingPage extends Apphooks {

    @Test(description = "success with JavascriptExecutor")
    public void openNewTab() {

        driver.get("https://www.google.com/");
        JavascriptExecutor je = (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript("window.open('https://about.google', 'about_page');");






    }
    @Test
    public void testGetAttributesAndValues(){
        driver.get("https://theautomationzone.blogspot.com/2020/07/mix-of-basic-webelements.html");
        // using method getText()
        System.out.println(driver.findElement(By.id("p1")).getText());
        System.out.println(driver.findElement(By.id("p2")).getText());
        System.out.println(driver.findElement(By.id("p3")).getText());
        // using method getAttribute()
        System.out.println(driver.findElement(By.id("p1")).getAttribute("textContent"));
        System.out.println(driver.findElement(By.id("p2")).getAttribute("textContent"));
        System.out.println(driver.findElement(By.id("p3")).getAttribute("innerHTML"));
        // getting value from input box using method getAttribute()
        driver.findElement(By.id("input1")).sendKeys("selenium");
        System.out.println(driver.findElement(By.id("input1")).getAttribute("value"));
        driver.findElement(By.id("input1")).clear();
        driver.findElement(By.id("input1")).sendKeys("hello world");
        System.out.println(driver.findElement(By.id("input1")).getAttribute("value"));

    }
    @Test(groups={"smoke"}, description = "success", enabled = true)
    public void handleAlerts(){
        try {
            driver.get("https://the-internet.herokuapp.com/javascript_alerts");

            // find the notification alert button and click on that
            WebElement notifi_altButton = driver.findElement(By.xpath("//button[text()='Click for JS Alert']"));
            notifi_altButton.click();
            // now move to the alert
            Alert a = driver.switchTo().alert();
            // get text from alert and print it
            String altText = a.getText();
            System.out.println("Alert text is: " + altText);
            a.accept();
            Thread.sleep(5000);

            // find the confirmation  alert button and click it to visible the alert
            WebElement confir_altButton = driver.findElement(By.xpath("//button[text()='Click for JS Confirm']"));
            confir_altButton.click();
            // switch to alert to get text and dismiss
            Alert a2 = driver.switchTo().alert();
            String confirmText = a2.getText();
            System.out.println("Alert text is: "+confirmText);
            a2.dismiss(); // dismiss will click on the cancel button
            Thread.sleep(5000);

            // find the pormpt for input text button and input text
            WebElement promptButton = driver.findElement(By.xpath("//button[text()='Click for JS Prompt']"));
            promptButton.click();
            // get the Alert object showing now
            Alert promptAlt = driver.switchTo().alert();
            String promptStr = promptAlt.getText();
            // prnit the text in the prompt alert
            System.out.println("Alert text is: "+promptStr);
            // submitting my text on prompt alert
            promptAlt.sendKeys("Submitting my Text");
            promptAlt.accept();

        } catch (Exception e){
            System.out.println(e);
        }




    }
    @Test(groups={"smoke"}, description="not define", enabled = true)
    public void verify_checkBoxes_And_RadioButtons(){
        driver.get("https://theautomationzone.blogspot.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        // check the radio button is enabled using isEnabled() method
        WebElement maleButton = driver.findElement(By.xpath("//input[@id='gender-0']"));
        boolean active = maleButton.isEnabled();
        System.out.println("The Male radio button is Enabled: "+ active);
        // check the radio button is selected using isSelected() method
        boolean selection = maleButton.isSelected();
        System.out.println("The Male radio button selected as Default: "+selection);
        // Select the female radio button
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='gender-1']")));
        WebElement femaleButton = driver.findElement(By.xpath("//input[@id='gender-1']"));
        femaleButton.click();
        System.out.println("Female button was selected");


    }
    @Test(description = "success")
    public void sampleMultipleMenu() {
        // Clicking on menu items

        driver.get("https://www.fedex.com/en-us/home.html");
        WebDriverWait wait = new WebDriverWait(driver, 12);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='fxg-wrapper']/ul/child::div[1]")));
        //driver.findElement(By.xpath("//div[@class='fxg-wrapper']/ul/child::div[1]")).click();
    List<WebElement> hMenu = driver.findElements(By.xpath("//div[@class='fxg-wrapper']/ul/child::div"));
    int size = hMenu.size();
        for (int i =1; i<=size; i++) {
            driver.findElement(By.xpath("//div[@class='fxg-wrapper']/ul/child::div["+i+"]")).click();
        }
    }
    @Test(description = "success")
    public void copyAndPaste1() throws InterruptedException{
        // copy paste Actions keyDown and KeyUp

        driver.get("https://www.google.com");
        WebDriverWait wait= new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@class='gLFyf gsfi']")));
        WebElement ele = driver.findElement(By.xpath("//input[@class='gLFyf gsfi']"));
        ele.sendKeys("largeBigTexthere");
        Actions action = new Actions(driver);
        //
        action.click(ele).keyDown(Keys.COMMAND).sendKeys("a").keyUp(Keys.COMMAND).build().perform();
        //copy the text
        action.keyDown(Keys.COMMAND).sendKeys("c").build().perform();
        ele.clear();
        Thread.sleep(5000);
        action.click(ele).keyDown(Keys.COMMAND).sendKeys("v").keyUp(Keys.COMMAND).build().perform();
        Thread.sleep(5000);

    }
    @Test(description = "success except Keys.Arrow")

    public void rightClickOrContextClick() throws InterruptedException {
        // right click on element
        driver.get("https://www.google.com");
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("About")));
        WebElement ele = driver.findElement(By.linkText("About"));
        String parentWindow = driver.getWindowHandle();
        Actions action = new Actions(driver);
        //action.contextClick(ele).build().perform();
        //Thread.sleep(5000);
        action.contextClick(ele).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN);
        action.build().perform();
        Thread.sleep(5000);



    }

    @Test(enabled = false)
    public void tryMouseHover() throws InterruptedException{
        System.out.println("");
        driver.get("https://www.macys.com/");
        WebElement ele = driver.findElement(By.xpath("//span[text()='home']"));
        Actions actions = new Actions(driver);
        actions.moveToElement(ele);


    }
    @Test(description = "success")
    public void sampleDoubleClick() throws InterruptedException {
        driver.get("https://www.google.com");
        WebElement ele = driver.findElement(By.xpath("//input[@class='gLFyf gsfi']"));

        ele.sendKeys("CopyTheText");
        Actions actions = new Actions(driver);
        actions.doubleClick(ele).keyDown(Keys.COMMAND).sendKeys("c").keyUp(Keys.COMMAND).build().perform();
        ele.clear();
        System.out.println("Text is erased");
        Thread.sleep(5000);
        actions.click(ele).keyDown(Keys.COMMAND).sendKeys("v").keyUp(Keys.COMMAND).build().perform();
        Thread.sleep(5000);

    }

    @Test(description = "success")
    public void copyAndPaste3() throws InterruptedException {
        // copy and paste using Actions class keyDown() and KeyUp() methods
        driver.get("https://www.adidas.com/us/account-login");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='login-email']")));
        WebElement email = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement pass = driver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement show = driver.findElement(By.xpath("//span[text()='SHOW']"));
        Actions copyandpaste = new Actions(driver);
        //Actionts sends keys to the element
        copyandpaste.sendKeys(email, "mhossain456@gmail.com").keyDown(Keys.COMMAND).sendKeys("a").build().perform();
        copyandpaste.keyDown(Keys.COMMAND).sendKeys("c").keyUp(Keys.COMMAND).build().perform();
        pass.click();
        copyandpaste.keyDown(Keys.COMMAND).sendKeys("v").keyUp(Keys.COMMAND).build().perform();
        show.click();
        Thread.sleep(5000);
    }
    @Test(description = "success")
    public void copyAndPasete2() throws InterruptedException {
        // copy and paste using keys.chord() method
        driver.get("https://www.adidas.com/us/account-login");

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='login-email']")));
        WebElement email = driver.findElement(By.xpath("//input[@id='login-email']"));
        WebElement pass = driver.findElement(By.xpath("//input[@id='login-password']"));
        WebElement show = driver.findElement(By.xpath("//span[text()='SHOW']"));
        email.sendKeys("mhossain456@gmail.com");
        email.sendKeys(Keys.chord(Keys.COMMAND,"a","c"));
        //email.sendKeys(Keys.TAB);
        pass.click();
        pass.sendKeys(Keys.chord(Keys.COMMAND, "v"));
        show.click();

        Thread.sleep(5000);


    }
    @Test(groups ={"smoke"}, enabled = true, description = "success")
    public void values_in_TexttField(){
        // getText, getAttribute("textContent"), getAttribute("value"), getAttribute("innerHTML")
        driver.get("https://theautomationzone.blogspot.com/");
        String textInTag = driver.findElement(By.id("label")).getText();
        System.out.println(textInTag);
        WebElement nameTestBox = driver.findElement(By.xpath("//input[@id='full_name']"));
                nameTestBox.sendKeys("Please write");
        System.out.println(nameTestBox.getAttribute("value"));
                nameTestBox.clear();
                nameTestBox.sendKeys("Md Jhangir Hossain");
        System.out.println(nameTestBox.getAttribute("value"));



    }

    //@Test (groups = {"smoke"}, enabled = false)
    public void verifyTitle() {
        // open new tab using JavasceriptExecutor
        // getting multiple windows and switching on them
        driver.get("https://www.fedex.com/en-us/home.html");
        String actual = driver.getTitle();
        String expected = "FedEx | Tracking, Shipping, and Locations";
        Assert.assertEquals(actual, expected);
    }
    @Test(groups = {"smoke"}, enabled = true)
    public void open_menuItems_OnTabs_And_Switch_Between_Them(){
        // open new tab using JavascriptExecutor
        driver.get("https://www.fedex.com/en-us/home.html");
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[normalize-space()='Shipping']")));
        driver.findElement(By.xpath("//span[normalize-space()='Shipping']")).click();
        WebElement cShipment = driver.findElement(By.linkText("Create a Shipment"));
        //cShipment.click();
       try {
           Actions action = new Actions(driver);

           action.moveToElement(cShipment);
           //Thread.sleep(5000);
           action.contextClick(cShipment).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
           //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       } catch(Exception e){
           System.out.println(e);
       }



    }


      /** try{
           driver.findElement(By.cssSelector("body")).sendKeys(Keys.COMMAND, "t", Keys.ENTER);
           driver.get("https://www.fedex.com/en-us/home.html");
            System.out.println("printing smoke test ########## 102");
            String actual = driver.getTitle();
            String expected = "FedEx | Tracking, Shipping, and Locations";
            Assert.assertEquals(actual, expected); --done


            //driver.findElement(By.xpath("/html/body/div[1]/header/div/div/nav/div/ul/div[1]/li/a/span")).click();

           driver.findElement(By.linkText("Shipping"));
           //open new tab with js
           JavascriptExecutor js = (JavascriptExecutor) driver;
           String jsCommand = "window.open('https://www.fedex.com/en-us/shipping/international.html', ' int_Shipping');";
           js.executeScript(jsCommand); --done


            // open new tab with Keys.chord() method
           String clickTab = Keys.chord(Keys.COMMAND, "t", Keys.ENTER );
           WebElement element1 = driver.findElement(By.linkText("Create a Shipment"));
           element1.sendKeys(clickTab);

           //Thread.sleep(5000);
//           JavascriptExecutor js = (JavascriptExecutor) driver;
//            js.executeScript("window.open('','_blank');");

            String parentWindow = driver.getWindowHandle();
            Set<String> tabs = driver.getWindowHandles();
            System.out.println(tabs.size());
            for(String tab: tabs){
               if(!tab.equals(parentWindow)){
                   driver.switchTo().window(tab);
                   driver.get("https://www.fedex.com/en-us/online/rating.html");
                   System.out.println(driver.getTitle());
                   //driver.close();
               }
               driver.switchTo().window(parentWindow);
              // driver.findElement(By.xpath("//div[1]/li/div/div[5]")).click();
               driver.findElement(By.linkText("Shipping Rates & Delivery Times")).click();
               driver.navigate().back();
               String title = driver.getTitle();
                System.out.println(title);
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                //driver.quit();

                // opening new tab using new Window(WindowType.TAB)
                //driver.switchTo().new Window(SafariDriver.WindowType.TAB);
                //String keysForTab = Keys.chord(Keys.COMMAND, "t");
                //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                //driver.findElement(By.linkText("NEED HELP?")).click();
//                Actions actions = new Actions(driver);
//                actions.keyDown(Keys.COMMAND).sendKeys("t").keyUp(Keys.COMMAND).build().perform();
                //Thread.sleep(8000);
                driver.get("https://www.fedex.com/en-us/shipping/schedule-manage-pickups.html");
                //Thread.sleep(5000);
                Set<String> helpTab = driver.getWindowHandles();
                for(String help: helpTab){
                    if(!help.equals(parentWindow)){
                        driver.switchTo().window(help);
                        String helpTitle = driver.getTitle();
                        System.out.println("###############"+ helpTitle);
                        //driver.close();
                    }
                }
                driver.switchTo().window(parentWindow);
                String title2 = driver.getTitle();
                System.out.println(title2);



           }




            // getting key sequence
           // String clicklnk = Keys.chord(Keys.CONTROL, Keys.RETURN);
            // passing the link with secquence
          // driver.findElement(By.xpath("//div[1]/li/div/div[5]")).sendKeys(clicklnk);





            //driver.switchTo().alert().accept();
            //driver.navigate().back();


//           List<WebElement> shippingMenu = driver.findElements(By.xpath("//a[contains(.,'Shipping')]"));
//           for(WebElement item: shippingMenu){
//               item.click();
//               driver.navigate().back();
//
//           }
            WebElement sbElement = driver.findElement(By.xpath("//div[@class='fxg-dropdown__sub-menu']"));
           Select menuItems = new Select(sbElement);
           menuItems.selectByVisibleText("Create a Shipment");
           menuItems.selectByVisibleText("Shipping Rates & Delivery Times");
           menuItems.selectByVisibleText("Schedule & Manage Pickups");
           menuItems.selectByVisibleText("Packing & Shipping Supplies");
           menuItems.selectByVisibleText("International Shipping Guide");

           // using loop
           List<WebElement> submenu = driver.findElements(By.xpath("//div[@class='fxg-dropdown__sub-menu']"));
           //WebElement sbElement = driver.findElement(By.xpath("//div[@class='fxg-dropdown__sub-menu']"));
           for(WebElement element: submenu){
               String menu = element.getText();
               System.out.println(menu);
           }




        } catch(Exception e){
            System.out.println("title didn't match");
        }

    }
       ****Commented out****
       **/

    @Test(description = "success")
    public void handle_OK_Prompt_Alerts() throws InterruptedException{
        System.out.println("CLicking on all alerts");
        driver.get("https://demoqa.com/alerts");
        WebElement ele1 = driver.findElement(By.xpath("//div/div/button[@id='alertButton']"));
        ele1.click();
        Thread.sleep(5000);
        // click on Ok button in Alert
        driver.switchTo().alert().accept();
        // handle prompt Alert, getting text and sending text
        WebElement ele2 = driver.findElement(By.xpath("//div/div/button[@id='promtButton']"));
        ele2.click();
        Thread.sleep(5000);
        Alert promptAlert = driver.switchTo().alert();
        String promptAlertText = promptAlert.getText();
        System.out.println("Alert text is "+ promptAlertText);
        promptAlert.sendKeys("Test User");
        promptAlert.accept();




    }
    @Test(description = "success")
    public void handle_Cancel_Alert() throws InterruptedException{
        System.out.println("Clicking on Cancel button");
        driver.get("https://demoqa.com/alerts");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/div/button[@id='confirmButton']")));
        WebElement ele = driver.findElement(By.xpath("//div/div/button[@id='confirmButton']"));
        ele.click();
        Thread.sleep(5000);
        driver.switchTo().alert().dismiss();
        Thread.sleep(3000);
    }

    @Test(enabled = false)
    public void mouseAndKeyboardActions(){
        System.out.println("Mouse and Keyborad actions............!");
        driver.get("https://www.tutorialspoint.com/questions/index.php");
        // Right click and the context menu item click  using  contextclick()
        Actions actions = new Actions(driver);
        //WebElement element = driver.findElement(By.xpath("//span[text()='Python']"));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Python']")));

        actions.moveToElement(driver.findElement(By.xpath("//span[text()='Python'][1]"))).contextClick().sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.RETURN).build().perform();

        //driver.close();
        //rightClick.moveByOffset(200, 300).click().perform();




    }


    @Test(enabled = true)
    public void mainMenuItems(){
        System.out.println("Checking the main menu #################");
        driver.get("https://www.fedex.com/en-us/home.html");
        driver.findElement(By.className("fxg-link fxg-dropdown-js  fxg-keyboard")).click();
       // WebDriverWait wait = new WebDriverWait(driver, 10);
       // wait.until(ExpectedConditions.elementToBeSelected(By.cssSelector("ul>div>li>a")));
        //driver.findElement(By.xpath("//button[contains(@aria-label,'new arrivals')]")).getText();
       List<WebElement> listOfItems = driver.findElements(By.cssSelector("ul>div>li>a"));
        System.out.println("Size of the list: "+ listOfItems.size());
       for(WebElement item: listOfItems){
           System.out.println("Menu item: "+ item.getText());
       }
        Select mainMenu = new Select(driver.findElement(By.cssSelector("ul>div>li>a")));
        mainMenu.selectByVisibleText("Shipping");
        mainMenu.selectByVisibleText("Tracking");
    }




}
