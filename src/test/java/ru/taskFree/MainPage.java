package ru.taskFree;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
     * Локатор, кнопка удаления записи
     */
    private By buttonDelete = By.xpath("//div[@class='vb-content']/div[1]/div/button[2]/*");

    public void openUrl(){
        driver.get(baseUrl);
    }

    /**
     * Запускаем цикл, который 10 раз добавит запись
     */
    public void addTask(){
        for (int i = 1; i <= 10; i++){
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

    private Actions actions(){
        return new Actions(driver);
    }

    /**
     * Локатор для наведения курсора на элемент,
     * наведение на иконку Удалить в этой же записи
     */
    public void actionsDeleteTask(){
        actions()
                .moveToElement(driver.findElement(By.xpath("//div[@class='vb-content']/div[1]")))
                        .perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(buttonDelete)));
        actions()
                .moveToElement(driver.findElement(buttonDelete))
                .click()
                .perform();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(buttonDelete)));
    }

    /**
     * Прокручиваем скролл внутри контейнера с помощьwю js
     */
    public void scrollElementList(){
        WebElement list = driver.findElement(By.cssSelector(".vb-content"));
        jsExecutor.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight;", list);
    }

    /**
     * Возвращаем найденый элемент из списка с массивом
     * @return
     */
    public String listElement(){
        List<WebElement> element = driver.findElements(By.xpath("//div[@class='vb-content']/div/div/p[@class='articlePreview__title']"));
     return  element.get(10).getText();
    }

    /**
     * Возвращаем второй найденый элемент из списка с массивом
     * @return
     */
    public String listElementTwo(){
        List<WebElement> element = driver.findElements(By.xpath("//div[@class='vb-content']/div/div/p[@class='articlePreview__title']"));
        return  element.get(11).getText();
    }

    /**
     * Проверяет, есть ли на странице запись с таким текстом и видна ли она
     * @param text
     * @return
     */
    public boolean isVisible(String text) {
        List<WebElement> notes = driver.findElements(By.xpath("//div[@class='vb-content']/div/div/p[@class='articlePreview__title']"));
        for (WebElement note : notes) {  // перебираем все записи по очереди
            if (note.getText().equals(text) && note.isDisplayed()) {  // если текст совпал и запись видна
                return true;  // значит нашли
            }
        }
        return false;  // если ничего не нашли
    }
    }



