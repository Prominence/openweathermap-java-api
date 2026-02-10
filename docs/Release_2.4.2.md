### Implemented features:
* Current weather data
* 5 day / 3-hour forecast
* One Call API
* One Call 3 API
* Air Pollution


Other:
* Request timeout settings

### Maven coordinates:

```xml
<dependency>
  <groupId>com.github.prominence</groupId>
  <artifactId>openweathermap-api</artifactId>
  <version>2.4.2</version>
</dependency>
```

### Gradle coordinates:

```groovy
compile('com.github.prominence:openweathermap-api:2.4.2')
```

### How to use:

Firstly, you need to create the instance of `OpenWeatherMapClient` class:
```java
OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient(API_TOKEN);
```
where `API_TOKEN` is your token([you can get it here](https://home.openweathermap.org/api_keys)) as `String`.

Currently, available APIs are:
* `currentWeather()`
* `forecast5Day3HourStep()`
* `oneCall()`
* `airPollution()`

Also, it is possible to set timeouts for the requests on `openWeatherClient` object:
```java
openWeatherClient.setReadTimeout(1000);
openWeatherClient.setConnectTimeout(1000);
```
Timeout settings are passed to the requesters as a copy.

Default(more or less) customization points:
```java
...
// response language
.language(Language.RUSSIAN)
...
// response units of measure
.unitSystem(UnitSystem.IMPERIAL)
...
```

Available output forms:
* `asJava()`
* `asJSON()`

Additional output forms, available for several APIs:
* `asXML()`
* `asHTML()`

_All response forms can be in **sync** and **async** variants._

#### Current weather data
Examples:
```java
final String weatherJson = openWeatherClient
        .currentWeather()
        .single()
        .byCityName("Minsk")
        .language(Language.RUSSIAN)
        .unitSystem(UnitSystem.IMPERIAL)
        .retrieve()
        .asJSON();
```

```java
final Weather weather = openWeatherClient
        .currentWeather()
        .single()
        .byCityName("Minsk")
        .language(Language.RUSSIAN)
        .unitSystem(UnitSystem.METRIC)
        .retrieve()
        .asJava();
```

```java
final List<Weather> weatherList = openWeatherClient
        .currentWeather()
        .multiple()
        .byCitiesInCycle(Coordinate.of(55.5, 37.5))
        .language(Language.GERMAN)
        .unitSystem(UnitSystem.IMPERIAL)
        .retrieve()
        .asJava();
```

```java
final CompletableFuture<String> weatherXmlFuture = openWeatherClient
        .currentWeather()
        .single()
        .byZipCodeAndCountry("220015", "by")
        .language(Language.RUSSIAN)
        .unitSystem(UnitSystem.METRIC)
        .retrieveAsync()
        .asXML();
```

You are able to set preferable options(via chain methods) and execute appropriate request.

`com.github.prominence.openweathermap.api.model.weather.Weather`'s useful public methods(setters are not listed):

| Method                    | Description                                                                                                                                                               |
|---------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `getCalculationTime()`    | Returns `LocalDateTime` object with data calculation time.                                                                                                                |
| `getWeatherState()`       | Returns `WeatherState` object with basic weather state information.                                                                                                       |
| `getTemperature()`        | Returns `Temperature` instance that contains information about temperature. Available fields: `value`, `maxTemperature`, `minTemperature`, `feelsLike` and `unit`.        |
| `getAtmosphericPressure()`| Returns `AtmosphericPressure` instance that contains information about atmospheric pressure. Available fields: `value`, `seaLevelValue`, `groundLevelValue` and `unit`.   |
| `getHumidity()`           | Returns `Humidity` instance that contains humidity percentage information.                                                                                                |
| `getWind()`               | Returns `Wind` instance that contains wind information: `speed`, `degrees`, `gust` and `unit`.                                                                            |
| `getRain()`               | Returns `Rain` instance that contains information about rain volume for the last one hour and/or the last 3 hours. Can be absent in case of no data.                      |
| `getSnow()`               | Returns `Snow` instance that contains information about snow volume for the last one hour and/or the last 3 hours. Can be absent in case of no data.                      |
| `getClouds()`             | Returns `Clouds` instance that contains information about cloudiness percentage.                                                                                          |
| `getLocation()`           | Returns `Location` object. Available fields: `id`, `name`, `countryCode`, `sunrise` and `sunset` time, `zoneOffset` and `coordinate`.                                     |
| `toString()`              | Returns informative string for the whole available weather information.                                                                                                   |

`toString()` output example:
```
Location: Minsk(BY), Weather: clear sky, -4.22 ℃, 1020.0 hPa, Clouds: 0%
```

#### 5 day / 3-hour forecast
Examples:
```java
final Forecast forecast = openWeatherClient
        .forecast5Day3HourStep()
        .byCityName("Minsk")
        .language(Language.ENGLISH)
        .unitSystem(UnitSystem.METRIC)
        .count(15)
        .retrieve()
        .asJava();
```

```java
final String forecastJson = getClient()
        .forecast5Day3HourStep()
        .byCityName("New York", "NY", "US")
        .language(Language.SPANISH)
        .unitSystem(UnitSystem.IMPERIAL)
        .count(15)
        .retrieve()
        .asJSON();
```

```java
CompletableFuture<String> forecastFuture = getClient()
        .forecast5Day3HourStep()
        .byCityId(350001514)
        .language(Language.ENGLISH)
        .unitSystem(UnitSystem.METRIC)
        .count(15)
        .retrieveAsync()
        .asXML();
```

```java
final String forecastXml = getClient()
        .forecast5Day3HourStep()
        .byZipCodeInUSA("10005")
        .language(Language.ENGLISH)
        .unitSystem(UnitSystem.METRIC)
        .retrieve()
        .asXML();
```

You are able to set preferable options(via chain methods) and execute appropriate request.

`com.github.prominence.openweathermap.api.model.forecast.free.Forecast`'s useful public methods(setters are not listed):

| Method                        | Description                                                                                                                                           |
|-------------------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------|
| `getLocation()`               | Returns `Location` object. Available fields: `id`, `name`, `countryCode`, `sunrise` and `sunset` time, `zoneOffset`, `coordinate` and `population`.   |
| `getWeatherForecasts()`       | Returns list of `WeatherForecast` objects with forecast information.                                                                                  |
| `toString()`                  | Returns informative string for the whole available forecast information.                                                                              |

`toString()` output example:
```
A forecast for Minsk with 15 timestamps.
```

`com.github.prominence.openweathermap.api.model.forecast.WeatherForecast`'s useful public methods(setters are not listed):

| Method                        | Description                                                                                                                                                               |
|-------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `getForecastTime()`           | Returns `LocalDateTime` object with weather forecast time.                                                                                                                |
| `getWeatherState()`           | Returns `WeatherState` object with basic weather state information.                                                                                                       |
| `getTemperature()`            | Returns `Temperature` instance that contains information about temperature. Available fields: `value`, `maxTemperature`, `minTemperature`, `feelsLike` and `unit`.        |
| `getAtmosphericPressure()`    | Returns `AtmosphericPressure` instance that contains information about atmospheric pressure. Available fields: `value`, `seaLevelValue`, `groundLevelValue` and `unit`.   |
| `getHumidity()`               | Returns `Humidity` instance that contains humidity percentage information.                                                                                                |
| `getWind()`                   | Returns `Wind` instance that contains wind information: `speed`, `degrees` and `unit`.                                                                                    |
| `getRain()`                   | Returns `Rain` instance that contains information about rain volume for the last 3 hours. Can be absent in case of no data.                                               |
| `getSnow()`                   | Returns `Snow` instance that contains information about snow volume for the last 3 hours. Can be absent in case of no data.                                               |
| `getClouds()`                 | Returns `Clouds` instance that contains information about cloudiness percentage.                                                                                          |
| `getForecastTimeISO()`        | Returns String with time of data forecasted, ISO, UTC.                                                                                                                    |
| `getDayTime()`                | Returns enumerations representing the part of day(day, night).                                                                                                            |
| `toString()`                  | Returns informative string for the forecast of particular timestamp.                                                                                                      |

#### One Call 3 API
Examples:
```java
        final CurrentWeatherData currentWeatherData = getClient()
        .oneCall3()
        .current()
        .byCoordinate(Coordinate.of(53.54, 27.34))
        .language(Language.ENGLISH)
        .unitSystem(UnitSystem.METRIC)
        .retrieve()
        .asJava();
```

#### One Call API (Deprecated)
Examples:
```java
final CurrentWeatherData currentWeatherData = openWeatherClient
        .oneCall()
        .current()
        .byCoordinate(Coordinate.of(53.54, 27.34))
        .language(Language.ENGLISH)
        .unitSystem(UnitSystem.METRIC)
        .retrieve()
        .asJava();
```

```java
final CurrentWeatherData currentWeatherData = openWeatherClient
        .oneCall()
        .current()
        .byCoordinate(Coordinate.of(53.54, 27.34))
        .language(Language.ENGLISH)
        .unitSystem(UnitSystem.METRIC)
        .exclude(OneCallResultOptions.CURRENT, OneCallResultOptions.MINUTELY)
        .retrieve()
        .asJava();
```

```java
final CompletableFuture<CurrentWeatherData> currentWeatherDataFuture = openWeatherClient
        .oneCall()
        .current()
        .byCoordinate(Coordinate.of(53.54, 27.34))
        .language(Language.ENGLISH)
        .unitSystem(UnitSystem.METRIC)
        .retrieveAsync()
        .asJava();
```

```java
final String responseJson = openWeatherClient
        .oneCall()
        .current()
        .byCoordinate(Coordinate.of(53.54, 27.34))
        .language(Language.ENGLISH)
        .unitSystem(UnitSystem.METRIC)
        .retrieve()
        .asJSON();
```

```java
final HistoricalWeatherData historicalWeatherData = openWeatherClient
        .oneCall()
        .historical()
        .byCoordinateAndTimestamp(Coordinate.of(60.99, 30.9), LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC))
        .language(Language.ENGLISH)
        .unitSystem(UnitSystem.METRIC)
        .retrieve()
        .asJava();
```

```java
final String responseJson = openWeatherClient
        .oneCall()
        .historical()
        .byCoordinateAndTimestamp(Coordinate.of(60.99, 30.9), LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC))
        .language(Language.ENGLISH)
        .unitSystem(UnitSystem.METRIC)
        .retrieve()
        .asJSON();
```

```java
final CompletableFuture<HistoricalWeatherData> historicalWeatherDataFuture = openWeatherClient
        .oneCall()
        .historical()
        .byCoordinateAndTimestamp(Coordinate.of(60.99, 30.9), LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC))
        .language(Language.ENGLISH)
        .unitSystem(UnitSystem.METRIC)
        .retrieveAsync()
        .asJava();
```

```java
final CompletableFuture<String> responseJsonFuture = openWeatherClient
        .oneCall()
        .historical()
        .byCoordinateAndTimestamp(Coordinate.of(60.99, 30.9), LocalDateTime.now().minusDays(5).toEpochSecond(ZoneOffset.UTC))
        .language(Language.ENGLISH)
        .unitSystem(UnitSystem.METRIC)
        .retrieveAsync()
        .asJSON();
```

You are able to set preferable options(via chain methods) and execute appropriate request.

`com.github.prominence.openweathermap.api.model.onecall.current.CurrentWeatherData`'s useful public methods(setters are not listed):

| Method                        | Description                                                                    |
|-------------------------------|--------------------------------------------------------------------------------|
| `getCoordinate()`             | Returns `Coordinate` object. Available fields: `latitude`, `longitude`.        |
| `getTimezone()`               | Returns location timezone object.                                              |
| `getTimezoneOffset()`         | Returns zone offset.                                                           |
| `getCurrent()`                | Returns `Current` object with current weather state if available.              |
| `getMinutelyList()`           | Returns list of `Minutely` objects if available.                               |
| `getHourlyList()`             | Returns list of `Houlry` objects if available.                                 |
| `getDailyList()`              | Returns list of `Daily` objects if available.                                  |
| `getAlerts()`                 | Returns list of `Alert` objects if available.                                  |

`com.github.prominence.openweathermap.api.model.onecall.Current`'s useful public methods(setters are not listed):

| Method                        | Description                                                                                     |
|-------------------------------|-------------------------------------------------------------------------------------------------|
| `getForecastTime()`           | Returns `LocalDateTime` object with weather forecast time.                                      |
| `getSunriseTime()`            | Returns `LocalDateTime` object with sunrise time.                                               |
| `getSunsetTime()`             | Returns `LocalDateTime` object with sunset time.                                                |
| `getWeatherState()`           | Returns `WeatherState` object with basic weather state information.                             |
| `getTemperature()`            | Returns `Temperature` object. Available fields: `value`, `feelsLike`, `dewPoint` and `unit`.    |
| `getAtmosphericPressure()`    | Returns `AtmosphericPressure` object. Available fields: `seaLevelValue`.                        |
| `getHumidity()`               | Returns `Humidity` object. Available fields: `value` and `unit`.                                |
| `getClouds()`                 | Returns `Clouds` object. Available fields: `value` and `unit`.                                  |
| `getUvIndex()`                | Returns UV index value.                                                                         |
| `getVisibilityInMetres()`     | Returns visibility in metres.                                                                   |
| `getWind()`                   | Returns `Wind` object. Available fields: `speed`, `degrees`, `gust` and `unit`.                 |
| `getRain()`                   | Returns `Rain` object. Available fields: `oneHourLevel` and `unit`.                             |
| `getSnow()`                   | Returns `Snow` object. Available fields: `oneHourLevel` and `unit`.                             |

`com.github.prominence.openweathermap.api.model.onecall.current.Minutely`'s useful public methods(setters are not listed):

| Method                        | Description                                                   |
|-------------------------------|---------------------------------------------------------------|
| `getForecastTime()`           | Returns `LocalDateTime` object with weather forecast time.    |
| `getPrecipitationVolume()`    | Returns precipitation volume.                                 |

`com.github.prominence.openweathermap.api.model.onecall.current.Hourly`'s useful public methods(setters are not listed):

| Method                                        | Description                                                                                       |
|-----------------------------------------------|---------------------------------------------------------------------------------------------------|
| `getForecastTime()`                           | Returns `LocalDateTime` object with weather forecast time.                                        |
| `getWeatherState()`                           | Returns `WeatherState` object with basic weather state information.                               |
| `getTemperature()`                            | Returns `Temperature` object. Available fields: `value`, `feelsLike`, `dewPoint` and `unit`.      |
| `getAtmosphericPressure()`                    | Returns `AtmosphericPressure` object. Available fields: `seaLevelValue`.                          |
| `getHumidity()`                               | Returns `Humidity` object. Available fields: `value` and `unit`.                                  |
| `getClouds()`                                 | Returns `Clouds` object. Available fields: `value` and `unit`.                                    |
| `getUvIndex()`                                | Returns UV index value.                                                                           |
| `getVisibilityInMetres()`                     | Returns visibility in metres.                                                                     |
| `getWind()`                                   | Returns `Wind` object. Available fields: `speed`, `degrees`, `gust` and `unit`.                   |
| `getProbabilityOfPrecipitation()`             | Returns probability of precipitation(not percentage).                                             |
| `getProbabilityOfPrecipitationPercentage()`   | Returns probability of precipitation percentage.                                                  |
| `getRain()`                                   | Returns `Rain` object. Available fields: `oneHourLevel` and `unit`.                               |
| `getSnow()`                                   | Returns `Snow` object. Available fields: `oneHourLevel` and `unit`.                               |

`com.github.prominence.openweathermap.api.model.onecall.current.Daily`'s useful public methods(setters are not listed):

| Method                                      | Description                                                                                       |
|---------------------------------------------|---------------------------------------------------------------------------------------------------|
| `getForecastTime()`                         | Returns `LocalDateTime` object with weather forecast time.                                        |
| `getSunriseTime()`                          | Returns `LocalDateTime` object with sunrise time.                                                 |
| `getSunsetTime()`                           | Returns `LocalDateTime` object with sunset time.                                                  |
| `getMoonriseTime()`                         | Returns `LocalDateTime` object with moonrise time.                                                |
| `getMoonsetTime()`                          | Returns `LocalDateTime` object with moonset time.                                                 |
| `getMoonPhase()`                            | Returns `MoonPhase` object with `MoonType` info and value.                                        |
| `getWeatherState()`                         | Returns `WeatherState` object with basic weather state information.                               |
| `getTemperature()`                          | Returns `DailyTemperature` object. Available fields: `value`, `feelsLike`, `dewPoint` and `unit`. |
| `getAtmosphericPressure()`                  | Returns `AtmosphericPressure` object. Available fields: `seaLevelValue`.                          |
| `getHumidity()`                             | Returns `Humidity` object. Available fields: `value` and `unit`.                                  |
| `getWind()`                                 | Returns `Wind` object. Available fields: `speed`, `degrees`, `gust` and `unit`.                   |
| `getClouds()`                               | Returns `Clouds` object. Available fields: `value` and `unit`.                                    |
| `getUvIndex()`                              | Returns UV index value.                                                                           |
| `getProbabilityOfPrecipitation()`           | Returns probability of precipitation(not percentage).                                             |
| `getProbabilityOfPrecipitationPercentage()` | Returns probability of precipitation percentage.                                                  |
| `getRain()`                                 | Returns `DailyRain` object. Available fields: `value`.                                            |
| `getSnow()`                                 | Returns `DailySnow` object. Available fields: `value`.                                            |

`com.github.prominence.openweathermap.api.model.onecall.current.Alert`'s useful public methods(setters are not listed):

| Method                       | Description                                            |
|------------------------------|--------------------------------------------------------|
| `getSenderName()`            | Returns alert sender name.                             |
| `getEventName()`             | Returns alert event name.                              |
| `getStartTime()`             | Returns `LocalDateTime` when event should start.       |
| `getEndTime()`               | Returns `LocalDateTime` when event should end.         |
| `getDescription()`           | Returns alert description.                             |

`com.github.prominence.openweathermap.api.model.onecall.historical.HistoricalWeatherData`'s useful public methods(setters are not listed):

| Method                        | Description                                                                   |
|-------------------------------|-------------------------------------------------------------------------------|
| `getCoordinate()`             | Returns `Coordinate` object. Available fields: `latitude`, `longitude`.       |
| `getTimezone()`               | Returns location timezone object.                                             |
| `getTimezoneOffset()`         | Returns zone offset.                                                          |
| `getHistoricalWeather()`      | Returns `HistoricalWeather` object with historical weather state.             |
| `getHourlyList()`             | Returns list of `HourlyHistorical` objects.                                   |

`com.github.prominence.openweathermap.api.model.onecall.historical.HistoricalWeather`'s useful public methods(setters are not listed):

| Method                        | Description                                                                                     |
|-------------------------------|-------------------------------------------------------------------------------------------------|
| `getForecastTime()`           | Returns `LocalDateTime` object with weather forecast time.                                      |
| `getSunriseTime()`            | Returns `LocalDateTime` object with sunrise time.                                               |
| `getSunsetTime()`             | Returns `LocalDateTime` object with sunset time.                                                |
| `getWeatherState()`           | Returns `WeatherState` object with basic weather state information.                             |
| `getTemperature()`            | Returns `Temperature` object. Available fields: `value`, `feelsLike`, `dewPoint` and `unit`.    |
| `getAtmosphericPressure()`    | Returns `AtmosphericPressure` object. Available fields: `seaLevelValue`.                        |
| `getHumidity()`               | Returns `Humidity` object. Available fields: `value` and `unit`.                                |
| `getClouds()`                 | Returns `Clouds` object. Available fields: `value` and `unit`.                                  |
| `getUvIndex()`                | Returns UV index value.                                                                         |
| `getVisibilityInMetres()`     | Returns visibility in metres.                                                                   |
| `getWind()`                   | Returns `Wind` object. Available fields: `speed`, `degrees`, `gust` and `unit`.                 |
| `getRain()`                   | Returns `Rain` object. Available fields: `oneHourLevel` and `unit`.                             |
| `getSnow()`                   | Returns `Snow` object. Available fields: `oneHourLevel` and `unit`.                             |

`com.github.prominence.openweathermap.api.model.onecall.historical.HourlyHistorical`'s useful public methods(setters are not listed):

| Method                            | Description                                                                                       |
|-----------------------------------|---------------------------------------------------------------------------------------------------|
| `getForecastTime()`               | Returns `LocalDateTime` object with weather forecast time.                                        |
| `getWeatherState()`               | Returns `WeatherState` object with basic weather state information.                               |
| `getTemperature()`                | Returns `Temperature` object. Available fields: `value`, `feelsLike`, `dewPoint` and `unit`.      |
| `getAtmosphericPressure()`        | Returns `AtmosphericPressure` object. Available fields: `seaLevelValue`.                          |
| `getHumidity()`                   | Returns `Humidity` object. Available fields: `value` and `unit`.                                  |
| `getClouds()`                     | Returns `Clouds` object. Available fields: `value` and `unit`.                                    |
| `getVisibilityInMetres()`         | Returns visibility in metres.                                                                     |
| `getWind()`                       | Returns `Wind` object. Available fields: `speed`, `degrees`, `gust` and `unit`.                   |
| `getRain()`                       | Returns `Rain` object. Available fields: `oneHourLevel` and `unit`.                               |
| `getSnow()`                       | Returns `Snow` object. Available fields: `oneHourLevel` and `unit`.                               |

#### Air Pollution API
Examples:
```java
final AirPollutionDetails airPollutionDetails = openWeatherClient
        .airPollution()
        .current()
        .byCoordinate(Coordinate.of(53.54, 27.34))
        .retrieve()
        .asJava();
```

```java
final AirPollutionDetails airPollutionDetails = openWeatherClient
        .airPollution()
        .historical()
        .byCoordinateAndPeriod(Coordinate.of(53.54, 27.34), 1606223802, 1606482999)
        .retrieve()
        .asJava();
```

`com.github.prominence.openweathermap.api.model.air.pollution.AirPollutionDetails`'s useful public methods(setters are not listed):

| Method                        | Description                                                               |
|-------------------------------|---------------------------------------------------------------------------|
| `getCoordinate()`             | Returns `Coordinate` object. Available fields: `latitude`, `longitude`.   |
| `getAirPollutionRecords()`    | Returns list of `AirPollutionRecord` objects.                             |

`com.github.prominence.openweathermap.api.model.air.pollution.AirPollutionRecord`'s useful public methods(setters are not listed):

| Method                        | Description                                                               |
|-------------------------------|---------------------------------------------------------------------------|
| `getForecastTime()`           | Returns `LocalDateTime` object with air pollution forecast time.          |
| `getAirQualityIndex()`        | Returns `AirQualityIndex` object.                                         |
| `getCO()`                     | Returns carbon monoxide concentration value in μg/m^3.s.                  |
| `getCarbonMonoxide()`         | An alias for `getCO()` method.                                            |
| `getNO()`                     | Returns nitrogen monoxide concentration value in μg/m^3.                  |
| `getNitrogenMonoxide()`       | An alias for `getNO()` method.                                            |
| `getNO2()`                    | Returns nitrogen dioxide concentration value in μg/m^3.                   |
| `getNitrogenDioxide()`        | An alias for `getNO2()` method.                                           |
| `getO3()`                     | Returns ozone concentration value in μg/m^3.                              |
| `getOzone()`                  | An alias for `getO3()` method.                                            |
| `getSO2()`                    | Returns sulphur dioxide concentration value in μg/m^3.                    |
| `getSulphurDioxide()`         | An alias for `getSO2()` method.                                           |
| `getPM2_5()`                  | Returns fine particles matter concentration value in μg/m^3.              |
| `getFineParticlesMatter()`    | An alias for `getPM2_5()` method.                                         |
| `getPM10()`                   | Returns coarse particulate matter concentration value in μg/m^3.          |
| `getCoarseParticulateMatter()`| An alias for `getPM10()` method.                                          |
| `getNH3()`                    | Returns ammonia concentration value in μg/m^3.                            |
| `getAmmonia()`                | An alias for `getNH3()` method.                                           |

### Constants and options

#### Language
| Constant                     | Description                   |
|------------------------------|-------------------------------|
| Language.AFRIKAANS           | Afrikaans language.           |
| Language.ALBANIAN            | ALBANIAN language.            |
| Language.ARABIC              | Arabic language.              |
| Language.AZERBAIJANI         | Azerbaijani language.         |
| Language.BULGARIAN           | Bulgarian language.           |
| Language.CATALAN             | Catalan language.             |
| Language.CZECH               | Czech language.               |
| Language.DANISH              | Danish language.              |
| Language.GERMAN              | German language.              |
| Language.GREEK               | Greek language.               |
| Language.ENGLISH             | English language.             |
| Language.BASQUE              | Basque language.              |
| Language.PERSIAN             | Persian (Farsi) language.     |
| Language.FINNISH             | Finnish language.             |
| Language.FRENCH              | French language.              |
| Language.GALICIAN            | Galician language.            |
| Language.HEBREW              | Hebrew language.              |
| Language.HINDI               | Hindi language.               |
| Language.CROATIAN            | Croatian language.            |
| Language.HUNGARIAN           | Hungarian language.           |
| Language.INDONESIAN          | Indonesian language.          |
| Language.ITALIAN             | Italian language.             |
| Language.JAPANESE            | Japanese language.            |
| Language.KOREAN              | Korean language.              |
| Language.LATVIAN             | Latvian language.             |
| Language.LITHUANIAN          | Lithuanian language.          |
| Language.MACEDONIAN          | Macedonian language.          |
| Language.NORWEGIAN           | Norwegian language.           |
| Language.DUTCH               | Dutch language.               |
| Language.POLISH              | Polish language.              |
| Language.PORTUGUESE          | Portuguese language.          |
| Language.PORTUGUES_BRAZIL    | Português Brasil language.    |
| Language.ROMANIAN            | Romanian language.            |
| Language.RUSSIAN             | Russian language.             |
| Language.SWEDISH             | Swedish language.             |
| Language.SLOVAK              | Slovak language.              |
| Language.SLOVENIAN           | Slovenian language.           |
| Language.SPANISH             | Spanish language.             |
| Language.SERBIAN             | Serbian language.             |
| Language.THAI                | Thai language.                |
| Language.TURKISH             | Turkish language.             |
| Language.UKRAINIAN           | Ukrainian language.           |
| Language.VIETNAMESE          | Vietnamese language.          |
| Language.CHINESE_SIMPLIFIED  | Chinese Simplified language.  |
| Language.CHINESE_TRADITIONAL | Chinese Traditional language. |
| Language.ZULU                | Zulu language.                |

#### Unit
| Constant             | Description                                    |
|----------------------|------------------------------------------------|
| Unit.METRIC_SYSTEM   | Celsius, meter/sec, hPa, mm(rain, snow).       |
| Unit.IMPERIAL_SYSTEM | Fahrenheit, miles/hour, hPa, mm(rain, snow).   |
| Unit.STANDARD_SYSTEM | Kelvin, meter/sec, hPa, mm(rain, snow).        |

### Dependencies
* com.fasterxml.jackson.core:jackson-databind:2.13.4.2
* org.slf4j:slf4j-api:1.7.36 (*compile*)
* org.junit.jupiter:junit-jupiter-engine:5.8.2 (*test*)
* org.junit.platform:junit-platform-runner:1.8.2 (*test*)