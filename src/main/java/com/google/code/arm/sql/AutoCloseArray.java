package com.google.code.arm.sql;

import java.sql.Array;
import java.sql.SQLException;

public class AutoCloseArray<S extends Array> extends DelegatingArray<S> implements Array, AutoCloseable {

    public static <S extends Array> AutoCloseArray<S> from(S delegate) {
        if (delegate instanceof AutoCloseArray) {
            @SuppressWarnings("unchecked") AutoCloseArray<S> array = (AutoCloseArray<S>) delegate;
            return array;
        }

        return new AutoCloseArray<S>(delegate);
    }

    private AutoCloseArray(S delegate) {
        super(delegate);
    }

    @Override
    public void close() throws SQLException {
        free();
    }

}
