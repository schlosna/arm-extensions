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

package com.google.code.arm.sql;

import java.sql.Array;
import java.sql.SQLException;

/**
 * An {@link AutoCloseable} {@link Array}. When the automatic resource management block construct invokes
 * {@link #close()}, {@link #free()} will be called on the underlying {@link Array}.
 */
public class AutoCloseArray extends DelegatingArray<Array> implements AutoCloseable, Array {

    /**
     * Returns an {@link AutoCloseable} {@link Array} from the given {@link Array}
     * 
     * @param array
     *            the Array
     * @return the {@link AutoCloseable} {@link Array}
     */
    public static AutoCloseArray from(Array array) {
        if (array instanceof AutoCloseArray) {
            return (AutoCloseArray) array;
        }

        return new AutoCloseArray(array);
    }

    /**
     * Instantiates a new auto close array.
     * 
     * @param array
     *            the array
     */
    private AutoCloseArray(Array array) {
        super(array);
    }

    /**
     * Implements {@link AutoCloseable} by invoking {@link #free()} on this instance. {@inheritDoc}
     * 
     * @see java.lang.AutoCloseable#close()
     */
    @Override
    public void close() throws SQLException {
        free();
    }

}
