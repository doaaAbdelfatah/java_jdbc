import java.sql.ResultSet;
import java.util.ArrayList;

public class Category {
    private  int id;
    private String name;

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
}
