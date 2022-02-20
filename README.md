# Alex Comeau's JavaFX editor
## Table of contents
   * [Requirements](#requirements)
   * [Running](#running)
   * [Building](#building)
   * [Used Libraries/Attributions](#used-libraries)
     * [RichTextFX](#richtextfx)
     * [OpenJFX](#openjfx)
   * [Linked examples of project requirements](#examples-of-project-requirements)
       * Inheritance
       * Interfaces
       * Collections
       * recursion
---
Requirements
====
[Apache Maven](https://maven.apache.org/download.cgi) >= 3.8.4 \
[Java JDK Version](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) >= 17*

\* you may be able to get away with a lesser java version if you edit the pom file, but this is not recommended

Running
=====
use command `mvn javafx:run` in the project root to run

Building
===
Steps: 
1. use the command `mvn clean install` to build into a jar file. 
2. The final jar file will be between 10 and 15 megabytes and in the target folder

Alternative steps:
1. use the command `mvn javafx:run` to run the file without building a jar file

Used Libraries
===

RichTextFX 
----------
[RichTextFX](https://github.com/FXMisc/RichTextFX) is a library which focusing on adding better support to to text areas in javafx, such as character highlighting 
<br>[LICENSE](https://choosealicense.com/licenses/bsd-2-clause/)

OpenJFX
----
[OpenJFX](https://github.com/openjdk/jfx) is the open source version of javafx, maintained by Red Hat, and available through maven
<br>[LICENSE](https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html)

Examples of Project Requirements
========