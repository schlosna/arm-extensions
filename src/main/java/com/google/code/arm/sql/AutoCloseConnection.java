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

/**
 * An {@link AutoCloseable} {@link Connection} to a database. When the automatic resource management block construct
 * invokes {@link #close()}, {@code close()} will be called on the underlying {@link Connection}.
 * <p>
 * It is <b>strongly recommended</b> that an application explicitly commits or rolls back an active transaction prior to
 * calling the {@link #close()} method and prior to {@link #close()} being invoked automatically by the automatic
 * resource management block construct. If the {@link #close()} method is called and there is an active transaction, the
 * results are implementation-defined.
 * </p>
 * 
 * @see AutoCloseDataSource#getConnection()
 * @see AutoCloseDriverManager#getConnection(String)
 */
public class AutoCloseConnection extends DelegatingConnection<Connection> implements AutoCloseable {

    /**
     * Returns an {@link AutoCloseable} {@link Connection} from the given {@link Connection}
     * 
     * @param connection
     *            the Connection
     * @return the {@link AutoCloseable} {@link Connection}
     */
    public static AutoCloseConnection from(Connection connection) {
        if (connection instanceof AutoCloseConnection) {
            return (AutoCloseConnection) connection;
        }

        return new AutoCloseConnection(connection);
    }

    private AutoCloseConnection(Connection connection) {
        super(connection);
    }

    /**
     * <p>
     * It is <b>strongly recommended</b> that an application explicitly commits or rolls back an active transaction
     * prior to calling the {@link #close()} method. If the {@link #close()} method is called and there is an active
     * transaction, the results are implementation-defined.
     * </p>
     * 
     * @see AutoCloseable#close()
     * @see Connection#close()
     */
    @Override
    public void close() throws SQLException {
        super.close();
    }

    /**
     * @see Connection#createArrayOf(java.lang.String, java.lang.Object[])
     */
    @Override
    public AutoCloseArray createArrayOf(String typeName, Object[] elements) throws SQLException {
        return AutoCloseArray.from(super.createArrayOf(typeName, elements));
    }

    /**
     * @see Connection#createBlob()
     */
    @Override
    public AutoCloseBlob createBlob() throws SQLException {
        return AutoCloseBlob.from(super.createBlob());
    }

    /**
     * @see Connection#createClob()
     */
    @Override
    public AutoCloseClob createClob() throws SQLException {
        return AutoCloseClob.from(super.createClob());
    }

    /**
     * @see Connection#createNClob()
     */
    @Override
    public AutoCloseNClob createNClob() throws SQLException {
        return AutoCloseNClob.from(super.createNClob());
    }

    /**
     * @see Connection#createSQLXML()
     */
    @Override
    public AutoCloseSQLXML createSQLXML() throws SQLException {
        return AutoCloseSQLXML.from(super.createSQLXML());
    }

    /**
     * @see Connection#createStatement()
     */
    @Override
    public AutoCloseStatement createStatement() throws SQLException {
        return AutoCloseStatement.from(super.createStatement());
    }

    /**
     * @see Connection#createStatement(int, int)
     */
    @Override
    public AutoCloseStatement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return AutoCloseStatement.from(super.createStatement(resultSetType, resultSetConcurrency));
    }

    /**
     * @see Connection#createStatement(int, int, int)
     */
    @Override
    public AutoCloseStatement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return AutoCloseStatement.from(super.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability));
    }

    /**
     * @see Connection#prepareCall(java.lang.String)
     */
    @Override
    public AutoCloseCallableStatement prepareCall(String sql) throws SQLException {
        return AutoCloseCallableStatement.from(super.prepareCall(sql));
    }

    /**
     * @see Connection#prepareCall(java.lang.String, int, int)
     */
    @Override
    public AutoCloseCallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return AutoCloseCallableStatement.from(super.prepareCall(sql, resultSetType, resultSetConcurrency));
    }

    /**
     * @see Connection#prepareCall(java.lang.String, int, int, int)
     */
    @Override
    public AutoCloseCallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return AutoCloseCallableStatement.from(super.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
    }

    /**
     * @see Connection#prepareStatement(java.lang.String)
     */
    @Override
    public AutoClosePreparedStatement prepareStatement(String sql) throws SQLException {
        return AutoClosePreparedStatement.from(super.prepareStatement(sql));
    }

    /**
     * @see Connection#prepareStatement(java.lang.String, int)
     */
    @Override
    public AutoClosePreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return AutoClosePreparedStatement.from(super.prepareStatement(sql, autoGeneratedKeys));
    }

    /**
     * @see Connection#prepareStatement(java.lang.String, int, int)
     */
    @Override
    public AutoClosePreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return AutoClosePreparedStatement.from(super.prepareStatement(sql, resultSetType, resultSetConcurrency));
    }

    /**
     * @see Connection#prepareStatement(java.lang.String, int, int, int)
     */
    @Override
    public AutoClosePreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return AutoClosePreparedStatement.from(super.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
    }

    /**
     * @see Connection#prepareStatement(java.lang.String, int[])
     */
    @Override
    public AutoClosePreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return AutoClosePreparedStatement.from(super.prepareStatement(sql, columnIndexes));
    }

    /**
     * @see Connection#prepareStatement(java.lang.String, java.lang.String[])
     */
    @Override
    public AutoClosePreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return AutoClosePreparedStatement.from(super.prepareStatement(sql, columnNames));
    }

}
