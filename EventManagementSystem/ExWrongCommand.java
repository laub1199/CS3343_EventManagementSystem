package EventManagementSystem;

public class ExWrongCommand extends Exception
{
    public ExWrongCommand() { super("Wrong Command\n"); }
    public ExWrongCommand(String msg) { super(msg);}
}