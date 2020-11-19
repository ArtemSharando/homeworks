package ua.dnipro.epam.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dnipro.epam.homework.dao.impl.ComplexityDAOImpl;
import ua.dnipro.epam.homework.entity.Complexity;
import ua.dnipro.epam.homework.service.ComplexityService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ComplexityServiceImpl implements ComplexityService {

    private final ComplexityDAOImpl complexityDAOImpl = new ComplexityDAOImpl();

    public List<Complexity> findAll(String lang) {
        return complexityDAOImpl.findAll(lang);
    }
}
