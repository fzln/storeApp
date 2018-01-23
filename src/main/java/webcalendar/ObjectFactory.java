
package webcalendar;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webcalendar package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetCalendarResponse_QNAME = new QName("http://service/", "getCalendarResponse");
    private final static QName _GetCalendar_QNAME = new QName("http://service/", "getCalendar");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webcalendar
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCalendarResponse }
     * 
     */
    public GetCalendarResponse createGetCalendarResponse() {
        return new GetCalendarResponse();
    }

    /**
     * Create an instance of {@link GetCalendar }
     * 
     */
    public GetCalendar createGetCalendar() {
        return new GetCalendar();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCalendarResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getCalendarResponse")
    public JAXBElement<GetCalendarResponse> createGetCalendarResponse(GetCalendarResponse value) {
        return new JAXBElement<GetCalendarResponse>(_GetCalendarResponse_QNAME, GetCalendarResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service/", name = "getCalendar")
    public JAXBElement<GetCalendar> createGetCalendar(GetCalendar value) {
        return new JAXBElement<GetCalendar>(_GetCalendar_QNAME, GetCalendar.class, null, value);
    }

}
