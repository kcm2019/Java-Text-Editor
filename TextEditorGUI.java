import javax.swing.*;
import java.awt.GraphicsEnvironment;
import java.awt.Font;
import java.awt.*;
import java.awt.event.*; 
import java.awt.Desktop;  
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class TextEditorGUI implements ActionListener
{    
    //Necessary J items for the GUI
    private JFrame f;    
    private JMenuBar mb;    
    private JMenu file, edit, help;    
    private JMenuItem cut, copy, paste, selectAll, about, save, openFile, exit;    
    private JTextArea ta;   
    private JScrollPane scroll;
    private FileDialog fileDialog;

    //Constructor
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
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.getAllFonts();
        Font font = new Font("Monospaced", Font.PLAIN, 13);
        ta.setFont(font);  
        f.add(mb); f.add(ta);    
        f.setJMenuBar(mb);  
        f.setLayout(null);    
        f.setSize(400,400);    
        f.setVisible(true);  
        
        // Create scroll pane to enable scroll on the TA
        scroll = new JScrollPane(ta);  
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        f.setContentPane(scroll);

        return f;
    }//end createTextEditor
    
    public JFrame getF() {
        return f;
    }//end getF

    public JTextArea getTa(){
        return ta;
    }//end getTa
    public FileDialog getfFileDialog(){
        return fileDialog;
    }//end getFileDialog

    //Can definitey make this into a switch statement to run lighter
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
            JOptionPane.showMessageDialog(null, "Made By Kurt Muller\nDate: January 1, 2021\nHave a great day!", "About", JOptionPane.INFORMATION_MESSAGE);
        }//end if
        if(e.getSource() == save) {
            //FileWriter fileOut = new FileWriter("C:\\Users\\qwert\\Documents\\test.txt");
            //JFrame temp = getF();
            //temp.getTextArea().write(fileOut);
            /*
            String fileName = JOptionPane.showInputDialog("Enter a name for this file");
            if(!(fileName == null)){
                File file = new File(fileName + ".txt");
                saveMethod(fileName, getTa(), file);
            }
            */
            JFrame parentFrame = new JFrame();
 
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Specify a file to save");   
 
            int userSelection = fileChooser.showSaveDialog(parentFrame);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                saveMethod(fileToSave.getName(), getTa(), fileToSave);
                System.out.println("Save as file: " + fileToSave.getAbsolutePath());
            }//end if
        }//end if
        if(e.getSource() == openFile){
            try{
                //File file = new File("bruh.txt");
                //File file = new File ("c:\"");
                //openFileMethod(file);
                //Creates new frame for fileChooser
                JFrame parentFrame = new JFrame();

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Specify a file to open");
                int userSelection = fileChooser.showOpenDialog(parentFrame);
                Scanner inputFile = new Scanner(fileChooser.getSelectedFile());
                String fileLines = "";
                
                //If the user chooses to select a file or not
                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    while (inputFile.hasNext()) {
                        String oneLine = inputFile.nextLine();
                        Scanner lineTokenizer = new Scanner (oneLine);
                        lineTokenizer.useDelimiter("\n");

                        if (lineTokenizer.hasNext() == false) {
                            fileLines += "\n";
                        }//end if 
                        else {
                            fileLines += lineTokenizer.next() + "\n";
                        }//end else

                        lineTokenizer.close();
                    }//end while
                    ta.setText(fileLines);
                }//end if
                else {
                    throw new IOException();
                }//end if 

                /*
                fileDialog = new FileDialog(f,"Select file");
                fileDialog.setVisible(true);
                Scanner inputFile = new Scanner(new File(fileDialog.getFile()));
                String fileLines = "";
                while(inputFile.hasNext()){
                    String oneLine = inputFile.nextLine();
                    Scanner lineTokenizer = new Scanner(oneLine);
                    lineTokenizer.useDelimiter("\n");
                    fileLines += lineTokenizer.next() + "\n";
                }
                //File file = new File(fileDialog.getFile());
                System.out.println(fileLines);
                //File.open(file);
                //String temp = fileDialog.getFile().toString();
                ta.setText(fileLines);
                */
            }//end try
            catch(IOException ex){
                System.out.println("Error opening file");
            }//end catch    
        }
        if(e.getSource() == exit){
            System.exit(0);
        }//end if

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
        }//end try
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error Saving File", "Save Error", JOptionPane.INFORMATION_MESSAGE);
        }//end catch
    }
    public void openFileMethod(File file){
        /*
        try{
            //Desktop.getDesktop().open(file);
            //File file = new File ("c:\"<directory>");
            //Desktop desktop = Desktop.getDesktop();
            //desktop.open(file);
        }
        catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error Saving File", "Save Error", JOptionPane.INFORMATION_MESSAGE);
        }
        
        try {
            Desktop.getDesktop().open(new File("C:/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(uri); // Throws
        }
        */

    }
}
