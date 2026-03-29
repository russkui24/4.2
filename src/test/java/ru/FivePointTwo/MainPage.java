package ru.FivePointTwo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl;
    protected final String url = "https://vk.com/skillbox_education";
    private JavascriptExecutor jsExecutor;

    public MainPage(WebDriver driver, WebDriverWait wait, String BaseUrl){
        this.driver = driver;
        this.wait = wait;
        this.baseUrl = BaseUrl;
        jsExecutor = (JavascriptExecutor)driver;
    }
    /**
     * Локатор, кнопка соц.сети ВК
     */
    private By buttonVK = By.cssSelector(".socialLinks__link:nth-child(2)");

    public void openUrl(){
        driver.get(baseUrl);
    }

    public String getUrl(){
        return baseUrl;
    }

    public String urlVK(){
        return url;
    }

    /**
     * Запускаем цикл, который 10 раз добавит запись
     */
    public void addTask(){
        for (int i = 1; i <= 2; i++){
            WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".pageCreate__sidebarHeaderAdd use")));
            button.click();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".popup__wrapper")));

            WebElement inputAddText = driver.findElement(By.cssSelector(".popup__content .popup__baseInput input"));
            inputAddText.click();
            inputAddText.sendKeys("Добавил запись " + i);

            WebElement inputAddTextTwo = driver.findElement(By.cssSelector(".popup__content .popup__textarea div.baseTextarea__inner textarea"));
            inputAddTextTwo.click();
            inputAddTextTwo.sendKeys("Добавил запись " + i);

            WebElement buttonClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".baseButton.popup__baseButton")));
            buttonClick.click();
        }
    }

    public void clickButtonVK(){
      WebElement element = wait.until(ExpectedConditions.elementToBeClickable(buttonVK));
      element.click();
    }


}
