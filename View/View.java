import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.awt.KeyEvent;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.Box;
import javax.swing.BoxLayout;

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
    private JPanel pane;
    private JList list;
    private DefaultListModel listModel;
    private JPanel editDialog;
    private JTextField pathDialog;
    private JTextField authorDialog;
    private JTextField locationDialog;
    private JTextField dateDialog;
    private JTextField tagsDialog;
       
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

        // panel
        pane = new JPanel(new BorderLayout());
        // list and list model
        listModel = new DefaultListModel();
        list = new JList(listModel);

        // Edit dialog
        pathDialog = new JTextField(15);
        authorDialog = new JTextField(15);
        locationDialog = new JTextField(15);
        dateDialog = new JTextField(15);
        tagsDialog = new JTextField(15);

        editDialog = new JPanel();
        editDialog.setLayout(new BoxLayout(editDialog, BoxLayout.Y_AXIS));
        editDialog.add(new JLabel("Path:"));
        editDialog.add(pathDialog);
        editDialog.add(new JLabel("Author:"));
        editDialog.add(authorDialog);
        editDialog.add(new JLabel("Location:"));
        editDialog.add(locationDialog);
        editDialog.add(new JLabel("Date:"));
        editDialog.add(dateDialog);
        editDialog.add(new JLabel("Tags:"));
        editDialog.add(tagsDialog);

        pane.add(list, BorderLayout.CENTER);

         // scrole pane
        listScrollPane = new JScrollPane(pane);
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

    public void setListLabels(String element){
        this.listModel.addElement(element);
    }

    public DefaultListModel getListModel() {
        return this.listModel;
    }
    
    public JList getList(){
        return this.list;
    }

    public JPanel getEditDialog(){
        return this.editDialog;
    }

    public String getPathDialog() {
        return this.pathDialog.getText();
    }

    public String getAuthorDialog() {
        return this.authorDialog.getText();
    }

    public String getLocationDialog() {
        return this.locationDialog.getText();
    }

    public String getDateDialog() {
        return this.dateDialog.getText();
    }

    public String getTagsDialog() {
        return this.tagsDialog.getText();
    }
}

