/*
 * Copyright (C) 2010 David Schlosnagle
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.code.arm.sql;

import static com.google.code.arm.Assert.*;
import static com.google.code.arm.Mock.*;
import java.sql.CallableStatement;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AutoCloseConnectionTest {

    private static final Object[] OBJ_ARRAY = new Object[] { "a", "b" };
    private AutoCloseConnection armConnection;
    private Connection mockConnection;

    @Before
    public void setUp() throws Exception {
        mockConnection = mock(Connection.class);
        armConnection = AutoCloseConnection.from(mockConnection);
    }

    @After
    public void tearDown() throws Exception {
        mockConnection = null;
        armConnection = null;
    }

    @Test
    public final void testClose() throws SQLException {
        armConnection.close();
        verify(mockConnection).close();
    }

    @Test(expected = SQLException.class)
    public final void testCloseWithException() throws SQLException {
        doThrow(new SQLException()).when(mockConnection).close();
        armConnection.close();
        verify(mockConnection).close();
    }

    @Test
    public final void testFrom() {
        AutoCloseConnection c = AutoCloseConnection.from(mockConnection);
        assertInstanceOf(Connection.class, c);
        assertInstanceOf(AutoCloseable.class, c);
        assertInstanceOf(Connection.class, c.delegate());
        assertNotInstanceOf(AutoCloseable.class, c.delegate());
    }

    @Test
    public final void testCreateArrayOfStringObjectArray() throws SQLException {
        Array mockArray = mock(Array.class);
        when(mockConnection.createArrayOf(anyString(), anyObjectArray())).thenReturn(mockArray);

        AutoCloseArray array = armConnection.createArrayOf("char", OBJ_ARRAY);

        verify(mockConnection).createArrayOf(anyString(), anyObjectArray());
        assertInstanceOf(Array.class, array);
        assertInstanceOf(AutoCloseable.class, array);
        assertInstanceOf(Array.class, array.delegate());
        assertNotInstanceOf(AutoCloseable.class, array.delegate());
        assertSame(mockArray, array.delegate());
    }

    @Test
    public final void testCreateBlob() throws SQLException {
        Blob mockBlob = mock(Blob.class);
        when(mockConnection.createBlob()).thenReturn(mockBlob);

        AutoCloseBlob blob = armConnection.createBlob();

        verify(mockConnection).createBlob();
        assertInstanceOf(Blob.class, blob);
        assertInstanceOf(AutoCloseable.class, blob);
        assertInstanceOf(Blob.class, blob.delegate());
        assertNotInstanceOf(AutoCloseable.class, blob.delegate());
        assertSame(mockBlob, blob.delegate());
    }

    @Test
    public final void testCreateClob() throws SQLException {
        Clob mockClob = mock(Clob.class);
        when(mockConnection.createClob()).thenReturn(mockClob);

        AutoCloseClob clob = armConnection.createClob();

        verify(mockConnection).createClob();
        assertInstanceOf(Clob.class, clob);
        assertInstanceOf(AutoCloseable.class, clob);
        assertInstanceOf(Clob.class, clob.delegate());
        assertNotInstanceOf(AutoCloseable.class, clob.delegate());
        assertSame(mockClob, clob.delegate());
    }

    @Test
    public final void testCreateNClob() throws SQLException {
        NClob mockNClob = mock(NClob.class);
        when(mockConnection.createNClob()).thenReturn(mockNClob);

        AutoCloseNClob nClob = armConnection.createNClob();

        verify(mockConnection).createNClob();
        assertInstanceOf(NClob.class, nClob);
        assertInstanceOf(AutoCloseable.class, nClob);
        assertInstanceOf(NClob.class, nClob.delegate());
        assertNotInstanceOf(AutoCloseable.class, nClob.delegate());
        assertSame(mockNClob, nClob.delegate());
    }

    @Test
    public final void testCreateSQLXML() throws SQLException {
        SQLXML mockSQLXML = mock(SQLXML.class);
        when(mockConnection.createSQLXML()).thenReturn(mockSQLXML);

        AutoCloseSQLXML sqlxml = armConnection.createSQLXML();

        verify(mockConnection).createSQLXML();
        assertInstanceOf(SQLXML.class, sqlxml);
        assertInstanceOf(AutoCloseable.class, sqlxml);
        assertInstanceOf(SQLXML.class, sqlxml.delegate());
        assertNotInstanceOf(AutoCloseable.class, sqlxml.delegate());
        assertSame(mockSQLXML, sqlxml.delegate());
    }

    @Test
    public final void testCreateStatement() throws SQLException {
        Statement mockStatement = mock(Statement.class);
        when(mockConnection.createStatement()).thenReturn(mockStatement);

        AutoCloseStatement c = armConnection.createStatement();

        verify(mockConnection).createStatement();
        assertInstanceOf(Statement.class, c);
        assertInstanceOf(AutoCloseable.class, c);
        assertInstanceOf(Statement.class, c.delegate());
        assertNotInstanceOf(AutoCloseable.class, c.delegate());
        assertSame(mockStatement, c.delegate());
    }

    @Test
    public final void testCreateStatementIntInt() throws SQLException {
        Statement mockStatement = mock(Statement.class);
        when(mockConnection.createStatement(anyInt(), anyInt())).thenReturn(mockStatement);

        AutoCloseStatement c = armConnection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

        verify(mockConnection).createStatement(anyInt(), anyInt());
        assertInstanceOf(Statement.class, c);
        assertInstanceOf(AutoCloseable.class, c);
        assertInstanceOf(Statement.class, c.delegate());
        assertNotInstanceOf(AutoCloseable.class, c.delegate());
        assertSame(mockStatement, c.delegate());
    }

    @Test
    public final void testCreateStatementIntIntInt() throws SQLException {
        Statement mockStatement = mock(Statement.class);
        when(mockConnection.createStatement(anyInt(), anyInt(), anyInt())).thenReturn(mockStatement);

        AutoCloseStatement c = armConnection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY, ResultSet.CLOSE_CURSORS_AT_COMMIT);

        verify(mockConnection).createStatement(anyInt(), anyInt(), anyInt());
        assertInstanceOf(Statement.class, c);
        assertInstanceOf(AutoCloseable.class, c);
        assertInstanceOf(Statement.class, c.delegate());
        assertNotInstanceOf(AutoCloseable.class, c.delegate());
        assertSame(mockStatement, c.delegate());
    }

    @Test
    public final void testPrepareCallString() throws SQLException {
        CallableStatement mockStatement = mock(CallableStatement.class);
        when(mockConnection.prepareCall(anyString())).thenReturn(mockStatement);

        AutoCloseCallableStatement c = armConnection.prepareCall("test");

        verify(mockConnection).prepareCall(anyString());
        assertInstanceOf(CallableStatement.class, c);
        assertInstanceOf(AutoCloseable.class, c);
        assertInstanceOf(CallableStatement.class, c.delegate());
        assertNotInstanceOf(AutoCloseable.class, c.delegate());
        assertSame(mockStatement, c.delegate());
    }

    @Test
    public final void testPrepareCallStringIntInt() throws SQLException {
        CallableStatement mockStatement = mock(CallableStatement.class);
        when(mockConnection.prepareCall(anyString(), anyInt(), anyInt())).thenReturn(mockStatement);

        AutoCloseCallableStatement c = armConnection.prepareCall("test", 1, 2);

        verify(mockConnection).prepareCall(anyString(), anyInt(), anyInt());
        assertInstanceOf(CallableStatement.class, c);
        assertInstanceOf(AutoCloseable.class, c);
        assertInstanceOf(CallableStatement.class, c.delegate());
        assertNotInstanceOf(AutoCloseable.class, c.delegate());
        assertSame(mockStatement, c.delegate());
    }

    @Test
    public final void testPrepareCallStringIntIntInt() throws SQLException {
        CallableStatement mockStatement = mock(CallableStatement.class);
        when(mockConnection.prepareCall(anyString(), anyInt(), anyInt(), anyInt())).thenReturn(mockStatement);

        AutoCloseCallableStatement c = armConnection.prepareCall("test", 1, 2, 3);

        verify(mockConnection).prepareCall(anyString(), anyInt(), anyInt(), anyInt());
        assertInstanceOf(CallableStatement.class, c);
        assertInstanceOf(AutoCloseable.class, c);
        assertInstanceOf(CallableStatement.class, c.delegate());
        assertNotInstanceOf(AutoCloseable.class, c.delegate());
        assertSame(mockStatement, c.delegate());
    }

    @Test
    public final void testPrepareStatementString() throws SQLException {
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);

        AutoClosePreparedStatement c = armConnection.prepareStatement("test");

        verify(mockConnection).prepareStatement(anyString());
        assertInstanceOf(PreparedStatement.class, c);
        assertInstanceOf(AutoCloseable.class, c);
        assertInstanceOf(PreparedStatement.class, c.delegate());
        assertNotInstanceOf(AutoCloseable.class, c.delegate());
        assertSame(mockStatement, c.delegate());
    }

    @Test
    public final void testPrepareStatementStringInt() throws SQLException {
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString(), anyInt())).thenReturn(mockStatement);

        AutoClosePreparedStatement c = armConnection.prepareStatement("test", 1);

        verify(mockConnection).prepareStatement(anyString(), anyInt());
        assertInstanceOf(PreparedStatement.class, c);
        assertInstanceOf(AutoCloseable.class, c);
        assertInstanceOf(PreparedStatement.class, c.delegate());
        assertNotInstanceOf(AutoCloseable.class, c.delegate());
        assertSame(mockStatement, c.delegate());
    }

    @Test
    public final void testPrepareStatementStringIntInt() throws SQLException {
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString(), anyInt(), anyInt())).thenReturn(mockStatement);

        AutoClosePreparedStatement c = armConnection.prepareStatement("test", 1, 2);

        verify(mockConnection).prepareStatement(anyString(), anyInt(), anyInt());
        assertInstanceOf(PreparedStatement.class, c);
        assertInstanceOf(AutoCloseable.class, c);
        assertInstanceOf(PreparedStatement.class, c.delegate());
        assertNotInstanceOf(AutoCloseable.class, c.delegate());
        assertSame(mockStatement, c.delegate());
    }

    @Test
    public final void testPrepareStatementStringIntIntInt() throws SQLException {
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString(), anyInt(), anyInt(), anyInt())).thenReturn(mockStatement);

        AutoClosePreparedStatement c = armConnection.prepareStatement("test", 1, 2, 3);

        verify(mockConnection).prepareStatement(anyString(), anyInt(), anyInt(), anyInt());
        assertInstanceOf(PreparedStatement.class, c);
        assertInstanceOf(AutoCloseable.class, c);
        assertInstanceOf(PreparedStatement.class, c.delegate());
        assertNotInstanceOf(AutoCloseable.class, c.delegate());
        assertSame(mockStatement, c.delegate());
    }

    @Test
    public final void testPrepareStatementStringIntArray() throws SQLException {
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString(), anyIntArray())).thenReturn(mockStatement);

        AutoClosePreparedStatement c = armConnection.prepareStatement("test", new int[] { 0 });

        verify(mockConnection).prepareStatement(anyString(), anyIntArray());
        assertInstanceOf(PreparedStatement.class, c);
        assertInstanceOf(AutoCloseable.class, c);
        assertInstanceOf(PreparedStatement.class, c.delegate());
        assertNotInstanceOf(AutoCloseable.class, c.delegate());
        assertSame(mockStatement, c.delegate());
    }

    @Test
    public final void testPrepareStatementStringStringArray() throws SQLException {
        PreparedStatement mockStatement = mock(PreparedStatement.class);
        when(mockConnection.prepareStatement(anyString(), anyStringArray())).thenReturn(mockStatement);

        AutoClosePreparedStatement c = armConnection.prepareStatement("test", new String[] { "1" });

        verify(mockConnection).prepareStatement(anyString(), anyStringArray());
        assertInstanceOf(PreparedStatement.class, c);
        assertInstanceOf(AutoCloseable.class, c);
        assertInstanceOf(PreparedStatement.class, c.delegate());
        assertNotInstanceOf(AutoCloseable.class, c.delegate());
        assertSame(mockStatement, c.delegate());
    }

}
