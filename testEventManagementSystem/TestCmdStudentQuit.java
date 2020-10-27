package testEventManagementSystem;

import EventManagementSystem.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCmdStudentQuit {

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
	public void testStudentQuitGroup() throws Exception {
		EventAllocator eventAllocator = EventAllocator.getInstance();
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();

		Group group = groupHandler.getGroup("g00000001");
		group.addStudent(studentHandler.getStudent("s00000001"));

		String[] cmd = {"studentQuit", "s00000001", "g00000001"};
		String result = (new CmdStudentQuit()).execute(cmd);
		String expected = "Student s00000001 has quited group g00000001\n";
		assertEquals(expected, result);
	}

	@Test
	public void testStudentQuitIndividualEvent() throws Exception {
		EventAllocator eventAllocator = EventAllocator.getInstance();
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();
		eventAllocator.studentJoinEvent(studentHandler.getStudent("s00000001"), (EventIndividual)eventAllocator.findEventByID("e00000001"));

		String[] cmd = {"studentQuit", "s00000001", "e00000001"};
		String result = (new CmdStudentQuit()).execute(cmd);
		String expected = "Student s00000001 has quited event e00000001\n";
		assertEquals(expected, result);
	}

	@Test
	public void testStudentQuitJoinedEventGroup1() throws Exception {
		// quit group with group has min of group event
		EventAllocator eventAllocator = EventAllocator.getInstance();
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();

		Group group = groupHandler.getGroup("g00000002");
		group.addStudent(studentHandler.getStudent("s00000001"));
		group.addStudent(studentHandler.getStudent("s00000002"));
		group.addStudent(studentHandler.getStudent("s00000003"));

		eventAllocator.studentJoinEvent(studentHandler.getStudent("s00000001"), (EventIndividual)eventAllocator.findEventByID("e00000001"));
		eventAllocator.groupJoinEvent(group, (EventGroup)eventAllocator.findEventByID("e00000002"));

		String[] cmd = {"studentQuit", "s00000001", "g00000002"};
		String result = (new CmdStudentQuit()).execute(cmd);
		String expected = "Student s00000001 has quited group g00000002\n";
		assertEquals(expected, result);
	}

	@Test
	public void testStudentQuitJoinedEventGroup2() throws Exception {
		// quit group with group does not has min of group event
		EventAllocator eventAllocator = EventAllocator.getInstance();
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();

		Group group = groupHandler.getGroup("g00000002");
		group.addStudent(studentHandler.getStudent("s00000001"));
		group.addStudent(studentHandler.getStudent("s00000002"));

		eventAllocator.studentJoinEvent(studentHandler.getStudent("s00000001"), (EventIndividual)eventAllocator.findEventByID("e00000001"));
		eventAllocator.groupJoinEvent(group, (EventGroup)eventAllocator.findEventByID("e00000002"));

		String[] cmd = {"studentQuit", "s00000001", "g00000002"};
		String result = (new CmdStudentQuit()).execute(cmd);
		String expected = "Student s00000001 has quited group g00000002\n";
		expected += "Group g00000002 with a number of 1 member does not meet the minimum number of 2 student for join event e00000002\n";
		expected += "Group g00000002 is forced to quit event e00000002\n";
		assertEquals(expected, result);
	}

	@Test
	public void testWrongInputA() throws Exception {
		// wrong input on line 12
		String[] cmd = {"studentQuit", "e00000001", "g00000002"};
		String result = (new CmdStudentQuit()).execute(cmd);
		String expected = "Wrong Command\n";
		expected += "Student quit command should be \"studentQuit sXXXXXXXXX gXXXXXXXXX\"\n";
		assertEquals(expected, result);
	}

	@Test
	public void testWrongInputB() throws Exception {
		// wrong input on line 17
		String[] cmd = {"studentQuit", "s000000011", "g00000002"};
		String result = (new CmdStudentQuit()).execute(cmd);
		String expected = "Invalid student ID!\n";
		assertEquals(expected, result);
	}

	@Test
	public void testWrongInputC() throws Exception {
		// wrong input on line 22
		String[] cmd = {"studentQuit", "s00000001", "g000000021"};
		String result = (new CmdStudentQuit()).execute(cmd);
		String expected = "Invalid group ID!\n";
		assertEquals(expected, result);
	}

	@Test
	public void testWrongInputD() throws Exception {
		// wrong input on line 40
		EventAllocator eventAllocator = EventAllocator.getInstance();
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();

		Group group = groupHandler.getGroup("g00000001");
		group.addStudent(studentHandler.getStudent("s00000001"));

		String[] cmd = {"studentQuit", "s00000002", "g00000001"};
		String result = (new CmdStudentQuit()).execute(cmd);
		String expected = "Student not found!\n";
		assertEquals(expected, result);
	}

	@Test
	public void testWrongInputE() throws Exception {
		// wrong input on line 46
		EventAllocator eventAllocator = EventAllocator.getInstance();
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();

		Group group = groupHandler.getGroup("g00000001");
		group.addStudent(studentHandler.getStudent("s00000001"));

		String[] cmd = {"studentQuit", "s00000002", "g00000001"};
		String result = (new CmdStudentQuit()).execute(cmd);
		String expected = "Student not found!\n";
		assertEquals(expected, result);
	}

	@Test
	public void testWrongInputF() throws Exception {
		// wrong input on line 45
		String[] cmd = {"studentQuit", "s00000001", "e000000021"};
		String result = (new CmdStudentQuit()).execute(cmd);
		String expected = "Invalid event ID!\n";
		assertEquals(expected, result);
	}

	@Test
	public void testWrongInputG() throws Exception {
		// wrong input on line 56
		EventAllocator eventAllocator = EventAllocator.getInstance();
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();
		eventAllocator.studentJoinEvent(studentHandler.getStudent("s00000001"), (EventIndividual)eventAllocator.findEventByID("e00000001"));

		String[] cmd = {"studentQuit", "s00000002", "e00000001"};
		String result = (new CmdStudentQuit()).execute(cmd);
		String expected = "Student not found!\n";
		assertEquals(expected, result);
	}

	@Test
	public void testWrongInputH() throws Exception {
		// wrong input on line 62
		String[] cmd = {"studentQuit", "s00000001", "e00000002"};
		String result = (new CmdStudentQuit()).execute(cmd);
		String expected = "As the select event is a group event, please quit the group instead!\n";
		assertEquals(expected, result);
	}
}
