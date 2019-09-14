package com.udacity.gradle.builditbigger;


import android.text.TextUtils;

import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertNotNull;


@LargeTest
@RunWith(AndroidJUnit4.class)
public class JokeTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void JokeTest() throws InterruptedException, ExecutionException {

        JokeAsyncTask jokeAsyncTask = new JokeAsyncTask(null, null);
        jokeAsyncTask.execute(mActivityTestRule.getActivity());

        String joke = jokeAsyncTask.get();

        assertNotNull(joke);
    }

//    @Test
//    public void testing(){
//
//        JokeAsyncTask jokeAsyncTask = new JokeAsyncTask(null, null);
//
//        try{
//            String joke = jokeAsyncTask.get();
//            Assert.assertFalse(TextUtils.isEmpty(joke));
//        } catch (InterruptedException e) {
//            Assert.assertNull(e);
//        } catch (ExecutionException e) {
//            Assert.assertNull(e);
//        }
//    }
}
