package EventManagementSystem;

public class CmdDeleteGroup implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (cmdParts.length != 3) {
                throw new ExWrongCommand();
            }
            GroupHandler instance = GroupHandler.getInstance();
            instance.deleteGroup(cmdParts[2]);
        } catch (ExWrongCommand | ExGroupNotFound e) {
            System.out.println(e.getMessage());
        }
    }
}
