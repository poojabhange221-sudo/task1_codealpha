import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Student Class implementing standard Encapsulation and OOP concepts.
 */
class Student {
    private String name;
    private double marks;

    // Default Constructor
    public Student(String name, double marks) {
        setName(name);
        setMarks(marks);
    }

    // Encapsulation: Getter and Setter for Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            this.name = "Unknown Student";
        } else {
            this.name = name.trim();
        }
    }

    // Encapsulation: Getter and Setter for Marks with Input Validation
    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        if (marks < 0.0 || marks > 100.0) {
            throw new IllegalArgumentException("Marks must be between 0.0 and 100.0 inclusive.");
        }
        this.marks = marks;
    }

    // Calculates the Grade based on the score
    public String getLetterGrade() {
        if (marks >= 90.0) {
            return "A";
        } else if (marks >= 80.0) {
            return "B";
        } else if (marks >= 70.0) {
            return "C";
        } else if (marks >= 60.0) {
            return "D";
        } else {
            return "F";
        }
    }

    @Override
    public String toString() {
        return String.format("Name: %-15s | Marks: %-6.2f | Grade: %s", name, marks, getLetterGrade());
    }
}

/**
 * GradeTracker Class to manage a list of Student Objects and perform search, sorts, and calculations.
 */
class GradeTracker {
    private ArrayList<Student> students;

    public GradeTracker() {
        this.students = new ArrayList<>();
    }

    // Adds a student to the tracker
    public void addStudent(Student student) {
        if (student != null) {
            students.add(student);
        }
    }

    // Getter for Student List
    public ArrayList<Student> getStudents() {
        return students;
    }

    // Returns the total number of student records
    public int getStudentCount() {
        return students.size();
    }

    // Calculates the Average Score of all student records
    public double calculateAverageScore() {
        if (students.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (Student s : students) {
            sum += s.getMarks();
        }
        return sum / students.size();
    }

    // Gets the Student with the highest score
    public Student getHighestScoringStudent() {
        if (students.isEmpty()) {
            return null;
        }
        Student highest = students.get(0);
        for (Student s : students) {
            if (s.getMarks() > highest.getMarks()) {
                highest = s;
            }
        }
        return highest;
    }

    // Gets the Student with the lowest score
    public Student getLowestScoringStudent() {
        if (students.isEmpty()) {
            return null;
        }
        Student lowest = students.get(0);
        for (Student s : students) {
            if (s.getMarks() < lowest.getMarks()) {
                lowest = s;
            }
        }
        return lowest;
    }

    // Search for students matching a substring in their name (case-insensitive)
    public ArrayList<Student> searchStudentByName(String targetName) {
        ArrayList<Student> results = new ArrayList<>();
        if (targetName == null || targetName.trim().isEmpty()) {
            return results;
        }
        String searchStr = targetName.toLowerCase().trim();
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(searchStr)) {
                results.add(s);
            }
        }
        return results;
    }

    // Sort students by marks (descending)
    public void sortByMarksDescending() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.getMarks(), s1.getMarks()); // Descending
            }
        });
    }

    // Sort students by marks (ascending)
    public void sortByMarksAscending() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s1.getMarks(), s2.getMarks()); // Ascending
            }
        });
    }

    // Sort students alphabetically by name
    public void sortByNameAlphabetical() {
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return s1.getName().compareToIgnoreCase(s2.getName());
            }
        });
    }
}

/**
 * Main Class providing the menu-driven command line interface.
 */
public class StudentGradeTracker {

    // Helper method to display the banner
    private static void displayBanner() {
        System.out.println("==================================================");
        System.out.println("          STUDENT GRADE TRACKER SYSTEM            ");
        System.out.println("==================================================");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GradeTracker tracker = new GradeTracker();

        // Adding some sample data for demonstration/initial convenience
        tracker.addStudent(new Student("Alice Smith", 88.5));
        tracker.addStudent(new Student("Bob Johnson", 95.0));
        tracker.addStudent(new Student("Charlie Brown", 72.0));
        tracker.addStudent(new Student("Diana Prince", 91.5));
        tracker.addStudent(new Student("Evan Wright", 58.0));

        boolean running = true;

        while (running) {
            displayBanner();
            System.out.println("1. Add New Student Detail");
            System.out.println("2. View All Student Records");
            System.out.println("3. Search Student by Name");
            System.out.println("4. Sort Student Records");
            System.out.println("5. View Class Statistics (Average, High, Low)");
            System.out.println("6. Generate Complete Summary Report");
            System.out.println("7. Exit Application");
            System.out.println("==================================================");
            System.out.print("Please select an option (1-7): ");

            String choiceInput = scanner.nextLine();
            System.out.println();

            int choice = -1;
            try {
                choice = Integer.parseInt(choiceInput.trim());
            } catch (NumberFormatException e) {
                System.out.println("[-] Error: Invalid input option. Please enter a number from 1 to 7.");
                System.out.println();
                continue;
            }

            switch (choice) {
                case 1: // Add New Student Detail
                    System.out.println(">>> [1] ADD NEW STUDENT DETAIL <<<");
                    System.out.print("Enter Student Full Name: ");
                    String name = scanner.nextLine().trim();
                    if (name.isEmpty()) {
                        System.out.println("[-] Error: Name cannot be blank.");
                        System.out.println();
                        break;
                    }

                    System.out.print("Enter Marks (0.0 to 100.0): ");
                    String marksInput = scanner.nextLine().trim();
                    double marks = -1;
                    try {
                        marks = Double.parseDouble(marksInput);
                        if (marks < 0.0 || marks > 100.0) {
                            System.out.println("[-] Error: Marks must be between 0.0 and 100.0 inclusive.");
                            System.out.println();
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("[-] Error: Marks must be a valid decimal/integer number.");
                        System.out.println();
                        break;
                    }

                    // Create & Add New Student
                    try {
                        Student newStudent = new Student(name, marks);
                        tracker.addStudent(newStudent);
                        System.out.println("[+] Success: Student records updated!");
                        System.out.println("    " + newStudent);
                    } catch (IllegalArgumentException e) {
                        System.out.println("[-] Error: " + e.getMessage());
                    }
                    System.out.println();
                    break;

                case 2: // View All Student Records
                    System.out.println(">>> [2] VIEW ALL STUDENT RECORDS <<<");
                    if (tracker.getStudentCount() == 0) {
                        System.out.println("No student records available. Please add some students first.");
                    } else {
                        System.out.println(String.format("%-4s | %-20s | %-8s | %-6s", "No.", "Student Name", "Marks", "Grade"));
                        System.out.println("--------------------------------------------------");
                        int idx = 1;
                        for (Student s : tracker.getStudents()) {
                            System.out.println(String.format("%02d.  | %-20s | %-8.2f | %-6s", idx++, s.getName(), s.getMarks(), s.getLetterGrade()));
                        }
                        System.out.println("Total: " + tracker.getStudentCount() + " Students listed.");
                    }
                    System.out.println();
                    break;

                case 3: // Search Student by Name
                    System.out.println(">>> [3] SEARCH STUDENT BY NAME <<<");
                    System.out.print("Enter name or partial name to search: ");
                    String searchQuery = scanner.nextLine().trim();
                    if (searchQuery.isEmpty()) {
                        System.out.println("[-] Error: Search query cannot be empty.");
                        System.out.println();
                        break;
                    }

                    ArrayList<Student> found = tracker.searchStudentByName(searchQuery);
                    if (found.isEmpty()) {
                        System.out.println("[-] No student records found matching: \"" + searchQuery + "\"");
                    } else {
                        System.out.println(String.format("[+] Found %d match(es):", found.size()));
                        System.out.println(String.format("%-4s | %-20s | %-8s | %-6s", "No.", "Student Name", "Marks", "Grade"));
                        System.out.println("--------------------------------------------------");
                        int idx = 1;
                        for (Student s : found) {
                            System.out.println(String.format("%02d.  | %-20s | %-8.2f | %-6s", idx++, s.getName(), s.getMarks(), s.getLetterGrade()));
                        }
                    }
                    System.out.println();
                    break;

                case 4: // Sort Student Records
                    System.out.println(">>> [4] SORT STUDENT RECORDS <<<");
                    if (tracker.getStudentCount() == 0) {
                        System.out.println("No student records to sort.");
                        System.out.println();
                        break;
                    }
                    System.out.println("1. Sort by Marks (High to Low)");
                    System.out.println("2. Sort by Marks (Low to High)");
                    System.out.println("3. Sort by Name (A-Z)");
                    System.out.print("Select sorting strategy: ");
                    String sortChoice = scanner.nextLine().trim();
                    
                    if (sortChoice.equals("1")) {
                        tracker.sortByMarksDescending();
                        System.out.println("[+] Success: Records sorted by marks descending!");
                    } else if (sortChoice.equals("2")) {
                        tracker.sortByMarksAscending();
                        System.out.println("[+] Success: Records sorted by marks ascending!");
                    } else if (sortChoice.equals("3")) {
                        tracker.sortByNameAlphabetical();
                        System.out.println("[+] Success: Records sorted alphabetically!");
                    } else {
                        System.out.println("[-] Error: Invalid sorting option selected.");
                    }
                    System.out.println();
                    break;

                case 5: // View Class Statistics
                    System.out.println(">>> [5] VIEW CLASS STATISTICS <<<");
                    if (tracker.getStudentCount() == 0) {
                        System.out.println("No student records available to calculate statistics.");
                    } else {
                        double avg = tracker.calculateAverageScore();
                        Student high = tracker.getHighestScoringStudent();
                        Student low = tracker.getLowestScoringStudent();

                        System.out.println("Total Students Registered : " + tracker.getStudentCount());
                        System.out.println(String.format("Class Arithmetic Mean    : %.2f", avg));
                        System.out.println(String.format("Highest Performing Student: %s (%.2f / Grade %s)", 
                                high.getName(), high.getMarks(), high.getLetterGrade()));
                        System.out.println(String.format("Lowest Performing Student : %s (%.2f / Grade %s)", 
                                low.getName(), low.getMarks(), low.getLetterGrade()));
                    }
                    System.out.println();
                    break;

                case 6: // Generate Complete Summary Report
                    System.out.println(">>> [6] GENERATING COMPLETE SUMMARY REPORT <<<");
                    if (tracker.getStudentCount() == 0) {
                        System.out.println("No records found. Cannot produce report.");
                    } else {
                        System.out.println("==================================================");
                        System.out.println("             OFFICIAL CLASS REPORT                 ");
                        System.out.println("==================================================");
                        System.out.println(String.format("%-25s | %-10s | %-5s", "Student Name", "Score (100)", "Grade"));
                        System.out.println("--------------------------------------------------");
                        for (Student s : tracker.getStudents()) {
                            System.out.println(String.format("%-25s | %-10.2f | %-5s", s.getName(), s.getMarks(), s.getLetterGrade()));
                        }
                        System.out.println("==================================================");
                        System.out.println("SUMMARY STATISTICS:");
                        System.out.println("--------------------------------------------------");
                        System.out.println(String.format("Total Students  : %d", tracker.getStudentCount()));
                        System.out.println(String.format("Class Average   : %.2f%%", tracker.calculateAverageScore()));
                        
                        Student highest = tracker.getHighestScoringStudent();
                        Student lowest = tracker.getLowestScoringStudent();
                        System.out.println(String.format("Highest score   : %-5.2f%% (%s)", highest.getMarks(), highest.getName()));
                        System.out.println(String.format("Lowest score    : %-5.2f%% (%s)", lowest.getMarks(), lowest.getName()));
                        System.out.println("==================================================");
                    }
                    System.out.println();
                    break;

                case 7: // Exit Option
                    running = false;
                    System.out.println("Thank you for using the Student Grade Tracker application. Exiting system...");
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("[-] Error: Choice out of bounds. Please select a number from 1 to 7.");
                    System.out.println();
            }
        }
        scanner.close();
    }
}
