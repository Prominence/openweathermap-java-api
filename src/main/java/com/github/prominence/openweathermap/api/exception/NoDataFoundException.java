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

package com.github.prominence.openweathermap.api.exception;

/**
 * An exception that could be thrown in several cases:
 * <ul>
 *     <li>There is no data found for your query(wrong coordinates somewhere in the sea, some distant place, etc.);</li>
 *     <li>Request is malformed. An occasion to report an <a href="https://github.com/Prominence/openweathermap-java-api/issues">issue</a>;</li>
 *     <li>Any other unpredictable problems.</li>
 * </ul>
 */
public class NoDataFoundException extends RuntimeException {
    /**
     * Creates {@link NoDataFoundException} with default message.
     */
    public NoDataFoundException() {
        super("Data for provided parameters wasn't found. Please, check requested location.");
    }

    /**
     * Creates {@link NoDataFoundException} with message from another throwable.
     * @param throwable source throwable.
     */
    public NoDataFoundException(Throwable throwable) {
        super(throwable.getMessage(), throwable.getCause());
    }
}
