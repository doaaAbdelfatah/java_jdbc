import javax.swing.*;

public class MainFrame extends JFrame {

    private  JDesktopPane desktopPane;
    public MainFrame(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100,100,1200,700);
        desktopPane= new JDesktopPane();
        this.getContentPane().add(desktopPane);

        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        menuBar.add(file);
        JMenuItem newItem = new JMenuItem("New");
        file.add(newItem);
        setJMenuBar(menuBar);


        newItem.addActionListener(e->{
            JInternalFrame internalFrame = new JInternalFrame("My Internal Frame" ,true,true,
                    true,true
                   );
            internalFrame.setSize(400,300);

            desktopPane.add(internalFrame);
            internalFrame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }

}
