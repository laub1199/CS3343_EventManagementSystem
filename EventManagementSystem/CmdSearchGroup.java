package EventManagementSystem;

public class CmdSearchGroup implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
    	
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
    		
    		EventAllocator eventAllocator = EventAllocator.getInstance();
    		Event event = eventAllocator.findEventByGroup(group);
    		
    		if (event != null) {
    			System.out.printf("|%-10s|%-18s|%-23s|%-10s|%-30s|","GroupID","Number Of Students","Max. Number Of Students","EventID","Event Name");
	        	//System.out.println("GroupID\tNumber Of Students\tMaximum Number Of Students\tEventID\tEvent Name");
		        System.out.print(group.toString());
		        System.out.printf("%-10s|%-30s|\n",event.getEventID() ,event.getEventName());
		        //System.out.println(event.getEventID() + "\t" + event.getEventName());
    		}
    		else {
    			System.out.println("GroupID\tNumber Of Students\tMaximum Number Of Students");
    			System.out.println(group.toString());
    		}
    		
        	System.out.println("Students in group:");
	        System.out.printf("|%-9s|%-20s|%-20s|%-3s|%-30s|%-3s|\n", "StudentID", "First Name", "Last Name", "Sex", "Major", "Age");
        	group.listStudentInGroup();
    		
    	}
    	catch (ExGroupNotFound | ExInvalidGroupID e) {
			System.out.println(e.getMessage());
    	} 
    	catch (ExWrongCommand e) {
			System.out.println(e.getMessage());
			System.out.println("Search group command should be \"search group gXXXXXXXXX\"");
		}
    }
}
