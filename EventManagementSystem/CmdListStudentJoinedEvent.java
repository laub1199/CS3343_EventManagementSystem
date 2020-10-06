package EventManagementSystem;

public class CmdListStudentJoinedEvent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
    	try {
    		if(cmdParts.length != 4) {
    			throw new ExWrongCommand();
    		}
    		
    		EventAllocator eventAllocator = EventAllocator.getInstance();
    		StudentHandler studentHandler = StudentHandler.getInstance();
    		//eventAllocator.findEventByStudent();
    		
    		if(cmdParts[3] == "all") {
    			System.out.println(studentHandler.getStudent(cmdParts[2]).printString());
    			System.out.println("All events: ");
    			for(Event event:eventAllocator.getEventList()) {
    				if(event.getStudentList().contains(studentHandler.getStudent(cmdParts[2]))) {
    					System.out.println(event.getEventID() + " | " + event.getEventName());
    				}
    			}
				// can use listEventFunction - laub
    		}else if(cmdParts[3] == "pending") {
    			System.out.println(studentHandler.getStudent(cmdParts[2]).printString());
    			System.out.println("Pending events: ");
				//need add header - laub
    			for(Event event:eventAllocator.getEventList()) {
    				if(event.getStudentList().contains(studentHandler.getStudent(cmdParts[2])) && event.getEventDate() > SystemDate.getInstance()) {
    					System.out.println(event.getEventID() + " | " + event.getEventName());
    				}
    			}
    		}else if(cmdParts[3] == "end") {
    			System.out.println(studentHandler.getStudent(cmdParts[2]).printString());
				//need add header - laub
    			System.out.println("End events: ");
    			for(Event event:eventAllocator.getEventList()) {
    				if(event.getStudentList().contains(studentHandler.getStudent(cmdParts[2])) && event.getEventDate() < SystemDate.getInstance()) {
    					System.out.println(event.getEventID() + " | " + event.getEventName());
    				}
    			}
    		}else {
    			throw new ExWrongCommand();
    		}
    	}catch(ExWrongCommand e) {
    		System.out.println(e.getMessage());
    	}catch(ExStudentNotFound e) {
    		System.out.println(e.getMessage());
    	}
    }
}
