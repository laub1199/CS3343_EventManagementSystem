package testEventManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 
import EventManagementSystem.*;

public class TestCmdSearchEvent {

	/**
     * Sets up the test fixture.
     * Called before every test case method.
     */
	@BeforeEach
	public void setUp() throws Exception {
		SystemDate.createTheInstance("10-oct-2020");
	}
    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
	@AfterEach
	public void tearDown() throws Exception {
		EventAllocator eventAllocator = EventAllocator.getInstance();
		eventAllocator.getEventList().clear();
	}
	
	@Test
	public void testNormalInput1() throws Exception {	
		String[] command1 = {"create", "event", "badminton", "e12345678", "10", "20-oct-2020", "cs", "5", "2", "3"};
		String[] command2 = {"search", "event", "id", "e12345678"};
		String returnstr = null;
		String expected = "";
		expected += String.format("|%-10s|%-30s|%-12s|%-8s|%-30s|%-5s|%-10s|%-15s|%-11s|%-16s|%-16s|\n",
				"Event ID","Event Name","Date","Capacity","Major","Quota","Type","Group Capacity","Group Quota","Min No. In Group","Max No. In Group");
		expected += String.format("|%-10s|%-30s|%-12s|%-8d|%-30s|%-5d|%-10s|%-15d|%-11d|%-16d|%-16d|\n",
				"e12345678","badminton","20-Oct-2020",10,"Computer Science",10,"Group",5,5,2,3);
		new CmdCreateEvent().execute(command1);
		returnstr = (new CmdSearchEvent()).execute(command2);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testNormalInput2() throws Exception {
		String[] command1 = {"create", "event", "drawing", "e87654321", "30", "20-oct-2020", "cs"};
		String[] command2 = {"search", "event", "id", "e87654321"};
		String returnstr = null;
		String expected = "";
		expected += String.format("|%-10s|%-30s|%-12s|%-8s|%-30s|%-5s|%-10s|%-15s|%-11s|%-16s|%-16s|\n",
				"Event ID","Event Name","Date","Capacity","Major","Quota","Type","Group Capacity","Group Quota","Min No. In Group","Max No. In Group");
		expected += String.format("|%-10s|%-30s|%-12s|%-8d|%-30s|%-5d|%-10s|%-15s|%-11s|%-16s|%-16s|\n",
				"e87654321","drawing","20-Oct-2020",30,"Computer Science",30,"Individual","/","/","/","/");
		new CmdCreateEvent().execute(command1);
		returnstr = (new CmdSearchEvent()).execute(command2);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testNormalInput3() throws Exception {
		String[] command1 = {"create", "event", "badminton", "e12345678", "10", "20-oct-2020", "cs", "5", "2", "3"};
		String[] command2 = {"create", "event", "drawing", "e87654321", "30", "20-oct-2020", "cs"};
		String[] command3 = {"search", "event", "major", "cs"};
		String returnstr = null;
		String expected = "";
		expected += String.format("|%-10s|%-30s|%-12s|%-8s|%-30s|%-5s|%-10s|%-15s|%-11s|%-16s|%-16s|\n",
				"Event ID","Event Name","Date","Capacity","Major","Quota","Type","Group Capacity","Group Quota","Min No. In Group","Max No. In Group");
		expected += String.format("|%-10s|%-30s|%-12s|%-8d|%-30s|%-5d|%-10s|%-15d|%-11d|%-16d|%-16d|\n",
				"e12345678","badminton","20-Oct-2020",10,"Computer Science",10,"Group",5,5,2,3);
		expected += String.format("|%-10s|%-30s|%-12s|%-8d|%-30s|%-5d|%-10s|%-15s|%-11s|%-16s|%-16s|\n",
				"e87654321","drawing","20-Oct-2020",30,"Computer Science",30,"Individual","/","/","/","/");
		new CmdCreateEvent().execute(command1);
		new CmdCreateEvent().execute(command2);
		returnstr = (new CmdSearchEvent()).execute(command3);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExWrongCommand1() throws Exception {	
		String[] command = {"search", "event", "id", "e12345678", "test"};
		String returnstr = null;
		String expected = "";
		expected += "Wrong Command\n";
		expected += "Search event command should be \"search event id eXXXXXXXXX\" or \"search event major XXXXXXXXX\"\n";
		returnstr = (new CmdSearchEvent()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExWrongCommand2() throws Exception {	
		String[] command = {"search", "event", "id", "s12345678"};
		String returnstr = null;
		String expected = "";
		expected += "Wrong Command\n";
		expected += "Search event command should be \"search event id eXXXXXXXXX\" or \"search event major XXXXXXXXX\"\n";
		returnstr = (new CmdSearchEvent()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExWrongCommand3() throws Exception {	
		String[] command = {"search", "event", "test", "test"};
		String returnstr = null;
		String expected = "";
		expected += "Wrong Command\n";
		expected += "Search event command should be \"search event id eXXXXXXXXX\" or \"search event major XXXXXXXXX\"\n";
		returnstr = (new CmdSearchEvent()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExInvalidEventID1() throws Exception {	
		String[] command = {"search", "event", "id", "e123456"};
		String returnstr = null;
		String expected = "";
		expected += "Invalid event ID!\n";
		returnstr = (new CmdSearchEvent()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExInvalidEventID2() throws Exception {	
		String[] command = {"search", "event", "id", "e-1234567"};
		String returnstr = null;
		String expected = "";
		expected += "Invalid event ID!\n";
		returnstr = (new CmdSearchEvent()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExInvalidEventID3() throws Exception {	
		String[] command = {"search", "event", "id", "etesttest"};
		String returnstr = null;
		String expected = "";
		expected += "Invalid event ID!\n";
		returnstr = (new CmdSearchEvent()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExEventNotFound() throws Exception {	
		String[] command = {"search", "event", "major", "cs"};
		String returnstr = null;
		String expected = "";
		expected += "Event not found!\n";
		returnstr = (new CmdSearchEvent()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExMajorNotFound() throws Exception {	
		String[] command = {"search", "event", "major", "aa"};
		String returnstr = null;
		String expected = "";
		expected += "Major not found!\n";
		returnstr = (new CmdSearchEvent()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
}
