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

package com.f2prateek.ln.dagger.example;

import android.app.Application;
import android.content.Context;
import com.f2prateek.ln.DebugLn;
import com.f2prateek.ln.EmptyLn;
import com.f2prateek.ln.Ln;
import com.f2prateek.ln.LnInterface;
import dagger.Module;
import dagger.ObjectGraph;
import dagger.Provides;
import javax.inject.Inject;

public class DaggerApp extends Application {
  @Inject LnInterface lnInterface;
  private ObjectGraph applicationGraph;

  @Override public void onCreate() {
    super.onCreate();
    applicationGraph = ObjectGraph.create(getModules());
    // Inject the dependencies
    applicationGraph.inject(this);
    /**
     * Dagger has injected lnInterface. In this case it, it is one of
     * {@link com.f2prateek.ln.DebugLn} or {@link com.f2prateek.ln.EmptyLn}.
     */
    Ln.set(lnInterface);
  }

  protected Object getModules() {
    if (BuildConfig.DEBUG) {
      return new DebugAppModule(this);
    } else {
      return new ReleaseAppModule();
    }
  }

  @Module(injects = DaggerApp.class) class DebugAppModule {
    private final DaggerApp exampleApp;

    public DebugAppModule(DaggerApp exampleApp) {
      this.exampleApp = exampleApp;
    }

    @Provides Context provideApplicationContext() {
      return exampleApp;
    }

    @Provides LnInterface provideDebugLn(Context context) {
      return new DebugLn(context);
    }
  }

  @Module(injects = DaggerApp.class) class ReleaseAppModule {
    @Provides LnInterface provideReleaseLn() {
      return new EmptyLn();
    }
  }
}