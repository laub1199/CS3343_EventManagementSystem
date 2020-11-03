package testEventManagementSystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import EventManagementSystem.*;

public class TestCmdListStudentJoinedEvent {
    private StudentHandler studentHandler;
    private EventAllocator eventAllocator;


    @BeforeAll
    public static void init() throws ExDateFormatDay, ExDateFormatMonth, ExInvalidDate, ExDateFormatYear {
        SystemDate.createTheInstance("30-Oct-2020");
    }

    @BeforeEach
    public void setUp(){
        studentHandler = StudentHandler.getInstance();
        eventAllocator = EventAllocator.getInstance();
    }

    @AfterEach
    public void tearDown(){
		eventAllocator.getEventList().clear();
		studentHandler.getStudentList().clear();
    }

    @Test   //test if cmd length is not equal to 4
    public void test_CmdListStudentJoinedEvent_01() throws CloneNotSupportedException {
        String[] cmdPart = {"list", "studentJoinedEvent", "s11111111"};
        CmdListStudentJoinedEvent cmdListStudentJoinedEvent = new CmdListStudentJoinedEvent();
        String result = cmdListStudentJoinedEvent.execute(cmdPart);
        assertEquals("Wrong Command\nList student joined event command should be \"list studentJoinedEvent sXXXXXXXX all\" or \"list studentJoinedEvent sXXXXXXXX pending\" or \"list studentJoinedEvent sXXXXXXXX end\"\n", result);
    }

    @Test   //test if student id is invalid (not start with "s")
    public void test_CmdListStudentJoinedEvent_02() throws CloneNotSupportedException {
        String[] cmdPart = {"list", "studentJoinedEvent", "e11111111", "all"};
        CmdListStudentJoinedEvent cmdListStudentJoinedEvent = new CmdListStudentJoinedEvent();
        String result = cmdListStudentJoinedEvent.execute(cmdPart);
        assertEquals("Wrong Command\nList student joined event command should be \"list studentJoinedEvent sXXXXXXXX all\" or \"list studentJoinedEvent sXXXXXXXX pending\" or \"list studentJoinedEvent sXXXXXXXX end\"\n", result);
    }

    @Test   //test if student id is invalid (length of student id is not equal to 9)
    public void test_CmdListStudentJoinedEvent_03() throws CloneNotSupportedException {
        String[] cmdPart = {"list", "studentJoinedEvent", "s1111111", "all"};
        CmdListStudentJoinedEvent cmdListStudentJoinedEvent = new CmdListStudentJoinedEvent();
        String result = cmdListStudentJoinedEvent.execute(cmdPart);
        assertEquals("Invalid student ID!\n", result);
    }

    @Test   //test if student id is invalid (student id less then 0)
    public void test_CmdListStudentJoinedEvent_04() throws CloneNotSupportedException {
        String[] cmdPart = {"list", "studentJoinedEvent", "s-1111111", "all"};
        CmdListStudentJoinedEvent cmdListStudentJoinedEvent = new CmdListStudentJoinedEvent();
        String result = cmdListStudentJoinedEvent.execute(cmdPart);
        assertEquals("Invalid student ID!\n", result);
    }
    
    @Test   //test if student id is invalid (student id is not number)
    public void test_CmdListStudentJoinedEvent_05() throws CloneNotSupportedException {
        String[] cmdPart = {"list", "studentJoinedEvent", "stesttest", "all"};
        CmdListStudentJoinedEvent cmdListStudentJoinedEvent = new CmdListStudentJoinedEvent();
        String result = cmdListStudentJoinedEvent.execute(cmdPart);
        assertEquals("Invalid student ID!\n", result);
    }

    @Test   //test list all event (one student and not jointed event)
    public void test_CmdListStudentJoinedEvent_06() throws CloneNotSupportedException, ExMajorNotFound {
        Student student1 = new Student("s11111111", Major.getMajor("cs"), "fn", "ln", 'f', 19);
        studentHandler.createStudent(student1);

        String[] cmdPart = {"list", "studentJoinedEvent", "s11111111", "all"};
        CmdListStudentJoinedEvent cmdListStudentJoinedEvent = new CmdListStudentJoinedEvent();
        String result = cmdListStudentJoinedEvent.execute(cmdPart);
        assertEquals("All events:\n|Event ID    |Event Name                    |\n", result);
    }

    @Test   //test list pending event (one student and not jointed event)
    public void test_CmdListStudentJoinedEvent_07() throws CloneNotSupportedException, ExMajorNotFound {
        Student student1 = new Student("s11111111", Major.getMajor("cs"), "fn", "ln", 'f', 19);
        studentHandler.createStudent(student1);

        String[] cmdPart = {"list", "studentJoinedEvent", "s11111111", "pending"};
        CmdListStudentJoinedEvent cmdListStudentJoinedEvent = new CmdListStudentJoinedEvent();
        String result = cmdListStudentJoinedEvent.execute(cmdPart);
        assertEquals("Pending events:\n|Event ID    |Event Name                    |\n", result);
    }

    @Test   //test list end event (one student and not jointed event)
    public void test_CmdListStudentJoinedEvent_08() throws CloneNotSupportedException, ExMajorNotFound {
        Student student1 = new Student("s11111111", Major.getMajor("cs"), "fn", "ln", 'f', 19);
        studentHandler.createStudent(student1);

        String[] cmdPart = {"list", "studentJoinedEvent", "s11111111", "end"};
        CmdListStudentJoinedEvent cmdListStudentJoinedEvent = new CmdListStudentJoinedEvent();
        String result = cmdListStudentJoinedEvent.execute(cmdPart);
        assertEquals("End events:\n|Event ID    |Event Name                    |\n", result);
    }

    @Test   //test list all event (one student and one jointed event)
    public void test_CmdListStudentJoinedEvent_09() throws CloneNotSupportedException, ExMajorNotFound, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate, ExDateFormatYear {
        Student student1 = new Student("s11111111", Major.getMajor("cs"), "fn", "ln", 'f', 19);
        studentHandler.createStudent(student1);

        EventIndividual eventIndividual = new EventIndividual("e11111111", "event1", 10, new Day("18-Oct-2020"), Major.getMajor("cs"));
        eventAllocator.addEvent(eventIndividual);
        eventAllocator.studentJoinEvent(student1, eventIndividual);

        String[] cmdPart = {"list", "studentJoinedEvent", "s11111111", "all"};
        CmdListStudentJoinedEvent cmdListStudentJoinedEvent = new CmdListStudentJoinedEvent();
        String result = cmdListStudentJoinedEvent.execute(cmdPart);
        //System.out.println(result);
        assertEquals("All events:\n|Event ID    |Event Name                    |\n|event1      |e11111111                     |\n", result);
    }

    @Test   //test list pending event (one student and one jointed event)
    public void test_CmdListStudentJoinedEvent_10() throws CloneNotSupportedException, ExMajorNotFound, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate, ExDateFormatYear {
        Student student1 = new Student("s11111111", Major.getMajor("cs"), "fn", "ln", 'f', 19);
        studentHandler.createStudent(student1);

        EventIndividual eventIndividual = new EventIndividual("e11111111", "event1", 10, new Day("22-Oct-2020"), Major.getMajor("cs"));
        eventAllocator.addEvent(eventIndividual);
        eventAllocator.studentJoinEvent(student1, eventIndividual);

        String[] cmdPart = {"list", "studentJoinedEvent", "s11111111", "pending"};
        CmdListStudentJoinedEvent cmdListStudentJoinedEvent = new CmdListStudentJoinedEvent();
        String result = cmdListStudentJoinedEvent.execute(cmdPart);
        assertEquals("Pending events:\n|Event ID    |Event Name                    |\n|event1      |e11111111                     |\n", result);
    }

    @Test   //test list pending event (one student and one jointed event and one end event)
    public void test_CmdListStudentJoinedEvent_11() throws CloneNotSupportedException, ExMajorNotFound, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate, ExDateFormatYear {
        Student student1 = new Student("s11111111", Major.getMajor("cs"), "fn", "ln", 'f', 19);
        studentHandler.createStudent(student1);

        EventIndividual eventIndividual1 = new EventIndividual("e11111111", "event1", 10, new Day("22-Nov-2020"), Major.getMajor("cs"));
        EventIndividual eventIndividual2 = new EventIndividual("e22222222", "event1", 10, new Day("7-Oct-2020"), Major.getMajor("cs"));
        eventAllocator.addEvent(eventIndividual1);
        eventAllocator.studentJoinEvent(student1, eventIndividual1);
        eventAllocator.addEvent(eventIndividual2);
        eventAllocator.studentJoinEvent(student1, eventIndividual2);

        String[] cmdPart = {"list", "studentJoinedEvent", "s11111111", "pending"};
        CmdListStudentJoinedEvent cmdListStudentJoinedEvent = new CmdListStudentJoinedEvent();
        String result = cmdListStudentJoinedEvent.execute(cmdPart);
        assertEquals("Pending events:\n|Event ID    |Event Name                    |\n|event1      |e11111111                     |\n", result);
    }

    @Test   //test list pending event (one student and one end event)
    public void test_CmdListStudentJoinedEvent_12() throws CloneNotSupportedException, ExMajorNotFound, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate, ExDateFormatYear {
        Student student1 = new Student("s11111111", Major.getMajor("cs"), "fn", "ln", 'f', 19);
        studentHandler.createStudent(student1);

        EventIndividual eventIndividual = new EventIndividual("e11111111", "event1", 10, new Day("9-Oct-2020"), Major.getMajor("cs"));
        eventAllocator.addEvent(eventIndividual);
        eventAllocator.studentJoinEvent(student1, eventIndividual);

        String[] cmdPart = {"list", "studentJoinedEvent", "s11111111", "end"};
        CmdListStudentJoinedEvent cmdListStudentJoinedEvent = new CmdListStudentJoinedEvent();
        String result = cmdListStudentJoinedEvent.execute(cmdPart);
        assertEquals("End events:\n|Event ID    |Event Name                    |\n|event1      |e11111111                     |\n", result);
    }

    @Test   //test list end event (one student and one jointed event and one pending event)
    public void test_CmdListStudentJoinedEvent_13() throws CloneNotSupportedException, ExMajorNotFound, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate, ExDateFormatYear {
        Student student1 = new Student("s11111111", Major.getMajor("cs"), "fn", "ln", 'f', 19);
        studentHandler.createStudent(student1);

        EventIndividual eventIndividual1 = new EventIndividual("e11111111", "event1", 10, new Day("22-Nov-2020"), Major.getMajor("cs"));
        EventIndividual eventIndividual2 = new EventIndividual("e22222222", "event1", 10, new Day("5-Oct-2020"), Major.getMajor("cs"));
        eventAllocator.addEvent(eventIndividual1);
        eventAllocator.studentJoinEvent(student1, eventIndividual1);
        eventAllocator.addEvent(eventIndividual2);
        eventAllocator.studentJoinEvent(student1, eventIndividual2);

        String[] cmdPart = {"list", "studentJoinedEvent", "s11111111", "end"};
        CmdListStudentJoinedEvent cmdListStudentJoinedEvent = new CmdListStudentJoinedEvent();
        String result = cmdListStudentJoinedEvent.execute(cmdPart);
        assertEquals("End events:\n|Event ID    |Event Name                    |\n|event1      |e22222222                     |\n", result);
    }
    
    @Test   //test if cmd 4th input is not valid
    public void test_CmdListStudentJoinedEvent_14() throws CloneNotSupportedException, ExMajorNotFound {
    	Student student1 = new Student("s11111111", Major.getMajor("cs"), "fn", "ln", 'f', 19);
        studentHandler.createStudent(student1);
        String[] cmdPart = {"list", "studentJoinedEvent", "s11111111", "test"};
        CmdListStudentJoinedEvent cmdListStudentJoinedEvent = new CmdListStudentJoinedEvent();
        String result = cmdListStudentJoinedEvent.execute(cmdPart);
        assertEquals("Wrong Command\nList student joined event command should be \"list studentJoinedEvent sXXXXXXXX all\" or \"list studentJoinedEvent sXXXXXXXX pending\" or \"list studentJoinedEvent sXXXXXXXX end\"\n", result);
    }
    
}
