package EventManagementSystem;
import java.util.ArrayList;

public class GroupHandler {
    private static GroupHandler instance = new GroupHandler();
    private ArrayList<Group> groupList = new ArrayList<Group>();
    
    private GroupHandler() {
    	
    }
    
    
    public static GroupHandler getInstance(){return instance;}
    public void listGroup(){
        for(Group group:groupList){
            System.out.println(group);
        }
    }
    public Group getGroup(String groupID){
        for(Group group:groupList){
            if(groupID == group.getGroupID()){
                return group;
            }
        }
        return null;
    }
    public ArrayList<Group> getGroupList(){
        return groupList;
    }
    public void addGroup(Group group){
        groupList.add(group);
    }
    public void deleteGroup(Group group){
        groupList.remove(group);
    }
    public void deleteGroup(String groupID){
        groupList.remove(getGroup(groupID));
    }
}

