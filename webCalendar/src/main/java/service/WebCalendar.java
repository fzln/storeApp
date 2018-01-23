package service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Calendar;

@WebService
public class WebCalendar {
    @WebMethod
    public Calendar getCalendar(){
        return Calendar.getInstance();
    }
}
