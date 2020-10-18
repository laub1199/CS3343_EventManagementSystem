package testEventManagementSystem;

import EventManagementSystem.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCmdGroupQuit {

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
		studentHandler.createStudent(new Student("s00000003", Major.getMajor("ds"), "Mark", "Kam", 'M', 18));
		studentHandler.createStudent(new Student("s00000004", Major.getMajor("ds"), "Ken", "Lee", 'M', 18));

		// create groups
		groupHandler.createGroup(new Group("g00000001", 4));
		groupHandler.createGroup(new Group("g00000002", 3));

		// create events
		eventAllocator.addEvent((Event)new EventIndividual("a-game", "e00000001", 11, new Day("13-oct-2020"), Major.getMajor("cs")));
		eventAllocator.addEvent((Event)new EventGroup("b-game", "e00000002", 6, new Day("13-oct-2020"), Major.getMajor("cs"), 2, 2, 3));
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
	public void testGroupQuitEvent() throws Exception {
		EventAllocator eventAllocator = EventAllocator.getInstance();
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();

		Group group = groupHandler.getGroup("g00000002");
		group.addStudent(studentHandler.getStudent("s00000001"));
		group.addStudent(studentHandler.getStudent("s00000002"));
		group.addStudent(studentHandler.getStudent("s00000003"));

		eventAllocator.studentJoinEvent(studentHandler.getStudent("s00000001"), (EventIndividual)eventAllocator.findEventByID("e00000001"));
		eventAllocator.groupJoinEvent(group, (EventGroup)eventAllocator.findEventByID("e00000002"));

		String[] cmd = {"groupQuit", "g00000002", "e00000002"};
		String result = (new CmdGroupQuit()).execute(cmd);
		String expected = "Group g00000002 has quited event e00000002\n";
		assertEquals(expected, result);
	}

	@Test
	public void testWrongInputA() throws Exception {
		// wrong input on line 11
		String[] cmd = {"groupQuit", "k00000001", "e00000002"};
		String result = (new CmdGroupQuit()).execute(cmd);
		String expected = "Wrong Command\n";
		expected += "Group quit command should be \"groupQuit gXXXXXXXXX eXXXXXXXX\"\n";
		assertEquals(expected, result);
	}

	@Test
	public void testWrongInputB() throws Exception {
		// wrong input on line 15
		String[] cmd = {"groupQuit", "g000000011", "e00000002"};
		String result = (new CmdGroupQuit()).execute(cmd);
		String expected = "Invalid group ID!\n";
		assertEquals(expected, result);
	}

	@Test
	public void testWrongInputC() throws Exception {
		// wrong input on line 22
		String[] cmd = {"groupQuit", "g00000001", "e00000002"};
		String result = (new CmdGroupQuit()).execute(cmd);
		String expected = "Group not found!\n";
		assertEquals(expected, result);
	}

	@Test
	public void testWrongInputD() throws Exception {
		// wrong input on line 30
		String[] cmd = {"groupQuit", "g00000001", "e00000001"};
		String result = (new CmdGroupQuit()).execute(cmd);
		String expected = "Group can only quit Group Event!\n";
		assertEquals(expected, result);
	}
}
