package trollify;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FrameStructure extends JFrame
{
    FrameMenuBar frameMenuBar;
    ToolsBar toolsBar;
    FacesToolBar facesToolBar;
    EditingOptionsBar editingOptionsBar;
    MainCanvas canvas;
    JPanel jpCanvas;
    JScrollPane scrollPane;

    int width;
    int height;
    int taskBarSize;
    
    public FrameStructure()
    {   
        frameMenuBar = new FrameMenuBar(this);
        toolsBar = new ToolsBar(this);
        facesToolBar = new FacesToolBar(this);
        editingOptionsBar = new EditingOptionsBar();
        canvas = new MainCanvas();
        jpCanvas = new JPanel();
        
        
        //position panels in frame
        jpCanvas.add(canvas);
        jpCanvas.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        this.add(toolsBar, BorderLayout.WEST);
        this.add(facesToolBar, BorderLayout.EAST);
        this.add(editingOptionsBar, BorderLayout.NORTH);

        scrollPane = new JScrollPane(jpCanvas,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane,BorderLayout.CENTER);
        this.pack();
        
        //Obtain screen screen height and width
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        height = toolkit.getScreenSize().height;
        width = toolkit.getScreenSize().width;
        
        //height of the task bar
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        taskBarSize = scnMax.bottom;
        
        //Frame setup
        this.setSize(width,height - taskBarSize);
        this.setLocationRelativeTo(null);
        this.setTitle("Trollify");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void showFrame()
    {
        this.setVisible(true);
        this.setResizable(false);        
    }
}
