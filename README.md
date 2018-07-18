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
Weather weatherResponse = weatherRequester
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

`Weather`'s useful public methods(setters are not listed):

| Method                    | Description                                                                                                                                                     |
|---------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `getCityId()`             | Returns city ID. Example: `625144` for Minsk.                                                                                                                   |   
| `getCityName()`           | Returns city name. Example: `Minsk`.                                                                                                                            |
| `getCoordinates()`        | Returns `Coordinates` instance that contains *latitude* and *longitude* information.                                                                            |
| `getWeatherStates()`      | Returns list of `WeatherState` instances with the only `getDescription` useful method.                                                                          |
| `getBase()`               | Returns `String` with some internal information. Example: `cmc stations` - from official documentation.                                                         |
| `getWeatherInfo()`        | Returns `Weather.WeatherInfo` instance that contains information about temperature, pressure and humidity.                                                      |
| `getWind()`               | Returns `Wind` instance that contains information about speed and degree.                                                                                       |
| `getClouds()`             | Returns `Clouds` instance that contains *cloudiness* percentage information.                                                                                    |
| `getRain()`               | Returns `Rain` instance that contains information about rain volume for the last 3 hours.                                                                       |
| `getSnow()`               | Returns `Snow` instance that contains information about snow volume for the last 3 hours.                                                                       |
| `getDataCalculationTime()`| Returns `long` value that represents data calculation timestamp.                                                                                                |
| `getWeatherSystemInfo()`  | Returns `Weather.WeatherSystemInfo` instance that contains internal information. There is also an information about country, sunrise and sunset time.           |
| `getResponseCode()`       | Returns OpenWeatherMap response code. Internal information.                                                                                                     |
| `getCountry()`            | An alias for `getWeatherSystemInfo().getCountry()`.                                                                                                             |
| `getWeatherDescription()` | An alias for `getWeatherStates.get(0).getDescription()`.                                                                                                        |
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

#### 5 day / 3 hour forecast
First step is retrieving `HourlyForecastRequester` instance:
```java
OpenWeatherMapManager openWeatherManager = new OpenWeatherMapManager(API_TOKEN);
ForecastRequester forecastRequester = openWeatherManager.getForecastRequester();
```
after you are able to set preferable options(via chain methods) and execute appropriate request:
```
HourlyForecast forecastResponse = forecastRequester
    .setLanguage(Language.ENGLISH)
    .setUnitSystem(Unit.METRIC_SYSTEM)
    .setAccuracy(Accuracy.ACCURATE)
    .getByCityName("Pruzhany");
```
*Language*, *UnitSystem* and *Accuracy* settings will be described below.

Available requests:
* `getByCityId(String cityId)`
* `getByCityName(String cityName)`
* `getByCoordinates(double latitude, double longitude)`
* `getByCoordinates(Coordinates coordinates)`
* `getByZIPCode(String zipCode, String countryCode)`

`HourlyForecast`'s useful public methods(setters are not listed):

| Method                      | Description                                                                                |
|-----------------------------|--------------------------------------------------------------------------------------------|
| `getCityId()`               | Returns city ID. Example: `625144` for Minsk.                                              |
| `getCityName()`             | Returns city name. Example: `Minsk`.                                                       |
| `getCoordinates()`          | Returns `Coordinates` instance that contains *latitude* and *longitude* information.       |
| `getCityInfo()`             | Returns `HourlyForecast.CityInfo` instance that contains information about city.           |
| `getResponseCode()`         | Returns OpenWeatherMap response code. Internal information.                                |
| `getCountry()`              | An alias for `getCityInfo().getCountry()`.                                                 |
| `getForecasts()`            | Returns `List<HourlyForecast.Forecast>` collection with all forecast information.          |
| `getAverageTemperature()`   | Returns average temperature from forecasts.                                                |
| `getMinimumTemperature()`   | Returns minimum temperature from forecasts.                                                |
| `getMaximumTemperature()`   | Returns maximum temperature from forecasts.                                                |
| `getByMinimumTemperature()` | Returns `HourlyForecast.Forecast` for the time where temperature is minimal.               |
| `getByMaximumTemperature()` | Returns `HourlyForecast.Forecast` for the time where temperature is maximal.               |
| `getAveragePressure()`      | Returns average pressure from forecasts.                                                   |
| `getMinimumPressure()`      | Returns minimum pressure from forecasts.                                                   |
| `getMaximumPressure()`      | Returns maximum pressure from forecasts.                                                   |
| `getByMinimumPressure()`    | Returns `HourlyForecast.Forecast` for the time where pressure is minimal.                  |
| `getByMaximumPressure()`    | Returns `HourlyForecast.Forecast` for the time where pressure is maximal.                  |
| `toString()`                | Returns pretty string for the whole available forecast information.                        |

`toString()` output example:
```
City: Pruzhany(622997). Coordinates: latitude=52.5582, longitude=24.4567
Country: BY
Forecasts:
    Time: Tue Jul 17 00:00:00 MSK 2018. Weather: light rain. Temperature: 16.24 ℃. Minimum temperature: 16.24 ℃. Maximum temperature: 17.36 ℃. Pressure: 997.38 hPa. Sea-level pressure: 1018.59 hPa. Ground-level pressure: 997.38 hPa. Humidity: 99%. Cloudiness: 80%. Wind: 2.85 meter/sec, 324 degrees. Rain(last 3 hrs): 0 mm
    Time: Tue Jul 17 03:00:00 MSK 2018. Weather: moderate rain. Temperature: 16.0 ℃. Minimum temperature: 16.0 ℃. Maximum temperature: 16.83 ℃. Pressure: 996.88 hPa. Sea-level pressure: 1017.86 hPa. Ground-level pressure: 996.88 hPa. Humidity: 99%. Cloudiness: 80%. Wind: 1.86 meter/sec, 349 degrees. Rain(last 3 hrs): 3 mm
    Time: Tue Jul 17 06:00:00 MSK 2018. Weather: light rain. Temperature: 15.76 ℃. Minimum temperature: 15.76 ℃. Maximum temperature: 16.31 ℃. Pressure: 996.7 hPa. Sea-level pressure: 1017.72 hPa. Ground-level pressure: 996.7 hPa. Humidity: 94%. Cloudiness: 76%. Wind: 1.62 meter/sec, 113 degrees. Rain(last 3 hrs): 0 mm
    Time: Tue Jul 17 09:00:00 MSK 2018. Weather: light rain. Temperature: 18.23 ℃. Minimum temperature: 18.23 ℃. Maximum temperature: 18.51 ℃. Pressure: 997.17 hPa. Sea-level pressure: 1018.18 hPa. Ground-level pressure: 997.17 hPa. Humidity: 100%. Cloudiness: 76%. Wind: 2.11 meter/sec, 107 degrees. Rain(last 3 hrs): 0 mm
    Time: Tue Jul 17 12:00:00 MSK 2018. Weather: light rain. Temperature: 21.0 ℃. Minimum temperature: 21.0 ℃. Maximum temperature: 21.0 ℃. Pressure: 997.6 hPa. Sea-level pressure: 1018.5 hPa. Ground-level pressure: 997.6 hPa. Humidity: 100%. Cloudiness: 68%. Wind: 2.51 meter/sec, 82 degrees. Rain(last 3 hrs): 0 mm
    Time: Tue Jul 17 15:00:00 MSK 2018. Weather: light rain. Temperature: 21.78 ℃. Minimum temperature: 21.78 ℃. Maximum temperature: 21.78 ℃. Pressure: 997.73 hPa. Sea-level pressure: 1018.66 hPa. Ground-level pressure: 997.73 hPa. Humidity: 92%. Cloudiness: 88%. Wind: 4.05 meter/sec, 78 degrees. Rain(last 3 hrs): 0 mm
    Time: Tue Jul 17 18:00:00 MSK 2018. Weather: light rain. Temperature: 22.9 ℃. Minimum temperature: 22.9 ℃. Maximum temperature: 22.9 ℃. Pressure: 997.66 hPa. Sea-level pressure: 1018.55 hPa. Ground-level pressure: 997.66 hPa. Humidity: 93%. Cloudiness: 68%. Wind: 3.06 meter/sec, 67 degrees. Rain(last 3 hrs): 0 mm
    Time: Tue Jul 17 21:00:00 MSK 2018. Weather: light rain. Temperature: 23.04 ℃. Minimum temperature: 23.04 ℃. Maximum temperature: 23.04 ℃. Pressure: 996.89 hPa. Sea-level pressure: 1017.99 hPa. Ground-level pressure: 996.89 hPa. Humidity: 83%. Cloudiness: 88%. Wind: 3.17 meter/sec, 16 degrees. Rain(last 3 hrs): 0 mm
    Time: Wed Jul 18 00:00:00 MSK 2018. Weather: moderate rain. Temperature: 18.5 ℃. Minimum temperature: 18.5 ℃. Maximum temperature: 18.5 ℃. Pressure: 997.33 hPa. Sea-level pressure: 1018.34 hPa. Ground-level pressure: 997.33 hPa. Humidity: 97%. Cloudiness: 44%. Wind: 3.56 meter/sec, 31 degrees. Rain(last 3 hrs): 7 mm
    Time: Wed Jul 18 03:00:00 MSK 2018. Weather: few clouds. Temperature: 18.57 ℃. Minimum temperature: 18.57 ℃. Maximum temperature: 18.57 ℃. Pressure: 996.91 hPa. Sea-level pressure: 1017.87 hPa. Ground-level pressure: 996.91 hPa. Humidity: 95%. Cloudiness: 24%. Wind: 5.26 meter/sec, 44 degrees. Rain(last 3 hrs): 0 mm
    Time: Wed Jul 18 06:00:00 MSK 2018. Weather: few clouds. Temperature: 18.94 ℃. Minimum temperature: 18.94 ℃. Maximum temperature: 18.94 ℃. Pressure: 997.07 hPa. Sea-level pressure: 1018.06 hPa. Ground-level pressure: 997.07 hPa. Humidity: 95%. Cloudiness: 20%. Wind: 4.8 meter/sec, 45 degrees. Rain(last 3 hrs): 0 mm
    Time: Wed Jul 18 09:00:00 MSK 2018. Weather: light rain. Temperature: 20.6 ℃. Minimum temperature: 20.6 ℃. Maximum temperature: 20.6 ℃. Pressure: 997.8 hPa. Sea-level pressure: 1018.66 hPa. Ground-level pressure: 997.8 hPa. Humidity: 97%. Cloudiness: 48%. Wind: 5.56 meter/sec, 54 degrees. Rain(last 3 hrs): 0 mm
    Time: Wed Jul 18 12:00:00 MSK 2018. Weather: scattered clouds. Temperature: 23.87 ℃. Minimum temperature: 23.87 ℃. Maximum temperature: 23.87 ℃. Pressure: 998.06 hPa. Sea-level pressure: 1019.05 hPa. Ground-level pressure: 998.06 hPa. Humidity: 88%. Cloudiness: 32%. Wind: 5.86 meter/sec, 52 degrees. Rain(last 3 hrs): 0 mm
    Time: Wed Jul 18 15:00:00 MSK 2018. Weather: scattered clouds. Temperature: 24.67 ℃. Minimum temperature: 24.67 ℃. Maximum temperature: 24.67 ℃. Pressure: 998.51 hPa. Sea-level pressure: 1019.33 hPa. Ground-level pressure: 998.51 hPa. Humidity: 84%. Cloudiness: 36%. Wind: 5.63 meter/sec, 51 degrees. Rain(last 3 hrs): 0 mm
    Time: Wed Jul 18 18:00:00 MSK 2018. Weather: scattered clouds. Temperature: 25.15 ℃. Minimum temperature: 25.15 ℃. Maximum temperature: 25.15 ℃. Pressure: 998.79 hPa. Sea-level pressure: 1019.64 hPa. Ground-level pressure: 998.79 hPa. Humidity: 78%. Cloudiness: 44%. Wind: 5.47 meter/sec, 38 degrees. Rain(last 3 hrs): 0 mm
    Time: Wed Jul 18 21:00:00 MSK 2018. Weather: scattered clouds. Temperature: 23.23 ℃. Minimum temperature: 23.23 ℃. Maximum temperature: 23.23 ℃. Pressure: 999.08 hPa. Sea-level pressure: 1020.04 hPa. Ground-level pressure: 999.08 hPa. Humidity: 75%. Cloudiness: 48%. Wind: 4.62 meter/sec, 25 degrees. Rain(last 3 hrs): 0 mm
    Time: Thu Jul 19 00:00:00 MSK 2018. Weather: scattered clouds. Temperature: 20.79 ℃. Minimum temperature: 20.79 ℃. Maximum temperature: 20.79 ℃. Pressure: 999.67 hPa. Sea-level pressure: 1020.68 hPa. Ground-level pressure: 999.67 hPa. Humidity: 76%. Cloudiness: 48%. Wind: 4.29 meter/sec, 13 degrees. Rain(last 3 hrs): 0 mm
    Time: Thu Jul 19 03:00:00 MSK 2018. Weather: scattered clouds. Temperature: 19.45 ℃. Minimum temperature: 19.45 ℃. Maximum temperature: 19.45 ℃. Pressure: 999.95 hPa. Sea-level pressure: 1021.02 hPa. Ground-level pressure: 999.95 hPa. Humidity: 80%. Cloudiness: 48%. Wind: 4.22 meter/sec, 17 degrees. Rain(last 3 hrs): 0 mm
    Time: Thu Jul 19 06:00:00 MSK 2018. Weather: light rain. Temperature: 18.9 ℃. Minimum temperature: 18.9 ℃. Maximum temperature: 18.9 ℃. Pressure: 1000.6 hPa. Sea-level pressure: 1021.62 hPa. Ground-level pressure: 1000.6 hPa. Humidity: 83%. Cloudiness: 92%. Wind: 4.43 meter/sec, 10 degrees. Rain(last 3 hrs): 0 mm
    Time: Thu Jul 19 09:00:00 MSK 2018. Weather: light rain. Temperature: 21.37 ℃. Minimum temperature: 21.37 ℃. Maximum temperature: 21.37 ℃. Pressure: 1000.95 hPa. Sea-level pressure: 1022.01 hPa. Ground-level pressure: 1000.95 hPa. Humidity: 87%. Cloudiness: 0%. Wind: 4.36 meter/sec, 6 degrees. Rain(last 3 hrs): 0 mm
    Time: Thu Jul 19 12:00:00 MSK 2018. Weather: clear sky. Temperature: 23.92 ℃. Minimum temperature: 23.92 ℃. Maximum temperature: 23.92 ℃. Pressure: 1001.5 hPa. Sea-level pressure: 1022.43 hPa. Ground-level pressure: 1001.5 hPa. Humidity: 77%. Cloudiness: 0%. Wind: 5.66 meter/sec, 12 degrees. Rain(last 3 hrs): 0 mm
    Time: Thu Jul 19 15:00:00 MSK 2018. Weather: broken clouds. Temperature: 23.7 ℃. Minimum temperature: 23.7 ℃. Maximum temperature: 23.7 ℃. Pressure: 1001.75 hPa. Sea-level pressure: 1022.72 hPa. Ground-level pressure: 1001.75 hPa. Humidity: 72%. Cloudiness: 56%. Wind: 5.87 meter/sec, 349 degrees. Rain(last 3 hrs): 0 mm
    Time: Thu Jul 19 18:00:00 MSK 2018. Weather: broken clouds. Temperature: 23.82 ℃. Minimum temperature: 23.82 ℃. Maximum temperature: 23.82 ℃. Pressure: 1001.55 hPa. Sea-level pressure: 1022.59 hPa. Ground-level pressure: 1001.55 hPa. Humidity: 72%. Cloudiness: 68%. Wind: 5.47 meter/sec, 340 degrees. Rain(last 3 hrs): 0 mm
    Time: Thu Jul 19 21:00:00 MSK 2018. Weather: broken clouds. Temperature: 22.22 ℃. Minimum temperature: 22.22 ℃. Maximum temperature: 22.22 ℃. Pressure: 1001.82 hPa. Sea-level pressure: 1022.93 hPa. Ground-level pressure: 1001.82 hPa. Humidity: 67%. Cloudiness: 76%. Wind: 4.12 meter/sec, 333 degrees. Rain(last 3 hrs): 0 mm
    Time: Fri Jul 20 00:00:00 MSK 2018. Weather: scattered clouds. Temperature: 19.76 ℃. Minimum temperature: 19.76 ℃. Maximum temperature: 19.76 ℃. Pressure: 1001.98 hPa. Sea-level pressure: 1023.13 hPa. Ground-level pressure: 1001.98 hPa. Humidity: 76%. Cloudiness: 32%. Wind: 4.11 meter/sec, 312 degrees. Rain(last 3 hrs): 0 mm
    Time: Fri Jul 20 03:00:00 MSK 2018. Weather: clear sky. Temperature: 17.53 ℃. Minimum temperature: 17.53 ℃. Maximum temperature: 17.53 ℃. Pressure: 1001.93 hPa. Sea-level pressure: 1023.13 hPa. Ground-level pressure: 1001.93 hPa. Humidity: 87%. Cloudiness: 8%. Wind: 4.21 meter/sec, 309 degrees. Rain(last 3 hrs): 0 mm
    Time: Fri Jul 20 06:00:00 MSK 2018. Weather: scattered clouds. Temperature: 16.83 ℃. Minimum temperature: 16.83 ℃. Maximum temperature: 16.83 ℃. Pressure: 1001.79 hPa. Sea-level pressure: 1022.99 hPa. Ground-level pressure: 1001.79 hPa. Humidity: 91%. Cloudiness: 44%. Wind: 3.65 meter/sec, 312 degrees. Rain(last 3 hrs): 0 mm
    Time: Fri Jul 20 09:00:00 MSK 2018. Weather: light rain. Temperature: 19.57 ℃. Minimum temperature: 19.57 ℃. Maximum temperature: 19.57 ℃. Pressure: 1001.34 hPa. Sea-level pressure: 1022.41 hPa. Ground-level pressure: 1001.34 hPa. Humidity: 85%. Cloudiness: 8%. Wind: 4.38 meter/sec, 305 degrees. Rain(last 3 hrs): 0 mm
    Time: Fri Jul 20 12:00:00 MSK 2018. Weather: clear sky. Temperature: 23.5 ℃. Minimum temperature: 23.5 ℃. Maximum temperature: 23.5 ℃. Pressure: 1001.0 hPa. Sea-level pressure: 1021.99 hPa. Ground-level pressure: 1001.0 hPa. Humidity: 85%. Cloudiness: 8%. Wind: 5.36 meter/sec, 299 degrees. Rain(last 3 hrs): 0 mm
    Time: Fri Jul 20 15:00:00 MSK 2018. Weather: scattered clouds. Temperature: 25.14 ℃. Minimum temperature: 25.14 ℃. Maximum temperature: 25.14 ℃. Pressure: 1000.5 hPa. Sea-level pressure: 1021.51 hPa. Ground-level pressure: 1000.5 hPa. Humidity: 73%. Cloudiness: 32%. Wind: 6.72 meter/sec, 305 degrees. Rain(last 3 hrs): 0 mm
    Time: Fri Jul 20 18:00:00 MSK 2018. Weather: overcast clouds. Temperature: 23.56 ℃. Minimum temperature: 23.56 ℃. Maximum temperature: 23.56 ℃. Pressure: 1000.7 hPa. Sea-level pressure: 1021.58 hPa. Ground-level pressure: 1000.7 hPa. Humidity: 66%. Cloudiness: 88%. Wind: 6.57 meter/sec, 317 degrees. Rain(last 3 hrs): 0 mm
    Time: Fri Jul 20 21:00:00 MSK 2018. Weather: broken clouds. Temperature: 22.41 ℃. Minimum temperature: 22.41 ℃. Maximum temperature: 22.41 ℃. Pressure: 1000.64 hPa. Sea-level pressure: 1021.67 hPa. Ground-level pressure: 1000.64 hPa. Humidity: 68%. Cloudiness: 64%. Wind: 4.31 meter/sec, 326 degrees. Rain(last 3 hrs): 0 mm
    Time: Sat Jul 21 00:00:00 MSK 2018. Weather: broken clouds. Temperature: 20.92 ℃. Minimum temperature: 20.92 ℃. Maximum temperature: 20.92 ℃. Pressure: 1001.06 hPa. Sea-level pressure: 1022.14 hPa. Ground-level pressure: 1001.06 hPa. Humidity: 78%. Cloudiness: 68%. Wind: 3.42 meter/sec, 327 degrees. Rain(last 3 hrs): 0 mm
    Time: Sat Jul 21 03:00:00 MSK 2018. Weather: broken clouds. Temperature: 20.28 ℃. Minimum temperature: 20.28 ℃. Maximum temperature: 20.28 ℃. Pressure: 1001.04 hPa. Sea-level pressure: 1022.13 hPa. Ground-level pressure: 1001.04 hPa. Humidity: 78%. Cloudiness: 76%. Wind: 4.27 meter/sec, 312 degrees. Rain(last 3 hrs): 0 mm
    Time: Sat Jul 21 06:00:00 MSK 2018. Weather: scattered clouds. Temperature: 19.11 ℃. Minimum temperature: 19.11 ℃. Maximum temperature: 19.11 ℃. Pressure: 1001.13 hPa. Sea-level pressure: 1022.28 hPa. Ground-level pressure: 1001.13 hPa. Humidity: 74%. Cloudiness: 32%. Wind: 4.96 meter/sec, 308 degrees. Rain(last 3 hrs): 0 mm
    Time: Sat Jul 21 09:00:00 MSK 2018. Weather: few clouds. Temperature: 20.16 ℃. Minimum temperature: 20.16 ℃. Maximum temperature: 20.16 ℃. Pressure: 1001.43 hPa. Sea-level pressure: 1022.62 hPa. Ground-level pressure: 1001.43 hPa. Humidity: 86%. Cloudiness: 20%. Wind: 5.16 meter/sec, 308 degrees. Rain(last 3 hrs): 0 mm
    Time: Sat Jul 21 12:00:00 MSK 2018. Weather: few clouds. Temperature: 22.37 ℃. Minimum temperature: 22.37 ℃. Maximum temperature: 22.37 ℃. Pressure: 1001.53 hPa. Sea-level pressure: 1022.62 hPa. Ground-level pressure: 1001.53 hPa. Humidity: 88%. Cloudiness: 20%. Wind: 5.56 meter/sec, 307 degrees. Rain(last 3 hrs): 0 mm
    Time: Sat Jul 21 15:00:00 MSK 2018. Weather: scattered clouds. Temperature: 22.85 ℃. Minimum temperature: 22.85 ℃. Maximum temperature: 22.85 ℃. Pressure: 1001.63 hPa. Sea-level pressure: 1022.65 hPa. Ground-level pressure: 1001.63 hPa. Humidity: 81%. Cloudiness: 44%. Wind: 5.46 meter/sec, 314 degrees. Rain(last 3 hrs): 0 mm
    Time: Sat Jul 21 18:00:00 MSK 2018. Weather: scattered clouds. Temperature: 23.79 ℃. Minimum temperature: 23.79 ℃. Maximum temperature: 23.79 ℃. Pressure: 1001.53 hPa. Sea-level pressure: 1022.53 hPa. Ground-level pressure: 1001.53 hPa. Humidity: 72%. Cloudiness: 32%. Wind: 5.56 meter/sec, 313 degrees. Rain(last 3 hrs): 0 mm
    Time: Sat Jul 21 21:00:00 MSK 2018. Weather: scattered clouds. Temperature: 22.56 ℃. Minimum temperature: 22.56 ℃. Maximum temperature: 22.56 ℃. Pressure: 1001.72 hPa. Sea-level pressure: 1022.7 hPa. Ground-level pressure: 1001.72 hPa. Humidity: 66%. Cloudiness: 48%. Wind: 3.96 meter/sec, 312 degrees. Rain(last 3 hrs): 0 mm
```

`Forecast`'s useful public methods(setters are not listed):

| Method                      | Description                                                                                                      |
|-----------------------------|------------------------------------------------------------------------------------------------------------------|
| `getDataCalculationTime()`  | Returns `long` value that represents data calculation timestamp.                                                 |
| `getDataCalculationDate()`  | Returns data calculation time in `Date` representation.                                                          |
| `getWeatherInfo()`          | Returns `HourlyForecast.WeatherInfo` instance that contains information about temperature, pressure and humidity.|
| `getWeatherStates()`        | Returns list of `WeatherState` instances with the only `getDescription` useful method.                           |
| `getClouds()`               | Returns `Clouds` instance that contains *cloudiness* percentage information.                                     |
| `getWind()`                 | Returns `Wind` instance that contains information about speed and degree.                                        |
| `getSnow()`                 | Returns `Snow` instance that contains information about snow volume for the last 3 hours.                        |
| `getRain()`                 | Returns `Rain` instance that contains information about rain volume for the last 3 hours.                        |
| `getSystemInfo()`           | Returns `HourlyForecast.ForecastSystemInfo` instance with internal information.                                  |
| `getDt_txt()`               | Returns `String` value that represents data calculation time.                                                    |
| `toString()`                | Returns pretty string for the whole available weather information.                                               |


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

#### System
| Constant                      | Description           |
|-------------------------------|-----------------------|
|System.OPEN_WEATHER_API_URL    | Basic API url.        |
|System.OPEN_WEATHER_API_VERSION| Current API version.  |

#### Unit
| Constant             | Description                                    |
|----------------------|------------------------------------------------|
| Unit.METRIC_SYSTEM   | Celsius, meter/sec, hPa, mm(rain, snow).       |
| Unit.IMPERIAL_SYSTEM | Fahrenheit, miles/hour, hPa, mm(rain, snow).   |
| Unit.STANDARD_SYSTEM | Kelvin, meter/sec, hPa, mm(rain, snow)         |

### License
MIT
