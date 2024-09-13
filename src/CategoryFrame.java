import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryFrame extends JInternalFrame {
    private  DefaultTableModel dtm;
    public CategoryFrame(){
        setIconifiable(true);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Manage Categories");
        setBounds(150,20,700,500);

        JPanel panel = new JPanel( new GridLayout(2,3 ,2,2));
        panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        JLabel labelName = new JLabel("Name");
        JLabel labelCategory = new JLabel("Main Category");

        JTextField textFieldName = new JTextField();
        JComboBox<Category> comboBoxCategory  = new JComboBox<>(Category.listCategories().toArray(new Category[0]));

        panel.add(labelName);
        panel.add(labelCategory);
        panel.add(new JLabel());
        panel.add(textFieldName);
        panel.add(comboBoxCategory);
        JButton buttonSave = new JButton("Save");
        panel.add(buttonSave);

        add(panel , BorderLayout.NORTH);

        String [] colsName ={"id" ,"name" , "main Category"};
        dtm = new DefaultTableModel(null ,colsName);
        fillCatTable();
        JTable table = new JTable(dtm);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane );

        buttonSave.addActionListener(e->{
            try {
                DB db = new DB("ecommerce");
                Category selectedCat = (Category) comboBoxCategory.getSelectedItem();
                String qry ="";
                if (selectedCat == null){
                    qry="insert into categories(name ) values('"+textFieldName.getText()+"')";
                }else{
                    qry="insert into categories(name ,category_id) values('"+textFieldName.getText()+"','"+selectedCat.getId()+"')";
                }
                // insert into categories(name ,category_id) values('','')
                db.getStatement().executeUpdate(qry);
                db.close();
                fillCatTable();
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void fillCatTable(){
        dtm.setRowCount(0); // delete all data in table
        DB db = null;
        try {
            db = new DB("ecommerce");
            ResultSet resultSet = db.getStatement().executeQuery("SELECT c.id , c.name ,c.category_id ,m.name main_cat from categories c left join categories m on (c.category_id =m.id)");
            while (resultSet.next()){
                String [] row = {
                        resultSet.getString("id") ,
                        resultSet.getString("name") ,
                        resultSet.getString("main_cat")
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
