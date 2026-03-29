package ru.shop.toolBar;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage {
    WebDriver driver;
    WebDriverWait wait;
    private final String BaseUrl;
    private JavascriptExecutor jsExecutor;

    public MainPage(WebDriver driver, WebDriverWait wait, String BaseUrl){
        this.driver = driver;
        this.wait = wait;
        this.BaseUrl = BaseUrl;
        jsExecutor = (JavascriptExecutor) driver;
    }

    /**
     * Локатор для Лодера
     */
    private final By loader = By.xpath("//div[@class='el-loading-mask']");

    /**
     * Локатор для кнопки Cookie
     */
    private final By buttonCookie = By.xpath("//*[@class='el-button el-button--info el-button--large cookie-block__button']");

    /**
     * Локатор для Отображения блока по Кальку
     */


    public void openUrl(){
        driver.get(BaseUrl);
        //Ждем когда пропадет лоадер по кальку
        waitForLoader();
    }

    /**
     * Метод для явного ожидания, когда элемент пропадет для Лодера
     */
    private void waitForLoader() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(loader));
        }
        catch (TimeoutException e) {
            //Лоадера может и не быть
        }
    }

    //Метод для скролла
    private void scrollToElement(WebElement element){
        jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    //Скроллим до калькулятора
    public void enableConstructor(){
        WebElement element = driver.findElement(By.xpath("//section[contains(@class,'product-calculator-constructor')]"));
        scrollToElement(element);
    }

    public void clickButtonCookie(){
        // Сначала проверяем, есть ли вообще cookie-баннер (может, его уже нет)
        List<WebElement> element = driver.findElements(buttonCookie);
        if(element.isEmpty()){
            return; // Баннер уже был закрыт или не появился
        }

        //Кликаем на кнопку в cookie баннер
     WebElement cookieBtn = wait.until(ExpectedConditions.elementToBeClickable(buttonCookie));
     cookieBtn.click();

       //Ждем когда исчезнет элемент
     wait.until(ExpectedConditions.invisibilityOfElementLocated(buttonCookie));

    }




}
