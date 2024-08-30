import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class DB {
    private Statement statement;
    private Connection connection;

    public DB(String dbName) throws ClassNotFoundException , SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String dbURL ="jdbc:mysql://127.0.0.1:3306/" +dbName;
        this.connection= DriverManager.getConnection(dbURL , "root" ,"");
        this.statement = connection.createStatement();
    }
    public Statement getStatement() {
        return statement;
    }
    public void close() throws SQLException {
        this.statement.close();
        this.connection.close();
    }

}
