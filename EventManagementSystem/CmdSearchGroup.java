package EventManagementSystem;

public class CmdSearchGroup implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
    	
    	try {
    		GroupHandler groupHandler = GroupHandler.getInstance();
    		Group group = groupHandler.getGroup(cmdParts[2]);
    		
        	System.out.println("GroupID\tNumber Of Student\tEventID\tEvent Name");
	        System.out.println(group.toString());
    		
    	}
    	catch (ExGroupNotFound e) {
			System.out.println(e.getMessage());
    	}
    	
    }
}
