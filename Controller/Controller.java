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
      ArrayList<Image> imageList = new ArrayList<Image>();
  
      try (Stream<String> stream = Files.lines(Paths.get(path))) {
        List<String> list = stream.collect(Collectors.toList()); 
        
        for (String item : list) {
          if(item.length() > 0) {
            String[] itemSplit = item.split(";");
            String imagePath = itemSplit[0];
            String imageAuthor = itemSplit[1];
            String location = itemSplit[2];
            String date = itemSplit[3];
            String tags = itemSplit[4];
            imageList.add(new Image(imagePath, imageAuthor, location, date, tags));
            view.setLabels(imagePath);
          }
        }
       
      } catch (ArrayIndexOutOfBoundsException | IOException e) {
        // check format of database 
        JOptionPane.showMessageDialog(view.getFrame(), "Cant read database!");
      }
    }
}

