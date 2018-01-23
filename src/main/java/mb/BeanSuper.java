package mb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Map;

public abstract class BeanSuper implements Serializable {

    transient protected Logger logger = LoggerFactory.getLogger(getClass());

    public void sendInfoMessage(String summary) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary));
    }

    public void sendInfoMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(summary, detail));
    }

    public void sendWarnMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, summary, detail));
    }

    public void sendErrorMessage(String summary, String detail) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail));
    }

    /**
     * @param <T>      Type of bean to return
     * @param beanName bean name to look for
     * @return Bean from the external context session or <code>null</code> if not found
     */
    @SuppressWarnings("unchecked")
    protected <T> T getBeanFromSession(String beanName) {
        return (T) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(beanName);
    }
    /**
     * Getting parameter from request
     *
     * @param paramName Name of the parameter
     * @return Found parameter or <code>null</code>
     */
    protected String getParameterFromRequest(String paramName) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String, String> param = facesContext.getExternalContext().getRequestParameterMap();

        return param.get(paramName);
    }

    /**
     * Getting parameter from request
     *
     * @param paramName Name of the parameter
     * @return Found parameter or <code>null</code>
     */
    protected Long getParameterFromRequestAsLong(String paramName) {
        String sParamValue = getParameterFromRequest(paramName);
        Long paramLong = null;
        try {
            paramLong = Long.valueOf(sParamValue);
        } catch (NumberFormatException e) {
            if (logger.isTraceEnabled()) logger.trace("Value not found in request for " + paramName);
        }
        return paramLong;
    }
}