package EventManagementSystem;

public class CmdCreateStudent implements Command {
    @Override
    public String execute(String[] cmdParts) throws CloneNotSupportedException {
    	String str = "";
        try {
            if (cmdParts.length != 8 || cmdParts[2].charAt(0) != 's') {
                throw new ExWrongCommand();
            }

            StudentHandler studentHandler = StudentHandler.getInstance();
            
            String studentID = cmdParts[2], firstName = cmdParts[4], lastName = cmdParts[5];
            int age = Integer.parseInt(cmdParts[7]);
            Major major = Major.getMajor(cmdParts[3]);
            try {
            	if (studentID.length() != 9 || Integer.parseInt(studentID.substring(1,8)) <0 || Integer.parseInt(studentID.substring(1,8)) > 99999999) {
            		throw new ExInvalidStudentID();
            	}
            } 
            catch (NumberFormatException ex) {
            	throw new ExInvalidStudentID();
            }
            if (firstName.length() > 20) {
                throw new ExFirstNameTooLong();
            }
            if (lastName.length() > 20) {
                throw new ExLastNameTooLong();
            }
            if (!(cmdParts[6].equals("M") || cmdParts[6].equals("F"))) {
                throw new ExWrongSexInput();
            }
            
            Student student = null;
            try {
            	student = studentHandler.getStudent(studentID);
            } catch (ExStudentNotFound e) {
            	char sex = cmdParts[6].charAt(0);
            	studentHandler.createStudent(new Student(studentID, major, firstName, lastName, sex, age));
            	str = "Created student " + firstName + " " + lastName + " with StudentID: " + studentID + ".";
            }
            finally {
            	if (student != null) {
            		throw new ExInvalidStudentID();
            	}
            }
            
            
        } catch (NumberFormatException e) {
        	str = "Wrong number format!\n";
        } catch (ExFirstNameTooLong | ExLastNameTooLong | ExWrongSexInput | ExInvalidStudentID |ExMajorNotFound e) {
        	str = e.getMessage();
        } catch (ExWrongCommand e) {
        	str = e.getMessage();
			str += "Create student command should be \"create student sXXXXXXXXX\" <major> <first name> <last name> <gender> <age>.\n";
    	}
        return str;
        
    }
}
