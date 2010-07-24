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
import java.sql.SQLXML;

public class AutoCloseSQLXML<S extends SQLXML> extends DelegatingSQLXML<S> implements AutoCloseable {

    public static <S extends SQLXML> AutoCloseSQLXML<S> from(S delegate) {
        if (delegate instanceof AutoCloseSQLXML) {
            @SuppressWarnings("unchecked") AutoCloseSQLXML<S> sqlxml = (AutoCloseSQLXML<S>) delegate;
            return sqlxml;
        }

        return new AutoCloseSQLXML<S>(delegate);
    }

    private AutoCloseSQLXML(S delegate) {
        super(delegate);
    }

    @Override
    public void close() throws SQLException {
        free();
    }

}
