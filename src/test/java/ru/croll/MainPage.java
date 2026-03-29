package ru.croll;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
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
     * Локатор для раздела Каталог
     */
    private final By buttonCatalog = By.xpath("//li[@id='menu-item-46']");
    /**
     * Локатор для подраздела Электроника
     */
    private final By buttonElectr = By.xpath("//li[@id='menu-item-47']");
    /**
     * Локатор для выбора подпунтка
     */
    private final By buttonMenu = By.xpath("//li[@id='menu-item-47']/ul/li/a[text()='Телефоны']");

    /**
     * Открываем страничку
     */
    public void openUrl(){
        driver.get(baseUrl);
    }

    /**
     * Создаем объект Actions
     */
    private Actions actions(){
        return new Actions(driver);
    }

    /**
     * Наводим на пункт Катало, из выпадающего списка кликаем на подменю Электроника
     */
    public void clickCatalog(){

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(buttonCatalog)));
        actions()
                .moveToElement(driver.findElement(buttonCatalog))
                .moveToElement(driver.findElement(buttonElectr))
                .perform();
    }

    /**
     * Параметризованный метод, где будем кликать на пункт в зависимости от названия
     * @param nameMenu
     */
   public void clickNameMenu(String nameMenu){
       String xpath = "//li[@id='menu-item-47']/ul/li/a[text()='" + nameMenu + "']";
       actions()
               .moveToElement(driver.findElement(By.xpath(xpath)))
               .click()
               .perform();
   }

    /**
     * Возвращаем загаловок страницы
     * @return
     */
   public String titleText(){
      return   driver.getTitle();
   }
}
