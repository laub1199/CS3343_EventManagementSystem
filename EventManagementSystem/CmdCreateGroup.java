package EventManagementSystem;

public class CmdCreateGroup implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (cmdParts.length != 4) {
                throw new ExWrongCommand();
            }
            GroupHandler instance = GroupHandler.getInstance();
            instance.createGroup(cmdParts[2], Integer.parseInt(cmdParts[3]));
        } catch (NumberFormatException e) {
            System.out.println("Wrong number format!");
        } catch (ExWrongCommand | ExInvalidGroupID | ExGroupStudentTooLess e) {
            System.out.println(e.getMessage());
        }
    }
}
