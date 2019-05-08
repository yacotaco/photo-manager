import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
      Stream<String> stream;

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
}

