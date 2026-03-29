package ru.shop.toolBar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainPageTest extends BaseTest {

    @Test
    public void test(){

        mainPage.openUrl();

        mainPage.enableConstructor();

        mainPage.clickButtonCookie();

        mainPage.toolBar();

        Assertions.assertEquals("left: 47.751%;", mainPage.sliderBar(),"получили нужный процент ширины");

    }


}
