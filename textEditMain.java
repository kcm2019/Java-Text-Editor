import javax.swing.*;       
import javax.swing.JFrame;

public class textEditMain
{
    private static void createAndShowGUI() 
    {

        //JFrame.setDefaultLookAndFeelDecorated(true);
        //JFrame frame = new JFrame("Text Editor");

        //Create and set up the content pane.
        TextEditorGUI demo = new TextEditorGUI();
        JFrame frame = demo.createTextEditor();
        //frame.setContentPane(demo.createTextEditor());
        //frame.add(demo.createTextEditor());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(400, 360);
        frame.setVisible(true);
    }  
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.~      
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
            
        });
    }
}
