package com.google.code.arm.sql;

import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.NClob;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLXML;

public class AutoCloseSQLInput<S extends SQLInput> extends DelegatingSQLInput<S> implements SQLInput {

    public static <S extends SQLInput> AutoCloseSQLInput<S> from(S delegate) {
        if (delegate instanceof AutoCloseSQLInput) {
            @SuppressWarnings("unchecked") AutoCloseSQLInput<S> sqlinput = (AutoCloseSQLInput<S>) delegate;
            return sqlinput;
        }

        return new AutoCloseSQLInput<S>(delegate);
    }

    private AutoCloseSQLInput(S delegate) {
        super(delegate);
    }

    @Override
    public Array readArray() throws SQLException {
        return AutoCloseArray.from(super.readArray());
    }

    @Override
    public Blob readBlob() throws SQLException {
        return AutoCloseBlob.from(super.readBlob());
    }

    @Override
    public Clob readClob() throws SQLException {
        return AutoCloseClob.from(super.readClob());
    }

    @Override
    public NClob readNClob() throws SQLException {
        return AutoCloseNClob.from(super.readNClob());
    }

    @Override
    public SQLXML readSQLXML() throws SQLException {
        return AutoCloseSQLXML.from(super.readSQLXML());
    }
}
