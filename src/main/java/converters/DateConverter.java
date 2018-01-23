package converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@FacesConverter("dateConverter")
public class DateConverter implements Converter<Date> {
    @Override
    public Date getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        Date date;
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            date = format.parse(s);
        } catch (Exception e) {
            return null;
        }
        return date;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Date date) {
        if (date == null) return null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return String.format("%02d", calendar.get(Calendar.DATE))
                + "." + String.format("%02d", (calendar.get(Calendar.MONTH) + 1))
                + "." + String.format("%04d", calendar.get(Calendar.YEAR));
    }
}
