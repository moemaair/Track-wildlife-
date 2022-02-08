package dao;

import modal.Animals;

import java.util.List;

public interface Animal {
    List<Animals> getAll();
    void add(Animals animal);

}
