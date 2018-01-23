package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@FacesConverter("calendarConverter")
public class CalendarConverter implements Converter<Calendar> {
    @Override
    public Calendar getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Calendar calendar = Calendar.getInstance();
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            calendar.setTime(format.parse(s));
        } catch (Exception e) {
            return null;
        }
        return calendar;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Calendar calendar) {
        if (calendar == null) return null;
        return String.format("%02d", calendar.get(Calendar.DATE))
                + "." + String.format("%02d", (calendar.get(Calendar.MONTH) + 1))
                + "." + String.format("%04d", calendar.get(Calendar.YEAR));
    }
}
