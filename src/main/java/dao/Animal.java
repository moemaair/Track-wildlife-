package dao;

import modal.Animals;
import modal.EndangeredAnimals;

import java.util.List;

public interface wildlifedao {
    List<Animals> getAll();
    void add(Animals animal);

}
