package EventManagementSystem;

public class CmdGroupQuit implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        GroupHandler groupHandler = GroupHandler.getInstance();
        EventAllocator eventAllocator = EventAllocator.getInstance();
        try {
            if (cmdParts.length != 4) {
                throw new ExWrongCommand();
            }
            try {
            	String gID = cmdParts[2];
            	if (gID.length() != 9 || gID.charAt(0) != 'g' || Integer.parseInt(gID.substring(1,8)) <0 || Integer.parseInt(gID.substring(1,8)) > 99999999) {
            		throw new ExInvalidGroupID();
            	}
            } 
            catch (NumberFormatException ex) {
            	throw new ExInvalidGroupID();
            }
            Group group = groupHandler.getGroup(cmdParts[2]);
            if (cmdParts[3].charAt(0) == 'e' && cmdParts[3].length() == 9) {
                Event event = eventAllocator.findEventByID(cmdParts[3]);
                if(event instanceof EventGroup) {
                    if(((EventGroup)event).foundGroup(group)) {
                        ((EventGroup)event).quitGroup(group);
                        System.out.println("Group" + cmdParts[2] + " has quited event " + cmdParts[3]);
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
            System.out.println("Wrong number format!");
        } catch (ExInvalidGroupQuitCommand | ExGroupNotFound | ExEventNotFound | ExInvalidGroupID e) {
            System.out.println(e.getMessage());
        } catch (ExWrongCommand e) {
			System.out.println(e.getMessage());
			System.out.println("Group quit command should be \"quit group gXXXXXXXXX\"");
    	} 
    }
}
