package EventManagementSystem;

public class CmdListGroup implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
    	try {
    		if(cmdParts.length != 2) {
    			throw new ExWrongCommand(); 
    		}
    		
    		GroupHandler groupHandler = GroupHandler.getInstance();
    		groupHandler.listGroup();
    	}catch(ExWrongCommand e){
    		System.out.println(e.getMessage());
    	}
    }
}
