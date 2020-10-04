package EventManagementSystem;

public class CmdSearchStudent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
    	
    	try {
    		StudentHandler studentHandler = StudentHandler.getInstance();
    		Student student = studentHandler.getStudent(cmdParts[2]);
        	System.out.printf("|%s|%-20s|%-20s|%s|%-10s|%s|", "StudentID", "First Name", "Last Name", "Sex", "Major", "Age");
        	System.out.println(student.printString());
    	}
    	catch (ExStudentNotFound e) {
			System.out.println(e.getMessage());
    	}
    	
    }
}
