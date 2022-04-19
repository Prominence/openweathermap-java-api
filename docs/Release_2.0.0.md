### Implemented features:
* Current weather data
* 5 day / 3-hour forecast

### Maven coordinates:

```xml
<dependency>
  <groupId>com.github.prominence</groupId>
  <artifactId>openweathermap-api</artifactId>
  <version>2.0.0</version>
</dependency>
```

### Gradle coordinates:

```groovy
implementation 'com.github.prominence:openweathermap-api:2.0.0'
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
| `getState()`              | Returns short weather description. Example: `Clear`.                                                                                                                      |
| `getDescription()`        | Returns weather description. Example: `clear sky`.                                                                                                                        |
| `getWeatherIconUrl()`     | Returns a link to weather icon hosted on https://openweathermap.org website.                                                                                              |
| `getCalculatedOn()`       | Returns `LocalDateTime` object with data calculation time.                                                                                                                |
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

`com.github.prominence.openweathermap.api.request.forecast.free.Forecast`'s useful public methods(setters are not listed):

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
| `getState()`                  | Returns short weather description. Example: `Clear`.                                                                                                                      |
| `getDescription()`            | Returns weather description. Example: `clear sky`.                                                                                                                        |
| `getWeatherIconUrl()`         | Returns a link to weather icon hosted on https://openweathermap.org website.                                                                                              |
| `getForecastTime()`           | Returns `LocalDateTime` object with weather forecast time.                                                                                                                |
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

### Constants and options

#### Language
| Constant                          | Description                   |
|-----------------------------------|-------------------------------|
| Language.ARABIC                   | Arabic language.              |
| Language.BULGARIAN                | Bulgarian language.           |
| Language.CATALAN                  | Catalan language.             |
| Language.CZECH                    | Czech language.               |
| Language.GERMAN                   | German language.              |
| Language.GREEK                    | Greek language.               |
| Language.ENGLISH                  | English language.             |
| Language.PERSIAN                  | Persian (Farsi) language.     |
| Language.FINNISH                  | Finnish language.             |
| Language.FRENCH                   | French language.              |
| Language.GALICIAN                 | Galician language.            |
| Language.CROATIAN                 | Croatian language.            |
| Language.HUNGARIAN                | Hungarian language.           |
| Language.ITALIAN                  | Italian language.             |
| Language.JAPANESE                 | Japanese language.            |
| Language.KOREAN                   | Korean language.              |
| Language.LATVIAN                  | Latvian language.             |
| Language.LITHUANIAN               | Lithuanian language.          |
| Language.MACEDONIAN               | Macedonian language.          |
| Language.DUTCH                    | Dutch language.               |
| Language.POLISH                   | Polish language.              |
| Language.PORTUGUESE               | Portuguese language.          |
| Language.ROMANIAN                 | Romanian language.            |
| Language.RUSSIAN                  | Russian language.             |
| Language.SWEDISH                  | Swedish language.             |
| Language.SLOVAK                   | Slovak language.              |
| Language.SLOVENIAN                | Slovenian language.           |
| Language.SPANISH                  | Spanish language.             |
| Language.TURKISH                  | Turkish language.             |
| Language.UKRANIAN                 | Ukrainian language.           |
| Language.VIETNAMESE               | Vietnamese language.          |
| Language.CHINESE_SIMPLIFIED       | Chinese Simplified language.  |
| Language.CHINESE_TRADITIONAL      | Chinese Traditional language. |

#### Unit
| Constant             | Description                                    |
|----------------------|------------------------------------------------|
| Unit.METRIC_SYSTEM   | Celsius, meter/sec, hPa, mm(rain, snow).       |
| Unit.IMPERIAL_SYSTEM | Fahrenheit, miles/hour, hPa, mm(rain, snow).   |
| Unit.STANDARD_SYSTEM | Kelvin, meter/sec, hPa, mm(rain, snow).        |

### Dependencies
* com.fasterxml.jackson.core:jackson-databind:2.12.2
* org.slf4j:slf4j-api:1.7.30 (*compile*)
* junit:junit:4.13.1 (*test*)