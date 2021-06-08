public class Date {
    private int year;
    private int month;
    private int day;

    private static final int[] numberOfDays = {
        0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    }; // Element 0 is 0 so Element 1 is January

    public Date() { }; // default

    public Date(int year, int month, int day) {
        // Validate month
        if (month <= 0 || month > 12) {
            throw new IllegalArgumentException (
                "Month is out of range."
            );
        }

        // Validate day
        if (day <=0 || (day > numberOfDays[month] && !(month == 2) && day == 29)) {
            throw new IllegalArgumentException (
                "Day is out of range."
            );
        }

        // Validate day for leap year
        if (month == 2 && day == 29 && !(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))) {
            throw new IllegalArgumentException (
                "Day is out of range."
            );
        }

        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Convert Month number to title

    public static String getMonthTitle(int month) {
        String monthTitle = "";

        switch (month) {
            case 1:
                monthTitle = "January";
                break;
            case 2:
                monthTitle = "February";
                break;
            case 3:
                monthTitle = "March";
                break;
            case 4:
                monthTitle = "April";
                break;
            case 5:
                monthTitle = "May";
                break;
            case 6:
                monthTitle = "June";
                break;
            case 7:
                monthTitle = "July";
                break;
            case 8:
                monthTitle = "August";
                break;
            case 9:
                monthTitle = "September";
                break;
            case 10:
                monthTitle = "October";
                break;
            case 11:
                monthTitle = "November";
                break;
            case 12:
                monthTitle = "December";
                break;
        }

        return monthTitle;
    }

    // Get Day of Week

    public static String getDayTitle(int year, int month, int day) {
        // Formula by Tomohiko Sakamoto
        String dayTitle = "";

        int t[] = {
                0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4
        };

        if (month < 3) {
            year -= 1;
        }

        int dayOfWeekNum = (year + year / 4 - year / 100 + year / 400 + t[month - 1] + day) % 7;

        switch (dayOfWeekNum) {
            case 0:
                dayTitle = "Sunday";
                break;
            case 1:
                dayTitle = "Monday";
                break;
            case 2:
                dayTitle = "Tuesday";
                break;
            case 3:
                dayTitle = "Wednesday";
                break;
            case 4:
                dayTitle = "Thursday";
                break;
            case 5:
                dayTitle = "Friday";
                break;
            case 6:
                dayTitle = "Saturday";
                break;
            case 7:
                dayTitle = "Sunday";
                break;
        }
        return dayTitle;
    }


    // String Override

    @Override
    public String toString() {
        String monthTitle = getMonthTitle(month);
        String dayTitle = getDayTitle(year, month, day);

        // String
        return dayTitle + ", " + monthTitle + " " + day + ", " + year;
    }
}

/*
    .
   _|________________
    |  .--.--.
    |  |  |  |
  `-`--'  '  `-

 */