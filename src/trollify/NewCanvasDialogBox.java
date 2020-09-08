package trollify;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class NewCanvasDialogBox extends JDialog
{
    private static int width;
    private static int height;
    
    private JTextField jtfWidth;
    private JTextField jtfHeight;
    
    private JButton btnOK;
    private JButton btnCancel;
   
    public static boolean btnOKClicked = true;  //to know if ok button has been clicked by user
    
    public NewCanvasDialogBox()
    {
        this(null,true);
    }
    
    public NewCanvasDialogBox(Frame f, boolean modal)
    {   
        super(f,modal);
        
        //text fields to get height and width of canvas  from users
        jtfWidth = new JTextField(5);
        jtfHeight = new JTextField(5);
        
        //buttons
        btnOK = new JButton("OK");
        btnCancel = new JButton("Cancel");
        
        //set Title of dialog box       
        this.setTitle("New File");
        this.setLocation(400, 200 );
        
        //Group Buttons
        JPanel jpButtons = new JPanel();
        jpButtons.add(btnOK);
        jpButtons.add(btnCancel);
        
        //Group width panel and text field
        JPanel jpWidth = new JPanel();
        jpWidth.add(new JLabel("Width (in pixels) : "));
        jpWidth.add(jtfWidth);
        
        //Group height panel and text field
        JPanel jpHeight = new JPanel();
        jpHeight.add(new JLabel("Height (in pixels) : "));
        jpHeight.add(jtfHeight);
        
        //dialogbox message
        JPanel jpMessage = new JPanel();
        jpMessage.add(new JLabel("Enter Canvas Size in pixels. (Default : 600px X 600px)"));
        
        //group width and height panels
        JPanel jpInbox = new JPanel();
        jpInbox.add(jpWidth);
        jpInbox.add(jpHeight);
        
        //group jpInbox and jpButtons
        JPanel jpInput = new JPanel();
        jpInput.add(jpInbox, BorderLayout.CENTER);
        jpInput.add(jpButtons, BorderLayout.SOUTH);
        
        //add jpMessage and jpInput in dialogbox
        this.add(jpMessage, BorderLayout.NORTH);
        this.add(jpInput, BorderLayout.CENTER);
        
        //register ok button with action listener
        btnOK.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {   
                btnOKClicked = true;
               
                //to make sure user enters only integer value in textfield
                try
                {  width = Integer.parseInt(jtfWidth.getText());
                   height = Integer.parseInt(jtfHeight.getText());
                }
                catch(NumberFormatException ex)
                {   
                    JOptionPane.showMessageDialog(f,"Dimension should be any integer value. Eg: 600","Wrong Input",JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
                //check if any textfield is  blank
                if( (jtfWidth.getText()).equals("") || (jtfHeight.getText()).equals("") )
                {
                    JOptionPane.showMessageDialog(f,"Please enter values in both fields.","Field left Blank",JOptionPane.ERROR_MESSAGE);
                }
                
                MainCanvas mc = new MainCanvas();
                mc.setDimensions(width, height);
                setVisible(false);
            }
        });
        
        btnCancel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                btnOKClicked = false;
                //stop displaying instance of dialogbox
                setVisible(false);
                
            }
        });
        
        this.pack();
        this.setVisible(true);
        
  
    }
    
    //size of dialog box
    
    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(400,200);
    }
              
}
