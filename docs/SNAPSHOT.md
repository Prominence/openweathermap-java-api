### Implemented features:
* Current weather data
### Maven coordinates:

```xml
<dependency>
  <groupId>com.github.prominence</groupId>
  <artifactId>openweathermap-api</artifactId>
  <version>2.0-SNAPSHOT</version>
</dependency>
```

### Gradle coordinates:

```groovy
compile('com.github.prominence:openweathermap-api:2.0-SNAPSHOT')
```

### How to use:

Firstly, you need to create the instance of `OpenWeatherMapManager` class:
```java
OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient(API_TOKEN);
```
where `API_TOKEN` is your token([you can get it here](https://home.openweathermap.org/api_keys)) as `String`.

#### Current weather data
Current weather request chain structure:
```java
openWeatherClient
    .currentWeather()
    .<single|multiple location(s)>()
    .<location definition>
    .<customize unitSystem/language/accuracy (1)>
    ...
    .<customize unitSystem/language/accuracy (N)>
    .<request/requestAsync>()
    .as(Java|JSON|XML|HTML)();
```

Available methods for location selection for **single** result request:
* `byCityName(String cityName)`
* `byCityName(String cityName, String countryCode)`
* `byCityId(long cityId)`
* `byCoordinate(Coordinate coordinate)`
* `byZipCodeAndCountry(String zipCode, String countryCode)`

Available methods for location selection for **multiple** result request:
* `byRectangle(CoordinateRectangle rectangle, int zoom)`
* `byRectangle(CoordinateRectangle rectangle, int zoom, boolean useServerClustering)`
* `byCitiesInCycle(Coordinate point, int citiesCount)`
* `byCitiesInCycle(Coordinate point, int citiesCount, boolean useServerClustering)`

Single location request examples:
```java
final Weather weather = openWeatherClient
                .currentWeather()
                .single()
                .byCoordinate(new Coordinate(5, 5))
                .accuracy(Accuracy.ACCURATE)
                .language(Language.ROMANIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJava();

final CompletableFuture<String> weatherXmlFuture = openWeatherClient
                .currentWeather()
                .single()
                .byZipCodeAndCountry("220015", "by")
                .language(Language.RUSSIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieveAsync()
                .asXML();
```

Multiple locations request examples:
```java
final String weatherListJson = openWeatherClient
                .currentWeather()
                .multiple()
                .byRectangle(new CoordinateRectangle(12, 32, 15, 37), 10, true)
                .accuracy(Accuracy.ACCURATE)
                .language(Language.ROMANIAN)
                .unitSystem(UnitSystem.METRIC)
                .retrieve()
                .asJSON();

final CompletableFuture<List<Weather>> weatherListFuture = openWeatherClient
                .currentWeather()
                .multiple()
                .byCitiesInCycle(new Coordinate(55.5, 37.5), 10, true)
                .unitSystem(UnitSystem.IMPERIAL)
                .retrieveAsync()
                .asJava();
```

`Weather`'s useful public methods(setters are not listed):

| Method                    | Description                                                                                                                         |
|---------------------------|-------------------------------------------------------------------------------------------------------------------------------------|
| `getWeatherState()`       | Returns weather identifier. Example: `Clouds`, `Clear`.                                                                             |
| `getWeatherDescription()` | Returns weather description. Example: `clear sky`.                                                                                  |
| `getWeatherIconUrl()`     | Returns weather icon url.                                                                                                           |
| `getRequestedOn()`        | Returns `LocalDateTime` instance which represents date when request was made.                                                       |
| `getTemperature()`        | Returns `Temperature` instance with temperature and max/min values.                                                                 |
| `getPressure()`           | Returns `Pressure` instance that contains information about atmosphericPressure and(not always) atmosphericPressure on ground/sea level.                  |
| `getHumidity()`           | Returns `Humidity` instance that contains information about humidity.                                                               |
| `getWind()`               | Returns `Wind` instance that contains *humidity* percentage information.                                                            |
| `getRain()`               | Returns `Rain` instance that contains information about rain level for the last 1 and 3 hours.                                      |
| `getSnow()`               | Returns `Snow` instance that contains information about snow level for the last 1 and 3 hours.                                      |
| `getClouds()`             | Returns `Clouds` instance that contains *cloudiness* percentage information.                                                        |
| `getLocation()`           | Returns `Location` instance that contains location information: coordinate, name and etc.                                           |

`toString()` output example:
```
Location: Minsk(BY), Weather: слегка облачно, 20.0 ℃, 1019.0 hPa, Clouds: 40%
```

### Constants and options

#### Accuracy
| Constant           | Description      |
|--------------------|------------------|
| Accuracy.LIKE      | Close result.    |
| Accuracy.ACCURATE  | Accurate result. |

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
| UnitSystem.METRIC    | Celsius, meter/sec, hPa, mm(rain, snow).       |
| UnitSystem.IMPERIAL  | Fahrenheit, miles/hour, hPa, mm(rain, snow).   |
| UnitSystem.STANDARD  | Kelvin, meter/sec, hPa, mm(rain, snow)         |

### Dependencies
* com.fasterxml.jackson.core:jackson-databind:2.9.9
* org.slf4j:slf4j-api:1.7.26 (*compile*)
* junit:junit:4.12 (*test*)