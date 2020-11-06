package EventManagementSystem;

public class CmdListStudentJoinedEvent implements Command {
    @Override
    public String execute(String[] cmdParts) throws CloneNotSupportedException {
    	String str = ""; 
    	try {
    		if(cmdParts.length != 4 || cmdParts[2].charAt(0) != 's') {
    			throw new ExWrongCommand();
    		}
    		String studentID = null;
    		
    		studentID = cmdParts[2];
        	if (!Student.checkStudentID(studentID)) {
        		throw new ExInvalidStudentID();
        	}
            
    		EventAllocator eventAllocator = EventAllocator.getInstance();
    		StudentHandler studentHandler = StudentHandler.getInstance();
    		//eventAllocator.findEventByStudent();
    		
    		System.out.println(studentHandler.getStudent(studentID).printString());
    		
    		if(cmdParts[3].equals("all")) {
    			str = "All events:\n";
    			str += String.format("|%-12s|%-30s|\n", "Event ID","Event Name");
    			for(Event event:eventAllocator.getEventList()) {
    				if(event.getStudentList().contains(studentHandler.getStudent(studentID))) {
    					str += String.format("|%-12s|%-30s|\n", event.getEventID(), event.getEventName());
    				}
    			}
				// can use listEventFunction - laub
    		}else if(cmdParts[3].equals("pending")) {
    			str = "Pending events:\n";
				//need add header - laub
    			str += String.format("|%-12s|%-30s|\n", "Event ID","Event Name");
    			for(Event event:eventAllocator.getEventList()) {
    				if(event.getStudentList().contains(studentHandler.getStudent(cmdParts[2])) && event.getEventDate().compareTo(SystemDate.getInstance()) > 0) {
    					str += String.format("|%-12s|%-30s|\n", event.getEventID(), event.getEventName());
    				}
    			}
    		}else if(cmdParts[3].equals("end")) {
				//need add header - laub
    			str = "End events:\n";
    			str += String.format("|%-12s|%-30s|\n", "Event ID","Event Name");
    			for(Event event:eventAllocator.getEventList()) {
    				if(event.getStudentList().contains(studentHandler.getStudent(cmdParts[2])) && event.getEventDate().compareTo(SystemDate.getInstance()) < 0) {
    					str += String.format("|%-12s|%-30s|\n", event.getEventID(), event.getEventName());
    				}
    			}
    		}else {
    			throw new ExWrongCommand();
    		}
    	}catch(ExInvalidStudentID | ExStudentNotFound e) {
    		str = e.getMessage();
    	} catch (ExWrongCommand e) {
    		str = e.getMessage();
    		str += "List student joined event command should be \"list studentJoinedEvent sXXXXXXXX all\" or \"list studentJoinedEvent sXXXXXXXX pending\" or \"list studentJoinedEvent sXXXXXXXX end\"\n";
    	} 
    	return str;
    	
    }
}
