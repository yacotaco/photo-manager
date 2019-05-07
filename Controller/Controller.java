import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        view.getExitItem().addActionListener(new ExitActionListener());
    }

    public class ExitActionListener implements ActionListener {
      
      public ExitAction(){};

      @Override
      public void actionPerformed(ActionEvent event) {
          System.exit(0);
      }

    }

    

}

