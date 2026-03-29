package ru.shop;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class BaseTest {
    WebDriver driver;
    WebDriverWait wait;
    protected ru.shop.MainPage mainPage;
    protected String BaseUrl = "https://shop.renlife.ru/";
    public String initialWindows;

    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
        initialWindows = driver.getWindowHandle();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        mainPage = new MainPage(driver, wait, BaseUrl);
    }
    @AfterEach
    public void tearDown() throws IOException {
        var sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File("C:\\ideaScreen\\screenshot.png"));
        driver.quit();
    }
}
