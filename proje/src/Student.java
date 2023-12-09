
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
public class Student {
    String name;
    String lastName;
    int ID;
    String department;
    String faculty;

    public Student(String name, String lastName, int ID, String department, String faculty) {
        this.name = name;
        this.lastName = lastName;
        this.ID = ID;
        this.department = department;
        this.faculty = faculty;
    }
}
