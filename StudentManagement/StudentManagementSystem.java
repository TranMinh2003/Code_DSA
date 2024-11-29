/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagement;

/**
 *
 * @author Admin
 */
import java.util.Optional;
import java.util.Scanner;

public class StudentManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManager studentManager = new StudentManager();

        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Student Management System ---");
    System.out.println("1. Add a new student");
    System.out.println("2. Edit an existing student");
    System.out.println("3. Delete a student");
    System.out.println("4. Display all students");
    System.out.println("5. Sort students by marks");
    System.out.println("6. Find a student by ID");
    System.out.println("7. Generate 10 random students");
    System.out.println("8. Exit");
    System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume the newline character 

            switch (choice) {
                case 1: // Add a new student
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Student Marks: ");
                    double marks = scanner.nextDouble();
                    scanner.nextLine(); // consume the newline character

                    studentManager.addStudent(new Student(id, name, marks));
                    System.out.println("Student added successfully.");
                    break;

                case 2: // Edit an existing student
                    System.out.print("Enter the ID of the student to edit: ");
                    String editId = scanner.nextLine();
                    System.out.print("Enter the new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter the new marks: ");
                    double newMarks = scanner.nextDouble();
                    scanner.nextLine(); // consume the newline character

                    studentManager.editStudent(editId, newName, newMarks);
                    System.out.println("Student updated successfully.");
                    break;

                case 3: // Delete a student
                    System.out.print("Enter the ID of the student to delete: ");
                    String deleteId = scanner.nextLine();
                    studentManager.deleteStudent(deleteId);
                    System.out.println("Student deleted successfully.");
                    break;

                case 4: // Display all students
                    System.out.println("\n--- Student List ---");
                    studentManager.displayStudents();
                    break;

                case 5: // Sort students by marks
                    System.out.println("Choose sorting order:");
                    System.out.println("1. Ascending (Low to High)");
                    System.out.println("2. Descending (High to Low)");
                    System.out.print("Enter your choice: ");
                    int sortChoice = scanner.nextInt();
                    scanner.nextLine(); // consume the newline character

                    if (sortChoice == 1) {
                        studentManager.sortStudents(true); // Sắp xếp tăng dần
                        System.out.println("Students sorted by marks in ascending order.");
                    } else if (sortChoice == 2) {
                        studentManager.sortStudents(false); // Sắp xếp giảm dần
                        System.out.println("Students sorted by marks in descending order.");
                    } else {
                        System.out.println("Invalid choice. Sorting canceled.");
                    }
                    break;


                case 6: // Find a student by ID
                    System.out.print("Enter the ID of the student to find: ");
                    String findId = scanner.nextLine();
                    Optional<Student> studentOptional = studentManager.findStudentById(findId);
                    if (studentOptional.isPresent()) {
                        Student student = studentOptional.get();
                        System.out.printf("ID: %s, Name: %s, Marks: %.2f, Ranking: %s%n",
                                student.getId(), student.getName(), student.getMarks(), student.getRanking());
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 7: // Generate random students
                    studentManager.generateRandomStudents();
                    System.out.println("Random students generated successfully.");
                    break;
                    
                case 8: // Exit
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;

                default: // Invalid option
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
