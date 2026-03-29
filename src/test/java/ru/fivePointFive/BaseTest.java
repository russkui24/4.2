package ru.fivePointFive;

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
import java.util.Set;
import java.util.stream.Collectors;

public class BaseTest {
    WebDriver driver;
    WebDriverWait wait;
    protected ru.fivePointFive.MainPage mainPage;
    String BaseUrl = "https://qajava.skillbox.ru/module5/homework/";
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
