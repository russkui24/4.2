package ru.all_books;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PageAllBooks {
   private WebDriver driver;
   private WebDriverWait wait;
   private final String baseUrl;
   protected final String url = "/pages/news.html";
   private JavascriptExecutor jsExecutor;

   public PageAllBooks(WebDriver driver,WebDriverWait wait,String BaseUrl){
       this.driver = driver;
       this.wait = wait;
       this.baseUrl = BaseUrl;
       jsExecutor = (JavascriptExecutor)driver;
   }

    /**
     * Метод возвращает урл
     * @return
     */
   public String getExpectedUrl(){
       return baseUrl + url;
   }


    /**
     * Локатор для раздела Книги
     */
    private final By book = By.cssSelector("[id='genres']");

    /**
     * Локатор для подраздел "Новинки"
     */
    private final By allBookNews = By.xpath("//ul[@class='dropdown-content']/a[2]");


    /**
     * Метод, в котором просто открываем страничку
     */
    public void openUrl(){
        driver.get(baseUrl);
   }

    /**
     * Метод возвращает готовый объект по Actions
     * @return
     */
   public Actions actions(){
        return new Actions(driver);
   }

    /**
     * Метод, в котором будем наводить курсор на нужный раздел и из него будем кликать на нужный список
     */
    public void goToAllBooks(){
        actions()
                .moveToElement(driver.findElement(book))
                .perform();
      wait.until(ExpectedConditions.visibilityOfElementLocated(allBookNews));
           actions()
          .moveToElement(driver.findElement(allBookNews))
                   .click()
                    .perform();
    }

    /**
     * Наводим на выпадающий элемент
     */
    public void goToAllBooksTwo(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(book));
        actions()
                .moveToElement(driver.findElement(book))
                .perform();
    }

    /**
     * Сбор тесктов всех пунктов подменю
     * @return
     */
    public List<String> getSubMenuText(){
        //Ожидаем список элементов
        List<WebElement> getText = wait.until(ExpectedConditions.visibilityOfAllElements(driver.findElements(By.xpath("//ul[@class='dropdown-content']/a"))));
        //Создаем пустой массив, в него будем складывать найденный текст
        List<String> texts = new ArrayList<>();
        for (WebElement item : getText){
            texts.add(item.getText());
        }
        return texts;
    }

}
