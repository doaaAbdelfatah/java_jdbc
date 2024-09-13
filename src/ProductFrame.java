import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class ProductFrame extends JInternalFrame {
    private JTextField tfName , tfPrice , tfBrand , tfQty ,tfSize;
    private JColorChooser colorChooser ;
    private JComboBox<Category> comboBoxCategory ;

    public ProductFrame(User loginUser){
        setTitle("Manage Products");
        setIconifiable(true);
        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setBounds(150,20,700,500);

        JTabbedPane tabbedPane = new JTabbedPane();
        getContentPane().add(tabbedPane);

        JPanel addProductPanel = new JPanel(new GridLayout(9,2,10,10));
        addProductPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        tabbedPane.addTab("Add Product" , addProductPanel );

        JPanel showProductPanel = new JPanel(new BorderLayout());
        showProductPanel.setBackground(Color.YELLOW);
        showProductPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        tabbedPane.addTab("Show All Products" , showProductPanel );

//        name, price, qty, comments, image, brand, color, size, category_id
        // addProductPanel

        JLabel labelName = new JLabel("Name");
        JLabel labelPrice = new JLabel("Price");
        JLabel labelQty = new JLabel("Quantity");
        JLabel labelBrand = new JLabel("Brand");
        JLabel labelColor= new JLabel("Color");
        JLabel labelSize= new JLabel("Size");
        JLabel labelcategory= new JLabel("Category");
        JLabel labelImage= new JLabel("Image");

//        tfName , tfPrice , tfBrand , tfQty ,tfSize

        tfName = new JTextField();
        tfPrice = new JTextField();
        tfBrand = new JTextField();
        tfQty = new JTextField();
        tfSize = new JTextField();

        JPanel filePanel  = new JPanel(new GridLayout(1,2,5,5));
        JTextField tfImage = new JTextField();
        tfImage.setEnabled(false);
        tfImage.setBackground(Color.white);
        JButton btnImage = new JButton("Choose Image");
        filePanel.add(tfImage);
        filePanel.add(btnImage);

        btnImage.addActionListener(e->{
            JFileChooser fileChooser = new JFileChooser();
            int choise = fileChooser.showOpenDialog(null);
            if (choise == 0 ){
                tfImage.setText(fileChooser.getSelectedFile().getAbsolutePath());
            }
        });
        colorChooser = new JColorChooser();
        comboBoxCategory = new JComboBox<>(Category.listCategories().toArray(new Category[0]));

        addProductPanel.add(labelName);addProductPanel.add(tfName);
        addProductPanel.add(labelPrice);addProductPanel.add(tfPrice);
        addProductPanel.add(labelQty);addProductPanel.add(tfQty);
        addProductPanel.add(labelBrand);addProductPanel.add(tfBrand);
        addProductPanel.add(labelColor);addProductPanel.add(colorChooser);
        addProductPanel.add(labelSize);addProductPanel.add(tfSize);
        addProductPanel.add(labelcategory);addProductPanel.add(comboBoxCategory);
        addProductPanel.add(labelImage);
        addProductPanel.add(filePanel);

        JButton btnAdd = new JButton("Add Product");
        addProductPanel.add(new JLabel());
        addProductPanel.add(btnAdd);
        btnAdd.addActionListener(e -> {
           Category category = (Category) comboBoxCategory.getSelectedItem();
           int catID = category.getId();
            try {
                DB db = new DB("ecommerce");
                db.getStatement().executeUpdate("insert into products ( name, price, qty, image, brand, color, size, category_id , user_id) values( '"
                        + tfName.getText()+ "', '"+tfPrice.getText()+"', "+tfQty.getText()+", '"+tfImage.getText()
                        +"', '"+ tfBrand.getText()+"', '' , '"+tfSize.getText()+"', "+catID+" , "+ loginUser.getId()+")");
                db.close();

            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }




        });








        ///////////////////////

        // showProductPanel



        ///////////////////////////

    }


}
