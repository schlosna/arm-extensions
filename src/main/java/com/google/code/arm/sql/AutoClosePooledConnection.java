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
