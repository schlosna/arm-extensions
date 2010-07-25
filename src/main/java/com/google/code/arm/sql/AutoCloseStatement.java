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
import java.sql.Statement;

/**
 * An {@link AutoCloseable} {@link Statement}. When the automatic resource management block construct invokes
 * {@link #close()}, {@code close()} will be called on the underlying {@link Statement}.
 */
public class AutoCloseStatement extends DelegatingStatement<Statement> implements AutoCloseable, Statement {

    /**
     * Returns an {@link AutoCloseable} {@link Statement} from the given {@link Statement}
     * 
     * @param statement
     *            the Statement
     * @return the {@link AutoCloseable} {@link Statement}
     */
    public static AutoCloseStatement from(Statement statement) {
        if (statement instanceof AutoCloseStatement) {
            return (AutoCloseStatement) statement;
        }

        return new AutoCloseStatement(statement);
    }

    private AutoCloseStatement(Statement statement) {
        super(statement);
    }

    /**
     * Closes the wrapped statement and implements {@link AutoCloseable}.
     * 
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
}
