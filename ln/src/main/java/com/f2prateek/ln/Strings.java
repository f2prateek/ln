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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * Convenience for working with Strings for logging.
 * Not part of the public api.
 */
class Strings {
  private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;

  static <T> String join(final String delimiter, final Collection<T> objs) {
    if (objs == null || objs.isEmpty()) return "";

    final Iterator<T> iter = objs.iterator();
    final StringBuilder buffer = new StringBuilder(Strings.toString(iter.next()));

    while (iter.hasNext()) {
      final T obj = iter.next();
      if (notEmpty(obj)) buffer.append(delimiter).append(Strings.toString(obj));
    }
    return buffer.toString();
  }

  static <T> String join(final String delimiter, final T... objects) {
    return join(delimiter, Arrays.asList(objects));
  }

  static String toString(InputStream input) {
    StringWriter sw = new StringWriter();
    copy(new InputStreamReader(input), sw);
    return sw.toString();
  }

  static String toString(Reader input) {
    StringWriter sw = new StringWriter();
    copy(input, sw);
    return sw.toString();
  }

  static int copy(Reader input, Writer output) {
    long count = copyLarge(input, output);
    return count > Integer.MAX_VALUE ? -1 : (int) count;
  }

  static long copyLarge(Reader input, Writer output) {
    try {
      char[] buffer = new char[DEFAULT_BUFFER_SIZE];
      long count = 0;
      int n;
      while (-1 != (n = input.read(buffer))) {
        output.write(buffer, 0, n);
        count += n;
      }
      return count;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  static String toString(final Object o) {
    return toString(o, "");
  }

  static String toString(final Object o, final String def) {
    return o == null ? def : o instanceof InputStream ? toString((InputStream) o)
        : o instanceof Reader ? toString((Reader) o)
            : o instanceof Object[] ? Strings.join(", ", (Object[]) o)
                : o instanceof Collection ? Strings.join(", ", (Collection<?>) o) : o.toString();
  }

  static boolean notEmpty(final Object o) {
    return toString(o).trim().length() != 0;
  }
}