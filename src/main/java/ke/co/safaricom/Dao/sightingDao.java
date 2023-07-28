package ke.co.safaricom.Dao;

import ke.co.safaricom.config.DatabaseConfig;
import ke.co.safaricom.model.Ranger;
import ke.co.safaricom.model.Sighting;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class sightingDao {
    private static final Sql2o sql2o = DatabaseConfig.getDatabase();

    public static void create(Sighting sighting) {
        try (Connection connection = sql2o.open()) {
            String query = "INSERT INTO sightings (name VALUES (:name);";
            connection.createQuery( query )
                    .addParameter( "location", sighting.getLocation() )
                    .addParameter( "health", sighting.getHealth() )
                    .addParameter("animal_id", sighting.getAnimal_id())
                    .addParameter("ranger_id", sighting.getRanger_id())
                    .executeUpdate();
        } catch (Exception exception) {
            System.out.println( exception.getMessage() );
        }
    }
}
