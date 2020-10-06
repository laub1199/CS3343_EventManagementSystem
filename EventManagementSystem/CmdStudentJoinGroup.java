package EventManagementSystem;
public class CmdStudentJoinGroup implements Command{
	//the student type the group name to join
	//command: studentJoin Group (student id) (groupid)
	
	 @Override
	    public void execute(String[] cmdParts) {
		 String studentID = cmdParts[2];
			String groupID = cmdParts[3];
		 try {	
			 
			 
			 StudentHandler studentHandler = StudentHandler.getInstance();
			 Student student = studentHandler.getStudent(studentID);
			 
			 GroupHandler groupHandler = GroupHandler.getInstance();
			 Group group = groupHandler.getGroup(groupID);
			 
			 if(group.isFoundStudentById(studentID)==false){
				 
				//check group is full or not
				 if (group.getNumOfStudent()<group.getMaxNumOfStudent()){
					 group.addStudent(student);
				}
				 else
				 {
					 System.out.print("The group is full.");
					 }
				 }
				 }
			 else
		 {
				 System.out.print("you already joined the group.");
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
