package com.google.code.arm.sql;

import java.sql.NClob;
import java.sql.SQLException;

public class AutoCloseNClob<S extends NClob> extends DelegatingNClob<S> implements AutoCloseable {

    public static <S extends NClob> AutoCloseNClob<S> from(S delegate) {
        if (delegate instanceof AutoCloseNClob) {
            @SuppressWarnings("unchecked") AutoCloseNClob<S> nClob = (AutoCloseNClob<S>) delegate;
            return nClob;
        }

        return new AutoCloseNClob<S>(delegate);
    }

    private AutoCloseNClob(S delegate) {
        super(delegate);
    }

    @Override
    public void close() throws SQLException {
        free();
    }

}
