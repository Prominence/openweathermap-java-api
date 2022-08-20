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

import com.github.prominence.openweathermap.api.enums.MoonType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoonPhaseUnitTest {

    @Test
    public void getValue() {
        final MoonPhase moonPhase = new MoonPhase(0.67);

        assertEquals(0.67, moonPhase.getValue(), 0.000001);
    }

    @Test
    public void getType() {
        final MoonPhase newMoon1 = new MoonPhase(0d);
        assertEquals(MoonType.NEW_MOON, newMoon1.getType());

        final MoonPhase newMoon2 = new MoonPhase(1d);
        assertEquals(MoonType.NEW_MOON, newMoon2.getType());

        final MoonPhase firstQuarterMoon = new MoonPhase(0.25d);
        assertEquals(MoonType.FIRST_QUARTER_MOON, firstQuarterMoon.getType());

        final MoonPhase fullMoon = new MoonPhase(0.5d);
        assertEquals(MoonType.FULL_MOON, fullMoon.getType());

        final MoonPhase lastQuarterMoon = new MoonPhase(0.75d);
        assertEquals(MoonType.LAST_QUARTER_MOON, lastQuarterMoon.getType());

        final MoonPhase waxingCrescentMoon = new MoonPhase(0.1d);
        assertEquals(MoonType.WAXING_CRESCENT, waxingCrescentMoon.getType());

        final MoonPhase waxingGibbousMoon = new MoonPhase(0.4d);
        assertEquals(MoonType.WAXING_GIBBOUS, waxingGibbousMoon.getType());

        final MoonPhase waningGibbousMoon = new MoonPhase(0.623d);
        assertEquals(MoonType.WANING_GIBBOUS, waningGibbousMoon.getType());

        final MoonPhase waningCrescentMoon = new MoonPhase(0.9999d);
        assertEquals(MoonType.WANING_CRESCENT, waningCrescentMoon.getType());
    }
}
