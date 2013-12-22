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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class) @Config(manifest = Config.NONE)
public class LnFacadeIntegrationTests {

  private static final Throwable THROWABLE = new Throwable("test");
  private static final String FORMAT = "%s, %d";
  private static final String ARG1 = "Hello";
  private static final int ARG2 = 1;
  LnInterface lnInterface;

  @Before public void setUp() {
    lnInterface = mock(LnInterface.class);
    LnFacade facade = new LnFacade(Log.VERBOSE, lnInterface);
    Ln.set(facade);
  }

  @Test public void verboseIsCalledCorrectly() {
    Ln.v(THROWABLE);
    verify(lnInterface).v(THROWABLE);
    Ln.v(FORMAT, ARG1, ARG2);
    verify(lnInterface).v(FORMAT, ARG1, ARG2);
    Ln.v(THROWABLE, FORMAT, ARG1, ARG2);
    verify(lnInterface).v(THROWABLE, FORMAT, ARG1, ARG2);
  }

  @Test public void debugIsCalledCorrectly() {
    Ln.d(THROWABLE);
    verify(lnInterface).d(THROWABLE);
    Ln.d(FORMAT, ARG1, ARG2);
    verify(lnInterface).d(FORMAT, ARG1, ARG2);
    Ln.d(THROWABLE, FORMAT, ARG1, ARG2);
    verify(lnInterface).d(THROWABLE, FORMAT, ARG1, ARG2);
  }

  @Test public void infoIsCalledCorrectly() {
    Ln.i(THROWABLE);
    verify(lnInterface).i(THROWABLE);
    Ln.i(FORMAT, ARG1, ARG2);
    verify(lnInterface).i(FORMAT, ARG1, ARG2);
    Ln.i(THROWABLE, FORMAT, ARG1, ARG2);
    verify(lnInterface).i(THROWABLE, FORMAT, ARG1, ARG2);
  }

  @Test public void warnIsCalledCorrectly() {
    Ln.w(THROWABLE);
    verify(lnInterface).w(THROWABLE);
    Ln.w(FORMAT, ARG1, ARG2);
    verify(lnInterface).w(FORMAT, ARG1, ARG2);
    Ln.w(THROWABLE, FORMAT, ARG1, ARG2);
    verify(lnInterface).w(THROWABLE, FORMAT, ARG1, ARG2);
  }

  @Test public void errorIsCalledCorrectly() {
    Ln.e(THROWABLE);
    verify(lnInterface).e(THROWABLE);
    Ln.e(FORMAT, ARG1, ARG2);
    verify(lnInterface).e(FORMAT, ARG1, ARG2);
    Ln.e(THROWABLE, FORMAT, ARG1, ARG2);
    verify(lnInterface).e(THROWABLE, FORMAT, ARG1, ARG2);
  }
}

