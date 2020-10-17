package EventManagementSystem;

public class CmdListGroup implements Command {
    @Override
    public String execute(String[] cmdParts) throws CloneNotSupportedException {
    	String str = ""; 
    	try {
    		if(cmdParts.length != 2) {
    			throw new ExWrongCommand(); 
    		}
    		
    		GroupHandler groupHandler = GroupHandler.getInstance();
    		str = groupHandler.listGroup();
    	} catch (ExWrongCommand e) {
    		str = e.getMessage();
    		str += "List group command should be \"list group\"\n";
    	} 
    	return str;
    	
    }
}
