import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Order {
    private  int id ;
    private Date orderTime;
    private int customerId;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public static void addOrder(int id , String date){
        try {
            DB db = new DB("ecommerce");

            Connection cn = db.getConnection();
            PreparedStatement preparedStatement= cn.prepareStatement("insert into orders( id , order_time) values(?,?)");
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2,date);
            preparedStatement.executeUpdate();
//            String qry ="insert into orders( id , order_time) values("+id+" , '"+date+"')";
//
//            db.getStatement().executeUpdate(qry);
            db.close();

        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static int getLastOrderId() {
        try {
            DB db = new DB("ecommerce");
            ResultSet resultSet= db.getStatement().executeQuery("select max(id) m from orders");
            int newOrderID = 1;
            if (resultSet.next()){
               newOrderID =  resultSet.getInt("m") +1;
            }
            db.close();
            return  newOrderID;
        } catch (Exception ex) {
            System.out.println("Exception : " + ex.getMessage());
            return 1;
        }
    }
}
