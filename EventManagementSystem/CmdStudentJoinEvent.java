package EventManagementSystem;
public class CmdStudentJoinEvent implements Command{
	//the student type the event name to join
	//command: studentJoin event (studentID) (eventID)
	public void execute(String[] cmdParts) {
		 String studentID = cmdParts[2];
			String eventID = cmdParts[3];
		 try {	
			 
			 
			 StudentHandler studentHandler = StudentHandler.getInstance();
			 Student student = studentHandler.getStudent(studentID);
			 
			 EventAllocator eventAllocator = EventAllocator.getInstance();
			 Event event = eventAllocator.findEventByID(eventID);
		    
			 //already joined the event or not
			 if(event.isFoundStudentById(studentID)==false){
				 
				//check event is full or not
				 if (event.isFull==false) {
					 if(event.isStudentJoined(student)==false)
						 {
						 eventAllocator.studentJoinEvent(student,event);
						 }
					 else
					 {
						 System.out.print("You already joined the event.");
					 }
		    	}
			}
			 else
		 {System.out.print("The number of participant of this event has reached its maximum.");}
			 	 
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
