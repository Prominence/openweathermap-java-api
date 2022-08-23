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

package com.github.prominence.openweathermap.api.request.roadrisk;

import com.github.prominence.openweathermap.api.ApiTest;
import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.context.ApiConfiguration;
import com.github.prominence.openweathermap.api.core.net.MockHttpClient;
import com.github.prominence.openweathermap.api.model.roadrisk.RoadRisk;
import com.github.prominence.openweathermap.api.model.roadrisk.TrackPoint;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

public class RoadRiskIntegrationTest extends ApiTest {
    private static final MockHttpClient httpClient = new MockHttpClient();

    @Test
    public void whenGetSingleCurrentWeatherByCoordinateRequestAsJava_thenReturnNotNull() throws IOException {
        final TrackPoint trackPoint = new TrackPoint(5, 5);
        trackPoint.setRequestedTime(OffsetDateTime.now(ZoneOffset.UTC));

        final String responseOutput = IOUtils.resourceToString("/responses/valid/road-risk.json", StandardCharsets.UTF_8);
        httpClient.setResponseOutput(responseOutput);
        final OpenWeatherMapClient client = new OpenWeatherMapClient(ApiConfiguration.builder()
                .httpClient(httpClient).apiKey("").build());

        final List<RoadRisk> roadRiskRecords = client
                .roadRisk()
                .byTrackPoints(Collections.singletonList(trackPoint))
                .retrieve()
                .asJava();

        System.out.println(roadRiskRecords);
    }

}
