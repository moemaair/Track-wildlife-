package dao;

import modal.EndangeredAnimals;

import java.util.List;

public interface Endangered {
    List<EndangeredAnimals> getAll();
    void add(EndangeredAnimals animal);
}
