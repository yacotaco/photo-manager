import javax.swing.JFrame;
// import javax.awt.BorderLayout;

/**
 * View
 */
public class View {
    
    public View() {
        new MainFrameView();
    }

    public class MainFrameView {

        private JFrame frame;

        /** 
        @param frame holds all componanets of UI.
        */

        public MainFrameView() {
            frame = new JFrame("PhotoManager");
            //frame.getContentPane().setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 120);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }

}
    

 