package testEventManagementSystem;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import EventManagementSystem.*;

public class TestGroupHandler {
    GroupHandler groupHandler;

    @BeforeEach
    public void setUp(){
    	groupHandler = GroupHandler.getInstance();
    }

    @AfterEach
    public void tearDown(){
		groupHandler.getGroupList().clear();
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
    }

    @Test   //test getGroup find one group
    public void test_GroupHandler_03() throws ExGroupNotFound {
        Group group1 = new Group("g11111111", 5);
        Group group2 = new Group("g22222222", 3);

        groupHandler.createGroup(group1);
        groupHandler.createGroup(group2);

        Group result = groupHandler.getGroup("g11111111");
        assertEquals(group1, result);
    }

    @Test   //test getGroup find no group
    public void test_GroupHandler_04() throws ExGroupNotFound {
        Group group1 = new Group("g11111111", 5);
        Group group2 = new Group("g22222222", 3);

        groupHandler.createGroup(group1);
        groupHandler.createGroup(group2);

        Exception e = assertThrows(ExGroupNotFound.class, () -> groupHandler.getGroup("g33333333"));
        assertEquals("Group not found!\n", e.getMessage());
    }
    
    @Test   //test deleteGroup is work
    public void test_GroupHandler_05() throws ExGroupNotFound {
        Group group1 = new Group("g11111111", 5);
        Group group2 = new Group("g22222222", 3);

        groupHandler.createGroup(group1);
        groupHandler.createGroup(group2);
        groupHandler.deleteGroup(group1);

        Exception e = assertThrows(ExGroupNotFound.class, () -> groupHandler.getGroup("g11111111"));
        assertEquals("Group not found!\n", e.getMessage());
    }
    
    @Test   //test list group
    public void test_GroupHandler_06() throws ExGroupNotFound, ExMajorNotFound, ExStudentNotFound, ExStudentNotJoined {
        Group group1 = new Group("g11111111", 5);
        Group group2 = new Group("g22222222", 3);
        Group group3 = new Group("g33333333", 4);

        Student student = new Student("s00000001", Major.getMajor("cs"), "Sam", "Chow", 'M', 18);
        groupHandler.createGroup(group1);
        groupHandler.createGroup(group2);
        groupHandler.createGroup(group3);
        group1.addStudent(student);
        group2.addStudent(student);

        String expect = String.format("|%-10s|%-18s|%-23s|\n","g11111111", 1, 5);
        expect += String.format("|%-10s|%-18s|%-23s|\n","g22222222", 1, 3);

        String result = groupHandler.listGroupByStudentId("s00000001");
        assertEquals(expect, result);
    }
    
    @Test   //test list group
    public void test_GroupHandler_07() throws ExGroupNotFound, ExMajorNotFound, ExStudentNotFound, ExStudentNotJoined {
        Student student = new Student("s00000001", Major.getMajor("cs"), "Sam", "Chow", 'M', 18);
        Exception e = assertThrows(ExStudentNotJoined.class, () -> groupHandler.listGroupByStudentId("s00000001"));
        assertEquals("Student did not join any group!\n", e.getMessage());
    }
    
}
