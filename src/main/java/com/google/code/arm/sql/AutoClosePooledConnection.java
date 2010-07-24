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

public class AutoClosePooledConnection extends DelegatingPooledConnection<PooledConnection> implements AutoCloseable {

    public static AutoClosePooledConnection from(PooledConnection delegate) {
        if (delegate instanceof AutoClosePooledConnection) {
            return (AutoClosePooledConnection) delegate;
        }

        return new AutoClosePooledConnection(delegate);
    }

    private AutoClosePooledConnection(PooledConnection delegate) {
        super(delegate);
    }

    /**
     * @see AutoCloseable#close()
     * @see com.google.code.arm.sql.DelegatingPooledConnection#close()
     */
    @Override
    public void close() throws SQLException {
        super.close();
    }

    /**
     * @see com.google.code.arm.sql.DelegatingPooledConnection#getConnection()
     */
    @Override
    public AutoCloseConnection getConnection() throws SQLException {
        return AutoCloseConnection.from(super.getConnection());
    }
}
