package EventManagementSystem;

public class CmdCreateGroup implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (cmdParts.length != 4) {
                throw new ExWrongCommand();
            }
            GroupHandler instance = GroupHandler.getInstance();
            String groupId = cmdParts[2];
            int numOfStudent = Integer.parseInt(cmdParts[3]);
            
            if (groupId.length() != 9 || groupId.charAt(0) != 'g') {
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
	            
	            group = new Group(groupId, numOfStudent);
	            instance.createGroup(group);
            }
            finally {
            	if (group != null)
            		throw new ExGroupNotFound();
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Wrong number format!");
        } catch (ExGroupNotFound | ExWrongCommand | ExInvalidGroupID | ExGroupStudentTooLess e) {
            System.out.println(e.getMessage());
        } 
        
    }
}
