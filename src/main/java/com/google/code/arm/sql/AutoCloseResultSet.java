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

public class AutoCloseResultSet extends DelegatingResultSet<ResultSet> implements AutoCloseable {

    public static AutoCloseResultSet from(ResultSet delegate) {
        if (delegate instanceof AutoCloseResultSet) {
            return (AutoCloseResultSet) delegate;
        }

        return new AutoCloseResultSet(delegate);
    }

    private AutoCloseResultSet(ResultSet delegate) {
        super(delegate);
    }

    /**
     * Implements {@link AutoCloseable}
     * 
     * @see AutoCloseable#close()
     * @see com.google.code.arm.sql.DelegatingResultSet#close()
     */
    @Override
    public void close() throws SQLException {
        super.close();
    }

    /**
     * @see com.google.code.arm.sql.DelegatingResultSet#getArray(int)
     */
    @Override
    public AutoCloseArray getArray(int columnIndex) throws SQLException {
        Array array = super.getArray(columnIndex);
        return (array == null) ? null : AutoCloseArray.from(array);
    }

    /**
     * @see com.google.code.arm.sql.DelegatingResultSet#getArray(java.lang.String)
     */
    @Override
    public AutoCloseArray getArray(String columnLabel) throws SQLException {
        Array array = super.getArray(columnLabel);
        return (array == null) ? null : AutoCloseArray.from(array);
    }

    /**
     * @see com.google.code.arm.sql.DelegatingResultSet#getBlob(int)
     */
    @Override
    public AutoCloseBlob getBlob(int columnIndex) throws SQLException {
        Blob blob = super.getBlob(columnIndex);
        return (blob == null) ? null : AutoCloseBlob.from(blob);
    }

    /**
     * @see com.google.code.arm.sql.DelegatingResultSet#getBlob(java.lang.String)
     */
    @Override
    public AutoCloseBlob getBlob(String columnLabel) throws SQLException {
        Blob blob = super.getBlob(columnLabel);
        return (blob == null) ? null : AutoCloseBlob.from(blob);
    }

    /**
     * @see com.google.code.arm.sql.DelegatingResultSet#getClob(int)
     */
    @Override
    public AutoCloseClob getClob(int columnIndex) throws SQLException {
        Clob clob = super.getClob(columnIndex);
        return (clob == null) ? null : AutoCloseClob.from(clob);
    }

    /**
     * @see com.google.code.arm.sql.DelegatingResultSet#getClob(java.lang.String)
     */
    @Override
    public AutoCloseClob getClob(String columnLabel) throws SQLException {
        Clob clob = super.getClob(columnLabel);
        return (clob == null) ? null : AutoCloseClob.from(clob);
    }

    /**
     * @see com.google.code.arm.sql.DelegatingResultSet#getNClob(int)
     */
    @Override
    public AutoCloseNClob getNClob(int columnIndex) throws SQLException {
        NClob nClob = super.getNClob(columnIndex);
        return (nClob == null) ? null : AutoCloseNClob.from(nClob);
    }

    /**
     * @see com.google.code.arm.sql.DelegatingResultSet#getNClob(java.lang.String)
     */
    @Override
    public AutoCloseNClob getNClob(String columnLabel) throws SQLException {
        NClob nClob = super.getNClob(columnLabel);
        return (nClob == null) ? null : AutoCloseNClob.from(nClob);
    }

    /**
     * @see com.google.code.arm.sql.DelegatingResultSet#getSQLXML(int)
     */
    @Override
    public AutoCloseSQLXML getSQLXML(int columnIndex) throws SQLException {
        SQLXML sqlxml = super.getSQLXML(columnIndex);
        return (sqlxml == null) ? null : AutoCloseSQLXML.from(sqlxml);
    }

    /**
     * @see com.google.code.arm.sql.DelegatingResultSet#getSQLXML(java.lang.String)
     */
    @Override
    public AutoCloseSQLXML getSQLXML(String columnLabel) throws SQLException {
        SQLXML sqlxml = super.getSQLXML(columnLabel);
        return (sqlxml == null) ? null : AutoCloseSQLXML.from(sqlxml);
    }
}
