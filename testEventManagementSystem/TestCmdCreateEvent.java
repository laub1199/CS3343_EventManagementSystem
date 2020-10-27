package testEventManagementSystem;

import EventManagementSystem.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCmdCreateEvent {
    @BeforeEach
    public void setUp() throws Exception {
        SystemDate.createTheInstance("10-oct-2020");
        EventAllocator eventAllocator = EventAllocator.getInstance();

        // create events
        eventAllocator.addEvent((Event)new EventIndividual("a-game", "e00000001", 11, new Day("13-oct-2020"), Major.getMajor("cs")));
        eventAllocator.addEvent((Event)new EventGroup("b-game", "e00000002", 6, new Day("13-oct-2020"), Major.getMajor("cs"), 2, 2, 3));
    }

    @AfterEach
    public void tearDown() throws Exception {
        EventAllocator eventAllocator = EventAllocator.getInstance();
        eventAllocator.getEventList().clear();
    }

    @Test
    public void testCmdCreateEvent_individual() throws Exception {
        String[] cmd = {"create", "event", "c-game", "e00000003", "10", "14-oct-2020", "cm"};
        String result = new CmdCreateEvent().execute(cmd);
        assertEquals("Created c-game event with EventID: e00000003.", result);
    }

    @Test
    public void testCmdCreateEvent_group() throws Exception {
        String[] cmd = {"create", "event", "c-game", "e00000003", "10", "14-oct-2020", "cm", "3", "3", "5"};
        String result = new CmdCreateEvent().execute(cmd);
        assertEquals("Created c-game event with EventID: e00000003.", result);
    }

    @Test
    public void testWrongCommand() throws Exception {
        String[] cmd = {"create", "event", "c-game", "e00000003", "10", "14-oct-2020"};
        String result = new CmdCreateEvent().execute(cmd);
        String expected = "Wrong Command\n";
        expected += "\nThere are two ways for create event command.";
        expected += "Create individual event: \"create event <name> eXXXXXXXXX <capacity> <dd-mmm-yyyy> <major>\".";
        expected += "Create group event: \"create event <name> eXXXXXXXXX <capacity> <dd-mmm-yyyy> <major> <group capacity> <min no. in one group> <max no. in one group>\".\n";
        assertEquals(expected, result);
    }

    @Test
    public void testWrongInput_EventNameTooLong() throws Exception {
        String[] cmd = {"create", "event", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "e00000003", "10", "14-oct-2020", "cm"};
        String result = new CmdCreateEvent().execute(cmd);
        assertEquals("Event name cannot exceed 30 characters!\n", result);
    }

    @Test
    public void testWrongInput_WrongNumberFormat() throws Exception {
        String[] cmd = {"create", "event", "c-game", "e00000003", "A", "14-oct-2020", "cm"};
        String result = new CmdCreateEvent().execute(cmd);
        assertEquals("Wrong number format!", result);
    }

    @Test
    public void testWrongInput_InvalidEventID() throws Exception {
        String[] cmd = {"create", "event", "c-game", "e03", "10", "14-oct-2020", "cm"};
        String result = new CmdCreateEvent().execute(cmd);
        assertEquals("Invalid event ID!\n", result);
    }

    @Test
    public void testWrongInput_WrongEventIDFormat() throws Exception {
        String[] cmd = {"create", "event", "c-game", "ee0000003", "10", "14-oct-2020", "cm"};
        String result = new CmdCreateEvent().execute(cmd);
        assertEquals("Invalid event ID!\n", result);
    }

    @Test
    public void testWrongInput_InvalidEventDate() throws Exception {
        String[] cmd = {"create", "event", "c-game", "e00000003", "10", "9-oct-2020", "cm"};
        String result = new CmdCreateEvent().execute(cmd);
        assertEquals("Invalid event date!\n", result);
    }

    @Test
    public void testWrongInput_InvalidEventCapacity() throws Exception {
        String[] cmd = {"create", "event", "c-game", "e00000003", "0", "14-oct-2020", "cm"};
        String result = new CmdCreateEvent().execute(cmd);
        assertEquals("Event capacity should be at least 1.\n", result);
    }

    @Test
    public void testWrongInput_InvalidEventGroupSize() throws Exception {
        String[] cmd = {"create", "event", "c-game", "e00000003", "10", "14-oct-2020", "cm", "3", "0", "5"};
        String result = new CmdCreateEvent().execute(cmd);
        String expected = "Event Group maximum and minimum number in one group should be at least 1.\n";
        expected += "Maximum number in one group should be greater or equals than minimum number in one group.\n";
        expected += "Capacity should be greater than minimum number in one group times group capacity.\n";
        assertEquals(expected, result);
    }

    @Test
    public void testWrongInput_InvalidEventGroupCapacity() throws Exception {
        String[] cmd = {"create", "event", "c-game", "e00000003", "10", "14-oct-2020", "cm", "0", "3", "5"};
        String result = new CmdCreateEvent().execute(cmd);
        assertEquals("Event Group's group capacity should be at least 1 and less than capacity.\n", result);
    }

    @Test
    public void testWrongInput_EventFound() throws Exception {
        String[] cmd = {"create", "event", "c-game", "e00000001", "10", "14-oct-2020", "cm"};
        String result = new CmdCreateEvent().execute(cmd);
        assertEquals("Invalid event ID!\n", result);
    }
}
