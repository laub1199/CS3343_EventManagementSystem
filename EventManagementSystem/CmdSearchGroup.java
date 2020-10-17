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
    			str += String.format("|%-10s|%-18s|%-23s|",group.getGroupID(), group.getNumOfStudent(), group.getMaxNumOfStudent());
    			str += String.format("%-10s|%-30s|\n",event.getEventID() ,event.getEventName());
    		}
    		else {
    			str += String.format("|%-10s|%-18s|%-23s|\n","GroupID", "Number Of Students", "Max Number Of Students");
    			str += group.toString();
    		}
    		
    		if (group.getNumOfStudent() > 0) {
	    		str += ("Students in group:\n");
	    		str += String.format("|%-9s|%-20s|%-20s|%-3s|%-30s|%-3s|\n", "StudentID", "First Name", "Last Name", "Sex", "Major", "Age");
	    		str += group.listStudentInGroup();
    		}
    		return str;
    	}
    	catch (ExGroupNotFound | ExInvalidGroupID e) {
    		str = e.getMessage();
    		return str;
    	} 
    	catch (ExWrongCommand e) {
    		str = e.getMessage();
    		str += "Search group command should be \"search group gXXXXXXXXX\"\n";
    		return str;
		}
    }
}
