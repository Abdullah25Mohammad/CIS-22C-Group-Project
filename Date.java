public class Date {
    final int month;
    final int day;
    final int year;

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%4d", month, day, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Date) {
            Date other = (Date) obj;
            return month == other.month && day == other.day && year == other.year;
        } else {
            return false;
        }
    }

    public int compareTo(Date other) {
        if (year != other.year) {
            return Integer.compare(year, other.year);
        } else if (month != other.month) {
            return Integer.compare(month, other.month);
        } else {
            return Integer.compare(day, other.day);
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
