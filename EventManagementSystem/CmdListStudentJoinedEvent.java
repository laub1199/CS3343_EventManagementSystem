package EventManagementSystem;

public class CmdListStudentJoinedEvent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
    	try {
    		if(cmdParts.length != 4) {
    			throw new ExWrongCommand();
    		}
    		String studentID = null;
    		try {
    			studentID = cmdParts[2];
            	if (studentID.length() != 9 || studentID.charAt(0) != 's') {
            		throw new ExInvalidStudentID();
            	}
            	Integer.parseInt(studentID.substring(1,8));
            } 
            catch (NumberFormatException ex) {
            	throw new ExInvalidStudentID();
            }
    		
    		EventAllocator eventAllocator = EventAllocator.getInstance();
    		StudentHandler studentHandler = StudentHandler.getInstance();
    		//eventAllocator.findEventByStudent();
    		
    		System.out.println(studentHandler.getStudent(studentID).printString());
    		
    		if(cmdParts[3] == "all") {
    			System.out.println("All events: ");
				System.out.printf("%-12s|%s\n", "Event ID","Event Name");
    			for(Event event:eventAllocator.getEventList()) {
    				if(event.getStudentList().contains(studentHandler.getStudent(studentID))) {
    					System.out.printf("%-12s|%s\n", event.getEventID(), event.getEventName());
    				}
    			}
				// can use listEventFunction - laub
    		}else if(cmdParts[3] == "pending") {
    			System.out.println("Pending events: ");
				//need add header - laub
				System.out.printf("%-12s|%s\n", "Event ID","Event Name");
    			for(Event event:eventAllocator.getEventList()) {
    				if(event.getStudentList().contains(studentHandler.getStudent(cmdParts[2])) && event.getEventDate().after(SystemDate.getInstance())) {
    					System.out.printf("%-12s|%s\n", event.getEventID(), event.getEventName());
    				}
    			}
    		}else if(cmdParts[3] == "end") {
				//need add header - laub
    			System.out.println("End events: ");
				System.out.printf("%-12s|%s\n", "Event ID","Event Name");
    			for(Event event:eventAllocator.getEventList()) {
    				if(event.getStudentList().contains(studentHandler.getStudent(cmdParts[2])) && event.getEventDate().before(SystemDate.getInstance())) {
    					System.out.printf("%-12s|%s\n", event.getEventID(), event.getEventName());
    				}
    			}
    		}else {
    			throw new ExWrongCommand();
    		}
    	}catch(ExInvalidStudentID | ExStudentNotFound e) {
    		System.out.println(e.getMessage());
    	} catch (ExWrongCommand e) {
			System.out.println(e.getMessage());
			System.out.println("List student joined event command should be \"list studentJoinedEvent sXXXXXXXX all\" or \"list studentJoinedEvent sXXXXXXXX pending\" or \"list studentJoinedEvent sXXXXXXXX end\" ");
    	} 
    }
}
