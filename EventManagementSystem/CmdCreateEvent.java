package EventManagementSystem;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CmdCreateEvent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (!(cmdParts.length == 7  || cmdParts.length == 10)) {
                throw new ExWrongCommand();
            }
            EventAllocator eventAllocator = EventAllocator.getInstance();
            
            String eName = cmdParts[2], eID = cmdParts[3], eMaj = cmdParts[6];
            int eCap = Integer.parseInt(cmdParts[4]);
            Day eDate = new Day(cmdParts[5]);
            if (eName.length() > 30)
            	throw new ExFirstNameTooLong();
            Event eventFound = null;
    		try {
    			eventFound = eventAllocator.findEventByID(eID);
    		} catch (ExEventNotFound e) {	//if event id not found then enter the catch block to add new event
	            if (eID.length() != 9 || eID.charAt(0) != 'e') {
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
					System.out.println("Created " + eName + " event with EventID: " + eID + ".");
    		} finally {
    			if (eventFound != null)
    				throw new ExInvalidEventID();
    		}
    		
            
        } catch (NumberFormatException e) {
            System.out.println("Wrong number format!");
        } catch (ExInvalidEventID | ExInvalidEventDate | ExInvalidEventCapacity | ExInvalidEventGroupCapacity |ExInvalidEventGroupSize | ExFirstNameTooLong e) {
            System.out.println(e.getMessage());
        } catch (ExWrongCommand e) {
			System.out.println(e.getMessage());
			System.out.println("Create event command should be \"create event eXXXXXXXXX\".");
    	} 
    }
}
