package EventManagementSystem;

public class CmdDeleteStudent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (cmdParts.length < 3) {
                throw new ExInsufficientArguments();
            }
            StudentHandler s = StudentHandler.getInstance();
            s.deleteStudent(cmdParts[3]);
        } catch (NullPointerException e) {
            System.out.println("Wrong Student ID!");
        } catch (ExInsufficientArguments e) {
            System.out.println(e.getMessage());
        }
    }
}
