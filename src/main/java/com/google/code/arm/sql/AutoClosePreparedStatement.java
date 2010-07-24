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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoClosePreparedStatement<S extends PreparedStatement> extends DelegatingPreparedStatement<S> implements PreparedStatement, AutoCloseable {

    public static <S extends PreparedStatement> AutoClosePreparedStatement<S> from(S delegate) {
        if (delegate instanceof AutoClosePreparedStatement) {
            @SuppressWarnings("unchecked") AutoClosePreparedStatement<S> stmt = (AutoClosePreparedStatement<S>) delegate;
            return stmt;
        }

        return new AutoClosePreparedStatement<S>(delegate);
    }

    private AutoClosePreparedStatement(S delegate) {
        super(delegate);
    }

    // AutoCloseable
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

    // PreparedStatement
    @Override
    public AutoCloseResultSet<ResultSet> executeQuery() throws SQLException {
        return AutoCloseResultSet.from(super.executeQuery());
    }
}
