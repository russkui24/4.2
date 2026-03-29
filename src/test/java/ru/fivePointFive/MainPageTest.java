package ru.fivePointFive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MainPageTest extends BaseTest{

    @Test
    public void testOne(){
//Переход на страницу
        mainPage.openUrl();
//Переключились на фрейм
        mainPage.goSwitchIFrame();
//Еще раз переключились на фрейм
        mainPage.goSwitchIFrame();
//Кликаем на гиперссылку
        mainPage.clickButtonSk();
//Ожидаем, что в браузере открыто ровно два окошка
        wait.until(d -> driver.getWindowHandles().size()== 2);
//Переключаемся в новое окошко браузера
        mainPage.switchToFirstNewWindows();
//Ждем полной подзагрузки страницы
        wait.until(ExpectedConditions.urlToBe("https://skillbox.ru/"));
//Закрываем окошко на котором находимся
        driver.close();
//Тут же переключаемся на первоначальное окошко
        mainPage.switchToWindows(initialWindows);
        //Проверяем, сколько окошек открыто на текущий момент в браузере
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, mainPage.getAllWindows().size(),"Неверное количество окошек")
        );
    }

    @Test
    public void testTwo(){
        //Открываем страницу
        mainPage.openUrl();
//Переключаемся на фрейм
        mainPage.goSwitchIFrame();
//Кликаем пол попугайчика
        mainPage.clickParrot();
//В поле вбиваем почту
        mainPage.inputParrot("russkui24@yandex.ru");
        //Кликаем на кнопку Подобрать имя
        mainPage.buttonName();
//Ожидаем элемент по header, выводим проверку
        mainPage.headerSite();
//Возвращаем фокус драйвера к основному контенту
        mainPage.switchDefaultCont();
//Проверяем отображение хедара
        Assertions.assertTrue(mainPage.headerSite(), "Не отобразился хедер страницы");
    }

    @Test
    public void testThree(){
        //Открываем страничку
        mainPage.openUrl();
//Переключаемся на фрейм
        mainPage.goSwitchIFrame();
//Кликаем на кнопку Подобрать Имя
        mainPage.buttonName();
//Переключаемся на еще один фрейм
        mainPage.goSwitchIFrame();
//Кликаем на гиперссылку СкиллБокс
        mainPage.clickButtonSk();
//Ждем появление и отображение окошек
        wait.until(d -> driver.getWindowHandles().size()== 2);
//Переключаемся на новое окошко
        mainPage.switchToFirstNewWindows();
//Ждем полную подзагрузку страницы
        wait.until(ExpectedConditions.urlToBe("https://skillbox.ru/"));
//Переключаемся обратно на основное окно браузера
        mainPage.switchToWindows(initialWindows);
//Переключаемся на фрейм - в котором присутсвует элемент по Ошибке "Введите email"
        mainPage.goSwitchIFrame();
        //Проверяем отображение об ошибке
        Assertions.assertEquals("Введите email", mainPage.errorEmailMessage(),"Не отобразилось сообщение об ошибке");
    }
}
