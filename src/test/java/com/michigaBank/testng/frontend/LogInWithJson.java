package com.michigaBank.testng.frontend;

import hooks.Apphooks;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LogInWithJson extends Apphooks {
    @Test
    public void sample1(){
        System.out.println("Test case 1.................!");
    }
    @Test
    public void sample2(){
        System.out.println("Test case 2.................!");
    }
    @Test
    public void sample3(){
        System.out.println("Test case 3.................!");
    }
    @Test
    public void sample4(){
        System.out.println("Test case 4.................!");
    }

    @Test
    public void testAuth() throws InterruptedException, IOException, ParseException{
        readWriteJSON();
    }
    public String login(String username, String password) throws InterruptedException {
        driver.get("http://demowebshop.tricentis.com");


        //driver.switchTo().alert().accept();

        driver.findElement(By.linkText("Log in")).click();
        driver.findElement(By.name("Email")).sendKeys(username);
        driver.findElement(By.name("Password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@class='button-1 login-button' and @value='Log in']")).click();

        if ((driver.findElements(By.xpath("//input[@id='vote-poll-1']")).size()) > 0) {
            String uname = driver.findElement(By.xpath("//a[@href='/customer/info']")).getText();
            if (uname.equals(username))
                driver.findElement(By.xpath("//a[@href='/logout")).click();
        } else {

            driver.findElement(By.xpath("//a[@href='/login']")).click();
            return "Invalid User";
        }
        return "Valid User";

    }

    @SuppressWarnings("unchecked")
    public void readWriteJSON() throws InterruptedException, IOException, ParseException {
        JSONParser jsonParser = new JSONParser();
        try {
            FileReader reader = new FileReader("src/TestData.json");
            // Read Json file
            Object obj = jsonParser.parse(reader);
            JSONArray userList = (JSONArray) obj;
            //This prints the entire json file
            System.out.println("Users List-> "+userList);
            for(int i = 0; i<userList.size(); i++) {
                JSONObject users = (JSONObject) userList.get(i);
                // this prints every block - one json object
                System.out.println("Users -> "+users);
                JSONObject user = (JSONObject) users.get("users");
                // This prints each data in the block
                System.out.println("User -> "+user);
                String username = (String) user.get("username");
                String password = (String) user.get("password");
                String result = login(username, password);
                user.put("result",result);

                //write JSON file
                try(FileWriter file = new FileWriter("Testdata1.json")){
                    file.append(userList.toJSONString());
                    file.flush();
                } catch(IOException e) {
                    e.printStackTrace();
                }
                System.out.println(user);

            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }

    }
    @Test
    public void sample5(){
        String title = driver.getTitle();
        System.out.println(title);
    }

}
