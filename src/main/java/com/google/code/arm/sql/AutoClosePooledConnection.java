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

import java.sql.SQLException;

import javax.sql.PooledConnection;

/**
 * An {@link AutoCloseable} {@link PooledConnection}. When the automatic resource management block construct invokes
 * {@link #close()}, {@code close()} will be called on the underlying {@link PooledConnection}.
 */
public class AutoClosePooledConnection extends DelegatingPooledConnection<PooledConnection> implements AutoCloseable, PooledConnection {

    /**
     * Returns an {@link AutoCloseable} {@link PooledConnection} from the given {@link PooledConnection}
     * 
     * @param connection
     *            the PooledConnection
     * @return the {@link AutoCloseable} {@link PooledConnection}
     */
    public static AutoClosePooledConnection from(PooledConnection connection) {
        if (connection instanceof AutoClosePooledConnection) {
            return (AutoClosePooledConnection) connection;
        }

        return new AutoClosePooledConnection(connection);
    }

    private AutoClosePooledConnection(PooledConnection connection) {
        super(connection);
    }

    /**
     * @see AutoCloseable#close()
     * @see PooledConnection#close()
     */
    @Override
    public void close() throws SQLException {
        super.close();
    }

    /**
     * @see PooledConnection#getConnection()
     */
    @Override
    public AutoCloseConnection getConnection() throws SQLException {
        return AutoCloseConnection.from(super.getConnection());
    }
}
