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
import static com.google.code.arm.sql.AutoCloseables.arm;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import org.junit.Test;

import com.google.code.arm.sql.AutoCloseDataSource;
import com.google.code.arm.sql.AutoClosePreparedStatement;

public class ResourceCloseTest {

    public static void assertAutoCloseable(Object obj) {
        assertInstanceOf(AutoCloseable.class, obj);
    }

    @Test
    public void testDataSource() throws Exception {
        DataSource mockDataSource = mock(DataSource.class);
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockDataSource.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        AutoCloseDataSource ds = arm(mockDataSource);
        assertNotNull(ds);
        assertNotNull(ds.delegate());

        Connection c = ds.getConnection();
        assertAutoCloseable(c);

        try {
            PreparedStatement stmt = c.prepareStatement("SELECT 1 from dual");
            assertAutoCloseable(stmt);
            try {
                ResultSet rs = stmt.executeQuery();
                assertAutoCloseable(rs);
                try {
                    rs.next();
                } finally {
                    assertFalse(rs.isClosed());
                    rs.close();
                    verify(mockResultSet).close();
                }
            } finally {
                assertFalse(stmt.isClosed());
                stmt.close();
                verify(mockPreparedStatement).close();
            }
        } finally {
            assertFalse(c.isClosed());
            c.close();
            verify(mockConnection).close();
        }
    }

    @Test
    public void testConnection() throws Exception {
        Connection mockConnection = mock(Connection.class);
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        Connection c = arm(mockConnection);
        assertAutoCloseable(c);

        try {
            PreparedStatement stmt = c.prepareStatement("SELECT 1 from dual");
            assertAutoCloseable(stmt);
            try {
                ResultSet rs = stmt.executeQuery();
                assertAutoCloseable(rs);
                try {
                    rs.next();
                } finally {
                    assertFalse(rs.isClosed());
                    rs.close();
                    verify(mockResultSet).close();
                }
            } finally {
                assertFalse(stmt.isClosed());
                stmt.close();
                verify(mockPreparedStatement).close();
            }
        } finally {
            assertFalse(c.isClosed());
            c.close();
            verify(mockConnection).close();
        }
    }

    @Test
    public void testResultSet() throws Exception {
        ResultSet mockResultSet = mock(ResultSet.class);
        ResultSet rs = arm(mockResultSet);
        assertAutoCloseable(rs);
        try {
            rs.next();
        } finally {
            assertFalse(rs.isClosed());
            rs.close();
            verify(mockResultSet).close();
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
                verify(mockResultSet).close();
            }
        } finally {
            assertFalse(stmt.isClosed());
            stmt.close();
            verify(mockStatement).close();
        }
    }

    @Test
    public void testPreparedStatement() throws Exception {
        PreparedStatement mockPreparedStatement = mock(PreparedStatement.class);
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        AutoClosePreparedStatement stmt = arm(mockPreparedStatement);
        assertAutoCloseable(stmt);
        try {
            stmt.setString(1, "a");
            ResultSet rs = stmt.executeQuery();
            assertAutoCloseable(rs);
            try {
                rs.next();
            } finally {
                assertFalse(rs.isClosed());
                rs.close();
                verify(mockResultSet).close();
            }
        } finally {
            assertFalse(stmt.isClosed());
            stmt.close();
            verify(mockPreparedStatement).close();
        }
        verify(mockPreparedStatement).setString(anyInt(), anyString());
    }
}

