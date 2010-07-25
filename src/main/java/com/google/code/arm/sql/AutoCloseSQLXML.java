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

import java.sql.SQLException;
import java.sql.SQLXML;

/**
 * An {@link AutoCloseable} {@link SQLXML}. When the automatic resource management block construct invokes
 * {@link #close()}, {@link #free()} will be called on the underlying {@link SQLXML}.
 */
public class AutoCloseSQLXML extends DelegatingSQLXML<SQLXML> implements AutoCloseable {

    /**
     * Returns an {@link AutoCloseable} {@link SQLXML} from the given {@link SQLXML}
     * 
     * @param sqlxml
     *            the SQLXML
     * @return the {@link AutoCloseable} {@link SQLXML}
     */
    public static AutoCloseSQLXML from(SQLXML sqlxml) {
        if (sqlxml instanceof AutoCloseSQLXML) {
            return (AutoCloseSQLXML) sqlxml;
        }

        return new AutoCloseSQLXML(sqlxml);
    }

    private AutoCloseSQLXML(SQLXML sqlxml) {
        super(sqlxml);
    }

    /**
     * Implements {@link AutoCloseable} by invoking {@link #free()} on the instance
     * 
     * @see java.lang.AutoCloseable#close()
     */
    @Override
    public void close() throws SQLException {
        free();
    }

}
