package museomanagment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseManagerImpl implements DataBaseManager {

    private String dbName;
    private String userName;
    private String password;
    private Connection conn;

    public DataBaseManagerImpl(String dbName, String userName, String password) {
        this.userName = userName;
        this.dbName = dbName;
        this.password = password;
    }

    private void connect() {
        final String dbUri = "jdbc:mysql://localhost:3306/" + this.dbName;
        try {
            // Thanks to the JDBC DriverManager we can get a connection to the database
            this.conn = DriverManager.getConnection(dbUri, this.userName, this.password);
        } catch (final SQLException e) {
            throw new IllegalStateException("Could not establish a connection with db", e);
        }

    }

    @Override
    public void close() {
        try {
            this.conn.close();
        } catch (final SQLException e) {
            throw new IllegalStateException("Could not establish a connection with db", e);
        }
    }

    @Override
    public void setQuery(String query) {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean addParameter(String parameter) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public ResultSet executeQuery() {
        // TODO Auto-generated method stub
        return null;
    }

}
