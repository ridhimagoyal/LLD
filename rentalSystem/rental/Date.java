package rental;

/**
 * Simple Date class used by your project (day, month, year).
 * Minimal utilities: compare, nextDay, equals/hashCode for map keys.
 */
public class Date {
    public int day;
    public int month;
    public int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    // Compare: negative if this < other, zero if equal, positive if this > other
    public int compare(Date other) {
        if (this.year != other.year) return this.year - other.year;
        if (this.month != other.month) return this.month - other.month;
        return this.day - other.day;
    }

    public boolean isBeforeOrEqual(Date other) {
        return compare(other) <= 0;
    }

    public boolean isAfterOrEqual(Date other) {
        return compare(other) >= 0;
    }

    // Simple next day (ignores leap-year feb 29 handling for simplicity)
    public Date nextDay() {
        int[] daysInMonth = {31,28,31,30,31,30,31,31,30,31,30,31};
        int d = this.day + 1;
        int m = this.month;
        int y = this.year;
        int monthIndex = m - 1;
        // leap year fix
        if (isLeap(y)) daysInMonth[1] = 29;
        if (d > daysInMonth[monthIndex]) {
            d = 1;
            m++;
            if (m > 12) {
                m = 1;
                y++;
            }
        }
        return new Date(d, m, y);
    }

    public static boolean isLeap(int y) {
        return (y % 400 == 0) || (y % 4 == 0 && y % 100 != 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;
        Date d = (Date) o;
        return day == d.day && month == d.month && year == d.year;
    }

    @Override
    public int hashCode() {
        return day * 1000000 + month * 10000 + year;
    }

    @Override
    public String toString() {
        return String.format("%02d-%02d-%04d", day, month, year);
    }
}
