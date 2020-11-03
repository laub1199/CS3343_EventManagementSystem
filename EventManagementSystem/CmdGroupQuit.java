package EventManagementSystem;

public class CmdGroupQuit implements Command {
    @Override
    public String execute(String[] cmdParts) throws CloneNotSupportedException {
    	String str = ""; 
        GroupHandler groupHandler = GroupHandler.getInstance();
        EventAllocator eventAllocator = EventAllocator.getInstance();
        try {
            if (cmdParts.length != 3 || cmdParts[1].charAt(0) != 'g' || cmdParts[2].charAt(0) != 'e') {
                throw new ExWrongCommand();
            }
            
            String gID = cmdParts[1];
            String eID = cmdParts[2];
          
            if (!Group.checkGroupID(gID)) {
        		throw new ExInvalidGroupID();
        	}
        	if (!Event.checkEventID(eID)) {
			    throw new ExInvalidEventID();
        	}
            Group group = groupHandler.getGroup(gID);
            Event event = eventAllocator.findEventByID(eID);
            if(event instanceof EventGroup) {
                if(((EventGroup)event).foundGroup(group)) {
                    ((EventGroup)event).quitGroup(group);
                    str += "Group " + gID + " has quited event " + eID + "\n";
                }
                else {
                    throw new ExGroupNotFound();
                }
            }
            else  {
                throw new ExInvalidGroupQuitIndividualEvent();
            }
            
        } catch (ExGroupNotFound | ExEventNotFound | ExInvalidGroupID | ExInvalidEventID | ExInvalidGroupQuitIndividualEvent e) {
        	str = e.getMessage();
        } catch (ExWrongCommand e) {
        	str = e.getMessage();
        	str += "Group quit command should be \"groupQuit gXXXXXXXXX eXXXXXXXX\"\n";
    	}
        return str;
        
    }
}
