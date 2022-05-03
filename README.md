# OpenWeatherMap Java API [![Build Status][ci-shield]][ci-link] [![codecov][codecov-shield]][codecov-link] [![FOSSA Status][FOSSA-shield]][FOSSA-link]
Java API for OpenWeatherMap services.

### Implemented features:
Free: 
* Current weather data
* 5 day / 3-hour forecast
* One Call API
* Air pollution
* Geocoding API

Paid:
* Hourly Forecast 4 days
* Daily Forecast 16 days
* Climatic Forecast 30 days
* Solar Radiation API
* Road Risk API

Other:
* Request timeout settings

### Will be implemented later:

Free:
* Weather Stations
* Weather Triggers
  
Paid:
* Bulk Downloading
* Historical Weather API
* Historical Weather API 40 years by timestamp
* Historical Weather API 40 years full archive
* History Bulk
* History Forecast Bulk
* Statistical Weather Data API
* Accumulated Parameters
* Historical Weather Data by State for all ZIP codes, USA

### Maven coordinates:

```xml
<dependency>
    <groupId>com.github.prominence</groupId>
    <artifactId>openweathermap-api</artifactId>
    <version>3.0.0-SNAPSHOT</version>
</dependency>
```

```xml
<repositories>
    ...
    <!-- Repository for snapshot versions -->
    <repository>
        <id>oss.sonatype.org-snapshot</id>
        <url>https://oss.sonatype.org/content/repositories/snapshots</url>
        <releases>
            <enabled>false</enabled>
        </releases>
        <snapshots>
            <enabled>true</enabled>
        </snapshots>
    </repository>
    ...
</repositories>
```

### Gradle coordinates:

```groovy
implementation 'com.github.prominence:openweathermap-api:3.0.0-SNAPSHOT'
```

```groovy
repositories {
    ...
    // Repository for snapshot versions
    maven {
        url "https://oss.sonatype.org/content/repositories/snapshots"
        mavenContent {
            snapshotsOnly()
        }
    }
    ...
}
```

### Documentation
* [OpenWeatherMap Java API - 1.0](docs/Release_1.0.md)
* [OpenWeatherMap Java API - 1.1](docs/Release_1.1.md)
* [OpenWeatherMap Java API - 1.2](docs/Release_1.2.md)
* [OpenWeatherMap Java API - 2.0.0](docs/Release_2.0.0.md)
* [OpenWeatherMap Java API - 2.0.1](docs/Release_2.0.1.md)
* [OpenWeatherMap Java API - 2.1.0](docs/Release_2.1.0.md)
* [OpenWeatherMap Java API - 2.1.1](docs/Release_2.1.1.md)
* [OpenWeatherMap Java API - 2.2.0](docs/Release_2.2.0.md)
* [OpenWeatherMap Java API - 2.3.0](docs/Release_2.3.0.md)
* [OpenWeatherMap Java API - SNAPSHOT](docs/SNAPSHOT.md)

### License
MIT

[ci-shield]: https://api.cirrus-ci.com/github/Prominence/openweathermap-java-api.svg?branch=dev
[ci-link]: https://api.cirrus-ci.com/github/Prominence/openweathermap-java-api


[codecov-shield]: https://codecov.io/gh/Prominence/openweathermap-java-api/branch/dev/graph/badge.svg
[codecov-link]: https://codecov.io/gh/Prominence/openweathermap-java-api

[FOSSA-shield]: https://app.fossa.com/api/projects/git%2Bgithub.com%2FProminence%2Fopenweathermap-java-api.svg?type=shield
[FOSSA-link]: https://app.fossa.com/projects/git%2Bgithub.com%2FProminence%2Fopenweathermap-java-api?ref=badge_shield
