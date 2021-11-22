package moon_lander;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * Landing area where rocket will land.
 * 
 * @author www.gametutorial.net
 */

public class LandingArea {
    
    /**
     * X coordinate of the landing area.
     */
    public int x;
    /**
     * Y coordinate of the landing area.
     */
    public int y;
    

    /**
     * Image of landing area.
     */
    private  BufferedImage landingAreaImg;
    private  BufferedImage landingAreaImg2;
    private  BufferedImage landingAreaImg3;
    private  BufferedImage landingAreaImg4;
    private  BufferedImage landingAreaImg5;
   
    /**
     * Width of landing area.
     */
    public int landingAreaImgWidth;
    public int landingAreaImgWidth2;
    public int landingAreaImgWidth3;
    public int landingAreaImgWidth4;
    public int landingAreaImgWidth5;
   
    public LandingArea()
    {
        Initialize();
        
        LoadContent();
    }
    
    
    private void Initialize()
    {   
        // X coordinate of the landing area is at 46% frame width.
        x = (int)(Framework.frameWidth * 0.46);
        // Y coordinate of the landing area is at 86% frame height.
        y = (int)(Framework.frameHeight * 0.88);
    }
    
    
    
    public void LoadContent()
    {
        try
        {
            URL landingAreaImgUrl = this.getClass().getResource("/resources/images/landing_area.png");
            landingAreaImg = ImageIO.read(landingAreaImgUrl);
            URL landingAreaImgUrl2 = this.getClass().getResource("/resources/images/landing_area_level2.png");
            landingAreaImg2 = ImageIO.read(landingAreaImgUrl2);
            URL landingAreaImgUrl3 = this.getClass().getResource("/resources/images/landing_area_level3.png");
            landingAreaImg3 = ImageIO.read(landingAreaImgUrl3);
            URL landingAreaImgUrl4 = this.getClass().getResource("/resources/images/landing_area_level4.png");
            landingAreaImg4 = ImageIO.read(landingAreaImgUrl4);
            URL landingAreaImgUrl5 = this.getClass().getResource("/resources/images/landing_area_level5.png");
            landingAreaImg5 = ImageIO.read(landingAreaImgUrl5);
            
          
           
            
            landingAreaImgWidth =  landingAreaImg.getWidth();
            landingAreaImgWidth2 = landingAreaImg2.getWidth();
            landingAreaImgWidth3 = landingAreaImg3.getWidth();
            landingAreaImgWidth4 = landingAreaImg4.getWidth();
            landingAreaImgWidth5 = landingAreaImg5.getWidth();
            
            	
        }
        catch (IOException ex) {
            Logger.getLogger(LandingArea.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void Draw(Graphics2D g2d)
    {
    	
    	if(StageBase.stage_count==1)
        g2d.drawImage(landingAreaImg, x, y, null);
    	else if(StageBase.stage_count==2)
    		 g2d.drawImage(landingAreaImg2, x, y, null);
    	else if(StageBase.stage_count==3)
   		 g2d.drawImage(landingAreaImg3, x, y, null);
    	else if(StageBase.stage_count==4)
      		 g2d.drawImage(landingAreaImg4, x, y, null);
    	else if(StageBase.stage_count==5)
      		 g2d.drawImage(landingAreaImg5, x, y, null);
    	else if(StageBase.stage_count==99)
     		 g2d.drawImage(landingAreaImg5, x, y, null);
    	
    }
    
}
