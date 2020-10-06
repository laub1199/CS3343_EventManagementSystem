package EventManagementSystem;

public class ExMajorTooLong extends Exception
{
    public ExMajorTooLong() { super("Major cannot exceed 10 characters!"); }
}