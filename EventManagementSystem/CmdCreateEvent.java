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
            
            EventAllocator instance = EventAllocator.getInstance();
            
            String eName = cmdParts[2], eID = cmdParts[3], eMaj = cmdParts[6];
            int eCap = Integer.parseInt(cmdParts[4]);
            Date eDate = new SimpleDateFormat("dd/MM/yyyy").parse(cmdParts[5]);

            Event eventFound = null;
    		try {
    			eventFound = instance.findEventByID(eID);
    		} catch (ExEventNotFound e) {	//if event id not found then enter the catch block to add new event
	            if (eID.length() != 9 || eID.charAt(0) != 'e') {
					throw new ExInvalidEventID();
				}
				if (eDate.before(SystemDate.getInstance())) {
					throw new ExInvalidEventDate();
				}
				System.out.println(eCap < 1);
				if (eCap < 1) {
					throw new ExInvalidEventCapacity();
				}
				
				Event event;
				if (cmdParts.length == 7) {
					event = new EventIndividual(eName, eID, eCap, eDate, eMaj);
				}
				else {
					int gpCap = Integer.parseInt(cmdParts[7]), gpMin = Integer.parseInt(cmdParts[8]), gpMax = Integer.parseInt(cmdParts[9]);
					if (gpCap < 1 || gpMin < 1 || gpMax < 1 || gpMax < gpMin || eCap <= gpCap) {
						throw new ExInvalidEventCapacity();
					}
					event = new EventGroup(eName, eID, eCap, eDate, eMaj, gpCap, gpMin, gpMax);
				}
					instance.addEvent(event);
    		} finally {
    			if (eventFound != null)
    				throw new ExInvalidEventID();
    		}
            
        } catch (NumberFormatException e) {
            System.out.println("Wrong number format!");
        } catch (ExWrongCommand | ExInvalidEventID | ExInvalidEventDate | ExInvalidEventCapacity e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("Wrong date format!");
        }
    }
}
