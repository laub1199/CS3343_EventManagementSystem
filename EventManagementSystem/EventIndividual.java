package EventManagementSystem;

import java.util.ArrayList;

public class EventIndividual extends Event {
	
	private ArrayList<Student> joinedStudentList;

	public EventIndividual(String eName, String eID, int cap) {
		eventName = eName;
		eventID = eID;
		capacity = cap;
	}
	
	public void addStudent(Student student) {
		joinedStudentList.add(student);
	}

	public void quitStudent(Student student) {
		joinedStudentList.remove(student);
	}
	
	@Override
	public void listJoinedStudent() {
		for (Student s:joinedStudentList) {
			System.out.println(s.toString());
		}
	}

	public boolean validToJoin(int numOfStudent) {
		return capacity <= capacity + numOfStudent;
	}

	@Override
	public boolean isFull() {
		return capacity <= joinedStudentList.size();
	}


}
