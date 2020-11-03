package EventManagementSystem;

public class CmdCreateGroup implements Command {
    @Override
    public String execute(String[] cmdParts) throws CloneNotSupportedException {
    	String str = ""; 
        try {
            if (cmdParts.length != 4 || cmdParts[2].charAt(0) != 'g') {
                throw new ExWrongCommand();
            }
            GroupHandler instance = GroupHandler.getInstance();
            String groupID = null;
            int numOfStudent = Integer.parseInt(cmdParts[3]);
            groupID = cmdParts[2];
        	if (!Group.checkGroupID(groupID)) {
        		throw new ExInvalidGroupID();
        	}
            Group group = null;
            try {
            	group = instance.getGroup(groupID);
            }
            catch (ExGroupNotFound e) {
	            
	            if (numOfStudent <= 1) {
	                throw new ExGroupStudentTooLess();
	            }
	            instance.createGroup(new Group(groupID, numOfStudent));
	            str = "Created group with GroupID: " + groupID + ".";
            }
            finally {
            	if (group != null)
            		throw new ExInvalidGroupID();
            }
            
        } catch (NumberFormatException e) {
        	str = "Wrong number format!\n";
        } catch (ExInvalidGroupID | ExGroupStudentTooLess e) {
        	str = e.getMessage();
        } catch (ExWrongCommand e) {
        	str = e.getMessage();
        	str += "Create group command should be \"create group gXXXXXXXXX <max no. in one group>\".\n";
    	} 
        return str;
        
    }
}
