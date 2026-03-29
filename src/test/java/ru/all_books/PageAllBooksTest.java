package ru.all_books;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class PageAllBooksTest extends BaseTest{

    @Test
    public void testOne(){
        //Открываем страничку
        pageAllBooks.openUrl();
        //Кликаем на раздел Книги, из подраздела кликаем на подраздел Новинки
      pageAllBooks.goToAllBooks();
        //Сравниваем полученную страницу
       Assertions.assertEquals(pageAllBooks.getExpectedUrl(), driver.getCurrentUrl(),"Не та страница");
    }

    @Test
    public void testTwo(){
        //Переходим на страницу
        pageAllBooks.openUrl();
        //Наводим на выпадющий элемент списка
        pageAllBooks.goToAllBooksTwo();
        //Список значений, который будем сравнивать из выпадющего списка
        List<String> expectedText = Arrays.asList("Все книги", "Новинки", "Бестселлеры", "Художественная литература",
                "Книги для детей", "Психология", "Фантастика", "Детективы");
        //Сравниваем полученный список
        Assertions.assertEquals(expectedText,pageAllBooks.getSubMenuText(),"не сравнилост");


    }




}
