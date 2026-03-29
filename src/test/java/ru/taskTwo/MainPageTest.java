package ru.taskTwo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainPageTest extends BaseTest {

    @Test
    public void testOne(){
        //Открыли страничку
        mainPage.openUrl();
        //Скролл вниз страницы
        mainPage.actionScroll();
        //Проверяем, что кнопка действительно отобразилась
        Assertions.assertTrue(mainPage.setButton(), "Не отображается кнопка- скролла вверх на страницу");
    }
}
