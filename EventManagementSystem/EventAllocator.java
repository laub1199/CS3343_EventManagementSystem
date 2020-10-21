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
	
	public void addEvent(Event event) {
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
	
	public String listEvent() {
    	String str = ""; 
		if (eventList.size() > 0) {
			str = String.format("|%-10s|%-30s|%-12s|%-8s|%-30s|%-5s|%-10s|%-15s|%-11s|%-16s|%-16s|\n",
					"Event ID","Event Name","Date","Capacity","Major","Quota","Type","Group Capacity","Group Quota","Min No. In Group","Max No. In Group");
			for (Event e: getEventList()) {
				if (e instanceof EventIndividual) {
					str += ((EventIndividual)e).printDetail();
				}
				else if ((e instanceof EventGroup)) {
					str += ((EventGroup)e).printDetail();
				}
			}
		}
		else {
			str = "There are no event.";
		}
		return str;
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
	
	public ArrayList<Event> findEventByMajor(Major major) {
		ArrayList<Event> eventMajorList = new ArrayList<>();
		for (Event e: getEventList()) {
			if (e.getMajor().equals(major)) {
				eventMajorList.add(e);
			}
		}
		return eventMajorList;
	}
	
	public Event findEventByGroup(Group group) {
		for (Event e: getEventList()) {
			if(e instanceof EventGroup) {
				if (((EventGroup) e).foundGroup(group)) {
					return e;
				}
			}

		}
		return null;
	}
	
	public ArrayList<Event> getEventList() {
		return eventList;
	}

}
