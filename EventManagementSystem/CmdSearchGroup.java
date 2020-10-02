package EventManagementSystem;

public class CmdSearchGroup implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
    	
    	try {
    		if (cmdParts.length != 3) {
    			throw new ExWrongCommand();
    		}
    		
    		GroupHandler groupHandler = GroupHandler.getInstance();
    		Group group = groupHandler.getGroup(cmdParts[2]);
    		
    		EventAllocator eventAllocator = EventAllocator.getInstance();
    		Event event = eventAllocator.findEventByGroup(group);
    		
    		if (event != null) {
	        	System.out.println("GroupID\tNumber Of Students\tMaximum Number Of Students\tEventID\tEvent Name");
		        System.out.print(group.toString());
		        System.out.println(event.getEventID() + "\t" + event.getEventName());
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
    	}
    	
    }
}
