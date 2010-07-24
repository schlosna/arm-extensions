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
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

import org.junit.Test;

import com.google.code.arm.sql.AutoCloseDataSource;

public class AutoCloseDataSourceTest {

    @Test
    public final void testFrom() {
        DataSource mockDataSource = mock(DataSource.class);
        AutoCloseDataSource ds = AutoCloseDataSource.from(mockDataSource);

        // don't re-wrap
        assertEquals(mockDataSource, ds.delegate());
        assertSame(mockDataSource, ds.delegate());

        // ensure that it is a valid data source
        assertInstanceOf(DataSource.class, ds);
        assertInstanceOf(DataSource.class, ds.delegate());

        // DataSource cannot implement AutoCloseable
        assertNotInstanceOf(AutoCloseable.class, ds);
        assertNotInstanceOf(AutoCloseable.class, ds.delegate());
    }

    @Test
    public final void testUnwrap() throws SQLException {
        DataSource delegate = mock(DataSource.class);
        DataSource armDataSource = AutoCloseDataSource.from(delegate);

        DataSource unwrappedDataSource = armDataSource.unwrap(DataSource.class);
        assertInstanceOf(DataSource.class, unwrappedDataSource);
        // DataSource cannot implement AutoCloseable
        assertNotInstanceOf(AutoCloseable.class, unwrappedDataSource);

        AutoCloseDataSource unwrappedArmDataSource = armDataSource.unwrap(AutoCloseDataSource.class);
        assertInstanceOf(DataSource.class, unwrappedArmDataSource);
        // DataSource cannot implement AutoCloseable
        assertNotInstanceOf(AutoCloseable.class, unwrappedArmDataSource);

        assertInstanceOf(DataSource.class, unwrappedArmDataSource.delegate());
        // DataSource cannot implement AutoCloseable
        assertNotInstanceOf(AutoCloseable.class, unwrappedArmDataSource.delegate());
    }

    @Test
    public final void testIsWrapperFor() throws SQLException {
        DataSource delegate = mock(DataSource.class);
        DataSource ds = AutoCloseDataSource.from(delegate);
        assertTrue(ds.isWrapperFor(DataSource.class));
        // DataSource cannot implement AutoCloseable
        assertFalse(ds.isWrapperFor(AutoCloseable.class));
    }

    @Test
    public final void testGetConnection() throws SQLException {
        DataSource mockDataSource = mock(DataSource.class);
        Connection mockConnection = mock(Connection.class);
        when(mockDataSource.getConnection()).thenReturn(mockConnection);

        AutoCloseDataSource ds = AutoCloseDataSource.from(mockDataSource);
        Connection c = ds.getConnection();
        assertInstanceOf(Connection.class, c);
        assertInstanceOf(AutoCloseable.class, c);
    }

    @Test
    public final void testGetConnectionStringString() throws SQLException {
        DataSource mockDataSource = mock(DataSource.class);
        Connection mockConnection = mock(Connection.class);
        when(mockDataSource.getConnection(anyString(), anyString())).thenReturn(mockConnection);

        AutoCloseDataSource ds = AutoCloseDataSource.from(mockDataSource);
        Connection c = ds.getConnection("username", "password");
        assertInstanceOf(Connection.class, c);
        assertInstanceOf(AutoCloseable.class, c);
    }

}
