package com.github.prominence.openweathermap.api.utils;

import com.github.prominence.openweathermap.api.exception.DataNotFoundException;
import org.junit.Test;

public class RequestUtilsUnitTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenPassInvalidUrl_thenThrowAnException() {
        RequestUtils.getResponse("wrongUrl");
    }

    @Test(expected = DataNotFoundException.class)
    public void whenPassUrlToNonExistingPage_thenThrowAnException() {
        RequestUtils.getResponse("https://openweathermap.org/somePage");
    }
}
