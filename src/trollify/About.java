package trollify;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class About
{
	public About()
	{       String spaceAdjustment = "                      ";
		String about = "Trollify is a free meme/troll creating application. "
                        + "\nIt allows you to sketch your thoughts in the form of memes"
                        + " or trolls without\n going through the hassle of buying expensive"
                        + " image editing softwares.\n\nVer: 1.0 \n\nEffort by:     Rohit Singh (username3663@gmail.com) "
                        + "\n" + spaceAdjustment + "Satyabeer (satya7965@gmail.com) \n" + spaceAdjustment+ "Gurkiran Singh"
                        + "\n\nMentor:   Er. Kanchan Salhotra"
                        + "\n\nBlog: 1241409.blogspot.com"
                        + "\n\nIcons source: www.iconarchive.com";
		JOptionPane.showMessageDialog(null,about,"About", JOptionPane.INFORMATION_MESSAGE, new ImageIcon("resources/niit-logo.png"));
	}      

}