package moon_lander;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;

/**
 * Actual game.
 * 
 * @author www.gametutorial.net
 */

public class Game {
	
	
	   
	    
    /**
     * The space rocket with which player will have to land.
     */
    public PlayerRocket playerRocket;
	public PlayerEnemy playerEnemy;
	public static boolean Mode = false;
    /**
     * Landing area on which rocket will have to land.
     */
    public LandingArea landingArea;
    public Obstacle obstacle;
    public Obstacle obstacle1;
    public MovingObstacle movingobstacle;
    public MovingObstacle movingobstacle1;
    public RankingWriter ranking;
    public Option option;

    public Game()
    {
        Framework.gameState = Framework.GameState.GAME_CONTENT_LOADING;
        
        Thread threadForInitGame = new Thread() {
            @Override
            public void run(){
                // Sets variables and objects for the game.
                Initialize();
                // Load game files (images, sounds, ...)
                LoadContent();
                
                Framework.gameState = Framework.GameState.PLAYING;
                
            }
        };
        threadForInitGame.start();
    }
   

   /**
     * Set variables and objects for the game.
     */
    private void Initialize()
    {
    	
    	playerRocket = new PlayerRocket();
    	option = new Option();
    	if(Mode==true)
    	{
    	playerEnemy = new PlayerEnemy();
    	}
    	
    	
    	landingArea  = new LandingArea();
        obstacle = new Obstacle();
        obstacle1 = new Obstacle();
        movingobstacle = new MovingObstacle();
        movingobstacle1 = new MovingObstacle();
    }
    
    /**
     * Load game files - images, sounds, ...
     */
    private void LoadContent()
    {
	        StageBase stagebase = new StageBase();
			stagebase.call_BackgroungImage();	
    }
    
    /**
     * Restart game - reset some variables.
     */
    public void RestartGame()
    {
		/* playerEnemy.ResetPlayer(); */
    	
        playerRocket.ResetPlayer();
    	
    	if(Mode==true)
    	{
        playerEnemy.ResetPlayer();
    	}
    	
        obstacle.ResetObstacle();
        obstacle1.ResetObstacle();
        movingobstacle.ResetObstacle();
        movingobstacle1.ResetObstacle();
        
    }
    
    public void landingAreaValue()
    {
    	int landingAreaArrayList[]= {landingArea.landingAreaImgWidth,landingArea.landingAreaImgWidth2,landingArea.landingAreaImgWidth3,landingArea.landingAreaImgWidth4,landingArea.landingAreaImgWidth5};
        for(int i=0; i<5;i++)
        {
        	int AreaValue = landingAreaArrayList[i];
        	for(int j=1;j<=6;j++)
        	if(StageBase.stage_count==j) 
        	{
            if((playerRocket.getCoordinateX() > landingArea.x) && (playerRocket.getCoordinateX() < landingArea.x + landingAreaArrayList[i] - playerRocket.rocketImgWidth))
            
            {
                // Here we check if the rocket speed isn't too high.
                if(playerRocket.getSpeedY() <= playerRocket.getTopLandingSpeed()) {
                    playerRocket.landed = true;

                }
                else
                    playerRocket.crashed = true;
            }
            else
                playerRocket.crashed = true;
        	}
        	
        }
    }
    public void RectangleCrashed()
    {
    	playerRocket.hp -= 10;
     	if(playerRocket.hp ==0)
     	{
        playerRocket.crashed = true;
        Framework.gameState = Framework.GameState.GAMEOVER;
     	}
    }
    public void RectangleOver()
    {
    	 Rectangle rocketRectangle = new Rectangle(playerRocket.getCoordinateX(),playerRocket.getCoordinateY(),playerRocket.rocketImg.getWidth(),playerRocket.rocketImg.getHeight());
         Rectangle obstacleRectangle = new Rectangle(obstacle.getCoordinateX(),obstacle.getCoordinateY(),obstacle.obstacleImg.getWidth(),obstacle.obstacleImg.getHeight());
         if(rocketRectangle.intersects(obstacleRectangle)) 
         {
        	 RectangleCrashed();
         }
         Rectangle rocketRectangle2 = new Rectangle(playerRocket.getCoordinateX(),playerRocket.getCoordinateY(),playerRocket.rocketImg.getWidth(),playerRocket.rocketImg.getHeight());
         Rectangle obstacleRectangle2 = new Rectangle(obstacle1.getCoordinateX(),obstacle1.getCoordinateY(),obstacle1.obstacleImg.getWidth(),obstacle1.obstacleImg.getHeight());
         if(rocketRectangle2.intersects(obstacleRectangle2)) 
         {
        	 RectangleCrashed();
         }
        
         Rectangle movingobstacleRectangle = new Rectangle(movingobstacle.getCoordinateX(),movingobstacle.getCoordinateY(),movingobstacle.movingobstacleImg.getWidth(),movingobstacle.movingobstacleImg.getHeight());
         Rectangle movingobstacleRectangle2 = new Rectangle(movingobstacle1.getCoordinateX(),movingobstacle1.getCoordinateY(),movingobstacle1.movingobstacleImg.getWidth(),movingobstacle.movingobstacleImg.getHeight());
         if(rocketRectangle.intersects(movingobstacleRectangle)) 
         {
        	 RectangleCrashed();
         }
        
         if(rocketRectangle.intersects(movingobstacleRectangle2)) 
         {
        	 RectangleCrashed();
         }
         if(Mode==true)
         {
     Rectangle enemyRectangle = new Rectangle(playerEnemy.getCoordinateX(),playerEnemy.getCoordinateY(),playerEnemy.enemyImg.getWidth(),playerEnemy.enemyImg.getHeight());
         {
         	 if(enemyRectangle.intersects(rocketRectangle)) 
         	 {
                  playerRocket.crashed = true;
                  Framework.gameState = Framework.GameState.GAMEOVER;
             }
         }
         }
    }
    /**
     * Update game logic.
     * 
     * @param gameTime gameTime of the game.
     * @param mousePosition current mouse position.
     */
    public void UpdateGame(long gameTime, Point mousePosition)
    {
        // Move the rocket
    	
    	playerRocket.Update();
    	if(Mode == true)
    	{
    	playerEnemy.Update();
    	}
    	movingobstacle.Update();
        movingobstacle1.Update();
        if(movingobstacle.getCoordinateX()>800)
        {
        	movingobstacle.ResetObstacle();
        }
        if(movingobstacle1.getCoordinateX()>800)
        {
        	movingobstacle1.ResetObstacle();
        }
        
        
        // Checks where the player rocket is. Is it still in the space or is it landed or crashed?
        // First we check bottom y coordinate of the rocket if is it near the landing area.
        if(playerRocket.getCoordinateY() + playerRocket.rocketImgHeight - 10 > landingArea.y)
        {
            // Here we check if the rocket is over landing area.
        	landingAreaValue();
            Framework.gameState = Framework.GameState.GAMEOVER;
        }
        RectangleOver();
    }
    }
