package services;

import entity.Description;

import java.util.List;

public interface DescriptionService {
    Description add(Description description);

    void update(Description description);

    void delete(Description description);

    void deleteAll();

    Description findBySerialNumber(String descriptionsId);

    List<Description> listAll();

    long count();
}
