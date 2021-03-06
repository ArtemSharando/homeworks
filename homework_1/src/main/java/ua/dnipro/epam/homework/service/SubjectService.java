package ua.dnipro.epam.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.dnipro.epam.homework.dao.impl.SubjectDAOImpl;
import ua.dnipro.epam.homework.entity.Subject;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubjectService {

   private SubjectDAOImpl subjectDAOImpl = new SubjectDAOImpl();

    public List<Subject> findAll(String lang) {
        return subjectDAOImpl.findAll(lang);
    }
}
