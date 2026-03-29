package ru.taskFree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainPageTest extends BaseTest{
    @Test
    public void testOne() {
        //Открыли страничку
        mainPage.openUrl();
        //Добавляем записи до 10 штук
        mainPage.addTask();
        //Наводим и удаляем самую верхнюю запись
        mainPage.actionsDeleteTask();
        //Скролим до самого низа "все записи"
        mainPage.scrollElementList();

        //Проверили, что первые две записи которые были изначально - по прежнему отображаются
        String firstBefore = mainPage.listElement();   //метод вернул текст "План на следующий месяц"
        String secondBefore = mainPage.listElementTwo(); //метод вернул текст "Путешествие на восток"

        //Проверяем, что те две записи никуда не делись
        boolean firstStillThere = mainPage.isVisible(firstBefore);
        boolean secondStillThere = mainPage.isVisible(secondBefore);

        //Проверяем через Assertions
        Assertions.assertTrue(firstStillThere, "Первая запись не пропала!");
        Assertions.assertTrue(secondStillThere, "Вторая запись  не пропала!");

    }
}
