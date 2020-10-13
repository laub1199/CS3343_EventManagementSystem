package EventManagementSystem;

import java.io.*;
import java.util.Scanner;

public class Day implements Cloneable{

    private int year;
    private int month;
    private int day;
    private static final String[] MonthNamesArr =
        new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    //Constructor
    public Day(int y, int m, int d) {
        this.year=y;
        this.month=m;
        this.day=d;
    }

    // check if a given year is a leap year
    static public boolean isLeapYear(int y)
    {
        if (y%400==0)
            return true;
        else if (y%100==0)
            return false;
        else if (y%4==0)
            return true;
        else
            return false;
    }

    // check if y,m,d valid
    static public boolean valid(int y, int m, int d) throws ExInvalidDate {
        switch(m){
            case 1: case 3: case 5: case 7:
            case 8: case 10: case 12:
                return d<=31;
            case 4: case 6: case 9: case 11:
                return d<=30;
            case 2:
                if (isLeapYear(y))
                    return d<=29;
                else
                    return d<=28;
        }
        throw new ExInvalidDate();
    }

    //Set year,month,day based on a string like 01-Mar-2019
    public void set(String sDay) throws ExDateFormatDay, ExDateFormatMonth, ExDateFormatYear, ExInvalidDate
    {
        String[] sDayParts = sDay.split("-");
        if (this.isValidDay(sDayParts[0]) && this.isValidMonth(sDayParts[1]) && this.isValidYear(sDayParts[2]) &&
            sDayParts.length == 3 ) {
            int month = this.strMonthToInt(sDayParts[1]);
            if(this.valid(Integer.parseInt(sDayParts[2]), month,
                    Integer.parseInt(sDayParts[0]))) {
                this.day = Integer.parseInt(sDayParts[0]); //Apply Integer.parseInt for sDayParts[0];
                this.year = Integer.parseInt(sDayParts[2]);
                this.month = month;
            }
            else {
                throw new ExInvalidDate();
            }
        }
    }

    public Day(String sDay) throws ExDateFormatYear, ExDateFormatDay, ExDateFormatMonth, ExInvalidDate {
        set(sDay);
    } //Constructor, simply call set(sDay)

    @Override
    public String toString()
    {
        return day+"-"+ MonthNamesArr[month-1] + "-"+ year;
    }
    @Override
    public Day clone() throws CloneNotSupportedException {
        Day copy = null;
        copy = (Day) super.clone();
        return copy;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public boolean isAfter (Day d) { //Check if another day object is after this day object
        if(d.getYear() > this.year) {
            return false;
        }
        else if(d.getYear() == this.year) {
            if(d.getMonth() > this.month) {
                    return false;
            }
            else if (d.getMonth() == this.month) {
                    if (d.getDay() >= this.day) {
                        return false;
                    }
            }
        }
        return true;
    }

    public boolean isEqual (Day d) {
        if(this.year == d.year && this.month == d.month && this.day == d.day) {
            return true;
        }
        return false;
    }

    public int compareTo (Day d) { //Check if another day object is after this day object
        if(this.isAfter(d)) {
            return 1;
        }
        else if (this.isEqual(d)) {
            return 0;
        }
        else {
            return -1;
        }
    }

    public boolean isValidDay(String day) throws ExDateFormatDay {
        if(Integer.parseInt(day) > 0 && Integer.parseInt(day) <=31) {
            return true;
        }
        throw new ExDateFormatDay();
    }

    public boolean isValidMonth(String month) throws ExDateFormatMonth {
        for (String m : MonthNamesArr) {
            if(m.toLowerCase().equals(month.toLowerCase())) {
                return true;
            }
        }
        throw new ExDateFormatMonth();
    }

    public boolean isValidYear(String year) throws ExDateFormatYear {
        if(Integer.parseInt(year) > 0) {
            return true;
        }
        throw new ExDateFormatYear();
    }

    public int strMonthToInt(String month) {
        int index = 1;
        for (String m : MonthNamesArr) {
            if(m.toLowerCase().equals(month.toLowerCase())) {
                return index;
            }
            index++;
        }
        return -1;
    }
}