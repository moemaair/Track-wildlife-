package dao;

import modal.Animals;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oAnimals implements Animal {
    private Sql2o sql2o;

    public Sql2oAnimals(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<Animals> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM animals")
                    .executeAndFetch(Animals.class); //fetch list
        }
    }

    @Override
    public void add(Animals animals) {
        String sql = "INSERT INTO animals (name) VALUES (:name)";

        try (Connection con = sql2o.open()){
            int id = (int) con.createQuery(sql, true)
                    .bind(animals)
                    .executeUpdate()
                    .getKey();
            animals.setId(id);

        } catch (Sql2oException ex) {
            System.out.println("There was a problem adding the animals! ");
        }
    }
}
