package testEventManagementSystem;

import EventManagementSystem.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCmdStudentJoinGroup {

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
		String[] cmd = {"studentJoin", "group", "S00000001", "G00000002"};
		String result = (new CmdStudentJoinGroup()).execute(cmd);
		String expected = "Wrong Command\n" + "Student join group command should be \"studentJoin group sXXXXXXXXX gXXXXXXXX\"\n";
		System.out.print(result);
		System.out.print(expected);
		assertEquals(expected, result);
	}
	
	
	@Test
	public void testInvalidGroupID() throws Exception {
		String[] cmd = {"studentJoin", "group", "s00000002", "g0000003"};
		String result = (new CmdStudentJoinGroup()).execute(cmd);
		String expected = "Invalid group ID!\n";
		assertEquals(expected, result);
	}
	
	
	@Test
	public void testInvalidStudentID() throws Exception {
		String[] cmd = {"studentJoin", "group", "s2", "g00000001"};
		String result = (new CmdStudentJoinGroup()).execute(cmd);
		String expected = "Invalid student ID!\n";
		assertEquals(expected, result);
	}
	
	
	@Test
	public void testNoThisStudentID() throws Exception {
		String[] cmd = {"studentJoin", "group", "s00000005", "g00000001"};
		String result = (new CmdStudentJoinGroup()).execute(cmd);
		String expected = "Student not found!\n";
		assertEquals(expected, result);
	}

	@Test
	public void testNoThisGroup() throws Exception {
		String[] cmd = {"studentJoin", "group", "s00000001", "g00000010"};
		String result = (new CmdStudentJoinGroup()).execute(cmd);
		String expected = "Group not found!\n";
		assertEquals(expected, result);
	}
	
	@Test
	  public void testGroupIsFull() throws Exception {
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();
		Group group = groupHandler.getGroup("g00000002");
		group.addStudent(studentHandler.getStudent("s00000001"));
		group.addStudent(studentHandler.getStudent("s00000002"));
		group.addStudent(studentHandler.getStudent("s00000003"));
		String[] cmd1 = {"studentJoin", "group", "s00000004", "g00000002"};
		String result = (new CmdStudentJoinGroup()).execute(cmd1);
		String expected = "Fail to join the group. The group is full.\n";
		assertEquals(expected, result);
	}

	@Test
	public void testStudentAlreadyJoinedGroup() throws Exception {
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();
		Group group = groupHandler.getGroup("g00000002");
		group.addStudent(studentHandler.getStudent("s00000001"));
		group.addStudent(studentHandler.getStudent("s00000002"));
		group.addStudent(studentHandler.getStudent("s00000003"));
		String[] cmd1 = {"studentJoin", "group", "s00000001", "g00000002"};
		
		String result = (new CmdStudentJoinGroup()).execute(cmd1);
		String expected = "Fail to join the group as you already joined the group.\n";
		assertEquals(expected, result);
	}

	@Test
	public void testStudentJoinGroupWillExceedEventRequirment() throws Exception {
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();
		Group group = groupHandler.getGroup("g00000002");
		group.addStudent(studentHandler.getStudent("s00000001"));
		group.addStudent(studentHandler.getStudent("s00000002"));
		
		String[] cmd1 = {"groupJoin", "event", "g00000002", "e00000002"};
		(new CmdGroupJoin()).execute(cmd1);
		
		String[] cmd = {"studentJoin", "group", "s00000003", "g00000002"};
		String result = (new CmdStudentJoinGroup()).execute(cmd);
		String expected = "Fail to join the group. The group have joined a event already, and the group have reached maximum number requirement of the event.\n";
		assertEquals(expected, result);
	}
	
	@Test
	public void testStudentJoinGroup() throws Exception {
		StudentHandler studentHandler = StudentHandler.getInstance();
		GroupHandler groupHandler = GroupHandler.getInstance();
		Group group = groupHandler.getGroup("g00000002");
		group.addStudent(studentHandler.getStudent("s00000001"));
		String[] cmd1 = {"groupJoin", "event", "g00000002", "e00000002"};
		(new CmdGroupJoin()).execute(cmd1);
		
		String[] cmd = {"studentJoin", "group", "s00000003", "g00000002"};
		String result = (new CmdStudentJoinGroup()).execute(cmd);
		String expected = "You joined the group successfully.\n";
		assertEquals(expected, result);
	}
	


}
