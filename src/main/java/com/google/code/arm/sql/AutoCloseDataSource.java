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

import javax.sql.DataSource;

/**
 * Wraps a {@link DataSource} so that it provides {@link AutoCloseable} {@link Connection} instances from the
 * {@link #getConnection()} and {@link #getConnection(String, String)} methods.
 */
public class AutoCloseDataSource extends DelegatingDataSource<DataSource> implements DataSource {

    /**
     * Returns a wrapped {@link DataSource} that creates {@link AutoCloseable} {@link Connection}s from the given
     * {@link DataSource}
     * 
     * @param dataSource
     *            the DataSource
     * @return the {@link AutoCloseDataSource}
     */
    public static AutoCloseDataSource from(DataSource dataSource) {
        if (dataSource instanceof AutoCloseDataSource) {
            return (AutoCloseDataSource) dataSource;
        }

        return new AutoCloseDataSource(dataSource);
    }

    private AutoCloseDataSource(DataSource dataSource) {
        super(dataSource);
    }

    /**
     * @see DataSource#getConnection()
     */
    @Override
    public AutoCloseConnection getConnection() throws SQLException {
        return AutoCloseConnection.from(super.getConnection());
    }

    /**
     * @see DataSource#getConnection(java.lang.String, java.lang.String)
     */
    @Override
    public AutoCloseConnection getConnection(String username, String password) throws SQLException {
        return AutoCloseConnection.from(super.getConnection(username, password));
    }
}
