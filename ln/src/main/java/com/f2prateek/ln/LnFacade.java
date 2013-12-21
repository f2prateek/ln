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
import java.util.ArrayList;
import java.util.Collections;

/**
 * A facade for posting to multiple {@link com.f2prateek.ln.LnInterface}'s.
 * Each endpoint is responsible for respecting the log level itself.
 */
public class LnFacade implements LnInterface {

  protected int minimumLogLevel;
  private ArrayList<LnInterface> endpoints;

  /**
   * Construct a {@link com.f2prateek.ln.LnFacade}. Requires at least one endpoint.
   * At least one instance of {@link com.f2prateek.ln.LnInterface} is required.
   * All instances are set to have a log level of {@link Log#INFO}.
   *
   * @param lnInterface An instance of {@link com.f2prateek.ln.LnInterface} you want to use
   * @param lnInterfaces Any additional instance of {@link com.f2prateek.ln.LnInterface} you want
   * to use.
   */
  public LnFacade(LnInterface lnInterface, LnInterface... lnInterfaces) {
    this(Log.INFO, lnInterface, lnInterfaces);
  }

  /**
   * Allows you to specify a initial log level for all endpoints this facade should post to.
   */
  public LnFacade(int logLevel, LnInterface lnInterface, LnInterface... lnInterfaces) {
    endpoints = new ArrayList<LnInterface>(1 + lnInterfaces.length);
    Collections.addAll(endpoints, lnInterface);
    if (lnInterfaces.length > 0) {
      Collections.addAll(endpoints, lnInterfaces);
    }
    setLoggingLevel(logLevel);
  }

  /**
   * Add an endpoint to this facade.
   */
  public void addLoggingEndpoint(LnInterface ln) {
    endpoints.add(ln);
  }

  @Override public void v(Throwable t) {
    for (LnInterface ln : endpoints) {
      ln.v(t);
    }
  }

  @Override public void v(Object s1, Object... args) {
    for (LnInterface ln : endpoints) {
      ln.v(s1, args);
    }
  }

  @Override public void v(Throwable throwable, Object s1, Object[] args) {
    for (LnInterface ln : endpoints) {
      ln.v(throwable, s1, args);
    }
  }

  @Override public void d(Throwable t) {
    for (LnInterface ln : endpoints) {
      ln.v(t);
    }
  }

  @Override public void d(Object s1, Object... args) {
    for (LnInterface ln : endpoints) {
      ln.d(s1, args);
    }
  }

  @Override public void d(Throwable throwable, Object s1, Object... args) {
    for (LnInterface ln : endpoints) {
      ln.d(throwable, s1, args);
    }
  }

  @Override public void i(Throwable t) {
    for (LnInterface ln : endpoints) {
      ln.i(t);
    }
  }

  @Override public void i(Throwable throwable, Object s1, Object... args) {
    for (LnInterface ln : endpoints) {
      ln.i(throwable, s1, args);
    }
  }

  @Override public void i(Object s1, Object... args) {
    for (LnInterface ln : endpoints) {
      ln.i(s1, args);
    }
  }

  @Override public void w(Throwable t) {
    for (LnInterface ln : endpoints) {
      ln.w(t);
    }
  }

  @Override public void w(Throwable throwable, Object s1, Object... args) {
    for (LnInterface ln : endpoints) {
      ln.w(throwable, s1, args);
    }
  }

  @Override public void w(Object s1, Object... args) {
    for (LnInterface ln : endpoints) {
      ln.w(s1, args);
    }
  }

  @Override public void e(Throwable t) {
    for (LnInterface ln : endpoints) {
      ln.v(t);
    }
  }

  @Override public void e(Throwable throwable, Object s1, Object... args) {
    for (LnInterface ln : endpoints) {
      ln.e(throwable, s1, args);
    }
  }

  @Override public void e(Object s1, Object... args) {
    for (LnInterface ln : endpoints) {
      ln.e(args);
    }
  }

  @Override public int getLoggingLevel() {
    return minimumLogLevel;
  }

  @Override public void setLoggingLevel(int level) {
    for (LnInterface ln : endpoints) {
      ln.setLoggingLevel(level);
    }
  }
}
