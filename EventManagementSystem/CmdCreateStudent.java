package EventManagementSystem;

public class CmdCreateStudent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (cmdParts.length != 8) {
                throw new ExWrongCommand();
            }

            StudentHandler studentHandler = StudentHandler.getInstance();
            
            String studentID = cmdParts[2], major = cmdParts[3], firstName = cmdParts[4], lastName = cmdParts[5];
            int age = Integer.parseInt(cmdParts[7]);
            if (studentID.length() != 9 || studentID.charAt(0) != 's') {
                throw new ExInvalidStudentID();
            }
            if (major.length() > 10) {
                throw new ExMajorTooLong();
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
        } catch (ExWrongCommand | ExMajorTooLong | ExFirstNameTooLong | ExLastNameTooLong | ExWrongSexInput | ExInvalidStudentID e) {
            System.out.println(e.getMessage());
        }
    }
}
