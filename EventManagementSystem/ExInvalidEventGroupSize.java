package EventManagementSystem;

public class ExInvalidEventGroupSize extends Exception {
    public ExInvalidEventGroupSize() {super("Event Group maximum and minimum number in one group should be at least 1.\n"
    		+ "Maximum number in one group should be greater or equals than minimum number in one group.\n"
    		+ "Capacity should be greater than minimum number in one group times group capacity.\n");}
}
