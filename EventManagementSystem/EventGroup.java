package EventManagementSystem;

import java.util.ArrayList;
import java.util.Date;

public class EventGroup extends Event {

	private ArrayList<Group> joinedGroupList;
	private int minNumInOneJoin;
	private int maxNumInOneJoin;
	
	public EventGroup(String eName, String eID, int cap, Date eDate, int min, int max) {
		super(eName, eID, cap, eDate);
		minNumInOneJoin = min;
		maxNumInOneJoin = max;
		joinedGroupList = new ArrayList<>();
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
		return capacity <= getTotalNumOfStudent();
	}
	
	public boolean validToJoin(int numOfStudent) {
		return numOfStudent > 0 && capacity <= getTotalNumOfStudent() + numOfStudent;
	}
	
	@Override
	public void printDetail() {
		super.printDetail();
		System.out.printf("%d\t%s\t%d\t%d\n", capacity-getTotalNumOfStudent(), "Group", minNumInOneJoin, maxNumInOneJoin);
	}
	
	public int getTotalNumOfStudent() {
		int numOfStudent = 0;
		for (Group g:joinedGroupList) {
			numOfStudent += g.getNumOfStudent();
		}
		return numOfStudent;
	}
	
}
