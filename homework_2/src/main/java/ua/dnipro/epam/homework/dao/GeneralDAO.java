package ua.dnipro.epam.homework.dao;

import java.util.List;

public interface GeneralDAO<T,ID>{

    T findById(ID id);

    List<T> findAll(String lang);

    T create (T entity);

    T update (T entity, ID id);

    void deleteById(ID id);

}
