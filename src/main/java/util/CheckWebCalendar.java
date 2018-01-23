package util;

import webcalendar.WebCalendar;
import webcalendar.WebCalendarService;

import java.util.Calendar;

public class CheckWebCalendar {
    public static void main(String[] args) {
        Calendar calendar = null;
        String s;
        try {
            WebCalendar service = new WebCalendarService().getWebCalendarPort();
            calendar = service.getCalendar().toGregorianCalendar();
        } catch (Exception e) {
        }

        if (calendar != null) {
            s = calendar.get(Calendar.DATE) + "." + (calendar.get(Calendar.MONTH) + 1) + "." + calendar.get(Calendar.YEAR)
                    + " " + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
        } else {
            s = "Service is not available";
        }
        System.out.println("WebCalendar " + s);
    }
}
