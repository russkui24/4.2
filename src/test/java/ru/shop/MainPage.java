package ru.shop;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl;
    protected final String url = "https://shop.renlife.ru/products/good-choise/calculation?agNum=85110&pol_b__base_sum=13140000";
    private JavascriptExecutor jsExecutor;

    public MainPage(WebDriver driver, WebDriverWait wait, String BaseUrl){
        this.driver = driver;
        this.wait = wait;
        this.baseUrl = BaseUrl;
        jsExecutor = (JavascriptExecutor)driver;
    }
private final By buttonOne = By.xpath("//a[@class='header-navbar-v2__item header-navbar-v2__item_desktop'][1]");

    private final By buttonCookie = By.xpath("//*[@class='el-button el-button--info el-button--large cookie-block__button']");

    private final By isDisable = By.xpath("//*[@class='header-expandable-panel-products']");

    /**
     * Открываем страничку
     */

    public void openUrl(){
        driver.get(baseUrl);
        wait.until(ExpectedConditions.urlToBe(baseUrl));
    }

    /**
     * Ждем появления баннера по Куки и кликаем на него
     */
    public void bannerCookie(){
       wait.until(ExpectedConditions.visibilityOfElementLocated(buttonCookie));
       WebElement element = driver.findElement(buttonCookie);
       element.click();

    }

    /**
     * Объект Actions
     * @return
     */
    public Actions actions(){
        return new Actions(driver);
    }

    /**
     * Наводим на элемент
     */
    public void clickElement(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(buttonOne));
        actions()
                .moveToElement(driver.findElement(buttonOne))
                .perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(isDisable));
    }


    /**
     * параметризованный метод, находим элемент по Заголовку продукта и возвращаем его
     * @param name
     * @return
     */
    public String nameProduct(String name){
        By pr = By.xpath("//div[@class='header-expandable-panel-products']//p[text()='" + name + "']");
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(pr));
        return element.getText();
    }

    /**
     * Возврат списка булевых значений для видимости элементов
     * @return
     */

public int listElementSize(){
    List<WebElement> inner = driver.findElements(By.xpath("//div[@class='header-expandable-panel-products']/a/div[1]"));
return inner.size();
}
    /**
     * Возврат списка булевых значений для видимости элементов
     * @return
     */
    public boolean listElement() {
        //выберет первые div внутри каждой ссылки
        List<WebElement> inner = driver.findElements(By.xpath("//div[@class='header-expandable-panel-products']/a/div[1]"));
        wait.until(ExpectedConditions.visibilityOfAllElements(inner));
        for (WebElement innnner : inner) {
            if (!innnner.isDisplayed())
                return false;
        }
        //Либо просто убрать цикл for и условный оператор if
       return true;
    }
    /*
    * ЭКСПЕРЕМЕНТЫ И НОРМ РАБОТАЕТ
    * */

    /**
     * Получаем страничук
     */
    public void openUrlDouble(){
        driver.get(url);
        wait.until(ExpectedConditions.urlToBe(url));
    }

    /**
     * Кликаем на элемент, который открывает календарик
     */
    public void clickCalendar(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='policy_start_date']/following-sibling::span")));
        element.click();
    }

    /**
     * Кликаем на текущий день в календаре
     */
    public void dayCalendar(){
        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td[@class='available current']")));
        element1.click();
    }

    /**
     * Выводим и проверяем дату- которая подтянулась в календарик
     * @return
     */
    public String getDay(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='policy_start_date']")));
        return element.getAttribute("value");
    }

    public String newDate(){
        LocalDate date = LocalDate.now().plusDays(1); // добавляем один день
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = date.format(formatter);
        return formattedDate;

    }


    /*
    ЦИКЛИ WHILE для Календаря

     */

    /**
     * Локатор
     */
    String xpathCal = "//div[@class='el-popper is-pure is-light el-tooltip el-picker__popper' and @id='el-id-1024-16']";


    public void clickCale(){
        WebElement element =
                wait.until(ExpectedConditions.elementToBeClickable
                        (By.xpath("//input[@class='el-input__inner' and @id='el-id-1024-32']/following-sibling::span")));
        element.click();
    }

    public void whileCale(){
        while (true){
            WebElement element1 =
                    wait.until(ExpectedConditions.visibilityOfElementLocated
                            (By.xpath(xpathCal + "//button[@class='el-picker-panel__icon-btn arrow-left']")));
            element1.click();

            String currentYear =
                    driver.findElement(By.xpath(xpathCal + "//span[@class='el-date-picker__header-label'][1]")).getText();

            String currentMonth =
                    driver.findElement(By.xpath(xpathCal + "//span[@class='el-date-picker__header-label'][2]")).getText();

            if(currentYear.equals("1994") && currentMonth.equals("Сентябрь")){
                break;
            }
        }
    }

    public void numberCalCkick(){
        driver.findElement(By.xpath(xpathCal + "//td[contains(.,'12')]"))
                .click();
    }


    public String dateTest(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='birth_date__0']")));
         return element.getAttribute("value");
    }

}
