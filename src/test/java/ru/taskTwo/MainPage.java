package ru.taskTwo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl;
    protected final String url = "/pages/news.html";
    private JavascriptExecutor jsExecutor;

    public MainPage(WebDriver driver, WebDriverWait wait, String BaseUrl){
        this.driver = driver;
        this.wait = wait;
        this.baseUrl = BaseUrl;
        jsExecutor = (JavascriptExecutor)driver;
    }

    /**
     *Локатор, кнопка скрола вверх страницы
     */
    private By tag = By.xpath("//div[@id='ak-top']");

    private Actions actions(){
        return new Actions(driver);
    }

    /**
     * Открываем страничку
     */
    public void openUrl(){
        driver.get(baseUrl);
    }

    /**
     * JS для прокрутки в самый низ страницы
     */
    public void actionScroll(){
       jsExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public boolean setButton(){
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(tag)));
        return driver.findElement(tag).isDisplayed();
    }

}
