import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBDemo {

    public static void main(String[] args) {
        try {
            // 1- load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
           // 2- db url
            String dbURL ="jdbc:mysql://127.0.0.1:3306/hr"; // 127.0.0.1 == localhost
            // 3- establish connection
            Connection connection= DriverManager.getConnection(dbURL , "root" ,"");
            // 4- create statement
            Statement statement = connection.createStatement();
            // 5 - execute query
//            int rslt = statement.executeUpdate("insert into regions values (5,'test')");
//            int rslt = statement.executeUpdate("delete from regions where region_id in (6,5)");
           // int rslt = statement.executeUpdate("update regions set region_name='Test Update' where region_id=5");
//            System.out.println(rslt);
            ResultSet resultSet = statement.executeQuery("select region_id , region_name from regions");
            // 6-
           while (resultSet.next()){
               String id = resultSet.getString("region_id");
               String name = resultSet.getString("region_name");
               System.out.println(id + "\t" + name );
           }
            // 7- close connection
            statement.close();
            connection.close();

        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
        }
    }

}
