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

public abstract class DelegatingSQLXML<S extends SQLXML> extends DelegatingObject<S> implements SQLXML {

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
