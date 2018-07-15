# OpenWeatherMap Java API
Java API for OpenWeatherMap services.

### Implemented features:
* Current weather data
* 5 day / 3 hour forecast

### Will be implemented later:
* 16 day / daily forecast
* UV Index(beta)
* Air pollution(beta)

### Maven coordinates:

_Not available yet._

### How to use:

Firstly, you need to create the instance of `OpenWeatherMapManager` class:
```java
OpenWeatherMapManager openWeatherManager = new OpenWeatherMapManager(API_TOKEN);
```
where `API_TOKEN` is your token([you can get it here](https://home.openweathermap.org/api_keys)) as `String`.

Currently available methods:
* `getWeatherRequester()`
* `getForecastRequester()`

#### Current weather data
First step is retrieving `WeatherRequester` instance:
```java
OpenWeatherMapManager openWeatherManager = new OpenWeatherMapManager(API_TOKEN);
WeatherRequester weatherRequester = openWeatherManager.getWeatherRequester();
```
after you are able to set preferable options(via chain methods) and execute appropriate request:
```
WeatherResponse weatherResponse = weatherRequester
    .setLanguage(Language.ENGLISH)
    .setUnitSystem(Unit.METRIC_SYSTEM)
    .setAccuracy(Accuracy.ACCURATE)
    .getByCityName("Minsk");
```
*Language*, *UnitSystem* and *Accuracy* settings will be described below.

Available requests:
* `getByCityId(String cityId)`
* `getByCityName(String cityName)`
* `getByCoordinates(double latitude, double longitude)`
* `getByCoordinates(Coordinates coordinates)`
* `getByZIPCode(String zipCode, String countryCode)`

`WeatherResponse`'s useful public methods(setters are not listed):

| Method                    | Description                                                                                                                                                     |
|---------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `getCityId()`             | Returns city ID. Example: `625144` for Minsk.                                                                                                                   |   
| `getCityName()`           | Returns city name. Example: `Minsk`.                                                                                                                            |
| `getCoordinates()`        | Returns `Coordinates` instance that contains *latitude* and *longitude* information.                                                                            |
| `getWeather()`            | Returns `Weather` instance with the only `getDescription` useful method.                                                                                        |
| `getBase()`               | Returns `String` with some internal information. Example: `cmc stations` - from official documentation.                                                           | 
| `getWeatherInfo()`        | Returns `WeatherResponse.WeatherInfo` instance that contains information about temperature, pressure and humidity.                                              |
| `getWind()`               | Returns `Wind` instance that contains information about speed and degree.                                                                                       |
| `getClouds()`             | Returns `Clouds` instance that contains *cloudiness* percentage information.                                                                                    |
| `getRain()`               | Returns `Rain` instance that contains information about rain volume for the last 3 hours.                                                                       |
| `getSnow()`               | Returns `Snow` instance that contains information about snow volume for the last 3 hours.                                                                       |
| `getDataCalculationTime()`| Returns `long` value that represents data calculation timestamp.                                                                                                |
| `getWeatherSystemInfo()`  | Returns `WeatherResponse.WeatherSystemInfo` instance that contains internal information. There is also an information about country, sunrise and sunset times.  |
| `getResponseCode()`       | Returns OpenWeatherMap response code. Internal information.                                                                                                     |
| `getCountry()`            | An alias for `getWeatherSystemInfo().getCountry()`.                                                                                                             |
| `getWeatherDescription()` | An alias for `getWeather.get(0).getDescription()`.                                                                                                              |
| `getDataCalculationDate()`| Returns data calculation time in `Date` representation.                                                                                                         |
| `getTemperature()`        | An alias for `getWeatherInfo().getTemperature()`.                                                                                                               |
| `getTemperatureUnit()`    | An alias for `getWeatherInfo().getTemperatureUnit()`.                                                                                                           |
| `getPressure()`           | An alias for `getWeatherInfo().getPressure()`.                                                                                                                  |
| `getPressureUnit()`       | An alias for `getWeatherInfo().getPressureUnit()`.                                                                                                              |
| `getHumidityPercentage()` | An alias for `getWeatherInfo().getHumidity()`.                                                                                                                  |
| `toString()`              | Returns pretty string for the whole available weather information.                                                                                              |

`toString()` output example:
```
City: Minsk(625144). Coordinates: latitude=53.9, longitude=27.56
Country: BY
Sunrise: Sun Jul 15 04:58:27 MSK 2018
Sunset: Sun Jul 15 21:32:19 MSK 2018
Weather: light intensity shower rain
Temperature: 17.0 ℃. Minimum temparature: 17.0 ℃. Maximum temperature: 17.0 ℃
Humidity: 93%
Pressure: 1008 hPa
Wind: 2.0 meter/sec, 20 degrees
Cloudiness: 75%
Data calculation time: Mon Jul 16 00:00:00 MSK 2018
```

### License
MIT
