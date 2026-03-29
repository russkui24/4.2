package ru.testAlert;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    WebDriver driver;
    WebDriverWait wait;
    private final String baseUrl;
    private JavascriptExecutor jsExecutor;
    public String initialWindows;
    private String email = "russkui24@yndex.ru";
    private String newEmail = "petux123";

    public MainPage(WebDriver driver, WebDriverWait wait, String baseUrl){
        this.driver = driver;
        this.wait = wait;
        this.baseUrl = baseUrl;
        jsExecutor = (JavascriptExecutor)driver;
    }

    /**
     * Локатор
     */
private  By inputEmail = By.xpath("//input[@class='email']");

private  By sendEmail = By.xpath("//button[@id='write-to-me']");

private  By linkTextEmail = By.id("anotherEmail");

    /**
     * Вызываем страничку
     */
    public void openUrl(){
        driver.get(baseUrl);
    }

    /**
     * Переключаемся на фрейм
     */

    public void switchFrame(){
        driver.switchTo().frame(driver.findElement(By.tagName("iframe")));
    }

    /**
     * Кликаем на поле, вводим данные в поле
     */
    public void sendInputEmail(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(inputEmail));
        driver.findElement(inputEmail).click();
        driver.findElement(inputEmail).sendKeys(email);
    }
    /**
     * Кликаем на кнопку отправить на почту
     */

    public void sendButtonEmail(){
        driver.findElement(sendEmail).click();
    }

    /**
     * Кликаем на линк 'Отправить на почту'
     */

    public void linkClick(){
        driver.findElement(linkTextEmail).click();
    }

    /**
     * Действия с алертом первый раз
     */

    public void switchAlert(){
        //Ждем появления Алерта
        wait.until(ExpectedConditions.alertIsPresent());
        //Создаем переменную, в которой будем указывать переключение на алерт
       Alert alert = driver.switchTo().alert();
       //В самом алерте вводим текст
       alert.sendKeys(newEmail);
       //В алерте соглашаемся
       alert.accept();
    }

    /**
     * Действия с алертом второй раз
     * @return
     */
    public String switchAlertDouble(){
        //Ждем появления Алерта
        wait.until(ExpectedConditions.alertIsPresent());
        //Создаем переменную, в которой будем указывать переключение на алерт
        Alert alertNew = driver.switchTo().alert();
        //Создаем переменную, в которой будем хранить фактический полученный текст из алерта
       String actualText = alertNew.getText();
       //В алерте соглашаемся
       alertNew.accept();
        //Возвращаем полученный текст из Алерта
       return actualText;
    }

    /**
     * Метод, возвразаем текст об измененной почте
     * @return
     */
    public String getExpectedEmailText(){
        //Возвращаем текст + новую почту из переменной
        return "E-mail успешно изменён на " + newEmail;
    }

}
