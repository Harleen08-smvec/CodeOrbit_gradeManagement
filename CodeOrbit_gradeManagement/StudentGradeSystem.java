import java.util.List;
import java.util.Scanner;

/**
 * StudentGradeSystem.java
 * Main console application providing an interactive menu-driven interface
 * for managing student grades and viewing statistical summary reports.
 */
public class StudentGradeSystem {
    private static final Scanner scanner = new Scanner(System.in);
    private static final GradeManager manager = new GradeManager();

    public static void main(String[] args) {
        // Pre-load demo records so the user immediately has data to inspect
        manager.loadSampleData();

        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readIntChoice("Enter your choice (0-9): ", 0, 9);
            System.out.println();

            switch (choice) {
                case 1:
                    handleAddStudent();
                    break;
                case 2:
                    handleAddMultipleStudents();
                    break;
                case 3:
                    handleDisplayAllStudents();
                    break;
                case 4:
                    handleDisplaySummaryReport();
                    break;
                case 5:
                    handleSearchStudent();
                    break;
                case 6:
                    handleUpdateMarks();
                    break;
                case 7:
                    handleDeleteStudent();
                    break;
                case 8:
                    handleSortStudents();
                    break;
                case 9:
                    handleManageData();
                    break;
                case 0:
                    running = false;
                    printExitMessage();
                    break;
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }

            if (running) {
                pausePrompt();
            }
        }
    }

    private static void printMainMenu() {
        System.out.println();
        System.out.println("================================================================");
        System.out.println("               STUDENT GRADE MANAGEMENT SYSTEM                  ");
        System.out.println("================================================================");
        System.out.printf(" Current Students Enrolled: %d%n", manager.getStudentCount());
        System.out.println("----------------------------------------------------------------");
        System.out.println("  [1] Add New Student");
        System.out.println("  [2] Add Multiple Students (Batch Input)");
        System.out.println("  [3] View All Students (Table View)");
        System.out.println("  [4] View Performance & Summary Report (Average, High, Low)");
        System.out.println("  [5] Search Student (by ID or Name)");
        System.out.println("  [6] Update Student Marks");
        System.out.println("  [7] Delete Student Record");
        System.out.println("  [8] Sort Student List");
        System.out.println("  [9] Data Options (Load Sample / Reset)");
        System.out.println("  [0] Exit Application");
        System.out.println("================================================================");
    }

    // =========================================================================
    // FEATURE HANDLERS
    // =========================================================================

    private static void handleAddStudent() {
        System.out.println(">>> [+] Add New Student");
        System.out.println("----------------------------------------------------------------");
        String name = readNonEmptyString("Enter Student Name: ");
        double marks = readMarks("Enter Marks (0.0 to 100.0): ");

        Student student = manager.addStudent(name, marks);
        System.out.println();
        System.out.printf("[SUCCESS] Student added! Assigned ID: %d | Grade: %s | Status: %s%n",
                student.getId(), student.getGrade(), student.getStatus());
    }

    private static void handleAddMultipleStudents() {
        System.out.println(">>> [++] Batch Add Students");
        System.out.println("----------------------------------------------------------------");
        int count = readIntChoice("How many students would you like to enter? (1-50): ", 1, 50);

        for (int i = 1; i <= count; i++) {
            System.out.printf("%n-- Entering Student %d of %d --%n", i, count);
            String name = readNonEmptyString("Name: ");
            double marks = readMarks("Marks (0.0 - 100.0): ");
            Student s = manager.addStudent(name, marks);
            System.out.printf("Added '%s' with ID %d (Grade: %s)%n", s.getName(), s.getId(), s.getGrade());
        }
        System.out.printf("%n[SUCCESS] All %d students added successfully!%n", count);
    }

    private static void handleDisplayAllStudents() {
        System.out.println(">>> [ROSTER] Registered Students List");
        if (manager.isEmpty()) {
            System.out.println("No student records found. Add students first or load sample data.");
            return;
        }

        renderStudentTable(manager.getAllStudents());
    }

    /**
     * Requirement: Calculate the average, highest, and lowest marks.
     * Display a clear, formatted summary report.
     */
    private static void handleDisplaySummaryReport() {
        System.out.println(">>> [REPORT] ACADEMIC PERFORMANCE & GRADE SUMMARY REPORT");
        if (manager.isEmpty()) {
            System.out.println("No student data available to generate a summary report.");
            return;
        }

        int total = manager.getStudentCount();
        double average = manager.calculateAverage();
        double highest = manager.getHighestMark();
        double lowest = manager.getLowestMark();
        List<Student> topStudents = manager.getHighestScorers();
        List<Student> lowStudents = manager.getLowestScorers();
        int passed = manager.getPassedCount();
        int failed = manager.getFailedCount();
        double passRate = (double) passed / total * 100.0;

        System.out.println("+==============================================================+");
        System.out.println("|                   EXECUTIVE SUMMARY REPORT                   |");
        System.out.println("+==============================================================+");
        System.out.printf("|  Total Students Registered : %-31d |%n", total);
        System.out.printf("|  Class Average Marks       : %-31.2f |%n", average);
        System.out.printf("|  Highest Mark Achieved     : %-31.2f |%n", highest);
        System.out.printf("|  Lowest Mark Achieved      : %-31.2f |%n", lowest);
        System.out.println("+--------------------------------------------------------------+");
        System.out.printf("|  Students Passed (>=50%%)   : %-3d (%5.1f%%)                    |%n", passed, passRate);
        System.out.printf("|  Students Failed (<50%%)    : %-3d (%5.1f%%)                    |%n", failed, (100.0 - passRate));
        System.out.println("+==============================================================+");

        // Top Performers
        System.out.println();
        System.out.println("[*] TOP PERFORMER(S) [Highest Mark: " + String.format("%.2f", highest) + "]:");
        for (Student s : topStudents) {
            System.out.printf("    - [ID: %d] %-20s : %6.2f marks (Grade: %s)%n",
                    s.getId(), s.getName(), s.getMarks(), s.getGrade());
        }

        // Lowest Performers
        System.out.println();
        System.out.println("[!] LOWEST PERFORMER(S) [Lowest Mark: " + String.format("%.2f", lowest) + "]:");
        for (Student s : lowStudents) {
            System.out.printf("    - [ID: %d] %-20s : %6.2f marks (Grade: %s)%n",
                    s.getId(), s.getName(), s.getMarks(), s.getGrade());
        }

        // Grade Distribution Breakdown
        System.out.println();
        System.out.println("[#] GRADE DISTRIBUTION BREAKDOWN:");
        System.out.println("+-------+------------+-------+---------------------------------+");
        System.out.println("| Grade | Range      | Count | Visual Distribution             |");
        System.out.println("+-------+------------+-------+---------------------------------+");
        int[] dist = manager.getGradeDistribution();
        String[] grades = {"A+", "A ", "B ", "C ", "D ", "F "};
        String[] ranges = {"90 - 100", "80 - 89.9", "70 - 79.9", "60 - 69.9", "50 - 59.9", "Below 50"};

        for (int i = 0; i < 6; i++) {
            int count = dist[i];
            String bar = "#".repeat(Math.max(0, count * 3));
            System.out.printf("|  %-4s | %-10s | %-5d | %-31s |%n", grades[i], ranges[i], count, bar);
        }
        System.out.println("+-------+------------+-------+---------------------------------+");

        // Array Demonstration
        double[] marksArray = manager.getMarksAsArray();
        System.out.println();
        System.out.printf("[INFO] Array Storage Check: %d marks stored in native double[] array (First: %.1f, Last: %.1f)%n",
                marksArray.length, marksArray[0], marksArray[marksArray.length - 1]);
    }

    private static void handleSearchStudent() {
        System.out.println(">>> [SEARCH] Search Student Records");
        if (manager.isEmpty()) {
            System.out.println("No records available to search.");
            return;
        }

        System.out.println("Search By: [1] Student ID   [2] Student Name");
        int subChoice = readIntChoice("Select option (1-2): ", 1, 2);

        if (subChoice == 1) {
            System.out.print("Enter Student ID to find: ");
            int id = readInt();
            Student s = manager.findById(id);
            if (s != null) {
                System.out.println("\n[MATCH FOUND] Student Record:");
                renderStudentTable(List.of(s));
            } else {
                System.out.printf("[NOT FOUND] No student found with ID %d.%n", id);
            }
        } else {
            String nameQuery = readNonEmptyString("Enter name to search (or partial name): ");
            List<Student> results = manager.searchByName(nameQuery);
            if (!results.isEmpty()) {
                System.out.printf("\n[MATCH FOUND] %d matching student(s):%n", results.size());
                renderStudentTable(results);
            } else {
                System.out.printf("[NOT FOUND] No student found matching \"%s\".%n", nameQuery);
            }
        }
    }

    private static void handleUpdateMarks() {
        System.out.println(">>> [UPDATE] Update Student Marks");
        if (manager.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        System.out.print("Enter ID of student to update: ");
        int id = readInt();
        Student s = manager.findById(id);

        if (s == null) {
            System.out.printf("[ERROR] Student with ID %d not found.%n", id);
            return;
        }

        System.out.printf("Current Record: %s (Current Marks: %.2f, Grade: %s)%n",
                s.getName(), s.getMarks(), s.getGrade());
        double newMarks = readMarks("Enter New Marks (0.0 to 100.0): ");

        manager.updateMarks(id, newMarks);
        System.out.println();
        System.out.printf("[SUCCESS] Marks updated! New Marks: %.2f | New Grade: %s | Status: %s%n",
                s.getMarks(), s.getGrade(), s.getStatus());
    }

    private static void handleDeleteStudent() {
        System.out.println(">>> [DELETE] Remove Student Record");
        if (manager.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }

        System.out.print("Enter ID of student to remove: ");
        int id = readInt();
        Student s = manager.findById(id);

        if (s == null) {
            System.out.printf("[ERROR] Student with ID %d not found.%n", id);
            return;
        }

        System.out.printf("Are you sure you want to delete '%s' (ID: %d)? (y/n): ", s.getName(), s.getId());
        String confirm = scanner.nextLine().trim().toLowerCase();
        if (confirm.startsWith("y")) {
            manager.deleteStudent(id);
            System.out.printf("[SUCCESS] Student ID %d removed successfully.%n", id);
        } else {
            System.out.println("Deletion canceled.");
        }
    }

    private static void handleSortStudents() {
        System.out.println(">>> [SORT] Sort Students");
        if (manager.isEmpty()) {
            System.out.println("No student records to sort.");
            return;
        }

        System.out.println("Choose Sorting Criteria:");
        System.out.println("  [1] Marks (High to Low - Rank Order)");
        System.out.println("  [2] Marks (Low to High)");
        System.out.println("  [3] Name (Alphabetical A-Z)");
        System.out.println("  [4] Student ID (Ascending)");
        int sortChoice = readIntChoice("Enter sort option (1-4): ", 1, 4);

        switch (sortChoice) {
            case 1:
                manager.sortByMarksDescending();
                System.out.println("[SUCCESS] Sorted by Marks: Highest to Lowest.");
                break;
            case 2:
                manager.sortByMarksAscending();
                System.out.println("[SUCCESS] Sorted by Marks: Lowest to Highest.");
                break;
            case 3:
                manager.sortByName();
                System.out.println("[SUCCESS] Sorted by Name: Alphabetical.");
                break;
            case 4:
                manager.sortById();
                System.out.println("[SUCCESS] Sorted by Student ID.");
                break;
        }

        renderStudentTable(manager.getAllStudents());
    }

    private static void handleManageData() {
        System.out.println(">>> [DATA] Data Management Options");
        System.out.println("  [1] Load Sample Demo Data (appends preset records)");
        System.out.println("  [2] Clear All Records (Fresh Start)");
        int subChoice = readIntChoice("Choose option (1-2): ", 1, 2);

        if (subChoice == 1) {
            manager.loadSampleData();
            System.out.printf("[SUCCESS] Sample student records loaded! Total students now: %d%n", manager.getStudentCount());
        } else {
            System.out.print("[WARNING] Are you sure you want to delete ALL records? (y/n): ");
            String confirm = scanner.nextLine().trim().toLowerCase();
            if (confirm.startsWith("y")) {
                manager.clearAll();
                System.out.println("[SUCCESS] All records have been cleared.");
            } else {
                System.out.println("Operation canceled.");
            }
        }
    }

    // =========================================================================
    // FORMATTED DISPLAY HELPERS
    // =========================================================================

    private static void renderStudentTable(List<Student> list) {
        System.out.println("+------+----------------------+-------+-------+--------+--------------------+");
        System.out.println("| ID   | Name                 | Marks | Grade | Status | Remark             |");
        System.out.println("+------+----------------------+-------+-------+--------+--------------------+");
        for (Student s : list) {
            System.out.printf("| %-4d | %-20s | %5.2f | %-5s | %-6s | %-18s |%n",
                    s.getId(),
                    truncate(s.getName(), 20),
                    s.getMarks(),
                    s.getGrade(),
                    s.getStatus(),
                    truncate(s.getRemark(), 18));
        }
        System.out.println("+------+----------------------+-------+-------+--------+--------------------+");
        System.out.printf(" Total records displayed: %d%n", list.size());
    }

    private static String truncate(String text, int maxLen) {
        if (text == null) return "";
        if (text.length() <= maxLen) return text;
        return text.substring(0, maxLen - 2) + "..";
    }

    private static void printExitMessage() {
        System.out.println("================================================================");
        System.out.println("  Thank you for using the Student Grade Management System!");
        System.out.println("                     Have a great day!                          ");
        System.out.println("================================================================");
    }

    private static void pausePrompt() {
        System.out.println("\nPress [Enter] to return to the main menu...");
        scanner.nextLine();
    }

    // =========================================================================
    // INPUT VALIDATION HELPERS
    // =========================================================================

    private static int readIntChoice(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.printf("Please enter a valid number between %d and %d.%n", min, max);
            } catch (NumberFormatException e) {
                System.out.printf("Invalid input. Please enter a number between %d and %d.%n", min, max);
            }
        }
    }

    private static int readInt() {
        while (true) {
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid integer. Please re-enter: ");
            }
        }
    }

    private static double readMarks(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double marks = Double.parseDouble(input);
                if (marks >= 0.0 && marks <= 100.0) {
                    return marks;
                }
                System.out.println("❌ Marks must be between 0.0 and 100.0. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid decimal/numeric value. Please enter valid marks (e.g., 85.5).");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("❌ Input cannot be empty. Please enter a valid name.");
        }
    }
}
