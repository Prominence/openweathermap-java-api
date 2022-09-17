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

package com.github.prominence.openweathermap.api.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.prominence.openweathermap.api.model.weather.CurrentWeather;
import com.github.prominence.openweathermap.api.model.weather.CurrentWeatherModel;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.github.prominence.openweathermap.api.context.TestMappingUtils.loadDeserializedResourceAs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CurrentWeatherResponseMapperTest {

    @Test
    public void testDeserialize_ShouldSucceed_WhenCalledWithOfficialJsonExample() throws JsonProcessingException {
        //given
        final String resource = "/responses/valid/current-weather-official.json";

        //when
        final CurrentWeather actual = loadDeserializedResourceAs(resource, CurrentWeatherModel.class);

        //then
        assertNotNull(actual);
        //TODO: Consider adding more assertions
    }

    @Test
    public void testDeserialize_ShouldSucceed_WhenCalledWithFullyPopulatedExample() throws JsonProcessingException {
        //given
        final String resource = "/responses/valid/current-weather-minsk.json";

        //when
        final CurrentWeather actual = loadDeserializedResourceAs(resource, CurrentWeatherModel.class);

        //then
        assertNotNull(actual);

        assertLatitudeSet(actual);
        assertLongitudeSet(actual);
        assertCountryCodeSet(actual);

        assertTemperatureSet(actual);
        assertFeelsLikeSet(actual);
        assertMinTempSet(actual);
        assertMaxTempSet(actual);

        assertWindSpeedSet(actual);
        assertWindDirectionSet(actual);
        assertWindSpeedGustSet(actual);

        assert1HrRainSet(actual);
        assert3HrRainSet(actual);
        assert1HrSnowSet(actual);
        assert3HrSnowSet(actual);
    }

    @Test
    public void testDeserialize_ShouldThrowException_WhenCalledWithInvalidJson() {
        //given
        final String resource = "/responses/invalid/current-weather-minsk-invalid.json";

        //when
        assertThrows(JsonProcessingException.class, () -> loadDeserializedResourceAs(resource, CurrentWeatherModel.class));

        //then + exception
    }

    @Test
    public void testDeserialize_ShouldLeaveOutForecastTime_WhenCalledWithJsonWithoutDt() throws JsonProcessingException {
        //given
        final String resource = "/responses/valid/current-weather-minsk-missing-dt.json";

        //when
        final CurrentWeather actual = loadDeserializedResourceAs(resource, CurrentWeatherModel.class);

        //then
        assertNotNull(actual);
        assertNull(actual.getForecastTime());
    }


    @Test
    public void testDeserialize_ShouldLeaveOutFeelsLike_WhenCalledWithJsonWithoutFeelsLike() throws JsonProcessingException {
        //given
        final String resource = "/responses/valid/current-weather-minsk-missing-feelslike.json";

        //when
        final CurrentWeather actual = loadDeserializedResourceAs(resource, CurrentWeatherModel.class);

        //then
        assertNotNull(actual);
        assertTemperatureSet(actual);
        assertNull(actual.getTemperature().getFeelsLike());
        assertMinTempSet(actual);
        assertMaxTempSet(actual);
    }

    @Test
    public void testDeserialize_ShouldLeaveOutMinTemp_WhenCalledWithJsonWithoutMinTemp() throws JsonProcessingException {
        //given
        final String resource = "/responses/valid/current-weather-minsk-missing-mintemp.json";

        //when
        final CurrentWeather actual = loadDeserializedResourceAs(resource, CurrentWeatherModel.class);

        //then
        assertNotNull(actual);
        assertTemperatureSet(actual);
        assertFeelsLikeSet(actual);
        assertNull(actual.getTemperature().getMin());
        assertMaxTempSet(actual);
    }

    @Test
    public void testDeserialize_ShouldLeaveOutMaxTemp_WhenCalledWithJsonWithoutMaxTemp() throws JsonProcessingException {
        //given
        final String resource = "/responses/valid/current-weather-minsk-missing-maxtemp.json";

        //when
        final CurrentWeather actual = loadDeserializedResourceAs(resource, CurrentWeatherModel.class);

        //then
        assertNotNull(actual);
        assertTemperatureSet(actual);
        assertFeelsLikeSet(actual);
        assertMinTempSet(actual);
        assertNull(actual.getTemperature().getMax());
    }

    @Test
    public void testDeserialize_ShouldLeaveOutWindDirection_WhenCalledWithJsonWithoutWindDirection() throws JsonProcessingException {
        //given
        final String resource = "/responses/valid/current-weather-minsk-missing-wind-direction.json";

        //when
        final CurrentWeather actual = loadDeserializedResourceAs(resource, CurrentWeatherModel.class);

        //then
        assertNotNull(actual);
        assertWindSpeedSet(actual);
        assertNull(actual.getWind().getDirectionDegrees());
        assertWindSpeedGustSet(actual);
    }

    @Test
    public void testDeserialize_ShouldLeaveOutWindSpeedGust_WhenCalledWithJsonWithoutWindSpeedGust() throws JsonProcessingException {
        //given
        final String resource = "/responses/valid/current-weather-minsk-missing-gust.json";

        //when
        final CurrentWeather actual = loadDeserializedResourceAs(resource, CurrentWeatherModel.class);

        //then
        assertNotNull(actual);
        assertWindSpeedSet(actual);
        assertWindDirectionSet(actual);
        assertNull(actual.getWind().getGust());
    }

    @Test
    public void testDeserialize_ShouldLeaveOut1HrPrecipitation_WhenCalledWithJsonWithout1HrPrecipitation() throws JsonProcessingException {
        //given
        final String resource = "/responses/valid/current-weather-minsk-missing-1h.json";

        //when
        final CurrentWeather actual = loadDeserializedResourceAs(resource, CurrentWeatherModel.class);

        //then
        assertNotNull(actual.getRain());
        assertNull(actual.getRain().getOneHourLevel());
        assert3HrRainSet(actual);
        assertNull(actual.getSnow().getOneHourLevel());
        assert3HrSnowSet(actual);
    }

    @Test
    public void testDeserialize_ShouldLeaveOut3HrPrecipitation_WhenCalledWithJsonWithout3HrPrecipitation() throws JsonProcessingException {
        //given
        final String resource = "/responses/valid/current-weather-minsk-missing-3h.json";

        //when
        final CurrentWeather actual = loadDeserializedResourceAs(resource, CurrentWeatherModel.class);

        //then
        assertNotNull(actual.getRain());
        assert1HrRainSet(actual);
        assertNull(actual.getRain().getThreeHourLevel());
        assert1HrSnowSet(actual);
        assertNull(actual.getSnow().getThreeHourLevel());
    }

    @Test
    public void testDeserialize_ShouldLeaveOutCoordinatesAndCountry_WhenCalledWithJsonWithoutCoordinatesAndCountryCode()
            throws JsonProcessingException {
        //given
        final String resource = "/responses/valid/current-weather-minsk-missing-coord-and-country.json";

        //when
        final CurrentWeather actual = loadDeserializedResourceAs(resource, CurrentWeatherModel.class);

        //then
        assertNull(actual.getLocation().getCoordinates());
        assertNull(actual.getLocation().getCountryCode());
    }

    private void assertCountryCodeSet(CurrentWeather weather) {
        assertEquals("BY", weather.getLocation().getCountryCode());
    }

    private void assertLongitudeSet(CurrentWeather weather) {
        assertEquals(27.5667D, weather.getLocation().getCoordinates().getLongitude());
    }

    private void assertLatitudeSet(CurrentWeather weather) {
        assertEquals(53.9D, weather.getLocation().getCoordinates().getLatitude());
    }


    private void assertMaxTempSet(CurrentWeather weather) {
        assertEquals(BigDecimal.valueOf(2), weather.getTemperature().getMax().asKelvin());
    }

    private void assertMinTempSet(CurrentWeather weather) {
        assertEquals(BigDecimal.valueOf(2), weather.getTemperature().getMin().asKelvin());
    }

    private void assertFeelsLikeSet(CurrentWeather weather) {
        assertEquals(BigDecimal.valueOf(0), weather.getTemperature().getFeelsLike().asKelvin());
    }

    private void assertTemperatureSet(CurrentWeather weather) {
        assertEquals(BigDecimal.valueOf(2), weather.getTemperature().getTemperature().asKelvin());
    }

    private void assertWindSpeedGustSet(CurrentWeather weather) {
        assertEquals(BigDecimal.valueOf(2.44), weather.getWind().getGust().asMetersPerSecond());
    }

    private void assertWindDirectionSet(CurrentWeather weather) {
        assertEquals(250, weather.getWind().getDirectionDegrees());
    }

    private void assertWindSpeedSet(CurrentWeather weather) {
        assertEquals(new BigDecimal("2.00"), weather.getWind().getSpeed().asMetersPerSecond());
    }

    private void assert1HrRainSet(CurrentWeather weather) {
        assertEquals(BigDecimal.valueOf(0.1), weather.getRain().getOneHourLevel());
    }

    private void assert3HrRainSet(CurrentWeather weather) {
        assertEquals(BigDecimal.valueOf(0.6), weather.getRain().getThreeHourLevel());
    }

    private void assert1HrSnowSet(CurrentWeather weather) {
        assertEquals(BigDecimal.valueOf(0.2), weather.getSnow().getOneHourLevel());
    }

    private void assert3HrSnowSet(CurrentWeather weather) {
        assertEquals(BigDecimal.valueOf(0.7), weather.getSnow().getThreeHourLevel());
    }
}
