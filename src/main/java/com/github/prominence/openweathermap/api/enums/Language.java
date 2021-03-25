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
    /**
     * Arabic language.
     */
    ARABIC("ar"),

    /**
     * Bulgarian language.
     */
    BULGARIAN("bg"),

    /**
     * Catalan language.
     */
    CATALAN("ca"),

    /**
     * Czech language.
     */
    CZECH("cz"),

    /**
     * German language.
     */
    GERMAN("de"),

    /**
     * Greek language.
     */
    GREEK("el"),

    /**
     * English language.
     */
    ENGLISH("en"),

    /**
     * Persian language.
     */
    PERSIAN("fa"),

    /**
     * Finnish language.
     */
    FINNISH("fi"),

    /**
     * French language.
     */
    FRENCH("fr"),

    /**
     * Galician language.
     */
    GALICIAN("gl"),

    /**
     * Croatian language.
     */
    CROATIAN("hr"),

    /**
     * Hungarian language.
     */
    HUNGARIAN("hu"),

    /**
     * Italian language.
     */
    ITALIAN("it"),

    /**
     * Japanese language.
     */
    JAPANESE("ja"),

    /**
     * Korean language.
     */
    KOREAN("kr"),

    /**
     * Latvian language.
     */
    LATVIAN("la"),

    /**
     * Lithuanian language.
     */
    LITHUANIAN("lt"),

    /**
     * Macedonian language.
     */
    MACEDONIAN("mk"),

    /**
     * Dutch language.
     */
    DUTCH("nl"),

    /**
     * Polish language.
     */
    POLISH("pl"),

    /**
     * Portuguese language.
     */
    PORTUGUESE("pt"),

    /**
     * Romanian language.
     */
    ROMANIAN ("ro"),

    /**
     * Russian language.
     */
    RUSSIAN("ru"),

    /**
     * Swedish language.
     */
    SWEDISH("se"),

    /**
     * Slovak language.
     */
    SLOVAK("sk"),

    /**
     * Slovenian language.
     */
    SLOVENIAN("sl"),

    /**
     * Spanish language.
     */
    SPANISH("en"),

    /**
     * Turkish language.
     */
    TURKISH("tr"),

    /**
     * Ukranian language.
     */
    UKRANIAN("uk"),

    /**
     * Vietnamese language.
     */
    VIETNAMESE("vi"),

    /**
     * Chinese simplified language.
     */
    CHINESE_SIMPLIFIED("zh_cn"),

    /**
     * Chinese traditional language.
     */
    CHINESE_TRADITIONAL("zh_tw");

    private final String value;

    Language(String value) {
        this.value = value;
    }

    /**
     * Returns language's value.
     * @return value.
     */
    public String getValue() {
        return value;
    }
}
