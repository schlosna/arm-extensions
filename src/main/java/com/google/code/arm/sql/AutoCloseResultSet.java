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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;

public class AutoCloseResultSet<S extends ResultSet> extends DelegatingResultSet<S> implements AutoCloseable {

    public static <S extends ResultSet> AutoCloseResultSet<S> from(S delegate) {
        if (delegate instanceof AutoCloseResultSet) {
            @SuppressWarnings("unchecked") AutoCloseResultSet<S> resultSet = (AutoCloseResultSet<S>) delegate;
            return resultSet;
        }

        return new AutoCloseResultSet<S>(delegate);
    }

    private AutoCloseResultSet(S delegate) {
        super(delegate);
    }

    @Override
    public void close() throws SQLException {
        super.close();
    }

    @Override
    public AutoCloseArray<Array> getArray(int columnIndex) throws SQLException {
        Array array = super.getArray(columnIndex);
        return (array == null) ? null : AutoCloseArray.from(array);
    }

    @Override
    public AutoCloseArray<Array> getArray(String columnLabel) throws SQLException {
        Array array = super.getArray(columnLabel);
        return (array == null) ? null : AutoCloseArray.from(array);
    }

    @Override
    public AutoCloseBlob<Blob> getBlob(int columnIndex) throws SQLException {
        Blob blob = super.getBlob(columnIndex);
        return (blob == null) ? null : AutoCloseBlob.from(blob);
    }

    @Override
    public AutoCloseBlob<Blob> getBlob(String columnLabel) throws SQLException {
        Blob blob = super.getBlob(columnLabel);
        return (blob == null) ? null : AutoCloseBlob.from(blob);
    }

    @Override
    public AutoCloseClob<Clob> getClob(int columnIndex) throws SQLException {
        Clob clob = super.getClob(columnIndex);
        return (clob == null) ? null : AutoCloseClob.from(clob);
    }

    @Override
    public AutoCloseClob<Clob> getClob(String columnLabel) throws SQLException {
        Clob clob = super.getClob(columnLabel);
        return (clob == null) ? null : AutoCloseClob.from(clob);
    }

    @Override
    public AutoCloseNClob<NClob> getNClob(int columnIndex) throws SQLException {
        NClob nClob = super.getNClob(columnIndex);
        return (nClob == null) ? null : AutoCloseNClob.from(nClob);
    }

    @Override
    public AutoCloseNClob<NClob> getNClob(String columnLabel) throws SQLException {
        NClob nClob = super.getNClob(columnLabel);
        return (nClob == null) ? null : AutoCloseNClob.from(nClob);
    }

    @Override
    public AutoCloseSQLXML<SQLXML> getSQLXML(int columnIndex) throws SQLException {
        SQLXML sqlxml = super.getSQLXML(columnIndex);
        return (sqlxml == null) ? null : AutoCloseSQLXML.from(sqlxml);
    }

    @Override
    public AutoCloseSQLXML<SQLXML> getSQLXML(String columnLabel) throws SQLException {
        SQLXML sqlxml = super.getSQLXML(columnLabel);
        return (sqlxml == null) ? null : AutoCloseSQLXML.from(sqlxml);
    }
}
