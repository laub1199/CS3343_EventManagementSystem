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
		getEventList().remove(event);
	}
	
	public void groupJoinEvent(Group group, EventGroup event) {
		group.setEventGroup((EventGroup)event);
		event.addGroup(group);
	}
	
	public void studentJoinEvent(Student student, EventIndividual event) {
		event.addStudent(student);
	}
	
	public void groupQuitEvent(Group group, EventGroup event) {
		event.quitGroup(group);
		group.setEventGroup(null);
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
	
	public ArrayList<Event> getEventList() {
		return eventList;
	}

}
