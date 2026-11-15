Write-Host "===================================================" -ForegroundColor Cyan
Write-Host "     Compiling Student Grade Management System...  " -ForegroundColor Cyan
Write-Host "===================================================" -ForegroundColor Cyan

& javac Student.java GradeManager.java StudentGradeSystem.java Main.java
if ($LASTEXITCODE -eq 0) {
    Write-Host "[SUCCESS] Compilation successful! Launching..." -ForegroundColor Green
    & java Main
} else {
    Write-Host "[ERROR] Compilation failed. Ensure JDK is installed." -ForegroundColor Red
}
