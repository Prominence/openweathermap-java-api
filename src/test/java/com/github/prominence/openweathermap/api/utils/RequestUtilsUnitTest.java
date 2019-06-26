package com.github.prominence.openweathermap.api.utils;

import com.github.prominence.openweathermap.api.exception.NoDataFoundException;
import org.junit.Test;

public class RequestUtilsUnitTest {

    @Test(expected = IllegalArgumentException.class)
    public void whenPassInvalidUrl_thenThrowAnException() {
        RequestUtils.getResponse("wrongUrl");
    }

    @Test(expected = NoDataFoundException.class)
    public void whenPassUrlToNonExistingPage_thenThrowAnException() {
        RequestUtils.getResponse("https://openweathermap.org/somePage");
    }
}
