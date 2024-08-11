package com.jrgs.libro.unit2;

public class MyBaseDate {
    protected int day;
    protected int month;
    protected int year;

    MyBaseDate() { day = 1; month = 1; year = 0; }

    MyBaseDate( int day, int month, int year ) {
        setDay(day);
        setMonth(month);
        setYear(year);
    }


    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if ( day>=1 && day<=31 )
            this.day = day;
        else
            throw new IllegalArgumentException("Day value not valid");
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if ( month >= 1 && month <= 12 )
            this.month = month;
        else
            throw new IllegalArgumentException("Month value not valid");
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
