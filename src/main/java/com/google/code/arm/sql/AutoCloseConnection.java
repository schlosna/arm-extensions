package com.google.code.arm.sql;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;

public class AutoCloseConnection<S extends Connection> extends DelegatingConnection<S> implements AutoCloseable {

    public static <S extends Connection> AutoCloseConnection<S> from(S delegate) {
        if (delegate instanceof AutoCloseConnection) {
            @SuppressWarnings("unchecked") AutoCloseConnection<S> connection = (AutoCloseConnection<S>) delegate;
            return connection;
        }

        return new AutoCloseConnection<S>(delegate);
    }

    private AutoCloseConnection(S delegate) {
        super(delegate);
    }

    @Override
    public void close() throws SQLException {
        super.close();
    }

    @Override
    public AutoCloseArray<Array> createArrayOf(String typeName, Object[] elements) throws SQLException {
        return AutoCloseArray.from(super.createArrayOf(typeName, elements));
    }

    @Override
    public AutoCloseBlob<Blob> createBlob() throws SQLException {
        return AutoCloseBlob.from(super.createBlob());
    }

    @Override
    public AutoCloseClob<Clob> createClob() throws SQLException {
        return AutoCloseClob.from(super.createClob());
    }

    @Override
    public AutoCloseNClob<NClob> createNClob() throws SQLException {
        return AutoCloseNClob.from(super.createNClob());
    }

    @Override
    public AutoCloseSQLXML<SQLXML> createSQLXML() throws SQLException {
        return AutoCloseSQLXML.from(super.createSQLXML());
    }

    @Override
    public AutoCloseStatement<Statement> createStatement() throws SQLException {
        return AutoCloseStatement.from(super.createStatement());
    }

    @Override
    public AutoCloseStatement<Statement> createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return AutoCloseStatement.from(super.createStatement(resultSetType, resultSetConcurrency));
    }

    @Override
    public AutoCloseStatement<Statement> createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return AutoCloseStatement.from(super.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability));
    }

    @Override
    public AutoCloseCallableStatement<CallableStatement> prepareCall(String sql) throws SQLException {
        return AutoCloseCallableStatement.from(super.prepareCall(sql));
    }

    @Override
    public AutoCloseCallableStatement<CallableStatement> prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return AutoCloseCallableStatement.from(super.prepareCall(sql, resultSetType, resultSetConcurrency));
    }

    @Override
    public AutoCloseCallableStatement<CallableStatement> prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        return AutoCloseCallableStatement.from(super.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
    }

    @Override
    public AutoClosePreparedStatement<PreparedStatement> prepareStatement(String sql) throws SQLException {
        return AutoClosePreparedStatement.from(super.prepareStatement(sql));
    }

    @Override
    public AutoClosePreparedStatement<PreparedStatement> prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return AutoClosePreparedStatement.from(super.prepareStatement(sql, autoGeneratedKeys));
    }

    @Override
    public AutoClosePreparedStatement<PreparedStatement> prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return AutoClosePreparedStatement.from(super.prepareStatement(sql, resultSetType, resultSetConcurrency));
    }

    @Override
    public AutoClosePreparedStatement<PreparedStatement> prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability)
            throws SQLException {
        return AutoClosePreparedStatement.from(super.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
    }

    @Override
    public AutoClosePreparedStatement<PreparedStatement> prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return AutoClosePreparedStatement.from(super.prepareStatement(sql, columnIndexes));
    }

    @Override
    public AutoClosePreparedStatement<PreparedStatement> prepareStatement(String sql, String[] columnNames) throws SQLException {
        return AutoClosePreparedStatement.from(super.prepareStatement(sql, columnNames));
    }

}