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
import java.io.Reader;
import java.io.Writer;
import java.sql.SQLException;
import java.sql.SQLXML;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

import com.google.code.arm.lang.DelegatingObject;

public class DelegatingSQLXML<S extends SQLXML> extends DelegatingObject<S> implements SQLXML {

    protected DelegatingSQLXML(S delegate) {
        super(delegate);
    }

    @Override
    public void free() throws SQLException {
        delegate().free();
    }

    @Override
    public InputStream getBinaryStream() throws SQLException {
        return delegate().getBinaryStream();
    }

    @Override
    public OutputStream setBinaryStream() throws SQLException {
        return delegate().setBinaryStream();
    }

    @Override
    public Reader getCharacterStream() throws SQLException {
        return delegate().getCharacterStream();
    }

    @Override
    public Writer setCharacterStream() throws SQLException {
        return delegate().setCharacterStream();
    }

    @Override
    public String getString() throws SQLException {
        return delegate().getString();
    }

    @Override
    public void setString(String value) throws SQLException {
        delegate().setString(value);
    }

    @Override
    public <T extends Source> T getSource(Class<T> sourceClass) throws SQLException {
        return delegate().getSource(sourceClass);
    }

    @Override
    public <T extends Result> T setResult(Class<T> resultClass) throws SQLException {
        return delegate().setResult(resultClass);
    }

}
