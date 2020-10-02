package EventManagementSystem;

public class CmdSearchEventMajor implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
    	
    	try {
	    	EventAllocator eventAllocator = EventAllocator.getInstance();
	    	String major = cmdParts[2];
	    	boolean foundEvent = eventAllocator.findEventByMajor(major);
	    	if (foundEvent) {
				System.out.println("Event ID\tEvent name\tDate\tCapacity\tMajor\tQuota\tType\tGroup capacity\tGroup Quota\tMin no. in group\tMax no. in group");
				for (Event e:eventAllocator.getEventList()) {
					if (e.getMajor().equals(major)) {
						if (e instanceof EventIndividual) {
							((EventIndividual)e).printDetail();
						}
						else if ((e instanceof EventGroup)) {
							((EventGroup)e).printDetail();
						}
						foundEvent = true;
					}
				}
	    	}
    	}
    	catch (ExEventNotFound e){
			System.out.println(e.getMessage());
    	}
    	
    }
}
