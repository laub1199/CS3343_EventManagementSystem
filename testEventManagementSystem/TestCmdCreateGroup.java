package testEventManagementSystem;

import EventManagementSystem.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCmdCreateGroup {
    @BeforeEach
    public void setUp() throws Exception {
        GroupHandler groupHandler = GroupHandler.getInstance();

        // create groups
        groupHandler.createGroup(new Group("g00000001", 4));
        groupHandler.createGroup(new Group("g00000002", 3));
    }

    @AfterEach
    public void tearDown() throws Exception {
        GroupHandler groupHandler = GroupHandler.getInstance();
        groupHandler.getGroupList().clear();
    }

    @Test
    public void testCmdCreateGroup() throws Exception {
        String[] cmd = {"create", "group", "g00000003", "5"};
        String result = new CmdCreateGroup().execute(cmd);
        assertEquals("Created group with GroupID: g00000003.", result);
    }

    @Test
    public void testWrongCommand() throws Exception {
        String[] cmd = {"create", "group", "g00000003"};
        String result = new CmdCreateGroup().execute(cmd);
        String expected = "Wrong Command\n";
        expected += "Create group command should be \"create group gXXXXXXXXX <max no. in one group>\".\n";
        assertEquals(expected, result);
    }

    @Test
    public void testWrongInput_InvalidGroupID() throws Exception {
        String[] cmd = {"create", "group", "g03", "5"};
        String result = new CmdCreateGroup().execute(cmd);
        assertEquals("Invalid group ID!\n", result);
    }

    @Test
    public void testWrongInput_WrongNumberFormatGroupID() throws Exception {
        String[] cmd = {"create", "group", "gg0000003", "5"};
        String result = new CmdCreateGroup().execute(cmd);
        assertEquals("Invalid group ID!\n", result);
    }

    @Test
    public void testWrongInput_GroupStudentTooLess() throws Exception {
        String[] cmd = {"create", "group", "g00000003", "1"};
        String result = new CmdCreateGroup().execute(cmd);
        assertEquals("A group should contain at least two students.\n", result);
    }

    @Test
    public void testWrongInput_GroupFound() throws Exception {
        String[] cmd = {"create", "group", "g00000001", "5"};
        String result = new CmdCreateGroup().execute(cmd);
        assertEquals("Invalid group ID!\n", result);
    }

    @Test
    public void testWrongInput_WrongNumberFormat() throws Exception {
        String[] cmd = {"create", "group", "g00000003", "A"};
        String result = new CmdCreateGroup().execute(cmd);
        assertEquals("Wrong number format!\n", result);
    }
}
