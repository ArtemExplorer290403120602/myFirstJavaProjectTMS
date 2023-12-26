# Description of the project:

This project describes the banking system of money transfer from one account to another

Previously, this project has all the basic knowledge of oriented programming, the use of classes, loops, regular expressions, and so on

# Project structure:
### 1) There are 3 folders in this project:
>actions:

 his folder describes the actions that our program will perform, such as parsing, file browsing, etc.
>diagram

This folder contains a class diagram of our project , you can see what depends on what
>myFirstJavaProject

There are two main classes in this folder, one of which is __Main__, the class that runs the program and the other class is __Menu__, which shows the menu to the user
### 2) Diagram classes
This project has a class diagram, which is very important for viewing classes, how and what they do and react with other classes, what methods, fields exist there, and so on
### 3) Main fields
There are 3 fields in this project that are important for the program:
```java
public static final String INPUT_DIRECTORY ="G://input";
public static final String ARCHIVE_DIRECTORY = "G://archive";
public static final String REPORT_FILE = "G://report.txt";
```
### 4) Methods:
there are many methods in my project that have their own function:
```java
 protected static void menu(){...}
```
 In class __Menu__ you can immediately understand that this is a method for using the program menu, which describes the actions that the user can do if he enters the program
```java
public static void main(String[] args)
```
In the __Main__ class, this method is created in order to run the program
```java
public static void showReport() throws IOException
```
The provided Java code defines a class named __ViewingTheTotal__, which extends __TransferMoneyToAnAccount__. Within this class, there is a static method named __showReport()__ that throws an IOException.
This line declares a class named ViewingTheTotal that extends (inherits from) another class called TransferMoneyToAnAccount. This implies that ViewingTheTotal inherits the properties and methods of the TransferMoneyToAnAccount class.
This line defines a static method named showReport(). The static keyword means that the method belongs to the class itself rather than an instance of the class. The method has a throws IOException clause, indicating that it can throw an IOException, and any code calling this method needs to handle or declare that exception.
Here, a BufferedReader is created to read from a file. The file to be read is specified by the constant REPORT_FILE. It's assumed that REPORT_FILE is a previously defined constant holding the file path.
Inside a while loop, each line from the file is read using the readLine() method of the BufferedReader. The loop continues until there are no more lines to read (i.e., readLine() returns null). The content of each line is then printed to the console using System.out.println().
Finally, the BufferedReader is closed to release the resources associated with reading from the file.
In summary, the showReport() method reads the contents of a file (presumably a report file) line by line and prints each line to the console. The file path is specified by the REPORT_FILE constant, and the method is declared to potentially throw an IOException.
```java
 private static void processFile(File file) throws IOException
 public static void parseFiles() throws IOException
```
__parseFiles__: This method is responsible for locating and processing text files in a specified input directory. It checks for the existence of the input directory, the presence of text files, and then iterates over each text file, processing it using the processFile method and moving it to an archive.

__processFile__: This method reads each line from a given text file, validates the format of the lines, extracts relevant information (account numbers, amount), performs various checks, and writes the results to a report file. It also moves the processed file to an archive directory.
# Requirements:

```java
public static final String INPUT_DIRECTORY ="G://input";
public static final String ARCHIVE_DIRECTORY = "G://archive";
public static final String REPORT_FILE = "G://report.txt";
```
The most important thing is that you correctly specify the path to your file in __input__, and also that your computer has the path to the __report__ file and it was created
```java
String[] parts = line.split("\\|");
        String fromAccount = parts[0].replaceAll(":", "").trim();
        String toAccount = parts[1].trim();
        String amountStr = parts[2].replaceAll(":", "").trim();
```
The format of the input files should look like __:xxxxx-xxxxx|xxxxx-xxxxx|nnn:__ , that is, in your file that is located in the __input__ folder, it should have this format

This project uses JDK version 21 and IDE version 2023
# Author: _Artem Denisovich Mekhanikov_
## You can contact me:
### telephone: _+375 (29) 689-84-07_
### telegram: _@unstoppable_pancakes_