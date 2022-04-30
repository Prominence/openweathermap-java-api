package com.github.prominence.openweathermap.api.request.forecast.climatic;

import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.request.RequestSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClimaticForecastRequestCustomizer {
    private static final Logger logger = LoggerFactory.getLogger(ClimaticForecastRequestCustomizer.class);

    private final RequestSettings requestSettings;

    public ClimaticForecastRequestCustomizer(RequestSettings requestSettings) {
        this.requestSettings = requestSettings;
    }

    public ClimaticForecastRequestCustomizer language(Language language) {
        requestSettings.setLanguage(language);
        return this;
    }

    public ClimaticForecastRequestCustomizer unitSystem(UnitSystem unitSystem) {
        requestSettings.setUnitSystem(unitSystem);
        return this;
    }

    public ClimaticForecastRequestCustomizer numberOfDays(int numberOfDays) {
        int days = numberOfDays;
        if (days > 30) {
            logger.warn("Cannot use more than 30 days for this API request. Please, specify 30 or less days. !!! Requesting information for 30 days...");
            days = 30;
        }
        requestSettings.putRequestParameter("cnt", Integer.toString(days));
        return this;
    }

    public ClimaticForecastRequestTerminator retrieve() {
        return new ClimaticForecastRequestTerminator(requestSettings);
    }

    public ClimaticForecastAsyncRequestTerminator retrieveAsync() {
        return new ClimaticForecastAsyncRequestTerminator(requestSettings);
    }
}
