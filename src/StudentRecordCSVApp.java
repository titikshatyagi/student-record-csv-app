import java.io.*;
import java.util.*;

class Student {
    private int id;
    private String name;
    private int age;
    private String course;

    public Student(int id, String name, int age, String course) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getCourse() { return course; }

    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setCourse(String course) { this.course = course; }

    @Override
    public String toString() {
        return id + "," + name + "," + age + "," + course;
    }

    public String display() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Course: " + course;
    }
}

public class StudentRecordCSVApp {
    static final String FILE_NAME = "students.csv";
    static Map<Integer, Student> studentMap = new HashMap<>();

    public static void main(String[] args) {
        loadFromCSV();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Student\n2. View All\n3. Search by ID\n4. Update Student\n5. Delete Student\n6. Exit");
            int choice = sc.nextInt(); sc.nextLine();
            switch (choice) {
                case 1 -> addStudent(sc);
                case 2 -> viewAll();
                case 3 -> searchStudent(sc);
                case 4 -> updateStudent(sc);
                case 5 -> deleteStudent(sc);
                case 6 -> {
                    saveToCSV();
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void addStudent(Scanner sc) {
        System.out.print("Enter ID: ");
        int id = sc.nextInt(); sc.nextLine();
        if (studentMap.containsKey(id)) {
            System.out.println("ID already exists. Use update or delete it first.");
            return;
        }
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Age: ");
        int age = sc.nextInt(); sc.nextLine();
        System.out.print("Enter Course: ");
        String course = sc.nextLine();
        Student s = new Student(id, name, age, course);
        studentMap.put(id, s);
        saveToCSV();
        System.out.println("Student added.");
    }

    static void viewAll() {
        if (studentMap.isEmpty()) {
            System.out.println("No students found.");
            return;
        }
        for (Student s : studentMap.values()) {
            System.out.println(s.display());
        }
    }

    static void searchStudent(Scanner sc) {
        System.out.print("Enter ID to search: ");
        int id = sc.nextInt(); sc.nextLine();
        Student s = studentMap.get(id);
        if (s != null) System.out.println(s.display());
        else System.out.println("Student not found.");
    }

    static void updateStudent(Scanner sc) {
        System.out.print("Enter ID to update: ");
        int id = sc.nextInt(); sc.nextLine();
        Student s = studentMap.get(id);
        if (s == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter New Name: ");
        s.setName(sc.nextLine());
        System.out.print("Enter New Age: ");
        s.setAge(sc.nextInt()); sc.nextLine();
        System.out.print("Enter New Course: ");
        s.setCourse(sc.nextLine());
        saveToCSV();
        System.out.println("Student updated.");
    }

    static void deleteStudent(Scanner sc) {
        System.out.print("Enter ID to delete: ");
        int id = sc.nextInt(); sc.nextLine();
        if (studentMap.remove(id) != null) {
            saveToCSV();
            System.out.println("Student deleted.");
        } else {
            System.out.println("Student not found.");
        }
    }

    static void saveToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student s : studentMap.values()) {
                writer.write(s.toString());
                writer.newLine();
            }
            // System.out.println("Data saved to CSV.");
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    static void loadFromCSV() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length == 4) {
                    int id = Integer.parseInt(tokens[0]);
                    String name = tokens[1];
                    int age = Integer.parseInt(tokens[2]);
                    String course = tokens[3];
                    studentMap.put(id, new Student(id, name, age, course));
                }
            }
            System.out.println("Data loaded from CSV. " + studentMap.size() + " students.");
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}
