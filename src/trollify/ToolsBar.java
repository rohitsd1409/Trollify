package trollify;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

public class ToolsBar extends JToolBar// extends JPanel
{
    JFrame frame;
    
    private JButton btnLine;
    private JButton btnRectangle;
    private JButton btnOval;
    private JButton btnPencil;
    private JButton btnText;
    private JButton btnLolCrazyFace;
    private JButton btnAngryMomFace;
    private JButton btnBinnuFace;
    private JButton btnBhallaFace;
    private JButton btnCheerSmileFace;
    private JButton btnGrumpyCat;
    private JButton btnGrammerGuy;
    private JButton btnOneDoesNotSimply;
   
           
    public ToolsBar(JFrame f)
    {   
        frame = f;
        
        //initialise buttons (set imsge icons on buttons)
        btnLine = new JButton(new ImageIcon("resources/line-icon.png"));
        btnRectangle = new JButton(new ImageIcon("resources/rectangle-icon.png"));
        btnOval = new JButton(new ImageIcon("resources/oval-icon.png"));
        btnPencil = new JButton(new ImageIcon("resources/pencil-icon.png"));
        btnText = new JButton(new ImageIcon("resources/text-tool-icon.png"));
        btnLolCrazyFace = new JButton(new ImageIcon("resources/lol-crazy-face.png"));
        btnAngryMomFace = new JButton(new ImageIcon("resources/angry-mom-face.png"));
        btnBinnuFace = new JButton(new ImageIcon("resources/binnu-face.png"));
        btnBhallaFace = new JButton(new ImageIcon("resources/bhalla-face.png"));
        btnCheerSmileFace = new JButton(new ImageIcon("resources/cheer-smile-face.png"));
        btnGrumpyCat = new JButton(new ImageIcon("resources/grumpy-cat-face.png"));
        btnGrammerGuy = new JButton(new ImageIcon("resources/grammer-guy-face.png"));
        btnOneDoesNotSimply = new JButton(new ImageIcon("resources/one-does-not-simply-face.png"));
        
        //set help tips for buttons when mouse is hovered over button
        btnLine.setToolTipText("Line");
        btnRectangle.setToolTipText("Rectangle");
        btnOval.setToolTipText("Oval");
        btnPencil.setToolTipText("Pencil");
        btnText.setToolTipText("Text");
        btnLolCrazyFace.setToolTipText("Lol Crazy Face");
        btnAngryMomFace.setToolTipText("Angry Mom Face");
        btnBinnuFace.setToolTipText("Binnu");
        btnBhallaFace.setToolTipText("Bhalla");
        btnCheerSmileFace.setToolTipText("Cheering ");
        btnGrumpyCat.setToolTipText("Grumpy Cat");
        btnGrammerGuy.setToolTipText("Tu rehndey");
        btnOneDoesNotSimply.setToolTipText("One Does Not Simply....");
                      
        //set Orientation of ToolBar
        this.setOrientation(VERTICAL);
        
        //make toolsbar non-floatable
        this.setFloatable(false);
        
        //set background color of panel
        this.setBackground(Color.GRAY);
        
        //add buttons in ToolsPanel
        this.add(btnLine);
        this.add(btnRectangle);
        this.add(btnOval);
        this.add(btnPencil);
        this.add(btnText);
        this.add(btnLolCrazyFace);
        this.add(btnAngryMomFace);
        this.add(btnBinnuFace);
        this.add(btnBhallaFace);
        this.add(btnCheerSmileFace);
        this.add(btnGrumpyCat);
        this.add(btnGrammerGuy);
        this.add(btnOneDoesNotSimply);
                
        //apply action listeners to components
        btnLine.addActionListener(new ToolsBarListener());
        btnRectangle.addActionListener(new ToolsBarListener());
        btnOval.addActionListener(new ToolsBarListener());
        btnPencil.addActionListener(new ToolsBarListener());
        btnText.addActionListener(new ToolsBarListener());
        btnLolCrazyFace.addActionListener(new ToolsBarListener());
        btnAngryMomFace.addActionListener(new ToolsBarListener());
        btnBinnuFace.addActionListener(new ToolsBarListener());
        btnBhallaFace.addActionListener(new ToolsBarListener());
        btnCheerSmileFace.addActionListener(new ToolsBarListener());
        btnGrumpyCat.addActionListener(new ToolsBarListener());
        btnGrammerGuy.addActionListener(new ToolsBarListener());
        btnOneDoesNotSimply.addActionListener(new ToolsBarListener());
    }
    
    class ToolsBarListener implements ActionListener
    {   
        MainCanvas mc = new MainCanvas();
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            //draw line
            if(e.getSource() == btnLine)
            {
                mc.setDrawMode(2);
                frame.revalidate();
                frame.repaint();
            }
            
            //draw rectangle
            if(e.getSource() == btnRectangle)
            {
                mc.setDrawMode(3);
                frame.revalidate();
                frame.repaint();
            }
            
            //draw Oval
            if(e.getSource() == btnOval)
            {
                mc.setDrawMode(4);
                frame.revalidate();
                frame.repaint();
            }
            
            //free hand drawing
            if(e.getSource() == btnPencil)
            {
                mc.setDrawMode(5);
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnText)
            {
                mc.setDrawMode(11);
                ++MainCanvas.textStringsIndex;
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnLolCrazyFace)
            {
                mc.openMemeFace("listedMemes/lol-crazy-face.png");
                frame.revalidate();
                frame.repaint();
            }
        
            if(e.getSource() == btnAngryMomFace)
            {
                mc.openMemeFace("listedMemes/angry-mom-face.png");
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnBinnuFace)
            {
                mc.openMemeFace("listedMemes/binnu-face.png");
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnBhallaFace)
            {
                mc.openMemeFace("listedMemes/bhalla-face.png");
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnCheerSmileFace)
            {
                mc.openMemeFace("listedMemes/cheer-smile-face.png");
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnGrumpyCat)
            {
                mc.openMemeFace("listedMemes/grumpy-cat-face.png");
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnGrammerGuy)
            {
                mc.openMemeFace("listedMemes/grammer-guy-face.png");
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnOneDoesNotSimply)
            {
                mc.openMemeFace("listedMemes/one-does-not-simply-face.png");
                frame.revalidate();
                frame.repaint();
            }
            
        }
        
    }
}
