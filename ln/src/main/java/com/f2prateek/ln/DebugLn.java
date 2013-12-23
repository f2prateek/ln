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

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

/**
 * Implementation of {@link com.f2prateek.ln.LnInterface} that prints to {@link android.util.Log}.
 *
 * This class will use your package name, source file,  line number of the call as the tag and adds
 * the  current thread to the message. Override {@link #processTag(String)} to modify this
 * behaviour.
 *
 * You can also override {@link #processMessage(String)}. A good scenario to do this would be if
 * you wanted to automatically filter sensitive information from your logs (credit card numbers,
 * passwords, etc.).
 * Subclass this if you want to have an easy to use implementation of managing log levels.
 * You can override {@link #println(int, String)} to change the endpoint (e.g. log to crash server
 * and not {@link {@link android.util.Log}} .
 */
public class DebugLn implements LnInterface {

  private static final String TAG_FORMAT = "%s/%s:%s";
  private static final String MSG_FORMAT = "[%s] %s";
  protected int minimumLogLevel;
  protected String packageName;

  public DebugLn(String packageName, int minimumLogLevel) {
    this.packageName = packageName;
    this.minimumLogLevel = minimumLogLevel;
  }

  public static DebugLn from(final Context context) {
    String packageName = context.getPackageName();
    int minimumLogLevel = Log.INFO;
    try {
      final int flags = context.getPackageManager().getApplicationInfo(packageName, 0).flags;
      minimumLogLevel =
          (flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0 ? Log.VERBOSE : minimumLogLevel;
    } catch (PackageManager.NameNotFoundException e) {
      e.printStackTrace();
    }
    return new DebugLn(packageName, minimumLogLevel);
  }

  @Override
  public void v(Throwable t) {
    if (getLoggingLevel() > Log.VERBOSE) return;
    println(Log.VERBOSE, Log.getStackTraceString(t));
  }

  @Override
  public void v(Object s1, Object... args) {
    if (getLoggingLevel() > Log.VERBOSE) return;

    final String s = Strings.toString(s1);
    final String message = args.length > 0 ? String.format(s, args) : s;
    println(Log.VERBOSE, message);
  }

  @Override
  public void v(Throwable throwable, Object s1, Object... args) {
    if (getLoggingLevel() > Log.VERBOSE) return;

    final String s = Strings.toString(s1);
    final String message =
        (args.length > 0 ? String.format(s, args) : s) + '\n' + Log.getStackTraceString(throwable);
    println(Log.VERBOSE, message);
  }

  @Override
  public void d(Throwable t) {
    if (getLoggingLevel() > Log.DEBUG) return;
    println(Log.DEBUG, Log.getStackTraceString(t));
  }

  @Override
  public void d(Object s1, Object... args) {
    if (getLoggingLevel() > Log.DEBUG) return;

    final String s = Strings.toString(s1);
    final String message = args.length > 0 ? String.format(s, args) : s;
    println(Log.DEBUG, message);
  }

  @Override
  public void d(Throwable throwable, Object s1, Object... args) {
    if (getLoggingLevel() > Log.DEBUG) return;

    final String s = Strings.toString(s1);
    final String message =
        (args.length > 0 ? String.format(s, args) : s) + '\n' + Log.getStackTraceString(throwable);
    println(Log.DEBUG, message);
  }

  @Override
  public void i(Throwable t) {
    if (getLoggingLevel() > Log.INFO) return;
    println(Log.INFO, Log.getStackTraceString(t));
  }

  @Override
  public void i(Throwable throwable, Object s1, Object... args) {
    if (getLoggingLevel() > Log.INFO) return;

    final String s = Strings.toString(s1);
    final String message =
        (args.length > 0 ? String.format(s, args) : s) + '\n' + Log.getStackTraceString(throwable);
    println(Log.INFO, message);
  }

  @Override
  public void i(Object s1, Object... args) {
    if (getLoggingLevel() > Log.INFO) return;

    final String s = Strings.toString(s1);
    final String message = args.length > 0 ? String.format(s, args) : s;
    println(Log.INFO, message);
  }

  @Override
  public void w(Throwable t) {
    if (getLoggingLevel() > Log.WARN) return;
    println(Log.WARN, Log.getStackTraceString(t));
  }

  @Override
  public void w(Throwable throwable, Object s1, Object... args) {
    if (getLoggingLevel() > Log.WARN) return;

    final String s = Strings.toString(s1);
    final String message =
        (args.length > 0 ? String.format(s, args) : s) + '\n' + Log.getStackTraceString(throwable);
    println(Log.WARN, message);
  }

  @Override
  public void w(Object s1, Object... args) {
    if (getLoggingLevel() > Log.WARN) return;

    final String s = Strings.toString(s1);
    final String message = args.length > 0 ? String.format(s, args) : s;
    println(Log.WARN, message);
  }

  @Override
  public void e(Throwable t) {
    if (getLoggingLevel() > Log.ERROR) return;
    println(Log.ERROR, Log.getStackTraceString(t));
  }

  @Override
  public void e(Throwable throwable, Object s1, Object... args) {
    if (getLoggingLevel() > Log.ERROR) return;

    final String s = Strings.toString(s1);
    final String message =
        (args.length > 0 ? String.format(s, args) : s) + '\n' + Log.getStackTraceString(throwable);
    println(Log.ERROR, message);
  }

  @Override
  public void e(Object s1, Object... args) {
    if (getLoggingLevel() > Log.ERROR) return;

    final String s = Strings.toString(s1);
    final String message = args.length > 0 ? String.format(s, args) : s;
    println(Log.ERROR, message);
  }

  /**
   * {@inheritDoc}
   */
  public int getLoggingLevel() {
    return minimumLogLevel;
  }

  /**
   * {@inheritDoc}
   */
  public void setLoggingLevel(int level) {
    minimumLogLevel = level;
  }

  /**
   * Print the message.
   *
   * This method simply prints to {@link android.util.Log}. Override this to use the log level
   * feature and post to your endpoint.
   *
   * @param priority The message priority to print.
   * @param msg The message to print.
   */
  protected void println(int priority, String msg) {
    Log.println(priority, processTag(packageName), processMessage(msg));
  }

  /**
   * Provide a message for logging. This prepends the current thread to the message.
   * Override this if you want to filter sensitive information, or add extra information to each
   * message before dispatching.
   *
   * @param msg The message evaluated from the format, arguments and exceptions.
   * @return The message that is logged
   */
  protected String processMessage(String msg) {
    return String.format(MSG_FORMAT, Thread.currentThread().getName(), msg);
  }

  /**
   * Provide a tag for logging.
   * By default this returns a tag with the package name, file name, and line number of the calling
   * function. Override this to show different information in the tag.
   *
   * @param packageName the packageName of the app
   * @return the tag to log with
   */
  protected String processTag(String packageName) {
    final int skipDepth = 6; // skip 6 stackframes to find the location where this was called
    final Thread thread = Thread.currentThread();
    final StackTraceElement trace = thread.getStackTrace()[skipDepth];
    return String.format(TAG_FORMAT, packageName, trace.getFileName(), trace.getLineNumber());
  }
}