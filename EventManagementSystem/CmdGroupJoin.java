package EventManagementSystem;

public class CmdGroupJoin implements Command {
	// represent a group to join a event
	@Override
    public void execute(String[] cmdParts) {
		//command: groupJoin event (group id) (event id)
		try {
	    		if (cmdParts.length != 4 || cmdParts[2].charAt(0) != 'g' || cmdParts[2].length() != 9 
	    				|| cmdParts[3].charAt(0) != 'e' || cmdParts[3].length() != 9) {
	    			throw new ExWrongCommand();
	    		}
		String groupID = cmdParts[2];
		String eventID = cmdParts[3];
		
		//if the group not exist, user cant join the event
		
    		GroupHandler groupHandler = GroupHandler.getInstance();
    		Group groupJoin = groupHandler.getGroup(groupID);
    		    		
    		EventAllocator eventAllocator = EventAllocator.getInstance();
    		Event groupEvent = eventAllocator.findEventByID(eventID);
    		boolean groupExist;
    		
    		//does the event allow group join
    		if(groupEvent instanceof EventGroup){
    			//does the event full already
    			if(groupEvent.isFull()==false){
    				//already joined the event or not
    				try { groupExist = ((EventGroup) groupEvent).foundGroup(groupJoin)}
    				catch (ExGroupNotFound e) {
    					System.out.println(e.getMessage());
    		    	}
    				if(groupExist !=true){
    					if(((EventGroup) groupEvent).getMinNumInOneJoin()<groupJoin.getNumOfStudent()){ {
    		       			if(((EventGroup) groupEvent).getMaxNumInOneJoin()>groupJoin.getMaxNumOfStudent()){
    		       			        eventAllocator.groupJoinEvent(groupJoin,((EventGroup) groupEvent));
    		       					System.out.print("Your group join the event successfully.");
    		       				}
    		       				else{
    		       					System.out.print("Fail to join the event.The number of groupmate is more than the required minimum number of event.");	 
    		       					};
    						}}
    						else{
    		                   System.out.print("Fail to join the event. The number of groupmate is less than the required minimum number of event.");	 
    							 };
    					}
    					else {
    					 System.out.print("Fail to join the event as you already joined the event.");
    						 };							 		
    				}
    						 	else
    						 	{
    						 		System.out.print("Fail to join the event. The number of participated group of this event has reached its maximum.");
    						 	};
    						 }else
    						  {System.out.print("Fail to join the event. This event only allows individuals to join.");};
    						 }
		
    	
		
		//if the event not exist, group cant join the event
		catch(ExEventNotFound e) {
			System.out.println(e.getMessage());
		}
	}

}
