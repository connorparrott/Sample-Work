package Lab5;


/**
 *
 * @author yaw
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        StudentDatabase database = new StudentDatabase();
        
        database.addStudent("Joe Schmo", 1101, 3.2);
        database.addStudent("Katie Katerson", 31415926, 3.7);
        database.addStudent("Watson TheBassetHound", 12345, 2.4);
        database.addStudent("Joe Schmo", 99999, 2.4);
        database.addStudent("Joe Schmo", 12345, 4.0);
        
        
        database.printDatabase();
        
        System.out.println();
        System.out.println("Count Students by Name:");
        database.countStudentsByName();
        
        System.out.println();
        System.out.println("Find Students By ID:");
        database.findStudentByID(1101);
        database.findStudentByID(1128310);
        
        System.out.println();
        System.out.println("Print students by GPA:");
        database.printStudentsByGPA();
    }
    
}

