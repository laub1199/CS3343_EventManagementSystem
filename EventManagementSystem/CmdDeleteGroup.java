package EventManagementSystem;

public class CmdDeleteGroup implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (cmdParts.length != 3) {
                throw new ExWrongCommand();
            }
            GroupHandler groupHandler = GroupHandler.getInstance();
            Group group = groupHandler.getGroup(cmdParts[2]);
            groupHandler.deleteGroup(group);
        } catch (ExWrongCommand | ExGroupNotFound e) {
            System.out.println(e.getMessage());
        }
    }
}
