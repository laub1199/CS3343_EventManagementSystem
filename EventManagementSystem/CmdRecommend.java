package EventManagementSystem;

import java.beans.EventHandler;
import java.util.ArrayList;


public class CmdRecommend implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {


        try{
        	if (cmdParts.length != 2) {
                throw new ExWrongCommand();
            }
            try {
            	String sID = cmdParts[2];
            	if (sID.length() != 9 || sID.charAt(0) != 's' || Integer.parseInt(sID.substring(1,8)) <0 || Integer.parseInt(sID.substring(1,8)) > 99999999) {
            		throw new ExInvalidStudentID();
            	}
            } 
            catch (NumberFormatException ex) {
            	throw new ExInvalidStudentID();
            }

            StudentHandler studentHandler = StudentHandler.getInstance();
            Student student = studentHandler.getStudent(cmdParts[1]);
            EventAllocator eventallocator = EventAllocator.getInstance();

            ArrayList<Event> events = new ArrayList<>();
            events = eventallocator.getEventList();
            ArrayList<Event> emaj = new ArrayList<>();
            ArrayList<Event> eothers = new ArrayList<>();

            for (int i= 0; i< events.size(); i++ ){
                if(events.get(i).getMajor().equals(student.getMajor()) && !events.get(i).isFull()){
                    emaj.add(events.get(i));
                }
                if( !events.get(i).getMajor().equals(student.getMajor()) && !events.get(i).isFull()){
                    eothers.add(events.get(i));
                }
            }
            if (emaj.isEmpty() && eothers.isEmpty()){
                throw new ExNoSuitableEvent();
            }


            if(emaj.isEmpty()){
                System.out.println("There is no recommended event from your department! \n" );
            }else{
                System.out.println("Here are the recommended events from your department: \n");
                System.out.printf("|%-10s|%-25s|%-12s|%-8s|%-6s|%-5s|%-10s|%-15s|%-11s|%-16s|%-16s|\n",
                        "Event ID","Event Name","Date","Capacity","Major","Quota","Type","Group Capacity","Group Quota","Min No. In Group","Max No. In Group");
                for(int j=0; j<2;j++){

                    if (emaj.get(j) instanceof EventIndividual) {
                        ((EventIndividual)emaj.get(j)).printDetail();
                    }
                    else if ((emaj.get(j) instanceof EventGroup)) {
                        ((EventGroup)emaj.get(j)).printDetail();
                    }
                }
            }
            if(eothers.isEmpty()){
                System.out.println("There is no recommended event from other department! \n");
            }else{
                System.out.println("Here are the recommended events outside your department \n");
                System.out.printf("|%-10s|%-25s|%-12s|%-8s|%-6s|%-5s|%-10s|%-15s|%-11s|%-16s|%-16s|\n",
                        "Event ID","Event Name","Date","Capacity","Major","Quota","Type","Group Capacity","Group Quota","Min No. In Group","Max No. In Group");
                for(int k=0; k<2;k++){
                    if (eothers.get(k) instanceof EventIndividual) {
                        ((EventIndividual)eothers.get(k)).printDetail();
                    }
                    else if ((eothers.get(k) instanceof EventGroup)) {
                        ((EventGroup)eothers.get(k)).printDetail();
                    }
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