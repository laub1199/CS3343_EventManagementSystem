package EventManagementSystem;

import java.util.ArrayList;

public class CmdSearchEvent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
    	try {
    		if (cmdParts.length != 4 || !(cmdParts[2].equals("id")||cmdParts[2].equals("major"))) {
    			throw new ExWrongCommand();
    		}
    		if (cmdParts[2].equals("id")) {
    			try {
                	String eID = cmdParts[3];
                	if (eID.length() != 9 || eID.charAt(0) != 'e' || Integer.parseInt(eID.substring(1,8)) <0 || Integer.parseInt(eID.substring(1,8)) > 99999999) {
                		throw new ExInvalidEventID();
                	}
                } 
                catch (NumberFormatException ex) {
                	throw new ExInvalidEventID();
                }
    		}
    		
            System.out.printf("|%-10s|%-25s|%-12s|%-8s|%-6s|%-5s|%-10s|%-15s|%-11s|%-16s|%-16s|\n",
    				"Event ID","Event Name","Date","Capacity","Major","Quota","Type","Group Capacity","Group Quota","Min No. In Group","Max No. In Group");
    		
    		//search event by id
	    	EventAllocator eventAllocator = EventAllocator.getInstance();
    		if (cmdParts[2].equals("id")) {
		    	Event event = eventAllocator.findEventByID(cmdParts[3]);
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
		    	ArrayList<Event> foundEventList = eventAllocator.findEventByMajor(major);
		    	if (foundEventList.size() == 0) {
		    		throw new ExEventNotFound();
		    	}
		    	
		    	for (Event e:foundEventList) {
		    		if (e instanceof EventIndividual) {
						((EventIndividual)e).printDetail();
					}
					else if ((e instanceof EventGroup)) {
						((EventGroup)e).printDetail();
					}
		    	}
    		}
    		
    		
    	}
    	catch (ExEventNotFound | ExInvalidEventID e){
			System.out.println(e.getMessage());
    	}
    	catch (ExWrongCommand e){
			System.out.println(e.getMessage());
			System.out.println("Search event command should be \"search event id eXXXXXXXXX\" or \"search event major XXXXXXXXX\"");
    	}
    	
    }
}
