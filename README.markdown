# Overview

**Android SISC Scheme Eval** is an experimental Android App that uses
The SISC Scheme Java interpreter to evaluate scheme code. The app is
mainly a proof of concept.  This repository contains
the Eclipse project and app source code to facilitate experimentation.  The sources
include:

* A patched version of [SISC Scheme](http://www.sisc-scheme.org) -
  release 1.17.0 alpha. The version in this repository does not contain the dynamic class generation
  library - which won't work on Android's dex bytecode.  It also includes a
  small patch to get the s2j module (scheme to java) implementation to work.
* A minimal version of the thankfully GPL-licensed openjdk's java beans source.
  SISC depends on it, but Android provides very limited support for java beans.
  The version in this repository is nearly completely decoupled from
  the `java.awt.*` classes. All classes are namespaced under `neomantic.*`.

# How to Use

The current version of this application does not support running `*.scm` files
that are external to the application (say, stored on the sdcard).  Instead, the
scheme source code must be compiled into the application itself.  By default, they must
be placed in the `assets/scm/` directory.  The application will automatically
load them at startup.

To use this source, this line must be edited in the app's `EvalActivity` class.
    Value v = interpreter.eval("(length)");

The results will be displayed, without fanfare, in a rudimentary android `TextWidget`
area.  An example is in the repository `assets/scm/java-test.scm`.  This code creates
an `org.json.JSONArray object`, and `interpreter.eval("(length)")` queries its length.

# Limitations

Currently, the app will perform all the tasks that I describe in
[this tutorial](http://www.neomantic.com/tutorials/sisc-scheme-on-androids-dalvik-vm).
Most importantly, it can instantiate and manipulate Java objects 'live'.

However, as the tutorial mentions, it fails to perform simple recursions.

# Future

This project will mainly be used a testing ground.  I may add, for instance, the ability
to eval scripts from the sdcard, from, say, list of items.  However, it will not be
involved into a full REPL.  I will create a new project for that.

# License
My source is released under the GNU General Public License Version 2 (GPL). The full text
of this license can be found in a file called COPYING released with the source code. The java
bean source code is GPL license by Sun, and hence so are my patches to it.  The SISC
source code is dual license Mozilla/GPL, so my changes to that code fall under those.
