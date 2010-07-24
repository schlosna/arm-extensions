package com.google.code.arm.sql;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public abstract class DelegatingDataSource<S extends DataSource> extends DelegatingWrapper<S> implements DataSource {

    protected DelegatingDataSource(S delegate) {
        super(delegate);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return delegate().getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return delegate().getConnection(username, password);
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return delegate().getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        delegate().setLogWriter(out);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return delegate().getLoginTimeout();
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        delegate().setLoginTimeout(seconds);
    }

}
