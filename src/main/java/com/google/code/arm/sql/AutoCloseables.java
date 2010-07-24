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

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLXML;
import java.sql.Statement;

import javax.sql.DataSource;
import javax.sql.PooledConnection;

public class AutoCloseables {

    public static <T extends Array> AutoCloseArray<T> arm(T array) {
        return AutoCloseArray.from(array);
    }

    public static <T extends Blob> AutoCloseBlob<T> arm(T blob) {
        return AutoCloseBlob.from(blob);
    }

    public static <T extends CallableStatement> AutoCloseCallableStatement<T> arm(T callableStatement) {
        return AutoCloseCallableStatement.from(callableStatement);
    }

    public static <T extends Clob> AutoCloseClob<T> arm(T clob) {
        return AutoCloseClob.from(clob);
    }

    public static <T extends Connection> AutoCloseConnection<T> arm(T connection) {
        return AutoCloseConnection.from(connection);
    }

    public static <T extends DataSource> AutoCloseDataSource<T> arm(T dataSource) {
        return AutoCloseDataSource.from(dataSource);
    }

    public static <T extends NClob> AutoCloseNClob<T> arm(T nClob) {
        return AutoCloseNClob.from(nClob);
    }

    public static <T extends PooledConnection> AutoClosePooledConnection<T> arm(T pooledConnection) {
        return AutoClosePooledConnection.from(pooledConnection);
    }

    public static <T extends PreparedStatement> AutoClosePreparedStatement<T> arm(T preparedStatement) {
        return AutoClosePreparedStatement.from(preparedStatement);
    }

    public static <T extends ResultSet> AutoCloseResultSet<T> arm(T resultSet) {
        return AutoCloseResultSet.from(resultSet);
    }

    public static <T extends SQLXML> AutoCloseSQLXML<T> arm(T sqlXml) {
        return AutoCloseSQLXML.from(sqlXml);
    }

    public static <T extends Statement> AutoCloseStatement<T> arm(T statement) {
        return AutoCloseStatement.from(statement);
    }
}
