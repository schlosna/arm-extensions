package com.google.code.arm.sql;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;

import com.google.code.arm.lang.DelegatingObject;

public class DelegatingDriver<S extends Driver> extends DelegatingObject<S> implements Driver {

    protected DelegatingDriver(S delegate) {
        super(delegate);
    }

    @Override
    public Connection connect(String url, Properties info) throws SQLException {
        return delegate().connect(url, info);
    }

    @Override
    public boolean acceptsURL(String url) throws SQLException {
        return delegate().acceptsURL(url);
    }

    @Override
    public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
        return delegate().getPropertyInfo(url, info);
    }

    @Override
    public int getMajorVersion() {
        return delegate().getMajorVersion();
    }

    @Override
    public int getMinorVersion() {
        return delegate().getMinorVersion();
    }

    @Override
    public boolean jdbcCompliant() {
        return delegate().jdbcCompliant();
    }
}
