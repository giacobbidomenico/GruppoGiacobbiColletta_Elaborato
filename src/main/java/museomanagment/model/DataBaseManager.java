package museomanagment.model;

import java.sql.ResultSet;
import java.util.Optional;

/**
 * Models a basic databaseManager.
 */
public interface DataBaseManager {


    /**
     * Closes connection.
     */
    void close();

    /**
     * set the query to be executed.
     * @param query
     */
    void setQuery(String query);

    /**
     * 
     * @param parameter
     * @return true if parameter was set
     */
    boolean addParameter(String parameter);

    /**
     * Executes query.
     * @return result from the query
     */
    Optional<ResultSet> executeQuery();

}
