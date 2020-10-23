package testEventManagementSystem;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import EventManagementSystem.*;

public class TestEvent {
	private EventAllocator eventAllocator;
	Student student1, student2, student3, student4, student5, student6, student7, student8;
	Major m1;
	Major m2;
    Day eDate;
    
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
	@BeforeEach
	public void setUp() throws Exception {
		SystemDate.createTheInstance("10-oct-2020");
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

	//test can find EventIndividual By ID
	@Test
	public void testFindEventIndividualByID01() throws ExEventNotFound {
		Event result = null;
		Event event = new EventIndividual("Programming course", "e12345123", 50, eDate, m1);
		eventAllocator.addEvent(event);
		result = eventAllocator.findEventByID("e12345123");
		assertEquals(event, result);
	}

	//test cannot find EventIndividual By ID
	@Test
	public void testFindEventIndividualByID02() {
		Event result = null;
		Event event = new EventIndividual("Programming course", "e12345123", 50, eDate, m1);
		eventAllocator.addEvent(event);
		try {
			result = eventAllocator.findEventByID("e13579123");
		} catch (ExEventNotFound e) {
			assertEquals(null, result);
		}
	}

	//test can find EventGroup by ID
	@Test
	public void testFindEventGroupByID01() throws ExEventNotFound {
		Event result = null;
		Event event = new EventGroup("Basketball competition", "e54321123", 90, eDate, m1, 8, 5, 15);
		eventAllocator.addEvent(event);
		result = eventAllocator.findEventByID("e54321123");
		assertEquals(event, result);
	}

	//test cannot find EventGroup by ID
	@Test
	public void testFindEventGroupByID02() {
		Event result = null;
		Event event = new EventGroup("Basketball competition", "e54321123", 90, eDate, m1, 8, 5, 15);
		eventAllocator.addEvent(event);
		try {
			result = eventAllocator.findEventByID("e13579123");
		} catch (ExEventNotFound e) {
			assertEquals(null, result);
		}
	}

	//test can find EventGroup by major
	@Test
	public void testFindEventGroupByMajor01() {
		ArrayList<Event> result = new ArrayList<>();
		ArrayList<Event> test = new ArrayList<>();

		Event event1 = new EventGroup("Basketball competition", "e54321123", 90, eDate, m1, 8, 5, 15);
		Event event2 = new EventIndividual("robot competition", "e12345123", 5, eDate, m2);
		Event event3 = new EventIndividual("Football competition", "e54321123", 90, eDate, m1);

		eventAllocator.addEvent(event1);
		eventAllocator.addEvent(event2);
		eventAllocator.addEvent(event3);

		test.add(event1);
		test.add(event3);

		result = eventAllocator.findEventByMajor(m1);
		assertEquals(test, result);

	}

	//test cannot find EventGroup by major
	@Test
	public void testFindEventGroupByMajor02() {
		ArrayList<Event> result = new ArrayList<>();
		ArrayList<Event> test = new ArrayList<>();

		Event event1 = new EventGroup("Basketball competition", "e54321123", 90, eDate, m1, 8, 5, 15);
		Event event2 = new EventIndividual("robot competition", "e12345123", 5, eDate, m1);

		eventAllocator.addEvent(event1);
		eventAllocator.addEvent(event2);

		result = eventAllocator.findEventByMajor(m2);
		assertEquals(test, result);
	}

	//test can find EventGroup By Group
	@Test
	public void testFindEventGroupByGroup01() {
		Event result;
		Event event = new EventGroup("Basketball competition", "e54321123", 90, eDate, m1, 8, 5, 15);
		Group gp1 = new Group("g12345123", 3);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
		eventAllocator.addEvent(event);
		result = eventAllocator.findEventByGroup(gp1);
		assertEquals(event, result);
	}

	//test cannot find EventGroup By Group
	@Test
	public void testFindEventGroupByGroup02() {
		Event result;
		Event event = new EventGroup("Basketball competition", "e54321123", 90, eDate, m1, 8, 5, 15);
		Group gp1 = new Group("g12345123", 3);
		Group gp2 = new Group("g54321123", 4);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
		eventAllocator.addEvent(event);
		result = eventAllocator.findEventByGroup(gp2);
		assertEquals(null, result);
		
	}

	//test can get EventGroupID
	@Test
	public void testGetEventGroupID01() {
		String eventID = "e54321123";
		Event event = new EventGroup("Basketball competition", "e54321123", 90, eDate, m1, 8, 5, 15);
		String result = event.getEventID();
		assertEquals(eventID, result);
	}
	
	//test can get event name
	@Test
	public void testGetEventName01() {
		String eventName = "Basketball competition";
		Event event = new EventGroup("Basketball competition", "e54321123", 90, eDate, m1, 8, 5, 15);
		String result = event.getEventName();
		assertEquals(eventName, result);
	}
	
	//test can get event day
	@Test
	public void testGetEventDate01() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day date = new Day("1-Dec-2020");
		Day result;
		Event event = new EventGroup("Basketball competition", "e54321123", 90, date, m1, 8, 5, 15);
	    result = event.getEventDate();
		assertEquals(date, result);
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

	//test EventGroup is valid to join---- cap: 8, added 8 students, group cap 4, added 3 groups, if add 2 more
	@Test
	public void testEventGroupValidToJoin01() {
		boolean result;

		EventGroup event = new EventGroup("Betmenten competition", "e12345123", 8, eDate, m1, 4, 2, 3);

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

		result = ((EventGroup) event).validToJoin(2);
	    assertEquals(false, result);
	}

	//test EventGroup is valid to join---- cap: 8, added 7 students, group cap 4, added 3 groups, if add 2 more
	@Test
	public void testEventGroupValidToJoin02() {
		boolean result;

		Event event = new EventGroup("Betmenten competition", "e12345123", 8, eDate, m1, 4, 2, 3);

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

		result = ((EventGroup) event).validToJoin(2);
	    assertEquals(false, result);
	}

	//test EventGroup is valid to join---- cap: 8, added 8 students, group cap 4, added 4 groups, if add 2 more
	@Test
	public void testEventGroupValidToJoin03() {
		boolean result;

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

		result = ((EventGroup) event).validToJoin(2);
	    assertEquals(false, result);
	}

	//test EventGroup is valid to join---- cap: 8, added 6 students, group cap 4, added 3 groups, if add 2 more
	@Test
	public void testEventGroupValidToJoin04() {
		boolean result;

		Event event = new EventGroup("Betmenten competition", "e12345123", 8, eDate, m1, 4, 2, 3);
		eventAllocator.addEvent(event);

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

		result = ((EventGroup) event).validToJoin(2);
	    assertEquals(true, result);
	}

	//test EventGroup is valid to join---- cap: 8, added 6 students, group cap 4, added 3 groups, if add 3 more
	@Test
	public void testEventGroupValidToJoin05() {
		boolean result;

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

		result = ((EventGroup) event).validToJoin(3);
	    assertEquals(false, result);
	}

	//test EventGroup is valid to join---- cap: 8, added 5 students, group cap 4, added 2 groups, if add 3 more
	@Test
	public void testEventGroupValidToJoin06() {
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
		studentList2.add(student5);
		Group gp2 = new Group("g54321123", 3);
		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);

		result = ((EventGroup) event).validToJoin(3);
	    assertEquals(true, result);
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
	
	//test can add event
		@Test
		public void testAddEvent01() {
			Event result;
			ArrayList<Event> eventList = eventAllocator.getEventList();
			Event event = new EventIndividual("Programming course", "e12345123", 50, eDate, m1);
			eventAllocator.addEvent(event);
			result = event;
			assertEquals(eventList.get(0), result);
		}
	
	//test can delete event
	@Test
	public void testDeleteEvent01() {
		int result;
		ArrayList<Event> eventList = eventAllocator.getEventList();
		Event event = new EventIndividual("Programming course", "e12345123", 50, eDate, m1);
		eventAllocator.addEvent(event);
		eventAllocator.deleteEvent(event);
		result = 0;
		assertEquals(eventList.size(), result);
	}
	
	//test student can join event
	@Test
	public void testStudentJoinEvent01() {
		Student result;
		Event event = new EventIndividual("Programming course", "e12345123", 50, eDate, m1);
		eventAllocator.studentJoinEvent(student1,(EventIndividual) event);
		ArrayList<Student> joinedStudentList = event.getStudentList();
		result = student1;
		assertEquals(joinedStudentList.get(0), result);
	}
	
	//test student can quit event
	@Test
	public void testStudentQuitEvent01() {
		int result;
		Event event = new EventIndividual("Programming course", "e12345123", 50, eDate, m1);
		eventAllocator.studentJoinEvent(student1,(EventIndividual) event);
		eventAllocator.studentQuitEvent(student1,(EventIndividual) event);
		ArrayList<Student> joinedStudentList = event.getStudentList();
		result = 0;
		assertEquals(joinedStudentList.size(), result);
	}
	
	//test group can join event
	@Test
	public void testGroupJoinEvent01() {
		Group result;
		Event event = new EventGroup("Betmenten competition", "e12345123", 8, eDate, m1, 4, 2, 3);
		Group gp1 = new Group("g12345123", 2);
		gp1.addStudent(student1);
		gp1.addStudent(student2);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
		ArrayList<Group> getJoinedGroupList = ((EventGroup) event).getJoinedGroupList();
		result = gp1;
		assertEquals(getJoinedGroupList.get(0), result);
	}
	
	//test group can quit event
	@Test
	public void testGroupQuitEvent01() {
		int result;
		Event event = new EventGroup("Betmenten competition", "e12345123", 8, eDate, m1, 4, 2, 3);
		Group gp1 = new Group("g12345123", 2);
		gp1.addStudent(student1);
		gp1.addStudent(student2);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
		eventAllocator.groupQuitEvent(gp1, (EventGroup) event);
		ArrayList<Group> getJoinedGroupList = ((EventGroup) event).getJoinedGroupList();
		result = 0;
		assertEquals(getJoinedGroupList.size(), result);
	}
	
	//test can list event
	@Test
	public void testlistEvent01() {
		String result = "";
		String expected = "";
		EventAllocator eventAllocator = EventAllocator.getInstance();
		Event event = new EventGroup("Betmenten competition", "e54321123", 8, eDate, m1, 4, 2, 3);
		Event event2 = new EventIndividual("Programming course", "e12345123", 50, eDate, m1);
		eventAllocator.addEvent(event);
		eventAllocator.addEvent(event2);
		expected += String.format("|%-10s|%-30s|%-12s|%-8s|%-30s|%-5s|%-10s|%-15s|%-11s|%-16s|%-16s|\n",
				"Event ID","Event Name","Date","Capacity","Major","Quota","Type","Group Capacity","Group Quota","Min No. In Group","Max No. In Group");
		expected += String.format("|%-10s|%-30s|%-12s|%-8d|%-30s|%-5d|%-10s|%-15d|%-11d|%-16d|%-16d|\n",
				"e54321123","Betmenten competition","25-Dec-2020",8,"Computer Science",8,"Group",4,4,2,3);
		expected += String.format("|%-10s|%-30s|%-12s|%-8d|%-30s|%-5d|%-10s|%-15s|%-11s|%-16s|%-16s|\n",
				"e12345123","Programming course","25-Dec-2020",50,"Computer Science",50,"Individual","/","/","/","/");
		result = eventAllocator.listEvent();
		assertEquals(expected, result);
	}
	
	//test can list event
	@Test
	public void testlistEvent02() {
		String result = "";
		String expected = "";
		EventAllocator eventAllocator = EventAllocator.getInstance();
		expected += "There are no event.";
		result = eventAllocator.listEvent();
		assertEquals(expected, result);
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
		

}
