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

package com.github.prominence.openweathermap.api.model.onecall.current;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlertUnitTest {
    @Test
    public void getSenderName() {
        final Alert alert = new Alert();
        alert.setSenderName("Sender");

        assertEquals("Sender", alert.getSenderName());
    }

    @Test
    public void getEventName() {
        final Alert alert = new Alert();
        alert.setEventName("Event");

        assertEquals("Event", alert.getEventName());
    }

    @Test
    public void getStartTime() {
        final Alert alert = new Alert();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        alert.setStartTime(now);

        assertEquals(now, alert.getStartTime());
    }

    @Test
    public void getEndTime() {
        final Alert alert = new Alert();
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        alert.setEndTime(now);

        assertEquals(now, alert.getEndTime());
    }

    @Test
    public void getDescription() {
        final Alert alert = new Alert();
        alert.setDescription("Description");

        assertEquals("Description", alert.getDescription());
    }

}
