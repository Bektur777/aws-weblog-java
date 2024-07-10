package kg.bektur.service;

import java.util.List;

public interface DefaultService<T, C> {

    List<T> findAll();

    T find(String id);

    void create(C c);

    void delete(String id);
}
