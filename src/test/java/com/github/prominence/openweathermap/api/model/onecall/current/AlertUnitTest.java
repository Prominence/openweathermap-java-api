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

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

    @Test
    public void getEquals() {
        final Alert first = new Alert();
        final Alert second = new Alert();

        assertEquals(first, first);
        assertNotEquals(first, null);
        assertNotEquals(first, new Object());

        final String sender = "Sender";
        final String event = "Event";
        final String description = "Description";
        final OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);

        assertEquals(first, second);

        first.setSenderName(sender);

        assertNotEquals(first, second);

        second.setSenderName(sender);

        assertEquals(first, second);

        first.setEventName(event);

        assertNotEquals(first, second);

        second.setEventName(event);

        assertEquals(first, second);

        first.setStartTime(now);

        assertNotEquals(first, second);

        second.setStartTime(now);

        assertEquals(first, second);

        first.setEndTime(now);

        assertNotEquals(first, second);

        second.setEndTime(now);

        assertEquals(first, second);

        first.setDescription(description);

        assertNotEquals(first, second);

        second.setDescription(description);

        assertEquals(first, second);
    }

    @Test
    public void getHashCode() {
        final Alert event1 = new Alert("Sender", "Event1", LocalDateTime.now(), LocalDateTime.now().plus(2, ChronoUnit.HOURS), "Description1", Collections.emptyList());
        final Alert event2 = new Alert("Sender", "Event2", LocalDateTime.now(), LocalDateTime.now().plus(2, ChronoUnit.HOURS), "Description2", Collections.emptyList());

        assertEquals(event1.hashCode(), event1.hashCode());
        assertNotEquals(event1.hashCode(), event2.hashCode());
    }

    @Test
    public void getToString() {
        final Alert alert = new Alert("Sender", "Event", LocalDateTime.now(), LocalDateTime.now().plus(2, ChronoUnit.HOURS), "Description", Collections.emptyList());

        assertNotNull(alert.toString());
        assertNotEquals("", alert.toString());
    }
}
