package EventManagementSystem;

public class CmdSearchStudent implements Command {
    @Override
    public String execute(String[] cmdParts) throws CloneNotSupportedException {
    	String str = ""; 
    	try {
    		if (cmdParts.length != 3 || cmdParts[2].charAt(0) != 's') {
    			throw new ExWrongCommand();
    		}
    		String studentID = null;

    		studentID = cmdParts[2];
        	if (!Student.checkStudentID(studentID)) {
        		throw new ExInvalidStudentID();
        	}
            
    		StudentHandler studentHandler = StudentHandler.getInstance();
    		Student student = studentHandler.getStudent(cmdParts[2]);
    		str += String.format("|%-9s|%-20s|%-20s|%-3s|%-30s|%-3s|\n", "StudentID", "First Name", "Last Name", "Sex", "Major", "Age");
    		str += student.printString();
    		return str;
    	}
    	catch (ExStudentNotFound | ExInvalidStudentID e) {
    		str = e.getMessage();
    		return str;
    	}
    	catch (ExWrongCommand e) {
    		str = e.getMessage();
    		str += "Search student command should be \"search student sXXXXXXXXX\"\n";
    		return str;
    	}
    	
    }
}
