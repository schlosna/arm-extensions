package com.google.code.arm.sql;

import java.sql.NClob;

public abstract class DelegatingNClob<S extends NClob> extends DelegatingClob<S> implements NClob {

    protected DelegatingNClob(S delegate) {
        super(delegate);
    }

}
