package com.google.code.arm.sql;

import java.sql.Clob;
import java.sql.SQLException;

public class AutoCloseClob<S extends Clob> extends DelegatingClob<S> implements Clob, AutoCloseable {

    public static <S extends Clob> AutoCloseClob<S> from(S delegate) {
        if (delegate instanceof AutoCloseClob) {
            @SuppressWarnings("unchecked") AutoCloseClob<S> clob = (AutoCloseClob<S>) delegate;
            return clob;
        }

        return new AutoCloseClob<S>(delegate);
    }

    private AutoCloseClob(S delegate) {
        super(delegate);
    }

    @Override
    public void close() throws SQLException {
        free();
    }

}
