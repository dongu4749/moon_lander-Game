package moon_lander;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public abstract class StageBase {
	
	static BufferedImage backgroundImg;
    
	static BufferedImage redBorderImg;
 
	static BufferedImage landingAreaImg;
	
    public static int stage_count = 1; //stage 구현 위해 추가
	
	public abstract void BackgroundImage();
	
	public static int Score = 15;
	
	private int Stagelevel=1;
	public static void Draw(Graphics2D g2d, Point mousePosition)
    {
        g2d.drawImage(backgroundImg, 0, 0, Framework.frameWidth, Framework.frameHeight, null);
        
        Game.landingArea.Draw(g2d);
        if(Game.Mode==true)
        {
		Game.playerEnemy.Draw(g2d);
        }
        Game.obstacle.Draw(g2d);
        Game.obstacle2.Draw(g2d);
        Game.movingobstacle.Draw(g2d);
        Game.movingobstacle2.Draw(g2d);
        Game.playerRocket.Draw(g2d);
    }
	
	public static void DrawGameOver(Graphics2D g2d, Point mousePosition, long gameTime,long PauseTime)
    {
        Draw(g2d, mousePosition);

        if(Game.playerRocket.landed && StageBase.stage_count!=6)
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

}
