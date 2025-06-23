## Student Record System - CSV Edition (Java Console App)

A simple Java console-based application that manages student records using `.csv` file storage.

---

## Features

- Add student (only if ID is not already used)
- View all students
- Search, Update, Delete
- Automatically saves to `students.csv` on every change

---

## How to Run

### Prerequisites
- Java 8 or later

### Steps
```bash
javac src/StudentRecordCSVApp.java
java -cp src StudentRecordCSVApp
```

---

## Technologies Used

-Java SE (Standard Edition)
-HashMap for storing data in memory
-BufferedReader / BufferedWriter for CSV file handling
-Java File I/O
-Console-based interaction

