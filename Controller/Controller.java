import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFileChooser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import java.util.Collections;

/**
 * Read
 */
public class Controller {

    private View view;
    private ArrayList<Image> imageList = new ArrayList<Image>();

    Controller(View view) {
        this.view =  view;
        initControler();
    }

    public void initControler(){
        view.getMenuExitItem().addActionListener(new ExitActionListener());
        view.getMenuOpenItem().addActionListener(new OpenActionListener());
        view.getList().addMouseListener(new ListListener());
        view.getListPopupMenuAdd().addActionListener(new AddActionListener());
        view.getListPopupMenuDelete().addActionListener(new DeleteActionListener());
        view.getListPopupMenuSearch().addActionListener(new SearchActionListener());
        view.getSubMenuSortAscAuthor().addActionListener(new SortAscAuthorListener());
        view.getSubMenuSortAscDate().addActionListener(new SortAscDateListener());
        view.getSubMenuSortAscLocation().addActionListener(new SortAscLocationListener());
        view.getSubMenuSortDescAuthor().addActionListener(new SortDescAuthorListener());
        view.getSubMenuSortDescDate().addActionListener(new SortDescDateListener());
        view.getSubMenuSortDescLocation().addActionListener(new SortDescLocationListener());
    }

    
    public class ExitActionListener implements ActionListener {
      
      public ExitAction(){};

      @Override
      public void actionPerformed(ActionEvent event) {
          System.exit(0);
      }

    }

    public class OpenActionListener implements ActionListener {
      final JFileChooser fc = new JFileChooser();
      String path;
      
      public OpenAction() {};

      @Override
      public void actionPerformed(ActionEvent event) {
        
        int returnVal = fc.showOpenDialog(view.getFrame());

        if (returnVal == JFileChooser.APPROVE_OPTION) {
            path = fc.getSelectedFile().getPath();
            readDatabaseFile(path); 
        }
      
      }  
    }

    public void readDatabaseFile(String path) {
      imageList.clear();
      view.getListModel().removeAllElements();
      String imagePath;
      String imageName;
      String imageAuthor;
      String location;
      String date;
      String tags;

      try (Stream<String> stream = Files.lines(Paths.get(path))) {
        List<String> list = stream.collect(Collectors.toList()); 
        
        for (String item : list) {
          if(item.length() > 0) {
            String[] itemSplit = item.split(";");
            if(itemSplit.length > 1) {
              imagePath = itemSplit[0];
              imageName = getImageNameFromPath(imagePath);
              imageAuthor = itemSplit[1];
              location = itemSplit[2];
              date = itemSplit[3];
              tags = itemSplit[4];
              imageList.add(new Image(imageName, imagePath, imageAuthor, location, date, tags));
              view.setListLabels(imageName);
            } else if(itemSplit.length == 1) {
              imagePath = itemSplit[0];
              imageName = getImageNameFromPath(imagePath);
              imageList.add(new Image(imageName, imagePath, "", "", "", ""));
              view.setListLabels(imageName);
            }
          
          }
        }
      
      } catch (ArrayIndexOutOfBoundsException | IOException e) {
        // check format of database 
        e.printStackTrace();
        JOptionPane.showMessageDialog(view.getFrame(), "Cant read database!");
      }
    }

    public String getImageNameFromPath(String imagePath) {
      String temp[] = imagePath.split("/");
      String imageName = temp[temp.length-1];
      return imageName;
    }

    public class ListListener implements MouseListener {

        @Override
        public void mouseReleased(MouseEvent arg0) {
          
        }
      
        @Override
        public void mousePressed(MouseEvent event) {
          if (SwingUtilities.isRightMouseButton(event)) {
            view.getListPopupMenu().show(event.getComponent(), event.getX(), event.getY());
          }
        }
      
        @Override
        public void mouseExited(MouseEvent arg0) {
          
        }
      
        @Override
        public void mouseEntered(MouseEvent arg0) {
          
        }
      
        @Override
        public void mouseClicked(MouseEvent event) {
          if(SwingUtilities.isLeftMouseButton(event) && event.getClickCount() == 2) {
            int index = view.getList().locationToIndex(event.getPoint());
            if(index != -1) {
              view.setPathDialog(imageList.get(index).getPath());
              view.setAuthorDialog(imageList.get(index).getAuthor());
              view.setLocationDialog(imageList.get(index).getLocation());
              view.setDateDialog(imageList.get(index).getDate());
              view.setTagsDialog(imageList.get(index).getTag());
        
              int result = JOptionPane.showConfirmDialog(null, view.getEditDialog(),
              "Edit", JOptionPane.OK_CANCEL_OPTION);
            
              if(result == JOptionPane.OK_OPTION) {
                // update list view
                String imageName = getImageNameFromPath(view.getPathDialog());
                view.getListModel().set(index, imageName);
                getImageNameFromPath(view.getPathDialog());
                
                imageList.get(index).setPath(view.getPathDialog());
                imageList.get(index).setAuthor(view.getAuthorDialog());
                imageList.get(index).setLocation(view.getLocationDialog());
                imageList.get(index).setDate(view.getDateDialog());
                imageList.get(index).setTag(view.getTagsDialog());
              }

            }
            
          }
        }
      }

      public class AddActionListener implements ActionListener {
        
        public AddActionListener(){};

        public void setEmptyTextFileds() {
          view.setAddPathDialog("");
          view.setAddAuthorDialog("");
          view.setAddLocationDialog("");
          view.setAddDateDialog("");
          view.setAddTagsDialog("");
        }
        
        @Override
        public void actionPerformed(ActionEvent event) {
          setEmptyTextFileds();
          int result = JOptionPane.showConfirmDialog(null, view.getAddDialog(), "Add", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
          // fix adding tags 
          // add fields validation
          try {
            if(result == JOptionPane.OK_OPTION) {
              String imagePath = view.getAddPathDialog();
              String imageName = getImageNameFromPath(imagePath);
              String imageAuthor = view.getAddAuthorDialog();
              String location = view.getAddLocationDialog();
              String date = view.getAddDateDialog();
              String tags = view.getAddTagsDialog();
              if(view.getListModel().contains(imageName) == false) {
                imageList.add(new Image(imageName, imagePath, imageAuthor, location, date, tags));
                view.setListLabels(imageName);
              } else {
                JOptionPane.showMessageDialog(null, "Image is on list!");
              }
            }
          } catch (IllegalArgumentException e) {
              JOptionPane.showMessageDialog(null, "Path cannot be empty!");
          }
          
        }
      }

      public class DeleteActionListener implements ActionListener {
        
        public DeleteActionListener(){};

        @Override
        public void actionPerformed(ActionEvent event) {
          int index = view.getList().getSelectedIndex();
          if(index > -1) {
            imageList.remove(index);
            view.getListModel().remove(index);
          } else if (index == -1 && imageList.size() > 0) {
            JOptionPane.showMessageDialog(null, "Select image!");
          } else {
            JOptionPane.showMessageDialog(null, "List is empty!");
          }
        }
      }

      public class SearchActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
          JOptionPane.showConfirmDialog(null, view.getSearchDialog(), "Search", JOptionPane.OK_CANCEL_OPTION);
        }
      }

      public void updateListLabels() {
        view.getListModel().removeAllElements();
        for (Image item : imageList) {
          view.getListModel().addElement(item.getImageName());
        }
      }


       public class SortAscAuthorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
          new ImageSorter().getSortedAscByAuthor();
          updateListLabels();
        }
      }

      public class SortAscDateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
          new ImageSorter().getSortedAscByDate();
          updateListLabels();
        }
      }

      public class SortAscLocationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
          new ImageSorter().getSortedAscByLocation();
          updateListLabels();
        }
      }

      public class SortDescAuthorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
          new ImageSorter().getSortedDescByAuthor();
          updateListLabels();
        }
      }

      public class SortDescDateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
          new ImageSorter().getSortedDescByDate();
          updateListLabels();
        }
      }

      public class SortDescLocationListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent event) {
          new ImageSorter().getSortedDescByLocation();
          updateListLabels();
        }
      }

      public class ImageSorter {

          public ArrayList<Image> getSortedAscByAuthor() {
            Collections.sort(imageList, Image.authorComparator);         

            return imageList;     
        
          }
          
          public ArrayList<Image> getSortedAscByDate() {
            Collections.sort(imageList, Image.dateComparator);         

            return imageList;     
        
          }

          public ArrayList<Image> getSortedAscByLocation() {
            Collections.sort(imageList, Image.locationComparator);         

            return imageList;     
        
          }
          
          public ArrayList<Image> getSortedDescByAuthor() {
            Collections.sort(imageList, Image.authorComparator);         
            Collections.reverse(imageList);

            return imageList;     
        
          }

          public ArrayList<Image> getSortedDescByDate() {
            Collections.sort(imageList, Image.dateComparator);         
            Collections.reverse(imageList);

            return imageList;     
        
          }

          public ArrayList<Image> getSortedDescByLocation() {
            Collections.sort(imageList, Image.locationComparator);         
            Collections.reverse(imageList);

            return imageList;     
        
          }
          
      }
}

