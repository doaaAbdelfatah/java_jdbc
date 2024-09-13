import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main  extends  JFrame{
    private JDesktopPane desktopPane;
    private User loginUser;

    public Main(User loginUser , LoginFrame loginFrame){
        this.loginUser = loginUser;

        setTitle("Welcome " + loginUser.getName());
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
        JMenuItem menuItemMCategories = new JMenuItem("Manage Category");
        JMenuItem menuItemCategories = new JMenuItem("Add Category");
        JMenuItem menuItemShowCategories = new JMenuItem("Show All Category");
        JMenuItem menuItemProducts = new JMenuItem("Products");
        JMenuItem menuItemLogout = new JMenuItem("LogOut");

        menueManage.add(menuItemUsers);
        menueManage.addSeparator();

        menueManage.add(menuItemMCategories);
        menueManage.add(menuItemCategories);
        menueManage.add(menuItemShowCategories);
        menueManage.addSeparator();
        menueManage.add(menuItemProducts);
        menueManage.addSeparator();
        menueManage.add(menuItemLogout);

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

        menuItemMCategories.addActionListener(e->{
            CategoryFrame categoryFrame = new CategoryFrame();
            categoryFrame.setVisible(true);
            desktopPane.add(categoryFrame);
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
            UsersFrame fUsers = new UsersFrame(loginUser);
            fUsers.setVisible(true);
            desktopPane.add(fUsers);

        });

        menuItemProducts.addActionListener(e->{
            ProductFrame productFrame= new ProductFrame(loginUser);
            productFrame.setVisible(true);
            desktopPane.add(productFrame);
        });
        menuItemLogout.addActionListener(e->{
            this.setVisible(false);
            loginFrame.setVisible(true);
        });
    }



    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }
}
