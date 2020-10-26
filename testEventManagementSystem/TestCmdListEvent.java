package testEventManagementSystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import EventManagementSystem.*;

public class TestCmdListEvent {
    private EventAllocator eventallocator;

    @BeforeEach
    public void setUp(){
        eventallocator = EventAllocator.getInstance();
    }

    @AfterEach
    public void tearDown() {
    }

    @Test   //test if cmd length is not equal to 2
    public void test_CmdListEvent_01() throws CloneNotSupportedException {
        String[] cmdParts = {"list", "event", "event21"};
        CmdListEvent cmdListEvent = new CmdListEvent();
        String result = cmdListEvent.execute(cmdParts);
        assertEquals("Wrong Command\nList event command should be \"list event\"\n", result);
    }

    @Test   //test if no event
    public void test_CmdListEvent_02() throws CloneNotSupportedException {
        String[] cmdParts = {"list", "event"};
        CmdListEvent cmdListEvent = new CmdListEvent();
        String result = cmdListEvent.execute(cmdParts);
        assertEquals("There are no event.", result);
    }

    @Test   //test show one event
    public void test_CmdListEvent_03() throws CloneNotSupportedException, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate, ExDateFormatYear, ExMajorNotFound {
        EventIndividual event1 = new EventIndividual("e11111111", "evemt1", 10, new Day("18-Oct-2020"), Major.getMajor("cs"));
        eventallocator.addEvent(event1);

        String[] cmdParts = {"list", "event"};
        CmdListEvent cmdListEvent = new CmdListEvent();
        String result = cmdListEvent.execute(cmdParts);
        //String expect = "|Event ID  |Event Name                    |Date        |Capacity|Major                         |Quota|Type      |Group Capacity |Group Quota|Min No. In Group|Max No. In Group|\n" +
        //        "|evemt1    |e11111111                     |18-Oct-2020 |10      |Computer Science              |10   |Individual|/              |/          |/               |/               |\n";
        assertEquals("|Event ID  |Event Name                    |Date        |Capacity|Major                         |Quota|Type      |Group Capacity |Group Quota|Min No. In Group|Max No. In Group|\n|evemt1    |e11111111                     |18-Oct-2020 |10      |Computer Science              |10   |Individual|/              |/          |/               |/               |\n", result);
    }

}
