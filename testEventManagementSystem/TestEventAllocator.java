package testEventManagementSystem;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import EventManagementSystem.*;

public class TestEventAllocator {
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

        Exception e = assertThrows(ExEventNotFound.class, () -> eventAllocator.findEventByID("e13579123"));
        assertEquals("Event not found!\n", e.getMessage());
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

        Exception e = assertThrows(ExEventNotFound.class, () -> eventAllocator.findEventByID("e13579123"));
        assertEquals("Event not found!\n", e.getMessage());
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
		expected += "There are no event.";
		result = eventAllocator.listEvent();
		assertEquals(expected, result);
	}
	
}
