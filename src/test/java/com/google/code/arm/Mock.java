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

package com.google.code.arm;

import static org.mockito.Matchers.any;

/**
 * Utilities for mocking objects and testing method calls
 */
public class Mock {
    
    private Mock() {}

    /**
     * @return any {@link int[]} array argument
     */
    public static int[] anyIntArray() {
        return any(int[].class);
    }

    /**
     * @return any {@link Object[]} array argument
     */
    public static Object[] anyObjectArray() {
        return any(Object[].class);
    }

    /**
     * @return any {@link String[]} array argument
     */
    public static String[] anyStringArray() {
        return any(String[].class);
    }
}
