package trollify;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import static javax.swing.SwingConstants.VERTICAL;

public class FacesToolBar extends JToolBar// extends JPanel
{
    JFrame frame;
    
    private JButton btnMoreFaces;
    private JButton btnFishYeahFace;
    private JButton btnFacePalmFace;
    private JButton btnForeverAloneFace;
    private JButton btnGTFOFace;
    private JButton btnIfYouKnowFace;
    private JButton btnThinkerFace;
    private JButton btnKnowThatFeelFace;
    private JButton btnYUNoFace;
    private JButton btnTrueStoryFace;
    private JButton btnTrollDadFace;
    private JButton btnOMGRunFace;
    private JButton btnBigSmileFace;
    
           
    public FacesToolBar(JFrame f)
    {   
        frame = f;
        
        //initialise buttons
        btnMoreFaces = new JButton(new ImageIcon("resources/more-faces.png"));
        btnFishYeahFace = new JButton(new ImageIcon("resources/fish-yeah-face.png"));
        btnFacePalmFace = new JButton(new ImageIcon("resources/face-palm-face.png"));
        btnForeverAloneFace = new JButton(new ImageIcon("resources/forever-alone-face.png"));
        btnGTFOFace = new JButton(new ImageIcon("resources/gtfo-face.png"));
        btnIfYouKnowFace = new JButton(new ImageIcon("resources/if-you-know-face.png"));
        btnThinkerFace = new JButton(new ImageIcon("resources/thinker-face.png"));
        btnKnowThatFeelFace = new JButton(new ImageIcon("resources/know-that-feel-face.png"));
        btnYUNoFace = new JButton(new ImageIcon("resources/y-u-no-face.png"));
        btnTrueStoryFace = new JButton(new ImageIcon("resources/true-story-face.png"));
        btnTrollDadFace = new JButton(new ImageIcon("resources/troll-dad-face.png"));
        btnOMGRunFace = new JButton(new ImageIcon("resources/omg-run-face.png"));
        btnBigSmileFace = new JButton(new ImageIcon("resources/big-smile-face.png"));
        
        //set help tips for buttons when mouse is hovered over button
        btnMoreFaces.setToolTipText("More Faces");
        btnFishYeahFace.setToolTipText("Fu** Yeah");
        btnFacePalmFace.setToolTipText("Face Palm");
        btnForeverAloneFace.setToolTipText("Forever Alone");
        btnGTFOFace.setToolTipText("Get The Fu** Outta Here ");
        btnIfYouKnowFace.setToolTipText("If you Know What I Mean");
        btnThinkerFace.setToolTipText("In Deep Thinking");
        btnKnowThatFeelFace.setToolTipText("I Know That Feel Bro");
        btnYUNoFace.setToolTipText("Y U No");
        btnTrueStoryFace.setToolTipText("True Story");
        btnTrollDadFace.setToolTipText("Troll Dad Dancing");
        btnOMGRunFace.setToolTipText("OMG! Run");
        btnBigSmileFace.setToolTipText("Derpina Smiling");
        
        //set Orientation of ToolBar
        this.setOrientation(VERTICAL);
        
        //make toolsbar non-floatable
        this.setFloatable(false);
        
        //set background color of panel
        this.setBackground(Color.GRAY);
        
        //add buttons in ToolsPanel
        this.add(btnMoreFaces);
        this.add(btnFishYeahFace);
        this.add(btnFacePalmFace);
        this.add(btnForeverAloneFace);
        this.add(btnGTFOFace);
        this.add(btnIfYouKnowFace);
        this.add(btnThinkerFace);
        this.add(btnKnowThatFeelFace);
        this.add(btnYUNoFace);
        this.add(btnTrueStoryFace);
        this.add(btnTrollDadFace);
        this.add(btnOMGRunFace);
        this.add(btnBigSmileFace);
        
        btnMoreFaces.addActionListener(new FacesToolBarListener());
        btnFishYeahFace.addActionListener(new FacesToolBarListener());
        btnFacePalmFace.addActionListener(new FacesToolBarListener());
        btnForeverAloneFace.addActionListener(new FacesToolBarListener());
        btnGTFOFace.addActionListener(new FacesToolBarListener());
        btnIfYouKnowFace.addActionListener(new FacesToolBarListener());
        btnThinkerFace.addActionListener(new FacesToolBarListener());
        btnKnowThatFeelFace.addActionListener(new FacesToolBarListener());
        btnYUNoFace.addActionListener(new FacesToolBarListener());
        btnTrueStoryFace.addActionListener(new FacesToolBarListener());
        btnTrollDadFace.addActionListener(new FacesToolBarListener());
        btnOMGRunFace.addActionListener(new FacesToolBarListener());
        btnBigSmileFace.addActionListener(new FacesToolBarListener());
    }
    
    public class FacesToolBarListener implements ActionListener
    {
        MainCanvas mc = new MainCanvas();
        
        @Override
        public void actionPerformed(ActionEvent e)
        {
            if(e.getSource() == btnMoreFaces)
            {
                mc.openFile("meme-faces/");
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnFishYeahFace)
            {
                mc.openMemeFace("listedMemes/fish-yeah-face.png");
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnFacePalmFace)
            {
                mc.openMemeFace("listedMemes/face-palm-face.png");
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnForeverAloneFace)
            {
                mc.openMemeFace("listedMemes/forever-alone-face.png");
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnGTFOFace)
            {
                mc.openMemeFace("listedMemes/gtfo-face.png");
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnIfYouKnowFace)
            {
                mc.openMemeFace("listedMemes/if-you-know-face.png");
                frame.revalidate();
                frame.repaint();
            }

            if(e.getSource() == btnThinkerFace)
            {
                mc.openMemeFace("listedMemes/thinker-face.png");
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnKnowThatFeelFace)
            {
                mc.openMemeFace("listedMemes/know-that-feel-face.png");
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnYUNoFace)
            {
                mc.openMemeFace("listedMemes/y-u-no-face.png");
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnTrueStoryFace)
            {
                mc.openMemeFace("listedMemes/true-story-face.png");
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnTrollDadFace)
            {
                mc.openMemeFace("listedMemes/troll-dad-face.png");
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnOMGRunFace)
            {
                mc.openMemeFace("listedMemes/omg-run-face.png");
                frame.revalidate();
                frame.repaint();
            }
            
            if(e.getSource() == btnBigSmileFace)
            {
                mc.openMemeFace("listedMemes/big-smile-face.png");
                frame.revalidate();
                frame.repaint();
            }
        }
    }
}
