package EventManagementSystem;

import java.util.ArrayList;

public class EventGroup extends Event {

	private ArrayList<Group> joinedGroupList;
	private int minNumInOneJoin;
	private int maxNumInOneJoin;
	
	public EventGroup(String eName, String eID, int cap, int min, int max) {
		eventName = eName;
		eventID = eID;
		capacity = cap;
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
		int numOfStudents = 0;
		for (Group g:joinedGroupList) {
			numOfStudents += g.getNumOfGroup();
		}
		
		return capacity <= numOfStudents;
	}
	
}
