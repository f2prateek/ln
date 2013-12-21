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

/**
 * A more natural android logging facility. Inspired by RoboGuice.
 *
 * WARNING: CHECK OUT COMMON PITFALLS BELOW
 *
 * This class simply delegates to an instance of {@link com.f2prateek.ln.LnInterface} that you can
 * set with {@link #set(LnInterface)}.
 *
 * By default, Ln is initalized with an instance of {@link com.f2prateek.ln.EmptyLn}. This
 * implementation does nothing, and is meant for release builds.
 *
 * Call {@link #set(LnInterface)} with an instance of {@link com.f2prateek.ln.DebugLn} or your own
 * implementation of {@link com.f2prateek.ln.LnInterface} to log to {@link android.util.Log} or
 * your own endpoints (could be anything, such as for a crash reporting tool or analytics tool).
 * Use {@link com.f2prateek.ln.LnFacade} to log to multiple endpoints (e.g. log to {@link
 * android.util.Log}, crash server, and analytics server).
 *
 * If used with {@link com.f2prateek.ln.DebugLn} (this MUST be done manually), your tag is
 * automatically determined. See it's documentation for what it does for you.
 *
 * Log messages may optionally use {@link String#format(String, Object...)} formatting, which will
 * not be evaluated unless the log statement is output.
 * Additional parameters to the logging statement are treated as varargs parameters to {@link
 * String#format(String, Object...)}
 *
 * A key feature is the ability to specify the logLevel {@link #setLoggingLevel(int)}.
 * When used with something like {@link com.f2prateek.ln.DebugLn}, it allows you to change what
 * type of messages get logged at different points in your app. A {@link
 * com.f2prateek.ln.LnInterface} implementation can choose to ignore this.
 *
 * COMMON PITFALLS:
 * Make sure you put the exception FIRST in the call.  A common mistake is to place it last as is
 * the android.util.Log convention, but then it will get treated as varargs parameter.
 * vararg parameters are not appended to the log message!  You must insert them into the log
 * message
 * using %s or another similar format parameter
 *
 * Usage Examples:
 *
 * Ln.v("hello there");
 * Ln.d("%s %s", "hello", "there");
 * Ln.e(exception, "Error during some operation");
 * Ln.w(exception, "Error during %s operation", "some other");
 */
public class Ln {

  /**
   * ln is initially set to EmptyLn().
   */
  protected static LnInterface ln = new EmptyLn();

  private Ln() {
  }

  public static void set(LnInterface lnInterface) {
    ln = lnInterface;
  }

  public static void v(Throwable t) {
    ln.v(t);
  }

  public static void v(Object s1, Object... args) {
    ln.v(s1, args);
  }

  public static void v(Throwable throwable, Object s1, Object... args) {
    ln.v(throwable, s1, args);
  }

  public static void d(Throwable t) {
    ln.d(t);
  }

  public static void d(Object s1, Object... args) {
    ln.d(s1, args);
  }

  public static void d(Throwable throwable, Object s1, Object... args) {
    ln.d(throwable, s1, args);
  }

  public static void i(Throwable t) {
    ln.i(t);
  }

  public static void i(Object s1, Object... args) {
    ln.i(s1, args);
  }

  public static void i(Throwable throwable, Object s1, Object... args) {
    ln.i(throwable, s1, args);
  }

  public static void w(Throwable t) {
    ln.w(t);
  }

  public static void w(Object s1, Object... args) {
    ln.w(s1, args);
  }

  public static void w(Throwable throwable, Object s1, Object... args) {
    ln.w(throwable, s1, args);
  }

  public static void e(Throwable t) {
    ln.e(t);
  }

  public static void e(Object s1, Object... args) {
    ln.e(s1, args);
  }

  public static void e(Throwable throwable, Object s1, Object... args) {
    ln.e(throwable, s1, args);
  }

  public static int getLoggingLevel() {
    return ln.getLoggingLevel();
  }

  public static void setLoggingLevel(int level) {
    ln.setLoggingLevel(level);
  }

  /**
   * Returns a string representation of the log level
   */
  public static String logLevelToString(int loglevel) {
    switch (loglevel) {
      case Log.VERBOSE:
        return "VERBOSE";
      case Log.DEBUG:
        return "DEBUG";
      case Log.INFO:
        return "INFO";
      case Log.WARN:
        return "WARN";
      case Log.ERROR:
        return "ERROR";
      case Log.ASSERT:
        return "ASSERT";
      default:
        return "UNKNOWN";
    }
  }
}