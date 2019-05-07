import javax.swing.JFrame;
import javax.swing.JMenu;
// import javax.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.awt.KeyEvent;

/**
 * View
 */
public class View {

    private Menu menu;
    private JMenuBar menuBar;
    private JMenuItem menuExitItme;
    
    public View() {
        this.menu = new Menu();
        this.menuExitItme = menu.getMenuExitItem();
        this.menuBar = menu.getMenuBar();
        new MainFrameView(this.menuBar);
    }

    public JMenuItem getExitItem() {
        return this.menuExitItme;
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
            frame.setSize(500, 420);
            frame.setLocationRelativeTo(null);
            frame.setJMenuBar(menuBar);
            frame.setVisible(true);
        }
    }

    public class Menu {

        private JMenuBar menuBar;
        private JMenu menu;
        private JMenuItem menuOpenItem;
        private JMenuItem menuSaveItem;
        private JMenuItem menuExitItem;

        /** 
        @param menuBar 
        @param menu
        @param menuOpenItem 
        @param menuSaveItem
        @param menuExitItem
        */

        public Menu() {
            menuBar = new JMenuBar();
            menu = new JMenu("File");
            menuOpenItem = new JMenuItem("Open");
            menu.add(menuOpenItem);
            menuSaveItem = new JMenuItem("Save");
            menu.add(menuSaveItem);
            menuExitItem = new JMenuItem("Exit");
            menu.add(menuExitItem);
            menuBar.add(menu);
        }

        public JMenuBar getMenuBar() {
            return this.menuBar;
        }

        public JMenuItem getMenuExitItem() {
            return this.menuExitItem;
        }
    }
}

