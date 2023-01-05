package museomanagment.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * DataBaseManager implementation.
 */
public class DataBaseManagerImpl implements DataBaseManager {

    private final String dbName;
    private final String userName;
    private final String password;
    private Connection conn;
    private Optional<PreparedStatement> query;
    private int parameterNumber;
    private boolean isClosed;

    /**
     * 
     * @param dbName database name to be connected to
     * @param userName
     * @param password
     */
    public DataBaseManagerImpl(final String dbName, final String userName, final String password) {
        this.userName = userName;
        this.dbName = dbName;
        this.password = password;
        this.conn = null;
        this.query = Optional.empty();
        this.parameterNumber = 1;
        this.isClosed = false;
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
        this.isClosed = true;
        try {
            this.conn.close();
        } catch (final SQLException e) {
            throw new IllegalStateException("Could not establish a connection with db", e);
        }
    }

    @Override
    public void setQuery(final String query) {
        if (this.isClosed) {
            throw new IllegalStateException();
        }
        try {
            this.query = Optional.ofNullable(this.conn.prepareStatement(query));
        } catch (final SQLException e) {
            throw new IllegalStateException();
        }
    }

    @Override
    public boolean addParameter(final String parameter) {
        if (this.query.isEmpty()) {
            return false;
        }
        try {
            this.query.get().setString(parameterNumber, parameter);
        } catch (final SQLException e) {
            throw new IllegalStateException();
        }
        return true;
    }

    @Override
    public ResultSet executeQuery() {
        if (this.query.isEmpty()) {
            throw new IllegalStateException();
        }
        try {
            return this.query.get().executeQuery();
        } catch (final SQLException e) {
            throw new IllegalStateException();
        }
    }

}
