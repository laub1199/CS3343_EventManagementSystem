package EventManagementSystem;

import java.beans.EventHandler;
import java.util.ArrayList;


public class CmdRecommend implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {


        try{
            StudentHandler ss = StudentHandler.getInstance();
            Student student = ss.getStudent(cmdParts[1]);
            EventAllocator eee = EventAllocator.getInstance();

            ArrayList<Event> events = new ArrayList<>();
            events = eee.getEventList();
            ArrayList<Event> emaj = new ArrayList<>();
            ArrayList<Event> epublic = new ArrayList<>();

            for (int i= 0; i< events.size(); i++ ){
                if(events.get(i).getMajor() == student.getMajor()){
                    emaj.add(events.get(i));
                }
                if(events.get(i).getMajor() == null){
                    epublic.add(events.get(i));
                }
            }
            if (emaj == null && epublic == null){
                throw new ExNoSuitableEvent();
            }

            if(emaj == null){
                System.out.println("There is no recommendation for your major!");
            }else{
                for(int j=0; j<2;j++){
                    System.out.println(emaj.get(j));
                }
            }
            if(epublic == null){
                System.out.println("There is no recommendation for event out of your major!");
            }else{
                for(int k=0; k<2;k++){
                    System.out.println(epublic.get(k));
                }
            }


        }catch(ExNoSuitableEvent e){
            System.out.println(e.getMessage());
        }catch(ExStudentNotFound e){
            System.out.println(e.getMessage());
        }
    }
}