package player;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

import moon_lander.Canvas;
import moon_lander.Framework;
import moon_lander.StageBase;

/**
 * The space rocket with which player will have to land.
 * 
 * @author www.gametutorial.net
 */

public class PlayerEnemy extends JFrame implements GamePlayer {
    
    /**
     * We use this to generate a random number for starting x coordinate of the rocket.
     */
    private Random random;
 
    /**
     * X coordinate of the rocket.
     */
    private int x;
    /**
     * Y coordinate of the rocket.
     */
    private int y;

    /**
     * ������ ���� �ӵ�.
     */
    private int speedAccelerating;
    /**
     * ������ ����/�ϰ� �ӵ�. �ӵ��� �������� ������ �߷��� ������ �޷� ������� �����̴�.
     */
    private double speedStopping;
    
    /**
     * ���� �� �浹 ���� ������ ���� �� �ִ� �ִ� �ӵ��Դϴ�.
     */
    
    
    /**
     * x��ǥ�󿡼� ������ �󸶳� ������ ��� �������� �����̴°�?
     */
    private int speedX;
    /**
     * y��ǥ�󿡼� ������ �󸶳� ������ ��� �������� �����̴°�?
     */
    private int speedY;
            
    /**
     * Image of the rocket in air.
     */
    public BufferedImage enemyImg;
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
    public BufferedImage rainbowImg;
    
    
    
    /**
     * Width of rocket.
     */
    public int enemyImgWidth;
    /**
     * Height of rocket.
     */
    public int enemyImgHeight;
    
    public void setSpeedX(int speedX) {
    	this.speedX=speedX;
    }
    
    public int getSpeedX() {
    	return speedX;
    }
    
    public void setSpeedY(int speedY) {
    	this.speedY=speedY;
    }
    
    public int getSpeedY() {
    	return speedY;
    }
    
    public int getCoordinateX() {
    	return x;
    }
    
    public int getCoordinateY() {
    	return y;
    }
    
    public void setSpeedStopping(double speedStopping) {
    	this.speedStopping=speedStopping;
    }
    public void playerEnemyKeyEventIPress()
    {
    	speedY -= speedAccelerating;
    }
    public void playerEnemyKeyEventINotPress()
    {
    	speedY += speedStopping;
    }
    public void playerEnemyKeyEventJPress()
    {
    	speedX -= speedAccelerating;
    }
    public void playerEnemyKeyEventJNotPress()
    {
    	speedX += speedStopping;
    }
    public void playerEnemytKeyEventLPress()
    {
    	speedX += speedAccelerating;
    }
    public void playerEnemyKeyEventLNotPress()
    {
    	speedX -= speedStopping;
    }
    public void moveEnemy()
    {
    	x += speedX;
        y += speedY;
    }
    public PlayerEnemy()
    {
        Initialize();
        LoadContent();
        
        //���� rocketImgWidth�� �����Ƿ� ���� x ��ǥ�� �����մϴ�.
        x = random.nextInt(Framework.frameWidth - enemyImgWidth);
    }
    
    
    public void Initialize()
    {
        random = new Random();
        
        ResetPlayer();
       
        speedStopping = 1;
       
        speedAccelerating = 1;
        
        
       
       
    }
    
    public void LoadContent()
    {
        try
        {
            URL enemyImgUrl = this.getClass().getResource("/resources/images/right.png");
            enemyImg = ImageIO.read(enemyImgUrl);
            enemyImgWidth = enemyImg.getWidth();
            enemyImgHeight = enemyImg.getHeight();
            
            
            
            URL rainbowImgUrl = this.getClass().getResource("/resources/images/rainbow.png");
            rainbowImg = ImageIO.read(rainbowImgUrl);
           
            
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
        
        
        x = random.nextInt(Framework.frameWidth - enemyImgWidth);
        y = 200;
        
        speedX = 0;
        speedY = 0;
        
    }

    public void Draw(Graphics2D g2d)
    {
        g2d.setColor(Color.white);
        g2d.drawString("2P-Rocket coordinates: " + x + " : " + y, 5, 30);
        g2d.drawString("2P-Enemy", x+2, y-7);
        
    }
    
    
    
}