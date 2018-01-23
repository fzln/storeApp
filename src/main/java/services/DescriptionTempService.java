package services;

import entity.Description;

import java.util.List;

public interface DescriptionTempService {

    void setDescriptionList(List<Description> descriptionList);

    List<Description> getDescriptionList();

    void add(Description description);

    void update(Description description);

    void remove(Description description);
    void delete(Description description);

    void deleteAll();

    Description findBySerialNumber(String descriptionsId);

    List<Description> listAll();

    long count();

}
