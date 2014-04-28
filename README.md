MathQuiz
========

![Alt text](/img/main-window.png?raw=true "Main window")

What is this About 
==================

A math quiz java application written using Eclipse it generally uses basic MVC architecture
and coded as an exercise for that pattern. It has the following features:

![Alt text](/img/settings.png?raw=true "Settings window")

 -can generate custom operand range, aside from basic 1-10,11-100 ranges
 -user defined number of items, and ability to set how much farther the wrong answer from the correct one.
 -can save settings to a file
 -uses the miglayout library for simple and more readable GUI programming in java

Requirements 
------------

 - Java Development Kit  
 - miglayout-swing.jar from MigLayout (www.miglayout.com/) 
   it assumes this library is located at <project>/lib/
 - hamcrest-core-1.3.jar and junit-4.11.jar of JUnit 4 suite for testing     


How to Compile
--------------

Simply import the project into Eclipse then include the library Junit, and MigLayout in the buildpath 
then you can test and run it.

How to Run 
----------

![Alt text](/img/quiz-ongoing.png?raw=true "During quiz")

assuming you have installed the latest jvm, just run the MathQuiz.jar java executable



Other Issues
------------

 - Havent fix the changing size of the Questions Border, maybe you can help me with it?
 - Havent tried compiling this manually, without the aid of an IDE like Eclipse
 - Havent created tests for other classes, tests covers the Settings part only.



copyleft 2014 0x726364.
