package EventManagementSystem;
public class CmdStudentJoinEvent implements Command{
	//the student type the event name to join
	//command: studentJoin event (studentID) (eventID)
	public void execute(String[] cmdParts) {
		 
		 try {	
			 
			 if (cmdParts.length != 4 || cmdParts[2].charAt(0) != 's' || cmdParts[2].length() != 9 
	    				|| cmdParts[3].charAt(0) != 'e' || cmdParts[3].length() != 9) {
	    			throw new ExWrongCommand();}
			 
			 String studentID = cmdParts[2];
			 String eventID = cmdParts[3];
			 StudentHandler studentHandler = StudentHandler.getInstance();
			 Student student = studentHandler.getStudent(studentID);
			 
			 EventAllocator eventAllocator = EventAllocator.getInstance();
			 Event individualEvent = eventAllocator.findEventByID(eventID);
			 
			 //does the event allow individual join
			 if(individualEvent instanceof EventIndividual)
				 {
				//does the event full already
				 	if(individualEvent.isFull()==false)
				 	{
				 		//already joined the event or not
						 if(individualEvent.isStudentJoined(student)==false){
								((EventIndividual) individualEvent).addStudent(student);
								System.out.print("You join the event successfully.");	 
						}
						 else {
							 System.out.print("Fail to join the event as you already joined the event.");
							 }
										 		
				 	}
				 	else
				 	{
				 		System.out.print("Fail to join the event. The number of participant of this event has reached its maximum.");
				 	}
				 }
			 else{
				 System.out.print("Fail to join the event. This event need to join in group.");
			 };
		 }
		//no this student
		 catch (ExStudentNotFound e) {
				System.out.println(e.getMessage());
	    	}
		 //no this event
		 catch (ExEventNotFound e){
				System.out.println(e.getMessage());
	    	}
		 }

}
