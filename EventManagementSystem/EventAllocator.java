package EventManagementSystem;

import java.util.ArrayList;

public class EventAllocator {
	private static EventAllocator instance = new EventAllocator();
	private StudentHandler studentHandler = StudentHandler.getInstance();
	private GroupHandler groupHandler = GroupHandler.getInstance();
	private ArrayList<Event> eventList;
	
	public static EventAllocator getInstance(){return instance;}
	
	public void addEvent(Event event) {
		eventList.add(event);
	}
	
	public void deleteEvent(Event event) {
		eventList.remove(event);
	}
	
	public void deleteEvent(String eventID) {
		Event event = findEventByID(eventID);
		if (event != null)
			eventList.remove(event);
	}
	
	public void groupJoinEvent(Group group, EventGroup event) {
		event.addGroup(group);
	}
	
	public void groupJoinEvent(Group group, String eventID) {
		Event event = findEventByID(eventID);
		if (event != null)
			((EventGroup) event).addGroup(group);
	}
	
	public void studentJoinEvent(Student student, EventIndividual event) {
		event.addStudent(student);
	}
	
	public void studentJoinEvent(Student student, String eventID) {
		Event event = findEventByID(eventID);
		if (event != null)
			((EventIndividual) event).addStudent(student);
	}
	
	public void groupQuitEvent(Group group, EventGroup event) {
		event.quitGroup(group);
	}
	
	public void groupQuitEvent(Group group, String eventID) {
		Event event = findEventByID(eventID);
		if (event != null)
			((EventGroup) event).quitGroup(group);
	}
	
	public void studentQuitEvent(Student student, EventIndividual event) {
		event.quitStudent(student);
	}
	
	public void studentQuitEvent(Student student, String eventID) {
		Event event = findEventByID(eventID);
		if (event != null)
			((EventIndividual) event).quitStudent(student);
	}
	
	public void listEvent() {
		for (Event e: eventList) {
			System.out.print(e.toString());
		}
	}
	
	public void listEventApplicans() {
		for (Event e: eventList) {
			((EventGroup) e).listJoinedStudent();
		}
	}
	
	private Event findEventByID(String eventID) {
		for (Event e: eventList) {
			if (e.getEventID().equals(eventID)) {
				return e;
			}
		}
		return null;
	}
	
}
