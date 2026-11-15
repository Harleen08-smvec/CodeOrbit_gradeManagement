# 🎓 Student Grade Management System

A robust, object-oriented console application written in **Java** to store, track, calculate, and report student grades and academic performance metrics.

---

## 📋 Table of Contents

- [Project Overview](#-project-overview)
- [Key Features](#-key-features)
- [Technical Architecture](#-technical-architecture)
- [Directory Structure](#-directory-structure)
- [System Requirements](#-system-requirements)
- [How to Run the Application](#-how-to-run-the-application)
  - [Option 1: Windows One-Click (Batch File)](#option-1-windows-one-click-batch-file)
  - [Option 2: PowerShell](#option-2-powershell)
  - [Option 3: Standard Terminal (Cross-Platform)](#option-3-standard-terminal-cross-platform)
  - [Option 4: Running in IDEs](#option-4-running-in-ides)
- [Menu Options Guide](#-menu-options-guide)
- [Grading Criteria & Academic Scale](#-grading-criteria--academic-scale)
- [Sample Execution & Formatted Output](#-sample-execution--formatted-output)
- [Data Structures: Arrays vs. ArrayList](#-data-structures-arrays-vs-arraylist)
- [Input Validation & Error Prevention](#-input-validation--error-prevention)

---

## 📌 Project Overview

The **Student Grade Management System** is designed to satisfy core computer science curriculum requirements for data structures, object-oriented programming (OOP), and algorithmic calculations:

1. **Student Record Management**: Store and maintain student names, unique IDs, and marks.
2. **Statistical Computations**: Real-time calculation of class **average**, **highest**, and **lowest** marks.
3. **Data Storage Mechanisms**: Implements dynamic storage using `ArrayList<Student>` while providing conversions to primitive `double[]` and object `Student[]` arrays.
4. **Executive Reporting**: Generates a clean, formatted ASCII performance report featuring student rankings, pass/fail percentages, and a visual grade distribution chart.

---

## ✨ Key Features

- **Dynamic Data Storage**: Uses `ArrayList<Student>` for dynamic insertions and removals without fixed-size limitations.
- **Array Conversion**: Extracts marks into native `double[]` arrays to demonstrate array traversal and processing.
- **Real-Time Statistical Analytics**:
  - Computes class mean/average score.
  - Identifies top scorers and lowest performers (including tied scores).
  - Calculates passing and failing percentages.
- **Comprehensive Grade Distribution**: Visual text-based histogram/bar graph categorized by grade bands (`A+`, `A`, `B`, `C`, `D`, `F`).
- **Full CRUD Capabilities**:
  - Add individual students.
  - Batch add multiple students in a single flow.
  - Search by Name (substring matching, case-insensitive) or Student ID.
  - Update student marks with automatic grade recalculation.
  - Remove/Delete student records.
- **Multi-Criteria Sorting**:
  - Rank Order (Highest Marks to Lowest).
  - Ascending Marks (Lowest to Highest).
  - Alphabetical Order by Name (A–Z).
  - Numerical Order by Student ID.
- **Crash-Proof Input Validation**: Validates user inputs against non-numeric entries, out-of-bounds mark ranges (0.0 to 100.0), and empty names.
- **Preloaded Sample Data**: Includes built-in demonstration records for instant testing.

---

## 🏗️ Technical Architecture

The project follows a clean separation of concerns using Object-Oriented Programming (OOP):

```
       +-----------------------+
       |         Main          |  <-- Entry Point
       +-----------------------+
                   |
                   v
       +-----------------------+
       |  StudentGradeSystem   |  <-- Console UI / Menu Loop / Input Validation
       +-----------------------+
                   |
                   v
       +-----------------------+
       |     GradeManager      |  <-- Business Logic / ArrayList / Array Stats
       +-----------------------+
                   |
                   v
       +-----------------------+
       |        Student        |  <-- Data Model (ID, Name, Marks, Grade)
       +-----------------------+
```

---

## 📂 Directory Structure

| File | Description |
| :--- | :--- |
| [`Student.java`](file:///c:/Users/dell/OneDrive/Desktop/CodeOrbit_gradeManagement/Student.java) | Data model representing an individual student (ID, Name, Marks, Grade, Status, Remark). |
| [`GradeManager.java`](file:///c:/Users/dell/OneDrive/Desktop/CodeOrbit_gradeManagement/GradeManager.java) | Business logic layer containing the `ArrayList<Student>`, array conversions, searches, and statistical methods. |
| [`StudentGradeSystem.java`](file:///c:/Users/dell/OneDrive/Desktop/CodeOrbit_gradeManagement/StudentGradeSystem.java) | Console user interface, interactive menu loop, formatting, and validation logic. |
| [`Main.java`](file:///c:/Users/dell/OneDrive/Desktop/CodeOrbit_gradeManagement/Main.java) | Standard application entry point. |
| [`run.bat`](file:///c:/Users/dell/OneDrive/Desktop/CodeOrbit_gradeManagement/run.bat) | Windows batch script to compile and run with one double-click. |
| [`run.ps1`](file:///c:/Users/dell/OneDrive/Desktop/CodeOrbit_gradeManagement/run.ps1) | PowerShell execution script. |
| [`README.md`](file:///c:/Users/dell/OneDrive/Desktop/CodeOrbit_gradeManagement/README.md) | Comprehensive project documentation. |

---

## 💻 System Requirements

- **Java Development Kit (JDK)**: Version 8 or higher (tested on OpenJDK / Oracle JDK 23).
- **Operating System**: Windows, macOS, or Linux.
- **Terminal**: Any standard console (PowerShell, Command Prompt, Bash, Zsh, or IDE embedded terminal).

---

## 🚀 How to Run the Application

### Option 1: Windows One-Click (Batch File)
Double-click the [`run.bat`](file:///c:/Users/dell/OneDrive/Desktop/CodeOrbit_gradeManagement/run.bat) file in File Explorer, or open Command Prompt in the project folder and type:
```cmd
run.bat
```

### Option 2: PowerShell
Open PowerShell in the project directory and execute:
```powershell
.\run.ps1
```

### Option 3: Standard Terminal (Cross-Platform)
Works on Windows, macOS, and Linux:

```bash
# Step 1: Compile all Java source files
javac Student.java GradeManager.java StudentGradeSystem.java Main.java

# Step 2: Execute the application
java Main
```

### Option 4: Running in IDEs
- **VS Code**: Open the folder in VS Code, install the "Extension Pack for Java", open `Main.java`, and click **Run**.
- **IntelliJ IDEA**: Open the directory as a project, let it detect Java sources, right-click `Main.java`, and select **Run 'Main.main()'**.
- **Eclipse**: Import as an Existing Java Project, right-click `Main.java` -> **Run As** -> **Java Application**.

---

## 📖 Menu Options Guide

When launched, the program displays an interactive menu:

```text
================================================================
               STUDENT GRADE MANAGEMENT SYSTEM                  
================================================================
 Current Students Enrolled: 8
----------------------------------------------------------------
  [1] Add New Student
  [2] Add Multiple Students (Batch Input)
  [3] View All Students (Table View)
  [4] View Performance & Summary Report (Average, High, Low)
  [5] Search Student (by ID or Name)
  [6] Update Student Marks
  [7] Delete Student Record
  [8] Sort Student List
  [9] Data Options (Load Sample / Reset)
  [0] Exit Application
================================================================
```

| Option | Action | Details |
| :---: | :--- | :--- |
| **`1`** | **Add New Student** | Prompts for student name and marks (0.0 to 100.0). Automatically assigns an incrementing unique ID. |
| **`2`** | **Batch Add Students** | Specify a count and enter multiple student records continuously. |
| **`3`** | **View All Students** | Displays a formatted ASCII table containing ID, Name, Marks, Grade, Status (PASS/FAIL), and Remark. |
| **`4`** | **Summary Report** | Generates the executive report: class average, highest score, lowest score, top/lowest students, pass/fail counts, and grade histogram. |
| **`5`** | **Search Student** | Search either by unique Student ID or by Name (supports case-insensitive substring search). |
| **`6`** | **Update Student Marks** | Look up a student by ID and assign new marks. Automatically recalculates letter grade and pass/fail status. |
| **`7`** | **Delete Student Record** | Permanently removes a student record after a confirmation prompt (`y/n`). |
| **`8`** | **Sort Student List** | Sort by Marks (Descending), Marks (Ascending), Alphabetical Name (A–Z), or Student ID. |
| **`9`** | **Data Management** | Reload default sample records or clear all records for a fresh start. |
| **`0`** | **Exit** | Terminates the application gracefully. |

---

## 🎓 Grading Criteria & Academic Scale

The system evaluates student performance using the following standard institutional grading scale:

| Marks Range (%) | Letter Grade | Academic Status | Qualitative Remark |
| :---: | :---: | :---: | :---: |
| **90.0 – 100.0** | **A+** | PASS | Outstanding |
| **80.0 – 89.9** | **A** | PASS | Excellent |
| **70.0 – 79.9** | **B** | PASS | Very Good |
| **60.0 – 69.9** | **C** | PASS | Good |
| **50.0 – 59.9** | **D** | PASS | Satisfactory |
| **0.0 – 49.9** | **F** | FAIL | Needs Improvement |

*Passing threshold is set at $\ge 50.0$ marks.*

---

## 📊 Sample Execution & Formatted Output

### 1. Formatted Student Roster (Option 3)
```text
+------+----------------------+-------+-------+--------+--------------------+
| ID   | Name                 | Marks | Grade | Status | Remark             |
+------+----------------------+-------+-------+--------+--------------------+
| 101  | Emma Watson          | 94.50 | A+    | PASS   | Outstanding        |
| 102  | Liam Smith           | 82.00 | A     | PASS   | Excellent          |
| 103  | Sophia Johnson       | 76.50 | B     | PASS   | Very Good          |
| 104  | Noah Brown           | 58.00 | D     | PASS   | Satisfactory       |
| 105  | Olivia Davis         | 91.00 | A+    | PASS   | Outstanding        |
| 106  | Ethan Miller         | 44.00 | F     | FAIL   | Needs Improvement  |
| 107  | Ava Wilson           | 88.50 | A     | PASS   | Excellent          |
| 108  | Lucas Garcia         | 67.00 | C     | PASS   | Good               |
+------+----------------------+-------+-------+--------+--------------------+
 Total records displayed: 8
```

### 2. Executive Performance & Summary Report (Option 4)
```text
+==============================================================+
|                   EXECUTIVE SUMMARY REPORT                   |
+==============================================================+
|  Total Students Registered : 8                               |
|  Class Average Marks       : 75.19                           |
|  Highest Mark Achieved     : 94.50                           |
|  Lowest Mark Achieved      : 44.00                           |
+--------------------------------------------------------------+
|  Students Passed (>=50%)   : 7   ( 87.5%)                    |
|  Students Failed (<50%)    : 1   ( 12.5%)                    |
+==============================================================+

[*] TOP PERFORMER(S) [Highest Mark: 94.50]:
    - [ID: 101] Emma Watson          :  94.50 marks (Grade: A+)

[!] LOWEST PERFORMER(S) [Lowest Mark: 44.00]:
    - [ID: 106] Ethan Miller         :  44.00 marks (Grade: F)

[#] GRADE DISTRIBUTION BREAKDOWN:
+-------+------------+-------+---------------------------------+
| Grade | Range      | Count | Visual Distribution             |
+-------+------------+-------+---------------------------------+
|  A+   | 90 - 100   | 2     | ######                          |
|  A    | 80 - 89.9  | 2     | ######                          |
|  B    | 70 - 79.9  | 1     | ###                             |
|  C    | 60 - 69.9  | 1     | ###                             |
|  D    | 50 - 59.9  | 1     | ###                             |
|  F    | Below 50   | 1     | ###                             |
+-------+------------+-------+---------------------------------+

[INFO] Array Storage Check: 8 marks stored in native double[] array (First: 94.5, Last: 67.0)
```

---

## 🧩 Data Structures: Arrays vs. ArrayList

To fulfill academic project criteria, this project demonstrates proficiency with both **`ArrayList`** and **Native Arrays**:

| Feature | `ArrayList<Student>` | Native Array (`double[]` / `Student[]`) |
| :--- | :--- | :--- |
| **Resizability** | Dynamic (grows and shrinks as students are added/removed). | Fixed size determined at creation. |
| **Primary Role** | Primary data repository in `GradeManager`. | Statistical data extraction (`getMarksAsArray()`). |
| **Operations** | `add()`, `removeIf()`, `sort()`, dynamic iteration. | Indexed traversal, memory efficiency. |
| **Demonstration Method** | `studentList.add(student)` | `studentList.toArray(new Student[0])` |

---

## 🛡️ Input Validation & Error Prevention

The console interface incorporates defensive programming patterns to ensure the application never crashes from unexpected user input:

- **Numeric Choice Validation (`readIntChoice`)**: Prevents `InputMismatchException` when letters or symbols are entered into numeric menus.
- **Mark Range Constraints (`readMarks`)**: Ensures student marks fall strictly between `0.0` and `100.0`. Re-prompts the user if an invalid mark or non-numeric value is provided.
- **Non-Empty String Validation (`readNonEmptyString`)**: Blocks empty or whitespace-only names from being registered.
- **Empty State Safeguards**: Menu operations like summary reporting, search, sorting, and deletion verify that the student repository is not empty before executing.

---

## 📄 License & Attribution

Developed for educational demonstration, grading management, and academic assignments. Feel free to customize and extend!
