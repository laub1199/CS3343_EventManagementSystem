package EventManagementSystem;

import java.util.ArrayList;

public class CmdSearchEvent implements Command {
    @Override
    public String execute(String[] cmdParts) throws CloneNotSupportedException {
    	String str = ""; 
    	try {
    		if (cmdParts.length != 4 || !((cmdParts[2].equals("id") && cmdParts[3].charAt(0) == 'e') ||cmdParts[2].equals("major"))) {
    			throw new ExWrongCommand();
    		}
    		if (cmdParts[2].equals("id")) {
    			try {
                	String eID = cmdParts[3];
                	if (eID.length() != 9 || Integer.parseInt(eID.substring(1,8)) <0 || Integer.parseInt(eID.substring(1,8)) > 99999999) {
                		throw new ExInvalidEventID();
                	}
                } 
                catch (NumberFormatException ex) {
                	throw new ExInvalidEventID();
                }
    		}
    		
    		//search event by id
	    	EventAllocator eventAllocator = EventAllocator.getInstance();
    		if (cmdParts[2].equals("id")) {
		    	Event event = eventAllocator.findEventByID(cmdParts[3]);
		    	str += String.format("|%-10s|%-30s|%-12s|%-8s|%-30s|%-5s|%-10s|%-15s|%-11s|%-16s|%-16s|\n",
	    				"Event ID","Event Name","Date","Capacity","Major","Quota","Type","Group Capacity","Group Quota","Min No. In Group","Max No. In Group");
				if (event instanceof EventIndividual) {
					str += ((EventIndividual)event).printDetail();
				}
				else if ((event instanceof EventGroup)) {
					str += ((EventGroup)event).printDetail();
				}
    		}
    		
    		//search event by major
    		else if (cmdParts[2].equals("major")){	
    			Major major = Major.getMajor(cmdParts[3]);
		    	ArrayList<Event> foundEventList = eventAllocator.findEventByMajor(major);
		    	if (foundEventList.size() == 0) {
		    		throw new ExEventNotFound();
		    	}
		    	str += String.format("|%-10s|%-30s|%-12s|%-8s|%-30s|%-5s|%-10s|%-15s|%-11s|%-16s|%-16s|\n",
	    				"Event ID","Event Name","Date","Capacity","Major","Quota","Type","Group Capacity","Group Quota","Min No. In Group","Max No. In Group");
		    	for (Event e:foundEventList) {
		    		if (e instanceof EventIndividual) {
		    			str += ((EventIndividual)e).printDetail();
					}
					else if ((e instanceof EventGroup)) {
						str += ((EventGroup)e).printDetail();
					}
		    	}
    		}
    	}
    	catch (ExEventNotFound | ExInvalidEventID | ExMajorNotFound e){
    		str = e.getMessage();
    	}
    	catch (ExWrongCommand e){
    		str = e.getMessage();
    		str += "Search event command should be \"search event id eXXXXXXXXX\" or \"search event major XXXXXXXXX\"\n";
    	}
		return str;

    }
}
