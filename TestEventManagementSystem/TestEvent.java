package TestEventManagementSystem;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import EventManagementSystem.*;

public class TestEvent {
	private EventAllocator eventAllocator;
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
	@BeforeEach
	public void setUp() throws Exception { 
		eventAllocator = new EventAllocator(); 
	}
    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
	public void tearDown() {}
	
	@Test
	public void testFindEventIndividualByID01() {
		Event event = new EventIndividual("Programming course", "e12345", 50, new Date());
		Event result = eventAllocator.findEventByID("e12345");
		assertEquals(event, result);
	}
	
	@Test
	public void testgetEventIndividualID01() {
		String eventName = "Programming course";
		Event event = new EventIndividual(eventName, "e12345", 50, new Date());
		String result = event.getEventID();
		assertEquals(eventName, result);
	}
	
	@Test
	public void testFindEventGroupByID01() {
		Event event = new EventGroup("Basketball competition", "e54321", 90, new Date(), 8, 5, 15);
		Event result = eventAllocator.findEventByID("e54321");
		assertEquals(event, result);
	}
	
	@Test
	public void testgetEventGroupID01() {
		String eventName = "Basketball competition";
		Event event = new EventGroup(eventName, "e54321", 90, new Date(), 8, 5, 15);
		String result = event.getEventID();
		assertEquals(eventName, result);
	}
	
	@Test
	public void testEventIndividualIsFull01() {
		boolean result;
		
		Event event = new EventIndividual("robot competition", "e12345", 5, new Date());
		Student student1 = new Student("S12345","Computer Science", "Mary", "Ng", 'F', 18);
		((EventIndividual) event).addStudent(student1);
		Student student2 = new Student("S13579","Computer Science", "Peter", "Chan", 'M', 20);
		((EventIndividual) event).addStudent(student2);
		Student student3 = new Student("S24680","Computer Science", "Simon", "Wong", 'M', 19);
		((EventIndividual) event).addStudent(student3);
		Student student4 = new Student("S54321","Computer Science", "Polly", "Chan", 'F', 22);
		((EventIndividual) event).addStudent(student4);
		Student student5 = new Student("S11111","Computer Science", "Tom", "Chan", 'M', 22);
		((EventIndividual) event).addStudent(student5);
		
		result = event.isFull();
	    assertEquals(true, result);
	}
	
	@Test
	public void testEventIndividualIsFull02() {
		boolean result;
		
		Event event = new EventIndividual("robot competition", "e12345", 5, new Date());
		Student student1 = new Student("S12345","Computer Science", "Mary", "Ng", 'F', 18);
		((EventIndividual) event).addStudent(student1);
		Student student2 = new Student("S13579","Computer Science", "Peter", "Chan", 'M', 20);
		((EventIndividual) event).addStudent(student2);
		Student student3 = new Student("S24680","Computer Science", "Simon", "Wong", 'M', 19);
		((EventIndividual) event).addStudent(student3);
		Student student4 = new Student("S54321","Computer Science", "Polly", "Chan", 'F', 22);
		((EventIndividual) event).addStudent(student4);
		
		result = event.isFull();
	    assertEquals(false, result);
	}
	
	@Test
	public void testEventGroupIsFull01() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), 4, 2, 3);
		Student student1 = new Student("S12345","Computer Science", "Mary", "Ng", 'F', 18);
		((EventIndividual) event).addStudent(student1);
		Student student2 = new Student("S13579","Computer Science", "Peter", "Chan", 'M', 20);
		((EventIndividual) event).addStudent(student2);
		Student student3 = new Student("S24680","Computer Science", "Simon", "Wong", 'M', 19);
		((EventIndividual) event).addStudent(student3);
		Student student4 = new Student("S54321","Computer Science", "Polly", "Chan", 'F', 22);
		((EventIndividual) event).addStudent(student4);
		Student student5 = new Student("S11111","Computer Science", "Tom", "Chan", 'M', 22);
		((EventIndividual) event).addStudent(student5);
		Student student6 = new Student("S55555","Data Science", "John", "Lee", 'M', 21);
		((EventIndividual) event).addStudent(student6);
		Student student7 = new Student("S11111","Science", "May", "Lam", 'F', 22);
		((EventIndividual) event).addStudent(student7);
		Student student8 = new Student("S11111","Science", "Chirtin", "Wong", 'F', 18);
		((EventIndividual) event).addStudent(student8);
		
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		studentList1.add(student3);
		Group gp1 = new Group("g12345", studentList1);
		eventAllocator.groupJoinEvent(gp1, event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student4);
		studentList2.add(student5);
		studentList2.add(student6);
		Group gp2 = new Group("g54321", studentList2);
		eventAllocator.groupJoinEvent(gp2, event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student7);
		studentList3.add(student8);
		Group gp3 = new Group("g13579", studentList3);
		eventAllocator.groupJoinEvent(gp3, event);
		
		result = event.isFull();
	    assertEquals(true, result);
	}
	
	@Test
	public void testEventGroupIsFull02() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), 4, 2, 3);
		Student student1 = new Student("S12345","Computer Science", "Mary", "Ng", 'F', 18);
		((EventIndividual) event).addStudent(student1);
		Student student2 = new Student("S13579","Computer Science", "Peter", "Chan", 'M', 20);
		((EventIndividual) event).addStudent(student2);
		Student student3 = new Student("S24680","Computer Science", "Simon", "Wong", 'M', 19);
		((EventIndividual) event).addStudent(student3);
		Student student4 = new Student("S54321","Computer Science", "Polly", "Chan", 'F', 22);
		((EventIndividual) event).addStudent(student4);
		Student student5 = new Student("S11111","Computer Science", "Tom", "Chan", 'M', 22);
		((EventIndividual) event).addStudent(student5);
		Student student6 = new Student("S55555","Data Science", "John", "Lee", 'M', 21);
		((EventIndividual) event).addStudent(student6);
		Student student7 = new Student("S11111","Science", "May", "Lam", 'F', 22);
		((EventIndividual) event).addStudent(student7);
		 	
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345", studentList1);
		eventAllocator.groupJoinEvent(gp1, event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		Group gp2 = new Group("g54321", studentList2);
		eventAllocator.groupJoinEvent(gp2, event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student5);
		studentList3.add(student6);
		studentList3.add(student7);
		Group gp3 = new Group("g13579", studentList3);
		eventAllocator.groupJoinEvent(gp3, event);
		
		result = event.isFull();
	    assertEquals(true, result);
	}
	
	@Test
	public void testEventGroupIsFull03() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), 4, 2, 3);
		Student student1 = new Student("S12345","Computer Science", "Mary", "Ng", 'F', 18);
		((EventIndividual) event).addStudent(student1);
		Student student2 = new Student("S13579","Computer Science", "Peter", "Chan", 'M', 20);
		((EventIndividual) event).addStudent(student2);
		Student student3 = new Student("S24680","Computer Science", "Simon", "Wong", 'M', 19);
		((EventIndividual) event).addStudent(student3);
		Student student4 = new Student("S54321","Computer Science", "Polly", "Chan", 'F', 22);
		((EventIndividual) event).addStudent(student4);
		Student student5 = new Student("S11111","Computer Science", "Tom", "Chan", 'M', 22);
		((EventIndividual) event).addStudent(student5);
		Student student6 = new Student("S55555","Data Science", "John", "Lee", 'M', 21);
		((EventIndividual) event).addStudent(student6);
		Student student7 = new Student("S11111","Science", "May", "Lam", 'F', 22);
		((EventIndividual) event).addStudent(student7);
		Student student8 = new Student("S11111","Science", "Chirtin", "Wong", 'F', 18);
		((EventIndividual) event).addStudent(student8);
		
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345", studentList1);
		eventAllocator.groupJoinEvent(gp1, event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		Group gp2 = new Group("g54321", studentList2);
		eventAllocator.groupJoinEvent(gp2, event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student5);
		studentList3.add(student6);
		Group gp3 = new Group("g13579", studentList3);
		eventAllocator.groupJoinEvent(gp3, event);

		ArrayList<Student> studentList4 = new ArrayList<>();
		studentList4.add(student7);
		studentList4.add(student8);
		Group gp4 = new Group("g24680", studentList4);
		eventAllocator.groupJoinEvent(gp4, event);
		
		result = event.isFull();
	    assertEquals(true, result);
	}
	
	@Test
	public void testEventGroupIsFull04() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), 4, 2, 3);
		Student student1 = new Student("S12345","Computer Science", "Mary", "Ng", 'F', 18);
		((EventIndividual) event).addStudent(student1);
		Student student2 = new Student("S13579","Computer Science", "Peter", "Chan", 'M', 20);
		((EventIndividual) event).addStudent(student2);
		Student student3 = new Student("S24680","Computer Science", "Simon", "Wong", 'M', 19);
		((EventIndividual) event).addStudent(student3);
		Student student4 = new Student("S54321","Computer Science", "Polly", "Chan", 'F', 22);
		((EventIndividual) event).addStudent(student4);
		Student student5 = new Student("S11111","Computer Science", "Tom", "Chan", 'M', 22);
		((EventIndividual) event).addStudent(student5);
		Student student6 = new Student("S55555","Data Science", "John", "Lee", 'M', 21);
		((EventIndividual) event).addStudent(student6);
		
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345", studentList1);
		eventAllocator.groupJoinEvent(gp1, event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		Group gp2 = new Group("g54321", studentList2);
		eventAllocator.groupJoinEvent(gp2, event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student5);
		studentList3.add(student6);
		Group gp3 = new Group("g13579", studentList3);
		eventAllocator.groupJoinEvent(gp3, event);

		result = event.isFull();
	    assertEquals(false, result);
	}
	
	
	@Test
	public void testEventGroupValidToJoin01() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), 4, 2, 3);
		Student student1 = new Student("S12345","Computer Science", "Mary", "Ng", 'F', 18);
		((EventIndividual) event).addStudent(student1);
		Student student2 = new Student("S13579","Computer Science", "Peter", "Chan", 'M', 20);
		((EventIndividual) event).addStudent(student2);
		Student student3 = new Student("S24680","Computer Science", "Simon", "Wong", 'M', 19);
		((EventIndividual) event).addStudent(student3);
		Student student4 = new Student("S54321","Computer Science", "Polly", "Chan", 'F', 22);
		((EventIndividual) event).addStudent(student4);
		Student student5 = new Student("S11111","Computer Science", "Tom", "Chan", 'M', 22);
		((EventIndividual) event).addStudent(student5);
		Student student6 = new Student("S55555","Data Science", "John", "Lee", 'M', 21);
		((EventIndividual) event).addStudent(student6);
		Student student7 = new Student("S11111","Science", "May", "Lam", 'F', 22);
		((EventIndividual) event).addStudent(student7);
		Student student8 = new Student("S11111","Science", "Chirtin", "Wong", 'F', 18);
		((EventIndividual) event).addStudent(student8);
		
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		studentList1.add(student3);
		Group gp1 = new Group("g12345", studentList1);
		eventAllocator.groupJoinEvent(gp1, event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student4);
		studentList2.add(student5);
		studentList2.add(student6);
		Group gp2 = new Group("g54321", studentList2);
		eventAllocator.groupJoinEvent(gp2, event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student7);
		studentList3.add(student8);
		Group gp3 = new Group("g13579", studentList3);
		eventAllocator.groupJoinEvent(gp3, event);
		
		result = ((EventGroup) event).validToJoin(2);
	    assertEquals(false, result);
	}
	
	@Test
	public void testEventGroupValidToJoin02() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), 4, 2, 3);
		Student student1 = new Student("S12345","Computer Science", "Mary", "Ng", 'F', 18);
		((EventIndividual) event).addStudent(student1);
		Student student2 = new Student("S13579","Computer Science", "Peter", "Chan", 'M', 20);
		((EventIndividual) event).addStudent(student2);
		Student student3 = new Student("S24680","Computer Science", "Simon", "Wong", 'M', 19);
		((EventIndividual) event).addStudent(student3);
		Student student4 = new Student("S54321","Computer Science", "Polly", "Chan", 'F', 22);
		((EventIndividual) event).addStudent(student4);
		Student student5 = new Student("S11111","Computer Science", "Tom", "Chan", 'M', 22);
		((EventIndividual) event).addStudent(student5);
		Student student6 = new Student("S55555","Data Science", "John", "Lee", 'M', 21);
		((EventIndividual) event).addStudent(student6);
		Student student7 = new Student("S11111","Science", "May", "Lam", 'F', 22);
		((EventIndividual) event).addStudent(student7);
		 	
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345", studentList1);
		eventAllocator.groupJoinEvent(gp1, event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		Group gp2 = new Group("g54321", studentList2);
		eventAllocator.groupJoinEvent(gp2, event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student5);
		studentList3.add(student6);
		studentList3.add(student7);
		Group gp3 = new Group("g13579", studentList3);
		eventAllocator.groupJoinEvent(gp3, event);
		
		result = ((EventGroup) event).validToJoin(2);
	    assertEquals(false, result);
	}
	
	@Test
	public void testEventGroupValidToJoin03() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), 4, 2, 3);
		Student student1 = new Student("S12345","Computer Science", "Mary", "Ng", 'F', 18);
		((EventIndividual) event).addStudent(student1);
		Student student2 = new Student("S13579","Computer Science", "Peter", "Chan", 'M', 20);
		((EventIndividual) event).addStudent(student2);
		Student student3 = new Student("S24680","Computer Science", "Simon", "Wong", 'M', 19);
		((EventIndividual) event).addStudent(student3);
		Student student4 = new Student("S54321","Computer Science", "Polly", "Chan", 'F', 22);
		((EventIndividual) event).addStudent(student4);
		Student student5 = new Student("S11111","Computer Science", "Tom", "Chan", 'M', 22);
		((EventIndividual) event).addStudent(student5);
		Student student6 = new Student("S55555","Data Science", "John", "Lee", 'M', 21);
		((EventIndividual) event).addStudent(student6);
		Student student7 = new Student("S11111","Science", "May", "Lam", 'F', 22);
		((EventIndividual) event).addStudent(student7);
		Student student8 = new Student("S11111","Science", "Chirtin", "Wong", 'F', 18);
		((EventIndividual) event).addStudent(student8);
		
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345", studentList1);
		eventAllocator.groupJoinEvent(gp1, event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		Group gp2 = new Group("g54321", studentList2);
		eventAllocator.groupJoinEvent(gp2, event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student5);
		studentList3.add(student6);
		Group gp3 = new Group("g13579", studentList3);
		eventAllocator.groupJoinEvent(gp3, event);

		ArrayList<Student> studentList4 = new ArrayList<>();
		studentList4.add(student7);
		studentList4.add(student8);
		Group gp4 = new Group("g24680", studentList4);
		eventAllocator.groupJoinEvent(gp4, event);
		
		result = ((EventGroup) event).validToJoin(2);
	    assertEquals(false, result);
	}
	
	@Test
	public void testEventGroupValidToJoin04() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), 4, 2, 3);
		Student student1 = new Student("S12345","Computer Science", "Mary", "Ng", 'F', 18);
		((EventIndividual) event).addStudent(student1);
		Student student2 = new Student("S13579","Computer Science", "Peter", "Chan", 'M', 20);
		((EventIndividual) event).addStudent(student2);
		Student student3 = new Student("S24680","Computer Science", "Simon", "Wong", 'M', 19);
		((EventIndividual) event).addStudent(student3);
		Student student4 = new Student("S54321","Computer Science", "Polly", "Chan", 'F', 22);
		((EventIndividual) event).addStudent(student4);
		Student student5 = new Student("S11111","Computer Science", "Tom", "Chan", 'M', 22);
		((EventIndividual) event).addStudent(student5);
		Student student6 = new Student("S55555","Data Science", "John", "Lee", 'M', 21);
		((EventIndividual) event).addStudent(student6);
		
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345", studentList1);
		eventAllocator.groupJoinEvent(gp1, event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		Group gp2 = new Group("g54321", studentList2);
		eventAllocator.groupJoinEvent(gp2, event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student5);
		studentList3.add(student6);
		Group gp3 = new Group("g13579", studentList3);
		eventAllocator.groupJoinEvent(gp3, event);

		result = ((EventGroup) event).validToJoin(2);
	    assertEquals(true, result);
	}
	
	@Test
	public void testEventGroupValidToJoin05() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), 4, 2, 3);
		
		Student student1 = new Student("S12345","Computer Science", "Mary", "Ng", 'F', 18);
		((EventIndividual) event).addStudent(student1);
		Student student2 = new Student("S13579","Computer Science", "Peter", "Chan", 'M', 20);
		((EventIndividual) event).addStudent(student2);
		Student student3 = new Student("S24680","Computer Science", "Simon", "Wong", 'M', 19);
		((EventIndividual) event).addStudent(student3);
		Student student4 = new Student("S54321","Computer Science", "Polly", "Chan", 'F', 22);
		((EventIndividual) event).addStudent(student4);
		Student student5 = new Student("S11111","Computer Science", "Tom", "Chan", 'M', 22);
		((EventIndividual) event).addStudent(student5);
		Student student6 = new Student("S55555","Data Science", "John", "Lee", 'M', 21);
		((EventIndividual) event).addStudent(student6);
		
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345", studentList1);
		eventAllocator.groupJoinEvent(gp1, event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		Group gp2 = new Group("g54321", studentList2);
		eventAllocator.groupJoinEvent(gp2, event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student5);
		studentList3.add(student6);
		Group gp3 = new Group("g13579", studentList3);
		eventAllocator.groupJoinEvent(gp3, event);

		result = ((EventGroup) event).validToJoin(3);
	    assertEquals(false, result);
	}
	
	@Test
	public void testEventGroupValidToJoin06() {
		boolean result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), 4, 2, 3);
		Student student1 = new Student("S12345","Computer Science", "Mary", "Ng", 'F', 18);
		((EventIndividual) event).addStudent(student1);
		Student student2 = new Student("S13579","Computer Science", "Peter", "Chan", 'M', 20);
		((EventIndividual) event).addStudent(student2);
		Student student3 = new Student("S24680","Computer Science", "Simon", "Wong", 'M', 19);
		((EventIndividual) event).addStudent(student3);
		Student student4 = new Student("S54321","Computer Science", "Polly", "Chan", 'F', 22);
		((EventIndividual) event).addStudent(student4);
		Student student5 = new Student("S11111","Computer Science", "Tom", "Chan", 'M', 22);
		((EventIndividual) event).addStudent(student5);
		
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345", studentList1);
		eventAllocator.groupJoinEvent(gp1, event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		studentList2.add(student5);
		Group gp2 = new Group("g54321", studentList2);
		eventAllocator.groupJoinEvent(gp2, event);
		
		result = ((EventGroup) event).validToJoin(3);
	    assertEquals(true, result);
	}
	
	@Test
	public void testEventGroupgetTotalNumOfStudent01() {
		int result;
		
		Event event = new EventGroup("Betmenten competition", "e12345", 8, new Date(), 4, 2, 3);
		Student student1 = new Student("S12345","Computer Science", "Mary", "Ng", 'F', 18);
		((EventIndividual) event).addStudent(student1);
		Student student2 = new Student("S13579","Computer Science", "Peter", "Chan", 'M', 20);
		((EventIndividual) event).addStudent(student2);
		Student student3 = new Student("S24680","Computer Science", "Simon", "Wong", 'M', 19);
		((EventIndividual) event).addStudent(student3);
		Student student4 = new Student("S54321","Computer Science", "Polly", "Chan", 'F', 22);
		((EventIndividual) event).addStudent(student4);
		Student student5 = new Student("S11111","Computer Science", "Tom", "Chan", 'M', 22);
		((EventIndividual) event).addStudent(student5);
		Student student6 = new Student("S55555","Data Science", "John", "Lee", 'M', 21);
		((EventIndividual) event).addStudent(student6);
		Student student7 = new Student("S11111","Science", "May", "Lam", 'F', 22);
		((EventIndividual) event).addStudent(student7);
		Student student8 = new Student("S11111","Science", "Chirtin", "Wong", 'F', 18);
		((EventIndividual) event).addStudent(student8);
		
		ArrayList<Student> studentList1 = new ArrayList<>();
		studentList1.add(student1);
		studentList1.add(student2);
		Group gp1 = new Group("g12345", studentList1);
		eventAllocator.groupJoinEvent(gp1, event);
		
		ArrayList<Student> studentList2 = new ArrayList<>();
		studentList2.add(student3);
		studentList2.add(student4);
		Group gp2 = new Group("g54321", studentList2);
		eventAllocator.groupJoinEvent(gp2, event);
		
		ArrayList<Student> studentList3 = new ArrayList<>();
		studentList3.add(student5);
		studentList3.add(student6);
		Group gp3 = new Group("g13579", studentList3);
		eventAllocator.groupJoinEvent(gp3, event);

		ArrayList<Student> studentList4 = new ArrayList<>();
		studentList4.add(student7);
		studentList4.add(student8);
		Group gp4 = new Group("g24680", studentList4);
		eventAllocator.groupJoinEvent(gp4, event);
		
		result = event.getTotalNumOfStudent();
	    assertEquals(8, result);
	}

	
}
