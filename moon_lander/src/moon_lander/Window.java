package moon_lander;


import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


/**
 * Creates frame and set its properties.rk
 * 
 * @author www.gametutorial.net
 */

public class Window extends JFrame{
       
	
    private Window()
    {
        // Sets the title for this frame.
        this.setTitle("Moon Lander");
	        
	        
	        // Sets size of the frame.
	        if(false) // Full screen mode
	        {
	            // Disables decorations for this frame.
            this.setUndecorated(true);
            // Puts the frame to full screen.
            this.setExtendedState(this.MAXIMIZED_BOTH);
        }
        else // Window mode
        {
            // Size of the frame.
            this.setSize(800, 1000);
            
            // Puts frame to center of the screen.
            this.setLocationRelativeTo(null);
            // So that frame cannot be resizable by the user.
            this.setResizable(false);
        }
        
        // Exit the application when user close frame.
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setContentPane(new Framework());
        
        this.setVisible(true);
    }
    
    public static void main(String[] args)
    {
        // Use the event dispatch thread to build the UI for thread-safety.
        SwingUtilities.invokeLater(
        		new Runnable() {
		            @Override
		            public void run() {
		                new Window();
		            }
        		}
        );
    }
    
    }

