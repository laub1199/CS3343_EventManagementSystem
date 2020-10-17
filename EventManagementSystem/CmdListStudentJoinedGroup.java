package EventManagementSystem;

public class CmdListStudentJoinedGroup implements Command {
    @Override
    public String execute(String[] cmdParts) throws CloneNotSupportedException {
    	String str = ""; 
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
    		
    		str = studentHandler.getStudent(studentID).printString();
    		str += "Joined Group:\n";
    		
    		for(Group group:groupHandler.getGroupList()) {
    			if(group.getStudentList().contains(studentHandler.getStudent(studentID))) {
    				str += group.toString();
    			}
    		}
    		
    		groupHandler.listGroupByStudentId(cmdParts[2]);
    	}catch(ExInvalidStudentID | ExStudentNotFound e) {
    		str = e.getMessage();
    	} catch (ExWrongCommand e) {
    		str = e.getMessage();
    		str += "List student joined group command should be \"list studentJoinedGroup sXXXXXXXX\"\n";
    	} 
    	return str;
    	
    }
}
