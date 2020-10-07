package EventManagementSystem;

public class CmdListStudent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
    	try {
    		if(cmdParts.length != 2) {
    			throw new ExWrongCommand();
    		}
    		
    		StudentHandler studentHandler = StudentHandler.getInstance();
    		studentHandler.listStudent();
    	}catch(ExWrongCommand e) {
    		System.out.println(e.getMessage());
    	}
    }
}
