import javax.swing.*;
import java.awt.*;

public class GridBagLayoutExample {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("GridBagLayout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Set the layout
        frame.setLayout(new GridBagLayout());

        // Create GridBagConstraints object to define constraints for components
        GridBagConstraints gbc = new GridBagConstraints();

        // Label 1 - Positioned at row 0, column 0
        gbc.gridx = 0;
        gbc.gridy = 0;
//        gbc.insets = new Insets(5, 5, 5, 5); // Padding around the component
        frame.add(new JLabel("Label 1"), gbc);

//        // Label 2 - Positioned at row 0, column 1
        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(new JLabel("Label 2"), gbc);
//
        // Button 1 - Positioned at row 1, column 0
        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(new JButton("Button 1"), gbc);
//
        // Button 2 - Positioned at row 1, column 1 and spans two columns
        gbc.gridx = 1;
        gbc.gridy = 1;
//        gbc.gridwidth = 2; // Span across 2 columns
        frame.add(new JButton("Button 2"), gbc);
//
        // TextField - Positioned at row 2, column 0, spanning 3 columns
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Stretch the TextField to fill horizontal space
        frame.add(new JTextField("Text Field"), gbc);

        // Make the frame visible
        frame.setVisible(true);
    }
}
