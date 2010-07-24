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

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AutoCloseStatement<S extends Statement> extends DelegatingStatement<S> implements AutoCloseable {

    public static <S extends Statement> AutoCloseStatement<S> from(S delegate) {
        if (delegate instanceof AutoCloseStatement) {
            @SuppressWarnings("unchecked") AutoCloseStatement<S> stmt = (AutoCloseStatement<S>) delegate;
            return stmt;
        }

        return new AutoCloseStatement<S>(delegate);
    }

    private AutoCloseStatement(S delegate) {
        super(delegate);
    }

    /**
     * Closes the delegate statement and implements {@link AutoCloseable}.
     * 
     * @see com.google.code.arm.sql.DelegatingStatement#close()
     */
    @Override
    public void close() throws SQLException {
        super.close();
    }

    // Statement
    @Override
    public AutoCloseResultSet<ResultSet> executeQuery(String sql) throws SQLException {
        return AutoCloseResultSet.from(super.executeQuery(sql));
    }

    // Statement
    @Override
    public AutoCloseConnection<Connection> getConnection() throws SQLException {
        return AutoCloseConnection.from(super.getConnection());
    }

    // Statement
    @Override
    public AutoCloseResultSet<ResultSet> getGeneratedKeys() throws SQLException {
        return AutoCloseResultSet.from(super.getGeneratedKeys());
    }

    // Statement
    @Override
    public AutoCloseResultSet<ResultSet> getResultSet() throws SQLException {
        return AutoCloseResultSet.from(super.getResultSet());
    }
}
