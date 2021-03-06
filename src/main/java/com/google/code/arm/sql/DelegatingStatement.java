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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

/**
 * @param <S>
 *            The type of delegate {@link Statement}
 */
public class DelegatingStatement<S extends Statement> extends DelegatingWrapper<S> implements Statement {

    protected DelegatingStatement(S delegate) {
        super(delegate);
    }

    @Override
    public void addBatch(String sql) throws SQLException {
        delegate().addBatch(sql);
    }

    @Override
    public void cancel() throws SQLException {
        delegate().cancel();
    }

    @Override
    public void clearBatch() throws SQLException {
        delegate().clearBatch();
    }

    @Override
    public void clearWarnings() throws SQLException {
        delegate().clearWarnings();
    }

    @Override
    public void close() throws SQLException {
        delegate().close();
    }

    @Override
    public boolean execute(String sql) throws SQLException {
        return delegate().execute(sql);
    }

    @Override
    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        return delegate().execute(sql, autoGeneratedKeys);
    }

    @Override
    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        return delegate().execute(sql, columnIndexes);
    }

    @Override
    public boolean execute(String sql, String[] columnNames) throws SQLException {
        return delegate().execute(sql, columnNames);
    }

    @Override
    public int[] executeBatch() throws SQLException {
        return delegate().executeBatch();
    }

    @Override
    public ResultSet executeQuery(String sql) throws SQLException {
        return delegate().executeQuery(sql);
    }

    @Override
    public int executeUpdate(String sql) throws SQLException {
        return delegate().executeUpdate(sql);
    }

    @Override
    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return delegate().executeUpdate(sql, autoGeneratedKeys);
    }

    @Override
    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return delegate().executeUpdate(sql, columnIndexes);
    }

    @Override
    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        return delegate().executeUpdate(sql, columnNames);
    }

    @Override
    public Connection getConnection() throws SQLException {
        return delegate().getConnection();
    }

    @Override
    public int getFetchDirection() throws SQLException {
        return delegate().getFetchDirection();
    }

    @Override
    public int getFetchSize() throws SQLException {
        return delegate().getFetchSize();
    }

    @Override
    public ResultSet getGeneratedKeys() throws SQLException {
        return delegate().getGeneratedKeys();
    }

    @Override
    public int getMaxFieldSize() throws SQLException {
        return delegate().getMaxFieldSize();
    }

    @Override
    public int getMaxRows() throws SQLException {
        return delegate().getMaxRows();
    }

    @Override
    public boolean getMoreResults() throws SQLException {
        return delegate().getMoreResults();
    }

    @Override
    public boolean getMoreResults(int current) throws SQLException {
        return delegate().getMoreResults(current);
    }

    @Override
    public int getQueryTimeout() throws SQLException {
        return delegate().getQueryTimeout();
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return delegate().getResultSet();
    }

    @Override
    public int getResultSetConcurrency() throws SQLException {
        return delegate().getResultSetConcurrency();
    }

    @Override
    public int getResultSetHoldability() throws SQLException {
        return delegate().getResultSetHoldability();
    }

    @Override
    public int getResultSetType() throws SQLException {
        return delegate().getResultSetType();
    }

    @Override
    public int getUpdateCount() throws SQLException {
        return delegate().getUpdateCount();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return delegate().getWarnings();
    }

    @Override
    public boolean isClosed() throws SQLException {
        return delegate().isClosed();
    }

    @Override
    public boolean isPoolable() throws SQLException {
        return delegate().isPoolable();
    }

    @Override
    public void setCursorName(String name) throws SQLException {
        delegate().setCursorName(name);
    }

    @Override
    public void setEscapeProcessing(boolean enable) throws SQLException {
        delegate().setEscapeProcessing(enable);
    }

    @Override
    public void setFetchDirection(int direction) throws SQLException {
        delegate().setFetchDirection(direction);
    }

    @Override
    public void setFetchSize(int rows) throws SQLException {
        delegate().setFetchSize(rows);
    }

    @Override
    public void setMaxFieldSize(int max) throws SQLException {
        delegate().setMaxFieldSize(max);
    }

    @Override
    public void setMaxRows(int max) throws SQLException {
        delegate().setMaxRows(max);
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        delegate().setPoolable(poolable);
    }

    @Override
    public void setQueryTimeout(int seconds) throws SQLException {
        delegate().setQueryTimeout(seconds);
    }
}
