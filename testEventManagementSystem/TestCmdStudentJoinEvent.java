package testEventManagementSystem;

import EventManagementSystem.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCmdStudentJoinEvent {

	/**
	 * Sets up the test fixture.
	 * Called before every test case method.
	 */
	@BeforeEach
	public void setUp() throws Exception {
		SystemDate.createTheInstance("10-oct-2020");
		EventAllocator eventAllocator = EventAllocator.getInstance();
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();

		// create students
		studentHandler.createStudent(new Student("s00000001", Major.getMajor("cs"), "Sam", "Chow", 'M', 18));
		studentHandler.createStudent(new Student("s00000002", Major.getMajor("cs"), "Peter", "Chan", 'M', 18));
		studentHandler.createStudent(new Student("s00000003", Major.getMajor("cs"), "Mark", "Kam", 'M', 18));
		studentHandler.createStudent(new Student("s00000004", Major.getMajor("ds"), "Ken", "Lee", 'M', 18));

		// create groups
		groupHandler.createGroup(new Group("g00000001", 4));
		groupHandler.createGroup(new Group("g00000002", 3));

		// create events
		eventAllocator.addEvent((Event)new EventIndividual("a-game", "e00000001", 3, new Day("13-oct-2020"), Major.getMajor("cs")));
		eventAllocator.addEvent((Event)new EventGroup("b-game", "e00000002", 6, new Day("13-oct-2020"), Major.getMajor("cs"), 2, 1, 2));
	}
	/**
	 * Tears down the test fixture.
	 * Called after every test case method.
	 */
	@AfterEach
	public void tearDown() throws Exception {
		EventAllocator eventAllocator = EventAllocator.getInstance();
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();
		eventAllocator.getEventList().clear();
		studentHandler.getStudentList().clear();
		groupHandler.getGroupList().clear();
	}

	@Test
	public void testWrongCommad() throws Exception {
		String[] cmd = {"studentJoin", "event", "S00000002", "e00000001"};
		String result = (new CmdStudentJoinEvent()).execute(cmd);
		String expected = "Wrong Command\n" + "Student join event command should be \"studentJoin event sXXXXXXXXX eXXXXXXXX\"\n";
		assertEquals(expected, result);
	}
	
	@Test
	public void testInvalidEventID() throws Exception {
		String[] cmd = {"studentJoin", "event", "s00000002", "e0000003"};
		String result = (new CmdStudentJoinEvent()).execute(cmd);
		String expected = "Invalid event ID!\n";
		assertEquals(expected, result);
	}
	
	@Test
	public void testInvalidStudentID() throws Exception {
		String[] cmd = {"studentJoin", "event", "s2", "e00000001"};
		String result = (new CmdStudentJoinEvent()).execute(cmd);
		String expected = "Invalid student ID!\n";
		assertEquals(expected, result);
	}
	
	@Test
	public void testNotIndividualEvent() throws Exception {
		String[] cmd = {"studentJoin", "event", "s00000001", "e00000002"};
		String result = (new CmdStudentJoinEvent()).execute(cmd);
		String expected = "This is not a individual event.\n";
		assertEquals(expected, result);
	}

	@Test
	  public void testIndividualEventIsFull() throws Exception {
		StudentHandler studentHandler = StudentHandler.getInstance();
		String[] cmd1 = {"studentJoin", "event", "s00000001", "e00000001"};
		(new CmdStudentJoinEvent()).execute(cmd1);
		String[] cmd2 = {"studentJoin", "event", "s00000002", "e00000001"};
		(new CmdStudentJoinEvent()).execute(cmd2);
		String[] cmd3 = {"studentJoin", "event", "s00000003", "e00000001"};
		(new CmdStudentJoinEvent()).execute(cmd3);
		
		String[] cmd = {"studentJoin", "event", "s00000004", "e00000001"};
		String result = (new CmdStudentJoinEvent()).execute(cmd);
		String expected = "Fail to join the event. The number of participant of this event has reached its maximum.\n";
		assertEquals(expected, result);
	}

	@Test
	public void testIndividualAlreadyJoinedEvent() throws Exception {
		StudentHandler studentHandler = StudentHandler.getInstance();
		String[] cmd1 = {"studentJoin", "event", "s00000001", "e00000001"};
		(new CmdStudentJoinEvent()).execute(cmd1);
		
		String[] cmd = {"studentJoin", "event", "s00000001", "e00000001"};
		String result = (new CmdStudentJoinEvent()).execute(cmd);
		String expected = "Fail to join the event as you already joined the event.\n";
		assertEquals(expected, result);
	}
	
	@Test
	public void testStudentJoinEvent() throws Exception {
			
		String[] cmd = {"studentJoin", "event", "s00000001", "e00000001"};
		String result = (new CmdStudentJoinEvent()).execute(cmd);
		String expected = "You join the event successfully.\n";
		assertEquals(expected, result);
	}


	@Test
	public void testInvalidEventInput() throws Exception {
		String[] cmd = {"studentJoin", "event", "s00000001", "e00000003"};
		String result = (new CmdStudentJoinEvent()).execute(cmd);
		String expected = "Event not found!\n";
		assertEquals(expected, result);
	}
	
	

}
