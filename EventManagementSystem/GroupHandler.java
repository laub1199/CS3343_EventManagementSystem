package EventManagementSystem;
import java.util.ArrayList;

public class GroupHandler {
    private static GroupHandler instance = new GroupHandler();
    private ArrayList<Group> groupList;
    
    GroupHandler() {
        groupList = new ArrayList<>();
    }
    
    public static GroupHandler getInstance(){return instance;}
    
    public String listGroup(){
    	String str = "";
    	if (groupList.size() > 0) {
    		str = String.format("|%-10s|%-18s|%-23s|\n","GroupID", "Number Of Student", "Max Number Of Student");
	        for(Group group:groupList){
	        	str += group.toString();
	        }
    	}
    	else {
    		str = "There are no group.";
    	}
    	return str;
    }
    
    public Group getGroup(String groupID) throws ExGroupNotFound{
        for(Group group:groupList){
            if(groupID.equals(group.getGroupID())){
                return group;
            }
        }
        throw new ExGroupNotFound();
    }
    
    public void listGroupByStudentId(String studentId) throws ExStudentNotFound {
    	for(Group group:groupList) {
    		if(group.isFoundStudentById(studentId)) {
    			System.out.println(group.toString());
    		}
    	}
    }
    
    public ArrayList<Group> getGroupList(){
        return groupList;
    }
    public void createGroup(Group group) {
        groupList.add(group);
    }
    
    public void deleteGroup(Group group){
        groupList.remove(group);
    }
    
    public void deleteGroup(String groupID) throws ExGroupNotFound{
        groupList.remove(getGroup(groupID));
    }
}

