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

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Utility similar to {@link DriverManager} for returning {@link AutoCloseable} connections from the
 * {@link DriverManager}.
 */
public class AutoCloseDriverManager {

    private AutoCloseDriverManager() {
    }

    /**
     * Attempts to establish a connection to the given database URL. The {@link DriverManager} attempts to select an
     * appropriate
     * driver from the set of registered JDBC drivers.
     * 
     * @param url
     *            a database URL of the form <code>jdbc:<em>subprotocol</em>:<em>subname</em>
     * @return an {@link AutoCloseable} {@link Connection} to the URL
     * @throws SQLException
     *             if a database access error occurs
     */
    public static AutoCloseConnection getConnection(String url) throws SQLException {
        return AutoCloseConnection.from(DriverManager.getConnection(url));
    }

    /**
     * Attempts to establish a connection to the given database URL.
     * The {@link DriverManager} attempts to select an appropriate driver from
     * the set of registered JDBC drivers.
     * 
     * @param url
     *            a database URL of the form <code>jdbc:<em>subprotocol</em>:<em>subname</em>
     * @param user
     *            the database user on whose behalf the connection is being
     *            made
     * @param password
     *            the user's password
     * @return an {@link AutoCloseable} {@link Connection} to the URL
     * @throws SQLException
     *             if a database access error occurs
     */
    public static AutoCloseConnection getConnection(String url, String user, String password) throws SQLException {
        return AutoCloseConnection.from(DriverManager.getConnection(url, user, password));
    }

    /**
     * Attempts to establish a connection to the given database URL.
     * The {@link DriverManager} attempts to select an appropriate driver from
     * the set of registered JDBC drivers.
     * 
     * @param url
     *            a database URL of the form <code>jdbc:<em>subprotocol</em>:<em>subname</em>
     * @param properties
     *            a list of arbitrary string tag/value pairs as
     *            connection arguments; normally at least a "user" and
     *            "password" property should be included
     * @return an {@link AutoCloseable} {@link Connection} to the URL
     * @throws SQLException
     *             if a database access error occurs
     */
    public static AutoCloseConnection getConnection(String url, Properties properties) throws SQLException {
        return AutoCloseConnection.from(DriverManager.getConnection(url, properties));
    }

    /**
     * Attempts to locate a driver that understands the given URL.
     * The {@link DriverManager} attempts to select an appropriate driver from
     * the set of registered JDBC drivers.
     * 
     * @param url
     *            a database URL of the form <code>jdbc:<em>subprotocol</em>:<em>subname</em>
     * @return an {@link AutoCloseable} {@link Driver} object representing a driver
     *         that can connect to the given URL
     * @throws SQLException
     *             if a database access error occurs
     */
    public static AutoCloseDriver getDriver(String url) throws SQLException {
        return AutoCloseDriver.from(DriverManager.getDriver(url));
    }
}
