package EventManagementSystem;

public class CmdCreateStudent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (cmdParts.length < 8) {
                throw new ExInsufficientArguments();
            }
            if (cmdParts[2].length() < 8) {
                throw new ExStudentIDtooLong();
            }
            if (cmdParts[3].length() < 10) {
                throw new ExMajorTooLong();
            }
            if (cmdParts[4].length() < 20) {
                throw new ExFirstNameTooLong();
            }
            if (cmdParts[5].length() < 20) {
                throw new ExLastNameTooLong();
            }
            if (cmdParts[6].charAt(0) != 'M' || cmdParts[6].charAt(0) != 'F') {
                throw new ExWrongSexInput();
            }
            StudentHandler s = StudentHandler.getInstance();
            s.createStudent(cmdParts[2], cmdParts[3], cmdParts[4], cmdParts[5], cmdParts[6].charAt(0), Integer.parseInt(cmdParts[7]));
        } catch (NumberFormatException e) {
            System.out.println("Wrong number format!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sex should be 1 character(M/F)!");
        } catch (ExInsufficientArguments | ExStudentIDtooLong | ExMajorTooLong | ExFirstNameTooLong | ExLastNameTooLong | ExWrongSexInput e) {
            System.out.println(e.getMessage());
        }
    }
}
