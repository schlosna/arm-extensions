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

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;

import com.google.code.arm.lang.DelegatingObject;

public class DelegatingBlob<S extends Blob> extends DelegatingObject<S> implements Blob {

    protected DelegatingBlob(S delegate) {
        super(delegate);
    }

    @Override
    public long length() throws SQLException {
        return delegate().length();
    }

    @Override
    public byte[] getBytes(long pos, int length) throws SQLException {
        return delegate().getBytes(pos, length);
    }

    @Override
    public InputStream getBinaryStream() throws SQLException {
        return delegate().getBinaryStream();
    }

    @Override
    public long position(byte[] pattern, long start) throws SQLException {
        return delegate().position(pattern, start);
    }

    @Override
    public long position(Blob pattern, long start) throws SQLException {
        return delegate().position(pattern, start);
    }

    @Override
    public int setBytes(long pos, byte[] bytes) throws SQLException {
        return delegate().setBytes(pos, bytes);
    }

    @Override
    public int setBytes(long pos, byte[] bytes, int offset, int len) throws SQLException {
        return delegate().setBytes(pos, bytes, offset, len);
    }

    @Override
    public OutputStream setBinaryStream(long pos) throws SQLException {
        return delegate().setBinaryStream(pos);
    }

    @Override
    public void truncate(long len) throws SQLException {
        delegate().truncate(len);
    }

    @Override
    public void free() throws SQLException {
        delegate().free();
    }

    @Override
    public InputStream getBinaryStream(long pos, long length) throws SQLException {
        return delegate().getBinaryStream(pos, length);
    }
}
