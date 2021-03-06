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

import java.sql.Blob;
import java.sql.SQLException;

/**
 * An {@link AutoCloseable} {@link Blob}. When the automatic resource management block construct invokes
 * {@link #close()}, {@link #free()} will be called on the underlying {@link Blob}.
 */
public class AutoCloseBlob extends DelegatingBlob<Blob> implements AutoCloseable, Blob {

    /**
     * Returns an {@link AutoCloseable} {@link Blob} from the given {@link Blob}
     * 
     * @param blob
     *            the Blob
     * @return the {@link AutoCloseable} {@link Blob}
     */
    public static AutoCloseBlob from(Blob blob) {
        if (blob instanceof AutoCloseBlob) {
            return (AutoCloseBlob) blob;
        }

        return new AutoCloseBlob(blob);
    }

    private AutoCloseBlob(Blob blob) {
        super(blob);
    }

    /**
     * Implements {@link AutoCloseable} by invoking {@link #free()} on this instance
     * 
     * @see java.lang.AutoCloseable#close()
     */
    @Override
    public void close() throws SQLException {
        free();
    }

}
