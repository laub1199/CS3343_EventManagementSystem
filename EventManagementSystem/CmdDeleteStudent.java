package EventManagementSystem;

public class CmdDeleteStudent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (cmdParts.length != 3) {
                throw new ExWrongCommand();
            }
<<<<<<< HEAD
            StudentHandler studentHandler = StudentHandler.getInstance();
            Student student = studentHandler.getStudent(cmdParts[3]);
            studentHandler.deleteStudent(student);
=======
            StudentHandler s = StudentHandler.getInstance();
            s.deleteStudent(cmdParts[2]);
>>>>>>> 6996f28f9eb1f97237e0c231e9631ce68f37aa4b
        } catch (NullPointerException e) {
            System.out.println("Wrong Student ID!");
        } catch (ExWrongCommand e) {
            System.out.println(e.getMessage());
        } catch (ExStudentNotFound e) {
        	System.out.println(e.getMessage());
		}
    }
}
