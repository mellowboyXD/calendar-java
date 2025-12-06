package content;

import java.time.LocalDate;

public class Calendar {
    private static Calendar calender = null;
    private final int date;
    private final int year;
    private final int monthValue;
    private final Month month;
    private final boolean isLeap;

    private Calendar() {
        LocalDate currentDate = LocalDate.now();
        isLeap = currentDate.isLeapYear();
        date = currentDate.getDayOfMonth();
        year = currentDate.getYear();
        monthValue = currentDate.getMonthValue();
        month = getMonth();
    }

    private static Calendar getInstance() {
        if (calender == null) {
            calender = new Calendar();
        }
        return calender;
    }

    private int getDate() {
        return date;
    }

    private int getYear() {
        return year;
    }

    private Month getMonth() {
        return switch (monthValue) {
            case 1 -> Month.JAN;
            case 2 -> Month.FEB;
            case 3 -> Month.MAR;
            case 4 -> Month.APR;
            case 5 -> Month.MAY;
            case 6 -> Month.JUN;
            case 7 -> Month.JUL;
            case 8 -> Month.AUG;
            case 9 -> Month.SEP;
            case 10 -> Month.OCT;
            case 11 -> Month.NOV;
            default -> Month.DEC;
        };
    }

    private int getMonthValue() {
        return monthValue;
    }

    private String getMonthString() {
        return month.getMonthName();
    }

    private int getNumberOfDays() {
        return switch (month) {
            case JAN, MAR, MAY, JUL, AUG, OCT, DEC -> 31;
            case APR, JUN, SEP, NOV -> 30;
            case FEB -> {
                if (isLeap)
                    yield 29;
                else
                    yield 28;
            }
        };
    }

    public static void displayCalender() {
        Calendar calendar = getInstance();
        int year = calendar.getYear();
        String month = calendar.getMonthString();
        System.out.println("\t\t" + month + " " + year);
        System.out.println("Sun Mon Tue Wed Thu Fri Sat");

        LocalDate firstOfMonth = LocalDate.of(year, calendar.getMonthValue(), 1);
        int startIndex = firstOfMonth.getDayOfWeek().getValue();
        for (int i = 0; i < startIndex; i++) {
            System.out.printf("%-4s", " ");
        }

        int numberOfDaysInMonth = calendar.getNumberOfDays();
        for (int i = 1; i <= numberOfDaysInMonth; i++) {
            if (i == calendar.getDate()) {
                String temp = i + "*";
                System.out.printf("%-4s", temp);
            } else
                System.out.printf("%-4d", i);

            if ((i + startIndex) % 7 == 0) System.out.print("\n");
        }
    }
}
