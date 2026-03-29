package ru.fivePointFive;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;
import java.util.stream.Collectors;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl;
    protected final String url = "https://vk.com/skillbox_education";
    public JavascriptExecutor jsExecutor;
    public String initialWindows;

    public MainPage(WebDriver driver, WebDriverWait wait, String BaseUrl){
        this.driver = driver;
        this.wait = wait;
        this.baseUrl = BaseUrl;
        jsExecutor = (JavascriptExecutor)driver;
    }

    /**
     * Локатор iframe
     */
    private By iframeOne = By.tagName("iframe");

    /**
     * Локатор - Иконка ВК
     */
    private By buttonSK = By.xpath("//a[contains(@class,'footer__contactItem descriptionText')][2]");

    /**
     * Локатор - радиобаттон пол попугая
     */
    private By nameParrot = By.cssSelector("#girl");

    /**
     * Локатор - поле Инпут email на какой адрес отправить варианты имен
     */
    private By emailName = By.xpath("//input[@name='email']");

    /**
     * Локатор - кнопочка Подобрать Имя
     */
    private By buttonName = By.xpath("//button[@id='sendMe']");

    public void openUrl(){
        driver.get(baseUrl);
    }

    /**
     * Переключаемся на фрейм в DOM модели
     */
    public void goSwitchIFrame(){
        driver.switchTo().frame(driver.findElement(iframeOne));
    }

    /**
     * Кликаем на гипер-ссылку Skillbox в футере страницы
     */
    public void clickButtonSk(){
        driver.findElement(buttonSK).click();
    }

    /**
     * Метод, в котором ожидаем радиобатон- напротив него выбираем пол
     */
    public void clickParrot(){
        WebElement radio = wait.until(ExpectedConditions.visibilityOfElementLocated(nameParrot)
        );
        radio.click();
    }

    /**
     * Поле для ввода email
     * @param email
     */
    public void inputParrot(String email){
        driver.findElement(emailName).sendKeys(email);
    }

    /**
     * кликаем на инпут "Подобрать имя"
     */
    public void buttonName(){
        driver.findElement(buttonName).click();

    }

    /**
     * Ждем элемент Хедер страницы
     * @return
     */
    public boolean headerSite(){
      WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".header")));
      return element.isDisplayed();
    }

    /**
     * Переключаемся и возвращаемся на основную страницу браузера
     * возвращает фокус WebDriver к основному документу после работы с iframe (встроенными фреймами)
     */
    public void switchDefaultCont(){
        driver.switchTo().defaultContent();
    }

    /**
     * Вернем сообщение об ошибке инпут "Введите Email"
     * @return
     */
   public String errorEmailMessage(){
        WebElement element = driver.findElement(By.cssSelector(".form-error"));
        return  element.getText();
   }

    public Set<String> getAllWindows(){
        //возвращает множество дескрипторов (или ID) открытых окон (и вкладок) во время текущей сессии
        return driver.getWindowHandles();
    }

    public void switchToFirstNewWindows(){
        // Из всех окон оставить только те, которые не равны исходному (т.е. новое окно)
        Set<String> otherWindows = getAllWindows().stream().filter(w -> !w.equals(initialWindows)).collect(Collectors.toSet());
        // Переключиться на новое окно (берём первый элемент из множества, так как оно содержит ровно одно окно)
        driver.switchTo().window(otherWindows.stream().findFirst().get());
    }

    public void switchToWindows(String winId) {
        driver.switchTo().window(winId);


    }
}
