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
   * [Troubleshooting](#troubleshooting)
---
Requirements
====
[Apache Maven](https://maven.apache.org/download.cgi) >= 3.8.4 \
[Java JDK Version](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html) >= 17*

\* you may be able to get away with a lesser java version if you edit the pom file, but this is not recommended


Running
===
there are multiple ways to run this application. you can either use the provided jar file, [build](#building) it yourself with maven and run as a jar, or you can run it from the command line with maven
**to run with maven:** use the command `mvn javafx:run` in the root of the project to run the file without building a jar file

Building
===
Steps: 
1. use the command `mvn clean install` to build into a jar file. 
2. The final jar file will be between 10 and 15 megabytes and in the target folder named `javafx-editor-1.0.0.jar`
you can then run it with `java -jar javafx-editor-1.0.0.jar`

if any of these steps do not work check [troubleshooting](#troubleshooting)



Used Libraries
===

RichTextFX 
----------
[RichTextFX](https://github.com/FXMisc/RichTextFX) is a library which focusing on adding better support to to text areas in javafx, such as character highlighting \
[LICENSE](https://choosealicense.com/licenses/bsd-2-clause/)

OpenJFX
----
[OpenJFX](https://github.com/openjdk/jfx) is the open source version of javafx, maintained by Red Hat, and available through maven \
[LICENSE](https://www.gnu.org/licenses/old-licenses/gpl-2.0.en.html)

Jackson-Core
----
[jackson-core](https://github.com/FasterXML/jackson-core) is a base library required to map json to object
[LICENSE](https://choosealicense.com/licenses/apache-2.0/)

Jackson-Databind
----
[Jackson-Databind](https://github.com/FasterXML/jackson-databind) is the library which is used to map a json file into an object using an `ObjectMapper`
[LICENSE](https://choosealicense.com/licenses/apache-2.0/)

Examples of Project Requirements
========
WIP

Troubleshooting
===
WIP