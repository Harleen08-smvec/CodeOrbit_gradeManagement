@echo off
title Student Grade Management System
echo ===================================================
echo     Compiling Student Grade Management System...
echo ===================================================
javac Student.java GradeManager.java StudentGradeSystem.java Main.java
if %errorlevel% neq 0 (
    echo [ERROR] Compilation failed! Check Java JDK installation.
    pause
    exit /b %errorlevel%
)
echo [SUCCESS] Compilation successful!
echo Starting application...
echo ===================================================
java Main
pause
