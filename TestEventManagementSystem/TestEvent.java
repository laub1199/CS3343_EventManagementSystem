package TestEventManagementSystem;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import EventManagementSystem.*;

public class TestEvent {
	private EventAllocator eventAllocator;
	Student student1, student2, student3, student4, student5, student6, student7, student8;
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
	@BeforeEach
	public void setUp() throws Exception { 
		eventAllocator = new EventAllocator(); 
		Student student1 = new Student("S12345","Computer Science", "Mary", "Ng", 'F', 18);
		Student student2 = new Student("S13579","Computer Science", "Peter", "Chan", 'M', 20);
		Student student3 = new Student("S24680","Computer Science", "Simon", "Wong", 'M', 19);
		Student student4 = new Student("S54321","Computer Science", "Polly", "Chan", 'F', 22);
		Student student5 = new Student("S11111","Computer Science", "Tom", "Chan", 'M', 22);
		Student student6 = new Student("S55555","Data Science", "John", "Lee", 'M', 21);
		Student student7 = new Student("S11111","Science", "May", "Lam", 'F', 22);
		Student student8 = new Student("S11111","Science", "Chirtin", "Wong", 'F', 18);
	}
    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
	public void tearDown() {}
	
	@Test
	public void testFindEventIndividualByID01() {
		try {
			Event event = new EventIndividual("Programming course", "e12345", 50, new Date(), "CS");
			eventAllocator.addEvent(event);
			Event result = eventAllocator.findEventByID("e12345");
			assertEquals(event, result);
		} catch (ExEventNotFound e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testgetEventIndividualID01() {
		String eventID = "e12345";
		Event event = new EventIndividual("Programming course", eventID, 50, new Date(), "CS");
		String result = event.getEventID();
		assertEquals(eventID, result);
	}
	
	@Test
	public void testFindEventGroupByID01() {
		try {
			Event event = new EventGroup("Basketball competition", "e54321", 90, new Date(), "CS", 8, 5, 15);
			eventAllocator.addEvent(event);
			Event result = eventAllocator.findEventByID("e54321");
			assertEquals(event, result);
		} catch (ExEventNotFound e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testgetEventGroupID01() {
		String eventID = "e54321";
		Event event = new EventGroup("Basketball competition", eventID, 90, new Date(), "CS", 8, 5, 15);
		eventAllocator.addEvent(event);
		String result = event.getEventID();
		assertEquals(eventID, result);
	}
	
	@Test
	public void testEventIndividualIsFull01() {
		boolean result;
		
		Event event = new EventIndividual("robot competition", "e12345", 5, new Date(), "CS");
		eventAllocator.addEvent(event);
		((EventIndividual) event).addStudent(student1);
		((EventIndividual) event).addStudent(student2);
		((EventIndividual) event).addStudent(student3);
		((EventIndividual) event).addStudent(student4);
		((EventIndividual) event).addStudent(student5);
		
		result = event.isFull();
	    assertEquals(true, result);
	}
	
	@Test
	public void testEventIndividualIsFull02() {
		boolean result;
		
		Event event = new EventIndividual("robot competition", "e12345", 5, new Date(), "CS");
		eventAllocator.addEvent(event);
		((EventIndividual) event).addStudent(student1);
		((EventIndividual) event).addStudent(student2);
		((EventIndividual) event).addStudent(student3);
		((EventIndividual) event).addStudent(student4);
		
		result = event.isFull();
	    assertEquals(false, result);
	}
	
	@Test
	public void testEventGroupIsFull01() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), "CS", 4, 2, 3);
		
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		studentList1.add(student3);
		Group gp1 = new Group("g12345", 3);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student4);
		studentList2.add(student5);
		studentList2.add(student6);
		Group gp2 = new Group("g54321", 3);
		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student7);
		studentList3.add(student8);
		Group gp3 = new Group("g13579", 2);
		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);
		
		result = event.isFull();
	    assertEquals(true, result);
	}
	
	@Test
	public void testEventGroupIsFull02() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), "CS", 4, 2, 3);
		eventAllocator.addEvent(event);
		
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345", 2);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		Group gp2 = new Group("g54321", 2);
		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student5);
		studentList3.add(student6);
		studentList3.add(student7);
		Group gp3 = new Group("g13579", 3);
		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);
		
		result = event.isFull();
	    assertEquals(true, result);
	}
	
	@Test
	public void testEventGroupIsFull03() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), "CS", 4, 2, 3);

		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345", 2);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		Group gp2 = new Group("g54321", 2);
		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student5);
		studentList3.add(student6);
		Group gp3 = new Group("g13579", 2);
		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);

		ArrayList<Student> studentList4 = new ArrayList<>();
		studentList4.add(student7);
		studentList4.add(student8);
		Group gp4 = new Group("g24680", 2);
		eventAllocator.groupJoinEvent(gp4, (EventGroup) event);
		
		result = event.isFull();
	    assertEquals(true, result);
	}
	
	@Test
	public void testEventGroupIsFull04() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), "CS", 4, 2, 3);
		
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345", 2);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		Group gp2 = new Group("g54321", 2);
		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student5);
		studentList3.add(student6);
		Group gp3 = new Group("g13579", 2);
		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);

		result = event.isFull();
	    assertEquals(false, result);
	}
	
	
	@Test
	public void testEventGroupValidToJoin01() {
		boolean result;
		
		EventGroup event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), "CS", 4, 2, 3);
		
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		studentList1.add(student3);
		Group gp1 = new Group("g12345", 3);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student4);
		studentList2.add(student5);
		studentList2.add(student6);
		Group gp2 = new Group("g54321", 3);
		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student7);
		studentList3.add(student8);
		Group gp3 = new Group("g13579", 2);
		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);
		
		result = ((EventGroup) event).validToJoin(2);
	    assertEquals(false, result);
	}
	
	@Test
	public void testEventGroupValidToJoin02() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), "CS", 4, 2, 3);
		 	
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345", 2);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		Group gp2 = new Group("g54321", 2);
		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student5);
		studentList3.add(student6);
		studentList3.add(student7);
		Group gp3 = new Group("g13579", 3);
		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);
		
		result = ((EventGroup) event).validToJoin(2);
	    assertEquals(false, result);
	}
	
	@Test
	public void testEventGroupValidToJoin03() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), "CS", 4, 2, 3);
		
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345", 2);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		Group gp2 = new Group("g54321", 2);
		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student5);
		studentList3.add(student6);
		Group gp3 = new Group("g13579", 2);
		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);

		ArrayList<Student> studentList4 = new ArrayList<>();
		studentList4.add(student7);
		studentList4.add(student8);
		Group gp4 = new Group("g24680", 2);
		eventAllocator.groupJoinEvent(gp4, (EventGroup) event);
		
		result = ((EventGroup) event).validToJoin(2);
	    assertEquals(false, result);
	}
	
	@Test
	public void testEventGroupValidToJoin04() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), "CS", 4, 2, 3);
		eventAllocator.addEvent(event);
		
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345", 2);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		Group gp2 = new Group("g54321", 2);
		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student5);
		studentList3.add(student6);
		Group gp3 = new Group("g13579", 2);
		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);

		result = ((EventGroup) event).validToJoin(2);
	    assertEquals(true, result);
	}
	
	@Test
	public void testEventGroupValidToJoin05() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), "CS", 4, 2, 3);
		
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345", 2);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		Group gp2 = new Group("g54321", 2);
		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student5);
		studentList3.add(student6);
		Group gp3 = new Group("g13579", 2);
		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);

		result = ((EventGroup) event).validToJoin(3);
	    assertEquals(false, result);
	}
	
	@Test
	public void testEventGroupValidToJoin06() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), "CS", 4, 2, 3);
		
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345", 2);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		studentList2.add(student5);
		Group gp2 = new Group("g54321", 3);
		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
		
		result = ((EventGroup) event).validToJoin(3);
	    assertEquals(true, result);
	}
	
	@Test
	public void testEventGroupgetTotalNumOfStudent01() {
		int result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), "CS", 4, 2, 3);
		
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345", 2);
		eventAllocator.groupJoinEvent(gp1, (EventGroup) event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		Group gp2 = new Group("g54321", 2);
		eventAllocator.groupJoinEvent(gp2, (EventGroup) event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student5);
		studentList3.add(student6);
		Group gp3 = new Group("g13579", 2);
		eventAllocator.groupJoinEvent(gp3, (EventGroup) event);

		ArrayList<Student> studentList4 = new ArrayList<>();
		studentList4.add(student7);
		studentList4.add(student8);
		Group gp4 = new Group("g24680", 2);
		eventAllocator.groupJoinEvent(gp4, (EventGroup) event);
		
		result = ((EventGroup) event).getTotalNumOfStudent();
	    assertEquals(8, result);
	}

	
}
