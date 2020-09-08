package trollify;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class EditingOptionsBar extends JToolBar
{
    //declare components 
    private JLabel lblFont;
    private JComboBox<String> jcbSelectFont;
    private JLabel lblSize;
    private JComboBox<String> jcbSelectSize;
    private JLabel lblBold;
    private JCheckBox jcbSelectBold;
    private JLabel lblItalic;
    private JCheckBox jcbSelectItalic;
    private JLabel lblForegroundColor;
    private JButton btnSelectForegroundColor;
    private JLabel lblBackgroundColor;
    private JButton btnSelectBackgroundColor;
    private JLabel lblStroke;
    private JTextField jtfSelectStroke;
    private JLabel lblScaleX;
    private JTextField jtfSelectScaleX;
    private JLabel lblScaleY;
    private JTextField jtfSelectScaleY;
    private JLabel lblTemp;
    private JLabel lblText;
    static JTextField jtfSelectText;
    
    SelectFont selectFont;
    
    public EditingOptionsBar()
    {
        selectFont = new SelectFont();
        
        //initialise components
        lblFont = new JLabel("Font ");
        jcbSelectFont = new JComboBox<String>(selectFont.getAllSystemFonts());
        
              
        lblSize  = new JLabel(" Size ");
        jcbSelectSize = new JComboBox<String>(selectFont.getFontSizes());
        
        Font f = new Font("Times New Roman",Font.BOLD,14);
        lblBold = new JLabel(" Bold");
        lblBold.setFont(f);
        jcbSelectBold = new JCheckBox();
        jcbSelectBold.setBackground(Color.GRAY);
        
        f = new Font("Times New Roman",Font.ITALIC,14);
        lblItalic = new JLabel("Italic");
        lblItalic.setFont(f);
        jcbSelectItalic = new JCheckBox();
        jcbSelectItalic.setBackground(Color.GRAY);
        
        lblForegroundColor = new JLabel("Foreground ");
        btnSelectForegroundColor = new JButton();
        //to display currently selected foreground Color on selectForegroundColor button
        btnSelectForegroundColor.setBackground(Color.BLACK);
        
        lblBackgroundColor = new JLabel("  Background ");
        btnSelectBackgroundColor = new JButton();
        //to display currently selected background Color on selectBackgroundColor button
        btnSelectBackgroundColor.setBackground(Color.WHITE);
        
        lblStroke = new JLabel("   \t\t       Stroke ");
        jtfSelectStroke = new JTextField("1",2);
        
        lblScaleX = new JLabel("       Scale:   X  ");
        jtfSelectScaleX = new JTextField("1",3);
        
        lblScaleY = new JLabel(" Y  ");
        jtfSelectScaleY = new JTextField("1",3);
        
        lblText = new JLabel(" Text:  ");
        jtfSelectText = new JTextField(50);
        jtfSelectText.setPreferredSize(new Dimension(300,10));
        jtfSelectText.isFocusable();
        //label with blank space for space adjustment in EditingOptionsBar
        lblTemp = new JLabel("");
       
        //add components
        this.add(lblFont);
        this.add(jcbSelectFont);
        this.add(lblSize);
        this.add(jcbSelectSize);
        this.add(lblBold);
        this.add(jcbSelectBold);
        this.add(lblItalic);
        this.add(jcbSelectItalic);
        this.add(lblForegroundColor);
        this.add(btnSelectForegroundColor);
        this.add(lblBackgroundColor);
        this.add(btnSelectBackgroundColor);
        this.add(lblStroke);
        this.add(jtfSelectStroke);
        this.add(lblScaleX);
        this.add(jtfSelectScaleX);
        this.add(lblScaleY);
        this.add(jtfSelectScaleY);
        this.add(lblText);
        this.add(jtfSelectText);
        this.add(lblTemp);
         
           
        //set orientation of editingOptionsToolBar
        this.setOrientation(HORIZONTAL);
        
        //set Floating property of editingOptionsToolBar
        this.setFloatable(false);
        
        //set Background of editingOptionsToolBar
        this.setBackground(Color.GRAY);
        
        //register components with action listener
        jcbSelectFont.addActionListener(new EditingOptionsListener());
        jcbSelectSize.addActionListener(new EditingOptionsListener());
        jcbSelectBold.addActionListener(new EditingOptionsListener());
        jcbSelectItalic.addActionListener(new EditingOptionsListener());
        btnSelectForegroundColor.addActionListener(new EditingOptionsListener());
        btnSelectBackgroundColor.addActionListener(new EditingOptionsListener());
        jtfSelectStroke.addActionListener(new EditingOptionsListener());
        jtfSelectScaleX.addActionListener(new EditingOptionsListener());
        jtfSelectScaleY.addActionListener(new EditingOptionsListener());
        jtfSelectText.addActionListener(new EditingOptionsListener());
              
    }
    
   
    //listener class
    class EditingOptionsListener implements ActionListener
    {   
        MainCanvas mc = new MainCanvas();
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //select foreground color when btnSelectForegroundColor is clicked
            if(e.getSource() == btnSelectForegroundColor)
            {
                Color foreColor = JColorChooser.showDialog(null,"Foreground Color", Color.BLACK);
                 
                if(foreColor != null)
                {
                    btnSelectForegroundColor.setBackground(foreColor);
                    mc.setForegroundColor(foreColor);
                }
            }
            
            //select background color when btnSelectBackgroundColor is clicked
            if(e.getSource() == btnSelectBackgroundColor)
            {   
                Color backColor = JColorChooser.showDialog(null,"Background Color", Color.WHITE);

                if(backColor != null)
                {
                    btnSelectBackgroundColor.setBackground(backColor);
                    mc.setBackgroundColor(backColor);
                }
            }
            
            //select font name when jcbSelectFont is clicked
            if(e.getSource() == jcbSelectFont)
            {
                String f = (String) jcbSelectFont.getSelectedItem();
                
                if(f != null)
                {
                    jcbSelectFont.setSelectedItem(f);
                    mc.setFontName(f);
                }
            }
            
            //select font styles(bold / italic)
            if(e.getSource() == jcbSelectBold || e.getSource() == jcbSelectItalic )
            {   
                int type;
                if(jcbSelectBold.isSelected() && jcbSelectItalic.isSelected())
                {
                    type = 3;   //(bold and italic)
                }
                else if(jcbSelectBold.isSelected() && !jcbSelectItalic.isSelected())
                {
                    type = 1;  //(bold)
                }
                else if(!jcbSelectBold.isSelected() && jcbSelectItalic.isSelected())
                {
                    type = 2;   //(italic)
                }
                else
                {
                    type = 0; //(plain)
                }
                
                mc.setFontStyle(type);
            }
            
            //select font size when jcbSelectSize is clicked
            if(e.getSource() == jcbSelectSize)
            {
                if(jcbSelectSize.getSelectedItem() != null)
                {
                    //parsing can throw NumberFormatException
                    try
                    {
                        int size = Integer.parseInt((String) jcbSelectSize.getSelectedItem());
                        jcbSelectSize.setSelectedItem(size);
                        mc.setFontSize(size);
                    }catch(NumberFormatException excp)
                    {
                        //do nothing
                    }
                }
            }
            
            //set stroke
            if(e.getSource() == jtfSelectStroke)
            {   
                int stroke;
                if(jtfSelectStroke.getText() != null)
                {
                    //parsing can throw NumberFormatException
                    try
                    {
                        stroke = Integer.parseInt(jtfSelectStroke.getText());
                        mc.setStroke(stroke);
                    }
                    catch(NumberFormatException ex)
                    {
                        JOptionPane.showMessageDialog(null, "Invalid Input. \nStroke can be any "
                                + "integer of float value.", "Bad Input", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
            if(e.getSource() == jtfSelectScaleX)
            {
                float scaleX;
                
                if(jtfSelectScaleX.getText() != null)
                {
                    //parsing can throw NumberFormatException
                    try
                    {
                        scaleX = Float.parseFloat(jtfSelectScaleX.getText());
                    }
                    catch(NumberFormatException exc)
                    {
                        JOptionPane.showMessageDialog(null, "Invalid Input. \nScale can be any "
                                + "integer of float value.", "Bad Input", JOptionPane.ERROR_MESSAGE);
                        scaleX = 1; //set default value
                    }
                }
                else
                {   
                    scaleX = 1;
                }
                
                mc.setScaleX(scaleX);
            }
            
            if(e.getSource() == jtfSelectScaleY)
            {
                float scaleY;
                
                if(jtfSelectScaleY.getText() != null)
                {
                    //parsing can throw NumberFormatException
                    try
                    {
                        scaleY = Float.parseFloat(jtfSelectScaleY.getText());
                    }
                    catch(NumberFormatException exc)
                    {
                        JOptionPane.showMessageDialog(null, "Invalid Input. \nScale can be any "
                                + "integer of float value.", "Bad Input", JOptionPane.ERROR_MESSAGE);
                        scaleY = 1; //set default value
                    }
                }
                else
                {   
                    scaleY = 1;
                }
                
                mc.setScaleY(scaleY);
            }
            
            if(e.getSource() == jtfSelectText)
            {   
                if(jtfSelectText.getText() != null && !jtfSelectText.getText().equals(""))
                {
                    mc.setTextStrings(jtfSelectText.getText());
                }
            }
        }
        
    }
}