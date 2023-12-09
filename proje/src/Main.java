public class Main {
    public static void main(String[] args) {


        OBSDatabase database = new OBSDatabase();

        // create student database
        database.generateStudents(database);

        OBSDatabase.listStudents(OBSDatabase.studentList);


    }
}
