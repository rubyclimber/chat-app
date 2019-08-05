package com.ohgnarly.chatapp.controller;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HomeControllerTest {
    private HomeController homeController;

    @Before
    public void setUp() throws Exception {
        homeController = new HomeController();
    }

    @Test
    public void testIndex() {
        //act
        String response = homeController.index();

        //assert
        assertEquals("/resources/public/index.html", response);
    }
}