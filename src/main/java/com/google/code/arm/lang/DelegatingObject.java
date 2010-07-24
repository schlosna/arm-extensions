/*
 * Copyright (C) 2010 David Schlosnagle
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.code.arm.lang;

/**
 * This immutable class provides the ability to wrap an instance of {@code T} for delegation. This class only delegates
 * {@link #toString()}, subclasses may override {@link #equals(Object)} and {@link #hashCode()} to conform to their
 * respective contracts.
 * 
 * @param <T>
 *            The type of delegate {@link Object}
 */
public abstract class DelegatingObject<T> {

    private final T delegate;

    public DelegatingObject(T delegate) {
        if (delegate == null) {
            throw new NullPointerException("Delgate cannot be null");
        }
        this.delegate = delegate;
    }

    /**
     * Returns a string representation of the delegate object from it's {@link #toString()} method.
     */
    @Override
    public String toString() {
        return delegate().toString();
    }

    /**
     * Returns the delegate instance
     * 
     * @return the delegate
     */
    public T delegate() {
        return this.delegate;
    }
}
