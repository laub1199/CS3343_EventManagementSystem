package EventManagementSystem;

public class CmdListEvent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
    	try {
    		if (cmdParts.length != 2) {
    			throw new ExWrongCommand();
    		}
    		EventAllocator eventallocator = EventAllocator.getInstance();
    		eventallocator.listEvent();
    	} catch (ExWrongCommand e) {
			System.out.println(e.getMessage());
			System.out.println("List event command should be \"list event\"");
    	} 
    }
}
