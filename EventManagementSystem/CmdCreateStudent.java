package EventManagementSystem;

public class CmdCreateStudent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (cmdParts.length != 8) {
                throw new ExWrongCommand();
            }
            StudentHandler s = StudentHandler.getInstance();
            s.createStudent(cmdParts[2], cmdParts[3], cmdParts[4], cmdParts[5], cmdParts[6].charAt(0), Integer.parseInt(cmdParts[7]));
        } catch (NumberFormatException e) {
            System.out.println("Wrong number format!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sex should be 1 character(M/F)!");
        } catch (ExWrongCommand | ExMajorTooLong | ExFirstNameTooLong | ExLastNameTooLong | ExWrongSexInput | ExInvalidStudentID e) {
            System.out.println(e.getMessage());
        }
    }
}
