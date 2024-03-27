import java.util.Comparator;

/**
 * Date class
 * Represents a date with a month, day, and year
 * 
 * @author Michael
 */

// import java.util.Comparator;

public class Date {
    final int month;
    final int day;
    final int year;

    // Comparator<Date> dateComparator = (d1, d2) ->
    //     Integer.compare(d1.year, d2.year) != 0 ? Integer.compare(d1.year, d2.year) :
    //     Integer.compare(d1.month, d2.month) != 0 ? Integer.compare(d1.month, d2.month) :
    //     Integer.compare(d1.day, d2.day);

    public Date() {
        this.month = 1;
        this.day = 1;
        this.year = 2000;
    }

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public Date(String date) {
        String[] dateParts = date.split("/");
        this.month = Integer.parseInt(dateParts[0]);
        this.day = Integer.parseInt(dateParts[1]);
        this.year = Integer.parseInt(dateParts[2]);
    }

    // public Comparator<Date> getDateComparator()
    // {
    //     return dateComparator;
    // }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%4d", month, day, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Date)) {
            return false;
        }
        Date d = (Date) obj;
        return d.month == month && d.day == day && d.year == year;
    }

    public int compareTo(Date d) {
        if (year != d.year) {
            return Integer.compare(year, d.year);
        } else if (month != d.month) {
            return Integer.compare(month, d.month);
        } else {
            return Integer.compare(day, d.day);
        }
    }

    public Date addDays(int days) {
        int newYear = year;
        int newMonth = month;
        int newDay = day + days;

        while (newDay > daysInMonth(newMonth, newYear)) {
            newDay -= daysInMonth(newMonth, newYear);
            newMonth++;
            if (newMonth > 12) {
                newMonth = 1;
                newYear++;
            }
        }

        return new Date(newMonth, newDay, newYear);
    }

    private int daysInMonth(int month, int year) {
        switch (month) {
            case 2:
                return 29;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return 31;
        }
    }


} // end of Date class

 class DateComparator implements Comparator<Date> {
     public int compare(Date d1, Date d2) {
         if (d1.year != d2.year) {
             return Integer.compare(d1.year, d2.year);
         } else if (d1.month != d2.month) {
             return Integer.compare(d1.month, d2.month);
         } else {
             return Integer.compare(d1.day, d2.day);
         }
     }
 } // end of dateComparator class
