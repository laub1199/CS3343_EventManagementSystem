package EventManagementSystem;

public class CmdSearchEvent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
    	try {
    		if (cmdParts.length != 4 || !(cmdParts[2].equals("id")||cmdParts[2].equals("major"))) {
    			throw new ExWrongCommand();
    		}
    		
    		//search event by id
	    	EventAllocator eventAllocator = EventAllocator.getInstance();
    		if (cmdParts[2].equals("id")) {
		    	Event event = eventAllocator.findEventByID(cmdParts[3]);
				System.out.println("Event ID\tEvent name\tDate\tCapacity\tMajor\tQuota\tType\tGroup capacity\tGroup Quota\tMin no. in group\tMax no. in group");
				if (event instanceof EventIndividual) {
					((EventIndividual)event).printDetail();
				}
				else if ((event instanceof EventGroup)) {
					((EventGroup)event).printDetail();
				}
    		}
    		
    		//search event by major
    		else if (cmdParts[2].equals("major")){	
		    	String major = cmdParts[3];
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
    		
    	}
    	catch (ExEventNotFound e){
			System.out.println(e.getMessage());
    	}
    	catch (ExWrongCommand e){
			System.out.println(e.getMessage());
    	}
    	
    }
}
