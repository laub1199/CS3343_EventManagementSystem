package EventManagementSystem;

public class CmdCreateEvent implements Command {
    @Override
    public String execute(String[] cmdParts) throws CloneNotSupportedException {
    	String str = "";    	
        try {
            if (!(cmdParts.length == 7  || cmdParts.length == 10) || cmdParts[3].charAt(0) != 'e') {
                throw new ExWrongCommand();
            }
            EventAllocator eventAllocator = EventAllocator.getInstance();
            
            String eName = cmdParts[2], eID = cmdParts[3];
            int eCap = Integer.parseInt(cmdParts[4]);
            Major eMaj = Major.getMajor(cmdParts[6]);
            Day eDate = new Day(cmdParts[5]);
            if (eName.length() > 30)
            	throw new ExEventNameTooLong();
            Event eventFound = null;
    		try {
    			eventFound = eventAllocator.findEventByID(eID);
    		} catch (ExEventNotFound e) {	//if event id not found then enter the catch block to add new event
	            try {	//this try... catch... block is checking event id format
	    			if (eID.length() != 9 || Integer.parseInt(eID.substring(1,8)) <0 || Integer.parseInt(eID.substring(1,8)) > 99999999) {
						throw new ExInvalidEventID();
					}
	            }
	            catch (NumberFormatException ex) {
	            	throw new ExInvalidEventID();
	            }
	            
				if (eDate.compareTo(SystemDate.getInstance()) < 0) {
					throw new ExInvalidEventDate();
				}
				if (eCap < 1) {
					throw new ExInvalidEventCapacity();
				}
				
				Event event;
				if (cmdParts.length == 7) {
					event = new EventIndividual(eName, eID, eCap, eDate, eMaj);
				}
				else {
					int gpCap = Integer.parseInt(cmdParts[7]), gpMin = Integer.parseInt(cmdParts[8]), gpMax = Integer.parseInt(cmdParts[9]);
					if (gpMin < 1 || gpMax < 1 || gpMax < gpMin || eCap < gpMin * gpCap ) {
						throw new ExInvalidEventGroupSize();
					}
					if (eCap <= gpCap || gpCap < 1) {
						throw new ExInvalidEventGroupCapacity();
					}
					
					event = new EventGroup(eName, eID, eCap, eDate, eMaj, gpCap, gpMin, gpMax);
				}
					eventAllocator.addEvent(event);
					str = "Created " + eName + " event with EventID: " + eID + ".";
    		} finally {
    			if (eventFound != null)
    				throw new ExInvalidEventID();
    		}
    		
            
        } catch (NumberFormatException e) {
        	str = "Wrong number format!";
        } catch (ExInvalidEventID | ExInvalidEventDate | ExInvalidEventCapacity | ExInvalidEventGroupCapacity |
				ExInvalidEventGroupSize | ExEventNameTooLong | ExDateFormatDay | ExDateFormatMonth | ExMajorNotFound|
				ExDateFormatYear | ExInvalidDate e) {
        	str = e.getMessage();
        } catch (ExWrongCommand e) {
        	str = e.getMessage();
        	str += "\nThere are two ways for create event command.";
        	str += "Create individual event: \"create event <name> eXXXXXXXXX <capacity> <dd-mmm-yyyy> <major>\".";
        	str += "Create group event: \"create event <name> eXXXXXXXXX <capacity> <dd-mmm-yyyy> <major> <group capacity> <min no. in one group> <max no. in one group>\".\n";
        } 
        return str;
    }
}
