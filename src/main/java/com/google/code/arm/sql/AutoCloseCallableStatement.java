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
import java.sql.Connection;
import java.sql.NClob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;

public class AutoCloseCallableStatement<S extends CallableStatement> extends DelegatingCallableStatement<S> implements CallableStatement, AutoCloseable {

    public static <S extends CallableStatement> AutoCloseCallableStatement<S> from(S delegate) {
        if (delegate instanceof AutoCloseCallableStatement) {
            @SuppressWarnings("unchecked") AutoCloseCallableStatement<S> stmt = (AutoCloseCallableStatement<S>) delegate;
            return stmt;

        }
        return new AutoCloseCallableStatement<S>(delegate);
    }

    private AutoCloseCallableStatement(S delegate) {
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

    // CallableStatement
    @Override
    public AutoCloseArray<Array> getArray(int parameterIndex) throws SQLException {
        Array array = super.getArray(parameterIndex);
        return (array == null) ? null : AutoCloseArray.from(array);
    }

    // CallableStatement
    @Override
    public AutoCloseArray<Array> getArray(String parameterName) throws SQLException {
        Array array = super.getArray(parameterName);
        return (array == null) ? null : AutoCloseArray.from(array);
    }

    // CallableStatement
    @Override
    public AutoCloseBlob<Blob> getBlob(int parameterIndex) throws SQLException {
        Blob blob = super.getBlob(parameterIndex);
        return (blob == null) ? null : AutoCloseBlob.from(blob);
    }

    // CallableStatement
    @Override
    public AutoCloseBlob<Blob> getBlob(String parameterName) throws SQLException {
        Blob blob = super.getBlob(parameterName);
        return (blob == null) ? null : AutoCloseBlob.from(blob);
    }

    // CallableStatement
    @Override
    public AutoCloseClob<Clob> getClob(int parameterIndex) throws SQLException {
        Clob clob = super.getClob(parameterIndex);
        return (clob == null) ? null : AutoCloseClob.from(clob);
    }

    // CallableStatement
    @Override
    public AutoCloseClob<Clob> getClob(String parameterName) throws SQLException {
        Clob clob = super.getClob(parameterName);
        return (clob == null) ? null : AutoCloseClob.from(clob);
    }

    // CallableStatement
    @Override
    public AutoCloseNClob<NClob> getNClob(int parameterIndex) throws SQLException {
        NClob nClob = super.getNClob(parameterIndex);
        return (nClob == null) ? null : AutoCloseNClob.from(nClob);
    }

    // CallableStatement
    @Override
    public AutoCloseNClob<NClob> getNClob(String parameterName) throws SQLException {
        NClob nClob = super.getNClob(parameterName);
        return (nClob == null) ? null : AutoCloseNClob.from(nClob);
    }

    // CallableStatement
    @Override
    public AutoCloseSQLXML<SQLXML> getSQLXML(int parameterIndex) throws SQLException {
        SQLXML sqlxml = super.getSQLXML(parameterIndex);
        return (sqlxml == null) ? null : AutoCloseSQLXML.from(sqlxml);
    }

    // CallableStatement
    @Override
    public AutoCloseSQLXML<SQLXML> getSQLXML(String parameterName) throws SQLException {
        SQLXML sqlxml = super.getSQLXML(parameterName);
        return (sqlxml == null) ? null : AutoCloseSQLXML.from(sqlxml);
    }
}
