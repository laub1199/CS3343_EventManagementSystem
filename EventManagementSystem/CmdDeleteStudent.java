package EventManagementSystem;

public class CmdDeleteStudent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (cmdParts.length != 3) {
                throw new ExWrongCommand();
            }
            StudentHandler studentHandler = StudentHandler.getInstance();
            Student student = studentHandler.getStudent(cmdParts[2]);
            studentHandler.deleteStudent(student);
        } catch (ExWrongCommand | ExStudentNotFound e) {
            System.out.println(e.getMessage());
        } 
    }
}
