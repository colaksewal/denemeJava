
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class OBSDatabase {

    protected static List<Student> studentList;

    public OBSDatabase() {
        this.studentList = new ArrayList<>();
    }

    public static void generateStudents(OBSDatabase studentInfo) {
        List<String> facultyNames = List.of("Faculty of Engineering", "Faculty of Science", "Faculty of Arts", "Faculty of Medicine", "Faculty of Business", "Faculty of Law", "Faculty of Social Sciences", "Faculty of Education", "Faculty of Agriculture");
        List<String> departmentNames = List.of("Computer Engineering", "Mechanical Engineering", "Electrical Engineering", "Physics", "Chemistry", "Mathematics", "History", "Psychology", "Biology");
        List<String> maleNames = readFile("https://www.cs.cmu.edu/Groups/AI/areas/nlp/corpora/names/male.txt");
        List<String> femaleNames = readFile("https://www.cs.cmu.edu/Groups/AI/areas/nlp/corpora/names/female.txt");
        List<String> lastNames = readFile("https://raw.githubusercontent.com/arineng/arincli/master/lib/last-names.txt");

        Random rand = new Random();

        for (int year = 2014; year <= 2023; year++) {
            for (String facultyName : facultyNames) {
                for (String departmentName : departmentNames) {
                    for (int i = 1; i <= 100; i++) {
                        String randomMaleName = getRandomName(maleNames);
                        String randomFemaleName = getRandomName(femaleNames);
                        String randomLastName = getRandomName(lastNames);

                        String name = rand.nextBoolean() ? randomMaleName : randomFemaleName;
                        int facultyIndex = facultyNames.indexOf(facultyName) + 1;
                        int departmentIndex = departmentNames.indexOf(departmentName) + 1;
                        int ID = (year%2000) * 10000000 + facultyIndex * 100000 + departmentIndex * 1000 + rand.nextInt(1000);

                        Student student = new Student(name, randomLastName, ID, departmentName, facultyName);
                        studentList.add(student);
                    }
                }
            }
        }
    }


    static void listStudents(List<Student> studentList) {
        System.out.println("Student List:");
        for (Student student : studentList) {
            System.out.println("ID: " + student.ID + ", Name: " + student.name + " " + student.lastName +
                    ", Faculty: " + student.faculty + ", Department: " + student.department);
        }
    }

    private static void findStudent(List<Student> studentList, String department, String faculty, int year) {
        for (Student student : studentList) {
            if (student.department.equals(department) && student.faculty.equals(faculty) && student.ID >= year * 1000 && student.ID < (year + 1) * 1000) {
                System.out.println("ID: " + student.ID + ", Name: " + student.name + " " + student.lastName +
                        ", Faculty: " + student.faculty + ", Department: " + student.department);
                return;
            }
        }
        System.out.println("Student did no found");
    }

    private static void deleteStudent(List<Student> studentList, int studentID) {
        for (Iterator<Student> iter = studentList.iterator(); iter.hasNext(); ) {
            Student student = iter.next();
            if (student.ID == studentID) {
                iter.remove();
                System.out.println("Delete: " + student.name + " " + student.lastName);
                return;
            }
        }
        System.out.println("Not found.");
    }

    private static void insertStudent(OBSDatabase data, String name, String lastName, int ID, String department, String faculty) {
        Student newStudent = new Student(name, lastName, ID, department, faculty);
        studentList.add(newStudent);
        System.out.println("New Student: " + newStudent.name + " " + newStudent.lastName);
    }


    private static String getRandomName(List<String> names) {
        Random rand = new Random();
        return names.get(rand.nextInt(names.size()));
    }

    private static List<String> readFile(String url) {
        List<String> names = new ArrayList<>();

        try (Scanner scanner = new Scanner(new URL(url).openStream(), StandardCharsets.UTF_8.toString())) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();


                if (!line.isEmpty() && !line.startsWith("#")) {
                    names.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }


    /*public static void main(String[] args) {
        OBSDatabase database = new OBSDatabase();

        // create student database
        database.generateStudents(database);

        listStudents(studentList);

    }
    */

}
