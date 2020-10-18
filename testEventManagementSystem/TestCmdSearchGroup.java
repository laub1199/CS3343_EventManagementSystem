package testEventManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import EventManagementSystem.*;

public class TestCmdSearchGroup {
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
		GroupHandler groupHandler = GroupHandler.getInstance();
		groupHandler.getGroupList().clear();
		EventAllocator eventAllocator = EventAllocator.getInstance();
		eventAllocator.getEventList().clear();
		StudentHandler studentHandler = StudentHandler.getInstance();
		studentHandler.getStudentList().clear();
	}
	
	@Test
	public void testNormalInput1() throws Exception {	
		String[] command1 = {"create", "group", "g12345678", "3"};
		String[] command2 = {"search", "group", "g12345678"};
		String returnstr = null;
		String expected = "";
		expected += String.format("|%-10s|%-18s|%-23s|\n","GroupID", "Number Of Students", "Max Number Of Students");
		expected += String.format("|%-10s|%-18d|%-23d|\n","g12345678", 0, 3);
		new CmdCreateGroup().execute(command1);
		returnstr = (new CmdSearchGroup()).execute(command2);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testNormalInput2() throws Exception {	
		String[] command1 = {"create", "group", "g12345678", "3"};
		String[] command2 = {"search", "group", "g12345678"};
		String[] command3 = {"create", "event", "badminton", "e12345678", "10", "20-oct-2020", "cs", "5", "2", "3"};
		String[] command4 = {"groupJoin", "event", "g12345678", "e12345678"};
		String[] command5 = {"create", "student", "s12345678", "cs", "Mary", "Chan", "F", "19"};
		String[] command6 = {"create", "student", "s87654321", "cs", "Tom ", "Chan", "M", "22"};
		String[] command7 = {"studentJoin", "group", "s12345678", "g12345678"};
		String[] command8 = {"studentJoin", "group", "s87654321", "g12345678"};

		String returnstr = null;
		String expected = "";
		expected += String.format("|%-10s|%-18s|%-23s|%-10s|%-30s|\n","GroupID","Number Of Students","Max Number Of Students","EventID","Event Name");
		expected += String.format("|%-10s|%-18d|%-23d|","g12345678", 2, 3);
		expected += String.format("%-10s|%-30s|\n","e12345678" ,"badminton");

		expected += ("Students in group:\n");
		expected += String.format("|%-9s|%-20s|%-20s|%-3s|%-30s|%-3s|\n", "StudentID", "First Name", "Last Name", "Sex", "Major", "Age");
		expected += String.format("|%-9s|%-20s|%-20s|%-3s|%-30s|%-3d|\n", "s12345678", "Mary", "Chan", "F", "Computer Science", 19);
		expected += String.format("|%-9s|%-20s|%-20s|%-3s|%-30s|%-3d|\n", "s87654321", "Tom", "Chan", "M", "Computer Science", 22);

		new CmdCreateGroup().execute(command1);
		new CmdCreateEvent().execute(command3);
		new CmdCreateStudent().execute(command5);
		new CmdCreateStudent().execute(command6);
		new CmdStudentJoinGroup().execute(command7);
		new CmdStudentJoinGroup().execute(command8);
		new CmdGroupJoin().execute(command4);
		returnstr = (new CmdSearchGroup()).execute(command2);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExWrongCommand1() throws Exception {	
		String[] command = {"search", "group", "g12345678", "test"};
		String returnstr = null;
		String expected = "";
		expected += "Wrong Command\n";
		expected += "Search group command should be \"search group gXXXXXXXXX\"\n";
		returnstr = (new CmdSearchGroup()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExWrongCommand2() throws Exception {	
		String[] command = {"search", "group", "e12345678"};
		String returnstr = null;
		String expected = "";
		expected += "Wrong Command\n";
		expected += "Search group command should be \"search group gXXXXXXXXX\"\n";
		returnstr = (new CmdSearchGroup()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExInvalidGroupID1() throws Exception {	
		String[] command = {"search", "group", "g123456"};
		String returnstr = null;
		String expected = "";
		expected += "Invalid group ID!\n";
		returnstr = (new CmdSearchGroup()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExInvalidGroupID2() throws Exception {	
		String[] command = {"search", "group", "g-2345678"};
		String returnstr = null;
		String expected = "";
		expected += "Invalid group ID!\n";
		returnstr = (new CmdSearchGroup()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExInvalidGroupID3() throws Exception {	
		String[] command = {"search", "group", "gtesttest"};
		String returnstr = null;
		String expected = "";
		expected += "Invalid group ID!\n";
		returnstr = (new CmdSearchGroup()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExGroupNotFound() throws Exception {	
		String[] command = {"search", "group", "g12345678"};
		String returnstr = null;
		String expected = "";
		expected += "Group not found!\n";
		returnstr = (new CmdSearchGroup()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
	
	
}
