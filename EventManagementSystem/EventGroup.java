package EventManagementSystem;

import java.util.ArrayList;

public class EventGroup extends Event {

	private ArrayList<Group> joinedGroupList;
	private int minNumInOneJoin;
	private int maxNumInOneJoin;
	
	public EventGroup(String eName, String eID, int cap, Date eDate, int min, int max) {
		super(eName, eID, cap, eDate);
		minNumInOneJoin = min;
		maxNumInOneJoin = max;
	}
	
	public void addGroup(Group group) {
		joinedGroupList.add(group);
	}

	public void quitGroup(Group group) {
		joinedGroupList.remove(group);
	}
	
	@Override
	public void listJoinedStudent() {		
		for (Group g:joinedGroupList) {
			g.listStudentInGroup();
		}
	}

	@Override
	public boolean isFull() {
		int numOfStudent = 0;
		for (Group g:joinedGroupList) {
			numOfStudent += g.getNumOfStudent();
		}
		return capacity <= numOfStudents;
	}
	
	public boolean validToJoin(int numOfStudent) {
		return capacity <= capacity + numOfStudent;
	}
	
}
