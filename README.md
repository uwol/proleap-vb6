proleap-vb6
===========

Code base for proleap-vb6 that allows you to analyze, execute & transform Visual Basic 6.0 in the cloud.


Eclipse
-------

- Install Eclipse >= 2022‑12
- Open Eclipse > Preferences > Maven > Annotation Processing and select "Automatically configure JDT APT".
- Open "Import Projects" > Maven > Existing Maven Projects
- Browse root directory to repository `proleap-vb6` and click Finish


Dependency proleap-vb6-parser
-----------------------------

* Clone repository `https://github.com/uwol/proleap-vb6-parser` locally.
* As described in the README.md of the repository, run:

```
$ mvn clean install
```

* Now your local `~/.m2/repository` should contain a build of dependency `proleap-vb6-parser`.
* In Eclipse, open "Import Projects" > Maven > Existing Maven Projects
* Browse root directory the repository `proleap-vb6-parser` and click Finish


Build Process
-------------

The build process is based on Maven (version 3 or higher). Building requires a JDK 17.

Install homebrew.

```
$ echo export "JAVA_HOME=\$(/usr/libexec/java_home)" >> ~/.zshrc
$ echo export "eval $(/opt/homebrew/bin/brew shellenv)" >> ~/.zshrc
```

```
$ brew install maven
$ mvn -T 1C clean test
```

```
$ mvn clean package
```
