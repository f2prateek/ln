/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 Prateek Srivastava
 * Copyright (c) 2010 Xtreme Labs and Pivotal Labs
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.f2prateek.ln;

import android.util.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class) @Config(manifest = Config.NONE)
public class EmptyLnTest {

  private static final String HELLO_WORLD = "Hello, world!";

  @Before public void setUp() {
    Ln.set(new EmptyLn());
    Ln.setLoggingLevel(Log.VERBOSE);
  }

  @Test public void verboseTagDoesNothing() {
    Ln.v(HELLO_WORLD);
    assertThat(ShadowLog.getLogs()).hasSize(0);
  }

  @Test public void debugTagDoesNothing() {
    Ln.d(HELLO_WORLD);
    assertThat(ShadowLog.getLogs()).hasSize(0);
  }

  @Test public void infoTagDoesNothing() {
    Ln.i(HELLO_WORLD);
    assertThat(ShadowLog.getLogs()).hasSize(0);
  }
}
