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

import android.os.AsyncTask;
import android.util.Log;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import static org.fest.assertions.api.Assertions.assertThat;

@RunWith(RobolectricTestRunner.class) @Config(manifest = Config.NONE)
public class LnTest {

  private static final String HELLO_WORLD = "Hello, world!";

  @Before public void setUp() {
    Ln.set(DebugLn.from(Robolectric.getShadowApplication().getApplicationContext()));
    Ln.setLoggingLevel(Log.VERBOSE);
  }

  @Test public void verboseTagWorks() {
    Ln.v(HELLO_WORLD);
    helloWorldTest(Log.VERBOSE);
  }

  @Test public void debugTagWorks() {
    Ln.d(HELLO_WORLD);
    helloWorldTest(Log.DEBUG);
  }

  @Test public void infoTagWorks() {
    Ln.i(HELLO_WORLD);
    helloWorldTest(Log.INFO);
  }

  @Test public void warnTagWorks() {
    Ln.w(HELLO_WORLD);
    helloWorldTest(Log.WARN);
  }

  @Test public void errorTagWorks() {
    Ln.e(HELLO_WORLD);
    helloWorldTest(Log.ERROR);
  }

  @Test public void errorTagWorksWithThrowable() {
    Throwable throwable = new Throwable();
    Ln.e(throwable, HELLO_WORLD);
    List<ShadowLog.LogItem> logs = ShadowLog.getLogs();
    assertThat(logs).hasSize(1);
    ShadowLog.LogItem log = logs.get(0);
    assertLogType(log, Log.ERROR);
    assertThat(log.msg).contains(HELLO_WORLD);
    assertThat(log.msg).contains("main");
    assertThat(log.msg).contains(Log.getStackTraceString(throwable));
  }

  @Test public void minimumLevelInfoWorks() {
    Ln.setLoggingLevel(Log.INFO);

    Ln.v(HELLO_WORLD);
    Ln.d(HELLO_WORLD);
    Ln.i(HELLO_WORLD);
    Ln.w(HELLO_WORLD);
    Ln.e(HELLO_WORLD);

    List<ShadowLog.LogItem> logs = ShadowLog.getLogs();
    assertThat(logs).hasSize(3);

    ShadowLog.LogItem infoLog = logs.get(0);
    assertLogType(infoLog, Log.INFO);
    assertThat(infoLog.msg).contains(HELLO_WORLD);

    ShadowLog.LogItem warnLog = logs.get(1);
    assertLogType(warnLog, Log.WARN);
    assertThat(warnLog.msg).contains(HELLO_WORLD);

    ShadowLog.LogItem errorLog = logs.get(2);
    assertLogType(errorLog, Log.ERROR);
    assertThat(errorLog.msg).contains(HELLO_WORLD);
  }

  @Test public void minimumLevelErrorWorks() {
    Ln.setLoggingLevel(Log.ERROR);

    Ln.v(HELLO_WORLD);
    Ln.d(HELLO_WORLD);
    Ln.i(HELLO_WORLD);
    Ln.w(HELLO_WORLD);
    Ln.e(HELLO_WORLD);

    List<ShadowLog.LogItem> logs = ShadowLog.getLogs();
    assertThat(logs).hasSize(1);

    ShadowLog.LogItem infoLog = logs.get(0);
    assertLogType(infoLog, Log.ERROR);
    assertThat(infoLog.msg).contains(HELLO_WORLD);
  }

  @Test public void testNonMainThread() {
    // This is a useless test.
    // We should expect the log message to contain a thread other than main,
    // but it doesn't! ShadowAsyncTask might be to blame....
    new AsyncTask<Void, Void, Void>() {
      @Override protected Void doInBackground(Void... params) {
        Ln.d(HELLO_WORLD);
        return null;
      }
    }.execute();

    List<ShadowLog.LogItem> logs = ShadowLog.getLogs();
    assertThat(logs).hasSize(1);
    ShadowLog.LogItem log = logs.get(0);
    assertLogType(log, Log.DEBUG);
    assertThat(log.msg).contains(HELLO_WORLD);
  }

  private void helloWorldTest(int type) {
    List<ShadowLog.LogItem> logs = ShadowLog.getLogs();
    assertThat(logs).hasSize(1);
    ShadowLog.LogItem log = logs.get(0);
    assertLogType(log, type);
    assertThat(log.msg).contains(HELLO_WORLD);
    assertThat(log.msg).contains("main");
    assertThat(log.throwable).isNull();
  }

  private void assertLogType(ShadowLog.LogItem log, int type) {
    assertThat(log.type).overridingErrorMessage("Expected level to be <%s> but was <%s>.",
        Ln.logLevelToString(type), Ln.logLevelToString(log.type)).isEqualTo(type);
  }
}