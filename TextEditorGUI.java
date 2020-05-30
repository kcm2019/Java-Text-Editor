import javax.swing.*;    
import java.awt.event.*;    
public class TextEditorGUI implements ActionListener
{    
    JFrame f;    
    JMenuBar mb;    
    JMenu file, edit, help;    
    JMenuItem cut, copy, paste, selectAll, about;    
    JTextArea ta;   
    JScrollPane scroll;
    
    public TextEditorGUI()
    {
        
    }

    // Creates the GUI for the text editor
    public JFrame createTextEditor()
    {    
        // Make new JFrame
        f=new JFrame();  
        
        // Create JMenuItems and action listeners
        cut = new JMenuItem("Cut");    
        copy = new JMenuItem("Copy");    
        paste = new JMenuItem("Paste");    
        selectAll = new JMenuItem("Select All");
        about = new JMenuItem("About"); 
        cut.addActionListener(this);    
        copy.addActionListener(this);    
        paste.addActionListener(this);    
        selectAll.addActionListener(this);    
        about.addActionListener(this);

        // Create JMenuBar and its Menus. Add the JMenuItems to each relevant JMenu
        mb = new JMenuBar();    
        file = new JMenu("File");    
        edit = new JMenu("Edit");    
        help = new JMenu("Help");     
        edit.add(cut); edit.add(copy); edit.add(paste); edit.add(selectAll);   
        help.add(about);
        mb.add(file);mb.add(edit);mb.add(help);  
        
        // Create text area
        ta = new JTextArea();    
        ta.setBounds(5,5,400,360);    
        f.add(mb);f.add(ta);    
        f.setJMenuBar(mb);  
        f.setLayout(null);    
        f.setSize(400,400);    
        f.setVisible(true);  
        
        // Create scroll pane

        return f;
    }   

    public void actionPerformed(ActionEvent e) 
    {    
        if(e.getSource() == cut)    
            ta.cut();   

        if(e.getSource() == paste)    
            ta.paste();

        if(e.getSource() == copy)    
            ta.copy(); 

        if(e.getSource() == selectAll)    
            ta.selectAll();   
        
        if(e.getSource() == about ) {
            System.out.println("Pembis");
        }
    }  
}
