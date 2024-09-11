import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class LoginFrame extends JFrame {
    public  LoginFrame(){
        setTitle("Login...");
        setBounds(400,250,500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel labelEmail = new JLabel("Email");
        JLabel labelPw = new JLabel("Password");

        JTextField textFieldEmail = new JTextField();
        textFieldEmail.setPreferredSize(new Dimension(250,30));

        JPasswordField textFieldPW = new JPasswordField();
        textFieldPW.setPreferredSize(new Dimension(250,30));
        JButton buttonLogin = new JButton("Login");

        c.insets = new Insets(10,10,10,10);
        c.gridx = 0;c.gridy = 0;
        add(labelEmail , c);
        c.gridx = 1;c.gridy = 0;
        add(textFieldEmail , c);
        c.gridx = 0;c.gridy = 1;
        add(labelPw , c);
        c.gridx = 1;c.gridy = 1;
        add(textFieldPW , c);

        c.gridx = 1;c.gridy = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(buttonLogin , c);

        JLabel labelMessage = new JLabel(); labelMessage.setForeground(Color.RED);
        c.gridx = 1;c.gridy = 3;
        c.fill = GridBagConstraints.HORIZONTAL;
        add(labelMessage , c);
        ////////////////////////////////
        buttonLogin.addActionListener(e->{
            // check email and password

            String email = textFieldEmail.getText().trim();
            String pw = textFieldPW.getText();

            try {
                DB db = new DB("ecommerce");
                ResultSet resultSet = db.getStatement().executeQuery("select * from users where email='"+email+"' and password='"+pw+"'");
                if (resultSet.next()){
//                    id, name, email, password, mobile, role, created_at, created_by
                    User loginUser = new User(email , pw);
                    loginUser.setId(resultSet.getInt("id"));
                    loginUser.setName(resultSet.getString("name"));
                    loginUser.setMobile(resultSet.getString("mobile"));
                    loginUser.setRole(resultSet.getString("role"));
                    loginUser.setCreatedBy(resultSet.getInt("created_by"));
                    loginUser.setCreatedAt((Date) resultSet.getObject("created_at"));

                    new Main(loginUser).setVisible(true);
                    this.setVisible(false);

                }else{
                    labelMessage.setText("Invalid email or password.");
                }
                db.close();

            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }


        });
    }

    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}
