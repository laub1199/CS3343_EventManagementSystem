package EventManagementSystem;

public class CmdSearchGroup implements Command {
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
    		
    		EventAllocator eventAllocator = EventAllocator.getInstance();
    		Event event = eventAllocator.findEventByGroup(group);
    		
    		if (event != null) {
    			str += String.format("|%-10s|%-18s|%-23s|%-10s|%-30s|\n","GroupID","Number Of Students","Max Number Of Students","EventID","Event Name");
	        	//System.out.println("GroupID\tNumber Of Students\tMaximum Number Of Students\tEventID\tEvent Name");
    			str += group.toString();
    			str += String.format("%-10s|%-30s|\n",event.getEventID() ,event.getEventName());
		        //System.out.println(event.getEventID() + "\t" + event.getEventName());
    		}
    		else {
    			str += String.format("|%-10s|%-18s|%-23s|\n","GroupID", "Number Of Student", "Max Number Of Student");
    			str += group.toString();
    		}
    		
    		str += ("Students in group:\n");
    		str += String.format("|%-9s|%-20s|%-20s|%-3s|%-30s|%-3s|\n", "StudentID", "First Name", "Last Name", "Sex", "Major", "Age");
    		str += group.listStudentInGroup();
    	}
    	catch (ExGroupNotFound | ExInvalidGroupID e) {
    		str = e.getMessage();
    	} 
    	catch (ExWrongCommand e) {
    		str = e.getMessage();
    		str += "Search group command should be \"search group gXXXXXXXXX\"\n";
		}
    	finally {
    		return str;
    	}
    }
}
