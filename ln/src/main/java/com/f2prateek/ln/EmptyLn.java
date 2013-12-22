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

/**
 * An empty Ln. Does nothing. Zilch. Nada. Meant for release builds.
 *
 * You could also use {@link com.f2prateek.ln.DebugLn} with a log level of {@link
 * android.util.Log#ASSERT} to log nothing.
 * This is slightly more efficient since it doesn't perform any check at all.
 *
 * If you don't need all available methods in {@link com.f2prateek.ln.LnInterface}, you can
 * subclass
 * this class instead and override the methods you need.
 */
public class EmptyLn implements LnInterface {
  @Override public void v(Throwable t) {

  }

  @Override public void v(Object s1, Object... args) {

  }

  @Override public void v(Throwable throwable, Object s1, Object... args) {

  }

  @Override public void d(Throwable t) {

  }

  @Override public void d(Object s1, Object... args) {

  }

  @Override public void d(Throwable throwable, Object s1, Object... args) {

  }

  @Override public void i(Throwable t) {

  }

  @Override public void i(Throwable throwable, Object s1, Object... args) {

  }

  @Override public void i(Object s1, Object... args) {

  }

  @Override public void w(Throwable t) {

  }

  @Override public void w(Throwable throwable, Object s1, Object... args) {

  }

  @Override public void w(Object s1, Object... args) {

  }

  @Override public void e(Throwable t) {

  }

  @Override public void e(Throwable throwable, Object s1, Object... args) {

  }

  @Override public void e(Object s1, Object... args) {

  }

  @Override public int getLoggingLevel() {
    return -1;
  }

  @Override public void setLoggingLevel(int level) {

  }
}
