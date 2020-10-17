package EventManagementSystem;

public class CmdListEvent implements Command {
    @Override
    public String execute(String[] cmdParts) throws CloneNotSupportedException {
    	String str = ""; 
    	try {
    		if (cmdParts.length != 2) {
    			throw new ExWrongCommand();
    		}
    		EventAllocator eventallocator = EventAllocator.getInstance();
        	str = eventallocator.listEvent();
    	} catch (ExWrongCommand e) {
    		str = e.getMessage();
    		str += "List event command should be \"list event\"\n";
    	} 
    	return str;
    	
    }
}
