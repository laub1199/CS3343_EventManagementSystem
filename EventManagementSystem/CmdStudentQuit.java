package EventManagementSystem;

public class CmdStudentQuit implements Command {
    @Override
    public String execute(String[] cmdParts) throws CloneNotSupportedException {
    	String str = ""; 
        GroupHandler groupHandler = GroupHandler.getInstance();
        EventAllocator eventAllocator = EventAllocator.getInstance();
        StudentHandler studentHandler = StudentHandler.getInstance();
        try {
            if (cmdParts.length != 3 || cmdParts[1].charAt(0) != 's' || (cmdParts[2].charAt(0) != 'g' && cmdParts[2].charAt(0) != 'e')) {
                throw new ExWrongCommand();
            }
            String studentID = null;
            studentID = cmdParts[1];
            try {
	    		 if (studentID.length() != 9 || Integer.parseInt(studentID.substring(1,8)) <0 || Integer.parseInt(studentID.substring(1,8)) > 99999999) {
					 throw new ExInvalidStudentID();
				 }
	        }
	        catch (NumberFormatException ex) {
	            throw new ExInvalidStudentID();
	        }
            Student student = studentHandler.getStudent(studentID);
            if (cmdParts[2].charAt(0) == 'g') {
            	try {
  	    		  if (cmdParts[2].length() != 9 || Integer.parseInt(cmdParts[2].substring(1,8)) <0 || Integer.parseInt(cmdParts[2].substring(1,8)) > 99999999) {
  					 throw new ExInvalidGroupID();
  				 }
	  	         }
	  	         catch (NumberFormatException ex) {
	  	           	 throw new ExInvalidGroupID();
	  	         }
                Group group = groupHandler.getGroup(cmdParts[2]);
                if (group.isFoundStudentById(studentID)) {
                    group.deleteStudent(student);
                    str += "Student" + studentID + " has quited group " + cmdParts[2];
                    EventGroup event = (EventGroup) (eventAllocator.findEventByGroup(group));
                    if (event != null) {
                        if (group.getNumOfStudent() < event.getMinNumInOneJoin()) {
                            event.quitGroup(group);
                            str += "Group " + cmdParts[2] + " with a number of " + group.getNumOfStudent() +
                                    " member does not meet the minimum number of " + event.getMinNumInOneJoin() +
                                    " student for join event " + event.getEventID() + "\n";
                            str += "Group " + cmdParts[2] + " is forced to quit event " + event.getEventID() + "\n";
                        }
                    }
                }
                else {
                    throw new ExStudentNotFound();
                }
            }
            else if (cmdParts[2].charAt(0) == 'e') {
            	try {
                	if (cmdParts[2].length() != 9 || Integer.parseInt(cmdParts[2].substring(1,8)) <0 || Integer.parseInt(cmdParts[2].substring(1,8)) > 99999999) {
                		throw new ExInvalidEventID();
                	}
                } 
                catch (NumberFormatException ex) {
                	throw new ExInvalidEventID();
                }
                Event event = eventAllocator.findEventByID(cmdParts[2]);
                if (event instanceof EventIndividual) {
                    boolean studentFound = false;
                    for(Student tempStudent : ((EventIndividual)event).getStudentList()) {
                        if(student.getStudentID().equals(tempStudent.getStudentID())) {
                            studentFound = true;
                        }
                    }
                    if(!studentFound) {
                        throw new ExStudentNotFound();
                    }
                    ((EventIndividual)event).quitStudent(student);
                    str += "Student" + cmdParts[2] + " has quited event " + cmdParts[2] + "\n";
                }
                else {
                    throw new ExInvalidEventGroupQuitCommand();
                }
            }
            else if (cmdParts[2].charAt(0) == 's' && cmdParts[2].length() == 9) {
                throw new ExInvalidStudentQuitCommand();
            }
            else {
                throw new ExInvalidID();
            }

        } catch (NumberFormatException e) {
        	str = "Wrong number format!\n";
        } catch (ExInvalidStudentQuitCommand | ExInvalidID | ExStudentNotFound | ExGroupNotFound | ExInvalidGroupID |
        		ExInvalidEventID | ExEventNotFound | ExInvalidEventGroupQuitCommand | ExInvalidStudentID e) {
        	str = e.getMessage();
        } catch (ExWrongCommand e) {
        	str = e.getMessage();
        	str += "Student quit command should be \"quit sXXXXXXXXX gXXXXXXXXX\"\n";
    	} 
    	return str;
    	
    }
}
