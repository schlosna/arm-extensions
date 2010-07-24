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

import java.sql.SQLException;

import javax.sql.DataSource;

public class AutoCloseDataSource extends DelegatingDataSource<DataSource> {

    public static AutoCloseDataSource from(DataSource delegate) {
        if (delegate instanceof AutoCloseDataSource) {
            return (AutoCloseDataSource) delegate;
        }

        return new AutoCloseDataSource(delegate);
    }

    private AutoCloseDataSource(DataSource delegate) {
        super(delegate);
    }

    /**
     * @see com.google.code.arm.sql.DelegatingDataSource#getConnection()
     */
    @Override
    public AutoCloseConnection getConnection() throws SQLException {
        return AutoCloseConnection.from(super.getConnection());
    }

    /**
     * @see com.google.code.arm.sql.DelegatingDataSource#getConnection(java.lang.String, java.lang.String)
     */
    @Override
    public AutoCloseConnection getConnection(String username, String password) throws SQLException {
        return AutoCloseConnection.from(super.getConnection(username, password));
    }
}
