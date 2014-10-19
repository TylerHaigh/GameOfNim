Game Of Nim
===========

# Abstract #

NIM (The Suubtraction Game) is a game played by two players. There are a number of sticks on a table and players take turns in removing sticks. The player to remove the last stick wins.

This aplication aims to implement the algorithms required to successfully play the Game of NIM.

This application was developed as an assignment for COMP2230 - Introduction to Algorithmics at the University of Newcastle, 2014.

Developed By:

* Tyler Haigh - C3182929
* Simon Hartcher - C3185790

# To Run #

To run this application:

* Compile the application using " *javac assign.java* "
* Run the application using " *java assign* "

# Input #

Users will be presented with a series of prompts throughout the application. User input should reflect the prompts given:

* Integer numbers when selecting menu options and the number of sticks to remove
* Y (yes) or N (no) for Yes/No prompts

It has been assumed that users will not enter data that will create errors upon reading it (e.g. entering a string when they should enter a number)

# Output #

Basic output is printed to the command line, or Terminal, window. To redirect output to a file: add " *> file.txt* " after the Run command as specified in **To Run** section. To view the output live when redirecting to a file, we recommend:

* Downloading and using [Baretail.exe](https://www.baremetalsoft.com/baretail/ "Baretail Homepage") for Windows
* Adding " *tail* " to the start of the Run command as specified in **To Run**

# Developers Notes #

This application makes use of a number of algorithms:

* Construct NIM Matrix
* Construct NIM Graph
* Label NIM Graph
* Topological Sort (Recursive)