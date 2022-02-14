import dao.Animal;
import dao.Sql2oAnimals;
import dao.Sql2oEanimals;
import dao.Sql2oSightings;
import modal.Animals;

import modal.EndangeredAnimals;
import modal.Sightings;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.* ;

public class App {
    public static void main(String[] args) {
        ProcessBuilder process = new ProcessBuilder();
        Integer port;
        if (process.environment().get("PORT") != null) {
            port = Integer.parseInt(process.environment().get("PORT"));
        } else {
            port = 4567;
        }

        port(port);
        staticFileLocation("/public");


        String connectmetodatabase = "jdbc:postgresql://localhost:5432/wildlife_tracker"; //connect to database wildlife_tracker
        Sql2o sql2o = new Sql2o(connectmetodatabase, "postgres","bnmk");

        Sql2oAnimals sql2oAnimals = new Sql2oAnimals(sql2o);

        Sql2oEanimals sql2oEanimals = new Sql2oEanimals(sql2o);

        Sql2oSightings sql2oSightings = new Sql2oSightings(sql2o);

         // home route
        get("/", (request, response)  ->{
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model, "index.hbs");
        },new HandlebarsTemplateEngine());

        get("/new/animal", (request, response) -> {
            Map<String,Object> model = new HashMap<>();
            return new ModelAndView(model, "animal.hbs");
        },new HandlebarsTemplateEngine());

        post("/animals", (request, response)-> {
            Map<String, Object> model = new HashMap<>();
            String name = request.queryParams("name");

            Animals newAnimals = new Animals(name);

            sql2oAnimals.add(newAnimals);
            response.redirect("/animal/list");
            return null;
        },new HandlebarsTemplateEngine());

//        endangered route
        get("/new/endangeredanimal", (req,res)->{
            Map<String,Object> model = new HashMap<>();

//            List<String> health = new ArrayList<String>();
//            health.add(EndangeredAnimals.HEALTH_1);
//            health.add(EndangeredAnimals.HEALTH_2);
//            health.add(EndangeredAnimals.HEALTH_3);
//
//            List<String> age = new ArrayList<String>();
//            age.add(EndangeredAnimals.AGE_1);
//            age.add(EndangeredAnimals.AGE_2);
//            age.add(EndangeredAnimals.AGE_3);
//
//            model.put("health",health);
//            model.put("age", age);
            return new ModelAndView(model,"endangeredanimal.hbs");
        }, new HandlebarsTemplateEngine());

        post("/Endangered", (request, response) -> {
            Map<String,Object> model = new HashMap<>();

            String name = request.queryParams("name");
            String health = request.params("health");
            String age = request.queryParams("age");

            EndangeredAnimals newEanimals = new EndangeredAnimals(name, health, age);

            sql2oEanimals.add(newEanimals);
            response.redirect("/animal/list");
            return null;
        },new HandlebarsTemplateEngine());


        //sighting routes
        get("/new/sighting", (request, response)  ->{
            Map<String,Object> model = new HashMap<String,Object>();

            List<String> locations = new ArrayList<String>();
            locations.add(Sightings.ZONE_A);
            locations.add(Sightings.ZONE_B);
            locations.add(Sightings.ZONE_C);
            model.put("locations", locations);

            return new ModelAndView(model, "sighting.hbs");
        },new HandlebarsTemplateEngine());

        post("/sightings", (request, response) ->{
            Map<String, Object> model = new HashMap<>();

            String ranger_name = request.queryParams("ranger_name");
            String animal_name = request.queryParams("animal_name");
            String location = request.queryParams("location");

            Sightings newSightings = new Sightings(location, ranger_name, animal_name);

            sql2oSightings.add(newSightings);

            response.redirect("/animal/list");
            return null;
        },new HandlebarsTemplateEngine());

        //route that shows all records!
        get("/animal/list", (request, response) -> {
            Map<String,Object> model = new HashMap<>();

            List<Animals> animals = sql2oAnimals.getAll();
            List<EndangeredAnimals> endangeredAnimals = sql2oEanimals.getAll();
            List<Sightings> sightings = sql2oSightings.getAll();

            model.put("animals", animals);
            model.put("endangeredAnimals", endangeredAnimals);
            model.put("sightings", sightings);

            return new ModelAndView(model, "animalList.hbs");
        },new HandlebarsTemplateEngine());








    }
}
