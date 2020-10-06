package EventManagementSystem;
public class CmdGroupJoin implements Command{
	// represent a group to join a event
	@Override
    public void execute(String[] cmdParts) {
		//command: groupJoin event (group id) (event id)
		String groupID = cmdParts[2];
		String eventID = cmdParts[3];
		boolean group exist;
		boolean event exist;
		
		//if the group not exist, user cant join the event
		try {
    		GroupHandler groupHandler = GroupHandler.getInstance();
    		Group group = groupHandler.getGroup(groupID);
    		EventAllocator eventAllocator = EventAllocator.getInstance();
			eventAllocator.findEventByGroupID(group);
			Event event = eventAllocator.findEventByID(eventID);
			groupJoinEvent(group,event);
    	}
    	catch (ExGroupNotFound e) {
			System.out.println(e.getMessage());
    	}
		
		//if the event not exist, group cant join the event
		catch(ExEventNotFound e) {
			System.out.println(e.getMessage());
		}
			//if already joined one event, cant join
		catch(ExEventNotFound e) {
			System.out.println(e.getMessage());
		}
	}
	
}
