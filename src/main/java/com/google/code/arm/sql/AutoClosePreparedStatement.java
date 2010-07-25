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

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * An {@link AutoCloseable} {@link PreparedStatement}. When the automatic resource management block construct invokes
 * {@link #close()}, {@code close()} will be called on the underlying {@link PreparedStatement}.
 */
public class AutoClosePreparedStatement extends DelegatingPreparedStatement<PreparedStatement> implements AutoCloseable, PreparedStatement {

    /**
     * Returns an {@link AutoCloseable} {@link PreparedStatement} from the given {@link PreparedStatement}
     * 
     * @param statement
     *            the PreparedStatement
     * @return the {@link AutoCloseable} {@link PreparedStatement}
     */
    public static AutoClosePreparedStatement from(PreparedStatement statement) {
        if (statement instanceof AutoClosePreparedStatement) {
            return (AutoClosePreparedStatement) statement;
        }

        return new AutoClosePreparedStatement(statement);
    }

    private AutoClosePreparedStatement(PreparedStatement statement) {
        super(statement);
    }

    /**
     * @see AutoCloseable#close()
     * @see Statement#close()
     */
    @Override
    public void close() throws SQLException {
        super.close();
    }

    /**
     * @see Statement#executeQuery(java.lang.String)
     */
    @Override
    public AutoCloseResultSet executeQuery(String sql) throws SQLException {
        return AutoCloseResultSet.from(super.executeQuery(sql));
    }

    /**
     * @see Statement#getConnection()
     */
    @Override
    public AutoCloseConnection getConnection() throws SQLException {
        return AutoCloseConnection.from(super.getConnection());
    }

    /**
     * @see Statement#getGeneratedKeys()
     */
    @Override
    public AutoCloseResultSet getGeneratedKeys() throws SQLException {
        return AutoCloseResultSet.from(super.getGeneratedKeys());
    }

    /**
     * @see Statement#getResultSet()
     */
    @Override
    public AutoCloseResultSet getResultSet() throws SQLException {
        return AutoCloseResultSet.from(super.getResultSet());
    }

    /**
     * @see PreparedStatement#executeQuery()
     */
    @Override
    public AutoCloseResultSet executeQuery() throws SQLException {
        return AutoCloseResultSet.from(super.executeQuery());
    }
}
