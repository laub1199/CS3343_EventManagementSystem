package EventManagementSystem;

public class SystemDate extends Day{
    //A class to represent the unique system date
    private static SystemDate instance;
    private SystemDate(String sDay) {super(sDay);}
    public static SystemDate getInstance(){return instance;}

    public static void createTheInstance(String sDay)
    {
        if (instance==null) {
            instance = new SystemDate(sDay);
            System.out.println("SystemDate confirm: " + instance.toString());
        }
        else
            System.out.println("Cannot create one more system date instance.");
    }
}
