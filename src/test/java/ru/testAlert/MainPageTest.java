package ru.testAlert;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainPageTest extends  BaseTest{
    @Test
    public void testOne(){
        //Открыли страничку
        mainPage.openUrl();
        //Переключились на iFrame
        mainPage.switchFrame();
        //В поле Почта, ввели Email
        mainPage.sendInputEmail();
        //Кликнули на кнопку отправить
        mainPage.sendButtonEmail();
        //Кликаем на link -Указать другой Email
        mainPage.linkClick();
        //Переключаемся на первый Alert, указываем в нем почту, далее Alert жмем продолжить
        mainPage.switchAlert();
        //Переключается и Появляется второй Alert, в котором отображается успешно измененная почта
      String actualText =  mainPage.switchAlertDouble();
//Сравниваем полученный текст из Алерта
        Assertions.assertEquals(mainPage.getExpectedEmailText(),actualText,"Не корректная текстовка в аллерте после изменения почты");
    }
}
