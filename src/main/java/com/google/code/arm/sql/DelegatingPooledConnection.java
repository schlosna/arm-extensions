package com.google.code.arm.sql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.ConnectionEventListener;
import javax.sql.PooledConnection;
import javax.sql.StatementEventListener;

import com.google.code.arm.lang.DelegatingObject;

/**
 * @param <S>
 *            The type of delegate {@link PooledConnection}
 */
public abstract class DelegatingPooledConnection<S extends PooledConnection> extends DelegatingObject<S> implements PooledConnection {

    protected DelegatingPooledConnection(S delegate) {
        super(delegate);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return delegate().getConnection();
    }

    @Override
    public void close() throws SQLException {
        delegate().close();
    }

    @Override
    public void addConnectionEventListener(ConnectionEventListener listener) {
        delegate().addConnectionEventListener(listener);
    }

    @Override
    public void removeConnectionEventListener(ConnectionEventListener listener) {
        delegate().removeConnectionEventListener(listener);
    }

    @Override
    public void addStatementEventListener(StatementEventListener listener) {
        delegate().addStatementEventListener(listener);
    }

    @Override
    public void removeStatementEventListener(StatementEventListener listener) {
        delegate().removeStatementEventListener(listener);
    }
}
