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

import javax.sql.PooledConnection;

public class AutoClosePooledConnection<S extends PooledConnection> extends DelegatingPooledConnection<S> implements AutoCloseable {

    public static <S extends PooledConnection> AutoClosePooledConnection<S> from(S delegate) {
        if (delegate instanceof AutoClosePooledConnection) {
            @SuppressWarnings("unchecked") AutoClosePooledConnection<S> pooledConnection = (AutoClosePooledConnection<S>) delegate;
            return pooledConnection;
        }

        return new AutoClosePooledConnection<S>(delegate);
    }

    private AutoClosePooledConnection(S delegate) {
        super(delegate);
    }

    @Override
    public void close() throws SQLException {
        super.close();
    }

    @Override
    public AutoCloseConnection<Connection> getConnection() throws SQLException {
        return AutoCloseConnection.from(super.getConnection());
    }
}
