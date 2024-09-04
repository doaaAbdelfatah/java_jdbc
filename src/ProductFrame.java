import javax.swing.*;
import java.awt.*;

public class ProductFrame extends JInternalFrame {
    public ProductFrame(){
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

        addProductPanel.add(labelName);
        addProductPanel.add(labelPrice);
        addProductPanel.add(labelQty);
        addProductPanel.add(labelBrand);
        addProductPanel.add(labelColor);
        addProductPanel.add(labelSize);
        addProductPanel.add(labelcategory);
        addProductPanel.add(labelImage);





        ///////////////////////

        // showProductPanel



        ///////////////////////////

    }


}
