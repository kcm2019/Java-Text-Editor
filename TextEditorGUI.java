import javax.swing.*;    
import java.awt.event.*;   
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class TextEditorGUI implements ActionListener
{    
    private JFrame f;    
    private JMenuBar mb;    
    private JMenu file, edit, help;    
    private JMenuItem cut, copy, paste, selectAll, about, save, openFile, exit;    
    private JTextArea ta;   
    private JScrollPane scroll;
    
    public TextEditorGUI()
    {
        
    }

    // Creates the GUI for the text editor
    public JFrame createTextEditor()
    {    
        // Make new JFrame
        f=new JFrame();  
        f.setLocationByPlatform(true);;
        
        // Create JMenuItems and action listeners
        // For File JMenu
        save = new JMenuItem("Save");
        openFile = new JMenuItem("Open File...");
        exit = new JMenuItem("Exit");

        save.addActionListener(this);
        openFile.addActionListener(this);
        exit.addActionListener(this);

        // For Edit JMenu
        cut = new JMenuItem("Cut");    
        copy = new JMenuItem("Copy");    
        paste = new JMenuItem("Paste");    
        selectAll = new JMenuItem("Select All");
        
        cut.addActionListener(this);    
        copy.addActionListener(this);    
        paste.addActionListener(this);    
        selectAll.addActionListener(this);   
        
        // For Help JMenu
        about = new JMenuItem("About"); 

        about.addActionListener(this);

        // Create JMenuBar and its Menus. Add the JMenuItems to each relevant JMenu
        mb = new JMenuBar();    
        file = new JMenu("File");    
        edit = new JMenu("Edit");    
        help = new JMenu("Help"); 
        
        file.add(save); file.add(openFile); file.add(exit);
        edit.add(cut); edit.add(copy); edit.add(paste); edit.add(selectAll);   
        help.add(about);

        mb.add(file); mb.add(edit); mb.add(help);  
        
        // Create text area
        ta = new JTextArea();    
        ta.setBounds(5,5,400,360);    
        f.add(mb); f.add(ta);    
        f.setJMenuBar(mb);  
        f.setLayout(null);    
        f.setSize(400,400);    
        f.setVisible(true);  
        
        // Create scroll pane
        scroll = new JScrollPane(ta);  
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        f.setContentPane(scroll);

        return f;
    } 
    
    public JFrame getF() {
        return f;
    }

    public JTextArea getTa(){
        return ta;
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
        if(e.getSource() == about) {
            JOptionPane.showMessageDialog(null, "Made By Kurt Muller\nDate: May 30, 2020\nBruh Moment", "About", JOptionPane.INFORMATION_MESSAGE);
        }
        if(e.getSource() == save) {
            //FileWriter fileOut = new FileWriter("C:\\Users\\qwert\\Documents\\test.txt");
            //JFrame temp = getF();
            //temp.getTextArea().write(fileOut);
            String fileName = JOptionPane.showInputDialog("Enter a name for this file");
            if(!(fileName == null)){
                File file = new File(fileName + ".txt");
                saveMethod(fileName, getTa(), file);
            }
        }
        if(e.getSource() == exit){
            System.exit(0);
        }
    } // end actionPerformed

    /*
    public void saveMethod(File fileName, TextArea textArea) {
        if (file != null) {
            String filePath = file.getAbsolutePath();
            try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                System.out.println("savefile exception");
            }
        }
    }
    */
    // Method for saving the JTextArea to a file
    public void saveMethod(String fileName, JTextArea textArea, File file) {

        try {
            PrintWriter outputPrintWriter = new PrintWriter(new FileWriter(file));
            //for (String line : textArea.getText().split("\\n")) 
            //    doStuffWithLine(line);
            String textAreaString = textArea.getText(); //Instead of .toString()
            System.out.print("TA STRING:" + textAreaString);
            outputPrintWriter.write(textAreaString);
            outputPrintWriter.close();
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error Saving File", "Save Error", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
