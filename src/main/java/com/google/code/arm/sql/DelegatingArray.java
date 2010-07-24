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
