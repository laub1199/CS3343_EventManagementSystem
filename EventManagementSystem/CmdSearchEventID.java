package EventManagementSystem;

public class CmdSearchEventID implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
    	
    	try {
	    	EventAllocator eventAllocator = EventAllocator.getInstance();
	    	Event event = eventAllocator.findEventByID(cmdParts[2]);
	    	
			System.out.println("Event ID\tEvent name\tDate\tCapacity\tMajor\tQuota\tType\tGroup capacity\tGroup Quota\tMin no. in group\tMax no. in group");
			if (event instanceof EventIndividual) {
				((EventIndividual)event).printDetail();
			}
			else if ((event instanceof EventGroup)) {
				((EventGroup)event).printDetail();
			}
    	}
    	catch (ExEventNotFound e){
			System.out.println(e.getMessage());
    	}
    	
    }
}
