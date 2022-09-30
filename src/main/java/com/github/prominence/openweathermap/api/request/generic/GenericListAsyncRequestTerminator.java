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

package com.github.prominence.openweathermap.api.request.generic;

import com.github.prominence.openweathermap.api.enums.UnitSystem;
import lombok.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GenericListAsyncRequestTerminator<T, I extends T> {
    protected final GenericListRequestTerminator<T, I> sync;

    public GenericListAsyncRequestTerminator(@NonNull GenericListRequestTerminator<T, I> sync) {
        this.sync = sync;
    }

    public CompletableFuture<List<T>> asJava() {
        return CompletableFuture.supplyAsync(sync::asJava);
    }

    public CompletableFuture<String> asJSON(final UnitSystem unitSystem) {
        return CompletableFuture.supplyAsync(() -> sync.asJSON(unitSystem));
    }

    public CompletableFuture<String> asXML(final UnitSystem unitSystem) {
        return CompletableFuture.supplyAsync(() -> sync.asXML(unitSystem));
    }

    public CompletableFuture<String> asHTML(final UnitSystem unitSystem) {
        return CompletableFuture.supplyAsync(() -> sync.asHTML(unitSystem));
    }
}
