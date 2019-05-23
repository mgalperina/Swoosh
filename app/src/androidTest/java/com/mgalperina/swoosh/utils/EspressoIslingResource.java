package com.mgalperina.swoosh.utils;

import androidx.test.espresso.IdlingResource;

public class EspressoIslingResource {

    private static final String RESOURCE = "FinishIR";

    private static SimpleCountingIdlingResource mCountingIdlingResource =
            new SimpleCountingIdlingResource(RESOURCE);

    public static void increment() {
        mCountingIdlingResource.increment();
    }

    public static void decrement() {
        mCountingIdlingResource.decrement();
    }

    public static IdlingResource getInstance() {
        return mCountingIdlingResource;
    }
}

