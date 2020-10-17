package EventManagementSystem;

public class CmdDeleteGroup implements Command {
    @Override
    public String execute(String[] cmdParts) throws CloneNotSupportedException {
    	String str = ""; 
        try {
            if (cmdParts.length != 3 || cmdParts[2].charAt(0) != 'g') {
                throw new ExWrongCommand();
            }
            try {
            	String gID = cmdParts[2];
            	if (gID.length() != 9 || Integer.parseInt(gID.substring(1,8)) <0 || Integer.parseInt(gID.substring(1,8)) > 99999999) {
            		throw new ExInvalidGroupID();
            	}
            } 
            catch (NumberFormatException ex) {
            	throw new ExInvalidGroupID();
            }
            GroupHandler groupHandler = GroupHandler.getInstance();
            Group group = groupHandler.getGroup(cmdParts[2]);
            groupHandler.deleteGroup(group);
            str = "Deleted group with GroupID: " + cmdParts[2] + ".";
        } catch (ExGroupNotFound | ExInvalidGroupID e) {
        	str = e.getMessage();
        } catch (ExWrongCommand e) {
        	str = e.getMessage();
        	str += "Delete group command should be \"delete group gXXXXXXXXX\"\n";
    	} 
        return str;
        
    }
}
