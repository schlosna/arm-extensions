package com.google.code.arm.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AutoCloseStatement<S extends Statement> extends DelegatingStatement<S> implements AutoCloseable {

    public static <S extends Statement> AutoCloseStatement<S> from(S delegate) {
        if (delegate instanceof AutoCloseStatement) {
            @SuppressWarnings("unchecked") AutoCloseStatement<S> stmt = (AutoCloseStatement<S>) delegate;
            return stmt;
        }

        return new AutoCloseStatement<S>(delegate);
    }

    private AutoCloseStatement(S delegate) {
        super(delegate);
    }

    /**
     * Closes the delegate statement and implements {@link AutoCloseable}.
     * 
     * @see com.google.code.arm.sql.DelegatingStatement#close()
     */
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
}
