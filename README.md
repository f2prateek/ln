Ln [![Build Status](https://travis-ci.org/f2prateek/ln.png)](https://travis-ci.org/f2prateek/ln)
==============

Android applications typically use the built-in android.util.Log facility to log information to the android console.
Ln is more natural log for Android. Forked from [RoboGuice](1).
It allows you to use a single API to control the log level, disable all logging (e.g. for release builds) and log to multiple endpoints.
I was tired of copying these classes into my apps, so now it's a library.

Usage
--------------

 1. Call `Ln#set` with your instance of `LnInterface`, such as `EmptyLn` (used by default) for release builds or `DebugLn` for debug builds.
 2. Call `Ln`'s static methods everywhere throughout your app.
 3. Call `Ln#setLogginLevel` to change the logLevel at different points in your app.

Check out the sample app in `ln-sample/` to see it in action.

Examples
--------------

```java
Ln.v("hello there"); // prints "hello there"
Ln.d("%s %s", "hello", "there"); // prints "hello there" with varargs
Ln.e(exception, "Error during some operation"); // Throwables go at the FRONT!
Ln.w(exception, "Error during %s operation", "some other"); // Throwables and varargs
```

Extending
-------------

To log to a different endpoint you have one of these options, simply implement `LnInterface`. If you don't want to implement all the methods, extend `EmptyLn` and override the ones you need.

To log to multiple endpoints (say analytics and crash server) :
 1. Initialize an instance of `LnFacade`. This class is facade, and delegates all calls to list of `LnInterface`'s that it maintains.
 2. Use `Ln#addLoggingEndpoint` to add your instances of `LnInterface`.
 3. Call `Ln#set` with your instance of `LnFacade`

Download
--------

Download [the latest JAR][1] or grab via Maven:

```xml
<dependency>
  <groupId>com.f2prateek.ln</groupId>
  <artifactId>ln</artifactId>
  <version>(insert latest version)</version>
</dependency>
```
or Gradle:
```groovy
compile 'com.f2prateek.ln:ln:(insert latest version)'
```


License
-------

    The MIT License (MIT)

    Copyright (c) 2013 Prateek Srivastava
    Copyright (c) 2010 Xtreme Labs and Pivotal Labs

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.


 [1]: http://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=com.f2prateek.ln&a=ln&v=LATEST
 [2]: https://code.google.com/p/roboguice/wiki/Logging