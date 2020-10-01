package EventManagementSystem;

public class GroupHandler {
    private static GroupHandler instance = new GroupHandler();
    private ArrayList<Group> groupList;
    
    public static GroupHandler getInstance(){return instance;}
    public void listGroup(){
        for(Group group:groupList){
            System.out.println(group);
        }
    }
    public Group getGroup(String groupID){
        for(Group group:grouplist){
            if(groupID = group.getGroupID()){
                return group;
                break;
            }
        }
    }
    public ArrayList<Group> getGroupList(){
        return grouplist;
    }
    public void addGroup(Group group){
        grouplist.add(group);
    }
    public void deleteGroup(Group group){
        grouplist.remove(group);
    }
    public void deleteGroup(String groupID){
        grouplist.remove(getGroup(groupID));
    }
}

