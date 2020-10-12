package EventManagementSystem;

public class CmdStudentJoinEvent implements Command {
    //the student type the event name to join
    //command: studentJoin event (studentID) (eventID)
    public void execute(String[] cmdParts) {

        try {

            if (cmdParts.length != 4 || cmdParts[2].charAt(0) != 's' || cmdParts[3].charAt(0) != 'e') {
                throw new ExWrongCommand();
            }

            String studentID = cmdParts[2];
            String eventID = cmdParts[3];
            
            try {
	    		  if (studentID.length() != 9 || Integer.parseInt(studentID.substring(1,8)) <0 || Integer.parseInt(studentID.substring(1,8)) > 99999999) {
					 throw new ExInvalidStudentID();
				 }
	         }
	         catch (NumberFormatException ex) {
	           	 throw new ExInvalidStudentID();
	         }
			 
			 try {
	    		  if (eventID.length() != 9 || Integer.parseInt(eventID.substring(1,8)) <0 || Integer.parseInt(eventID.substring(1,8)) > 99999999) {
					 throw new ExInvalidEventID();
				 }
	         }
	         catch (NumberFormatException ex) {
	           	 throw new ExInvalidEventID();
	         }
            
            StudentHandler studentHandler = StudentHandler.getInstance();
            Student student = studentHandler.getStudent(studentID);

            EventAllocator eventAllocator = EventAllocator.getInstance();
            Event individualEvent = eventAllocator.findEventByID(eventID);

            //does the event allow individual join
            if (individualEvent instanceof EventIndividual != true) {
                throw new ExNotIndividualEvent();
            }
            //does the event full already
            if (individualEvent.isFull() == true) {
                throw new ExEventIndividualIsFull();
            }

            //already joined the event or not
            if (individualEvent.isStudentJoined(student) == true) {
                throw new ExIndividualAlreadyJoinEvent();
            }


            ((EventIndividual) individualEvent).addStudent(student);
            System.out.println("You join the event successfully.");

        }
        //no this student
        catch (ExStudentNotFound | ExEventNotFound | ExNotIndividualEvent | ExEventIndividualIsFull |
        		ExInvalidStudentID | ExInvalidEventID | ExIndividualAlreadyJoinEvent e) {
            System.out.println(e.getMessage());
        } catch (ExWrongCommand e) {
			System.out.println(e.getMessage());
			System.out.println("Student join event command should be \"studentJoin event sXXXXXXXXX eXXXXXXXX\"");
		}
    }

}
