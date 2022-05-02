package com.github.prominence.openweathermap.api.core.net;

import com.github.prominence.openweathermap.api.conf.TimeoutSettings;

public class MockHttpClient implements HttpClient {
    private String responseOutput;

    @Override
    public void setTimeoutSettings(TimeoutSettings timeoutSettings) {

    }

    @Override
    public String executeGetRequest(String url) {
        System.out.println("Executing request to " + url);

        return responseOutput;
    }

    @Override
    public String executePostRequest(String url, String body) {
        System.out.println("Executing request to " + url);
        System.out.println("Request body: " + body);

        return responseOutput;
    }

    public void setResponseOutput(String responseOutput) {
        this.responseOutput = responseOutput;
    }
}
