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
            if (cmdParts[2].charAt(0) != 'g' || cmdParts[2].length() != 9) {
                throw new ExInvalidGroupQuitCommand();
            }
            Group group = groupHandler.getGroup(cmdParts[2]);
            if (cmdParts[3].charAt(0) == 'e' && cmdParts[3].length() == 9) {
                Event event = eventAllocator.findEventByID(cmdParts[3]);
                if(event instanceof EventGroup) {
                    if(((EventGroup)event).foundGroup(group)) {
                        ((EventGroup)event).quitGroup(group);
                        System.out.println("Group" + cmdParts[2] + " has quited event " + cmdParts[3]);
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
        } catch (ExWrongCommand | ExInvalidGroupQuitCommand | ExGroupNotFound | ExEventNotFound e) {
            System.out.println(e.getMessage());
        }
    }
}
