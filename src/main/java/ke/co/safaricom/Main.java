package ke.co.safaricom;


import ke.co.safaricom.Dao.animalDao;
import ke.co.safaricom.Dao.rangerDao;
import ke.co.safaricom.Dao.sightingDao;
import ke.co.safaricom.configuration.Age;
import ke.co.safaricom.configuration.Health;
import ke.co.safaricom.model.Animal;
import ke.co.safaricom.model.Ranger;
import ke.co.safaricom.model.Sighting;
import ke.co.safaricom.model.Sighting;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.post;

public class Main {
    private static sightingDao sightingDao;

    public static void main(String[] args) {
        port(8085);
        staticFiles.location("/public");
        staticFiles.expireTime(600L);
        staticFiles.registerMimeType("html", "text/html");
        staticFiles.registerMimeType("hbs", "text/html");
        staticFiles.externalLocation("src/main/resources");

        get("/ranger/add",(request, response) ->{
            return new ModelAndView( new HashMap<>(), "ranger.hbs");
        },new HandlebarsTemplateEngine());


        post("/create-ranger",(request, response) -> {
            String name = request.queryParams("ranger");
            Ranger ranger =new Ranger();

            ranger.setName( name );
            rangerDao.create( ranger);
            System.out.println(name );
            String alertScript = "<script>alert('successful added ranger "+name+"');</script>";
            return alertScript;
        });

        get("/animal/add",(request, response) ->{
            Map<String, Object> model = new HashMap<>();
           // model.put("options", animalDao.());
            return new ModelAndView(model, "animal.hbs");
        },new HandlebarsTemplateEngine());


        post("/create-animal",(request, response) -> {
            String name = request.queryParams("animal");
            String health = request.queryParams("health");
            String age = request.queryParams("age");
            String endangered = request.queryParams("endangered");
            Animal animal =new Animal();
            animal.setAge( Age.valueOf( age ) );
            animal.setHealth( Health.valueOf( health ) );
            animal.setName( name );
            animal.setEndangered( endangered.equalsIgnoreCase( "yes" ));
            animalDao.create(animal);
            System.out.println(name + " " +age +health);
            String alertScript = "<script>alert('successful added animal "+name+"' );</script>";
            return alertScript;
        });
        get("/sighting/add",(request, response) ->{
            Map<String, Object> model = new HashMap<>();
            model.put("rangers", rangerDao.getRangerOption());
            model.put("animals", animalDao.getAnimalOption());
            return new ModelAndView(model, "sighting.hbs");
        },new HandlebarsTemplateEngine());


        post("/create-sighting",(request, response) -> {
            String location = request.queryParams("location");
            String health = request.queryParams("health");
            String animalId = request.queryParams("animalId");
            String rangerId = request.queryParams("rangerId");
            Sighting sighting =new Sighting();
            sighting.setLocation( location );
            sighting.setHealth( health );
            sighting.setAnimal_id( Integer.parseInt( animalId ) );
            sighting.setRanger_id( Integer.parseInt( rangerId) );

            sightingDao.create(sighting);

            System.out.println(location + " " +health);
            String alertScript = "<script>alert('successful added sighting "+sighting+"' );</script>";
            return alertScript;
        });


    }

}
