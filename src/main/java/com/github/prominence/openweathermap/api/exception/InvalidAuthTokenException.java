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

package com.github.prominence.openweathermap.api.exception;

/**
 * An exception that could be thrown in case of your API key is invalid or your subscription plan does not cover requested functionality.
 * Subscription plans information you can find <a href="https://openweathermap.org/price">here</a>.
 * API Keys could be checked in <a href="https://home.openweathermap.org/api_keys/">your profile</a>.
 */
public class InvalidAuthTokenException extends RuntimeException {
    /**
     * Creates {@link InvalidAuthTokenException} exception with default message.
     */
    public InvalidAuthTokenException() {
        super("Authentication token wasn't set or requested functionality is not permitted for your subscription plan. Please, check https://home.openweathermap.org/api_keys/ and https://openweathermap.org/price.");
    }
}
