package dao;

import modal.Sightings;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oSightings implements Sighting{
    private Sql2o sql2o;

    public Sql2oSightings(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Sightings>getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM sightings")
                    .executeAndFetch(Sightings.class); //fetch list
        }
    }

    @Override
    public void add(Sightings sightings) {
        String sql = "INSERT INTO sightings (location, ranger_name, animal_name) VALUES (:location, :ranger_name, :animal_name)";

        try (Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(sightings)
                    .executeUpdate()
                    .getKey();
            sightings.setId(id);

        } catch (Sql2oException ex) {
            System.out.println("There was a problem adding the Sighting! ");
        }

    }


}
