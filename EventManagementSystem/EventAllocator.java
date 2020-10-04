package EventManagementSystem;

import java.util.ArrayList;
import java.util.Date;

public class EventAllocator {
	
	private static EventAllocator instance = new EventAllocator();
	private ArrayList<Event> eventList;
	
	public EventAllocator() {
		eventList = new ArrayList<>();
	}
	
	public static EventAllocator getInstance(){return instance;}
	
	public void addEvent(String eName, String eID, int cap, Date eDate) throws ExInvalidEventID, ExInvalidEventDate {
		if (findEventByID(eID) != null) {
			throw new ExInvalidEventID();
		}
		if (eDate.before(new Date())) {
			throw new ExInvalidEventDate();
		}
		Event event = new EventIndividual(eName, eID, cap, eDate);
		eventList.add(event);
	}
	
	public void deleteEvent(Event event) {
		eventList.remove(event);
	}
	
	public void groupJoinEvent(Group group, EventGroup event) {
		event.addGroup(group);
	}
	
	public void studentJoinEvent(Student student, EventIndividual event) {
		event.addStudent(student);
	}
	
	public void groupQuitEvent(Group group, EventGroup event) {
		event.quitGroup(group);
	}
	
	public void studentQuitEvent(Student student, EventIndividual event) {
		event.quitStudent(student);
	}
	
	public void listEvent() {
		System.out.println("Event ID" + "\t" + "Event name" + "\t" + "Date" + "\t" + "Capacity" + "\t" + "Quota" + 
							"\t" + "Type" + "\t" + "Min no. in group" + "\t" + "Max no. in group" + "\t");
		for (Event e: eventList) {
			if (e instanceof EventIndividual) {
				((EventIndividual)e).printDetail();
			}
			else if ((e instanceof EventGroup)) {
				((EventGroup)e).printDetail();
			}
		}
	}
	
	public void listEventApplicans() {
		for (Event e: eventList) {
			((EventGroup) e).listJoinedStudent();
		}
	}
	
	public Event findEventByID(String eventID) {
		for (Event e: eventList) {
			if (e.getEventID().equals(eventID)) {
				return e;
			}
		}
		return null;
	}
	
}
