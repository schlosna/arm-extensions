package com.google.code.arm.sql;

import java.sql.Blob;
import java.sql.SQLException;

public class AutoCloseBlob<S extends Blob> extends DelegatingBlob<S> implements Blob, AutoCloseable {

    public static <S extends Blob> AutoCloseBlob<S> from(S delegate) {
        if (delegate instanceof AutoCloseBlob) {
            @SuppressWarnings("unchecked") AutoCloseBlob<S> blob = (AutoCloseBlob<S>) delegate;
            return blob;
        }

        return new AutoCloseBlob<S>(delegate);
    }

    private AutoCloseBlob(S delegate) {
        super(delegate);
    }

    @Override
    public void close() throws SQLException {
        free();
    }

}
