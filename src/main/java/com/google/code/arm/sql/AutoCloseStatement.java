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

public class AutoCloseStatement extends DelegatingStatement<Statement> implements AutoCloseable {

    public static AutoCloseStatement from(Statement delegate) {
        if (delegate instanceof AutoCloseStatement) {
            return (AutoCloseStatement) delegate;
        }

        return new AutoCloseStatement(delegate);
    }

    private AutoCloseStatement(Statement delegate) {
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

    /**
     * @see com.google.code.arm.sql.DelegatingStatement#executeQuery(java.lang.String)
     */
    @Override
    public AutoCloseResultSet executeQuery(String sql) throws SQLException {
        return AutoCloseResultSet.from(super.executeQuery(sql));
    }

    /**
     * @see com.google.code.arm.sql.DelegatingStatement#getConnection()
     */
    @Override
    public AutoCloseConnection getConnection() throws SQLException {
        return AutoCloseConnection.from(super.getConnection());
    }

    /**
     * @see com.google.code.arm.sql.DelegatingStatement#getGeneratedKeys()
     */
    @Override
    public AutoCloseResultSet getGeneratedKeys() throws SQLException {
        return AutoCloseResultSet.from(super.getGeneratedKeys());
    }

    /**
     * @see com.google.code.arm.sql.DelegatingStatement#getResultSet()
     */
    @Override
    public AutoCloseResultSet getResultSet() throws SQLException {
        return AutoCloseResultSet.from(super.getResultSet());
    }
}
