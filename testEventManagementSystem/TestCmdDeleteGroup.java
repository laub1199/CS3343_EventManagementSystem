package testEventManagementSystem;

import EventManagementSystem.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCmdDeleteGroup {
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
    public void testCmdDeleteGroup() throws Exception {
        String[] cmd = {"delete", "group", "g00000001"};
        String result = new CmdDeleteGroup().execute(cmd);
        assertEquals("Deleted group with GroupID: g00000001.", result);
    }

    @Test
    public void testWrongInput_WrongCommand() throws Exception {
        String[] cmd = {"delete", "group"};
        String result = new CmdDeleteGroup().execute(cmd);
        String expected = "Wrong Command\n";
        expected += "Delete group command should be \"delete group gXXXXXXXXX\"\n";
        assertEquals(expected, result);
    }

    @Test
    public void testWrongInput_InvalidGroupID() throws Exception {
        String[] cmd = {"delete", "event", "g01"};
        String result = new CmdDeleteGroup().execute(cmd);
        assertEquals("Invalid group ID!\n", result);
    }

    @Test
    public void testWrongInput_WrongNumberFormat() throws Exception {
        String[] cmd = {"delete", "event", "gg0000001"};
        String result = new CmdDeleteGroup().execute(cmd);
        assertEquals("Invalid group ID!\n", result);
    }

    @Test
    public void testWrongInput_GroupNotFound() throws Exception {
        String[] cmd = {"delete", "event", "g00000003"};
        String result = new CmdDeleteGroup().execute(cmd);
        assertEquals("Group not found!\n", result);
    }
}
