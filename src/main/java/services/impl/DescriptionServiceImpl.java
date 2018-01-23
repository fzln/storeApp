package services.impl;

import entity.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.DescriptionRepository;
import services.DescriptionService;

import java.util.List;

@Service("descriptionService")
@Transactional
public class DescriptionServiceImpl implements DescriptionService {
    @Autowired
    private DescriptionRepository descriptionRepository;

    @Override
    public Description add(Description description) {
        return descriptionRepository.saveAndFlush(description);
    }

    @Override
    public void update(Description description) {
        descriptionRepository.saveAndFlush(description);
    }

    @Override
    public void delete(Description description) {
        descriptionRepository.delete(description);

    }
    @Override
    public void deleteAll() {
        descriptionRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Description findBySerialNumber(String serialNumber) {
        return descriptionRepository.findBySerialNumber(serialNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Description> listAll() {
        return descriptionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public long count() {
        return descriptionRepository.count();
    }
}
