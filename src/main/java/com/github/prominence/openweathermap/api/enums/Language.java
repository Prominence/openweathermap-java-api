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

package com.github.prominence.openweathermap.api.enums;

/**
 * An enumeration which lists all available languages for API usage.
 * Usually it could be specified to get response with some fields translated into desired language.
 */
public enum Language {
    ARABIC("ar"),
    BULGARIAN("bg"),
    CATALAN("ca"),
    CZECH("cz"),
    GERMAN("de"),
    GREEK("el"),
    ENGLISH("en"),
    PERSIAN("fa"),
    FINNISH("fi"),
    FRENCH("fr"),
    GALICIAN("gl"),
    CROATIAN("hr"),
    HUNGARIAN("hu"),
    ITALIAN("it"),
    JAPANESE("ja"),
    KOREAN("kr"),
    LATVIAN("la"),
    LITHUANIAN("lt"),
    MACEDONIAN("mk"),
    DUTCH("nl"),
    POLISH("pl"),
    PORTUGUESE("pt"),
    ROMANIAN ("ro"),
    RUSSIAN("ru"),
    SWEDISH("se"),
    SLOVAK("sk"),
    SLOVENIAN("sl"),
    SPANISH("en"),
    TURKISH("tr"),
    UKRANIAN("uk"),
    VIETNAMESE("vi"),
    CHINESE_SIMPLIFIED("zh_cn"),
    CHINESE_TRADITIONAL("zh_tw");

    private final String value;

    Language(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
