package EventManagementSystem;

public class CmdGroupJoin implements Command {
	// represent a group to join a event
	@Override
    public String execute(String[] cmdParts) {
    	String str = ""; 
		//command: groupJoin event (group id) (event id)
		try {
	    	if (cmdParts.length != 4 || cmdParts[2].charAt(0) != 'g' || cmdParts[3].charAt(0) != 'e') {
	    		throw new ExWrongCommand();
	    	}
			String groupID = cmdParts[2];
			String eventID = cmdParts[3];
			
			 try {
	    		  if (eventID.length() != 9 || Integer.parseInt(eventID.substring(1,8)) <0 || Integer.parseInt(eventID.substring(1,8)) > 99999999) {
					 throw new ExInvalidEventID();
				 }
	         }
	         catch (NumberFormatException ex) {
	           	 throw new ExInvalidEventID();
	         }
			 
			 try {
	    		  if (groupID.length() != 9 || Integer.parseInt(groupID.substring(1,8)) <0 || Integer.parseInt(groupID.substring(1,8)) > 99999999) {
					 throw new ExInvalidGroupID();
				 }
	         }
	         catch (NumberFormatException ex) {
	           	 throw new ExInvalidGroupID();
	         }
		
		//if the group not exist, user cant join the event
		
    		GroupHandler groupHandler = GroupHandler.getInstance();
    		Group groupJoin = groupHandler.getGroup(groupID);
    		    		
    		EventAllocator eventAllocator = EventAllocator.getInstance();
    		Event groupEvent = eventAllocator.findEventByID(eventID);
    		boolean groupExist;
    		
    		//does the event allow group join
    		if(groupEvent instanceof EventGroup==false){
    			throw new ExNotGroupEvent();
    		}
    			
			//does the event full already
			if(groupEvent.isFull()==true){
				throw new ExEventGroupIsFull();
			}

			//already joined the event or not
			groupExist = ((EventGroup) groupEvent).foundGroup(groupJoin);

			if(groupExist == true){
				throw new ExGroupAlreadyJoinEvent();
			}
			if(((EventGroup) groupEvent).getMinNumInOneJoin()>groupJoin.getNumOfStudent()){
				throw new ExEventGroupMinNum();
			}
			if(((EventGroup) groupEvent).getMaxNumInOneJoin()<groupJoin.getNumOfStudent()){
				throw new ExEventGroupMaxNum();
			}

			eventAllocator.groupJoinEvent(groupJoin,((EventGroup) groupEvent));
			str = "Your group join the event successfully.";
			}
    		

		//if the event not exist, group cant join the event
		catch(ExEventNotFound | ExNotGroupEvent | ExEventGroupIsFull | ExGroupAlreadyJoinEvent | ExInvalidEventID |
				ExInvalidGroupID | ExEventGroupMinNum | ExEventGroupMaxNum | ExGroupNotFound e) {
			str = e.getMessage();
		} catch (ExWrongCommand e) {
			str = e.getMessage();
			str += "Group join event command should be \"groupJoin event gXXXXXXXXX eXXXXXXXX\"\n";
		}
		return str;
		
	
	}}
