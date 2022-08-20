/*
 * Copyright (c) 2021-present Alexey Zinchenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.github.prominence.openweathermap.api.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Optional;

/**
 * Represents weather condition and related information.
 * More details <a href="https://openweathermap.org/weather-conditions#Weather-Condition-Codes-2">here</a>.
 */
public enum WeatherCondition {
    // Group 2xx: Thunderstorm

    /**
     * The thunderstorm with light rain.
     */
    THUNDERSTORM_LIGHT_RAIN(200, "Thunderstorm", "thunderstorm with light rain", "11"),
    /**
     * The thunderstorm with rain.
     */
    THUNDERSTORM_RAIN(201, "Thunderstorm", "thunderstorm with rain", "11"),
    /**
     * The thunderstorm with heavy rain.
     */
    THUNDERSTORM_HEAVY_RAIN(202, "Thunderstorm", "thunderstorm with heavy rain", "11"),
    /**
     * The light thunderstorm.
     */
    THUNDERSTORM_LIGHT(210, "Thunderstorm", "light thunderstorm", "11"),
    /**
     * The thunderstorm.
     */
    THUNDERSTORM(211, "Thunderstorm", "thunderstorm", "11"),
    /**
     * The heavy thunderstorm.
     */
    THUNDERSTORM_HEAVY(212, "Thunderstorm", "heavy thunderstorm", "11"),
    /**
     * The ragged thunderstorm.
     */
    THUNDERSTORM_RAGGED(221, "Thunderstorm", "ragged thunderstorm", "11"),
    /**
     * The thunderstorm with light drizzle.
     */
    THUNDERSTORM_LIGHT_DRIZZLE(230, "Thunderstorm", "thunderstorm with light drizzle", "11"),
    /**
     * The thunderstorm with drizzle.
     */
    THUNDERSTORM_DRIZZLE(231, "Thunderstorm", "thunderstorm with drizzle", "11"),
    /**
     * The thunderstorm with heavy drizzle.
     */
    THUNDERSTORM_HEAVY_DRIZZLE(232, "Thunderstorm", "thunderstorm with heavy drizzle", "11"),

    // Group 3xx: Drizzle

    /**
     * The light intensity drizzle.
     */
    DRIZZLE_LIGHT(300, "Drizzle", "light intensity drizzle", "09"),
    /**
     * The drizzle.
     */
    DRIZZLE(301, "Drizzle", "drizzle", "09"),
    /**
     * The heavy intensity drizzle.
     */
    DRIZZLE_HEAVY(302, "Drizzle", "heavy intensity drizzle", "09"),
    /**
     * The light intensity drizzle rain.
     */
    DRIZZLE_LIGHT_RAIN(310, "Drizzle", "light intensity drizzle rain", "09"),
    /**
     * The drizzle rain.
     */
    DRIZZLE_RAIN(311, "Drizzle", "drizzle rain", "09"),
    /**
     * The heavy intensity drizzle rain.
     */
    DRIZZLE_HEAVY_RAIN(312, "Drizzle", "heavy intensity drizzle rain", "09"),
    /**
     * The shower rain and drizzle.
     */
    DRIZZLE_SHOWER_RAIN(313, "Drizzle", "shower rain and drizzle", "09"),
    /**
     * The heavy shower rain and drizzle.
     */
    DRIZZLE_HEAVY_SHOWER_RAIN(314, "Drizzle", "heavy shower rain and drizzle", "09"),
    /**
     * The shower drizzle.
     */
    DRIZZLE_SHOWER(321, "Drizzle", "shower drizzle", "09"),

    // Group 5xx: Rain

    /**
     * The light rain.
     */
    RAIN_LIGHT(500, "Rain", "light rain", "10"),
    /**
     * The moderate rain.
     */
    RAIN_MODERATE(501, "Rain", "moderate rain", "10"),
    /**
     * The heavy intensity rain.
     */
    RAIN_HEAVY(502, "Rain", "heavy intensity rain", "10"),
    /**
     * The very heavy rain.
     */
    RAIN_VERY_HEAVY(503, "Rain", "very heavy rain", "10"),
    /**
     * The very heavy rain.
     */
    RAIN_EXTREME(504, "Rain", "very heavy rain", "10"),
    /**
     * The freezing rain.
     */
    RAIN_FREEZING(511, "Rain", "freezing rain", "10"),
    /**
     * The light intensity shower rain.
     */
    RAIN_LIGHT_SHOWER(520, "Rain", "light intensity shower rain", "10"),
    /**
     * The shower rain.
     */
    RAIN_SHOWER(521, "Rain", "shower rain", "10"),
    /**
     * The heavy intensity shower rain.
     */
    RAIN_HEAVY_SHOWER(522, "Rain", "heavy intensity shower rain", "10"),
    /**
     * The ragged shower rain.
     */
    RAIN_RAGGED_SHOWER(531, "Rain", "ragged shower rain", "10"),

    // Group 6xx: Snow

    /**
     * The light snow.
     */
    SNOW_LIGHT(600, "Snow", "light snow", "13"),
    /**
     * The snow.
     */
    SNOW(601, "Snow", "snow", "13"),
    /**
     * The heavy snow.
     */
    SNOW_HEAVY(602, "Snow", "heavy snow", "13"),
    /**
     * The sleet.
     */
    SNOW_SLEET(611, "Snow", "sleet", "13"),
    /**
     * The light shower sleet.
     */
    SNOW_LIGHT_SHOWER_SLEET(612, "Snow", "light shower sleet", "13"),
    /**
     * The shower sleet.
     */
    SNOW_SHOWER_SLEET(613, "Snow", "shower sleet", "13"),
    /**
     * The light rain and snow.
     */
    SNOW_LIGHT_RAIN_AND_SNOW(615, "Snow", "light rain and snow", "13"),
    /**
     * The rain and snow.
     */
    SNOW_RAIN_AND_SNOW(616, "Snow", "rain and snow", "13"),
    /**
     * The light shower snow.
     */
    SNOW_LIGHT_SHOWER_SNOW(620, "Snow", "light shower snow", "13"),
    /**
     * The shower snow.
     */
    SNOW_SHOWER_SNOW(621, "Snow", "shower snow", "13"),
    /**
     * The heavy shower snow.
     */
    SNOW_HEAVY_SHOWER_SNOW(622, "Snow", "heavy shower snow", "13"),

    // Group 7xx: Atmosphere

    /**
     * The mist.
     */
    MIST(701, "Mist", "mist", "50"),
    /**
     * The smoke.
     */
    SMOKE(711, "Smoke", "smoke", "50"),
    /**
     * The haze.
     */
    HAZE(721, "Haze", "haze", "50"),
    /**
     * The sand/dust whirls.
     */
    DUST_WHIRLS(731, "Dust", "sand/dust whirls", "50"),
    /**
     * The fog.
     */
    FOG(741, "Fog", "fog", "50"),
    /**
     * The sand.
     */
    SAND(751, "Sand", "sand", "50"),
    /**
     * The dust.
     */
    DUST(761, "Dust", "dust", "50"),
    /**
     * The volcanic ash.
     */
    ASH(762, "Ash", "volcanic ash", "50"),
    /**
     * The squall.
     */
    SQUALL(771, "Squall", "squalls", "50"),
    /**
     * The tornado.
     */
    TORNADO(781, "Tornado", "tornado", "50"),

    // Group 800: Clear

    /**
     * The clear sky.
     */
    CLEAR(800, "Clear", "clear sky", "01"),

    // Group 80x: Clouds

    /**
     * A few clouds: 11-25%.
     */
    CLOUDS_FEW(801, "Clouds", "few clouds: 11-25%", "02"),
    /**
     * A scattered clouds: 25-50%.
     */
    CLOUDS_SCATTERED(802, "Clouds", "scattered clouds: 25-50%", "03"),
    /**
     * A broken clouds: 51-84%.
     */
    CLOUDS_BROKEN(803, "Clouds", "broken clouds: 51-84%", "04"),
    /**
     * An overcast clouds: 85-100%.
     */
    CLOUDS_OVERCAST(804, "Clouds", "overcast clouds: 85-100%", "04");


    private final int id;
    private final String name;
    private final String description;
    private final String iconId;

    private WeatherCondition(int id, String name, String description, String iconId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.iconId = iconId;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }


    /**
     * Gets icon id based on part of day.
     *
     * @param partOfDay The part of day we need the icon for.
     * @return the icon id
     */
    public String getIconId(DayTime partOfDay) {
        return iconId + partOfDay.getValue();
    }

    /**
     * Gets day icon id.
     *
     * @return the day icon id
     */
    public String getDayIconId() {
        return getIconId(DayTime.DAY);
    }

    /**
     * Gets night icon id.
     *
     * @return the night icon id
     */
    public String getNightIconId() {
        return getIconId(DayTime.NIGHT);
    }

    /**
     * Gets day icon url.
     *
     * @return the day icon url
     */
    public String getDayIconUrl() {
        return getIconUrl(getDayIconId());
    }

    /**
     * Gets night icon url.
     *
     * @return the night icon url
     */
    public String getNightIconUrl() {
        return getIconUrl(getNightIconId());
    }

    /**
     * Gets icon url.
     *
     * @param iconId the icon id
     * @return the icon url
     */
    public static String getIconUrl(String iconId) {
        return "https://openweathermap.org/img/w/" + iconId + ".png";
    }

    /**
     * Gets {@link WeatherCondition} by id.
     *
     * @param id the id
     * @return the by id
     */
    @JsonCreator
    public static WeatherCondition getById(@JsonProperty("id") int id) {
        final Optional<WeatherCondition> optionalWeatherCondition =
                Arrays.stream(values()).filter(weatherCondition -> weatherCondition.getId() == id).findFirst();
        return optionalWeatherCondition.orElse(null);
    }

    @Override
    public String toString() {
        return "Weather condition(" + id + "): " + name + '(' + description + ')';
    }
}
