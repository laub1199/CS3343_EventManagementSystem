package EventManagementSystem;

import java.util.ArrayList;
import java.util.Date;

public class EventIndividual extends Event {
	
	private ArrayList<Student> joinedStudentList;

	public EventIndividual(String eName, String eID, int cap, Date eDate, String maj) {
		super(eName, eID, cap, eDate, maj);
		joinedStudentList = new ArrayList<>();
	}
	
	public void addStudent(Student student) {
		joinedStudentList.add(student);
	}

	public void quitStudent(Student student) {
		joinedStudentList.remove(student);
	}
	
	@Override
	public ArrayList<Student> getStudentList(){
		return joinedStudentList;
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
		System.out.printf("%d\t%s\t/\t/\n",capacity-joinedStudentList.size(), "Individual");
	}


}
