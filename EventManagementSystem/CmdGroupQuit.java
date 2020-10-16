package EventManagementSystem;

public class CmdGroupQuit implements Command {
    @Override
    public String execute(String[] cmdParts) throws CloneNotSupportedException {
    	String str = ""; 
        GroupHandler groupHandler = GroupHandler.getInstance();
        EventAllocator eventAllocator = EventAllocator.getInstance();
        try {
            if (cmdParts.length != 3 || cmdParts[1].charAt(0) != 'g') {
                throw new ExWrongCommand();
            }
            try {
            	String gID = cmdParts[1];
            	if (gID.length() != 9 || Integer.parseInt(gID.substring(1,8)) <0 || Integer.parseInt(gID.substring(1,8)) > 99999999) {
            		throw new ExInvalidGroupID();
            	}
            } 
            catch (NumberFormatException ex) {
            	throw new ExInvalidGroupID();
            }
            Group group = groupHandler.getGroup(cmdParts[1]);
            if (cmdParts[2].charAt(0) == 'e' && cmdParts[2].length() == 9) {
                Event event = eventAllocator.findEventByID(cmdParts[2]);
                if(event instanceof EventGroup) {
                    if(((EventGroup)event).foundGroup(group)) {
                        ((EventGroup)event).quitGroup(group);
                        str += "Group " + cmdParts[1] + " has quited event " + cmdParts[2] + "\n";
                    }
                    else {
                        throw new ExGroupNotFound();
                    }
                }
                else  {
                    throw new ExInvalidGroupQuitCommand();
                }
            }
            else {
                throw new ExInvalidGroupQuitCommand();
            }
        } catch (NumberFormatException e) {
        	str = "Wrong number format!\n";
        } catch (ExInvalidGroupQuitCommand | ExGroupNotFound | ExEventNotFound | ExInvalidGroupID e) {
        	str = e.getMessage();
        } catch (ExWrongCommand e) {
        	str = e.getMessage();
        	str += "Group quit command should be \"groupQuit gXXXXXXXXX eXXXXXXXX\"\n";
    	} 
        finally {
        	return str;
        }
    }
}
