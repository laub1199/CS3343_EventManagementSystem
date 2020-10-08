package EventManagementSystem;
public class CmdStudentJoinGroup implements Command{
	//the student type the group name to join
	//command: studentJoin Group (student id) (groupid)
	
	 @Override
	    public void execute(String[] cmdParts) {
		
		 try {	
			 if (cmdParts.length != 4 || cmdParts[2].charAt(0) != 's' || cmdParts[2].length() != 9 
	    				|| cmdParts[3].charAt(0) != 'g' || cmdParts[3].length() != 9) {
	    			throw new ExWrongCommand();}
			 
			 String studentID = cmdParts[2];
			 String groupID = cmdParts[3];
			 StudentHandler studentHandler = StudentHandler.getInstance();
			 Student student = studentHandler.getStudent(studentID);
			 
			 GroupHandler groupHandler = GroupHandler.getInstance();
			 Group group = groupHandler.getGroup(groupID);
			 
			 EventAllocator eventAllocator = EventAllocator.getInstance();
			 Event groupJoinedEvent;
			 //check student is in that group already or not
			 if(group.isFoundStudentById(studentID)==false){
				 
				//check group is full or not
				 if (group.getNumOfStudent()<group.getMaxNumOfStudent()){
					 
					 //if the group have joined a event, need to consider the event required group max number
					 try {
						 groupJoinedEvent = eventAllocator.findEventByGroup(group);
					 }
					 catch (ExGroupNotFound e) {
							System.out.println(e.getMessage());
				    	} 
					 if (groupJoinedEvent == null) {
						 group.addStudent(student);
						 System.out.print("You joined the group successfully.");
					 }
					 else {
						 System.out.print("Fail to join the group. The group have joined a event already, and the group have reached maximum number requirment of the event.");
					 }
				 }
				 else
				 {
					 System.out.print("Fail to join the group. The group is full.");
					 }
				 }
					 				 
			 else
		 {
				 System.out.print("Fail to join the group as you already joined the group.");
				 }
			 
		 }

		//no this student
		 catch (ExStudentNotFound e) {
				System.out.println(e.getMessage());
	    	}
		 //no this group
		 catch (ExGroupNotFound e) {
				System.out.println(e.getMessage());
	    	}
		 }
