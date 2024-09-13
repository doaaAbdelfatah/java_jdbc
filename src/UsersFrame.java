import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersFrame extends JInternalFrame {

    private DefaultTableModel dtm;

    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfMobile;
    private JComboBox<String> roleComboBox;

    private  boolean editAble = true;

    public  UsersFrame(User loginUser){

        setIconifiable(true);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Manage Users");
        setBounds(150,20,700,500);
        String [] colsName ={"id", "name", "email", "mobile", "role", "created at", "created by"};
        String []  dbColNames ={"id", "name", "email", "mobile", "role", "created_at", "created_by"};

        dtm = new DefaultTableModel(null ,colsName);
        fillUserTable();
        JTable table = new JTable(dtm);

        String arrRole [] ={"admin" ,"editor","user"};
        JComboBox<String> roleCB = new JComboBox<>(arrRole);
        TableColumn columnRole = table.getColumnModel().getColumn(4);
        columnRole.setCellEditor(new DefaultCellEditor(roleCB));

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        JLabel labelName = new JLabel("Name");
        JLabel labelEmail = new JLabel("Email");
        JLabel labelMobile = new JLabel("Mobile");
        JLabel labelRole= new JLabel("Role");
        JLabel label = new JLabel("");
        tfName= new JTextField();
        tfEmail= new JTextField();
        tfMobile= new JTextField();
        roleComboBox=new JComboBox<>(arrRole);

        JButton  btnAdd = new JButton("Add User");
        JButton  btnDelete = new JButton("Delete User");
//
        JPanel panel = new JPanel( new GridLayout(2,5 ,2,2));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        panel.add(labelName);
        panel.add(labelEmail);
        panel.add(labelMobile);
        panel.add(labelRole);
        panel.add(label);
        panel.add(tfName);
        panel.add(tfEmail);
        panel.add(tfMobile);
        panel.add(roleComboBox);
        panel.add(btnAdd);

        getContentPane().add(panel , BorderLayout.NORTH);

        JPanel panelSouth = new JPanel();
        panelSouth.add(btnDelete);
        this.getContentPane().add(panelSouth ,BorderLayout.SOUTH);
        btnAdd.addActionListener(e->{
            editAble =false;
            try {
                DB db = new DB("ecommerce");
                // insert into users(name , email ,mobile) values('','','')
                db.getStatement().executeUpdate("insert into users(name , email ,mobile ,role ,created_by) values('"+tfName.getText()+"','"
                        + tfEmail.getText()+"','"+tfMobile.getText()+"' ,'"+roleComboBox.getSelectedItem()+"' , '"+loginUser.getId()+"')");
                db.close();
                fillUserTable();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            editAble=true;
        });

        btnDelete.addActionListener(e->{
          editAble=false;
          int row =  table.getSelectedRow();
          String userID = (String) dtm.getValueAt(row , 0);
            DB db = null;
            try {
                db = new DB("ecommerce");
                db.getStatement().executeUpdate("delete from users where id=" + userID);
                db.close();
                dtm.removeRow(row);
            } catch (ClassNotFoundException ex) {
                System.out.println("ClassNotFoundException : " + ex.getMessage());
            } catch (SQLException exp) {
                System.out.println("SQLException : " + exp.getMessage());
            }
            editAble=true;
        });

     table.getModel().addTableModelListener(e -> {
         if (editAble){
             int row =  e.getFirstRow();
             int col = e.getColumn() ;
             String value = (String) table.getValueAt(row , col); // Doaa   name ='Doaa'
             String userID = (String) table.getValueAt(row , 0); // 4
             try {
                 DB db = new DB("ecommerce"); // update users set name='Doaa' where id=4
                 db.getStatement().executeUpdate("update users set "
                         + dbColNames[col] +"='"+value+"' where id=" + userID);
                 db.close();
             } catch (ClassNotFoundException ex) {
                 throw new RuntimeException(ex);
             } catch (SQLException ex) {
                 throw new RuntimeException(ex);
             }
         }
     });
    }

    void fillUserTable(){
        dtm.setRowCount(0); // delete all data in table
        DB db = null;
        try {
            db = new DB("ecommerce");
            ResultSet resultSet = db.getStatement().executeQuery("select u.id, u.name, u.email, u.mobile, u.role, u.created_at, u.created_by , c.name creator_name from users u left join users c on (u.created_by = c.id)");
            while (resultSet.next()){
                String [] row = {
                        resultSet.getString("id") ,
                        resultSet.getString("name") ,
                        resultSet.getString("email") ,
                        resultSet.getString("mobile") ,
                        resultSet.getString("role") ,
                        resultSet.getString("created_at") ,
                        resultSet.getString("creator_name")
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
