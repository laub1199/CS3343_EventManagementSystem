package EventManagementSystem;

public class CmdListStudentJoinedGroup implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
    	try {
    		if(cmdParts.length != 3 || !(cmdParts[2] instanceof String)) {
    			throw new ExWrongCommand();
    		}
    		
    		GroupHandler groupHandler = GroupHandler.getInstance();
    		StudentHandler studentHandler = StudentHandler.getInstance();
    		
    		System.out.println(studentHandler.getStudent(cmdParts[2]).printString());
    		System.out.println("Joined Group: ");
    		
    		for(Group group:groupHandler.getGroupList()) {
    			if(group.getStudentList().contains(studentHandler.getStudent(cmdParts[2]))) {
    				System.out.println(group.toString());
    			}
    		}
    		
    		groupHandler.listGroupByStudentId(cmdParts[2]);
    	}catch(ExWrongCommand e) {
    		System.out.println(e.getMessage());
    	}catch(ExStudentNotFound e) {
    		System.out.println(e.getMessage());
    	}
    }
}
