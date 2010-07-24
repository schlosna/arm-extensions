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
import java.sql.SQLException;
import java.sql.SQLXML;

public class AutoCloseCallableStatement extends DelegatingCallableStatement<CallableStatement> implements CallableStatement, AutoCloseable {

    public static AutoCloseCallableStatement from(CallableStatement delegate) {
        if (delegate instanceof AutoCloseCallableStatement) {
            return (AutoCloseCallableStatement) delegate;

        }
        return new AutoCloseCallableStatement(delegate);
    }

    private AutoCloseCallableStatement(CallableStatement delegate) {
        super(delegate);
    }

    /**
     * Implements {@link AutoCloseable}.
     * 
     * @see AutoCloseable#close()
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

    /**
     * @see com.google.code.arm.sql.DelegatingPreparedStatement#executeQuery()
     */
    @Override
    public AutoCloseResultSet executeQuery() throws SQLException {
        return AutoCloseResultSet.from(super.executeQuery());
    }

    /**
     * @see com.google.code.arm.sql.DelegatingCallableStatement#getArray(int)
     */
    @Override
    public AutoCloseArray getArray(int parameterIndex) throws SQLException {
        Array array = super.getArray(parameterIndex);
        return (array == null) ? null : AutoCloseArray.from(array);
    }

    /**
     * @see com.google.code.arm.sql.DelegatingCallableStatement#getArray(java.lang.String)
     */
    @Override
    public AutoCloseArray getArray(String parameterName) throws SQLException {
        Array array = super.getArray(parameterName);
        return (array == null) ? null : AutoCloseArray.from(array);
    }

    /**
     * @see com.google.code.arm.sql.DelegatingCallableStatement#getBlob(int)
     */
    @Override
    public AutoCloseBlob getBlob(int parameterIndex) throws SQLException {
        Blob blob = super.getBlob(parameterIndex);
        return (blob == null) ? null : AutoCloseBlob.from(blob);
    }

    /**
     * @see com.google.code.arm.sql.DelegatingCallableStatement#getBlob(java.lang.String)
     */
    @Override
    public AutoCloseBlob getBlob(String parameterName) throws SQLException {
        Blob blob = super.getBlob(parameterName);
        return (blob == null) ? null : AutoCloseBlob.from(blob);
    }

    /**
     * @see com.google.code.arm.sql.DelegatingCallableStatement#getClob(int)
     */
    @Override
    public AutoCloseClob getClob(int parameterIndex) throws SQLException {
        Clob clob = super.getClob(parameterIndex);
        return (clob == null) ? null : AutoCloseClob.from(clob);
    }

    // CallableStatement
    /**
     * @see com.google.code.arm.sql.DelegatingCallableStatement#getClob(java.lang.String)
     */
    @Override
    public AutoCloseClob getClob(String parameterName) throws SQLException {
        Clob clob = super.getClob(parameterName);
        return (clob == null) ? null : AutoCloseClob.from(clob);
    }

    /**
     * @see com.google.code.arm.sql.DelegatingCallableStatement#getNClob(int)
     */
    @Override
    public AutoCloseNClob getNClob(int parameterIndex) throws SQLException {
        NClob nClob = super.getNClob(parameterIndex);
        return (nClob == null) ? null : AutoCloseNClob.from(nClob);
    }

    /**
     * @see com.google.code.arm.sql.DelegatingCallableStatement#getNClob(java.lang.String)
     */
    @Override
    public AutoCloseNClob getNClob(String parameterName) throws SQLException {
        NClob nClob = super.getNClob(parameterName);
        return (nClob == null) ? null : AutoCloseNClob.from(nClob);
    }

    /**
     * @see com.google.code.arm.sql.DelegatingCallableStatement#getSQLXML(int)
     */
    @Override
    public AutoCloseSQLXML getSQLXML(int parameterIndex) throws SQLException {
        SQLXML sqlxml = super.getSQLXML(parameterIndex);
        return (sqlxml == null) ? null : AutoCloseSQLXML.from(sqlxml);
    }

    /**
     * @see com.google.code.arm.sql.DelegatingCallableStatement#getSQLXML(java.lang.String)
     */
    @Override
    public AutoCloseSQLXML getSQLXML(String parameterName) throws SQLException {
        SQLXML sqlxml = super.getSQLXML(parameterName);
        return (sqlxml == null) ? null : AutoCloseSQLXML.from(sqlxml);
    }
}
