package EventManagementSystem;

import java.util.ArrayList;

public class EventIndividual extends Event {
	
	private ArrayList<Student> joinedStudentList;

	public EventIndividual(String eName, String eID, int cap, Date eDate) {
		super(eName, eID, cap, eDate);
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
			System.out.println(s.printString());
		}
	}

	@Override
	public boolean isFull() {
		return capacity <= joinedStudentList.size();
	}
	
	@Override
	public void printDetail() {
		super.printDetail();
		System.out.printf("%d\t%s\n",capacity-joinedStudentList.size(), "Individual");
	}


}
