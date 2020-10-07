package EventManagementSystem;

public class CmdSearchGroup implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
    	
    	try {
    		if (cmdParts.length != 3 || cmdParts[2].charAt(0) != 'g' || cmdParts[2].length() != 9) {
    			throw new ExWrongCommand();
    		}
    		
    		GroupHandler groupHandler = GroupHandler.getInstance();
    		Group group = groupHandler.getGroup(cmdParts[2]);
    		
    		EventAllocator eventAllocator = EventAllocator.getInstance();
    		Event event = eventAllocator.findEventByGroup(group);
    		
    		if (event != null) {
    			System.out.printf("|%-10s|%-18s|%-23s|%-10s|%-25s|","GroupID","Number Of Students","Max. Number Of Students","EventID","Event Name");
	        	//System.out.println("GroupID\tNumber Of Students\tMaximum Number Of Students\tEventID\tEvent Name");
		        System.out.print(group.toString());
		        System.out.printf("%-10s|%-25s|\n",event.getEventID() ,event.getEventName());
		        //System.out.println(event.getEventID() + "\t" + event.getEventName());
    		}
    		else {
    			System.out.println("GroupID\tNumber Of Students\tMaximum Number Of Students");
    			System.out.println(group.toString());
    		}
    		
        	System.out.println("Students in group:");
        	System.out.printf("|%s|%-20s|%-20s|%s|%-10s|%s|\n", "StudentID", "First Name", "Last Name", "Sex", "Major", "Age");
        	group.listStudentInGroup();
    		
    	}
    	catch (ExGroupNotFound e) {
			System.out.println(e.getMessage());
    	} 
    	catch (ExWrongCommand e) {
			System.out.println(e.getMessage());
			System.out.println("Search group command should be \"search group gXXXXXXXXX\"");
    	} 
    	catch (ExEventNotFound e) {
			System.out.println("This group did not join event yet.");
		}
    	
    }
}
