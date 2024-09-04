import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InternalFrameExample extends JFrame {

    private JDesktopPane desktopPane;

    public InternalFrameExample() {
        // Create the desktop pane
        desktopPane = new JDesktopPane();
        getContentPane().add(desktopPane);

        // Create a menu bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        // Add a "File" menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        // Add "New" item to the "File" menu
        JMenuItem newItem = new JMenuItem("New");
        fileMenu.add(newItem);

        // Add an action listener to create a new internal frame when "New" is clicked
        newItem.addActionListener(e->{
            createInternalFrame();
        });

        // Set the size of the main window
        setSize(800, 600);

        // Exit the application when the window is closed
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Method to create a new internal frame
    private void createInternalFrame() {
        // Create a new internal frame
        JInternalFrame internalFrame = new JInternalFrame(
                "Internal Frame", // title
                true,             // resizable
                true,             // closable
                true,             // maximizable
                true              // iconifiable
        );

        // Set size and add some content
        internalFrame.setSize(300, 200);
        internalFrame.getContentPane().add(new JLabel("This is an internal frame", JLabel.CENTER));



        // Add the internal frame to the desktop pane
        desktopPane.add(internalFrame);

        // Make the internal frame visible
        internalFrame.setVisible(true);
    }

    public static void main(String[] args) {
        // Create and display the main application window
        InternalFrameExample example = new InternalFrameExample();
        example.setVisible(true);
    }
}
