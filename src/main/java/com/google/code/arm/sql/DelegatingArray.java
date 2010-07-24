package com.google.code.arm.sql;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.google.code.arm.lang.DelegatingObject;

public abstract class DelegatingArray<S extends Array> extends DelegatingObject<S> implements Array {

    protected DelegatingArray(S delegate) {
        super(delegate);
    }

    @Override
    public String getBaseTypeName() throws SQLException {
        return delegate().getBaseTypeName();
    }

    @Override
    public int getBaseType() throws SQLException {
        return delegate().getBaseType();
    }

    @Override
    public Object getArray() throws SQLException {
        return delegate().getArray();
    }

    @Override
    public Object getArray(Map<String, Class<?>> map) throws SQLException {
        return delegate().getArray(map);
    }

    @Override
    public Object getArray(long index, int count) throws SQLException {
        return delegate().getArray(index, count);
    }

    @Override
    public Object getArray(long index, int count, Map<String, Class<?>> map) throws SQLException {
        return delegate().getArray(index, count, map);
    }

    @Override
    public ResultSet getResultSet() throws SQLException {
        return delegate().getResultSet();
    }

    @Override
    public ResultSet getResultSet(Map<String, Class<?>> map) throws SQLException {
        return delegate().getResultSet(map);
    }

    @Override
    public ResultSet getResultSet(long index, int count) throws SQLException {
        return delegate().getResultSet(index, count);
    }

    @Override
    public ResultSet getResultSet(long index, int count, Map<String, Class<?>> map) throws SQLException {
        return delegate().getResultSet(index, count, map);
    }

    @Override
    public void free() throws SQLException {
        delegate().free();
    }
}
