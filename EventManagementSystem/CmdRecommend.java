package EventManagementSystem;

import java.beans.EventHandler;
import java.util.ArrayList;


public class CmdRecommend implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {


        try{
        	if (cmdParts.length != 2 || cmdParts[1].charAt(0) != 's') {
                throw new ExWrongCommand();
            }
        	String sID = null;
            try {
            	sID = cmdParts[1];
            	if (sID.length() != 9 || Integer.parseInt(sID.substring(1,8)) <0 || Integer.parseInt(sID.substring(1,8)) > 99999999) {
            		throw new ExInvalidStudentID();
            	}
            } 
            catch (NumberFormatException ex) {
            	throw new ExInvalidStudentID();
            }

            StudentHandler studentHandler = StudentHandler.getInstance();
            Student student = studentHandler.getStudent(sID);
            EventAllocator eventallocator = EventAllocator.getInstance();

            ArrayList<Event> emaj = new ArrayList<>();
            ArrayList<Event> eothers = new ArrayList<>();

            for (Event e : eventallocator.getEventList()) {
            	if (!e.isFull()) {
	            	if (e.getMajor().equals(student.getMajor())) {
	            		emaj.add(e);
	            	}
	            	else {
	                    eothers.add(e);
	                }
            	}
            }
            if (emaj.isEmpty() && eothers.isEmpty()){
                throw new ExNoSuitableEvent();
            }
            
            int count = 0;
            if(emaj.isEmpty()){
                System.out.println("There is no recommended event from your department! \n" );
            }else{
                System.out.println("Here are the recommended events from your department: \n");
                System.out.printf("|%-10s|%-30s|%-12s|%-8s|%-30s|%-5s|%-10s|%-15s|%-11s|%-16s|%-16s|\n",
                        "Event ID","Event Name","Date","Capacity","Major","Quota","Type","Group Capacity","Group Quota","Min No. In Group","Max No. In Group");
                count = 0;
                for(Event e: emaj){
                	count++;
                    if (e instanceof EventIndividual) {
                        ((EventIndividual)e).printDetail();
                    }
                    else if (e instanceof EventGroup) {
                        ((EventGroup)e).printDetail();
                    }
                    if (count >= 2) break;
                }
            }
            
            if(eothers.isEmpty()){
                System.out.println("There is no recommended event from other department! \n");
            }else{
                System.out.println("Here are the recommended events outside your department \n");
                System.out.printf("|%-10s|%-30s|%-12s|%-8s|%-30s|%-5s|%-10s|%-15s|%-11s|%-16s|%-16s|\n",
                        "Event ID","Event Name","Date","Capacity","Major","Quota","Type","Group Capacity","Group Quota","Min No. In Group","Max No. In Group");
                count = 0;
                for(Event e: eothers){
                	count++;
                    if (e instanceof EventIndividual) {
                        ((EventIndividual)e).printDetail();
                    }
                    else if (e instanceof EventGroup) {
                        ((EventGroup)e).printDetail();
                    }
                    if (count >= 2) break;
                }
            }

        }catch(ExNoSuitableEvent | ExStudentNotFound | ExInvalidStudentID e){
            System.out.println(e.getMessage());
        }catch(ExWrongCommand e) {
			System.out.println(e.getMessage());
			System.out.println("Delete student command should be \"recommend sXXXXXXXXX\"");
        }
    }
}