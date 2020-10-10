package EventManagementSystem;

import java.util.Date;

public class SystemDate extends Date{
    //A class to represent the unique system date
    private static SystemDate instance;
    private SystemDate(String sDay) {super(sDay);}
    public static SystemDate getInstance(){return instance;}

    public static void createTheInstance(String sDay)
    {
        if (instance==null)
            instance = new SystemDate(sDay);
        else
            System.out.println("Cannot create one more system date instance.");
    }
}
