package EventManagementSystem;

import java.beans.EventHandler;
import java.util.ArrayList;


public class CmdRecommend implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {


        try{
            if (cmdParts[1].length() != 9 || cmdParts[1].charAt(0) != 's') {
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
                System.out.println("There is no recommendation for your major!");
            }else{
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
                System.out.println("There is no recommendation for event out of your major!");
            }else{
                for(int k=0; k<2;k++){
                    if (eothers.get(k) instanceof EventIndividual) {
                        ((EventIndividual)eothers.get(k)).printDetail();
                    }
                    else if ((eothers.get(k) instanceof EventGroup)) {
                        ((EventGroup)eothers.get(k)).printDetail();
                    }
                }
            }


        }catch(ExNoSuitableEvent e){
            System.out.println(e.getMessage());
        }catch(ExStudentNotFound e){
            System.out.println(e.getMessage());
        }catch(ExInvalidStudentID e){
            System.out.println(e.getMessage());
        }
    }
}