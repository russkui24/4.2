package ru.FivePointTwo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.stream.Collectors;

public class MainPageTest extends BaseTest{

    @Test
    public void testOne(){

        mainPage.openUrl();

        mainPage.addTask();
//Запоминаем идентификатор текущего окна
        //Кликаем на иконку Вк
        mainPage.clickButtonVK();
//Ждем, что количество открытых окон станет ровненько двум
        wait.until(d -> driver.getWindowHandles().size() == 2);
//Получаем множество идентификаторов всех открытых окон

// Из всех окон оставить только те, которые не равны исходному (т.е. новое окно)
        switchToFirstNewWindows();

        wait.until(ExpectedConditions.urlToBe("https://vk.com/skillbox_education"));

        Assertions.assertAll(
                //Количество окон должно быть равно двум
                () -> Assertions.assertEquals(2, getAllWindows().size(), "Отобразилось два окна"),
                //Текущий URL (уже в новом окне) должен совпадать с адресом страницы ВК
                () -> Assertions.assertEquals(mainPage.urlVK(), driver.getCurrentUrl(), "не корректно отображается урл")
        );
    }

    /**
     * Тестовый метод, где мы пытаемся кликнуть на иконку в ВК перейти на навую вкладку в ВК, закрыть его
     * и вернуться обратно на основную вкладку
     */
    @Test
    public void testTwo(){
        mainPage.openUrl();

        mainPage.addTask();
        //Кликаем на иконку Вк
        mainPage.clickButtonVK();
//Ждем, что количество открытых окон станет ровненько двум
        wait.until(d -> driver.getWindowHandles().size() == 2);
//Получаем множество идентификаторов всех открытых окон

        switchToFirstNewWindows();

        wait.until(ExpectedConditions.urlToBe("https://vk.com/skillbox_education"));

        driver.close();

        switchToWindows(initialWindows);

        Assertions.assertAll(
                //Количество окон должно быть равно одному
                () -> Assertions.assertEquals(1, getAllWindows().size(), "После закрытия окна с ВК кол-во окон осталось неверным"),
                //Текущий URL уже должен совпадать с первоначальной вкладкой
                () -> Assertions.assertEquals(mainPage.getUrl(), driver.getCurrentUrl(), "после закрытия окна с ВК- изменился адрес основной страницы")
        );
    }
}
