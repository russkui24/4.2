package ru.croll;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class MainPageTest extends BaseTest{

    @ParameterizedTest
    @CsvSource({
            "Телефоны, Телефоны — Skillbox ",
            "Планшеты, Планшеты — Skillbox ",
            "Телевизоры, Телевизоры — Skillbox ",
            "Фото/видео, Фото/видео — Skillbox ",
            "Часы, Часы — Skillbox ",
    })
    public void testOne(String nameMenu, String title){
        //Открыли страничку
        mainPage.openUrl();
        //Произвели действия с менюшклй
        mainPage.clickCatalog();
        //Кликнули нужный пункт-меню
        mainPage.clickNameMenu(nameMenu);
        //Сравниваем полученное значение
        Assertions.assertEquals(title, mainPage.titleText(),"Отобразился не тот раздел");
    }
}
