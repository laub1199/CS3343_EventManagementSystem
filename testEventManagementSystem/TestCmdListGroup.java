package testEventManagementSystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import EventManagementSystem.*;

public class TestCmdListGroup {
    private GroupHandler groupHandler;

    @BeforeEach
    public void setUp(){
        groupHandler = GroupHandler.getInstance();
    }

    @AfterEach
    public void tearDown(){
    }

    @Test   //test if cmd length is not equal to 2
    public void test_CmdListGroup_01() throws CloneNotSupportedException {
        String[] cmdPrats = {"list", "group", "group1"};
        CmdListGroup cmdListGroup = new CmdListGroup();
        String result = cmdListGroup.execute(cmdPrats);
        assertEquals("Wrong Command\nList group command should be \"list group\"\n", result);
    }

    @Test   //test if no group
    public void test_CmdListGroup_02() throws CloneNotSupportedException {
        String[] cmdPrats = {"list", "group"};
        CmdListGroup cmdListGroup = new CmdListGroup();
        String result = cmdListGroup.execute(cmdPrats);
        assertEquals("There are no group.", result);
    }

    @Test   //test show one group
    public void test_CmdListGroup_03() throws CloneNotSupportedException {
        Group group1 = new Group("g11111111", 5);
        groupHandler.createGroup(group1);

        String[] cmdPrats = {"list", "group"};
        CmdListGroup cmdListGroup = new CmdListGroup();
        String result = cmdListGroup.execute(cmdPrats);
        assertEquals("|GroupID   |Number Of Student |Max Number Of Student  |\n|g11111111 |0                 |5                      |\n", result);
    }
}
