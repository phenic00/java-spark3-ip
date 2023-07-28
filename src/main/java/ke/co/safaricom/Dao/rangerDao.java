package ke.co.safaricom.Dao;

import ke.co.safaricom.config.DatabaseConfig;
import ke.co.safaricom.model.Animal;
import ke.co.safaricom.model.Ranger;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class rangerDao {
    private static final Sql2o sql2o = DatabaseConfig.getDatabase();

    public static void create(Ranger ranger) {
        try (Connection connection = sql2o.open()) {
            String query = "INSERT INTO rangers (name VALUES (:name);";
            connection.createQuery( query )
                    .addParameter( "name", ranger.getName() )
                    .executeUpdate();
        } catch (Exception exception) {
            System.out.println( exception.getMessage() );
        }
    }
}
