package moon_lander;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class StageBase {
	
	static BufferedImage backgroundImg;
    
	static BufferedImage redBorderImg;
 
	static BufferedImage landingAreaImg;
	
    public static int stage_count = 1; //stage 구현 위해 추가
	
	public static int Score = 15;
	
	
	public static void Draw(Graphics2D g2d, Point mousePosition)
    {
        g2d.drawImage(backgroundImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        
        Framework.game.landingArea.Draw(g2d);
        if(Game.Mode==true)
        {
        Framework.game.playerEnemy.Draw(g2d);
        }
        Framework.game.obstacle.Draw(g2d);
        
        Framework.game.obstacle1.Draw(g2d);
        
        Framework.game.movingobstacle.Draw(g2d);
        
        Framework.game.movingobstacle1.Draw(g2d);
        
        Framework.game.playerRocket.Draw(g2d);
    }
	
	public static void DrawGameOver(Graphics2D g2d, Point mousePosition, long gameTime,long PauseTime)
    {
        Draw(g2d, mousePosition);

        if(Framework.game.playerRocket.landed && StageBase.stage_count!=6)
        {
        	Score = (int)((gameTime-PauseTime) / Framework.secInNanosec);
            g2d.drawString("You have successfully landed!", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3);
            g2d.drawString("You have landed in " + Score + " seconds.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 20);
            g2d.drawString("Press Clicked restart game Button.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 70);
        }
        else if(StageBase.stage_count==6)
     	   g2d.drawString("Game is Clear. There's no stage left. Please go back to the main menu. ", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3);
        
        else
        {
            g2d.setColor(Color.red);
            g2d.drawString("You have crashed the rocket!", Framework.frameWidth / 2 - 95, Framework.frameHeight / 3);
            g2d.drawImage(redBorderImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
            g2d.drawString("Press Clicked restart game Button.", Framework.frameWidth / 2 - 100, Framework.frameHeight / 3 + 70);
        }
    }
	
	private void BackgroundImage() {
	      try
	        {
	         if(stage_count == 1) {
	               URL backgroundImgUrl = this.getClass().getResource("/resources/images/background1.jpg");
	               backgroundImg = ImageIO.read(backgroundImgUrl);
	         }
	         else if(stage_count == 2) {
	            URL backgroundImgUrl = this.getClass().getResource("/resources/images/background2.jpg");
	               backgroundImg = ImageIO.read(backgroundImgUrl);
	         }
	         else if(stage_count == 3) {
	            URL backgroundImgUrl = this.getClass().getResource("/resources/images/background3.jpg");
	               backgroundImg = ImageIO.read(backgroundImgUrl);
	         }
	         else if(stage_count == 4) {
	            URL backgroundImgUrl = this.getClass().getResource("/resources/images/background4.jpg");
	               backgroundImg = ImageIO.read(backgroundImgUrl);
	         }
	         else if(stage_count == 5) {
	            URL backgroundImgUrl = this.getClass().getResource("/resources/images/background5.jpg");
	               backgroundImg = ImageIO.read(backgroundImgUrl);
	         }
	         else if(stage_count == 6) {
	            URL backgroundImgUrl = this.getClass().getResource("/resources/images/background6.jpg");
	               backgroundImg = ImageIO.read(backgroundImgUrl);
	         }
	            URL redBorderImgUrl = this.getClass().getResource("/resources/images/red_border.png");
	            redBorderImg = ImageIO.read(redBorderImgUrl);
	        }
	        catch (IOException ex) {
	            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
	        }
	      
	   }
	   public void call_BackgroungImage(){
	      BackgroundImage();
	   }

}
