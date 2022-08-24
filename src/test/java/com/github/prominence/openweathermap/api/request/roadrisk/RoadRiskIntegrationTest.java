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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.prominence.openweathermap.api.ApiTest;
import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.context.ApiConfiguration;
import com.github.prominence.openweathermap.api.core.net.HttpClient;
import com.github.prominence.openweathermap.api.model.roadrisk.RoadRisk;
import com.github.prominence.openweathermap.api.model.roadrisk.TrackPoint;
import com.github.prominence.openweathermap.api.request.roadrisk.model.RoadRiskRequestPayload;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RoadRiskIntegrationTest extends ApiTest {
    @Mock
    private HttpClient httpClient;
    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        if (openMocks != null) {
            openMocks.close();
        }
    }

    @Test
    public void testAsJavaTerminator_ShouldReturnRoadRiskData_WhenCalledWithValidRoadRiskRequest() throws IOException {
        //given
        final TrackPoint trackPoint = new TrackPoint(5, 5);
        trackPoint.setRequestedTime(OffsetDateTime.now(ZoneOffset.UTC));
        final String body = new ObjectMapper().writeValueAsString(new RoadRiskRequestPayload(Collections.singletonList(trackPoint)));

        final String responseOutput = IOUtils.resourceToString("/responses/valid/road-risk.json", StandardCharsets.UTF_8);
        when(httpClient.executePostRequest(anyString(), eq(body))).thenReturn(responseOutput);
        final OpenWeatherMapClient client = new OpenWeatherMapClient(ApiConfiguration.builder()
                .httpClient(httpClient).apiKey("").build());

        //when
        final List<RoadRisk> roadRiskRecords = client
                .roadRisk()
                .byTrackPoints(Collections.singletonList(trackPoint))
                .retrieve()
                .asJava();

        //then
        verify(httpClient).executePostRequest(anyString(), eq(body));
        assertNotNull(roadRiskRecords);
        assertEquals(2, roadRiskRecords.size());
    }

}
