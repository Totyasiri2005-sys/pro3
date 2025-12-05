package project3;

import java.io.*;
import java.util.*;

public class RegistrationSystem {

    private ArrayList<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.txt";

    public RegistrationSystem() {
        loadFromFile();
    }

    // ----------------------------------------------------
    // LOAD DATA FROM FILE
    // ----------------------------------------------------
    private void loadFromFile() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return;

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                // expected: type,id,name,numCourses,mark1,mark2,...

                if (parts.length < 4) continue; // سطر غير صالح، تجاهله

                String type = parts[0];
                String id = parts[1];
                String name = parts[2];
                int numCourses = Integer.parseInt(parts[3]);

                Student s;
                if (type.equalsIgnoreCase("U"))
                    s = new Undergraduate(id, name, numCourses);
                else
                    s = new Graduated(id, name, numCourses);

                // لو فيه علامات في الملف، اقرأها
                if (parts.length >= 5) {
                    double[] marks = new double[numCourses];
                    // قد يكون عدد الأجزاء أقل من numCourses — نتعامل مع ذلك
                    for (int i = 0; i < numCourses; i++) {
                        int partIndex = 4 + i;
                        if (partIndex < parts.length && !parts[partIndex].isEmpty()) {
                            try {
                                marks[i] = Double.parseDouble(parts[partIndex]);
                            } catch (NumberFormatException e) {
                                marks[i] = 0;
                            }
                        } else {
                            marks[i] = 0; // قيمة افتراضية لو ما موجودة
                        }
                    }
                    s.setMarks(marks);
                } else {
                    s.setMarks(null); // أو new double[numCourses] حسب ما تفضّلين
                }

                students.add(s);
            }

            sc.close();
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
            // e.printStackTrace(); // لفحص الأخطاء أثناء التطوير
        }
    }

    // ----------------------------------------------------
    // SAVE DATA TO FILE
    // ----------------------------------------------------
    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {

            for (Student s : students) {
                // type,id,name,numCourses,[marks...]
                pw.print(s.getType() + "," + s.getKkuId() + "," + s.getName() + "," + s.getNumCourses());

                double[] marks = s.getMarks();
                if (marks != null) {
                    for (double m : marks) {
                        pw.print("," + m);
                    }
                }
                pw.println();
            }

        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
            // e.printStackTrace();
        }
    }

    // ----------------------------------------------------
    // ADD STUDENT
    // ----------------------------------------------------
    public void addStudent(Scanner input) {
        input.nextLine(); // يستهلك newline من الإدخالات السابقة
        System.out.print("Enter KKU ID: ");
        String id = input.nextLine().trim();

        System.out.print("Enter student name: ");
        String name = input.nextLine().trim();

        System.out.print("Enter number of courses: ");
        int num = Integer.parseInt(input.nextLine().trim());

        System.out.print("Graduate (G) or Undergraduate (U)? ");
        String type = input.nextLine().trim().toUpperCase();

        Student s;
        if (type.equals("U"))
            s = new Undergraduate(id, name, num);
        else
            s = new Graduated(id, name, num);

        students.add(s);
        saveToFile();

        System.out.println("Student added successfully!");
    }

    // ----------------------------------------------------
    // ENTER MARKS
    // ----------------------------------------------------
    public void enterMarks(Scanner input) {
        input.nextLine();
        System.out.print("Enter Student ID: ");
        String id = input.nextLine().trim();

        Student s = findById(id);
        if (s == null) {
            System.out.println("Student not found!");
            return;
        }

        double[] marks = new double[s.getNumCourses()];
        for (int i = 0; i < marks.length; i++) {
            System.out.print("Enter mark for course " + (i + 1) + ": ");
            marks[i] = Double.parseDouble(input.nextLine().trim());
        }

        s.setMarks(marks);
        saveToFile();

        System.out.println("Marks saved!");
    }

    // ----------------------------------------------------
    // DISPLAY MARKS INFO
    // ----------------------------------------------------
    public void displayMarksInfo(Scanner input) {
        input.nextLine();
        System.out.print("Enter Student ID: ");
        String id = input.nextLine().trim();

        Student s = findById(id);
        if (s == null) {
            System.out.println("Student not found!");
            return;
        }

        double[] marks = s.getMarks();
        System.out.println("Marks: " + (marks == null ? "No marks" : Arrays.toString(marks)));
        System.out.println("Average: " + s.calculateAverage());
        System.out.println("Maximum: " + s.findMax());
        System.out.println("Minimum: " + s.findMin());
        System.out.println(s.isPass() ? "PASS" : "FAIL");
    }

    // ----------------------------------------------------
    // DISPLAY ALL STUDENTS
    // ----------------------------------------------------
    public void displayAllStudents() {
        for (Student s : students) {
            System.out.println(s.getName() + " - " + s.getKkuId());
        }
    }

    // ----------------------------------------------------
    // MODIFY MARKS
    // ----------------------------------------------------
    public void modifyMarks(Scanner input) {
        enterMarks(input); // إعادة استخدام نفس المنطق
    }

    // ----------------------------------------------------
    // FIND STUDENT BY ID
    // ----------------------------------------------------
    private Student findById(String id) {
        for (Student s : students)
            if (s.getKkuId().equals(id))
                return s;
        return null;
    }
}