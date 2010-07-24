package com.google.code.arm.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AutoClosePreparedStatement<S extends PreparedStatement> extends DelegatingPreparedStatement<S> implements PreparedStatement, AutoCloseable {

    public static <S extends PreparedStatement> AutoClosePreparedStatement<S> from(S delegate) {
        if (delegate instanceof AutoClosePreparedStatement) {
            @SuppressWarnings("unchecked") AutoClosePreparedStatement<S> stmt = (AutoClosePreparedStatement<S>) delegate;
            return stmt;
        }

        return new AutoClosePreparedStatement<S>(delegate);
    }

    private AutoClosePreparedStatement(S delegate) {
        super(delegate);
    }

    // AutoCloseable
    @Override
    public void close() throws SQLException {
        super.close();
    }

    // Statement
    @Override
    public AutoCloseResultSet<ResultSet> executeQuery(String sql) throws SQLException {
        return AutoCloseResultSet.from(super.executeQuery(sql));
    }

    // Statement
    @Override
    public AutoCloseConnection<Connection> getConnection() throws SQLException {
        return AutoCloseConnection.from(super.getConnection());
    }

    // Statement
    @Override
    public AutoCloseResultSet<ResultSet> getGeneratedKeys() throws SQLException {
        return AutoCloseResultSet.from(super.getGeneratedKeys());
    }

    // Statement
    @Override
    public AutoCloseResultSet<ResultSet> getResultSet() throws SQLException {
        return AutoCloseResultSet.from(super.getResultSet());
    }

    // PreparedStatement
    @Override
    public AutoCloseResultSet<ResultSet> executeQuery() throws SQLException {
        return AutoCloseResultSet.from(super.executeQuery());
    }
}
