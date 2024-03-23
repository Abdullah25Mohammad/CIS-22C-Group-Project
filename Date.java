import java.util.Comparator;

public class Date {
    final int month;
    final int day;
    final int year;
    Comparator<Date> dateComparator = (d1, d2) ->
        Integer.compare(d1.year, d2.year) != 0 ? Integer.compare(d1.year, d2.year) :
        Integer.compare(d1.month, d2.month) != 0 ? Integer.compare(d1.month, d2.month) :
        Integer.compare(d1.day, d2.day);

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public Comparator<Date> getDateComparator()
    {
        return dateComparator;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%4d", month, day, year);
    }

    public static void main(String[] args) {
        Date k = new Date(5,3,2000);
        System.out.println(k);
    }
}
