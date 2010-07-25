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
import java.sql.Blob;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLXML;

/**
 * A {@link SQLInput} that provides {@link AutoCloseable} instances of {@link Array}, {@link Blob}, {@link Clob},
 * {@link NClob}, and {@link SQLXML}
 */
public class AutoCloseSQLInput extends DelegatingSQLInput<SQLInput> implements SQLInput {

    /**
     * Returns a wrapped {@link SQLInput} that provides {@link AutoCloseable} instances of {@link Array}, {@link Blob},
     * {@link Clob}, {@link NClob}, and {@link SQLXML} from the given {@link SQLInput}.
     * 
     * @param sqlInput
     *            the SQLInput
     * @return the {@link AutoCloseable} {@link SQLInput}
     */
    public static AutoCloseSQLInput from(SQLInput sqlInput) {
        if (sqlInput instanceof AutoCloseSQLInput) {
            return (AutoCloseSQLInput) sqlInput;
        }

        return new AutoCloseSQLInput(sqlInput);
    }

    private AutoCloseSQLInput(SQLInput sqlInput) {
        super(sqlInput);
    }

    @Override
    public Array readArray() throws SQLException {
        return AutoCloseArray.from(super.readArray());
    }

    @Override
    public Blob readBlob() throws SQLException {
        return AutoCloseBlob.from(super.readBlob());
    }

    @Override
    public Clob readClob() throws SQLException {
        return AutoCloseClob.from(super.readClob());
    }

    @Override
    public NClob readNClob() throws SQLException {
        return AutoCloseNClob.from(super.readNClob());
    }

    @Override
    public SQLXML readSQLXML() throws SQLException {
        return AutoCloseSQLXML.from(super.readSQLXML());
    }
}
