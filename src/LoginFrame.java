import javax.swing.*;
import java.awt.*;

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

        ////////////////////////////////
        buttonLogin.addActionListener(e->{
            // check email and password
            new Main().setVisible(true);
            this.setVisible(false);
        });
    }

    public static void main(String[] args) {
        new LoginFrame().setVisible(true);
    }
}
