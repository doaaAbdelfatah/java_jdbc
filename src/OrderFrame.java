import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OrderFrame extends JInternalFrame {
    private JPanel panel1;
    private JLabel labelProduct;
    private JTextField textFieldQty;
    private JButton buttonAdd;
    private JComboBox<Category> comboBoxSubCategory;
    private JComboBox<Category> comboBoxCategory;
    private JComboBox comboBoxProducts;
    private JLabel labelTotal;

    private DefaultTableModel dtm ;

    public OrderFrame(){

        getContentPane().add(panel1);
        setTitle("Order");
        setBounds(100,40,700,400);
        setIconifiable(true);
        setClosable(true);
        setMaximizable(true);

        setResizable(true);

        String columns[] ={"id" ,"name" ,"qty" ,"price" ,"vat" , "total"};
        dtm = new DefaultTableModel(null ,columns); // محتوي الجدول الداخلي
        JTable tableProducts = new JTable(dtm);
        JScrollPane scrollPane = new JScrollPane(tableProducts);

        panel1.add(scrollPane);
        for (Category category: Category.listMainCategories()){
            comboBoxCategory.addItem(category);
        }
        for (Product p: Product.listProducts()){
            comboBoxProducts.addItem(p);
        }
        comboBoxCategory.addActionListener(e->{
           Category mainCat = (Category) comboBoxCategory.getSelectedItem();
            comboBoxSubCategory.removeAllItems();
            for (Category category: Category.listSubCategories(mainCat.getId())){
                comboBoxSubCategory.addItem(category);
            }

            comboBoxProducts.removeAllItems();
            for (Product p: Product.listProducts(mainCat.getId())){
                comboBoxProducts.addItem(p);
            }
        });
        comboBoxSubCategory.addActionListener(e->{
            Category cat = (Category) comboBoxSubCategory.getSelectedItem();
            comboBoxProducts.removeAllItems();
            if (cat != null){
                for (Product p: Product.listProducts(cat.getId())){
                    comboBoxProducts.addItem(p);
                }
            }

        });

        buttonAdd.addActionListener(e->{
            Product product = (Product) comboBoxProducts.getSelectedItem();
            int qty = Integer.parseInt(textFieldQty.getText());
            double rowTotal =Math.round(product.getPrice()  * 1.19  * qty );
            String [] row = {
                product.getId() +"" ,
                    product.getName(),
                    qty+"",
                    product.getPrice() +"",
                    product.getPrice() * 0.19  + "(19%)",
                    rowTotal + ""
            };
            dtm.addRow(row);
            double s = Double.parseDouble(labelTotal.getText());
            labelTotal.setText((s + rowTotal )+"");

        });
    }



}
