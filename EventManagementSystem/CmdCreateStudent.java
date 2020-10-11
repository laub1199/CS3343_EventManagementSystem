package EventManagementSystem;

public class CmdCreateStudent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (cmdParts.length != 8) {
                throw new ExWrongCommand();
            }

            StudentHandler studentHandler = StudentHandler.getInstance();
            
            String studentID = cmdParts[2], firstName = cmdParts[4], lastName = cmdParts[5];
            int age = Integer.parseInt(cmdParts[7]);
            Major major = Major.getMajor(cmdParts[3]);
            if(major == null) {
            	throw new ExMajorNotFound();
            }
            try {
            	if (studentID.length() != 9 || studentID.charAt(0) != 's' || Integer.parseInt(studentID.substring(1,8)) <0 || Integer.parseInt(studentID.substring(1,8)) > 99999999) {
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
            	System.out.println("Created student " + firstName + " " + lastName + " with StudentID: " + studentID + ".");
            }
            finally {
            	if (student != null) {
            		throw new ExInvalidStudentID();
            	}
            }
            
            
        } catch (NumberFormatException e) {
            System.out.println("Wrong number format!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sex should be 1 character(M/F)!");
        } catch (ExFirstNameTooLong | ExLastNameTooLong | ExWrongSexInput | ExInvalidStudentID |ExMajorNotFound e) {
            System.out.println(e.getMessage());
        } catch (ExWrongCommand e) {
			System.out.println(e.getMessage());
			System.out.println("Create student command should be \"create student sXXXXXXXXX\".");
    	}
    }
}
