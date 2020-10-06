package EventManagementSystem;
import java.util.ArrayList;

public class GroupHandler {
    private static GroupHandler instance = new GroupHandler();
    private ArrayList<Group> groupList;
    
    private GroupHandler() {
        groupList = new ArrayList<>();
    }
    
    
    public static GroupHandler getInstance(){return instance;}
    public void listGroup(){
    	System.out.println("GroupID\tNumber Of Student");
        for(Group group:groupList){
            System.out.println(group);
        }
    }
    public Group getGroup(String groupID) throws ExGroupNotFound{
        for(Group group:groupList){
            if(groupID == group.getGroupID()){
                return group;
            }
        }
        throw new ExGroupNotFound();
    }
    public ArrayList<Group> getGroupList(){
        return groupList;
    }
    public void createGroup(String groupId, int numOfStudent) throws ExInvalidGroupID, ExGroupStudentTooLess {
        if (groupId.length() != 9 || groupId.charAt(0) != 'g') {
            throw new ExInvalidGroupID();
        }
        for (Group group:groupList) {
            if (groupId.equals(group.getGroupID())) {
                throw new ExInvalidGroupID();
            }
        }
        if (numOfStudent <= 1) {
            throw new ExGroupStudentTooLess();
        }
        groupList.add(new Group(groupId, numOfStudent));
    }
    public void deleteGroup(Group group){
        groupList.remove(group);
    }
    public void deleteGroup(String groupID) throws ExGroupNotFound{
        groupList.remove(getGroup(groupID));
    }
}

