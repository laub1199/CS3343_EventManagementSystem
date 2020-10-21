package testEventManagementSystem;

import EventManagementSystem.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCmdDeleteEvent {
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
    public void testCmdDeleteEvent() throws Exception {
        String[] cmd = {"delete", "event", "e00000001"};
        String result = new CmdDeleteEvent().execute(cmd);
        assertEquals("Deleted event with EventID: e00000001.", result);
    }

    @Test
    public void testWrongInput_WrongCommand() throws Exception {
        String[] cmd = {"delete", "event"};
        String result = new CmdDeleteEvent().execute(cmd);
        String expected = "Wrong Command\n";
        expected += "Delete event command should be \"delete event eXXXXXXXXX\"\n";
        assertEquals(expected, result);
    }

    @Test
    public void testWrongInput_InvalidEventID() throws Exception {
        String[] cmd = {"delete", "event", "e01"};
        String result = new CmdDeleteEvent().execute(cmd);
        assertEquals("Invalid event ID!\n", result);
    }

    @Test
    public void testWrongInput_WrongNumberFormat() throws Exception {
        String[] cmd = {"delete", "event", "ee0000001"};
        String result = new CmdDeleteEvent().execute(cmd);
        assertEquals("Invalid event ID!\n", result);
    }

    @Test
    public void testWrongInput_EventNotFound() throws Exception {
        String[] cmd = {"delete", "event", "e00000003"};
        String result = new CmdDeleteEvent().execute(cmd);
        assertEquals("Event not found!\n", result);
    }
}
