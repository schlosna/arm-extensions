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
import java.sql.SQLException;
import java.util.Properties;

/**
 * Wraps a {@link Driver} so that it provides {@link AutoCloseable} {@link Connection} instances from the
 * {@link #connect(String, Properties)} method.
 */
public class AutoCloseDriver extends DelegatingDriver<Driver> implements Driver {

    /**
     * Returns a wrapped {@link Driver} that creates {@link AutoCloseable} {@link Connection}s from the given {@link Driver}
     * 
     * @param driver
     *            the Driver
     * @return the {@link AutoCloseDriver}
     */
    public static AutoCloseDriver from(Driver driver) {
        if (driver instanceof AutoCloseDriver) {
            return (AutoCloseDriver) driver;
        }

        return new AutoCloseDriver(driver);
    }

    private AutoCloseDriver(Driver driver) {
        super(driver);
    }

    /**
     * Returns an {@link AutoCloseable} {@link Connection}
     * 
     * @see Driver#connect(java.lang.String, java.util.Properties)
     */
    @Override
    public AutoCloseConnection connect(String url, Properties info) throws SQLException {
        return AutoCloseConnection.from(super.connect(url, info));
    }

}
