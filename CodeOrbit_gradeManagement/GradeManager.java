import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * GradeManager.java
 * Handles storage, updates, queries, and statistical calculations
 * for student grade data using ArrayList and Array operations.
 */
public class GradeManager {
    // Dynamic storage using ArrayList
    private final ArrayList<Student> studentList;
    private int nextId;

    public GradeManager() {
        this.studentList = new ArrayList<>();
        this.nextId = 101; // Starting ID for students
    }

    /**
     * Adds a new student to the system.
     */
    public Student addStudent(String name, double marks) {
        Student student = new Student(nextId++, name.trim(), marks);
        studentList.add(student);
        return student;
    }

    /**
     * Returns all registered students.
     */
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentList);
    }

    /**
     * Demonstrates array usage by converting the ArrayList to a native Student array.
     */
    public Student[] getStudentsAsArray() {
        return studentList.toArray(new Student[0]);
    }

    /**
     * Extracts all marks into a primitive double[] array.
     */
    public double[] getMarksAsArray() {
        double[] marksArray = new double[studentList.size()];
        for (int i = 0; i < studentList.size(); i++) {
            marksArray[i] = studentList.get(i).getMarks();
        }
        return marksArray;
    }

    /**
     * Returns total number of students.
     */
    public int getStudentCount() {
        return studentList.size();
    }

    public boolean isEmpty() {
        return studentList.isEmpty();
    }

    /**
     * Calculates the class average marks.
     */
    public double calculateAverage() {
        if (studentList.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (Student s : studentList) {
            sum += s.getMarks();
        }
        return sum / studentList.size();
    }

    /**
     * Finds the maximum mark in the system.
     */
    public double getHighestMark() {
        if (studentList.isEmpty()) {
            return 0.0;
        }
        double max = studentList.get(0).getMarks();
        for (Student s : studentList) {
            if (s.getMarks() > max) {
                max = s.getMarks();
            }
        }
        return max;
    }

    /**
     * Finds the minimum mark in the system.
     */
    public double getLowestMark() {
        if (studentList.isEmpty()) {
            return 0.0;
        }
        double min = studentList.get(0).getMarks();
        for (Student s : studentList) {
            if (s.getMarks() < min) {
                min = s.getMarks();
            }
        }
        return min;
    }

    /**
     * Returns all students holding the highest mark (handles ties).
     */
    public List<Student> getHighestScorers() {
        List<Student> topScorers = new ArrayList<>();
        if (studentList.isEmpty()) return topScorers;

        double highest = getHighestMark();
        for (Student s : studentList) {
            if (Double.compare(s.getMarks(), highest) == 0) {
                topScorers.add(s);
            }
        }
        return topScorers;
    }

    /**
     * Returns all students holding the lowest mark (handles ties).
     */
    public List<Student> getLowestScorers() {
        List<Student> lowestScorers = new ArrayList<>();
        if (studentList.isEmpty()) return lowestScorers;

        double lowest = getLowestMark();
        for (Student s : studentList) {
            if (Double.compare(s.getMarks(), lowest) == 0) {
                lowestScorers.add(s);
            }
        }
        return lowestScorers;
    }

    /**
     * Counts how many students passed.
     */
    public int getPassedCount() {
        int count = 0;
        for (Student s : studentList) {
            if (s.isPassed()) count++;
        }
        return count;
    }

    /**
     * Counts how many students failed.
     */
    public int getFailedCount() {
        return studentList.size() - getPassedCount();
    }

    /**
     * Finds student by unique ID.
     */
    public Student findById(int id) {
        for (Student s : studentList) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }

    /**
     * Searches students by case-insensitive name substring.
     */
    public List<Student> searchByName(String query) {
        List<Student> results = new ArrayList<>();
        String lowerQuery = query.toLowerCase().trim();
        for (Student s : studentList) {
            if (s.getName().toLowerCase().contains(lowerQuery)) {
                results.add(s);
            }
        }
        return results;
    }

    /**
     * Updates marks for a student by ID.
     */
    public boolean updateMarks(int id, double newMarks) {
        Student s = findById(id);
        if (s != null) {
            s.setMarks(newMarks);
            return true;
        }
        return false;
    }

    /**
     * Deletes a student by ID.
     */
    public boolean deleteStudent(int id) {
        return studentList.removeIf(s -> s.getId() == id);
    }

    /**
     * Sorts list by marks (descending: highest to lowest).
     */
    public void sortByMarksDescending() {
        studentList.sort(Comparator.comparingDouble(Student::getMarks).reversed());
    }

    /**
     * Sorts list by marks (ascending: lowest to highest).
     */
    public void sortByMarksAscending() {
        studentList.sort(Comparator.comparingDouble(Student::getMarks));
    }

    /**
     * Sorts list alphabetically by student name.
     */
    public void sortByName() {
        studentList.sort(Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER));
    }

    /**
     * Sorts list by Student ID.
     */
    public void sortById() {
        studentList.sort(Comparator.comparingInt(Student::getId));
    }

    /**
     * Returns grade distribution breakdown: [A+, A, B, C, D, F].
     */
    public int[] getGradeDistribution() {
        // Index 0: A+, 1: A, 2: B, 3: C, 4: D, 5: F
        int[] counts = new int[6];
        for (Student s : studentList) {
            switch (s.getGrade()) {
                case "A+": counts[0]++; break;
                case "A":  counts[1]++; break;
                case "B":  counts[2]++; break;
                case "C":  counts[3]++; break;
                case "D":  counts[4]++; break;
                case "F":  counts[5]++; break;
            }
        }
        return counts;
    }

    /**
     * Populates initial sample data for quick demonstration.
     */
    public void loadSampleData() {
        addStudent("Emma Watson", 94.5);
        addStudent("Liam Smith", 82.0);
        addStudent("Sophia Johnson", 76.5);
        addStudent("Noah Brown", 58.0);
        addStudent("Olivia Davis", 91.0);
        addStudent("Ethan Miller", 44.0);
        addStudent("Ava Wilson", 88.5);
        addStudent("Lucas Garcia", 67.0);
    }

    /**
     * Clears all records and resets ID generator.
     */
    public void clearAll() {
        studentList.clear();
        nextId = 101;
    }
}
