package com.iprofile.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class IProfileTest {

    public static void main(String[] args) {

        System.setProperty(
                "webdriver.chrome.driver", "/Users/sumilon/Downloads/chromedriver-mac-x64/chromedriver");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Instantiate a ChromeDriver class.
        WebDriver driver=new ChromeDriver(options);

        driver.get("http://www.sumilon.one");

        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("Sumilon");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("sumilon");
        WebElement submit = driver.findElement(By.tagName("button"));
        submit.click();

        WebElement todoLink = driver.findElement(By.xpath("/html/body/nav/div[2]/ul[1]/li[2]/a"));
        todoLink.click();

        WebElement todoAddLink = driver.findElement(By.xpath("/html/body/div[1]/div[1]/a[1]"));
        todoAddLink.click();

        WebElement description = driver.findElement(By.name("description"));
        description.sendKeys("Test Task Added");

        WebElement priority = driver.findElement(By.name("priority"));
        priority.sendKeys("10");

        WebElement addTask = driver.findElement(By.xpath("//*[@id=\"todo\"]/button"));
        addTask.click();

        WebElement search = driver.findElement(By.xpath("//*[@id=\"dtBasicExample_filter\"]/label/input"));
        search.sendKeys("Test Task Added");

        WebElement delete = driver.findElement(By.xpath("//*[@id=\"dtBasicExample\"]/tbody/tr/td[6]/a"));
        delete.click();

        WebElement logout = driver.findElement(By.xpath("/html/body/nav/div[2]/ul[2]/li/a"));
        logout.click();

    }
}
