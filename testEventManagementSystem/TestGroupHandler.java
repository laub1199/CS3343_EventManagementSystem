package testEventManagementSystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import EventManagementSystem.*;

public class TestGroupHandler {
    GroupHandler groupHandler = GroupHandler.getInstance();

    @BeforeEach
    public void setUp(){
    }

    @AfterEach
    public void tearDown(){
    }

    @Test   //test listGroup no group
    public void test_GroupHandler_01(){
        String result = groupHandler.listGroup();
        assertEquals("There are no group.", result);
    }

    @Test   //test listGroup two groups
    public void test_GroupHandler_02(){

        Group group1 = new Group("g11111111", 5);
        Group group2 = new Group("g22222222", 3);

        groupHandler.createGroup(group1);
        groupHandler.createGroup(group2);

        String result = groupHandler.listGroup();
        assertEquals("|GroupID   |Number Of Student |Max Number Of Student  |\n|g11111111 |0                 |5                      |\n|g22222222 |0                 |3                      |\n", result);
        groupHandler.deleteGroup(group1);
        groupHandler.deleteGroup(group2);
    }

    @Test   //test getGroup find one group
    public void test_GroupHandler_03() throws ExGroupNotFound {
        Group group1 = new Group("g11111111", 5);
        Group group2 = new Group("g22222222", 3);

        groupHandler.createGroup(group1);
        groupHandler.createGroup(group2);

        Group result = groupHandler.getGroup("g11111111");
        assertEquals(group1, result);
        groupHandler.deleteGroup(group1);
        groupHandler.deleteGroup(group2);
    }

    @Test   //test getGroup find no group
    public void test_GroupHandler_04() throws ExGroupNotFound {
        Group group1 = new Group("g11111111", 5);
        Group group2 = new Group("g22222222", 3);

        groupHandler.createGroup(group1);
        groupHandler.createGroup(group2);

        Exception e = assertThrows(ExGroupNotFound.class, () -> groupHandler.getGroup("g33333333"));
        assertEquals("Group not found!\n", e.getMessage());
        groupHandler.deleteGroup(group1);
        groupHandler.deleteGroup(group2);
    }
}
