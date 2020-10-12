package EventManagementSystem;

public class CmdStudentQuit implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        GroupHandler groupHandler = GroupHandler.getInstance();
        EventAllocator eventAllocator = EventAllocator.getInstance();
        StudentHandler studentHandler = StudentHandler.getInstance();
        try {
            if (cmdParts.length != 3) {
                throw new ExWrongCommand();
            }
            String studentID = null;
            studentID = cmdParts[1];
            if (studentID.length() != 9 || studentID.charAt(0) != 's') {
                throw new ExInvalidStudentID();
            }
            Student student = studentHandler.getStudent(studentID);
            if (cmdParts[2].charAt(0) == 'g' && cmdParts[2].length() == 9) {
                Group group = groupHandler.getGroup(cmdParts[2]);
                if (group.isFoundStudentById(studentID)) {
                    group.deleteStudent(student);
                    System.out.println("Student" + studentID + " has quited group " + cmdParts[2]);
                    EventGroup event = (EventGroup) (eventAllocator.findEventByGroup(group));
                    if (event != null) {
                        if (group.getNumOfStudent() < event.getMinNumInOneJoin()) {
                            event.quitGroup(group);
                            System.out.println("Group " + cmdParts[2] + " with a number of " + group.getNumOfStudent() +
                                    " does not meet the minimum number of " + event.getMinNumInOneJoin() +
                                    " student for join event " + event.getEventID());
                            System.out.println("Group " + cmdParts[2] + " is forced to quit event " + event.getEventID());
                        }
                    }
                }
                else {
                    throw new ExStudentNotFound();
                }
            }
            else if (cmdParts[2].charAt(0) == 'e' && cmdParts[2].length() == 9) {
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
                    System.out.println("Student" + cmdParts[2] + " has quited event " + cmdParts[2]);
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
            System.out.println("Wrong number format!");
        } catch (ExInvalidStudentQuitCommand | ExInvalidID | ExStudentNotFound | ExGroupNotFound |
                ExEventNotFound | ExInvalidEventGroupQuitCommand | ExInvalidStudentID e) {
            System.out.println(e.getMessage());
        } catch (ExWrongCommand e) {
			System.out.println(e.getMessage());
			System.out.println("Student quit command should be \"quit sXXXXXXXXX gXXXXXXXXX\"");
    	} 
    }
}
