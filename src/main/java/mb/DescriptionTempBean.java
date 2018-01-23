package mb;

import entity.Description;
import entity.DescriptionList;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.JiBXException;
import org.primefaces.component.api.UIColumn;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.annotation.RequestScope;
import services.DescriptionTempService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScope
public class DescriptionTempBean extends BeanSuper {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private Description description = new Description();
    private List<Description> descriptionList;
    private StreamedContent file;

    @Inject
    private DescriptionTempService descriptionTempService;

    @Inject
    private DescriptionTempSession descriptionTempSession;

    @PostConstruct
    public void postConstruct() {
        descriptionList = descriptionTempService.getDescriptionList();
        if (descriptionList == null) {
            descriptionList = new ArrayList<Description>();
            descriptionTempService.setDescriptionList(descriptionList);
        }
    }

    public void saveToFile() {
        DescriptionList descriptionListOut = new DescriptionList();
        descriptionListOut.setList(descriptionList);
        try {
            IBindingFactory bfact = BindingDirectory.getFactory(DescriptionList.class);

            // marshal object back out to file (with nice indentation, as UTF-8)
            IMarshallingContext mctx = bfact.createMarshallingContext();
            mctx.setIndent(2);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            mctx.setOutput(baos, null);
            mctx.marshalDocument(descriptionListOut);

            file = new DefaultStreamedContent(
                    new ByteArrayInputStream(baos.toByteArray()),
                    "txt/xml",
                    "descriptionList.xml");

        } catch (JiBXException e) {
            logger.error("Download to file error(JiBX) ", e);
        }
    }

    public StreamedContent getFile() {
        return file;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public void setDescriptionTempSession(DescriptionTempSession descriptionTempSession) {
        this.descriptionTempSession = descriptionTempSession;
    }

    public void setDescriptionTempService(DescriptionTempService descriptionTempService) {
        this.descriptionTempService = descriptionTempService;
    }

    public void createDescriptionFromSession() {
        description = descriptionTempSession.getDescription();
        createDescription();
    }

    public void createDescription() {
        if (description == null) return;
        try {
            descriptionTempService.update(description);
        } catch (Exception e) {
            sendErrorMessage("Can't create Description", "Create failed");
            e.printStackTrace();
        }
        loadDescriptionList();
        sendInfoMessage("Description Created", "Id " + description.getSerialNumber());
        descriptionTempSession.resetDescription();
    }

    public void deleteDescription(Description description) {
        try {
            descriptionTempService.delete(description);
        } catch (Exception e) {
            sendErrorMessage("Can't delete Description", "Delete failed");
            e.printStackTrace();
        }
        loadDescriptionList();
        sendInfoMessage("Description Deleted", "Id " + description.getSerialNumber());
    }

    public void deleteDescription() {
        description = descriptionTempSession.getDescription();
        if (description == null) return;
        try {
            descriptionTempService.delete(description);
        } catch (Exception e) {
            sendErrorMessage("Can't delete Description", "Delete failed");
            e.printStackTrace();
        }
        loadDescriptionList();
        sendInfoMessage("Description Deleted", "Id " + description.getSerialNumber());
        descriptionTempSession.resetDescription();
    }

    public void saveDescription() {
        descriptionTempService.update(description);
        description = new Description();
        invalidateDescriptionList();
    }

    public void resetDescription() {
        description = new Description();
    }

    private void invalidateDescriptionList() {
        descriptionList = null;
    }

    private void loadDescriptionList() {
        descriptionList = descriptionTempService.listAll();
    }

    public void clearDescriptionList() {
        descriptionTempService.deleteAll();
    }

    public List<Description> getDescriptionList() {
        if (descriptionList == null) loadDescriptionList();
        return descriptionList;
    }

    public void onRowEdit(RowEditEvent event) {
        description = (Description) event.getObject();
        if (description == null) return;
        try {
            descriptionTempService.update(description);
        } catch (Exception e) {
            sendErrorMessage("Can't update Description", "Update failed");
            e.printStackTrace();
        }
        loadDescriptionList();
        sendInfoMessage("Description Edited", "Id " + description.getSerialNumber());
        descriptionTempSession.resetDescription();
        resetDescription();
    }

    public void onRowCancel(RowEditEvent event) {
        sendInfoMessage("Edit Cancelled", "Id " + ((Description) event.getObject()).getSerialNumber());
    }

    public void onCellEdit(Description description) {
        try {
            descriptionTempService.update(description);
        } catch (Exception e) {
            sendErrorMessage("Can't update Description", "Update failed");
            e.printStackTrace();
        }
        loadDescriptionList();
        sendInfoMessage("Description Edited", "Id " + description.getSerialNumber());
        descriptionTempSession.resetDescription();
        resetDescription();
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        int rowIndex = event.getRowIndex();
        UIColumn column = event.getColumn();

        if (newValue != null && !newValue.equals(oldValue)) {
            sendInfoMessage("Cell Changed", "Old: " + oldValue + ", New:" + newValue + " rowIndex:" + rowIndex +
                    " CK:" + column.getColumnKey());
        }
    }
}
