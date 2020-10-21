package testEventManagementSystem;

import EventManagementSystem.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCmdGroupJoin {

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
		groupHandler.createGroup(new Group("g00000003", 3));

		// create events
		eventAllocator.addEvent((Event)new EventIndividual("a-game", "e00000001", 11, new Day("13-oct-2020"), Major.getMajor("cs")));
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
		String[] cmd = {"groupJoin", "event", "G00000002", "e00000003"};
		String result = (new CmdGroupJoin()).execute(cmd);
		String expected = "Wrong Command\n" + "Group join event command should be \"groupJoin event gXXXXXXXXX eXXXXXXXX\"\n";
		assertEquals(expected, result);
	}
	
	@Test
	public void testInvalidEventID() throws Exception {
		String[] cmd = {"groupJoin", "event", "g00000002", "e0000003"};
		String result = (new CmdGroupJoin()).execute(cmd);
		String expected = "Invalid event ID!\n";
		assertEquals(expected, result);
	}
	
	@Test
	public void testInvalidGroupID() throws Exception {
		String[] cmd = {"groupJoin", "event", "g2", "e00000003"};
		String result = (new CmdGroupJoin()).execute(cmd);
		String expected = "Invalid group ID!\n";
		assertEquals(expected, result);
	}
	
	@Test
	public void testNotGroupEvent() throws Exception {
		String[] cmd = {"groupJoin", "event", "g00000001", "e00000001"};
		String result = (new CmdGroupJoin()).execute(cmd);
		String expected = "This is not a group event.\n";
		assertEquals(expected, result);
	}

	@Test
	  public void testGroupEventIsFull() throws Exception {
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();
		Group group1 = groupHandler.getGroup("g00000001");
		group1.addStudent(studentHandler.getStudent("s00000001"));
		String[] cmd1 = {"groupJoin", "event", "g00000001", "e00000002"};
		(new CmdGroupJoin()).execute(cmd1);
		
		Group group2 = groupHandler.getGroup("g00000002");
		group2.addStudent(studentHandler.getStudent("s00000002"));
		String[] cmd2 = {"groupJoin", "event", "g00000002", "e00000002"};
		(new CmdGroupJoin()).execute(cmd2);
		
		Group group3 = groupHandler.getGroup("g00000003");
		group3.addStudent(studentHandler.getStudent("s00000003"));	
		String[] cmd = {"groupJoin", "event", "g00000003", "e00000002"};
		String result = (new CmdGroupJoin()).execute(cmd);
		
		String expected = "Fail to join the event. The number of participated group of this event has reached its maximum.\n";
		assertEquals(expected, result);
	}

	@Test
	public void testGroupAlreadyJoinedEvent() throws Exception {
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();
		Group group = groupHandler.getGroup("g00000001");
		group.addStudent(studentHandler.getStudent("s00000001"));
		group.addStudent(studentHandler.getStudent("s00000002"));
		String[] cmd1 = {"groupJoin", "event", "g00000001", "e00000002"};
		(new CmdGroupJoin()).execute(cmd1);
		
		String[] cmd = {"groupJoin", "event", "g00000001", "e00000002"};
		String result = (new CmdGroupJoin()).execute(cmd);
		String expected = "Fail to join the event as you already joined the event.\n";
		assertEquals(expected, result);
	}
	
	@Test
	public void testGroupJoinEvent() throws Exception {
		EventAllocator eventAllocator = EventAllocator.getInstance();
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();
		Group group = groupHandler.getGroup("g00000001");
		group.addStudent(studentHandler.getStudent("s00000001"));
		group.addStudent(studentHandler.getStudent("s00000002"));
		
		String[] cmd = {"groupJoin", "event", "g00000001", "e00000002"};
		String result = (new CmdGroupJoin()).execute(cmd);
		String expected = "Your group join the event successfully.";
		assertEquals(expected, result);
	}
	
	@Test
	public void testGroupNotEnoughMemberToJoin() throws Exception {
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();
		Group group = groupHandler.getGroup("g00000001");
		
		String[] cmd = {"groupJoin", "event", "g00000001", "e00000002"};
		String result = (new CmdGroupJoin()).execute(cmd);
		String expected = "Fail to join the event. The number of groupmate is less than the required minimum number of event.\n";
		assertEquals(expected, result);
	}
	
	@Test
	public void testGroupTooMuchMemberToJoin() throws Exception {
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();
		Group group = groupHandler.getGroup("g00000001");
		group.addStudent(studentHandler.getStudent("s00000001"));
		group.addStudent(studentHandler.getStudent("s00000002"));
		group.addStudent(studentHandler.getStudent("s00000003"));
		String[] cmd = {"groupJoin", "event", "g00000001", "e00000002"};
		String result = (new CmdGroupJoin()).execute(cmd);
		String expected = "Fail to join the event.The number of groupmate is more than the required maximum number of event.\n";
		assertEquals(expected, result);
	}

	@Test
	public void testInvalidGroupInput() throws Exception {
		String[] cmd = {"groupJoin", "event", "g00000005", "e00000002"};
		String result = (new CmdGroupJoin()).execute(cmd);
		String expected = "Group not found!\n";
		assertEquals(expected, result);
	}

	@Test
	public void testInvalidEventInput() throws Exception {
		String[] cmd = {"groupJoin", "event", "g00000002", "e00000003"};
		String result = (new CmdGroupJoin()).execute(cmd);
		String expected = "Event not found!\n";
		assertEquals(expected, result);
	}

}
