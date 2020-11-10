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
        	if (!Student.checkStudentID(studentID)) {
        		throw new ExInvalidStudentID();
        	}
            Student student = studentHandler.getStudent(studentID);
            if (cmdParts[2].charAt(0) == 'g') {
                String groupID = cmdParts[2];
            	
                groupID = cmdParts[2];
            	if (!Group.checkGroupID(groupID)) {
            		throw new ExInvalidGroupID();
            	}
                
                Group group = groupHandler.getGroup(groupID);
                if (group.isFoundStudentById(studentID)) {
                    group.deleteStudent(student);
                    str += "Student " + studentID + " has quited group " + groupID + "\n";
                    EventGroup event = (EventGroup) (eventAllocator.findEventByGroup(group));
                    if (event != null) {
                        if (group.getNumOfStudent() < event.getMinNumInOneJoin()) {
                            event.quitGroup(group);
                            str += "Group " + groupID + " with a number of " + group.getNumOfStudent() +
                                    " member does not meet the minimum number of " + event.getMinNumInOneJoin() +
                                    " student for join event " + event.getEventID() + "\n";
                            str += "Group " + groupID + " is forced to quit event " + event.getEventID() + "\n";
                        }
                    }
                }
                else {
                    throw new ExStudentNotFound();
                }
            }
            if (cmdParts[2].charAt(0) == 'e') {
                String eventID = cmdParts[2];
                if (!Event.checkEventID(eventID)) {
					throw new ExInvalidEventID();
				}
                Event event = eventAllocator.findEventByID(eventID);
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
                    str += "Student " + studentID + " has quited event " + eventID + "\n";
                }
                else {
                    throw new ExInvalidEventGroupQuitCommand();
                }
            }
        } catch (ExStudentNotFound | ExGroupNotFound | ExInvalidGroupID |
                ExInvalidEventID | ExEventNotFound | ExInvalidEventGroupQuitCommand | ExInvalidStudentID e) {
            str = e.getMessage();
        } catch (ExWrongCommand e) {
            str = e.getMessage();
            str += "Student quit command should be \"studentQuit sXXXXXXXXX gXXXXXXXXX\"\n";
        }
        return str;

    }
}
