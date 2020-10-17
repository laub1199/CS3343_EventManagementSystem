package EventManagementSystem;

public class CmdDeleteStudent implements Command {
    @Override
    public String execute(String[] cmdParts) throws CloneNotSupportedException {
    	String str = ""; 
        try {
            if (cmdParts.length != 3 || cmdParts[2].charAt(0) != 's') {
                throw new ExWrongCommand();
            }
            try {
            	String sID = cmdParts[2];
            	if (sID.length() != 9 || Integer.parseInt(sID.substring(1,8)) <0 || Integer.parseInt(sID.substring(1,8)) > 99999999) {
            		throw new ExInvalidStudentID();
            	}
            } 
            catch (NumberFormatException ex) {
            	throw new ExInvalidStudentID();
            }
            StudentHandler studentHandler = StudentHandler.getInstance();
            Student student = studentHandler.getStudent(cmdParts[2]);
            studentHandler.deleteStudent(student);
            str = "Deleted student with StudentID: " + cmdParts[2] + ".";
        } catch (ExStudentNotFound | ExInvalidStudentID e) {
        	str = e.getMessage();
        } catch (ExWrongCommand e) {
        	str = e.getMessage();
        	str += "Delete student command should be \"delete student sXXXXXXXXX\"\n";
    	} 
        return str;
        
    }
}
