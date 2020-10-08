package EventManagementSystem;

public class CmdDeleteGroup implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (cmdParts.length != 3) {
                throw new ExWrongCommand();
            }
            try {
            	String gID = cmdParts[2];
            	if (gID.length() != 9 || gID.charAt(0) != 'g') {
            		throw new ExInvalidGroupID();
            	}
            	Integer.parseInt(gID.substring(1,8));
            } 
            catch (NumberFormatException ex) {
            	throw new ExInvalidGroupID();
            }
            GroupHandler groupHandler = GroupHandler.getInstance();
            Group group = groupHandler.getGroup(cmdParts[2]);
            groupHandler.deleteGroup(group);
            System.out.println("Deleted group with GroupID: " + cmdParts[2] + ".");
        } catch (ExGroupNotFound | ExInvalidGroupID e) {
            System.out.println(e.getMessage());
        } catch (ExWrongCommand e) {
			System.out.println(e.getMessage());
			System.out.println("Delete group command should be \"delete group gXXXXXXXXX\"");
    	} 
    }
}
