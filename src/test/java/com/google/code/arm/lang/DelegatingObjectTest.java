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

package com.google.code.arm.lang;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

import org.junit.Test;

import com.google.code.arm.lang.DelegatingObject;

public class DelegatingObjectTest {

    @Test
    public final void testDelegatingObject() {
        DelegatingObject<String> obj = new DelegatingObject<String>("Hi");
        assertNotNull(obj);
    }

    @Test
    public final void testDelegate() {
        String delegate = "Hi";
        DelegatingObject<String> obj = new DelegatingObject<String>(delegate);
        assertNotNull(obj.delegate());
        assertSame(delegate, obj.delegate());
        assertEquals(delegate, obj.delegate());
    }

    @Test
    public final void testToString() {
        String delegate = "Hi";
        DelegatingObject<String> obj = new DelegatingObject<String>(delegate);
        assertNotNull(obj.toString());
        assertEquals(delegate, obj.toString());
    }

}
