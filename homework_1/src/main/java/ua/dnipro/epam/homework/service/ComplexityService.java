package ua.dnipro.epam.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dnipro.epam.homework.dao.impl.ComplexityDAOImpl;
import ua.dnipro.epam.homework.entity.Complexity;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ComplexityService {

    private ComplexityDAOImpl complexityDAOImpl = new ComplexityDAOImpl();

    public List<Complexity> findAll(String lang) {
        return complexityDAOImpl.findAll(lang);
    }
}
