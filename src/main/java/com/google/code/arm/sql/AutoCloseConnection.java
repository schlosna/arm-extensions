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
import java.sql.SQLException;

public class AutoCloseConnection extends DelegatingConnection<Connection> implements AutoCloseable {

    public static AutoCloseConnection from(Connection delegate) {
        if (delegate instanceof AutoCloseConnection) {
            return (AutoCloseConnection) delegate;
        }

        return new AutoCloseConnection(delegate);
    }

    private AutoCloseConnection(Connection delegate) {
        super(delegate);
    }

    /**
     * Implements {@link AutoCloseable}
     * 
     * @see AutoCloseable#close()
     * @see com.google.code.arm.sql.DelegatingConnection#close()
     */
    @Override
    public void close() throws SQLException {
        super.close();
    }

    /**
     * @see com.google.code.arm.sql.DelegatingConnection#createArrayOf(java.lang.String, java.lang.Object[])
     */
    @Override
    public AutoCloseArray createArrayOf(String typeName, Object[] elements) throws SQLException {
        return AutoCloseArray.from(super.createArrayOf(typeName, elements));
    }

    /**
     * @see com.google.code.arm.sql.DelegatingConnection#createBlob()
     */
    @Override
    public AutoCloseBlob createBlob() throws SQLException {
        return AutoCloseBlob.from(super.createBlob());
    }

    /**
     * @see com.google.code.arm.sql.DelegatingConnection#createClob()
     */
    @Override
    public AutoCloseClob createClob() throws SQLException {
        return AutoCloseClob.from(super.createClob());
    }

    /**
     * @see com.google.code.arm.sql.DelegatingConnection#createNClob()
     */
    @Override
    public AutoCloseNClob createNClob() throws SQLException {
        return AutoCloseNClob.from(super.createNClob());
    }

    /**
     * @see com.google.code.arm.sql.DelegatingConnection#createSQLXML()
     */
    @Override
    public AutoCloseSQLXML createSQLXML() throws SQLException {
        return AutoCloseSQLXML.from(super.createSQLXML());
    }

    /**
     * @see com.google.code.arm.sql.DelegatingConnection#createStatement()
     */
    @Override
    public AutoCloseStatement createStatement() throws SQLException {
        return AutoCloseStatement.from(super.createStatement());
    }

    /**
     * @see com.google.code.arm.sql.DelegatingConnection#createStatement(int, int)
     */
    @Override
    public AutoCloseStatement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return AutoCloseStatement.from(super.createStatement(resultSetType, resultSetConcurrency));
    }

    /**
     * @see com.google.code.arm.sql.DelegatingConnection#createStatement(int, int, int)
     */
    @Override
    public AutoCloseStatement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return AutoCloseStatement.from(super.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability));
    }

    /**
     * @see com.google.code.arm.sql.DelegatingConnection#prepareCall(java.lang.String)
     */
    @Override
    public AutoCloseCallableStatement prepareCall(String sql) throws SQLException {
        return AutoCloseCallableStatement.from(super.prepareCall(sql));
    }

    /**
     * @see com.google.code.arm.sql.DelegatingConnection#prepareCall(java.lang.String, int, int)
     */
    @Override
    public AutoCloseCallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return AutoCloseCallableStatement.from(super.prepareCall(sql, resultSetType, resultSetConcurrency));
    }

    /**
     * @see com.google.code.arm.sql.DelegatingConnection#prepareCall(java.lang.String, int, int, int)
     */
    @Override
    public AutoCloseCallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return AutoCloseCallableStatement.from(super.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
    }

    /**
     * @see com.google.code.arm.sql.DelegatingConnection#prepareStatement(java.lang.String)
     */
    @Override
    public AutoClosePreparedStatement prepareStatement(String sql) throws SQLException {
        return AutoClosePreparedStatement.from(super.prepareStatement(sql));
    }

    /**
     * @see com.google.code.arm.sql.DelegatingConnection#prepareStatement(java.lang.String, int)
     */
    @Override
    public AutoClosePreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return AutoClosePreparedStatement.from(super.prepareStatement(sql, autoGeneratedKeys));
    }

    /**
     * @see com.google.code.arm.sql.DelegatingConnection#prepareStatement(java.lang.String, int, int)
     */
    @Override
    public AutoClosePreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return AutoClosePreparedStatement.from(super.prepareStatement(sql, resultSetType, resultSetConcurrency));
    }

    /**
     * @see com.google.code.arm.sql.DelegatingConnection#prepareStatement(java.lang.String, int, int, int)
     */
    @Override
    public AutoClosePreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return AutoClosePreparedStatement.from(super.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
    }

    /**
     * @see com.google.code.arm.sql.DelegatingConnection#prepareStatement(java.lang.String, int[])
     */
    @Override
    public AutoClosePreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return AutoClosePreparedStatement.from(super.prepareStatement(sql, columnIndexes));
    }

    /**
     * @see com.google.code.arm.sql.DelegatingConnection#prepareStatement(java.lang.String, java.lang.String[])
     */
    @Override
    public AutoClosePreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return AutoClosePreparedStatement.from(super.prepareStatement(sql, columnNames));
    }

}
