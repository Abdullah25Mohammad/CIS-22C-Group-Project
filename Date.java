import java.util.Comparator;

public class Date {
    final int month;
    final int day;
    final int year;
    
    // Comparator<Date> dateComparator = (d1, d2) ->
    //     Integer.compare(d1.year, d2.year) != 0 ? Integer.compare(d1.year, d2.year) :
    //     Integer.compare(d1.month, d2.month) != 0 ? Integer.compare(d1.month, d2.month) :
    //     Integer.compare(d1.day, d2.day);

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
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
} // end of Date class

// class dateComparator implements Comparator<Date> {
//     @Override
//     public int compare(Date d1, Date d2) {
//         if (d1.year != d2.year) {
//             return Integer.compare(d1.year, d2.year);
//         } else if (d1.month != d2.month) {
//             return Integer.compare(d1.month, d2.month);
//         } else {
//             return Integer.compare(d1.day, d2.day);
//         }
//     }
// } // end of dateComparator class
