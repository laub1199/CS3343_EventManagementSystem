package EventManagementSystem;

public class CmdListStudent implements Command {
    @Override
    public String execute(String[] cmdParts) throws CloneNotSupportedException {
    	String str = ""; 
    	try {
    		if(cmdParts.length != 2) {
    			throw new ExWrongCommand();
    		}
    		
    		StudentHandler studentHandler = StudentHandler.getInstance();
    		str = studentHandler.listStudent();
    	} catch (ExWrongCommand e) {
    		str = e.getMessage();
    		str += "List student command should be \"list student\"\n";
    	} 
    	return str;
    	
    }
}
