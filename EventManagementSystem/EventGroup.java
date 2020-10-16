package EventManagementSystem;

import java.util.ArrayList;
import java.util.Date;

public class EventGroup extends Event {

	private ArrayList<Group> joinedGroupList;
	private int groupCapacity;
	private int minNumInOneJoin;
	private int maxNumInOneJoin;
	
	public EventGroup(String eName, String eID, int cap, Day eDate, Major maj, int gpCap, int min, int max) {
		super(eName, eID, cap, eDate, maj);
		groupCapacity = gpCap;
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
	public ArrayList<Student> getStudentList(){
		ArrayList<Student> allStudentList = new ArrayList<Student>();
		for(Group group:joinedGroupList) {
			allStudentList.addAll(group.getStudentList());
		}
		return allStudentList;
	}
	
	@Override
	public void listJoinedStudent() {		
		for (Group g:joinedGroupList) {
			g.listStudentInGroup();
		}
	}

	@Override
	public boolean isFull() {
		return capacity < getTotalNumOfStudent() + minNumInOneJoin || groupCapacity <= joinedGroupList.size();
	}
	
	public boolean validToJoin(int numOfStudent) {
		return capacity >= getTotalNumOfStudent() + numOfStudent && groupCapacity >= joinedGroupList.size() - 1;
	}
	
	@Override
	public String printDetail() {
		return super.printDetail() + String.format("%-5d|%-10s|%-15d|%-11d|%-16d|%-16d|\n",capacity-getTotalNumOfStudent(),
				"Group",groupCapacity, groupCapacity- joinedGroupList.size(), minNumInOneJoin, maxNumInOneJoin);
	}
	
	public int getTotalNumOfStudent() {
		int numOfStudent = 0;
		for (Group g:joinedGroupList) {
			numOfStudent += g.getNumOfStudent();
		}
		return numOfStudent;
	}
	
	public boolean foundGroup(Group group) {
		for (Group gp: joinedGroupList) {
			if (gp.equals(group)) {
				return true;
			}
		}
		return false;
	}

	public int getMinNumInOneJoin() {
		return minNumInOneJoin;
	}
	
	public int getMaxNumInOneJoin() {
		return maxNumInOneJoin;
	}
}
