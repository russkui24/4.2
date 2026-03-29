package ru.shop.toolBar;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainPageTest extends BaseTest {

    @Test
    public void test(){
        mainPage.openUrl();

        mainPage.enableConstructor();

        mainPage.clickButtonCookie();

    }


}
