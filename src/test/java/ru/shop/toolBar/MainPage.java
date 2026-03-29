package ru.shop.toolBar;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
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

    /**
     * Метод для скроллинга
     * @param element
     */
    private void scrollToElement(WebElement element){
        jsExecutor.executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }

    /**
     * Скроллимся до элемента
     */
    public void enableConstructor(){
        WebElement element = driver.findElement(By.xpath("//section[contains(@class,'product-calculator-constructor')]"));
        scrollToElement(element);
    }


    /**
     * Метод для работы с элементом по Куки
     */
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

    /**
     * Задаем значение для x и y
     */
    int xoffSet = 300;
    int yoffSet = 0;

    /**
     * Локатор для слайдера
     */
    private final By sliderButton = By.xpath("//div[contains(@class,'el-tooltip__trigger')]");

    /**
     * Создаем объект для actions
     * @return
     */
    public Actions actions(){
      return new Actions(driver);
    }

    /**
     * Метод для работы с Actions
     */
    public void toolBar(){
        actions()
                .dragAndDropBy(driver.findElement(sliderButton), xoffSet, yoffSet)
                .perform();
        waitForLoader();

    }

    /**
     * Метод для получения ширины слайдера
     * @return
     */
    public String sliderBar(){
        WebElement element = driver.findElement(By.xpath("//div[@class='el-slider__button-wrapper']"));
        return  element.getAttribute("style");
    }




}
