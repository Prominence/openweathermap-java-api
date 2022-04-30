/*
 * Copyright (c) 2022 Alexey Zinchenko
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

import com.github.prominence.openweathermap.api.model.Coordinates;
import com.github.prominence.openweathermap.api.model.geocoding.GeocodingRecord;
import com.github.prominence.openweathermap.api.model.geocoding.ZipCodeGeocodingRecord;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class GeocodingResponseMapperTest {

    @Test
    public void reverseGeocodingResponseMappingTest() {
        String jsonResponse = """
                [
                  {
                    "name": "City of London",
                    "local_names": {
                      "ar": "مدينة لندن",
                      "ascii": "City of London",
                      "bg": "Сити",
                      "ca": "La City",
                      "de": "London City",
                      "el": "Σίτι του Λονδίνου",
                      "en": "City of London",
                      "fa": "سیتی لندن",
                      "feature_name": "City of London",
                      "fi": "Lontoon City",
                      "fr": "Cité de Londres",
                      "gl": "Cidade de Londres",
                      "he": "הסיטי של לונדון",
                      "hi": "सिटी ऑफ़ लंदन",
                      "id": "Kota London",
                      "it": "Londra",
                      "ja": "シティ・オブ・ロンドン",
                      "la": "Civitas Londinium",
                      "lt": "Londono Sitis",
                      "pt": "Cidade de Londres",
                      "ru": "Сити",
                      "sr": "Сити",
                      "th": "นครลอนดอน",
                      "tr": "Londra Şehri",
                      "vi": "Thành phố Luân Đôn",
                      "zu": "Idolobha weLondon"
                    },
                    "lat": 51.5128,
                    "lon": -0.0918,
                    "country": "GB"
                  },
                  {
                    "name": "London",
                    "local_names": {
                      "af": "Londen",
                      "ar": "لندن",
                      "ascii": "London",
                      "az": "London",
                      "bg": "Лондон",
                      "ca": "Londres",
                      "da": "London",
                      "de": "London",
                      "el": "Λονδίνο",
                      "en": "London",
                      "eu": "Londres",
                      "fa": "لندن",
                      "feature_name": "London",
                      "fi": "Lontoo",
                      "fr": "Londres",
                      "gl": "Londres",
                      "he": "לונדון",
                      "hi": "लंदन",
                      "hr": "London",
                      "hu": "London",
                      "id": "London",
                      "it": "Londra",
                      "ja": "ロンドン",
                      "la": "Londinium",
                      "lt": "Londonas",
                      "mk": "Лондон",
                      "nl": "Londen",
                      "no": "London",
                      "pl": "Londyn",
                      "pt": "Londres",
                      "ro": "Londra",
                      "ru": "Лондон",
                      "sk": "Londýn",
                      "sl": "London",
                      "sr": "Лондон",
                      "th": "ลอนดอน",
                      "tr": "Londra",
                      "vi": "Luân Đôn",
                      "zu": "ILondon"
                    },
                    "lat": 51.5085,
                    "lon": -0.1257,
                    "country": "GB"
                  },
                  {
                    "name": "Islington",
                    "local_names": {
                      "ascii": "Islington",
                      "az": "İslinqton",
                      "fa": "ایزلینتن",
                      "feature_name": "Islington",
                      "fr": "District londonien d'Islington",
                      "he": "איזלינגטון",
                      "ja": "イズリントン",
                      "ru": "Ислингтон"
                    },
                    "lat": 51.5362,
                    "lon": -0.103,
                    "country": "GB"
                  },
                  {
                    "name": "Lewisham",
                    "local_names": {
                      "ascii": "Lewisham",
                      "de": "London Borough of Lewisham",
                      "en": "Lewisham",
                      "feature_name": "Lewisham",
                      "fi": "Lewisham",
                      "fr": "Lewisham",
                      "hu": "Lewisham kerület",
                      "nl": "Lewisham",
                      "no": "Lewisham",
                      "ro": "Lewisham"
                    },
                    "lat": 51.4535,
                    "lon": -0.018,
                    "country": "GB"
                  },
                  {
                    "name": "Islington",
                    "local_names": {
                      "ascii": "Islington",
                      "de": "London Borough of Islington",
                      "en": "Islington",
                      "feature_name": "Islington",
                      "fr": "Islington",
                      "nl": "Islington",
                      "no": "Islington",
                      "ro": "Islington"
                    },
                    "lat": 51.547,
                    "lon": -0.1094,
                    "country": "GB"
                  }
                ]
                """;

        List<GeocodingRecord> geocodingRecords = new GeocodingResponseMapper().mapGeocodingResponse(jsonResponse);

        assertNotNull(geocodingRecords);
        assertEquals(5, geocodingRecords.size());
    }

    public void zipGeocodingInfoResponseMappingTest() {
        String jsonResponse = """
                {
                  "zip": "90210",
                  "name": "Beverly Hills",
                  "lat": 34.0901,
                  "lon": -118.4065,
                  "country": "US"
                }
                """;

        ZipCodeGeocodingRecord zipCodeGeocodingRecord = new GeocodingResponseMapper().mapZipCodeGeocodingResponse(jsonResponse);

        assertNotNull(zipCodeGeocodingRecord);
        assertEquals("90210", zipCodeGeocodingRecord.getZip());
        assertEquals("Beverly Hills", zipCodeGeocodingRecord.getName());
        assertEquals("US", zipCodeGeocodingRecord.getCountryCode());
        assertEquals(Coordinates.of(34.0901, -118.4065), zipCodeGeocodingRecord.getCoordinates());
    }
}