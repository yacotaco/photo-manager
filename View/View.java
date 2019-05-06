import javax.swing.JFrame;
import javax.swing.JMenu;
// import javax.awt.BorderLayout;
import javax.swing.JMenuBar;

/**
 * View
 */
public class View {
    
    public View() {
        JMenuBar menuBar = new Menu().menuBar;
        new MainFrameView(menuBar);
    }

    public class MainFrameView {

        private JFrame frame;

        /** 
        @param frame holds all componanets of UI.
        */

        public MainFrameView(JMenuBar menuBar) {
            frame = new JFrame("PhotoManager");
            //frame.getContentPane().setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 120);
            frame.setLocationRelativeTo(null);
            frame.setJMenuBar(menuBar);
            frame.setVisible(true);
        }
    }

    public class Menu {

        private JMenuBar menuBar;
        private JMenu menu;

        public Menu() {
            menuBar = new JMenuBar();
            menu = new JMenu("File");
            menuBar.add(menu);
        }
    }
}
    
