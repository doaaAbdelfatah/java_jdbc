import javax.swing.*;
import java.awt.*;

public class LayoutDemo extends JFrame {
    public LayoutDemo(){
        setBounds(300,150,500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JButton b1 = new JButton("One");
        JButton b2 = new JButton("Two");
        JButton b3 = new JButton("Three");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5,5,5,5);
        add(b1 , gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;


        add(b2 , gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx =150;
        gbc.ipady =150;
        add(b3 , gbc);
        JColorChooser jColorChooser = new JColorChooser();
        gbc.gridx =2;
        gbc.gridy =0;

        add(jColorChooser ,gbc);
    }

    public static void main(String[] args) {
        new LayoutDemo().setVisible(true);
    }
}
