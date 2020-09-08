package trollify;

import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class FrameMenuBar extends JPanel
{   
    private JMenuBar jmbBar;
    private JFrame frame;
    
    private JMenu fileMenu;
    private JMenu editMenu;
    private JMenu helpMenu;
    private JMenu shareMenu;
    private JMenu exitMenu;
    
    private JMenuItem jmiNew;
    private JMenuItem jmiOpen;
    private JMenuItem jmiSave;
    private JMenuItem jmiSaveAs;
    private JMenuItem jmiUndo;
    private JMenuItem jmiRedo;
    private JMenuItem jmiClear;
    private JMenuItem jmiShareOnFB;
    private JMenuItem jmiShareOnTwitter;
    private JMenuItem jmiHelp;
    private JMenuItem jmiAbout;
    private JMenuItem jmiExit;
    
    File file = null;
    
    FrameMenuBar(JFrame f)
    {   //specify frame
        frame = f;
        
        //create menu bar
        jmbBar = new JMenuBar();
        
        //associate menu bar with frame
        frame.setJMenuBar(jmbBar);
        
        //create menu
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");
        shareMenu = new JMenu("Share");
        helpMenu = new JMenu("Help");
        exitMenu = new JMenu("Exit");
        
        //associate menu with menubar
        jmbBar.add(fileMenu);
        jmbBar.add(editMenu);
        jmbBar.add(shareMenu);
        jmbBar.add(helpMenu);
        jmbBar.add(exitMenu);
        
        //create menu items
        jmiNew = new JMenuItem("New",new ImageIcon("resources/new-file-icon.png"));
        jmiOpen = new JMenuItem("Open",new ImageIcon("resources/open-file-icon.png"));
        jmiSave = new JMenuItem("Save",new ImageIcon("resources/save-file-icon.png"));
        jmiSaveAs = new JMenuItem("Save As",new ImageIcon("resources/save-as-icon.png"));
        jmiUndo = new JMenuItem("Undo /*Upcoming Feature*/",new ImageIcon("resources/undo-icon.png"));
        jmiRedo = new JMenuItem("Redo /*Upcoming Feature*/",new ImageIcon("resources/redo-icon.png")); 
        jmiClear = new JMenuItem("Clear", new ImageIcon("resources/clear-icon.png"));
        jmiShareOnFB = new JMenuItem("Share on Facebook /*Upcoming Feature*/",new ImageIcon("resources/facebook-icon.png"));
        jmiShareOnTwitter = new JMenuItem("Share on Twitter /*Upcoming Feature*/",new ImageIcon("resources/twitter-icon.png"));
        jmiHelp = new JMenuItem("How to use /*Upcoming Feature*/",new ImageIcon("resources/help-icon.png"));
        jmiAbout = new JMenuItem("About",new ImageIcon("resources/about-icon.png"));
        jmiExit = new JMenuItem("Exit");

        
        //associate menu items with menu
        fileMenu.add(jmiNew);
        fileMenu.add(jmiOpen);
        fileMenu.add(jmiSave);
        fileMenu.add(jmiSaveAs);
        editMenu.add(jmiUndo);
        editMenu.add(jmiRedo);
        editMenu.add(jmiClear);
        shareMenu.add(jmiShareOnFB);
        shareMenu.add(jmiShareOnTwitter);
        helpMenu.add(jmiHelp);
        helpMenu.add(jmiAbout);
        exitMenu.add(jmiExit);
            
        //register menu items with action listeners
        
        jmiNew.addActionListener(new FrameMenuBarListener());
        jmiOpen.addActionListener(new FrameMenuBarListener());
        jmiSave.addActionListener(new FrameMenuBarListener());
        jmiSaveAs.addActionListener(new FrameMenuBarListener());
        jmiUndo.addActionListener(new FrameMenuBarListener());
        jmiRedo.addActionListener(new FrameMenuBarListener());
        jmiClear.addActionListener(new FrameMenuBarListener());
        jmiShareOnFB.addActionListener(new FrameMenuBarListener());
        jmiShareOnTwitter.addActionListener(new FrameMenuBarListener());
        jmiHelp.addActionListener(new FrameMenuBarListener());
        jmiAbout.addActionListener(new FrameMenuBarListener());
        jmiExit.addActionListener(new FrameMenuBarListener());
        
    }
    

    //set File name
    public void setFileName(File f)
    {
        file = f;
    }
    
    //get File name
    public File getFileName()
    {
        return file;
    }
            
    public class FrameMenuBarListener implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e)
        {   MainCanvas mc = new MainCanvas();
            
            //New File (jmiNew)
            if(jmiNew == e.getSource())
            {               
                mc.newCanvas(frame);
                //clear contents of canvas only if Ok button is clicked by user otherwise continue without clearing canvas
                if(NewCanvasDialogBox.btnOKClicked)
                {  
                    mc.clearData();
                    frame.revalidate();
                    frame.repaint();
                }        
    
            }
            
            //Open File (jmiOpen)
            if(e.getSource() == jmiOpen)
            {
                    mc.openFile(".");
                    frame.revalidate();
                    frame.repaint();
            }
            //About (jmiAbout)
            if(jmiAbout == e.getSource())
            {
                About ab = new About();  
              
            }
            

            //SaveAs (jmiSaveAs)
            if(jmiSaveAs == e.getSource())
            {
                //create bufferedImage
                BufferedImage img = new BufferedImage(mc.getDimensions().width, mc.getDimensions().height, BufferedImage.TYPE_INT_RGB);
                //g2 makes buffered image editable
                Graphics2D g2 = (Graphics2D)img.createGraphics();
                
                /*
                    if coded directly i.e. mc.paintComponent(g2) than image gets saved but with black background.
                    Found solution at below link
                   http://stackoverflow.com/questions/16565310/java-bufferedimage-returns-black-image-from-canvas
                */
                
                //paint buffered image with other graphical components drawn by user
                SwingUtilities.paintComponent(g2, mc, frame, 0, 0,mc.getDimensions().width, mc.getDimensions().height);

                //write file to disk 
                JFileChooser saveFile = new JFileChooser(new File("."));
                if(saveFile.showSaveDialog(null) == JFileChooser.APPROVE_OPTION)
                {
                    file = saveFile.getSelectedFile();
                    String fileNameWithExtension = (file.toString().concat(".png"));
                    file = new File(fileNameWithExtension);
                    
                    setFileName(file);
                    
                    if(file != null && !file.toString().equals(""))
                    {
                        try
                        {  
                            ImageIO.write(img, "png",file);
                        }    
                        catch(IOException ex)
                        {
                            JOptionPane.showMessageDialog(null,"Failed to save file", "IO Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"File Name not specified or Bad File Name", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    g2.dispose();
                            
                }

            }
            
            //Save File 
            if(e.getSource() == jmiSave)
            {
                //create bufferedImage
                BufferedImage img = new BufferedImage(mc.getDimensions().width, mc.getDimensions().height, BufferedImage.TYPE_INT_RGB);
                //g2 makes buffered image editable
                Graphics2D g2 = (Graphics2D)img.createGraphics();

                SwingUtilities.paintComponent(g2, mc, frame, 0, 0,mc.getDimensions().width, mc.getDimensions().height);
                
                if(file == null)
                {
                    JOptionPane.showMessageDialog(null,"Please specify filename before saving \nTip: Use Save As option.", "IO Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try
                {  
                    ImageIO.write(img, "png",file);
                }    
                catch(IOException ex)
                {
                    JOptionPane.showMessageDialog(null,"Failed to save file", "IO Error", JOptionPane.ERROR_MESSAGE);
                }
                System.out.println("File in save: " + getFileName());
                g2.dispose();
            }
            //Clear
            if(jmiClear == e.getSource())
            {
                mc.clearData();
                frame.revalidate();
                frame.repaint();
            }
            
            //Exit (jmiExit)
            if(jmiExit == e.getSource())
            {   
                //to confirm if user really wants to exit
                int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?", "Confirmation", JOptionPane.YES_NO_OPTION);
                
                switch(result)
                {   
                    case JOptionPane.YES_OPTION:
                        System.exit(0);
                        break;
                     
                    case JOptionPane.NO_OPTION:
                        break;
                        
                    default:
                        
                }

            }

        }
         
    }
}
