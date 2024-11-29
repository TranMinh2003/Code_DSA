/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StudentManagement;

/**
 *
 * @author Admin
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class StudentManager {
    private List<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void editStudent(String id, String newName, double newMarks) {
        Optional<Student> studentOptional = findStudentById(id);
        studentOptional.ifPresent(student -> {
            students.remove(student);
            students.add(new Student(id, newName, newMarks));
        });
    }

    public void deleteStudent(String id) {
        Optional<Student> studentOptional = findStudentById(id);
        studentOptional.ifPresent(students::remove);
    }

    public Optional<Student> findStudentById(String id) {
        return students.stream().filter(student -> student.getId().equals(id)).findFirst();
    }

    public void sortStudents(boolean ascending) {
    // Sử dụng Bubble Sort
    // for (int i = 0; i < students.size() - 1; i++) {
    //     for (int j = 0; j < students.size() - i - 1; j++) {
    //         // Điều kiện hoán đổi
    //         if ((ascending && students.get(j).getMarks() > students.get(j + 1).getMarks())
    //                 || (!ascending && students.get(j).getMarks() < students.get(j + 1).getMarks())) {
    //             // Hoán đổi vị trí 2 sinh viên
    //             Student temp = students.get(j);
    //             students.set(j, students.get(j + 1));
    //             students.set(j + 1, temp);
    //         }
    //     }
    // }

    // Sử dụng Merge Sort
    students = mergeSort(students, ascending);
}

private List<Student> mergeSort(List<Student> list, boolean ascending) {
    if (list.size() <= 1) {
        return list;
    }

    int mid = list.size() / 2;
    List<Student> left = mergeSort(list.subList(0, mid), ascending);
    List<Student> right = mergeSort(list.subList(mid, list.size()), ascending);

    return merge(left, right, ascending);
}

private List<Student> merge(List<Student> left, List<Student> right, boolean ascending) {
    List<Student> merged = new ArrayList<>();
    int i = 0, j = 0;

    while (i < left.size() && j < right.size()) {
        if ((ascending && left.get(i).getMarks() <= right.get(j).getMarks())
                || (!ascending && left.get(i).getMarks() >= right.get(j).getMarks())) {
            merged.add(left.get(i));
            i++;
        } else {
            merged.add(right.get(j));
            j++;
        }
    }

    // Thêm phần còn lại của left hoặc right vào merged
    while (i < left.size()) {
        merged.add(left.get(i));
        i++;
    }
    while (j < right.size()) {
        merged.add(right.get(j));
        j++;
    }

    return merged;
}



    public void displayStudents() {
        for (Student student : students) {
            System.out.printf("ID: %s, Name: %s, Marks: %.2f, Ranking: %s%n",
                    student.getId(), student.getName(), student.getMarks(), student.getRanking());
        }
    }
    
    public void generateRandomStudents() {
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            String id = "" + String.format("%03d", i); // ID dạng S001, S002,...
            String name = "Student" + i; // Tên dạng Student1, Student2,...
            double marks = 1.0 + (9.0 - 1.0) * random.nextDouble(); // Điểm ngẫu nhiên từ 1.0 đến 9.0
            students.add(new Student(id, name, marks));
        }
        System.out.println("10 random students have been generated and added.");
    }
}
