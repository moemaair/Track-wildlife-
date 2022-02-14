package dao;

import modal.Sightings;

import java.util.List;

public interface Sighting {
    List<Sightings> getAll();
    void add(Sightings sightings);
}
