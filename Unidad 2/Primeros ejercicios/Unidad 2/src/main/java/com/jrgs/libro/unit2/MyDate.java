package com.jrgs.libro.unit2;

// Class extending activity 3.3.3 with the new requirements in 3.3.4
public class MyDate extends MyBaseDate {

    public enum Months {
        JANUARY(1), FEBRUARY(2), MARCH(3), APRIL(4), MAY(5), JUNE(6),
        JULY(7), AUGUST(8), SEPTEMBER(9), OCTOBER(10), NOVEMBER(11), DECEMBER(12);

        public final int monthNumber;

        private Months(int monthNumber) {
            this.monthNumber = monthNumber;
        }

        public static Months toMonth( int monthNumber ) {
            for (Months m : values())
                if (m.monthNumber == monthNumber)
                    return m;
            return null;
        }
    }

    public MyDate() { super(); }

    public MyDate(int day, Months month, int year ) {
        if ( checkDate(day, month, year) ) {
            setDay(day);
            setMonth(month);
            setYear(year);
        }
        else
            throw new IllegalArgumentException("Invalid date");
    }

    boolean leapYear( int year ) {
        if ( (year % 400 == 0) || ((year % 4 == 0) && (year % 100 != 0)))
            return true;
        return false;
    }

    public boolean leapYear() {
        return leapYear( getYear() );
    }

    public boolean checkDate(int day, Months month, int year) {
        int maxDay;

       if ( month == null )
            return false;

        if ( month == Months.FEBRUARY ) {
            if ( leapYear(year) )
                maxDay = 29;
            else
                maxDay = 28;
        }
        else if ( month == Months.APRIL || month == Months.JUNE || month == Months.SEPTEMBER || month == Months.NOVEMBER )
            maxDay = 30;
        else
            maxDay = 31;

        if ( day < 1 || day > maxDay )
            return false;

        return true;
    }

    @Override
    public void setDay(int day) {
        if ( checkDate(day, Months.toMonth(getMonth()), getYear()) )
            this.day = day;
        else
            throw new IllegalArgumentException("Day value not valid");
    }

    public void setMonth(Months month) {
        if ( checkDate(getDay(), month, getYear()) )
            this.month = month.monthNumber;
        else
            throw new IllegalArgumentException("Month value not valid");
    }

    @Override
    public void setMonth(int month) {
        if ( checkDate(getDay(), Months.toMonth(month), getYear()) )
            this.month = month;
        else
            throw new IllegalArgumentException("Month value not valid");
    }

    @Override
    public void setYear(int year) {
        if ( checkDate(getDay(), Months.toMonth(getMonth()), year) )
            this.year = year;
        else
            throw new IllegalArgumentException("Year value not valid");
    }
}
