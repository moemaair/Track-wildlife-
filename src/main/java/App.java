import dao.Sql2oAnimals;
import dao.Sql2oEanimals;
import modal.Animals;

import modal.EndangeredAnimals;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.* ;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");


        String connectmetodatabase = "jdbc:postgresql://localhost:5432/wildlife_tracker"; //connect to database wildlife_tracker
        Sql2o sql2o = new Sql2o(connectmetodatabase, "postgres", "bnmk");
        Sql2oAnimals sql2oAnimals = new Sql2oAnimals(sql2o);
        Sql2oEanimals sql2oEanimals = new Sql2oEanimals(sql2o);

        // home route
        get("/", (req, res) ->{
            Map<String,Object> model = new HashMap<>();
            return modelAndView(model, "index.hbs");
        },new HandlebarsTemplateEngine());

        //animal route
        get("/animals/new", (req, res) ->{
            Map<String,Object> model = new HashMap<>();
            List<Animals> animals = sql2oAnimals.getAll();
            model.put("animals", animals);
            return modelAndView(model, "animal.hbs");
        },new HandlebarsTemplateEngine());

        post("/animals", (req,res)->{
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(req.queryParams("id"));
            String name = req.queryParams("name");
            Animals newAnimals = new Animals(id, name);
            sql2oAnimals.add(newAnimals);
            res.redirect("/animals/new");
            return null;
        }, new HandlebarsTemplateEngine());

        //retrieving all animals
//        get("/animals", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            return new ModelAndView(model, "animalList.hbs");
//        }, new HandlebarsTemplateEngine());


//        endangered route
        get("/endangeredanimal/new", (req,res)->{
            Map<String, Object> model = new HashMap<String, Object>();
            List<EndangeredAnimals> endangeredAnimals = sql2oEanimals.getAll();
            return new ModelAndView(model,"endangeredanimal.hbs");
        }, new HandlebarsTemplateEngine());

        post("/endangeredanimals", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int id = Integer.parseInt(request.queryParams("id"));
            String name = request.queryParams("name");
            String health = request.queryParams("health");
            int age = Integer.parseInt(request.queryParams("age"));

            EndangeredAnimals newEanimals = new EndangeredAnimals(id, name, health, age);
            sql2oEanimals.add(newEanimals);
            response.redirect("/endangeredanimal/new");
            return null;
        }, new HandlebarsTemplateEngine());


    }
}
