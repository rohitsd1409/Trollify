package trollify;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MainCanvas extends JPanel implements MouseListener, MouseMotionListener
{   
    //default width and height of drawing panel
    public static int width = 600 ;
    public static int height = 800 ;
    
    //foreground and background colors of drawing panel
    static Color backgroundColor = Color.WHITE;
    static Color foregroundColor = Color.BLACK;
    
    //constants, to keep track of actions
    protected static final int IMAGE = 1;
    
    protected static final int LINE = 2;
    protected static final int RECTANGLE = 3; 
    protected static final int OVAL = 4; 
    protected static final int PENCIL = 5; 
    
    protected static final int TEXT = 11; 
    
    //keeps track of currently selected operation by user
    protected static int drawMode = 0;
    
    //to hold x and y axis values
    private static int x1;
    private static int x2;
    private static int y1;
    private static int y2;
    
    //kept for future usage
    public static int CURRENT_OPERATION = 0;     // 100 for image operations , 101 for shape operations, 102 for textOperations 
    
    //shapes and properties
    protected static ArrayList<Shape> shapes = new ArrayList<Shape>();
    protected static int[] shapesStroke = new int[5000];        //large size to accomodate free hand drawing
    protected static ArrayList<Color> shapesColor = new ArrayList<Color>();
    public static int shapesIndex = -1; //index starts from 0. hence initialised with -1 (++shapesIndex = 0)
    public static int incrementShapesIndex = -1; //used to increment shapesIndex while using free hand drawing tool
    public static int tempCounterValue = 0;
    
    //text and properties
    protected static String[] textStrings = new String[50];
    protected static String[] fontName = new String[50];
    protected static int[] fontSize = new int[50];
    protected static int[] fontStyle = new int[50];     // Font.PLAIN(0),Font.BOLD(1),Font.ITALIC(2) and Font.BOLD + Font.ITALIC(3),
    protected static ArrayList<Color> fontColor = new ArrayList<Color>(50);
    public static int textStringsIndex = -1; //index starts from 0. hence initialised with -1 (++bImageIndex = 0)
    protected static int[] xPosOfText = new int[50];
    protected static int[] yPosOfText = new int[50];
    
    //buffered images and properties
    protected static ArrayList<BufferedImage> bImage = new ArrayList<BufferedImage>(20);
    public static int bImageIndex = -1; //index starts from 0. hence initialised with -1 (++bImageIndex = 0)
    protected static int[] xPosOfImage = new int[20];
    protected static int[] yPosOfImage = new int[20];
    protected static float[] xScale = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};    //initialise scaling with default
    protected static float[] yScale = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};    //value 1. {size  = 20 }
    
    

    public MainCanvas()
    {
        this.setBackground(backgroundColor);
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    /*  ==================
      || paintComponent() ||   
        ==================
    */

    @Override
    protected void paintComponent(Graphics g)
    {   
        super.paintComponent(g);
                
        this.setPreferredSize(getDimensions());
        this.setSize(getDimensions());
        this.setBackground(getBackgroundColor());
        repaint(); //refresh paint canvas  
        
        Graphics2D g2  = (Graphics2D) g;

        //make text field focusable
        EditingOptionsBar.jtfSelectText.setFocusable(true);
                
        //set Current Operation
        if(drawMode == 1)
        {
            CURRENT_OPERATION = 100; 
        }
        else if(drawMode >= 2 && drawMode <= 10)
        {
            CURRENT_OPERATION = 101;
        }
        else if(drawMode == 11)
        {
            CURRENT_OPERATION = 102;
        }

        //temporarily store current transformations
        AffineTransform currentAF = g2.getTransform();
        
        
        //draw all buffered images on canvas
        for(int i = 0; i < getImage().size(); i++ )
        {   
           AffineTransform af = new AffineTransform();
           af.scale(xScale[i], yScale[i]);
           
           System.out.println("xScale[" + i +"]: " + xScale[i]);

           /* ||  bug solved 
              ||  Cause: use of setTransform() method (g2.setTransform(af)) to add a coordinate transform.
              ||  Solution: using transform() method (g2.transform(af)) in place of setTransform() 
              ||  Reference: http://docs.oracle.com/javase/7/docs/api/java/awt/Graphics2D.html#setTransform%28java.awt.geom.AffineTransform%29
              ||  Varience caused by bug: image placed on drawing panel moved alongwith movement of jscrollbar
              ||  positioning of image on panel was not respected
           */  
           
           //apply transformation on graphics instance
           g2.transform(af);
           
           //draw image on panel
           g2.drawImage(bImage.get(i), xPosOfImage[i], yPosOfImage[i], null);
           g2.setTransform(currentAF);
        }

        //re-apply current transformations
        g2.setTransform(currentAF);
        
       /* 
        //resize shapesStroke[] when required
        if(shapesStroke[shapesStroke.length - 50] != 0) 
        {
            System.out.println("Resizing.");
            int[] tempShapesStroke = new int[shapesStroke.length * 2];
            for(int i = 0; i< shapesStroke.length; i++)
            {
                tempShapesStroke[i] = shapesStroke[i];
            }
            shapesStroke = tempShapesStroke;           
        }
        */
        //draw all shapes on canvas
        for(int j = 0; j < getShapes().size(); j++)
        {
            try
            {
                g2.setColor(shapesColor.get(j));
            }
            catch(Exception ex)
            {       
                shapesColor.add(j,foregroundColor);
                g2.setColor(shapesColor.get(j));
            }

            g2.setStroke(new BasicStroke(shapesStroke[j]));

            g2.draw(shapes.get(j));
        }
        
   
        //store fonts related properties temporarily
        String[] tempFontName = null;
        int[] tempFontStyle = null;
        int[] tempFontSize = null;
        ArrayList<Color> tempFontColor = null;
        
        if(textStringsIndex >= 0 )
        {
            tempFontName = getFontName();
            tempFontStyle = getFontStyle();
            tempFontSize = getFontSize();
            tempFontColor = getFontColor();
        }
        
        //draw all text areas on canvas
        for(int k = 0; k < textStrings.length; k++)
        {   
            //perform operations only if elements in array exists
            if(textStrings[k] != null)
            {   //apply properties on fonts
                g2.setFont(new Font(tempFontName[k], tempFontStyle[k], tempFontSize[k]));
                g2.setColor(tempFontColor.get(k));

                //draw string
                g2.drawString(textStrings[k], xPosOfText[k], yPosOfText[k] + 50 );
            }
        }
        
        //dispose graphics2d instance
        g2.dispose();
   
    }
    
    /* ========================================================
      || Brace Yourself, Getter and Setter methods are arriving||   
        =======================================================
    */
    
    //set Dimensions of canvas
    public void setDimensions(int width, int height)
    {
        this.width = width;
        this.height = height;
        repaint();
    }
    
    //get Dimensions of canvas
    public Dimension getDimensions()
    {   return new Dimension(width,height);
    }
    
  
    //set Draw Mode
    public void setDrawMode(int val)
    {
        drawMode = val;
    }
    
    //set x pos for each buffered image
    public void setXPosOfImage(int x)
    {
        xPosOfImage[bImageIndex] = x;
    }
    //set y pos for each buffered image
    public void setYPosOfImage(int y)
    {
        yPosOfImage[bImageIndex] = y;
    }
    
    public int[] getXPosOfImage()
    {
        return xPosOfImage;
    }
    
    public int[] getYPosOfImage()
    {
        return yPosOfImage;
    }
        
    //set buffered image
    public void setImage(BufferedImage bImage)
    {
        this.bImage.add(bImage);
    }
    
    //get buffered image
    public ArrayList<BufferedImage> getImage()
    {
        return this.bImage;
    }
    
    //set Shape
    public void setShapes(Shape s)
    {
        this.shapes.add(s);
    }
    
    //get shape
    public ArrayList<Shape> getShapes()
    {   return this.shapes;
    }

    //set foreground Color
    public void setForegroundColor(Color foregroundColor)
    {
        this.foregroundColor = foregroundColor;

        if(drawMode >= 2 && drawMode <= 10)
        {
            shapesColor.add(foregroundColor);
        }
        
    }
    
    //get font color
    public ArrayList<Color> getFontColor()
    {   if(drawMode == 11)
        {
            fontColor.add(textStringsIndex,foregroundColor);
        }
        else
        {   fontColor.add(foregroundColor);
        }
        return fontColor;
    }
    
    //get foreground Color
    public ArrayList<Color> getForegroundColor()
    {
        return shapesColor;
    }

    //set background Color
    public void setBackgroundColor(Color backgroundColor)
    {
        this.backgroundColor = backgroundColor;
    }
    
    //get foreground Color
    public Color getBackgroundColor()
    {
        return this.backgroundColor;
    }
    
    public void setTextStrings(String s)
    {   
        textStrings[textStringsIndex] = s;
    }
    //set text font name
    public void setFontName(String fontName)
    {      
       this.fontName[textStringsIndex] = fontName;
    }
    
    //get text font name
    public String[] getFontName()
    {   //to provide default font name for current uninitialised element
        if(fontName[textStringsIndex] == null)
        {
            fontName[textStringsIndex] = "Times New Roman";
        }
        return this.fontName;
    }
    
    //set font style
    public void setFontStyle(int fontStyle)
    {
        this.fontStyle[textStringsIndex] = fontStyle;
    }
    
    //get font style
    public int[] getFontStyle()
    {   //font.PLAIN for 0
        return this.fontStyle;
    }
    
    //set font Size
    public void setFontSize(int fontSize)
    {
        this.fontSize[textStringsIndex] = fontSize;
    }
    
    //get font Size
    public int[] getFontSize()
    {   //to provide default font name for current uninitialised element
        if(fontSize[textStringsIndex] == 0)
        {
            fontSize[textStringsIndex] = 48;
        }
        return this.fontSize;
    }
        
    //set x pos for each buffered image
    public void setXPosOfText(int x)
    {
        xPosOfText[textStringsIndex] = x;
    }
    //set y pos for each buffered image
    public void setYPosOfText(int y)
    {
        yPosOfText[textStringsIndex] = y;
    }
    
    //set Stroke size
    public void setStroke(int stroke)
    {           
        
        if(drawMode != 5)
        {
            shapesStroke[shapesIndex] = stroke; 
        }
            
        //in case of free hand drawing
        if(drawMode == 5)
        {   
            for(int i = shapesIndex; i >= (shapesIndex - tempCounterValue); i--)
            {
                shapesStroke[i] = stroke;
            }
        } 
        
        //to set current stroke as stroke of all further shapes unless explicitly changed by user
        for(int i = shapesIndex + 1; i < shapesStroke.length; i++)
        {
            shapesStroke[i] = stroke;
        }
    }
    
    //get Stroke
    public int[] getStroke()
    {
        return shapesStroke;
    }
    
    //set scale about x-axis
    public void setScaleX(float x)
    {
        xScale[bImageIndex] = x;
    }
    
    public float[] getScaleX()
    {
        return xScale;
    }
    
    //set scale about y-axis
    public void setScaleY(float y)
    {
        yScale[bImageIndex] = y;
    }
    
    public float[] getScaleY()
    {
        return yScale;
    }
    
/*  ===================================
   || Methods to fullfil user requests ||   
    ===================================
*/
    //set new canvas dimensions
    public void newCanvas(JFrame frame)
    {   
        NewCanvasDialogBox ncdb = new NewCanvasDialogBox(frame, true);
    }
    
    
    //open new image file
    public void openFile(String fileLocation)
    {
            JFileChooser jFileChooser = new JFileChooser(new File(fileLocation));
            if(jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
            {
                try
                {   
                    setDrawMode(1);
                    File file = jFileChooser.getSelectedFile();
                    setImage(ImageIO.read(file));
                    ++bImageIndex; //increment index value
                    repaint();
                }
                catch(IOException ex)
                {
                    JOptionPane.showMessageDialog(null,"Unable to open file", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                //return
            }
    }
    
    public void openMemeFace(String fileName)
    {
        try
        {   
            setDrawMode(1);
            File file = new File(fileName);
            setImage(ImageIO.read(file));
            ++bImageIndex; //increment index value
            repaint();
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null,"Unable to open file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //clear contents from canvas
    public void clearData()
    {   
        this.removeAll();
        
        shapes.removeAll(shapes);
        shapesIndex = -1;
        shapesColor.removeAll(shapesColor);
        
        for(int i = 0; i < shapesStroke.length; i++)
        {
            shapesStroke[i] = 1;
        }
        
        bImage.removeAll(bImage);
        for(int j = 0; j < bImage.size(); j++ )
        {
            xScale[j] = 1;
            yScale[j] = 1;
            xPosOfImage[j] = 1;
            yPosOfImage[j] = 1;
        }    

        bImageIndex = -1;
        
        for(int i = 0; i < textStrings.length; i++)
        {
            textStrings[i] = null;
            fontName[i] = "Times New Roman";
            fontSize[i] = 48;
            fontStyle[i] = 0;
        }
        fontColor.clear();
        textStringsIndex = -1;
        
        this.setDrawMode(0);
        this.revalidate();
                    
    }
    
/*  ============
   || Listeners ||   
    ============
*/
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        x2 = e.getX();
        y2 = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
    }
    
    @Override
    public void mousePressed(MouseEvent e)
    {   
        if(drawMode == 2 || drawMode == 3 || drawMode == 4 || drawMode == 5)
        {
            ++MainCanvas.shapesIndex;   //increment for rectangle, oval and line (not for freehand, freehand will be handled in mousedragged)
        }
        x1 = e.getX();
        y1 = e.getY();
    }
            
    @Override
    public void mouseReleased(MouseEvent e)
    {
        x2 = e.getX();
        y2 = e.getY();
        
        Shape shape = null;
        
        if(drawMode == 2)
        {
            shape = new Line2D.Float(x1,y1,x2,y2);
        }
        
        if(drawMode == 3)
        {
            shape = new Rectangle2D.Float(x1,y1,x2-x1,y2-y1);
        }
        
        if(drawMode == 4)
        {
            shape = new Ellipse2D.Float(x1,y1,x2-x1,y2-y1);
        }
        
        if(shape != null)
        {   
            setShapes(shape);
            repaint();
        }
        
        //special case of free hand drawing
        if(drawMode == 5)
        {   //update shapes index value by add counter 
            shapesIndex = shapesIndex + incrementShapesIndex;

            //shift value to temp var
            tempCounterValue = incrementShapesIndex;

            //re-initialise index incrementer
            incrementShapesIndex = -1;
        }
               
    }
    
    @Override
    public void mouseDragged(MouseEvent e)
    {
        //bufferedImage
        if(drawMode == 1)
        {   int destX = e.getX();
            int destY = e.getY();
            
            //dynaically calculate multiplier for correct positioning of image
            int xMultiplier = (int) (this.getWidth() / (this.getWidth() * xScale[bImageIndex]));
            int yMultiplier = (int) (this.getWidth() / (this.getWidth() * yScale[bImageIndex]));
            
            //multiplier should not be 0 neither it should increase when scaling becomes larger than 1
            if(xMultiplier == 0 || xScale[bImageIndex] > 1)
            {
                xMultiplier = 1;
            }
            if(yMultiplier == 0 || yScale[bImageIndex] > 1)
            {
                yMultiplier = 1;
            }
            
            setXPosOfImage((destX * xMultiplier)); 
            setYPosOfImage((destY * yMultiplier));

        }
        
        //free hand drawing
        if(drawMode == 5)
        {
            x2 = e.getX();
            y2 = e.getY();
           
            Shape s = new Line2D.Float(x1,y1,x2,y2);
            setShapes(s);
            ++incrementShapesIndex; //keep record of number of sub-lines joined to create free hand line
            repaint();
            
            x1 = x2;
            y1 = y2;
        }
     
        if(drawMode == 11)
        {   int destX = e.getX();
            int destY = e.getY();
            
            setXPosOfText(destX );
            setYPosOfText(destY );
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) 
    {
    
    }

    
}
