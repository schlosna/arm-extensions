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

/**
 * An {@link AutoCloseable} {@link ResultSet}. When the automatic resource management block construct invokes
 * {@link #close()}, {@code close()} will be called on the underlying {@link ResultSet}.
 */
public class AutoCloseResultSet extends DelegatingResultSet<ResultSet> implements AutoCloseable, ResultSet {

    /**
     * Returns an {@link AutoCloseable} {@link ResultSet} from the given {@link ResultSet}
     * 
     * @param resultSet
     *            the ResultSet
     * @return the {@link AutoCloseable} {@link ResultSet}
     */
    public static AutoCloseResultSet from(ResultSet resultSet) {
        if (resultSet instanceof AutoCloseResultSet) {
            return (AutoCloseResultSet) resultSet;
        }

        return new AutoCloseResultSet(resultSet);
    }

    private AutoCloseResultSet(ResultSet resultSet) {
        super(resultSet);
    }

    /**
     * Implements {@link AutoCloseable}
     * 
     * @see AutoCloseable#close()
     * @see ResultSet#close()
     */
    @Override
    public void close() throws SQLException {
        super.close();
    }

    /**
     * @see ResultSet#getArray(int)
     */
    @Override
    public AutoCloseArray getArray(int columnIndex) throws SQLException {
        Array array = super.getArray(columnIndex);
        return (array == null) ? null : AutoCloseArray.from(array);
    }

    /**
     * @see ResultSet#getArray(java.lang.String)
     */
    @Override
    public AutoCloseArray getArray(String columnLabel) throws SQLException {
        Array array = super.getArray(columnLabel);
        return (array == null) ? null : AutoCloseArray.from(array);
    }

    /**
     * @see ResultSet#getBlob(int)
     */
    @Override
    public AutoCloseBlob getBlob(int columnIndex) throws SQLException {
        Blob blob = super.getBlob(columnIndex);
        return (blob == null) ? null : AutoCloseBlob.from(blob);
    }

    /**
     * @see ResultSet#getBlob(java.lang.String)
     */
    @Override
    public AutoCloseBlob getBlob(String columnLabel) throws SQLException {
        Blob blob = super.getBlob(columnLabel);
        return (blob == null) ? null : AutoCloseBlob.from(blob);
    }

    /**
     * @see ResultSet#getClob(int)
     */
    @Override
    public AutoCloseClob getClob(int columnIndex) throws SQLException {
        Clob clob = super.getClob(columnIndex);
        return (clob == null) ? null : AutoCloseClob.from(clob);
    }

    /**
     * @see ResultSet#getClob(java.lang.String)
     */
    @Override
    public AutoCloseClob getClob(String columnLabel) throws SQLException {
        Clob clob = super.getClob(columnLabel);
        return (clob == null) ? null : AutoCloseClob.from(clob);
    }

    /**
     * @see ResultSet#getNClob(int)
     */
    @Override
    public AutoCloseNClob getNClob(int columnIndex) throws SQLException {
        NClob nClob = super.getNClob(columnIndex);
        return (nClob == null) ? null : AutoCloseNClob.from(nClob);
    }

    /**
     * @see ResultSet#getNClob(java.lang.String)
     */
    @Override
    public AutoCloseNClob getNClob(String columnLabel) throws SQLException {
        NClob nClob = super.getNClob(columnLabel);
        return (nClob == null) ? null : AutoCloseNClob.from(nClob);
    }

    /**
     * @see ResultSet#getSQLXML(int)
     */
    @Override
    public AutoCloseSQLXML getSQLXML(int columnIndex) throws SQLException {
        SQLXML sqlxml = super.getSQLXML(columnIndex);
        return (sqlxml == null) ? null : AutoCloseSQLXML.from(sqlxml);
    }

    /**
     * @see ResultSet#getSQLXML(java.lang.String)
     */
    @Override
    public AutoCloseSQLXML getSQLXML(String columnLabel) throws SQLException {
        SQLXML sqlxml = super.getSQLXML(columnLabel);
        return (sqlxml == null) ? null : AutoCloseSQLXML.from(sqlxml);
    }
}
