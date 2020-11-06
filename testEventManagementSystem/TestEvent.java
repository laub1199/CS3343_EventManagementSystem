package testEventManagementSystem;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import EventManagementSystem.*;

public class TestEvent {
	private EventAllocator eventAllocator;
	Student student1, student2, student3, student4, student5, student6, student7, student8;
	Major m1;
	Major m2;
    Day eDate;
    
    @BeforeAll
    public static void init() throws ExDateFormatDay, ExDateFormatMonth, ExInvalidDate, ExDateFormatYear {
		SystemDate.createTheInstance("10-oct-2020");    
	}
    
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
	@BeforeEach
	public void setUp() throws Exception {
		eventAllocator = new EventAllocator();
		m1 = Major.getMajor("cs");
		m2 = Major.getMajor("ee");
        eDate = new Day("25-Dec-2020");
		Student student1 = new Student("s12345123",m1, "Mary", "Ng", 'F', 18);
		Student student2 = new Student("s13579123",m1, "Peter", "Chan", 'M', 20);
		Student student3 = new Student("s24680123",m1, "Simon", "Wong", 'M', 19);
		Student student4 = new Student("s54321123",m1, "Polly", "Chan", 'F', 22);
		Student student5 = new Student("s22222123",m2, "Tom", "Chan", 'M', 22);
		Student student6 = new Student("s55555123",m2, "John", "Lee", 'M', 21);
		Student student7 = new Student("s11111123",m2, "May", "Lam", 'F', 22);
		Student student8 = new Student("s33333123",m2, "Chirtin", "Wong", 'F', 18);
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

	//test can get EventGroupID
	@Test
	public void testGetEventGroupID01() {
		String eventID = "e54321123";
		Event event = new EventGroup("Basketball competition", eventID, 90, eDate, m1, 8, 5, 15);
		String result = event.getEventID();
		assertEquals(eventID, result);
	}
	
	//test can get EventIndividualID
	@Test
	public void testGetEventIndividualID01() {
		String eventID = "e12341235";
		Event event = new EventIndividual("robot competition", eventID, 5, eDate, m1);
		String result = event.getEventID();
		assertEquals(eventID, result);
	}
	
	//test can get EventGroup name
	@Test
	public void testGetEventGroupName01() {
		String eventName = "Basketball competition";
		Event event = new EventGroup(eventName, "e54321123", 90, eDate, m1, 8, 5, 15);
		String result = event.getEventName();
		assertEquals(eventName, result);
	}
	
	//test can get EventIndividual name
	@Test
	public void testGetEventIndividualName01() {
		String eventName = "robot competition";
		Event event = new EventIndividual(eventName, "e12341235", 5, eDate, m1);
		String result = event.getEventName();
		assertEquals(eventName, result);
	}
	
	//test can get EventGroup major
	@Test
	public void testGetEventGroupMajor01() throws ExMajorNotFound {
		Major major = Major.getMajor("cs");
		Event event = new EventGroup("Basketball competition", "e54321123", 90, eDate, major, 8, 5, 15);
		Major result = event.getMajor();
		assertEquals(major, result);
	}
	
	//test can get EventIndividual major
	@Test
	public void testGetEventIndividualMajor01() throws ExMajorNotFound {
		Major major = Major.getMajor("cs");
		Event event = new EventIndividual("robot competition", "e12341235", 5, eDate, major);
		Major result = event.getMajor();
		assertEquals(major, result);
	}
	
	//test can get event day
	@Test
	public void testGetEventGroupDate01() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day date = new Day("1-Dec-2020");
		Day result;
		Event event = new EventGroup("Basketball competition", "e54321123", 90, date, m1, 8, 5, 15);
	    result = event.getEventDate();
		assertEquals(date, result);
	}
	
	//test can get event day
	@Test
	public void testGetEventIndividualDate01() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day date = new Day("1-Dec-2020");
		Day result;
		Event event = new EventIndividual("robot competition", "e12341235", 5, date, m1);
	    result = event.getEventDate();
		assertEquals(date, result);
	}
	
	//test can get EventGroup getMajorFullTitle
	@Test
	public void testGetMajorFullTitle01() throws ExMajorNotFound {
		Major major = Major.getMajor("cs");
		Event event = new EventGroup("Basketball competition", "e54321123", 90, eDate, major, 8, 5, 15);
		String result = event.getMajorFullTitle();
		assertEquals("Computer Science", result);
	}
	
	//test can get EventIndividual getMajorFullTitle
	@Test
	public void testGetMajorFullTitle02() throws ExMajorNotFound {
		Major major = Major.getMajor("cs");
		Event event = new EventIndividual("robot competition", "e12341235", 5, eDate, major);
		String result = event.getMajorFullTitle();
		assertEquals("Computer Science", result);
	}
	
	//test can get EventGroup printDetail
	@Test
	public void testPrintDetail01() {
		String expect = String.format("|%-10s|%-30s|%-12s|%-8d|%-30s|%-5d|%-10s|%-15d|%-11d|%-16d|%-16d|\n",
				"e54321123","Basketball competition","25-Dec-2020",90,"Computer Science",90,"Group",8, 8, 5, 15);
		Event event = new EventGroup("Basketball competition", "e54321123", 90, eDate, m1, 8, 5, 15);
		String result = event.printDetail();
		assertEquals(expect, result);
	}
	
	//test can get EventIndividual printDetail
	@Test
	public void testPrintDetail02() {
		String expect = String.format("|%-10s|%-30s|%-12s|%-8d|%-30s|%-5d|%-10s|%-15s|%-11s|%-16s|%-16s|\n",
				"e12341235","robot competition","25-Dec-2020",5,"Computer Science",5,"Individual","/","/","/","/");
		Event event = new EventIndividual("robot competition", "e12341235", 5, eDate, m1);
		String result = event.printDetail();
		assertEquals(expect, result);
	}
	
	//test can add Event
	@Test
	public void testAddStudent01() {	
		int result;
		Event event = new EventIndividual("robot competition", "e12341235", 5, eDate, m1);
		ArrayList<Student> joinedStudentList = event.getStudentList();
		((EventIndividual) event).addStudent(student1);
		result = joinedStudentList.size();
		assertEquals(1, result);
	}
	
	//test can quit Event
	@Test
	public void testQuitStudent01() {	
		int result;
		Event event = new EventIndividual("robot competition", "e12341235", 5, eDate, m1);
		ArrayList<Student> joinedStudentList = event.getStudentList();
		((EventIndividual) event).addStudent(student1);
		((EventIndividual) event).quitStudent(student1);
		result = joinedStudentList.size();
		assertEquals(0, result);
	}
	
	//test get add group
	@Test
	public void testAddGroup01() {
		int result;
		Event event = new EventGroup("Basketball competition", "e54321123", 90, eDate, m1, 8, 5, 15);
		ArrayList<Group> getJoinedGroupList = ((EventGroup) event).getJoinedGroupList();
		Group gp1 = new Group("g12345123", 3);
		((EventGroup) event).addGroup(gp1);
		result = getJoinedGroupList.size();
		assertEquals(1, result);
	}	
	
	//test get quit group
	@Test
	public void testQuitGroup01() {
		int result;
		Event event = new EventGroup("Basketball competition", "e54321123", 90, eDate, m1, 8, 5, 15);
		ArrayList<Group> getJoinedGroupList = ((EventGroup) event).getJoinedGroupList();
		Group gp1 = new Group("g12345123", 3);
		((EventGroup) event).addGroup(gp1);
		((EventGroup) event).quitGroup(gp1);
		result = getJoinedGroupList.size();
		assertEquals(0, result);
	}	
	
	//test get min num in one join
	@Test
	public void testGetMinNumInOneJoin01() {
		int result;
		Event event = new EventGroup("Basketball competition", "e54321123", 90, eDate, m1, 8, 5, 15);
		result = ((EventGroup) event).getMinNumInOneJoin();
		assertEquals(5, result);
	}
	
	//test get max num in one join
		@Test
		public void testGetMaxNumInOneJoin01() {
			int result;
			Event event = new EventGroup("Basketball competition", "e54321123", 90, eDate, m1, 8, 5, 15);
			result = ((EventGroup) event).getMaxNumInOneJoin();
			assertEquals(15, result);
		}

	//test EventIndividual is Full---- cap: 5, added 5 students
	@Test
	public void testEventIndividualIsFull01() {
		boolean result;

		Event event = new EventIndividual("robot competition", "e12341235", 5, eDate, m1);
		eventAllocator.addEvent(event);
		((EventIndividual) event).addStudent(student1);
		((EventIndividual) event).addStudent(student2);
		((EventIndividual) event).addStudent(student3);
		((EventIndividual) event).addStudent(student4);
		((EventIndividual) event).addStudent(student5);

		result = event.isFull();
	    assertEquals(true, result);
	}

	//test EventIndividual is Full---- cap: 5, added 4 students
	@Test
	public void testEventIndividualIsFull02() {
		boolean result;

		Event event = new EventIndividual("robot competition", "e12345123", 5, eDate, m1);
		eventAllocator.addEvent(event);
		((EventIndividual) event).addStudent(student1);
		((EventIndividual) event).addStudent(student2);
		((EventIndividual) event).addStudent(student3);
		((EventIndividual) event).addStudent(student4);

		result = event.isFull();
	    assertEquals(false, result);
	}

	//test EventGroup is Full---- cap: 8, added 8 students, group cap 4, added 3 groups
	@Test
	public void testEventGroupIsFull01() {
		boolean result;

		Event event = new EventGroup("Betmenten competition", "e12345123", 8, eDate, m1, 4, 2, 3);

		Group gp1 = new Group("g12345123", 3);
		gp1.addStudent(student1);
		gp1.addStudent(student2);
		gp1.addStudent(student3);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);

		Group gp2 = new Group("g54321123", 3);
		gp2.addStudent(student4);
		gp2.addStudent(student5);
		gp2.addStudent(student6);
		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);

		Group gp3 = new Group("g13579123", 2);
		gp3.addStudent(student7);
		gp3.addStudent(student8);
		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);

		result = event.isFull();
	    assertEquals(true, result);
	}

	//test EventGroup is Full---- cap: 8, added 7 students, group cap 4, added 3 groups
	@Test
	public void testEventGroupIsFull02() {
		boolean result;

		Event event = new EventGroup("Betmenten competition", "e12345123", 8, eDate, m1, 4, 2, 3);
		eventAllocator.addEvent(event);

		Group gp1 = new Group("g12345123", 2);
		gp1.addStudent(student1);
		gp1.addStudent(student2);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);

		Group gp2 = new Group("g54321123", 2);
		gp2.addStudent(student3);
		gp2.addStudent(student4);
		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);

		Group gp3 = new Group("g13579123", 3);
		gp3.addStudent(student5);
		gp3.addStudent(student6);
		gp3.addStudent(student7);
		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);

		result = event.isFull();
	    assertEquals(true, result);
	}

	//test EventGroup is Full---- cap: 8, added 8 students, group cap 4, added 4 groups
	@Test
	public void testEventGroupIsFull03() {
		boolean result;

		Event event = new EventGroup("Betmenten competition", "e12345123", 8, eDate, m1, 4, 2, 3);

		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345123", 2);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);

		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		Group gp2 = new Group("g54321123", 2);
		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);

		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student5);
		studentList3.add(student6);
		Group gp3 = new Group("g13579123", 2);
		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);

		ArrayList<Student> studentList4 = new ArrayList<>();
		studentList4.add(student7);
		studentList4.add(student8);
		Group gp4 = new Group("g24680123", 2);
		eventAllocator.groupJoinEvent(gp4, (EventGroup) event);

		result = event.isFull();
	    assertEquals(true, result);
	}

	//test EventGroup is Full---- cap: 8, added 6 students, group cap 4, added 3 groups
	@Test
	public void testEventGroupIsFull04() {
		boolean result;

		Event event = new EventGroup("Betmenten competition", "e12345123", 8, eDate, m1, 4, 2, 3);

		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345123", 2);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);

		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		Group gp2 = new Group("g54321123", 2);
		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);

		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student5);
		studentList3.add(student6);
		Group gp3 = new Group("g13579123", 2);
		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);

		result = event.isFull();
	    assertEquals(false, result);
	}
	
	//test EventGroup can get total number of students
	@Test
	public void testEventGroupgetTotalNumOfStudent01() {
		int result;

		Event event = new EventGroup("Betmenten competition", "e12345123", 8, eDate, m1, 4, 2, 3);

		Group gp1 = new Group("g12345123", 2);
		gp1.addStudent(student1);
		gp1.addStudent(student2);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);

		Group gp2 = new Group("g54321123", 2);
		gp2.addStudent(student3);
		gp2.addStudent(student4);
		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);

		Group gp3 = new Group("g13579123", 2);
		gp3.addStudent(student5);
		gp3.addStudent(student6);
		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);

		Group gp4 = new Group("g24680123", 2);
		gp4.addStudent(student7);
		gp4.addStudent(student8);
		eventAllocator.groupJoinEvent(gp4, (EventGroup) event);

		result = ((EventGroup) event).getTotalNumOfStudent();
	    assertEquals(8, result);
	}
	
	//test is student joined
	@Test
	public void testIsStudentJoined01() {
		boolean result;
		Event event = new EventIndividual("Programming course", "e12345123", 50, eDate, m1);
		eventAllocator.studentJoinEvent(student1,(EventIndividual) event);
		result = event.isStudentJoined(student1);
		assertEquals(true, result);
	}
	
	//test is student joined
	@Test
	public void testIsStudentJoined02() {
		boolean result;
		Event event = new EventIndividual("Programming course", "e12345123", 50, eDate, m1);
		result = event.isStudentJoined(student1);
		assertEquals(false, result);
	}
	
	//test get student list
	@Test
	public void testGetStudentList01() {
		ArrayList<Student> result;
		ArrayList<Student> student = new ArrayList<>();
		Event event = new EventIndividual("Programming course", "e12345123", 50, eDate, m1);
		eventAllocator.studentJoinEvent(student1,(EventIndividual) event);
		eventAllocator.studentJoinEvent(student2,(EventIndividual) event);
		student.add(student1);
		student.add(student2);
		result = event.getStudentList();
		assertEquals(student, result);
	}
	
	//test get student list
	@Test
	public void testGetStudentList02() {
		ArrayList<Student> result;
		ArrayList<Student> student = new ArrayList<>();
		Event event = new EventGroup("Betmenten competition", "e12345123", 8, eDate, m1, 4, 2, 3);
		Group gp1 = new Group("g12345123", 2);
		gp1.addStudent(student1);
		gp1.addStudent(student2);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
		student.add(student1);
		student.add(student2);
		result = event.getStudentList();
		assertEquals(student, result);
	}
	
	//test can found group
	@Test
	public void testFoundGroup01() {
		boolean result;
		Event event = new EventGroup("Betmenten competition", "e12345123", 8, eDate, m1, 4, 2, 3);
		Group gp1 = new Group("g12345123", 2);
		gp1.addStudent(student1);
		gp1.addStudent(student2);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
		result = ((EventGroup) event).foundGroup(gp1);
		assertEquals(true, result);
	}
	
	//test cannot found group
	@Test
	public void testFoundGroup02() {
		boolean result;
		Event event = new EventGroup("Betmenten competition", "e12345123", 8, eDate, m1, 4, 2, 3);
		Group gp1 = new Group("g12345123", 2);
		result = ((EventGroup) event).foundGroup(gp1);
		assertEquals(false, result);
	}
	
	//test check Event ID is true
	 @Test
	 public void testCheckEventID01() {
		assertEquals(true, Event.checkEventID("e00000001"));
	 }
	    
	 //test check Event ID is false
	 @Test
	 public void testCheckEventID02() {
		assertEquals(false, Event.checkEventID("e000000001"));
	 }
	 
	 //test check Event ID is false
	 @Test
	 public void testCheckEventID03() {
		 assertEquals(false, Event.checkEventID("e!0000001"));
	 }
	    
	 //test check Event ID is false
	 @Test
	 public void testCheckEventID04() {
		 assertEquals(false, Event.checkEventID("e0000000t"));
	 }
		
}
