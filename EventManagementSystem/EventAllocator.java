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
		int x=0;
		for(int i=0;i<eventList.size();i++){
			if(eventList.get(i).getEventDate().compareTo(event.getEventDate()) <= 0 && eventList.get(i+1).getEventDate().compareTo(event.getEventDate()) >= 0 ){
				x=i;
				break;
			}

		}
		for (int j= eventList.size() ; j>x ;j--){
			eventList.set(j, eventList.get(j - 1));
		}
		getEventList().add(x, event);
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
		
		if (eventList.size() > 0) {
			System.out.printf("|%-10s|%-25s|%-28s|%-8s|%-25s|%-5s|%-10s|%-15s|%-11s|%-16s|%-16s|\n",
					"Event ID","Event Name","Date","Capacity","Major","Quota","Type","Group Capacity","Group Quota","Min No. In Group","Max No. In Group");
			for (Event e: getEventList()) {
				if (e instanceof EventIndividual) {
					((EventIndividual)e).printDetail();
				}
				else if ((e instanceof EventGroup)) {
					((EventGroup)e).printDetail();
				}
			}
		}
		else {
			System.out.println("There are no event.");
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
	
	public ArrayList<Event> findEventByMajor(String major) {
		ArrayList<Event> eventMajorList = new ArrayList<>();
		for (Event e: getEventList()) {
			if (e.getMajor().equals(major)) {
				eventMajorList.add(e);
			}
		}
		return eventMajorList;
	}
	
	public Event findEventByGroup(Group group) throws ExEventNotFound, ExGroupNotFound {
		for (Event e: getEventList()) {
			if (((EventGroup) e).foundGroup(group)) {
				return e;
			}
		}
		throw new ExEventNotFound();
	}
	
	public ArrayList<Event> getEventList() {
		return eventList;
	}

}
