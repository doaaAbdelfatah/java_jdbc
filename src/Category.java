import java.sql.ResultSet;
import java.util.ArrayList;

public class Category { // Entity Class " Class bind DB Table"
    private  int id;
    private String name;

    private  int category_id;

    public Category(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    static  ArrayList<Category> listCategories(){
        ArrayList<Category> categories= new ArrayList<>();
        try {
            DB db = new DB("ecommerce");
            ResultSet resultSet= db.getStatement().executeQuery("select id ,name from categories");
            categories.add(null);
            while (resultSet.next()){
                Category c = new Category();
                c.id = resultSet.getInt("id");
                c.name = resultSet.getString("name");
                categories.add(c);
            }
            db.close();
        } catch (Exception ex) {
            System.out.println("Exception : " + ex.getMessage());
        }
        return  categories;
    }

    static  ArrayList<Category> listMainCategories(){
        ArrayList<Category> categories= new ArrayList<>();
        try {
            DB db = new DB("ecommerce");
            ResultSet resultSet= db.getStatement().executeQuery("select id ,name from categories where category_id is null");
            categories.add(null);
            while (resultSet.next()){
                Category c = new Category();
                c.id = resultSet.getInt("id");
                c.name = resultSet.getString("name");
                categories.add(c);
            }
            db.close();
        } catch (Exception ex) {
            System.out.println("Exception : " + ex.getMessage());
        }
        return  categories;
    }
    static  ArrayList<Category> listSubCategories(int mainCategoryID){
        ArrayList<Category> categories= new ArrayList<>();
        try {
            DB db = new DB("ecommerce");
            ResultSet resultSet= db.getStatement().executeQuery("select id ,name from categories where category_id =" + mainCategoryID);
            categories.add(null);
            while (resultSet.next()){
                Category c = new Category();
                c.id = resultSet.getInt("id");
                c.name = resultSet.getString("name");
                categories.add(c);
            }
            db.close();
        } catch (Exception ex) {
            System.out.println("Exception : " + ex.getMessage());
        }
        return  categories;
    }
    @Override
    public String toString() {
        return  name ;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
