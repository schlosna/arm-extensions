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
import static com.google.code.arm.sql.AutoCloseables.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Test;

public class AutoCloseTest {

    public static void assertAutoCloseable(Object obj) {
        assertInstanceOf(AutoCloseable.class, obj);
    }

    @Test
    public void testDataSource() throws Exception {
        // mocks
        DataSource mockDataSource = mock(DataSource.class);
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.TRUE);

        AutoCloseDataSource ds = arm(mockDataSource);
        assertNotNull(ds);
        assertNotNull(ds.delegate());

        try (AutoCloseConnection c = ds.getConnection()) {
            assertAutoCloseable(c);
            assertFalse(c.isClosed());
            assertFalse(mockConnection.isClosed());

            try (AutoClosePreparedStatement stmt = c.prepareStatement("SELECT 1 from dual")) {
                assertAutoCloseable(stmt);
                assertFalse(stmt.isClosed());
                assertFalse(mockPreparedStatement.isClosed());

                try (AutoCloseResultSet rs = stmt.executeQuery()) {
                    assertAutoCloseable(rs);
                    assertFalse(rs.isClosed());
                    assertFalse(mockResultSet.isClosed());
                    rs.next();
                    assertFalse(rs.isClosed());
                    assertFalse(stmt.isClosed());
                }
                verify(mockResultSet).close();
                assertFalse(stmt.isClosed());
            }
            verify(mockPreparedStatement).close();
            assertFalse(c.isClosed());
        } finally {}
        verify(mockConnection).close();
    }

    @Test
    public void testDataSourceCompound() throws Exception {
        // mocks
        DataSource mockDataSource = mock(DataSource.class);
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);

        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.TRUE, Boolean.TRUE, Boolean.FALSE); // simulate 2 records

        try ( AutoCloseConnection c = arm(mockDataSource).getConnection();
                AutoClosePreparedStatement stmt = c.prepareStatement("SELECT 1 from dual");
                AutoCloseResultSet rs = stmt.executeQuery() ) {

            assertAutoCloseable(c);
            assertAutoCloseable(stmt);
            assertAutoCloseable(rs);

            assertFalse(c.isClosed());
            assertFalse(stmt.isClosed());
            assertFalse(rs.isClosed());
            assertFalse(mockConnection.isClosed());
            assertFalse(mockPreparedStatement.isClosed());
            assertFalse(mockResultSet.isClosed());

            while (rs.next()) {
                assertFalse(rs.isClosed());
            }

            assertFalse(c.isClosed());
            assertFalse(stmt.isClosed());
            assertFalse(rs.isClosed());
            assertFalse(mockConnection.isClosed());
            assertFalse(mockPreparedStatement.isClosed());
            assertFalse(mockResultSet.isClosed());
        }

        // ensure all closed
        verify(mockResultSet).close();
        verify(mockPreparedStatement).close();
        verify(mockConnection).close();
    }

    @Test
    public void testConnection() throws Exception {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);        

        try (AutoCloseConnection c = arm(mockConnection)) {
            assertAutoCloseable(c);

            try (AutoClosePreparedStatement stmt = c.prepareStatement("SELECT 1 from dual")) {
                assertAutoCloseable(stmt);
                try (AutoCloseResultSet rs = stmt.executeQuery()) {
                    assertAutoCloseable(rs);
                    rs.next();
                    verify(mockResultSet, never()).close();
                    assertFalse(rs.isClosed());
                } finally {
                    verify(mockResultSet).close();
                    verify(mockPreparedStatement, never()).close();
                    assertFalse(stmt.isClosed());
                }
            } finally {
                verify(mockPreparedStatement).close();
                verify(mockConnection, never()).close();
                assertFalse(c.isClosed());
            }
        }
        verify(mockConnection).close();
    }

    @Test
    public void testResultSet() throws Exception {
        ResultSet mockResultSet = mock(ResultSet.class);

        try (AutoCloseResultSet rs = arm(mockResultSet)) {
            assertAutoCloseable(rs);
            rs.next();
            assertFalse(rs.isClosed());
        }
        verify(mockResultSet).close();
    }

    @Test
    public void testResultSetThrows() throws Exception {
        ResultSet mockResultSet = mock(ResultSet.class);
        doThrow(new SQLException("failing result set")).when(mockResultSet).next();
        doThrow(new SQLException("failing close")).when(mockResultSet).close();
        try {
            try (AutoCloseResultSet rs = arm(mockResultSet)) {
                assertAutoCloseable(rs);
                rs.next();
                assertFalse(rs.isClosed());
            } finally {
                verify(mockResultSet).close();
            }
        } catch (SQLException expected) {
            assertEquals("failing result set", expected.getMessage());
            Throwable[] suppressedExceptions = expected.getSuppressedExceptions();
            assertEquals(1, suppressedExceptions.length);
            assertEquals("failing close", suppressedExceptions[0].getMessage());
        }
    }

    @Test
    public void testStatement() throws Exception {
        Statement mockStatement = mock(Statement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);

        Statement stmt = arm(mockStatement);
        assertAutoCloseable(stmt);

        try {
            ResultSet rs = stmt.executeQuery("SELECT 1 FROM dual");
            assertAutoCloseable(rs);
            try {
                rs.next();
            } finally {
                assertFalse(rs.isClosed());
                rs.close();
            }
        } finally {
            assertFalse(stmt.isClosed());
            stmt.close();
        }
        verify(mockResultSet).close();
        verify(mockStatement).close();
    }

    @Test
    public void testPreparedStatement() throws Exception {
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockPreparedStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        AutoClosePreparedStatement stmt = arm(mockPreparedStatement);
        assertAutoCloseable(stmt);
        try {
            stmt.setString(1, "a");
            ResultSet rs = stmt.executeQuery("SELECT 1 FROM dual");
            assertAutoCloseable(rs);
            try {
                rs.next();
            } finally {
                assertFalse(rs.isClosed());
                rs.close();
            }
        } finally {
            assertFalse(stmt.isClosed());
            stmt.close();
        }
        verify(mockPreparedStatement).setString(anyInt(), anyString());
        verify(mockResultSet).close();
        verify(mockPreparedStatement).close();
    }
}
