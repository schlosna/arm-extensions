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

import static com.google.code.arm.Assert.assertInstanceOf;
import static com.google.code.arm.Assert.assertNotInstanceOf;

import org.junit.Test;

/**
 * Tests for {@link Assert}
 */
public class AssertTest {

    @Test
    public final void testAssertInstanceOf() {
        assertInstanceOf(Object.class, this);
        assertInstanceOf(Object.class, "foo");
        assertInstanceOf(String.class, "foo");
        assertInstanceOf(Integer.class, Integer.valueOf(4));
        assertInstanceOf(Object.class, Integer.valueOf(4));
    }

    @Test(expected = AssertionError.class)
    public final void testAssertInstanceOfNull() {
        assertInstanceOf(Object.class, null);
    }

    @Test(expected = AssertionError.class)
    public final void testAssertInstanceOfThrows() {
        // should throw AssertionError as it is not instanceof
        assertInstanceOf(String.class, Integer.valueOf(4));
    }

    @Test
    public final void testAssertNotInstanceOf() {
        assertNotInstanceOf(Object.class, null);
        assertNotInstanceOf(String.class, Integer.valueOf(4));
        assertNotInstanceOf(Integer.class, "foo");
    }

    @Test(expected = AssertionError.class)
    public final void testAssertNotInstanceOfThrows() {
        // should throw AssertionError as it is instanceof
        assertNotInstanceOf(String.class, "foo");
    }
}
