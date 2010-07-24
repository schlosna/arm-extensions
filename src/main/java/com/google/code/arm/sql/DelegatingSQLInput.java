package com.google.code.arm.sql;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;

import com.google.code.arm.lang.DelegatingObject;

public class DelegatingSQLInput<D extends SQLInput> extends DelegatingObject<D> implements SQLInput {

    protected DelegatingSQLInput(D delegate) {
        super(delegate);
    }

    @Override
    public String readString() throws SQLException {
        return delegate().readString();
    }

    @Override
    public boolean readBoolean() throws SQLException {
        return delegate().readBoolean();
    }

    @Override
    public byte readByte() throws SQLException {
        return delegate().readByte();
    }

    @Override
    public short readShort() throws SQLException {
        return delegate().readShort();
    }

    @Override
    public int readInt() throws SQLException {
        return delegate().readInt();
    }

    @Override
    public long readLong() throws SQLException {
        return delegate().readLong();
    }

    @Override
    public float readFloat() throws SQLException {
        return delegate().readFloat();
    }

    @Override
    public double readDouble() throws SQLException {
        return delegate().readDouble();
    }

    @Override
    public BigDecimal readBigDecimal() throws SQLException {
        return delegate().readBigDecimal();
    }

    @Override
    public byte[] readBytes() throws SQLException {
        return delegate().readBytes();
    }

    @Override
    public Date readDate() throws SQLException {
        return delegate().readDate();
    }

    @Override
    public Time readTime() throws SQLException {
        return delegate().readTime();
    }

    @Override
    public Timestamp readTimestamp() throws SQLException {
        return delegate().readTimestamp();
    }

    @Override
    public Reader readCharacterStream() throws SQLException {
        return delegate().readCharacterStream();
    }

    @Override
    public InputStream readAsciiStream() throws SQLException {
        return delegate().readAsciiStream();
    }

    @Override
    public InputStream readBinaryStream() throws SQLException {
        return delegate().readBinaryStream();
    }

    @Override
    public Object readObject() throws SQLException {
        return delegate().readObject();
    }

    @Override
    public Ref readRef() throws SQLException {
        return delegate().readRef();
    }

    @Override
    public Blob readBlob() throws SQLException {
        return delegate().readBlob();
    }

    @Override
    public Clob readClob() throws SQLException {
        return delegate().readClob();
    }

    @Override
    public Array readArray() throws SQLException {
        return delegate().readArray();
    }

    @Override
    public boolean wasNull() throws SQLException {
        return delegate().wasNull();
    }

    @Override
    public URL readURL() throws SQLException {
        return delegate().readURL();
    }

    @Override
    public NClob readNClob() throws SQLException {
        return delegate().readNClob();
    }

    @Override
    public String readNString() throws SQLException {
        return delegate().readNString();
    }

    @Override
    public SQLXML readSQLXML() throws SQLException {
        return delegate().readSQLXML();
    }

    @Override
    public RowId readRowId() throws SQLException {
        return delegate().readRowId();
    }
}
