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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLXML;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.sql.PooledConnection;

/**
 * Utility methods for adapting {@code java.sql.*} types to instances that implement {@link AutoCloseable}. This class
 * is intended to be statically imported (e.g. {@code import static com.google.code.arm.AutoCloseables.*}).
 * Where
 * possible, the instances returned by these methods will return {@link AutoCloseable} instances (e.g.
 * {@link AutoCloseConnection#prepareStatement(String)} returns an {@link AutoClosePreparedStatement} that implements
 * {@link AutoCloseable}). Note that all methods throw {@link NullPointerException} for null arguments.
 */
public class AutoCloseables {

    private AutoCloseables() {
    }

    /**
     * @param array
     *            array
     * @return {@link AutoCloseable} {@link Array} instance
     */
    public static AutoCloseArray arm(Array array) {
        return AutoCloseArray.from(array);
    }

    /**
     * @param blob
     *            blob
     * @return {@link AutoCloseable} {@link Blob} instance
     */
    public static AutoCloseBlob arm(Blob blob) {
        return AutoCloseBlob.from(blob);
    }

    /**
     * @param callableStatement
     *            callable statement
     * @return {@link AutoCloseable} {@link CallableStatement} instance
     */
    public static AutoCloseCallableStatement arm(CallableStatement callableStatement) {
        return AutoCloseCallableStatement.from(callableStatement);
    }

    /**
     * @param clob
     *            clob
     * @return {@link AutoCloseable} {@link Clob} instance
     */
    public static AutoCloseClob arm(Clob clob) {
        return AutoCloseClob.from(clob);
    }

    /**
     * @param connection
     *            connection
     * @return {@link AutoCloseable} {@link Connection} instance
     */
    public static AutoCloseConnection arm(Connection connection) {
        return AutoCloseConnection.from(connection);
    }

    /**
     * @param dataSource
     *            data source
     * @return {@link AutoCloseable} {@link DataSource} instance
     */
    public static AutoCloseDataSource arm(DataSource dataSource) {
        return AutoCloseDataSource.from(dataSource);
    }

    /**
     * @param nClob
     *            NClob
     * @return {@link AutoCloseable} {@link NClob} instance
     */
    public static AutoCloseNClob arm(NClob nClob) {
        return AutoCloseNClob.from(nClob);
    }

    /**
     * @param pooledConnection
     *            pooled connection
     * @return {@link AutoCloseable} {@link PooledConnection} instance
     */
    public static AutoClosePooledConnection arm(PooledConnection pooledConnection) {
        return AutoClosePooledConnection.from(pooledConnection);
    }

    /**
     * @param preparedStatement
     *            prepared statement
     * @return {@link AutoCloseable} {@link PreparedStatement} instance
     */
    public static AutoClosePreparedStatement arm(PreparedStatement preparedStatement) {
        return AutoClosePreparedStatement.from(preparedStatement);
    }

    /**
     * @param resultSet
     *            result set
     * @return {@link AutoCloseable} {@link ResultSet} instance
     */
    public static AutoCloseResultSet arm(ResultSet resultSet) {
        return AutoCloseResultSet.from(resultSet);
    }

    /**
     * @param sqlXml
     *            SQL XML
     * @return {@link AutoCloseable} {@link SQLXML} instance
     */
    public static AutoCloseSQLXML arm(SQLXML sqlXml) {
        return AutoCloseSQLXML.from(sqlXml);
    }

    /**
     * @param statement
     *            statement
     * @return {@link AutoCloseable} {@link Statement} instance
     */
    public static AutoCloseStatement arm(Statement statement) {
        return AutoCloseStatement.from(statement);
    }
}
