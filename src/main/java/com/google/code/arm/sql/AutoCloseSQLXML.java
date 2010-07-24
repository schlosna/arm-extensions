package com.google.code.arm.sql;

import java.sql.SQLException;
import java.sql.SQLXML;

public class AutoCloseSQLXML<S extends SQLXML> extends DelegatingSQLXML<S> implements AutoCloseable {

    public static <S extends SQLXML> AutoCloseSQLXML<S> from(S delegate) {
        if (delegate instanceof AutoCloseSQLXML) {
            @SuppressWarnings("unchecked") AutoCloseSQLXML<S> sqlxml = (AutoCloseSQLXML<S>) delegate;
            return sqlxml;
        }

        return new AutoCloseSQLXML<S>(delegate);
    }

    private AutoCloseSQLXML(S delegate) {
        super(delegate);
    }

    @Override
    public void close() throws SQLException {
        free();
    }

}
