package EventManagementSystem;

public class CmdCreateGroup implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (cmdParts.length != 4 || cmdParts[2].charAt(0) != 'g') {
                throw new ExWrongCommand();
            }
            GroupHandler instance = GroupHandler.getInstance();
            String groupId = null;
            int numOfStudent = Integer.parseInt(cmdParts[3]);
            try {
            	groupId = cmdParts[2];
            	if (groupId.length() != 9 || Integer.parseInt(groupId.substring(1,8)) < 0 || Integer.parseInt(groupId.substring(1,8)) > 99999999) {
            		throw new ExInvalidGroupID();
            	}
            } 
            catch (NumberFormatException ex) {
            	throw new ExInvalidGroupID();
            }
            Group group = null;
            try {
            	group = instance.getGroup(groupId);
            }
            catch (ExGroupNotFound e) {
	            
	            if (numOfStudent <= 1) {
	                throw new ExGroupStudentTooLess();
	            }
	            instance.createGroup(new Group(groupId, numOfStudent));
	            System.out.println("Created group with GroupID: " + groupId + ".");
            }
            finally {
            	if (group != null)
            		throw new ExInvalidGroupID();
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Wrong number format!");
        } catch (ExInvalidGroupID | ExGroupStudentTooLess e) {
            System.out.println(e.getMessage());
        } catch (ExWrongCommand e) {
			System.out.println(e.getMessage());
			System.out.println("Create group command should be \"create group gXXXXXXXXX <max no. in one group>\".");
    	} 
        
    }
}
