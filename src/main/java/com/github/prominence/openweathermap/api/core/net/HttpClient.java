package com.github.prominence.openweathermap.api.core.net;

import com.github.prominence.openweathermap.api.conf.TimeoutSettings;

public interface HttpClient {
    void setTimeoutSettings(TimeoutSettings timeoutSettings);

    String executeGetRequest(String url);
    String executePostRequest(String url, String body);
}
