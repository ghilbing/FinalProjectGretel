package com.udacity.gradle.builditbigger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class MyTest {


    @Test
    public void jokeValidator() {
        assertTrue("joke".equals("joke"));

    }

}
