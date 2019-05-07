import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
// import javax.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import java.awt.Color;

import javax.awt.KeyEvent;
import java.awt.Dimension;

/**
 * View
 */
public class View {

    private JFrame frame;
    private JMenu menu;
    private JMenuBar menuBar;
    private JMenuItem menuOpenItem;
    private JMenuItem menuExitItem;
    private JMenuItem menuSaveItem;
    private JSplitPane splitPane;
    private JScrollPane listScrollPane;
    private JScrollPane pictureScrollPane;
    private Dimension minimumSize;
       
    /** 
    @param frame holds all componanets of UI.
    */
    
    public View() {
        // Add frame parameters
        frame = new JFrame("PhotoManager");
        // frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 420);
        frame.setLocationRelativeTo(null);
        
        // Add menu bar with items
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        menuOpenItem = new JMenuItem("Open");
        menu.add(menuOpenItem);
        menuSaveItem = new JMenuItem("Save");
        menu.add(menuSaveItem);
        menuExitItem = new JMenuItem("Exit");
        menu.add(menuExitItem);
        menuBar.add(menu);

        // scrole pane
        listScrollPane = new JScrollPane();
        pictureScrollPane = new JScrollPane();
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                           listScrollPane, pictureScrollPane);

        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);

        //Provide minimum sizes for the two components in the split pane
        minimumSize = new Dimension(100, 50);
        listScrollPane.setMinimumSize(minimumSize);
        pictureScrollPane.setMinimumSize(minimumSize);
        
        // split pane
        frame.add(splitPane); 

        // menu bar 
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return this.frame;
    }
       
    public JMenuBar getMenuBar() {
        return this.menuBar;
    }

    public JMenuItem getMenuOpenItem() {
        return this.menuOpenItem;
    }

    public JMenuItem getMenuExitItem() {
        return this.menuExitItem;
    }

}

