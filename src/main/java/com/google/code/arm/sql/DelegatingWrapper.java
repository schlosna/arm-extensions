package com.google.code.arm.sql;

import java.sql.SQLException;
import java.sql.Wrapper;

import com.google.code.arm.lang.DelegatingObject;

public abstract class DelegatingWrapper<S extends Wrapper> extends DelegatingObject<S> implements Wrapper {

    protected DelegatingWrapper(S delegate) {
        super(delegate);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        if (iface.isInstance(this)) {
            return iface.cast(this);
        }
        return delegate().unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        if (iface.isInstance(this)) {
            return true;
        }
        return delegate().isWrapperFor(iface);
    }

}
