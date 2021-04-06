/*
 * Copyright (c) 2021 Alexey Zinchenko
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

package com.github.prominence.openweathermap.api.request.weather;

import com.github.prominence.openweathermap.api.enums.UnitSystem;
import com.github.prominence.openweathermap.api.model.weather.Weather;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CurrentWeatherResponseMapperUnitTest {
    @Test
    public void getSingle() {
        final String jsonString = "{\"coord\":{\"lon\":27.5667,\"lat\":53.9},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"snow\":{\"1h\":0.2},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";

        final Weather weather = new CurrentWeatherResponseMapper(UnitSystem.METRIC).getSingle(jsonString);

        assertNotNull(weather);
    }

    @Test(expected = RuntimeException.class)
    public void getSingle_withDamagedJSON() {
        final String jsonString = "{\"coord\":\"lon\":27.5667,\"lat\":53.9},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"snow\":{\"1h\":0.2},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";
        new CurrentWeatherResponseMapper(null).getSingle(jsonString);
    }

    @Test
    public void getSingle_withoutDt() {
        final String jsonString = "{\"coord\":{\"lon\":27.5667,\"lat\":53.9},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"snow\":{\"1h\":0.2},\"clouds\":{\"all\":75},\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";

        final Weather weather = new CurrentWeatherResponseMapper(UnitSystem.METRIC).getSingle(jsonString);

        assertNotNull(weather);
        assertNull(weather.getCalculationTime());
    }

    @Test
    public void getSingle_withoutWeatherNode() {
        final String jsonString = "{\"coord\":{\"lon\":27.5667,\"lat\":53.9},\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"snow\":{\"1h\":0.2},\"clouds\":{\"all\":75},\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";

        final Weather weather = new CurrentWeatherResponseMapper(UnitSystem.METRIC).getSingle(jsonString);

        assertNotNull(weather);
        assertNull(weather.getWeatherState());
    }

    @Test
    public void getSingle_withTemperatureVariations() {
        String jsonString = "{\"coord\":{\"lon\":27.5667,\"lat\":53.9},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"snow\":{\"1h\":0.2},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";
        final CurrentWeatherResponseMapper mapper = new CurrentWeatherResponseMapper(UnitSystem.METRIC);

        Weather weather = mapper.getSingle(jsonString);

        assertNotNull(weather);
        assertEquals(1.84, weather.getTemperature().getValue(), 0.00001);
        assertEquals(-0.31, weather.getTemperature().getFeelsLike(), 0.00001);
        assertEquals(1.67, weather.getTemperature().getMinTemperature(), 0.00001);
        assertEquals(2, weather.getTemperature().getMaxTemperature(), 0.00001);

        // without feels like node
        jsonString = "{\"coord\":{\"lon\":27.5667,\"lat\":53.9},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"snow\":{\"1h\":0.2},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";
        weather = mapper.getSingle(jsonString);

        assertNotNull(weather);
        assertNotNull(weather.getTemperature());
        assertNull(weather.getTemperature().getFeelsLike());
        assertNotNull(weather.getTemperature().getMinTemperature());
        assertNotNull(weather.getTemperature().getMaxTemperature());

        // without min temperature node
        jsonString = "{\"coord\":{\"lon\":27.5667,\"lat\":53.9},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"snow\":{\"1h\":0.2},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";
        weather = mapper.getSingle(jsonString);

        assertNotNull(weather);
        assertNotNull(weather.getTemperature());
        assertNotNull(weather.getTemperature().getFeelsLike());
        assertNull(weather.getTemperature().getMinTemperature());
        assertNotNull(weather.getTemperature().getMaxTemperature());

        // without max temperature node
        jsonString = "{\"coord\":{\"lon\":27.5667,\"lat\":53.9},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"snow\":{\"1h\":0.2},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";
        weather = mapper.getSingle(jsonString);

        assertNotNull(weather);
        assertNotNull(weather.getTemperature());
        assertNotNull(weather.getTemperature().getFeelsLike());
        assertNotNull(weather.getTemperature().getMinTemperature());
        assertNull(weather.getTemperature().getMaxTemperature());
    }

    @Test
    public void getSingle_withWindVariations() {
        String jsonString = "{\"coord\":{\"lon\":27.5667,\"lat\":53.9},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"snow\":{\"1h\":0.2},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";

        final CurrentWeatherResponseMapper mapper = new CurrentWeatherResponseMapper(UnitSystem.METRIC);
        Weather weather = mapper.getSingle(jsonString);

        assertNotNull(weather);
        assertEquals(2, weather.getWind().getSpeed(), 0.00001);
        assertEquals(250, weather.getWind().getDegrees(), 0.00001);
        assertNull(weather.getWind().getGust());

        // without degrees
        jsonString = "{\"coord\":{\"lon\":27.5667,\"lat\":53.9},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2},\"snow\":{\"1h\":0.2},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";
        weather = mapper.getSingle(jsonString);

        assertNotNull(weather);
        assertNotNull(weather.getWind());
        assertNull(weather.getWind().getDegrees());

        // with gust
        jsonString = "{\"coord\":{\"lon\":27.5667,\"lat\":53.9},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250, \"gust\": 2.44},\"snow\":{\"1h\":0.2},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";
        weather = mapper.getSingle(jsonString);

        assertNotNull(weather);
        assertNotNull(weather.getWind());
        assertNotNull(weather.getWind().getDegrees());
        assertEquals(2.44, weather.getWind().getGust(), 0.00001);
    }

    @Test
    public void getSingle_withRainVariations() {
        final String jsonWith1Hr = "{\"coord\":{\"lon\":27.5667,\"lat\":53.9},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"rain\":{\"1h\":0.1},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";
        final String jsonWith3Hr = "{\"coord\":{\"lon\":27.5667,\"lat\":53.9},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"rain\":{\"3h\":0.3},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";
        final String jsonWithBoth = "{\"coord\":{\"lon\":27.5667,\"lat\":53.9},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"rain\":{\"1h\":0.1, \"3h\":0.3},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";

        final CurrentWeatherResponseMapper mapper = new CurrentWeatherResponseMapper(UnitSystem.METRIC);
        Weather weather = mapper.getSingle(jsonWith1Hr);

        // with 1h level only
        assertNotNull(weather.getRain());
        assertEquals(0.1, weather.getRain().getOneHourLevel(), 0.00001);
        assertNull(weather.getRain().getThreeHourLevel());

        weather = mapper.getSingle(jsonWith3Hr);

        // with 3h level only
        assertNotNull(weather.getRain());
        assertNull(weather.getRain().getOneHourLevel());
        assertEquals(0.3, weather.getRain().getThreeHourLevel(), 0.00001);

        weather = mapper.getSingle(jsonWithBoth);

        // with both levels
        assertNotNull(weather.getRain());
        assertEquals(0.1, weather.getRain().getOneHourLevel(), 0.00001);
        assertEquals(0.3, weather.getRain().getThreeHourLevel(), 0.00001);
    }

    @Test
    public void getSingle_withSnowVariations() {
        final String jsonWith1Hr = "{\"coord\":{\"lon\":27.5667,\"lat\":53.9},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"snow\":{\"1h\":0.1},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";
        final String jsonWith3Hr = "{\"coord\":{\"lon\":27.5667,\"lat\":53.9},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"snow\":{\"3h\":0.3},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";
        final String jsonWithBoth = "{\"coord\":{\"lon\":27.5667,\"lat\":53.9},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"snow\":{\"1h\":0.1, \"3h\":0.3},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";

        final CurrentWeatherResponseMapper mapper = new CurrentWeatherResponseMapper(UnitSystem.METRIC);
        Weather weather = mapper.getSingle(jsonWith1Hr);

        // with 1h level only
        assertNotNull(weather.getSnow());
        assertEquals(0.1, weather.getSnow().getOneHourLevel(), 0.00001);
        assertNull(weather.getSnow().getThreeHourLevel());

        weather = mapper.getSingle(jsonWith3Hr);

        // with 3h level only
        assertNotNull(weather.getSnow());
        assertNull(weather.getSnow().getOneHourLevel());
        assertEquals(0.3, weather.getSnow().getThreeHourLevel(), 0.00001);

        weather = mapper.getSingle(jsonWithBoth);

        // with both levels
        assertNotNull(weather.getSnow());
        assertEquals(0.1, weather.getSnow().getOneHourLevel(), 0.00001);
        assertEquals(0.3, weather.getSnow().getThreeHourLevel(), 0.00001);
    }

    @Test
    public void getSingle_withLocationVariations() {
        String jsonString = "{\"coord\":{\"lon\":27.5667,\"lat\":53.9},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"snow\":{\"1h\":0.1},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";
        final CurrentWeatherResponseMapper mapper = new CurrentWeatherResponseMapper(UnitSystem.METRIC);

        Weather weather = mapper.getSingle(jsonString);

        assertNotNull(weather.getLocation().getCoordinate());
        assertNotNull(weather.getLocation().getCountryCode());

        // without coordinates and country code
        jsonString = "{\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"snow\":{\"1h\":0.1},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";
        weather = mapper.getSingle(jsonString);
        assertNull(weather.getLocation().getCoordinate());
        assertNull(weather.getLocation().getCountryCode());

        // coordinates without latitude
        jsonString = "{\"coord\":{\"lon\":27.5667},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"snow\":{\"1h\":0.1},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";
        weather = mapper.getSingle(jsonString);
        assertNull(weather.getLocation().getCoordinate());
        assertNotNull(weather.getLocation().getCountryCode());

        // coordinates without longitude
        jsonString = "{\"coord\":{\"lat\":53.9},\"weather\":[{\"id\":600,\"main\":\"Snow\",\"description\":\"небольшой снег\",\"icon\":\"13n\"}],\"base\":\"stations\",\"main\":{\"temp\":1.84,\"feels_like\":-0.31,\"temp_min\":1.67,\"temp_max\":2,\"pressure\":1001,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":2,\"deg\":250},\"snow\":{\"1h\":0.1},\"clouds\":{\"all\":75},\"dt\":1617746826,\"sys\":{\"type\":1,\"id\":8939,\"country\":\"BY\",\"sunrise\":1617766068,\"sunset\":1617814530},\"timezone\":10800,\"id\":0,\"name\":\"Minsk\",\"cod\":200}";
        weather = mapper.getSingle(jsonString);
        assertNull(weather.getLocation().getCoordinate());
        assertNotNull(weather.getLocation().getCountryCode());
    }

    @Test
    public void getList() {
        final String jsonString = "{\"cod\":200,\"calctime\":0.002580978,\"cnt\":15,\"list\":[{\"id\":2563191,\"dt\":1617746970,\"name\":\"Birkirkara\",\"coord\":{\"Lon\":14.4611,\"Lat\":35.8972},\"main\":{\"temp\":14.42,\"feels_like\":11.94,\"temp_min\":14,\"temp_max\":15,\"pressure\":1013,\"humidity\":88},\"visibility\":10000,\"wind\":{\"speed\":4.63,\"deg\":240},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2210247,\"dt\":1617746968,\"name\":\"Tripoli\",\"coord\":{\"Lon\":13.1875,\"Lat\":32.8752},\"main\":{\"temp\":16.44,\"feels_like\":14.28,\"temp_min\":16.44,\"temp_max\":16.44,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":1013,\"humidity\":65},\"visibility\":10000,\"wind\":{\"speed\":3.09,\"deg\":150},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2216885,\"dt\":1617746968,\"name\":\"Zawiya\",\"coord\":{\"Lon\":12.7278,\"Lat\":32.7522},\"main\":{\"temp\":16.87,\"feels_like\":14.35,\"temp_min\":16.87,\"temp_max\":16.87,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":1013,\"humidity\":60},\"visibility\":10000,\"wind\":{\"speed\":3.31,\"deg\":135},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2212771,\"dt\":1617746968,\"name\":\"Şabrātah\",\"coord\":{\"Lon\":12.4885,\"Lat\":32.7933},\"main\":{\"temp\":16.51,\"feels_like\":13.71,\"temp_min\":16.51,\"temp_max\":16.51,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":1014,\"humidity\":61},\"visibility\":10000,\"wind\":{\"speed\":3.67,\"deg\":136},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2215163,\"dt\":1617746968,\"name\":\"Masallātah\",\"coord\":{\"Lon\":14,\"Lat\":32.6167},\"main\":{\"temp\":14.09,\"feels_like\":13.24,\"temp_min\":14.09,\"temp_max\":14.09,\"pressure\":1016,\"sea_level\":1016,\"grnd_level\":989,\"humidity\":68},\"visibility\":10000,\"wind\":{\"speed\":0.64,\"deg\":226},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2219905,\"dt\":1617746968,\"name\":\"Al Khums\",\"coord\":{\"Lon\":14.2619,\"Lat\":32.6486},\"main\":{\"temp\":15.7,\"feels_like\":15.67,\"temp_min\":15.7,\"temp_max\":15.7,\"pressure\":1016,\"sea_level\":1016,\"grnd_level\":1014,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":0.12,\"deg\":348},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2208425,\"dt\":1617746968,\"name\":\"Zuwārah\",\"coord\":{\"Lon\":12.082,\"Lat\":32.9312},\"main\":{\"temp\":16.55,\"feels_like\":13.33,\"temp_min\":16.55,\"temp_max\":16.55,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":1015,\"humidity\":67},\"visibility\":10000,\"wind\":{\"speed\":4.82,\"deg\":145},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2210221,\"dt\":1617746968,\"name\":\"Tarhuna\",\"coord\":{\"Lon\":13.6332,\"Lat\":32.435},\"main\":{\"temp\":13.37,\"feels_like\":12.26,\"temp_min\":13.37,\"temp_max\":13.37,\"pressure\":1016,\"sea_level\":1016,\"grnd_level\":970,\"humidity\":62},\"visibility\":10000,\"wind\":{\"speed\":0.35,\"deg\":135},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2208485,\"dt\":1617746968,\"name\":\"Zliten\",\"coord\":{\"Lon\":14.5687,\"Lat\":32.4674},\"main\":{\"temp\":15.76,\"feels_like\":14.69,\"temp_min\":15.76,\"temp_max\":15.76,\"pressure\":1016,\"sea_level\":1016,\"grnd_level\":1015,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":1.63,\"deg\":133},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2217362,\"dt\":1617746968,\"name\":\"Gharyan\",\"coord\":{\"Lon\":13.0203,\"Lat\":32.1722},\"main\":{\"temp\":13.79,\"feels_like\":11.83,\"temp_min\":13.79,\"temp_max\":13.79,\"pressure\":1016,\"sea_level\":1016,\"grnd_level\":936,\"humidity\":56},\"visibility\":10000,\"wind\":{\"speed\":1.24,\"deg\":188},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2523693,\"dt\":1617746921,\"name\":\"Pozzallo\",\"coord\":{\"Lon\":14.8499,\"Lat\":36.7305},\"main\":{\"temp\":11.65,\"feels_like\":8.3,\"temp_min\":10,\"temp_max\":13,\"pressure\":1012,\"humidity\":94},\"visibility\":10000,\"wind\":{\"speed\":5.14,\"deg\":260},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":75},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"cer fragmentat\",\"icon\":\"04n\"}]},{\"id\":2524119,\"dt\":1617746921,\"name\":\"Modica\",\"coord\":{\"Lon\":14.774,\"Lat\":36.8459},\"main\":{\"temp\":11.51,\"feels_like\":8.12,\"temp_min\":10,\"temp_max\":13,\"pressure\":1012,\"humidity\":94},\"visibility\":10000,\"wind\":{\"speed\":5.14,\"deg\":260},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":75},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"cer fragmentat\",\"icon\":\"04n\"}]},{\"id\":2208791,\"dt\":1617746968,\"name\":\"Yafran\",\"coord\":{\"Lon\":12.5286,\"Lat\":32.0633},\"main\":{\"temp\":14.29,\"feels_like\":11.33,\"temp_min\":14.29,\"temp_max\":14.29,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":937,\"humidity\":50},\"visibility\":10000,\"wind\":{\"speed\":2.34,\"deg\":142},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2523581,\"dt\":1617746920,\"name\":\"Rosolini\",\"coord\":{\"Lon\":14.9478,\"Lat\":36.8242},\"main\":{\"temp\":11.54,\"feels_like\":8.16,\"temp_min\":10,\"temp_max\":13,\"pressure\":1012,\"humidity\":94},\"visibility\":10000,\"wind\":{\"speed\":5.14,\"deg\":260},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":75},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"cer fragmentat\",\"icon\":\"04n\"}]},{\"id\":2523650,\"dt\":1617746922,\"name\":\"Ragusa\",\"coord\":{\"Lon\":14.7172,\"Lat\":36.9282},\"main\":{\"temp\":11.63,\"feels_like\":8.27,\"temp_min\":10,\"temp_max\":13,\"pressure\":1012,\"humidity\":94},\"visibility\":10000,\"wind\":{\"speed\":5.14,\"deg\":260},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":75},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"cer fragmentat\",\"icon\":\"04n\"}]}]}";
        final List<Weather> weatherList = new CurrentWeatherResponseMapper(null).getList(jsonString);

        assertNotNull(weatherList);
        assertNotEquals(0, weatherList.size());
    }

    @Test(expected = RuntimeException.class)
    public void getList_withDamagedJSON() {
        final String jsonString = "{\"cod\":200,\"calctime\":0.002580978,\"cnt\":15,\"list\":{\"id\":2563191,\"dt\":1617746970,\"name\":\"Birkirkara\",\"coord\":{\"Lon\":14.4611,\"Lat\":35.8972},\"main\":{\"temp\":14.42,\"feels_like\":11.94,\"temp_min\":14,\"temp_max\":15,\"pressure\":1013,\"humidity\":88},\"visibility\":10000,\"wind\":{\"speed\":4.63,\"deg\":240},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2210247,\"dt\":1617746968,\"name\":\"Tripoli\",\"coord\":{\"Lon\":13.1875,\"Lat\":32.8752},\"main\":{\"temp\":16.44,\"feels_like\":14.28,\"temp_min\":16.44,\"temp_max\":16.44,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":1013,\"humidity\":65},\"visibility\":10000,\"wind\":{\"speed\":3.09,\"deg\":150},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2216885,\"dt\":1617746968,\"name\":\"Zawiya\",\"coord\":{\"Lon\":12.7278,\"Lat\":32.7522},\"main\":{\"temp\":16.87,\"feels_like\":14.35,\"temp_min\":16.87,\"temp_max\":16.87,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":1013,\"humidity\":60},\"visibility\":10000,\"wind\":{\"speed\":3.31,\"deg\":135},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2212771,\"dt\":1617746968,\"name\":\"Şabrātah\",\"coord\":{\"Lon\":12.4885,\"Lat\":32.7933},\"main\":{\"temp\":16.51,\"feels_like\":13.71,\"temp_min\":16.51,\"temp_max\":16.51,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":1014,\"humidity\":61},\"visibility\":10000,\"wind\":{\"speed\":3.67,\"deg\":136},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2215163,\"dt\":1617746968,\"name\":\"Masallātah\",\"coord\":{\"Lon\":14,\"Lat\":32.6167},\"main\":{\"temp\":14.09,\"feels_like\":13.24,\"temp_min\":14.09,\"temp_max\":14.09,\"pressure\":1016,\"sea_level\":1016,\"grnd_level\":989,\"humidity\":68},\"visibility\":10000,\"wind\":{\"speed\":0.64,\"deg\":226},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2219905,\"dt\":1617746968,\"name\":\"Al Khums\",\"coord\":{\"Lon\":14.2619,\"Lat\":32.6486},\"main\":{\"temp\":15.7,\"feels_like\":15.67,\"temp_min\":15.7,\"temp_max\":15.7,\"pressure\":1016,\"sea_level\":1016,\"grnd_level\":1014,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":0.12,\"deg\":348},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2208425,\"dt\":1617746968,\"name\":\"Zuwārah\",\"coord\":{\"Lon\":12.082,\"Lat\":32.9312},\"main\":{\"temp\":16.55,\"feels_like\":13.33,\"temp_min\":16.55,\"temp_max\":16.55,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":1015,\"humidity\":67},\"visibility\":10000,\"wind\":{\"speed\":4.82,\"deg\":145},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2210221,\"dt\":1617746968,\"name\":\"Tarhuna\",\"coord\":{\"Lon\":13.6332,\"Lat\":32.435},\"main\":{\"temp\":13.37,\"feels_like\":12.26,\"temp_min\":13.37,\"temp_max\":13.37,\"pressure\":1016,\"sea_level\":1016,\"grnd_level\":970,\"humidity\":62},\"visibility\":10000,\"wind\":{\"speed\":0.35,\"deg\":135},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2208485,\"dt\":1617746968,\"name\":\"Zliten\",\"coord\":{\"Lon\":14.5687,\"Lat\":32.4674},\"main\":{\"temp\":15.76,\"feels_like\":14.69,\"temp_min\":15.76,\"temp_max\":15.76,\"pressure\":1016,\"sea_level\":1016,\"grnd_level\":1015,\"humidity\":69},\"visibility\":10000,\"wind\":{\"speed\":1.63,\"deg\":133},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2217362,\"dt\":1617746968,\"name\":\"Gharyan\",\"coord\":{\"Lon\":13.0203,\"Lat\":32.1722},\"main\":{\"temp\":13.79,\"feels_like\":11.83,\"temp_min\":13.79,\"temp_max\":13.79,\"pressure\":1016,\"sea_level\":1016,\"grnd_level\":936,\"humidity\":56},\"visibility\":10000,\"wind\":{\"speed\":1.24,\"deg\":188},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2523693,\"dt\":1617746921,\"name\":\"Pozzallo\",\"coord\":{\"Lon\":14.8499,\"Lat\":36.7305},\"main\":{\"temp\":11.65,\"feels_like\":8.3,\"temp_min\":10,\"temp_max\":13,\"pressure\":1012,\"humidity\":94},\"visibility\":10000,\"wind\":{\"speed\":5.14,\"deg\":260},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":75},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"cer fragmentat\",\"icon\":\"04n\"}]},{\"id\":2524119,\"dt\":1617746921,\"name\":\"Modica\",\"coord\":{\"Lon\":14.774,\"Lat\":36.8459},\"main\":{\"temp\":11.51,\"feels_like\":8.12,\"temp_min\":10,\"temp_max\":13,\"pressure\":1012,\"humidity\":94},\"visibility\":10000,\"wind\":{\"speed\":5.14,\"deg\":260},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":75},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"cer fragmentat\",\"icon\":\"04n\"}]},{\"id\":2208791,\"dt\":1617746968,\"name\":\"Yafran\",\"coord\":{\"Lon\":12.5286,\"Lat\":32.0633},\"main\":{\"temp\":14.29,\"feels_like\":11.33,\"temp_min\":14.29,\"temp_max\":14.29,\"pressure\":1015,\"sea_level\":1015,\"grnd_level\":937,\"humidity\":50},\"visibility\":10000,\"wind\":{\"speed\":2.34,\"deg\":142},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":0},\"weather\":[{\"id\":800,\"main\":\"Clear\",\"description\":\"cer senin\",\"icon\":\"01n\"}]},{\"id\":2523581,\"dt\":1617746920,\"name\":\"Rosolini\",\"coord\":{\"Lon\":14.9478,\"Lat\":36.8242},\"main\":{\"temp\":11.54,\"feels_like\":8.16,\"temp_min\":10,\"temp_max\":13,\"pressure\":1012,\"humidity\":94},\"visibility\":10000,\"wind\":{\"speed\":5.14,\"deg\":260},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":75},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"cer fragmentat\",\"icon\":\"04n\"}]},{\"id\":2523650,\"dt\":1617746922,\"name\":\"Ragusa\",\"coord\":{\"Lon\":14.7172,\"Lat\":36.9282},\"main\":{\"temp\":11.63,\"feels_like\":8.27,\"temp_min\":10,\"temp_max\":13,\"pressure\":1012,\"humidity\":94},\"visibility\":10000,\"wind\":{\"speed\":5.14,\"deg\":260},\"rain\":null,\"snow\":null,\"clouds\":{\"today\":75},\"weather\":[{\"id\":803,\"main\":\"Clouds\",\"description\":\"cer fragmentat\",\"icon\":\"04n\"}]}]}";
        new CurrentWeatherResponseMapper(null).getList(jsonString);
    }
}