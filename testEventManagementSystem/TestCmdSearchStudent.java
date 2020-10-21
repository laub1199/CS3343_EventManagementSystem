package testEventManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import EventManagementSystem.*;

public class TestCmdSearchStudent {
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
	@BeforeEach
	public void setUp() throws Exception {}
    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
	@AfterEach
	public void tearDown() throws Exception {
		StudentHandler studentHandler = StudentHandler.getInstance();
		studentHandler.getStudentList().clear();
	}
	
	@Test
	public void testNormalInput() throws Exception {
		String[] command1 = {"create", "student", "s12345678", "cs", "Mary", "Chan", "F", "19"};
		String[] command2 = {"search", "student", "s12345678"};
		String returnstr = null;
		String expected = "";
		expected += String.format("|%-9s|%-20s|%-20s|%-3s|%-30s|%-3s|\n", "StudentID", "First Name", "Last Name", "Sex", "Major", "Age");
		expected += String.format("|%-9s|%-20s|%-20s|%-3s|%-30s|%-3s|\n", "s12345678", "Mary", "Chan", "F", "Computer Science", "19");
		new CmdCreateStudent().execute(command1);
		returnstr = (new CmdSearchStudent()).execute(command2);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExWrongCommand1() throws Exception {
		String[] command = {"search", "student", "e12345678"};
		String returnstr = null;
		String expected = "";
		expected += "Wrong Command\n";
		expected += "Search student command should be \"search student sXXXXXXXXX\"\n";
		returnstr = (new CmdSearchStudent()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExWrongCommand2() throws Exception {
		String[] command = {"search", "student", "s12345678", "test"};
		String returnstr = null;
		String expected = "";
		expected += "Wrong Command\n";
		expected += "Search student command should be \"search student sXXXXXXXXX\"\n";
		returnstr = (new CmdSearchStudent()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExInvalidStudentID1() throws Exception {
		String[] command = {"search", "student", "s123456"};
		String returnstr = null;
		String expected = "";
		expected += "Invalid student ID!\n";
		returnstr = (new CmdSearchStudent()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExInvalidStudentID2() throws Exception {
		String[] command = {"search", "student", "s-2345678"};
		String returnstr = null;
		String expected = "";
		expected +="Invalid student ID!\n";
		returnstr = (new CmdSearchStudent()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExInvalidStudentID3() throws Exception {
		String[] command = {"search", "student", "stesttest"};
		String returnstr = null;
		String expected = "";
		expected += "Invalid student ID!\n";
		returnstr = (new CmdSearchStudent()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
	@Test
	public void testExStudentNotFound() throws Exception {
		String[] command = {"search", "student", "s12345678"};
		String returnstr = null;
		String expected = "";
		expected += "Student not found!\n";
		returnstr = (new CmdSearchStudent()).execute(command);	
		assertEquals(expected, returnstr);
	}
	
}
