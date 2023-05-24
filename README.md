ProLeap ANTLR4-based analyzer & transformer for Visual Basic 6.0
================================================================

This is an analyzer & transformer for Visual Basic 6.0 based on [ProLeap Visual Basic 6.0 parser](https://github.com/uwol/proleap-vb6-parser). The parser generates an **Abstract Syntax Tree** (AST) and **Abstract Semantic Graph** (ASG) for Visual Basic 6.0 code, which then is processed by this analyzer & transformer.

💫 **Star** if you like our work.

[![License: AGPL v3](https://img.shields.io/badge/License-AGPL%20v3-blue.svg)](https://www.gnu.org/licenses/agpl-3.0)
[![ProLeap on Twitter](https://img.shields.io/twitter/follow/proleap_io.svg?style=social&label=Follow)](https://twitter.com/proleap_io)


Support
-------

This program is free software under AGPL v3 license and in this form comes without support.


Where to look next
------------------

- [ANTLR4 COBOL grammar](https://github.com/uwol/proleap-vb6-parser/tree/master/src/main/antlr4/io/proleap/vb6/VisualBasic6.g4)
- [Analysis unit tests](proleap-vb6-analysis/src/test/java/io/proleap/vb6/analysis)
- [Transform unit tests](proleap-vb6-transform/src/test/java/io/proleap/vb6/transform)


Build dependency proleap-vb6-parser
-----------------------------------

* Clone repository `https://github.com/uwol/proleap-vb6-parser` locally.
* As described in the README.md of that repository, run:

```
$ mvn clean install
```

* Now your local `~/.m2/repository` should contain dependency `io/github/uwol/proleap-vb6-parser`.
* In [Eclipse](https://eclipse.org) import the directory `proleap-vb6-parser` as a an `existing Maven project`.


Build Process
-------------

The build process is based on Maven (version 3 or higher). Building requires a JDK 17.

* Clone or download the repository.
* In [Eclipse](https://eclipse.org) import the directory as a an `existing Maven project`. *Important*: Open Eclipse > Preferences > Maven > Annotation Processing and select "Automatically configure JDT APT", so that dependency injection framework [Micronaut](https://micronaut.io/) automatically processes all `@Inject` annotations.
* To build, run:

```
$ mvn clean package
```

* The test suite executes tests against Visual Basic 6.0 test code.
* You should see output like this:

```
[INFO] Scanning for projects...
...
[INFO] ------------------------------------------------------------------------
[INFO] Reactor Summary for proleap-vb6 1.0.0:
[INFO] 
[INFO] proleap-vb6 ........................................ SUCCESS [  0.108 s]
[INFO] proleap-vb6-analysis ............................... SUCCESS [  3.270 s]
[INFO] proleap-vb6-transform .............................. SUCCESS [  4.496 s]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

* To install the JAR in your local Maven repository:

```
$ mvn clean install
```

* To only run the tests:

```
$ mvn clean test
```
