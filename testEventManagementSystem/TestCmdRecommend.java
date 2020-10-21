package testEventManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import EventManagementSystem.CmdRecommend;
import EventManagementSystem.Day;
import EventManagementSystem.Event;
import EventManagementSystem.EventAllocator;
import EventManagementSystem.EventGroup;
import EventManagementSystem.EventIndividual;
import EventManagementSystem.Group;
import EventManagementSystem.GroupHandler;
import EventManagementSystem.Major;
import EventManagementSystem.Student;
import EventManagementSystem.StudentHandler;
import EventManagementSystem.SystemDate;

public class TestCmdRecommend {

    @BeforeEach
    public void setUp() throws Exception {
        SystemDate.createTheInstance("01-jan-2020");
        EventAllocator eventAllocator = EventAllocator.getInstance();
        StudentHandler studentHandler = StudentHandler.getInstance();


        // create student
        studentHandler.createStudent(new Student("s00000001", Major.getMajor("cs"), "Sam", "Chow", 'M', 18));


        // create events
        eventAllocator.addEvent((Event)new EventIndividual("a-game", "e00000001", 11, new Day("13-oct-2020"), Major.getMajor("cs")));
        eventAllocator.addEvent((Event)new EventIndividual("b-game", "e00000002", 11, new Day("15-oct-2020"), Major.getMajor("ds")));


        eventAllocator.addEvent((Event)new EventGroup("e-game", "e00000003", 6, new Day("14-oct-2020"), Major.getMajor("cs"), 2, 2, 3));
        eventAllocator.addEvent((Event)new EventGroup("f-game", "e00000004", 6, new Day("16-oct-2020"), Major.getMajor("cm"), 2, 2, 3));
        eventAllocator.addEvent((Event)new EventGroup("g-game", "e00000005", 6, new Day("18-oct-2020"), Major.getMajor("cs"), 2, 2, 3));

    }

    @AfterEach
    public void tearDown() throws Exception {
        EventAllocator eventAllocator = EventAllocator.getInstance();
        eventAllocator.getEventList().clear();
        StudentHandler studentHandler = StudentHandler.getInstance();
        studentHandler.getStudentList().clear();
    }

    @Test
    public void testcmdRecommend001() throws Exception { //Excessive command
        String[] cmd = {"recommend", "s00000001","Peter"};
        String result = (new CmdRecommend()).execute(cmd);
        String expected = "Wrong Command\n";
        expected += "Recommend command should be \"recommend sXXXXXXXXX\"\n";
        assertEquals(expected, result);
    }

    @Test
    public void testcmdRecommend002() throws Exception { //SID first char is not s
        String[] cmd = {"recommend", "000000011"};
        String result = (new CmdRecommend()).execute(cmd);
        String expected = "Wrong Command\n";
        expected += "Recommend command should be \"recommend sXXXXXXXXX\"\n";
        assertEquals(expected, result);
    }

    @Test
    public void testcmdRecommend003() throws Exception { //SID length >9
        String[] cmd = {"recommend", "s000000011"};
        String result = (new CmdRecommend()).execute(cmd);
        String expected = "Invalid student ID!\n";
        assertEquals(expected, result);
    }


    @Test
    public void testcmdRecommend004() throws Exception { //no recommendation
        EventAllocator eventAllocator = EventAllocator.getInstance();
        eventAllocator.deleteEvent(eventAllocator.findEventByID("e00000001"));
        eventAllocator.deleteEvent(eventAllocator.findEventByID("e00000002"));
        eventAllocator.deleteEvent(eventAllocator.findEventByID("e00000003"));
        eventAllocator.deleteEvent(eventAllocator.findEventByID("e00000004"));
        eventAllocator.deleteEvent(eventAllocator.findEventByID("e00000005"));
        String[] cmd = {"recommend", "s00000001"};
        String result = (new CmdRecommend()).execute(cmd);
        String expected = "Sorry, there is no recommended event for you.\n";
        assertEquals(expected, result);
    }

    @Test
    public void testcmdRecommend005() throws Exception { //both have full recommendation
        String[] cmd = {"recommend", "s00000001"};
        String result = (new CmdRecommend()).execute(cmd);
        String expected = "Here are the recommended events from your department:\n" +
                "|Event ID  |Event Name                    |Date        |Capacity|Major                         |Quota|Type      |Group Capacity |Group Quota|Min No. In Group|Max No. In Group|\n" +
                "|e00000001 |a-game                        |13-Oct-2020 |11      |Computer Science              |11   |Individual|/              |/          |/               |/               |\n" +
                "|e00000003 |e-game                        |14-Oct-2020 |6       |Computer Science              |6    |Group     |2              |2          |2               |3               |\n" +
                "Here are the recommended events outside your department:\n" +
                "|Event ID  |Event Name                    |Date        |Capacity|Major                         |Quota|Type      |Group Capacity |Group Quota|Min No. In Group|Max No. In Group|\n" +
                "|e00000002 |b-game                        |15-Oct-2020 |11      |Data Science                  |11   |Individual|/              |/          |/               |/               |\n" +
                "|e00000004 |f-game                        |16-Oct-2020 |6       |Creative Media                |6    |Group     |2              |2          |2               |3               |\n" ;
        assertEquals(expected, result);
    }

    @Test
    public void testcmdRecommend006() throws Exception { //no recommendation for major
        EventAllocator eventAllocator = EventAllocator.getInstance();
        eventAllocator.deleteEvent(eventAllocator.findEventByID("e00000001"));
        eventAllocator.deleteEvent(eventAllocator.findEventByID("e00000003"));
        eventAllocator.deleteEvent(eventAllocator.findEventByID("e00000005"));
        String[] cmd = {"recommend", "s00000001"};
        String result = (new CmdRecommend()).execute(cmd);
        String expected = "There is no recommended event from your department!\n" +
                "Here are the recommended events outside your department:\n" +
                "|Event ID  |Event Name                    |Date        |Capacity|Major                         |Quota|Type      |Group Capacity |Group Quota|Min No. In Group|Max No. In Group|\n" +
                "|e00000002 |b-game                        |15-Oct-2020 |11      |Data Science                  |11   |Individual|/              |/          |/               |/               |\n" +
                "|e00000004 |f-game                        |16-Oct-2020 |6       |Creative Media                |6    |Group     |2              |2          |2               |3               |\n";
        assertEquals(expected, result);
    }

    @Test
    public void testcmdRecommend007() throws Exception { //no recommendation for other
        EventAllocator eventAllocator = EventAllocator.getInstance();
        eventAllocator.deleteEvent(eventAllocator.findEventByID("e00000002"));
        eventAllocator.deleteEvent(eventAllocator.findEventByID("e00000004"));
        String[] cmd = {"recommend", "s00000001"};
        String result = (new CmdRecommend()).execute(cmd);
        String expected = "Here are the recommended events from your department:\n" +
                "|Event ID  |Event Name                    |Date        |Capacity|Major                         |Quota|Type      |Group Capacity |Group Quota|Min No. In Group|Max No. In Group|\n" +
                "|e00000001 |a-game                        |13-Oct-2020 |11      |Computer Science              |11   |Individual|/              |/          |/               |/               |\n" +
                "|e00000003 |e-game                        |14-Oct-2020 |6       |Computer Science              |6    |Group     |2              |2          |2               |3               |\n" +
                "There is no recommended event from other department!\n";
        assertEquals(expected, result);
    }











}
