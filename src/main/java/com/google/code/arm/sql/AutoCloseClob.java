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

import java.sql.Clob;
import java.sql.SQLException;

public class AutoCloseClob extends DelegatingClob<Clob> implements Clob, AutoCloseable {

    public static AutoCloseClob from(Clob delegate) {
        if (delegate instanceof AutoCloseClob) {
            return (AutoCloseClob) delegate;
        }

        return new AutoCloseClob(delegate);
    }

    private AutoCloseClob(Clob delegate) {
        super(delegate);
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
