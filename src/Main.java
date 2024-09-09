import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main  extends  JFrame{
    private JDesktopPane desktopPane;

    public Main(){
        setTitle("Doaa Market");
        desktopPane = new JDesktopPane();
        getContentPane().add(desktopPane);
        setBounds(100,100,1200,600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu menueManage = new JMenu("Manage");
        JMenu menueOders = new JMenu("Customers and Orders");
        menuBar.add(menueManage);
        menuBar.add(menueOders);
        JMenuItem menuItemUsers = new JMenuItem("Users");
        JMenuItem menuItemCategories = new JMenuItem("Add Category");
        JMenuItem menuItemShowCategories = new JMenuItem("Show All Category");
        JMenuItem menuItemProducts = new JMenuItem("Products");

        menueManage.add(menuItemUsers);
        menueManage.addSeparator();
        menueManage.add(menuItemCategories);
        menueManage.add(menuItemShowCategories);
        menueManage.addSeparator();
        menueManage.add(menuItemProducts);

        JMenuItem menuItemCustomers = new JMenuItem("Customers");
        JMenuItem menuItemOrders = new JMenuItem("Orders");
        menueOders.add(menuItemCustomers);
        menueOders.add(menuItemOrders);
        // --------------------------------------------
        menuItemCategories.addActionListener(e->{
           String name = JOptionPane.showInputDialog(null , "Enter Category Name");
           if (name != null && !name.trim().equals("")){
               try {
                   DB db = new DB("ecommerce");
                   db.getStatement().executeUpdate("insert into categories(name) values('"+ name +"')");
                   db.close();
               } catch (Exception ex) {
                   System.out.println("Exception : " + ex.getMessage());
               }
           }
        });

        // show categories
        menuItemShowCategories.addActionListener(e->{
            String allCategories ="";
            try {
                DB db = new DB("ecommerce");
                ResultSet resultSet= db.getStatement().executeQuery("select id ,name from categories");
                while (resultSet.next()){
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");

                    allCategories += (id + "  " + name +"\n");
                }
               db.close();
            } catch (Exception ex) {
                System.out.println("Exception : " + ex.getMessage());
            }
            JOptionPane.showMessageDialog(null , allCategories);
        });

        // ////////////////////

        menuItemUsers.addActionListener(e->{
            UsersFrame fUsers = new UsersFrame();
            fUsers.setVisible(true);
            desktopPane.add(fUsers);

        });

        menuItemProducts.addActionListener(e->{
            ProductFrame productFrame= new ProductFrame();
            productFrame.setVisible(true);
            desktopPane.add(productFrame);
        });

    }





}
