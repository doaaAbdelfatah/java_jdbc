import java.sql.ResultSet;
import java.util.ArrayList;

public class Product {
//    id, name, price, qty, comments, image, brand, color, size, category_id, user_id, created_at
   private int id ,qty ,category_id ,user_id ,price;
   private String name ,comments,image,brand,color,size;

    public Product() {
    }


    static ArrayList<Product> listProducts(){
        ArrayList<Product> products= new ArrayList<>();
        try {
            DB db = new DB("ecommerce");
            ResultSet resultSet= db.getStatement().executeQuery("select id, name, price, qty, comments, image, brand, color, size, category_id, user_id, created_at from products");
            products.add(null);
            while (resultSet.next()){
                Product p = new Product();
                p.id = resultSet.getInt("id");
                p.name = resultSet.getString("name");
                p.price = resultSet.getInt("price");
                p.qty = resultSet.getInt("qty");
                p.size = resultSet.getString("size");
                p.category_id = resultSet.getInt("category_id");
                products.add(p);
            }
            db.close();
        } catch (Exception ex) {
            System.out.println("Exception : " + ex.getMessage());
        }
        return  products;
    }
    static ArrayList<Product> listProducts(int category_id){
        ArrayList<Product> products= new ArrayList<>();
        try {
            DB db = new DB("ecommerce");
            ResultSet resultSet= db.getStatement().executeQuery("select id, name, price, qty, comments, image, brand, color, size, category_id, user_id, created_at from products where category_id=" + category_id);
            products.add(null);
            while (resultSet.next()){
                Product p = new Product();
                p.id = resultSet.getInt("id");
                p.name = resultSet.getString("name");
                p.price = resultSet.getInt("price");
                p.qty = resultSet.getInt("qty");
                p.size = resultSet.getString("size");
                p.category_id = resultSet.getInt("category_id");
                products.add(p);
            }
            db.close();
        } catch (Exception ex) {
            System.out.println("Exception : " + ex.getMessage());
        }
        return  products;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return  name + "( price : "+price+" , size : "+size+")" ;
    }
}
