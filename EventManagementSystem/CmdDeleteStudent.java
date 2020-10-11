package EventManagementSystem;

public class CmdDeleteStudent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (cmdParts.length != 3) {
                throw new ExWrongCommand();
            }
            try {
            	String sID = cmdParts[2];
            	if (sID.length() != 9 || sID.charAt(0) != 's' || Integer.parseInt(sID.substring(1,8)) <0 || Integer.parseInt(sID.substring(1,8)) > 99999999) {
            		throw new ExInvalidStudentID();
            	}
            } 
            catch (NumberFormatException ex) {
            	throw new ExInvalidStudentID();
            }
            StudentHandler studentHandler = StudentHandler.getInstance();
            Student student = studentHandler.getStudent(cmdParts[2]);
            studentHandler.deleteStudent(student);
            System.out.println("Deleted student with StudentID: " + cmdParts[2] + ".");
        } catch (ExStudentNotFound | ExInvalidStudentID e) {
            System.out.println(e.getMessage());
        } catch (ExWrongCommand e) {
			System.out.println(e.getMessage());
			System.out.println("Delete student command should be \"delete student sXXXXXXXXX\"");
    	} 
    }
}
