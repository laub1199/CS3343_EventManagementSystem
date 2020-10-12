package EventManagementSystem;

public class CmdListStudentJoinedGroup implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
    	try {
    		if(cmdParts.length != 3 || cmdParts[2].charAt(0) != 's') {
    			throw new ExWrongCommand();
    		}
    		String studentID = null;
    		try {
    			studentID = cmdParts[2];
            	if (studentID.length() != 9 || Integer.parseInt(studentID.substring(1,8)) <0 || Integer.parseInt(studentID.substring(1,8)) > 99999999) {
            		throw new ExInvalidStudentID();
            	}
            } 
            catch (NumberFormatException ex) {
            	throw new ExInvalidStudentID();
            }
    		GroupHandler groupHandler = GroupHandler.getInstance();
    		StudentHandler studentHandler = StudentHandler.getInstance();
    		
    		System.out.println(studentHandler.getStudent(studentID).printString());
    		System.out.println("Joined Group: ");
    		
    		for(Group group:groupHandler.getGroupList()) {
    			if(group.getStudentList().contains(studentHandler.getStudent(studentID))) {
    				System.out.println(group.toString());
    			}
    		}
    		
    		groupHandler.listGroupByStudentId(cmdParts[2]);
    	}catch(ExInvalidStudentID | ExStudentNotFound e) {
    		System.out.println(e.getMessage());
    	} catch (ExWrongCommand e) {
			System.out.println(e.getMessage());
			System.out.println("List student joined group command should be \"list studentJoinedGroup sXXXXXXXX\"");
    	} 
    }
}
