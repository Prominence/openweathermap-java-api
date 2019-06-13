/*
 * Copyright (c) 2019 Alexey Zinchenko
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

package com.github.prominence.openweathermap.api.utils;

import com.github.prominence.openweathermap.api.enums.TimeFrame;

import java.text.SimpleDateFormat;
import java.util.Date;

public final class TimeFrameUtils {

    private TimeFrameUtils() {

    }

    /*
    2016-01-02T15:04:05Z
    searches between 2016-01-02T15:04:05Z and 2016-01-02T15:04:05.9999Z
    2016-01-02T15:04Z
    searches between 2016-01-02T15:04:00Z and 2016-01-02T15:04:59.9999Z
    2016-01-02T15Z
    searches between 2016-01-02T15:00:00Z and 2016-01-02T15:59:59.9999Z
    2016-01-02Z
    searches between 2016-01-02T00:00:00Z and 2016-01-02T23:59:59.9999Z
    2016-01Z
    searches between 2016-01-01T00:00:00Z and 2016-12-31T23:59:59.9999Z
    2016Z
    searches between 2016-01-01T00:00:00Z and 2016-12-31T23:59:99.9999Z
    */

    public static String formatDate(Date date, TimeFrame timeFrame) {

        SimpleDateFormat formatter;

        switch (timeFrame) {
            case YEAR:
                formatter = new SimpleDateFormat("yyyy'Z'");
                break;
            case MONTH:
                formatter = new SimpleDateFormat("yyyy-MM'Z'");
                break;
            case DAY:
                formatter = new SimpleDateFormat("yyyy-MM-dd'Z'");
                break;
            case HOUR:
                formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH'Z'");
                break;
            case MINUTE:
                formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
                break;
            case SECOND:
                formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                break;
            default:
                formatter = new SimpleDateFormat("yyyy-MM'Z'");
        }

        return formatter.format(date);
    }

}
