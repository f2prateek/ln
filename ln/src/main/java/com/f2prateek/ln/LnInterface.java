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
 * LnInterface that {@link com.f2prateek.ln.Ln} talks to.
 * Implement this interface to provide your own endpoints, and then call {@link
 * Ln#set(LnInterface)}
 * with an instance of your endpoint.
 *
 * To post to multiple endpoints, create instances of your implementations of LnInterface, add them
 * to {@link LnFacade} and call {@link Ln#set(LnInterface)} with an an instance of
 * {@link LnFacade}.
 */
public interface LnInterface {

  /**
   * Send a {@link android.util.Log#VERBOSE} log message.
   *
   * @param t An exception to log
   */
  void v(Throwable t);

  /**
   * Send a {@link android.util.Log#VERBOSE} log message.
   *
   * @param s1 the format string (see {@link java.util.Formatter#format})
   * @param args the list of arguments passed to the formatter
   */
  void v(Object s1, Object... args);

  /**
   * Send a {@link android.util.Log#VERBOSE} log message.
   *
   * @param throwable An exception to log
   * @param s1 the format string (see {@link java.util.Formatter#format})
   * @param args the list of arguments passed to the formatter
   */
  void v(Throwable throwable, Object s1, Object[] args);

  /**
   * Send a {@link android.util.Log#DEBUG} log message.
   *
   * @param t An exception to log
   */
  void d(Throwable t);

  /**
   * Send a {@link android.util.Log#DEBUG} log message.
   *
   * @param s1 the format string (see {@link java.util.Formatter#format})
   * @param args the list of arguments passed to the formatter
   */
  void d(Object s1, Object... args);

  /**
   * Send a {@link android.util.Log#DEBUG} log message.
   *
   * @param throwable An exception to log
   * @param s1 the format string (see {@link java.util.Formatter#format})
   * @param args the list of arguments passed to the formatter
   */
  void d(Throwable throwable, Object s1, Object... args);

  /**
   * Send a {@link android.util.Log#INFO} log message.
   *
   * @param t An exception to log
   */
  void i(Throwable t);

  /**
   * Send a {@link android.util.Log#INFO} log message.
   *
   * @param s1 the format string (see {@link java.util.Formatter#format})
   * @param args the list of arguments passed to the formatter
   */
  void i(Object s1, Object... args);

  /**
   * Send a {@link android.util.Log#DEBUG} log message.
   *
   * @param throwable An exception to log
   * @param s1 the format string (see {@link java.util.Formatter#format})
   * @param args the list of arguments passed to the formatter
   */
  void i(Throwable throwable, Object s1, Object... args);

  /**
   * Send a {@link android.util.Log#WARN} log message.
   *
   * @param t An exception to log
   */
  void w(Throwable t);

  /**
   * Send a {@link android.util.Log#WARN} log message.
   *
   * @param s1 the format string (see {@link java.util.Formatter#format})
   * @param args the list of arguments passed to the formatter
   */
  void w(Object s1, Object... args);

  /**
   * Send a {@link android.util.Log#WARN} log message.
   *
   * @param throwable An exception to log
   * @param s1 the format string (see {@link java.util.Formatter#format})
   * @param args the list of arguments passed to the formatter
   */
  void w(Throwable throwable, Object s1, Object... args);

  /**
   * Send a {@link android.util.Log#ERROR} log message.
   *
   * @param t An exception to log
   */
  void e(Throwable t);

  /**
   * Send a {@link android.util.Log#ERROR} log message.
   *
   * @param s1 the format string (see {@link java.util.Formatter#format})
   * @param args the list of arguments passed to the formatter
   */
  void e(Object s1, Object... args);

  /**
   * Send a {@link android.util.Log#ERROR} log message.
   *
   * @param throwable An exception to log
   * @param s1 the format string (see {@link java.util.Formatter#format})
   * @param args the list of arguments passed to the formatter
   */
  void e(Throwable throwable, Object s1, Object... args);

  /**
   * Get the current logging level.
   * Should return one of :
   * 1. {@link android.util.Log#VERBOSE}
   * 2. {@link android.util.Log#ERROR}
   * 3. {@link android.util.Log#INFO}
   * 4. {@link android.util.Log#WARN}
   * 5. {@link android.util.Log#ERROR}
   * 6. {@link android.util.Log#ASSERT}
   * 7. -1 if not supported
   */
  int getLoggingLevel();

  /**
   * Set the logging level.
   * Should be one of :
   * 1. {@link android.util.Log#VERBOSE}
   * 2. {@link android.util.Log#ERROR}
   * 3. {@link android.util.Log#INFO}
   * 4. {@link android.util.Log#WARN}
   * 5. {@link android.util.Log#ERROR}
   * 6. {@link android.util.Log#ASSERT}
   */
  void setLoggingLevel(int level);
}