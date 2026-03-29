package ru.shop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPageTest extends BaseTest{

    @ParameterizedTest
    @CsvSource({
            "Уверенный выбор",
            "Денежный поток"
    })
    public void test(String nameProduct){

        mainPage.openUrl();

        mainPage.bannerCookie();

        mainPage.clickElement();

        Assertions.assertEquals(nameProduct, mainPage.nameProduct(nameProduct),"Не отобразилася заголовок продукта");
       // Assertions.assertEquals(nameDescription, mainPage.nameDescription(nameDescription),"Не отображается описание продукта один");


    }

    @Test
    public void testTwo(){

        mainPage.openUrl();

        mainPage.bannerCookie();

        mainPage.clickElement();
Assertions.assertAll(
        () -> Assertions.assertEquals(6, mainPage.listElementSize(),"не четкое колличество"),
        () -> Assertions.assertTrue(mainPage.listElement(), "фак")
);

        
    }

    @Test
    public void testThree(){
        mainPage.openUrlDouble();
        mainPage.clickCalendar();
        mainPage.dayCalendar();
        Assertions.assertEquals(mainPage.newDate() ,mainPage.getDay(),"Дата выбрана норм в календаре");
    }

    @Test
    public void ttttttttetData(){

        mainPage.openUrlDouble();

     WebElement element =  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@class='el-input__inner' and @id='el-id-1024-32']/following-sibling::span")));
    element.click();

    while (true){
      WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='el-popper is-pure is-light el-tooltip el-picker__popper' and @id='el-id-1024-16']//button[@class='el-picker-panel__icon-btn arrow-left']")));
      element1
              .click();

    String currentYear =
            driver.findElement(By.xpath("//div[@class='el-popper is-pure is-light el-tooltip el-picker__popper' and @id='el-id-1024-16']//span[@class='el-date-picker__header-label'][1]")).getText();

    String currentMonth =
            driver.findElement(By.xpath("//div[@class='el-popper is-pure is-light el-tooltip el-picker__popper' and @id='el-id-1024-16']//span[@class='el-date-picker__header-label'][2]")).getText();

    if(currentYear.equals("1994") && currentMonth.equals("Сентябрь")){
    break;
    }
}

    driver.findElement(By.xpath("//div[@class='el-popper is-pure is-light el-tooltip el-picker__popper' and @id='el-id-1024-16']//td[contains(.,'12')]"))
            .click();


Assertions.assertEquals("12.09.1994", mainPage.dateTest(), "не корректная дата");

    }

    @Test
    public void WhileCal(){
        mainPage.openUrlDouble();

        mainPage.clickCale();

        mainPage.whileCale();

        mainPage.numberCalCkick();
        Assertions.assertEquals("12.09.1994", mainPage.dateTest(), "не корректная дата");

    }
}


