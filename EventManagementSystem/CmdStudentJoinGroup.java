package EventManagementSystem;
public class CmdStudentJoinGroup implements Command {
	//the student type the group name to join
	//command: studentJoin Group (student id) (groupid)

	@Override
	public void execute(String[] cmdParts) {

		try {
			if (cmdParts.length != 4 || cmdParts[2].charAt(0) != 's' || cmdParts[3].charAt(0) != 'g') {
				throw new ExWrongCommand();
			}

			String studentID = cmdParts[2];
			String groupID = cmdParts[3];
			
			try {
	    		  if (studentID.length() != 9 || Integer.parseInt(studentID.substring(1,8)) <0 || Integer.parseInt(studentID.substring(1,8)) > 99999999) {
					 throw new ExInvalidStudentID();
				 }
	         }
	         catch (NumberFormatException ex) {
	           	 throw new ExInvalidStudentID();
	         }
			 
			 try {
	    		  if (groupID.length() != 9 || Integer.parseInt(groupID.substring(1,8)) <0 || Integer.parseInt(groupID.substring(1,8)) > 99999999) {
					 throw new ExInvalidGroupID();
				 }
	         }
	         catch (NumberFormatException ex) {
	           	 throw new ExInvalidGroupID();
	         }
			
			StudentHandler studentHandler = StudentHandler.getInstance();
			Student student = studentHandler.getStudent(studentID);

			GroupHandler groupHandler = GroupHandler.getInstance();
			Group group = groupHandler.getGroup(groupID);


			EventAllocator eventAllocator = EventAllocator.getInstance();
			Event groupJoinedEvent;

			//check student is in that group already or not
			if (group.isFoundStudentById(studentID) == true) {
				throw new ExStudentAlreadyJoinedGroup();
			}

			//check group is full or not
			if (group.getNumOfStudent() >= group.getMaxNumOfStudent()) {
				throw new ExGroupIsFull();
			}

			//if the group have joined a event, need to consider the event required group max number
			groupJoinedEvent = eventAllocator.findEventByGroup(group);
			if (groupJoinedEvent == null) {
				group.addStudent(student);
				System.out.println("You joined the group successfully.");
			} else {
				System.out.println("Fail to join the group. The group have joined a event already, and the group have reached maximum number requirment of the event.");
			}
		} catch (ExStudentNotFound | ExGroupNotFound | ExStudentAlreadyJoinedGroup | ExInvalidStudentID | ExInvalidGroupID | ExGroupIsFull e) {
			System.out.println(e.getMessage());
		} catch (ExWrongCommand e) {
			System.out.println(e.getMessage());
			System.out.println("Student join group command should be \"studentJoin group sXXXXXXXXX gXXXXXXXX\"");
		}
	}
}