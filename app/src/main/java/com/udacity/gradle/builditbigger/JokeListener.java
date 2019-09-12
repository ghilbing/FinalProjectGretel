package com.udacity.gradle.builditbigger;

import androidx.annotation.Nullable;
import androidx.annotation.UiThread;

public interface JokeListener {

    @UiThread
    void onStarted();

    @UiThread
    void onFinished(@Nullable String result);
}
