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
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;

/**
 * An {@link AutoCloseable} {@link CallableStatement}. When the automatic resource management block construct invokes
 * {@link #close()}, {@code close()} will be called on the underlying {@link CallableStatement}.
 */
public class AutoCloseCallableStatement extends DelegatingCallableStatement<CallableStatement> implements AutoCloseable, CallableStatement {

    /**
     * Returns an {@link AutoCloseable} {@link CallableStatement} from the given {@link CallableStatement}
     * 
     * @param statement
     *            the CallableStatement
     * @return the {@link AutoCloseable} {@link CallableStatement}
     */
    public static AutoCloseCallableStatement from(CallableStatement statement) {
        if (statement instanceof AutoCloseCallableStatement) {
            return (AutoCloseCallableStatement) statement;

        }
        return new AutoCloseCallableStatement(statement);
    }

    private AutoCloseCallableStatement(CallableStatement statement) {
        super(statement);
    }

    /**
     * Implements {@link AutoCloseable}.
     * 
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

    /**
     * @see CallableStatement#getArray(int)
     */
    @Override
    public AutoCloseArray getArray(int parameterIndex) throws SQLException {
        Array array = super.getArray(parameterIndex);
        return (array == null) ? null : AutoCloseArray.from(array);
    }

    /**
     * @see CallableStatement#getArray(java.lang.String)
     */
    @Override
    public AutoCloseArray getArray(String parameterName) throws SQLException {
        Array array = super.getArray(parameterName);
        return (array == null) ? null : AutoCloseArray.from(array);
    }

    /**
     * @see CallableStatement#getBlob(int)
     */
    @Override
    public AutoCloseBlob getBlob(int parameterIndex) throws SQLException {
        Blob blob = super.getBlob(parameterIndex);
        return (blob == null) ? null : AutoCloseBlob.from(blob);
    }

    /**
     * @see CallableStatement#getBlob(java.lang.String)
     */
    @Override
    public AutoCloseBlob getBlob(String parameterName) throws SQLException {
        Blob blob = super.getBlob(parameterName);
        return (blob == null) ? null : AutoCloseBlob.from(blob);
    }

    /**
     * @see CallableStatement#getClob(int)
     */
    @Override
    public AutoCloseClob getClob(int parameterIndex) throws SQLException {
        Clob clob = super.getClob(parameterIndex);
        return (clob == null) ? null : AutoCloseClob.from(clob);
    }

    // CallableStatement
    /**
     * @see CallableStatement#getClob(java.lang.String)
     */
    @Override
    public AutoCloseClob getClob(String parameterName) throws SQLException {
        Clob clob = super.getClob(parameterName);
        return (clob == null) ? null : AutoCloseClob.from(clob);
    }

    /**
     * @see CallableStatement#getNClob(int)
     */
    @Override
    public AutoCloseNClob getNClob(int parameterIndex) throws SQLException {
        NClob nClob = super.getNClob(parameterIndex);
        return (nClob == null) ? null : AutoCloseNClob.from(nClob);
    }

    /**
     * @see CallableStatement#getNClob(java.lang.String)
     */
    @Override
    public AutoCloseNClob getNClob(String parameterName) throws SQLException {
        NClob nClob = super.getNClob(parameterName);
        return (nClob == null) ? null : AutoCloseNClob.from(nClob);
    }

    /**
     * @see CallableStatement#getSQLXML(int)
     */
    @Override
    public AutoCloseSQLXML getSQLXML(int parameterIndex) throws SQLException {
        SQLXML sqlxml = super.getSQLXML(parameterIndex);
        return (sqlxml == null) ? null : AutoCloseSQLXML.from(sqlxml);
    }

    /**
     * @see CallableStatement#getSQLXML(java.lang.String)
     */
    @Override
    public AutoCloseSQLXML getSQLXML(String parameterName) throws SQLException {
        SQLXML sqlxml = super.getSQLXML(parameterName);
        return (sqlxml == null) ? null : AutoCloseSQLXML.from(sqlxml);
    }
}
