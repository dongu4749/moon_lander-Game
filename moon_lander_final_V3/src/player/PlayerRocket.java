package player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import moon_lander.Canvas;
import moon_lander.Framework;
import moon_lander.PauseOption;
import moon_lander.StageBase;

/**
 * The space rocket with which player will have to land.
 * 
 * @author www.gametutorial.net
 */

public class PlayerRocket extends JFrame implements GamePlayer{
	
	public static int character_num;
    
    /**
     * We use this to generate a random number for starting x coordinate of the rocket.
     */
    private Random random;
    public static int hp = 100;
    /**
     * X coordinate of the rocket.
     */
    private int x;
    /**
     * Y coordinate of the rocket.
     */
    private int y;
    
    /**
     * Is rocket landed?
     */
    public boolean isLanded;
    
    
    /**
     * Has rocket crashed?
     */
    public boolean isCrashed;
        
    /**
     * 로켓의 가속 속도.
     */
    private int speedAccelerating;
    /**
     * 로켓의 정지/하강 속도. 속도가 떨어지는 이유는 중력이 로켓을 달로 끌어내리기 때문이다.
     */
    private double speedStopping;
    
    /**
     * 착륙 시 충돌 없이 로켓이 가질 수 있는 최대 속도입니다.
     */
    private int topLandingSpeed;
    
    /**
     * x좌표상에서 로켓은 얼마나 빠르고 어느 방향으로 움직이는가?
     */
    private int speedX;
    /**
     * y좌표상에서 로켓은 얼마나 빠르고 어느 방향으로 움직이는가?
     */
    private int speedY;
            
    /**
     * Image of the rocket in air.
     */
    public BufferedImage rocketImg;
    /**
     * Image of the rocket when landed.
     */
    private BufferedImage rocketLandedImg;
    /**
     * Image of the rocket when crashed.
     */
    private BufferedImage rocketCrashedImg;
    /**
     * Image of the rocket fire.
     */
    public BufferedImage rocketFireImg;
    
    
    /**
     * Width of rocket.
     */
    public int rocketImgWidth;
    /**
     * Height of rocket.
     */
    public int rocketImgHeight;

    public int getCoordinateX() {
    	return x;
    }
    
    public int getCoordinateY() {
    	return y;
    }
    
    public void setSpeedStopping(double speedStopping) {
    	this.speedStopping=speedStopping;
    }
    
    public int getSpeedX() {
    	return speedX;
    }
    
    public int getSpeedY() {
    	return speedY;
    }
    
    public void setSpeedX(int speedX) {
    	this.speedX=speedX;
    }
    
    public void setSpeedY(int speedY) {
    	this.speedY=speedY;
    }
    
    public int getTopLandingSpeed() {
    	return topLandingSpeed;
    }
    public void playerRocketKeyEventWPress()
    {
    	speedY -= speedAccelerating;
    }
    public void playerRocketKeyEventWNotPress()
    {
    	speedY += speedStopping;
    }
    public void playerRocketKeyEventLeftPress()
    {
    	speedX -= speedAccelerating;
    }
    public void playerRocketKeyEventLeftNotPress()
    {
    	speedX += speedStopping;
    }
    public void playerRocketKeyEventRightPress()
    {
    	speedX += speedAccelerating;
    }
    public void playerRocketKeyEventRightNotPress()
    {
    	speedX -= speedStopping;
    }
    public void moveRocket()
    {
    	x += speedX;
        y += speedY;
    }
    public PlayerRocket()
    {
        Initialize();
        LoadContent();
        
        //이제 rocketImgWidth가 있으므로 시작 x 좌표를 설정합니다.
        x = random.nextInt(800 - rocketImgWidth);
        
        if(character_num==2) {
        	topLandingSpeed=9;
            hp=80;
         }
         else if(character_num==3) {
            hp=150;
            topLandingSpeed=6;
         }
    }
    
    
    public void Initialize()
    {
        random = new Random();
        
        ResetPlayer();
       
        speedStopping = 1;
       
        speedAccelerating = 2;
        
        topLandingSpeed = 7;
    }
    
    public void LoadContent()
    {
        try
        {
        	URL rocketImgUrl = null;
        	URL rocketLandedImgUrl = null;
        	URL rocketCrashedImgUrl = null;
        	URL rocketFireImgUrl = null;
        	
        	if(character_num == 1) {
            	rocketImgUrl = this.getClass().getResource("/resources/images/rocket.png");
            	rocketLandedImgUrl = this.getClass().getResource("/resources/images/rocket_landed.png");
            	rocketCrashedImgUrl = this.getClass().getResource("/resources/images/rocket_crashed.png");
            	rocketFireImgUrl = this.getClass().getResource("/resources/images/rocket_fire.png");
            }
            else if(character_num == 2) {
            	rocketImgUrl = this.getClass().getResource("/resources/images/right.png");
            	rocketLandedImgUrl = this.getClass().getResource("/resources/images/right_landed.png");
            	rocketCrashedImgUrl = this.getClass().getResource("/resources/images/right.crashed.png");
            	rocketFireImgUrl = this.getClass().getResource("/resources/images/rainbow.png");
            }
            else if(character_num == 3) {
            	rocketImgUrl = this.getClass().getResource("/resources/images/rocket2.png");
            	rocketLandedImgUrl = this.getClass().getResource("/resources/images/rocket2.landed.png");
            	rocketCrashedImgUrl = this.getClass().getResource("/resources/images/rocket2.crahsed.png");
            	rocketFireImgUrl = this.getClass().getResource("/resources/images/rocket2.fire.png");
            }
            else {
                rocketImgUrl = this.getClass().getResource("/resources/images/rocket.png");
                rocketLandedImgUrl = this.getClass().getResource("/resources/images/rocket_landed.png");
                rocketCrashedImgUrl = this.getClass().getResource("/resources/images/rocket_crashed.png");
                rocketFireImgUrl = this.getClass().getResource("/resources/images/rocket_fire.png");
             }
            
			rocketImg = ImageIO.read(rocketImgUrl);
            rocketImgWidth = rocketImg.getWidth();
            rocketImgHeight = rocketImg.getHeight();

            rocketLandedImg = ImageIO.read(rocketLandedImgUrl);

            rocketCrashedImg = ImageIO.read(rocketCrashedImgUrl);
            
            rocketFireImg = ImageIO.read(rocketFireImgUrl);

        }
        catch (IOException ex) {
            Logger.getLogger(PlayerRocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Here we set up the rocket when we starting a new game.
     */
    public void ResetPlayer()
    {
    	
    	if(character_num == 1)
    	{
    		hp=100;
    	}
    	else if(character_num == 2)
    	{
    		hp=150;
    		topLandingSpeed=6;
    	}
    	else if(character_num == 3)
    	{
    		hp=80;
    		topLandingSpeed=9;
    	}
        isLanded = false;
        isCrashed = false;
        x = random.nextInt(800 - rocketImgWidth);
        y = 10;    
        speedX = 0;
        speedY = 0;
    }
    /**
     * Here we move the rocket.
     */
    public void Draw(Graphics2D g2d)
    {
        g2d.setColor(Color.white);
        g2d.drawString("Rocket coordinates: " + x + " : " + y, 5, 15);
        g2d.drawString("1P-Rocket", x+2, y-7);
        g2d.drawString("HP:", 5, 40);
        g2d.setColor(Color.green);
        g2d.fillRect(30,30,hp,10);
        // If the rocket is landed.
        if(isLanded)
        {
            g2d.drawImage(rocketLandedImg, x, y, null);
        }
        // If the rocket is crashed.
        else if(isCrashed)
        {
            g2d.drawImage(rocketCrashedImg, x, y + rocketImgHeight - rocketCrashedImg.getHeight(), null);
        }
        // If the rocket is still in the space.
        else
        {	
            // If player hold down a W key we draw rocket fire.   	
            g2d.drawImage(rocketImg, x, y, null);
        	
        }
    }
    
}
