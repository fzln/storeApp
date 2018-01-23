package services.impl;

import entity.Description;
import org.springframework.stereotype.Service;
import services.DescriptionTempService;

import java.util.List;

@Service("descriptionTempService")
public class DescriptionTempServiceImpl implements DescriptionTempService {

    private List<Description> descriptionList;

    public List<Description> getDescriptionList() {
        return descriptionList;
    }

    public void setDescriptionList(List<Description> descriptionList) {
        this.descriptionList = descriptionList;
    }

    @Override
    public void add(Description description) {
        descriptionList.add(description);
    }

    @Override
    public void update(Description description) {
        for (Description descriptionT : descriptionList) {
            if (descriptionT.getSerialNumber().equals(description.getDescription()))
                descriptionList.remove(description);
        }
        descriptionList.add(description);
    }

    @Override
    public void remove(Description description) {
        descriptionList.remove(description);

    }

    @Override
    public void delete(Description description) {
        descriptionList.remove(description);
    }

    @Override
    public void deleteAll() {
        descriptionList.clear();
    }

    @Override
    public Description findBySerialNumber(String serialNumber) {
        for (Description description : descriptionList) {
            if (description.getSerialNumber().equals(serialNumber)) return description;
        }
        return null;
    }

    @Override
    public List<Description> listAll() {
        return descriptionList;
    }

    @Override
    public long count() {
        return descriptionList.size();
    }
}
