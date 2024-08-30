import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersFrame extends JFrame {

    private DefaultTableModel dtm;

    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfMobile;
    public  UsersFrame(){
        setTitle("Manage Users");
        setBounds(250,150,700,500);
        String [] colsName ={"id", "name", "email", "mobile", "role", "created at", "created by"};
        dtm = new DefaultTableModel(null ,colsName);
        fillUserTable();
        JTable table = new JTable(dtm);
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        JLabel labelName = new JLabel("Name");
        JLabel labelEmail = new JLabel("Email");
        JLabel labelMobile = new JLabel("Mobile");
        JLabel label = new JLabel("");
        tfName= new JTextField();
        tfEmail= new JTextField();
        tfMobile= new JTextField();

        JButton  btnAdd = new JButton("Add User");
//
        JPanel panel = new JPanel( new GridLayout(2,4 ,2,2));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panel.add(labelName);
        panel.add(labelEmail);
        panel.add(labelMobile);
        panel.add(label);
        panel.add(tfName);
        panel.add(tfEmail);
        panel.add(tfMobile);
        panel.add(btnAdd);
        getContentPane().add(panel , BorderLayout.NORTH);

        btnAdd.addActionListener(e->{
            try {
                DB db = new DB("ecommerce");
                // insert into users(name , email ,mobile) values('','','')
                db.getStatement().executeUpdate("insert into users(name , email ,mobile) values('"+tfName.getText()+"','"+ tfEmail.getText()+"','"+tfMobile.getText()+"')");
                db.close();

                fillUserTable();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    void fillUserTable(){
        dtm.setRowCount(0); // delete all data in table
        DB db = null;
        try {
            db = new DB("ecommerce");
            ResultSet resultSet = db.getStatement().executeQuery("select id, name, email, mobile, role, created_at, created_by from users");
            while (resultSet.next()){
                String [] row = {
                        resultSet.getString("id") ,
                        resultSet.getString("name") ,
                        resultSet.getString("email") ,
                        resultSet.getString("mobile") ,
                        resultSet.getString("role") ,
                        resultSet.getString("created_at") ,
                        resultSet.getString("created_by")
                };
                dtm.addRow(row);
            }
            db.close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
