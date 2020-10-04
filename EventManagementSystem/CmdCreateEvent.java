package EventManagementSystem;

import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CmdCreateEvent implements Command {
    @Override
    public void execute(String[] cmdParts) throws CloneNotSupportedException {
        try {
            if (cmdParts.length != 5) {
                throw new ExWrongCommand();
            }
            EventAllocator instance = EventAllocator.getInstance();
            Date eDate = new SimpleDateFormat("dd/MM/yyyy").parse(cmdParts[5]);
            instance.addEvent(cmdParts[2], cmdParts[3], Integer.parseInt(cmdParts[4]), eDate);
        } catch (NumberFormatException e) {
            System.out.println("Wrong number format!");
        } catch (ExWrongCommand | ExInvalidEventID | ExInvalidEventDate e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("Wrong date format!");
        }
    }
}
