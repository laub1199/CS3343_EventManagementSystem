package EventManagementSystem;

public class CmdSearchStudent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
    	
    	try {
    		if (cmdParts.length != 3) {
    			throw new ExWrongCommand();
    		}
    		String studentID = null;
    		try {
    			studentID = cmdParts[2];
            	if (studentID.length() != 9 || studentID.charAt(0) != 's' || Integer.parseInt(studentID.substring(1,8)) <0 || Integer.parseInt(studentID.substring(1,8)) > 99999999) {
            		throw new ExInvalidStudentID();
            	}
            } 
            catch (NumberFormatException ex) {
            	throw new ExInvalidStudentID();
            }
    		StudentHandler studentHandler = StudentHandler.getInstance();
    		Student student = studentHandler.getStudent(cmdParts[2]);
	        System.out.printf("|%-9s|%-20s|%-20s|%-3s|%-30s|%-3s|\n", "StudentID", "First Name", "Last Name", "Sex", "Major", "Age");
        	System.out.println(student.printString());
    	}
    	catch (ExStudentNotFound | ExInvalidStudentID e) {
			System.out.println(e.getMessage());
    	}
    	catch (ExWrongCommand e) {
			System.out.println(e.getMessage());
			System.out.println("Search student command should be \"search student sXXXXXXXXX\"");
    	}
    	
    }
}
