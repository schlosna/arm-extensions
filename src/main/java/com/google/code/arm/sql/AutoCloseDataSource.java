package com.google.code.arm.sql;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class AutoCloseDataSource<S extends DataSource> extends DelegatingDataSource<S> {

    public static <S extends DataSource> AutoCloseDataSource<S> from(S delegate) {
        if (delegate instanceof AutoCloseDataSource) {
            @SuppressWarnings("unchecked") AutoCloseDataSource<S> dataSource = (AutoCloseDataSource<S>) delegate;
            return dataSource;
        }

        return new AutoCloseDataSource<S>(delegate);
    }

    private AutoCloseDataSource(S delegate) {
        super(delegate);
    }

    @Override
    public AutoCloseConnection<Connection> getConnection() throws SQLException {
        return AutoCloseConnection.from(super.getConnection());
    }

    @Override
    public AutoCloseConnection<Connection> getConnection(String username, String password) throws SQLException {
        return AutoCloseConnection.from(super.getConnection(username, password));
    }
}
