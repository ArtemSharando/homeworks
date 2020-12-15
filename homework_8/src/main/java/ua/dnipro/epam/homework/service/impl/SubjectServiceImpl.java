package ua.dnipro.epam.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dnipro.epam.homework.dao.impl.SubjectDAOImpl;
import ua.dnipro.epam.homework.entity.Subject;
import ua.dnipro.epam.homework.service.SubjectService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubjectServiceImpl implements SubjectService {

   private final SubjectDAOImpl subjectDAOImpl = new SubjectDAOImpl();

    public List<Subject> findAll(String lang) {
        return subjectDAOImpl.findAll(lang);
    }
}
