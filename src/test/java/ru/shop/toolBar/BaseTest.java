package ru.shop.toolBar;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    WebDriver driver;
    WebDriverWait wait;
    protected String BaseUrl = "https://shop.renlife.ru/products/good-choise?pol_b__base_sum=100000&agNum=85110";
    protected MainPage mainPage;

    @BeforeEach
    public void setUp(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        driver.manage().window().maximize();
        mainPage = new MainPage(driver, wait, BaseUrl);
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }

}
