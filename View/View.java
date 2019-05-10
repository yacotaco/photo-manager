import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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
import javax.swing.JPopupMenu;
import javax.swing.JComboBox;

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
    private JPopupMenu listPopupMenu;
    private JMenuItem listPopupMenuAdd;
    private JMenuItem listPopupMenuDelete;
    private JMenuItem listPopupMenuSearch;
    private JMenu listPopupMenuSortAsc;
    private JMenuItem subMenuSortAscAuthor;
    private JMenuItem subMenuSortAscLocation;
    private JMenuItem subMenuSortAscDate;
    private JMenu listPopupMenuSortDesc;
    private JMenuItem subMenuSortDescAuthor;
    private JMenuItem subMenuSortDescLocation;
    private JMenuItem subMenuSortDescDate;
    private JPanel addDialog;
    private JPanel searchDialog;
    private JComboBox searchComboListValueType;
    private JComboBox searchComboListCompareCondition;
    private String[] selectSearchTypeValue = {"Author", "Location", "Date"};
    private String[] selectSearchConditionValue = {"equal to", "greater than", "less than" };
    private JTextField searchValueDialog;
    private JTextField addPathDialog;
    private JTextField addAuthorDialog;
    private JTextField addLocationDialog;
    private JTextField addDateDialog;
    private JTextField addTagsDialog;
    
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

        // drop down menu for list 
        listPopupMenu = new JPopupMenu();
        listPopupMenuAdd = new JMenuItem("Add");
        listPopupMenuDelete = new JMenuItem("Delete");
        listPopupMenuSearch = new JMenuItem("Search");
        listPopupMenuSortAsc = new JMenu("Sort ascending by");
        listPopupMenuSortDesc = new JMenu("Sort descending by");

        // sort ascending submenu
        subMenuSortAscAuthor = new JMenuItem("Author");
        subMenuSortAscLocation = new JMenuItem("Location");
        subMenuSortAscDate = new JMenuItem("Date");

        listPopupMenuSortAsc.add(subMenuSortAscAuthor);
        listPopupMenuSortAsc.add(subMenuSortAscLocation);
        listPopupMenuSortAsc.add(subMenuSortAscDate);

        // sort descending submenu
        subMenuSortDescAuthor = new JMenuItem("Author");
        subMenuSortDescLocation = new JMenuItem("Location");
        subMenuSortDescDate = new JMenuItem("Date");

        listPopupMenuSortDesc.add(subMenuSortDescAuthor);
        listPopupMenuSortDesc.add(subMenuSortDescLocation);
        listPopupMenuSortDesc.add(subMenuSortDescDate);

        // combine submenu
        listPopupMenu.add(listPopupMenuAdd);
        listPopupMenu.add(listPopupMenuDelete);
        listPopupMenu.add(listPopupMenuSearch);
        listPopupMenu.add(listPopupMenuSortAsc);
        listPopupMenu.add(listPopupMenuSortDesc);

        // panel
        pane = new JPanel(new BorderLayout());
        // list and list model
        listModel = new DefaultListModel();
        list = new JList(listModel);
        pane.add(list, BorderLayout.CENTER);

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

        // Add dialog
        addPathDialog = new JTextField(15);
        addAuthorDialog = new JTextField(15);
        addLocationDialog = new JTextField(15);
        addDateDialog = new JTextField(15);
        addTagsDialog = new JTextField(15);

        addDialog = new JPanel();
        addDialog.setLayout(new BoxLayout(addDialog, BoxLayout.Y_AXIS));
        addDialog.add(new JLabel("Path:"));
        addDialog.add(addPathDialog);
        addDialog.add(new JLabel("Author:"));
        addDialog.add(addAuthorDialog);
        addDialog.add(new JLabel("Location:"));
        addDialog.add(addLocationDialog);
        addDialog.add(new JLabel("Date:"));
        addDialog.add(addDateDialog);
        addDialog.add(new JLabel("Tags:"));
        addDialog.add(addTagsDialog);

        // search
        searchComboListValueType = new JComboBox(selectSearchTypeValue);
        searchComboListCompareCondition = new JComboBox(selectSearchConditionValue);
        searchValueDialog = new JTextField(15);

        searchDialog = new JPanel();
        searchDialog.setLayout(new BoxLayout(searchDialog, BoxLayout.Y_AXIS));
        searchDialog.add(searchComboListValueType);
        searchDialog.add(Box.createVerticalStrut(10));
        searchDialog.add(searchComboListCompareCondition);
        searchDialog.add(Box.createVerticalStrut(10));
        searchDialog.add(searchValueDialog);

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

    public void setPathDialog(String pathDialog) {
        this.pathDialog.setText(pathDialog);
    }

    public void setAuthorDialog(String authorDialog) {
        this.authorDialog.setText(authorDialog);
    }

    public void setLocationDialog(String locationDialog) {
        this.locationDialog.setText(locationDialog);
    }

    public void setDateDialog(String dateDialog) {
        this.dateDialog.setText(dateDialog);
    }

    public void setTagsDialog(String tagsDialog) {
        this.tagsDialog.setText(tagsDialog);
    }

    public JPopupMenu getListPopupMenu() {
        return this.listPopupMenu;
    }

    public JMenuItem getListPopupMenuAdd() {
        return this.listPopupMenuAdd;
    }

    public JMenuItem getListPopupMenuDelete() {
        return this.listPopupMenuDelete;
    }

    public JMenuItem getListPopupMenuSearch() {
        return this.listPopupMenuSearch;
    }

    public JPanel getAddDialog() {
        return this.addDialog;
    }

    public JPanel getSearchDialog() {
        return this.searchDialog;
    }

    public void setAddPathDialog(String imagePath) {
        this.addPathDialog.setText(imagePath);
    }

    public void setAddAuthorDialog(String imageAuthor) {
        this.addAuthorDialog.setText(imageAuthor);
    }

    public void setAddLocationDialog(String location) {
        this.addLocationDialog.setText(location);
    }

    public void setAddDateDialog(String date) {
        this.addDateDialog.setText(date);
    }

    public void setAddTagsDialog(String tags) {
        this.addTagsDialog.setText(tags);
    }

    public String getAddPathDialog() {
        return this.addPathDialog.getText();
    }

    public String getAddAuthorDialog() {
        return this.addAuthorDialog.getText();
    }

    public String getAddLocationDialog() {
        return this.addLocationDialog.getText();
    }

    public String getAddDateDialog() {
        return this.addDateDialog.getText();
    }

    public String getAddTagsDialog() {
        return this.addTagsDialog.getText();
    }

    public JMenuItem getSubMenuSortAscAuthor() {
        return this.subMenuSortAscAuthor;
    }

    public JMenuItem getSubMenuSortAscDate() {
        return this.subMenuSortAscDate;
    }

    public JMenuItem getSubMenuSortAscLocation() {
        return this.subMenuSortAscLocation;
    }

    public JMenuItem getSubMenuSortDescAuthor() {
        return this.subMenuSortDescAuthor;
    }

    public JMenuItem getSubMenuSortDescDate() {
        return this.subMenuSortDescDate;
    }

    public JMenuItem getSubMenuSortDescLocation() {
        return this.subMenuSortDescLocation;
    }
}

