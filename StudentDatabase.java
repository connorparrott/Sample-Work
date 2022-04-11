package Lab5;


import java.util.HashMap;
import java.util.HashSet;

/**
 *
 * @author yaw
 */
public class StudentDatabase {

    private HashMap<String, HashSet<Student>> database;

    public StudentDatabase() {
        database = new HashMap<>();
    }

    public void addStudent(String name, int studentNumber, double gpa) {
        Student newStudent = new Student(name, studentNumber, gpa);
        if (!database.containsKey(name)) {
            database.put(name, new HashSet<>());
        }
        database.get(name).add(newStudent);
    }

    public void printDatabase() {
        for (String name : database.keySet()) {
            for (Student student : database.get(name)) {
                System.out.println(student.getName() + ": (ID=" + student.getIdNumber() + "), (gpa=" + student.getGPA() + ")");
            }
        }
        System.out.println();
    }

    public void removeStudent(String name) {
    	database.remove(name);
    }

    public void countStudentsByName() {
        for(String name : database.keySet()) {
        	System.out.print(name + ": ");
        	System.out.print(database.get(name).size());
        	System.out.println();
        }
    }

    public void findStudentByID(int id) {
        String temp = null;	
    	for(String name : database.keySet()) {
        		for(Student student : database.get(name)) {
        			if(student.getIdNumber() == id) {
        				temp = student.getName();
        			}
        		}       		
        	}
    	if(temp == null) {
        	System.out.println("There is no student with that ID");
    	} else {
    		System.out.println(temp);
    	}
   } 
        	
    

    public void printStudentsByGPA() {
    	
    	HashMap<Double, HashSet<Student>> gpaDatabase = new HashMap<>();
    	
    	for(String name : database.keySet()) {
    		for(Student student : database.get(name)) {
    			 double gpa = student.getGPA();
    			 	if (!gpaDatabase.containsKey(gpa)) {
    		            gpaDatabase.put(gpa, new HashSet<>());
    		        }
    		        gpaDatabase.get(gpa).add(student);
    		}
    	}
    	
    	for(Double gpa : gpaDatabase.keySet()) {
    		System.out.println(gpa + ": ");
    		for(Student student : gpaDatabase.get(gpa)) {
    			System.out.println(student.getName() + ", " + student.getIdNumber());
    		}
    	}
    }
}
