package dao;

import modal.EndangeredAnimals;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oEanimals implements Endangered {
    private Sql2o sql2o;

    public Sql2oEanimals(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public List<EndangeredAnimals> getAll() {
            try (Connection con = sql2o.open()) {
                return (List<EndangeredAnimals>) con.createQuery("SELECT FROM * animals")
                        .executeAndFetch(EndangeredAnimals.class); //fetch list
            }
    }

    @Override
        public void add(EndangeredAnimals endangeredAnimals) {
            String sql = "INSERT INTO endangered_animals (id, name, health, age) VALUES (:id, :name, :health, :age)";

            try (Connection con = sql2o.open()){
                int id = (int) con.createQuery(sql, true)
                        .bind(endangeredAnimals)
                        .executeUpdate()
                        .getKey();
                endangeredAnimals.setId(id);

            } catch (Sql2oException ex) {
                System.out.println("There was a problem adding the animals! ");
            }
        }

}
