package mb;

import entity.Description;
import entity.DescriptionList;
import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.primefaces.component.api.UIColumn;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.annotation.RequestScope;
import services.DescriptionService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.List;

@Named
@RequestScope
public class DescriptionBean extends BeanSuper {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private Description description = new Description();
    private List<Description> descriptionList;
    private UploadedFile file;

    @Inject
    private DescriptionService descriptionService;

    @Inject
    private DescriptionSession descriptionSession;

    @PostConstruct
    public void postConstruct() {
        loadDescriptionList();
    }

    public void setDescriptionService(DescriptionService descriptionService) {
        this.descriptionService = descriptionService;
    }

    public void setDescriptionSession(DescriptionSession descriptionSession) {
        this.descriptionSession = descriptionSession;
    }

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void uploadFile() {
        if (file != null) {
            DescriptionList descriptionListIn = null;
            try {
                IBindingFactory bfact = BindingDirectory.getFactory(DescriptionList.class);

                // unmarshal customer information from file
                IUnmarshallingContext uctx = bfact.createUnmarshallingContext();
                descriptionListIn = (DescriptionList) uctx.unmarshalDocument(file.getInputstream(), null);

            } catch (JiBXException | IOException e) {
                logger.error("Upload from file error(JiBX) ", e);
            } catch (Exception e) {
                logger.error("Upload error x: ", e);
            }

            int i = 0;
            if (descriptionListIn != null) {
                for (Description descriptionLocal : descriptionListIn.getList()) {

                    String s = descriptionLocal.getSerialNumber()
                            + " " + descriptionLocal.getName()
                            + " " + descriptionLocal.getProductionDate();
                    logger.info("Upload : " + s);
                    sendInfoMessage(s);

                    try {
                        descriptionService.update(descriptionLocal);
                    } catch (Exception e) {
                        sendErrorMessage("Can't create Description", s);
                        e.printStackTrace();
                    }
                    i++;
                }
                loadDescriptionList();
                sendInfoMessage(" Загрузка выполнена. обработано " + i + " записей.");
            }
        }
    }

    public void createDescription() {
        description = descriptionSession.getDescription();
        if (description == null) return;
        try {
            descriptionService.update(description);
        } catch (Exception e) {
            sendErrorMessage("Can't create Description", "Create failed");
            e.printStackTrace();
        }
        loadDescriptionList();
        sendInfoMessage("Description Created", "Id " + description.getSerialNumber());
        descriptionSession.resetDescription();
    }

    public void deleteDescription(Description description) {
        try {
            descriptionService.delete(description);
        } catch (Exception e) {
            sendErrorMessage("Can't delete Description", "Delete failed");
            e.printStackTrace();
        }
        loadDescriptionList();
        sendInfoMessage("Description Deleted", "Id " + description.getSerialNumber());
    }

    public void deleteDescription() {
        description = descriptionSession.getDescription();
        if (description == null) return;
        try {
            descriptionService.delete(description);
        } catch (Exception e) {
            sendErrorMessage("Can't delete Description", "Delete failed");
            e.printStackTrace();
        }
        loadDescriptionList();
        sendInfoMessage("Description Deleted", "Id " + description.getSerialNumber());
        descriptionSession.resetDescription();
    }

    public void saveDescription() {
        descriptionService.update(description);
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
        descriptionList = descriptionService.listAll();
    }

    public List<Description> getDescriptionList() {
        if (descriptionList == null) loadDescriptionList();
        return descriptionList;
    }

    public void onRowEdit(RowEditEvent event) {
        description = (Description) event.getObject();
        if (description == null) return;
        try {
            descriptionService.update(description);
        } catch (Exception e) {
            sendErrorMessage("Can't update Description", "Update failed");
            e.printStackTrace();
        }
        loadDescriptionList();
        sendInfoMessage("Description Edited", "Id " + description.getSerialNumber());
        descriptionSession.resetDescription();
        resetDescription();
    }

    public void onRowCancel(RowEditEvent event) {
        sendInfoMessage("Edit Cancelled", "Id " + ((Description) event.getObject()).getSerialNumber());
    }

    public void onCellEdit(Description description) {
        try {
            descriptionService.update(description);
        } catch (Exception e) {
            sendErrorMessage("Can't update Description", "Update failed");
            e.printStackTrace();
        }
        loadDescriptionList();
        sendInfoMessage("Description Edited", "Id " + description.getSerialNumber());
        descriptionSession.resetDescription();
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
