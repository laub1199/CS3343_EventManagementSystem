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
	
	public void addEvent(String eName, String eID, int cap, Date eDate) throws ExInvalidEventID, ExInvalidEventDate, ExInvalidEventCapacity {
		if (findEventByID(eID) != null || eID.length() != 9 || eID.charAt(0) != 'e') {
			throw new ExInvalidEventID();
		}
		if (eDate.before(new Date())) {
			throw new ExInvalidEventDate();
		}
		if (cap < 1) {
			throw new ExInvalidEventCapacity();
		}
		Event event = new EventIndividual(eName, eID, cap, eDate);
		eventList.add(event);
	}
	
	public void deleteEvent(Event event) {
		getEventList().remove(event);
	}

	public void deleteEvent(String eID) throws NullPointerException {
		eventList.removeIf(e -> e.getEventID().equals(eID));
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
		System.out.println("Event ID\tEvent Name\tDate\tCapacity\tMajor\tQuota\tType\tGroup Capacity\tGroup Quota\tMin No. In Group\tMax No. In Group");
		for (Event e: getEventList()) {
			if (e instanceof EventIndividual) {
				((EventIndividual)e).printDetail();
			}
			else if ((e instanceof EventGroup)) {
				((EventGroup)e).printDetail();
			}
		}
	}
	
	public void listEventApplicans() {
		for (Event e: getEventList()) {
			((EventGroup) e).listJoinedStudent();
		}
	}
	
	public Event findEventByID(String eventID)  throws ExEventNotFound {
		for (Event e: getEventList()) {
			if (e.getEventID().equals(eventID)) {
				return e;
			}
		}
		throw new ExEventNotFound();
	}
	
	public boolean findEventByMajor(String major)  throws ExEventNotFound {
		for (Event e: getEventList()) {
			if (e.getMajor().equals(major)) {
				return true;
			}
		}
		throw new ExEventNotFound();
	}
	
	public Event findEventByGroup(Group group) {
		for (Event e: getEventList()) {
			if (((EventGroup) e).foundGroup(group)) {
				return e;
			}
		}
		return null;
	}
	
	public ArrayList<Event> getEventList() {
		return eventList;
	}
	
	
}
