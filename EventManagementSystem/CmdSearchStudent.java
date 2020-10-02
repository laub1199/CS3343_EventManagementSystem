package EventManagementSystem;

public class CmdSearchStudent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
    	
    	try {
    		if (cmdParts.length != 3) {
    			throw new ExWrongCommand();
    		}
    		StudentHandler studentHandler = StudentHandler.getInstance();
    		Student student = studentHandler.getStudent(cmdParts[2]);
        	System.out.printf("|%s|%-20s|%-20s|%s|%-10s|%s|\n", "StudentID", "First Name", "Last Name", "Sex", "Major", "Age");
        	System.out.println(student.printString());
    	}
    	catch (ExStudentNotFound e) {
			System.out.println(e.getMessage());
    	}
    	catch (ExWrongCommand e) {
			System.out.println(e.getMessage());
    	}
    	
    }
}
