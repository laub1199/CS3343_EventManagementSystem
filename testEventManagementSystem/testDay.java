package testEventManagementSystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import EventManagementSystem.Day;
import EventManagementSystem.Event;
import EventManagementSystem.EventAllocator;
import EventManagementSystem.EventGroup;
import EventManagementSystem.ExDateFormatDay;
import EventManagementSystem.ExDateFormatMonth;
import EventManagementSystem.ExDateFormatYear;
import EventManagementSystem.ExEventNotFound;
import EventManagementSystem.ExInvalidDate;
import EventManagementSystem.GroupHandler;
import EventManagementSystem.Major;
import EventManagementSystem.Student;
import EventManagementSystem.StudentHandler;
import EventManagementSystem.SystemDate;

public class TestDay {
	@BeforeAll
    public static void init() throws ExDateFormatDay, ExDateFormatMonth, ExInvalidDate, ExDateFormatYear {
	}
    
    /**
     * Sets up the test fixture.
     * Called before every test case method.
     */
	@BeforeEach
	public void setUp() throws Exception {
		
	}
    /**
     * Tears down the test fixture.
     * Called after every test case method.
     */
	@AfterEach
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testIsLeapYear01() {
		boolean result = Day.isLeapYear(2000);
		assertEquals(true, result);
	}
	
	@Test
	public void testIsLeapYear02() {
		boolean result = Day.isLeapYear(2100);
		assertEquals(false, result);
	}
	
	@Test
	public void testIsLeapYear03() {
		boolean result = Day.isLeapYear(2020);
		assertEquals(true, result);
	}
	
	@Test
	public void testIsLeapYear04() {
		boolean result = Day.isLeapYear(2019);
		assertEquals(false, result);
	}
	
	@Test
	public void testValid01() throws ExInvalidDate {
		boolean result = Day.valid(2020,2,29);
		assertEquals(true, result);
	}
	
	@Test
	public void testValid02() throws ExInvalidDate {
		boolean result = Day.valid(2019,2,28);
		assertEquals(true, result);
	}
	
	@Test
	public void testValid03() throws ExInvalidDate {
		boolean result = Day.valid(2020,1,31);
		assertEquals(true, result);
	}
	
	@Test
	public void testValid04() throws ExInvalidDate {
		boolean result = Day.valid(2020,3,31);
		assertEquals(true, result);
	}
	
	@Test
	public void testValid05() throws ExInvalidDate {
		boolean result = Day.valid(2020,4,30);
		assertEquals(true, result);
	}
	
	@Test
	public void testValid06() throws ExInvalidDate {
		boolean result = Day.valid(2020,5,31);
		assertEquals(true, result);
	}
	
	@Test
	public void testValid07() throws ExInvalidDate {
		boolean result = Day.valid(2020,6,30);
		assertEquals(true, result);
	}
	
	@Test
	public void testValid08() throws ExInvalidDate {
		boolean result = Day.valid(2020,7,31);
		assertEquals(true, result);
	}
	
	@Test
	public void testValid09() throws ExInvalidDate {
		boolean result = Day.valid(2020,8,31);
		assertEquals(true, result);
	}
	
	@Test
	public void testValid10() throws ExInvalidDate {
		boolean result = Day.valid(2020,9,30);
		assertEquals(true, result);
	}
	
	@Test
	public void testValid11() throws ExInvalidDate {
		boolean result = Day.valid(2020,10,31);
		assertEquals(true, result);
	}
	
	@Test
	public void testValid12() throws ExInvalidDate {
		boolean result = Day.valid(2020,11,30);
		assertEquals(true, result);
	}
	
	@Test
	public void testValid13() throws ExInvalidDate {
		boolean result = Day.valid(2020,12,31);
		assertEquals(true, result);
	}
	
	@Test
	public void testValid14() {
        Exception e = assertThrows(ExInvalidDate.class, () -> Day.valid(2020,13,28));
        assertEquals("Invalid Date\n", e.getMessage());	
    }
	
	@Test
	public void testValid15() throws ExInvalidDate {
		boolean result = Day.valid(2020,1,32);
		assertEquals(false, result);
	}
	
	@Test
	public void testValid16() throws ExInvalidDate {
		boolean result = Day.valid(2020,4,31);
		assertEquals(false, result);
	}
	
	@Test
	public void testValid17() throws ExInvalidDate {
		boolean result = Day.valid(2020,2,30);
		assertEquals(false, result);
	}
	
	@Test
	public void testIsValidDay01() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day day = new Day("10-Dec-2020");
		boolean result = day.isValidDay("1");
		assertEquals(true, result);
    }
	
	@Test
	public void testIsValidDay02() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day day = new Day("10-Dec-2020");
        Exception e = assertThrows(ExDateFormatDay.class, () -> day.isValidDay("32"));
		assertEquals("Invalid Date format - day, please enter 01 - 31!\n", e.getMessage());
    }
	
	@Test
	public void testIsValidDay03() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day day = new Day("10-Dec-2020");
        Exception e = assertThrows(ExDateFormatDay.class, () -> day.isValidDay("0"));
		assertEquals("Invalid Date format - day, please enter 01 - 31!\n", e.getMessage());
    }
	
	@Test
	public void testGetDay01() throws ExInvalidDate, ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth {
		Day day = new Day("10-Dec-2020");
		int result = day.getDay();
		assertEquals(10, result);
	}
	
	@Test
	public void testGetMonth01() throws ExInvalidDate, ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth {
		Day day = new Day("10-Dec-2020");
		int result = day.getMonth();
		assertEquals(12, result);
	}
	
	@Test
	public void testGetYear01() throws ExInvalidDate, ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth {
		Day day = new Day("10-Dec-2020");
		int result = day.getYear();
		assertEquals(2020, result);
	}
	
	@Test
	public void testSet01() throws ExInvalidDate, ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth {
		Day day = new Day("10-Dec-2020");
		day.set("10-Nov-2020");
		int result = day.getMonth();
		assertEquals(11, result);
	}
	
	@Test
	public void testSet02() throws ExInvalidDate, ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth {
		Day day = new Day("10-Dec-2020");
        Exception e = assertThrows(ExDateFormatDay.class, () -> day.set("40-Dec-2020"));
        assertEquals("Invalid Date format - day, please enter 01 - 31!\n", e.getMessage());	
	}
	
	@Test
	public void testSet03() throws ExInvalidDate, ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth {
		Day day = new Day("10-Dec-2020");
        Exception e = assertThrows(ExDateFormatMonth.class, () -> day.set("10-Abc-2020"));
        assertEquals("Invalid Date format - month, please enter a 3 letters english short form!\n", e.getMessage());	
	}
	
	@Test
	public void testSet04() throws ExInvalidDate, ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth {
		Day day = new Day("10-Dec-2020");
        Exception e = assertThrows(ExDateFormatYear.class, () -> day.set("10-Dec-0"));
        assertEquals("Invalid Date format - month, year must be larger than 0!\n", e.getMessage());	
	}
	
	@Test
	public void testSet05() throws ExInvalidDate, ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth {
		Day day = new Day("10-Dec-2020");
        Exception e = assertThrows(ExInvalidDate.class, () -> day.set("10-Dec"));
        assertEquals("Invalid Date\n", e.getMessage());	
	}
	
	@Test
	public void testSet06() throws ExInvalidDate, ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth {
		Day day = new Day("10-Dec-2020");
        Exception e = assertThrows(ExInvalidDate.class, () -> day.set("29-Feb-2019"));
        assertEquals("Invalid Date\n", e.getMessage());	
	}
	
	@Test
	public void testToString01() throws ExInvalidDate, ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth {
		Day day = new Day("10-Dec-2020");
		String result = day.toString();
        assertEquals("10-Dec-2020", result);	
	}
	
	@Test
	public void TestStrMonthToInt01() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day day = new Day("10-Dec-2020");
		int result = day.strMonthToInt("Abc");
        assertEquals(-1, result);	
	}
	
	@Test
	public void TestIsEqual01() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day day1 = new Day("10-Dec-2020");
		Day day2 = new Day("10-Dec-2020");
		boolean result = day1.isEqual(day2);
        assertEquals(true, result);	
	}
	
	@Test
	public void TestIsEqual02() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day day1 = new Day("10-Dec-2020");
		Day day2 = new Day("11-Dec-2020");
		boolean result = day1.isEqual(day2);
        assertEquals(false, result);	
	}
	
	@Test
	public void TestIsEqual03() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day day1 = new Day("10-Nov-2020");
		Day day2 = new Day("10-Dec-2020");
		boolean result = day1.isEqual(day2);
        assertEquals(false, result);	
	}
	
	@Test
	public void TestIsEqual04() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day day1 = new Day("10-Dec-2020");
		Day day2 = new Day("10-Dec-2019");
		boolean result = day1.isEqual(day2);
        assertEquals(false, result);	
	}
	
	@Test
	public void TestIsAfter01() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day day1 = new Day("10-Dec-2020");
		Day day2 = new Day("1-Dec-2020");
		boolean result = day1.isAfter(day2);
        assertEquals(true, result);	
	}
	
	@Test
	public void TestIsAfter02() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day day1 = new Day("10-Dec-2020");
		Day day2 = new Day("10-Dec-2021");
		boolean result = day1.isAfter(day2);
        assertEquals(false, result);	
	}
	
	@Test
	public void TestIsAfter03() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day day1 = new Day("10-Nov-2020");
		Day day2 = new Day("10-Dec-2020");
		boolean result = day1.isAfter(day2);
        assertEquals(false, result);	
	}
	
	@Test
	public void TestIsAfter04() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day day1 = new Day("10-Dec-2020");
		Day day2 = new Day("20-Dec-2020");
		boolean result = day1.isAfter(day2);
        assertEquals(false, result);	
	}
	
	@Test
	public void TestIsAfter05() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day day1 = new Day("10-Dec-2020");
		Day day2 = new Day("10-Nov-2020");
		boolean result = day1.isAfter(day2);
        assertEquals(true, result);	
	}
	
	@Test
	public void TestIsAfter06() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day day1 = new Day("10-Dec-2021");
		Day day2 = new Day("10-Nov-2020");
		boolean result = day1.isAfter(day2);
        assertEquals(true, result);	
	}
	
	@Test
	public void TestCompareTo01() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day day1 = new Day("10-Dec-2020");
		Day day2 = new Day("20-Dec-2020");
		int result = day1.compareTo(day2);
        assertEquals(-1, result);	
	}
	
	@Test
	public void TestCompareTo02() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day day1 = new Day("10-Dec-2020");
		Day day2 = new Day("1-Dec-2020");
		int result = day1.compareTo(day2);
        assertEquals(1, result);	
	}
	
	@Test
	public void TestCompareTo03() throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
		Day day1 = new Day("10-Dec-2020");
		Day day2 = new Day("10-Dec-2020");
		int result = day1.compareTo(day2);
        assertEquals(0, result);	
	}
	
}
