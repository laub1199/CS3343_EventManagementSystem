package testEventManagementSystem;//package testEventManagementSystem;
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//import java.util.Date;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import EventManagementSystem.*;
//
//public class TestEvent {
//	private EventAllocator eventAllocator;
//	Student student1, student2, student3, student4, student5, student6, student7, student8;
//    /**
//     * Sets up the test fixture.
//     * Called before every test case method.
//     */
//	@BeforeEach
//	public void setUp() throws Exception {
//		eventAllocator = new EventAllocator();
//		Student student1 = new Student("s12345123","CS", "Mary", "Ng", 'F', 18);
//		Student student2 = new Student("s13579123","CS", "Peter", "Chan", 'M', 20);
//		Student student3 = new Student("s24680123","CS", "Simon", "Wong", 'M', 19);
//		Student student4 = new Student("s54321123","CS", "Polly", "Chan", 'F', 22);
//		Student student5 = new Student("s22222123","CS", "Tom", "Chan", 'M', 22);
//		Student student6 = new Student("s55555123","DS", "John", "Lee", 'M', 21);
//		Student student7 = new Student("s11111123","Sci", "May", "Lam", 'F', 22);
//		Student student8 = new Student("s33333123","Sci", "Chirtin", "Wong", 'F', 18);
//	}
//    /**
//     * Tears down the test fixture.
//     * Called after every test case method.
//     */
//	public void tearDown() {}
//
//	//test can find EventIndividual By ID
//	@Test
//	public void testFindEventIndividualByID01() {
//		Event result = null;
//		Event event = new EventIndividual("Programming course", "e12345123", 50, new Date(), "CS");
//		eventAllocator.addEvent(event);
//		try {
//			result = eventAllocator.findEventByID("e12345");
//			assertEquals(event, result);
//		} catch (ExEventNotFound e) {
//			System.out.println("Wrong test case.");
//		}
//	}
//
//	//test cannot find EventIndividual By ID
//	@Test
//	public void testFindEventIndividualByID02() {
//		Event result = null;
//		Event event = new EventIndividual("Programming course", "e12345123", 50, new Date(), "CS");
//		eventAllocator.addEvent(event);
//		try {
//			result = eventAllocator.findEventByID("e13579123");
//
//			System.out.println("Wrong test case.");
//		} catch (ExEventNotFound e) {
//			assertEquals(null, result);
//		}
//	}
//
//	//test can find EventGroup by ID
//	@Test
//	public void testFindEventGroupByID01() {
//		Event result = null;
//		Event event = new EventGroup("Basketball competition", "e54321123", 90, new Date(), "CS", 8, 5, 15);
//		eventAllocator.addEvent(event);
//		try {
//			result = eventAllocator.findEventByID("e54321123");
//			assertEquals(event, result);
//		} catch (ExEventNotFound e) {
//			System.out.println("Wrong test case.");
//		}
//	}
//
//	//test cannot find EventGroup by ID
//	@Test
//	public void testFindEventGroupByID02() {
//		Event result = null;
//		Event event = new EventGroup("Basketball competition", "e54321123", 90, new Date(), "CS", 8, 5, 15);
//		eventAllocator.addEvent(event);
//		try {
//			result = eventAllocator.findEventByID("e13579123");
//
//			System.out.println("Wrong test case.");
//		} catch (ExEventNotFound e) {
//			assertEquals(null, result);
//		}
//	}
//
//	//test can find EventGroup by major
//	@Test
//	public void testFindEventGroupByMajor01() {
//		ArrayList<Event> result = new ArrayList<>();
//		ArrayList<Event> test = new ArrayList<>();
//
//		Event event1 = new EventGroup("Basketball competition", "e54321123", 90, new Date(), "CS", 8, 5, 15);
//		Event event2 = new EventIndividual("robot competition", "e12345123", 5, new Date(), "EE");
//		Event event3 = new EventIndividual("Football competition", "e54321123", 90, new Date(), "CS");
//
//		eventAllocator.addEvent(event1);
//		eventAllocator.addEvent(event2);
//		eventAllocator.addEvent(event3);
//
//		test.add(event1);
//		test.add(event3);
//
//		result = eventAllocator.findEventByMajor("CS");
//		assertEquals(test, result);
//
//	}
//
//	//test cannot find EventGroup by major
//	@Test
//	public void testFindEventGroupByMajor02() {
//		ArrayList<Event> result = new ArrayList<>();
//		ArrayList<Event> test = new ArrayList<>();
//
//		Event event1 = new EventGroup("Basketball competition", "e54321123", 90, new Date(), "CS", 8, 5, 15);
//		Event event2 = new EventIndividual("robot competition", "e12345123", 5, new Date(), "CS");
//
//		eventAllocator.addEvent(event1);
//		eventAllocator.addEvent(event2);
//
//		result = eventAllocator.findEventByMajor("EE");
//		assertEquals(test, result);
//	}
//
//	//test can find EventGroup By Group
//	@Test
//	public void testFindEventGroupByGroup01() {
//		Event result = null;
//		Event event = new EventGroup("Basketball competition", "e54321123", 90, new Date(), "CS", 8, 5, 15);
//		Group gp1 = new Group("g12345123", 3);
//		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
//		eventAllocator.addEvent(event);
//		try {
//			result = eventAllocator.findEventByGroup(gp1);
//			assertEquals(event, result);
//		} catch (ExEventNotFound | ExGroupNotFound e) {
//			System.out.println("Wrong test case.");
//		}
//	}
//
//	//test cannot find EventGroup By Group
//	@Test
//	public void testFindEventGroupByGroup02() {
//		Event result = null;
//		Event event = new EventGroup("Basketball competition", "e54321123", 90, new Date(), "CS", 8, 5, 15);
//		Group gp1 = new Group("g12345123", 3);
//		Group gp2 = new Group("g54321123", 4);
//		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
//		eventAllocator.addEvent(event);
//		try {
//			result = eventAllocator.findEventByGroup(gp2);
//
//			System.out.println("Wrong test case.");
//		} catch (ExEventNotFound | ExGroupNotFound e) {
//			assertEquals(null, result);
//		}
//	}
//
//	//test can get EventGroupID
//	@Test
//	public void testGetEventGroupID01() {
//		String eventID = "e54321123";
//		Event event = new EventGroup("Basketball competition", eventID, 90, new Date(), "CS", 8, 5, 15);
//		String result = event.getEventID();
//		assertEquals(eventID, result);
//	}
//
//	//test EventIndividual is Full---- cap: 5, added 5 students
//	@Test
//	public void testEventIndividualIsFull01() {
//		boolean result;
//
//		Event event = new EventIndividual("robot competition", "e12341235", 5, new Date(), "CS");
//		eventAllocator.addEvent(event);
//		((EventIndividual) event).addStudent(student1);
//		((EventIndividual) event).addStudent(student2);
//		((EventIndividual) event).addStudent(student3);
//		((EventIndividual) event).addStudent(student4);
//		((EventIndividual) event).addStudent(student5);
//
//		result = event.isFull();
//	    assertEquals(true, result);
//	}
//
//	//test EventIndividual is Full---- cap: 5, added 4 students
//	@Test
//	public void testEventIndividualIsFull02() {
//		boolean result;
//
//		Event event = new EventIndividual("robot competition", "e12345123", 5, new Date(), "CS");
//		eventAllocator.addEvent(event);
//		((EventIndividual) event).addStudent(student1);
//		((EventIndividual) event).addStudent(student2);
//		((EventIndividual) event).addStudent(student3);
//		((EventIndividual) event).addStudent(student4);
//
//		result = event.isFull();
//	    assertEquals(false, result);
//	}
//
//	//test EventGroup is Full---- cap: 8, added 8 students, group cap 4, added 3 groups
//	@Test
//	public void testEventGroupIsFull01() {
//		boolean result;
//
//		Event event = new EventGroup("Betmenten competition", "e12345123", 8, new Date(), "CS", 4, 2, 3);
//
//		Group gp1 = new Group("g12345123", 3);
//		gp1.addStudent(student1);
//		gp1.addStudent(student2);
//		gp1.addStudent(student3);
//		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
//
//		Group gp2 = new Group("g54321123", 3);
//		gp2.addStudent(student4);
//		gp2.addStudent(student5);
//		gp2.addStudent(student6);
//		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
//
//		Group gp3 = new Group("g13579123", 2);
//		gp3.addStudent(student7);
//		gp3.addStudent(student8);
//		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);
//
//		result = event.isFull();
//	    assertEquals(true, result);
//	}
//
//	//test EventGroup is Full---- cap: 8, added 7 students, group cap 4, added 3 groups
//	@Test
//	public void testEventGroupIsFull02() {
//		boolean result;
//
//		Event event = new EventGroup("Betmenten competition", "e12345123", 8, new Date(), "CS", 4, 2, 3);
//		eventAllocator.addEvent(event);
//
//		Group gp1 = new Group("g12345123", 2);
//		gp1.addStudent(student1);
//		gp1.addStudent(student2);
//		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
//
//		Group gp2 = new Group("g54321123", 2);
//		gp2.addStudent(student3);
//		gp2.addStudent(student4);
//		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
//
//		Group gp3 = new Group("g13579123", 3);
//		gp3.addStudent(student5);
//		gp3.addStudent(student6);
//		gp3.addStudent(student7);
//		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);
//
//		result = event.isFull();
//	    assertEquals(true, result);
//	}
//
//	//test EventGroup is Full---- cap: 8, added 8 students, group cap 4, added 4 groups
//	@Test
//	public void testEventGroupIsFull03() {
//		boolean result;
//
//		Event event = new EventGroup("Betmenten competition", "e12345123", 8, new Date(), "CS", 4, 2, 3);
//
//		ArrayList<Student> studentList1 = new ArrayList<>();
//		studentList1.add(student1);
//		studentList1.add(student2);
//		Group gp1 = new Group("g12345123", 2);
//		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
//
//		ArrayList<Student> studentList2 = new ArrayList<>();
//		studentList2.add(student3);
//		studentList2.add(student4);
//		Group gp2 = new Group("g54321123", 2);
//		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
//
//		ArrayList<Student> studentList3 = new ArrayList<>();
//		studentList3.add(student5);
//		studentList3.add(student6);
//		Group gp3 = new Group("g13579123", 2);
//		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);
//
//		ArrayList<Student> studentList4 = new ArrayList<>();
//		studentList4.add(student7);
//		studentList4.add(student8);
//		Group gp4 = new Group("g24680123", 2);
//		eventAllocator.groupJoinEvent(gp4, (EventGroup) event);
//
//		result = event.isFull();
//	    assertEquals(true, result);
//	}
//
//	//test EventGroup is Full---- cap: 8, added 6 students, group cap 4, added 3 groups
//	@Test
//	public void testEventGroupIsFull04() {
//		boolean result;
//
//		Event event = new EventGroup("Betmenten competition", "e12345123", 8, new Date(), "CS", 4, 2, 3);
//
//		ArrayList<Student> studentList1 = new ArrayList<>();
//		studentList1.add(student1);
//		studentList1.add(student2);
//		Group gp1 = new Group("g12345123", 2);
//		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
//
//		ArrayList<Student> studentList2 = new ArrayList<>();
//		studentList2.add(student3);
//		studentList2.add(student4);
//		Group gp2 = new Group("g54321123", 2);
//		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
//
//		ArrayList<Student> studentList3 = new ArrayList<>();
//		studentList3.add(student5);
//		studentList3.add(student6);
//		Group gp3 = new Group("g13579123", 2);
//		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);
//
//		result = event.isFull();
//	    assertEquals(false, result);
//	}
//
//	//test EventGroup is valid to join---- cap: 8, added 8 students, group cap 4, added 3 groups, if add 2 more
//	@Test
//	public void testEventGroupValidToJoin01() {
//		boolean result;
//
//		EventGroup event = new EventGroup("Betmenten competition", "e12345123", 8, new Date(), "CS", 4, 2, 3);
//
//		Group gp1 = new Group("g12345123", 3);
//		gp1.addStudent(student1);
//		gp1.addStudent(student2);
//		gp1.addStudent(student3);
//
//		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
//
//		Group gp2 = new Group("g54321123", 3);
//		gp2.addStudent(student4);
//		gp2.addStudent(student5);
//		gp2.addStudent(student6);
//		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
//
//		Group gp3 = new Group("g13579123", 2);
//		gp3.addStudent(student7);
//		gp3.addStudent(student8);
//		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);
//
//		result = ((EventGroup) event).validToJoin(2);
//	    assertEquals(false, result);
//	}
//
//	//test EventGroup is valid to join---- cap: 8, added 7 students, group cap 4, added 3 groups, if add 2 more
//	@Test
//	public void testEventGroupValidToJoin02() {
//		boolean result;
//
//		Event event = new EventGroup("Betmenten competition", "e12345123", 8, new Date(), "CS", 4, 2, 3);
//
//		Group gp1 = new Group("g12345123", 2);
//		gp1.addStudent(student1);
//		gp1.addStudent(student2);
//		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
//
//		Group gp2 = new Group("g54321123", 2);
//		gp2.addStudent(student3);
//		gp2.addStudent(student4);
//		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
//
//		Group gp3 = new Group("g13579123", 3);
//		gp3.addStudent(student5);
//		gp3.addStudent(student6);
//		gp3.addStudent(student7);
//		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);
//
//		result = ((EventGroup) event).validToJoin(2);
//	    assertEquals(false, result);
//	}
//
//	//test EventGroup is valid to join---- cap: 8, added 8 students, group cap 4, added 4 groups, if add 2 more
//	@Test
//	public void testEventGroupValidToJoin03() {
//		boolean result;
//
//		Event event = new EventGroup("Betmenten competition", "e12345123", 8, new Date(), "CS", 4, 2, 3);
//
//		Group gp1 = new Group("g12345123", 2);
//		gp1.addStudent(student1);
//		gp1.addStudent(student2);
//		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
//
//		Group gp2 = new Group("g54321123", 2);
//		gp2.addStudent(student3);
//		gp2.addStudent(student4);
//		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
//
//		Group gp3 = new Group("g13579123", 2);
//		gp3.addStudent(student5);
//		gp3.addStudent(student6);
//		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);
//
//		Group gp4 = new Group("g24680123", 2);
//		gp4.addStudent(student7);
//		gp4.addStudent(student8);
//		eventAllocator.groupJoinEvent(gp4, (EventGroup) event);
//
//		result = ((EventGroup) event).validToJoin(2);
//	    assertEquals(false, result);
//	}
//
//	//test EventGroup is valid to join---- cap: 8, added 6 students, group cap 4, added 3 groups, if add 2 more
//	@Test
//	public void testEventGroupValidToJoin04() {
//		boolean result;
//
//		Event event = new EventGroup("Betmenten competition", "e12345123", 8, new Date(), "CS", 4, 2, 3);
//		eventAllocator.addEvent(event);
//
//		ArrayList<Student> studentList1 = new ArrayList<>();
//		studentList1.add(student1);
//		studentList1.add(student2);
//		Group gp1 = new Group("g12345123", 2);
//		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
//
//		ArrayList<Student> studentList2 = new ArrayList<>();
//		studentList2.add(student3);
//		studentList2.add(student4);
//		Group gp2 = new Group("g54321123", 2);
//		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
//
//		ArrayList<Student> studentList3 = new ArrayList<>();
//		studentList3.add(student5);
//		studentList3.add(student6);
//		Group gp3 = new Group("g13579123", 2);
//		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);
//
//		result = ((EventGroup) event).validToJoin(2);
//	    assertEquals(true, result);
//	}
//
//	//test EventGroup is valid to join---- cap: 8, added 6 students, group cap 4, added 3 groups, if add 3 more
//	@Test
//	public void testEventGroupValidToJoin05() {
//		boolean result;
//
//		Event event = new EventGroup("Betmenten competition", "e12345123", 8, new Date(), "CS", 4, 2, 3);
//
//		Group gp1 = new Group("g12345123", 2);
//		gp1.addStudent(student1);
//		gp1.addStudent(student2);
//		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
//
//		Group gp2 = new Group("g54321123", 2);
//		gp2.addStudent(student3);
//		gp2.addStudent(student4);
//		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
//
//		Group gp3 = new Group("g13579123", 2);
//		gp3.addStudent(student5);
//		gp3.addStudent(student6);
//		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);
//
//		result = ((EventGroup) event).validToJoin(3);
//	    assertEquals(false, result);
//	}
//
//	//test EventGroup is valid to join---- cap: 8, added 5 students, group cap 4, added 2 groups, if add 3 more
//	@Test
//	public void testEventGroupValidToJoin06() {
//		boolean result;
//
//		Event event = new EventGroup("Betmenten competition", "e12345123", 8, new Date(), "CS", 4, 2, 3);
//
//		ArrayList<Student> studentList1 = new ArrayList<>();
//		studentList1.add(student1);
//		studentList1.add(student2);
//		Group gp1 = new Group("g12345123", 2);
//		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
//
//		ArrayList<Student> studentList2 = new ArrayList<>();
//		studentList2.add(student3);
//		studentList2.add(student4);
//		studentList2.add(student5);
//		Group gp2 = new Group("g54321123", 3);
//		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
//
//		result = ((EventGroup) event).validToJoin(3);
//	    assertEquals(true, result);
//	}
//
//	//test EventGroup can get total number of students
//	@Test
//	public void testEventGroupgetTotalNumOfStudent01() {
//		int result;
//
//		Event event = new EventGroup("Betmenten competition", "e12345123", 8, new Date(), "CS", 4, 2, 3);
//
//		Group gp1 = new Group("g12345123", 2);
//		gp1.addStudent(student1);
//		gp1.addStudent(student2);
//		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
//
//		Group gp2 = new Group("g54321123", 2);
//		gp2.addStudent(student3);
//		gp2.addStudent(student4);
//		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
//
//		Group gp3 = new Group("g13579123", 2);
//		gp3.addStudent(student5);
//		gp3.addStudent(student6);
//		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);
//
//		Group gp4 = new Group("g24680123", 2);
//		gp4.addStudent(student7);
//		gp4.addStudent(student8);
//		eventAllocator.groupJoinEvent(gp4, (EventGroup) event);
//
//		result = ((EventGroup) event).getTotalNumOfStudent();
//	    assertEquals(8, result);
//	}
//
//
//}
