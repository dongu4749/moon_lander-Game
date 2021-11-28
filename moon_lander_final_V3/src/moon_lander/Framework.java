package moon_lander;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JRootPane;
import javax.swing.RepaintManager;
import javax.swing.SwingUtilities;

import moon_lander.Framework.GameState;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
/**
 * Framework that controls the game (Game.java) that created it, update it and draw it on the screen.
 * 
 * @author www.gametutorial.net
 */

public class Framework extends Canvas {
    
	
	//한번 실행되었을 때 닉네임 설정한 유저 수
	public static Integer Player_num = 0;
    private ImageCreate image = new ImageCreate();
    /**
     * Width of the frame.
     */
    public static int frameWidth;
    /**
     * Height of the frame.
     */
    public static int frameHeight;
    private Audio backgroundMusic;
    public static final long secInNanosec = 1000000000L;
    
    /**
     * Time of one millisecond in nanoseconds.
     * 1 millisecond = 1 000 000 nanoseconds
     */
    public static final long milisecInNanosec = 1000000L;
    
    /**
     * FPS - Frames per second
     * How many times per second the game should update?
     */
    private final int GAME_FPS = 16;
    /**
     * Pause between updates. It is in nanoseconds.
     */
    private final long GAME_UPDATE_PERIOD = secInNanosec / GAME_FPS;
    
    /**
     * Possible states of the game
     */
    public static enum GameState{STARTING, VISUALIZING, GAME_CONTENT_LOADING, MAIN_MENU, OPTIONS, PLAYING, GAMEOVER,PLAYER_SELECT,STATISTICS,PRODUCER_PAGE,HELP,CHARACTER_SELECT}
    /**
     * Current state of the game
     */
    public static GameState gameState;
    /**
     * Elapsed game time in nanoseconds.
     */
    public static long gameTime;
    public static long pauseTime;
    // It is used for calculating elapsed time.
    public static long lastTime;
    
    // The actual game
    public static Game game;
    private void AddButton()
    {
    	add(image.main_start);
    	add(image.statistics_button);
    	add(image.producer_button);
    	add(image.Help_button);
    	add(image.Hidden_button);
    	add(image.gotoMain);
    	add(image.gotoNextStage);
    	add(image.Restart);
    	add(image.Music_start_button);
    	add(image.Music_stop_button);
    	add(image.ONE_button);
    	add(image.TWO_button);
    	add(image.rocket1select);
    	add(image.rocket2select);
    	add(image.rocket3select);
    }
	private void Initialize()
    {
    	AddButton();
    	image.rocket1select.addMouseListener(new MouseAdapter() {
    		@Override
         	public void mousePressed(MouseEvent e) {
         		PlayerRocket.character_num = 1;
         		new RankingWriter();
         	}
         		
    	});
    	image.rocket2select.addMouseListener(new MouseAdapter() {
    		@Override
         	public void mousePressed(MouseEvent e) {
         		PlayerRocket.character_num = 2;
         		new RankingWriter();
         	}
         	
    	});
    	image.rocket3select.addMouseListener(new MouseAdapter() {
    		@Override
         	public void mousePressed(MouseEvent e) {
         		PlayerRocket.character_num = 3;
         		new RankingWriter();
         	}
         	
    	});
    	image.statistics_button.addMouseListener(new MouseAdapter() {
   		 @Override
         	public void mousePressed(MouseEvent e) {
   		    gameState = GameState.STATISTICS;
         	}
         	
   	 	}); 
    	image.main_start.addMouseListener(new MouseAdapter() {
         	@Override
         	public void mousePressed(MouseEvent e) {
         		gameState = GameState.PLAYER_SELECT;
         	}
         	
         });
    	image.ONE_button.addMouseListener(new MouseAdapter() {
          	@Override
          	public void mousePressed(MouseEvent e) {
          		Game.Mode = false;
          		gameState = GameState.CHARACTER_SELECT;
          	}
          	
          });
    	 image.TWO_button.addMouseListener(new MouseAdapter() {
          	@Override
          	public void mousePressed(MouseEvent e) {
          	    Game.Mode=true;	
          		newGame();
          	}
          	
          });
    	 image.producer_button.addMouseListener(new MouseAdapter() {
          	@Override
          	public void mousePressed(MouseEvent e) {
          		gameState = GameState.PRODUCER_PAGE;
          	}
          	
          });
    	 image.Help_button.addMouseListener(new MouseAdapter() {
           	@Override
           	public void mousePressed(MouseEvent e) {
           		gameState = GameState.HELP;
           	}
           	
           });
    	 image.Hidden_button.addMouseListener(new MouseAdapter() {
            	@Override
            	public void mousePressed(MouseEvent e) {
            		StageBase.stage_count = 6;
                	newGame();
            	}
            	
            });
    	 image.gotoMain.addMouseListener(new MouseAdapter() {
          	
          	@Override
          	public void mousePressed(MouseEvent e) {
          		StageBase.stage_count = 1;
            	gameState = GameState.MAIN_MENU;
          	}
          });
    	 image.gotoNextStage.addMouseListener(new MouseAdapter() {
           	
           	@Override
           	public void mousePressed(MouseEvent e) {
           		if(game.playerRocket.landed==true && StageBase.stage_count!=6)
           		StageBase.stage_count++;
           		restartGame();
           	}
           });
    	 image.Restart.addMouseListener(new MouseAdapter() {
           	
           	@Override
           	public void mousePressed(MouseEvent e) {
           		restartGame();
           	}
           });
    	image.Music_start_button.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		backgroundMusic.MusicResume();
        	}
        	
        });
    	image.Music_stop_button.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		backgroundMusic.Musicpause();
        	}
        });  	 
    }
    
    /**
     * Image for menu.
     */
    public Framework ()
    {
        super(); // Canvas() : 기본생성자 호출!
        
        gameState = GameState.VISUALIZING;
        
        //We start game in new thread.
        Thread gameThread = new Thread() {
            @Override
            public void run(){
                GameLoop();
            }
        };
        
        gameThread.start();
    }
    
    
   /**
     * Set variables and objects.
     * This method is intended to set the variables and objects for this class, variables and objects for the actual game can be set in Game.java.
     */
 
    
    /**
     * Load files - images, sounds, ...
     * This method is intended to load files for this class, files for the actual game can be loaded in Game.java.
     */
    private void LoadContent()
    {
        image.ImageLoadContent();
		backgroundMusic = new Audio("src/audio/bgm.wav",true);
		if(backgroundMusic.audioPlayingTrue())
		backgroundMusic.start();
    }
    
    /**
     * In specific intervals of time (GAME_UPDATE_PERIOD) the game/logic is updated and then the game is drawn on the screen.
     */
    private void GameLoop()
    {
    	
        // This two variables are used in VISUALIZING state of the game. We used them to wait some time so that we get correct frame/window resolution.
        long visualizingTime = 0, lastVisualizingTime = System.nanoTime();
        
        // This variables are used for calculating the time that defines for how long we should put threat to sleep to meet the GAME_FPS.
        long beginTime, timeTaken, timeLeft;
        boolean first_in = false;
        
        while(true)
        {
            beginTime = System.nanoTime();
            
            switch (gameState)
            {
                case PLAYING:
                    
                    if(Option.pause == false)
                    {
                     gameTime += System.nanoTime() - lastTime;
                     game.UpdateGame(gameTime, mousePosition());
                    
                     lastTime = System.nanoTime();
                    }
                    else if(Option.pause == true)
                    {
                    pauseTime += System.nanoTime() - lastTime;
                    game.UpdateGame(gameTime, mousePosition());
                    
                    lastTime = System.nanoTime();
                    }
                    image.ButtonHidden();
                    
                    first_in = true;
                break;
                case GAMEOVER:
                	if(game.playerRocket.landed==false)
                	{
                		image.Restart.setVisible(true);
                		image.gotoNextStage.setVisible(false);
                	}
                	 if(StageBase.stage_count==6)
                	 {
                		 if(game.playerRocket.landed==true)
                		 {
                			 image.Restart.setVisible(false);
                		 }
                	 }
                 	if(first_in == true && game.playerRocket.landed == true) {
                 		RankingCalculation.Save_Score(StageBase.stage_count, StageBase.Time_Score,PlayerRocket.hp); 
                 		// 5레벨에서 깨도 한번 ++되어서 ==6으로 바꿈.
                 		if(StageBase.stage_count == 5) {
                 			RankingCalculation.Add_Score();
                 			RankingCalculation.Cal_Ranking();
                 			RankingWriter.Update_Ranking();
                 		}
                 	first_in = false;
                 	}
                break;
                case STATISTICS:
                	image.ButtonHidden();
                	new RankingViewer();
                    gameState = GameState.MAIN_MENU;
                break;
                case STARTING:
                    // Sets variables and objects.
                    Initialize();
                    // Load files - images, sounds, ...
                    LoadContent();
                    
                    // When all things that are called above finished, we change game status to main menu.
                    gameState = GameState.MAIN_MENU;
                break;
                case PRODUCER_PAGE:
                	image.gotoMain.setVisible(true);
                	break;
                	
                case HELP:
                	image.gotoMain.setVisible(true);
                	break;
                case VISUALIZING:
                    // On Ubuntu OS (when I tested on my old computer) this.getWidth() method doesn't return the correct value immediately (eg. for frame that should be 800px width, returns 0 than 790 and at last 798px). 
                    // So we wait one second for the window/frame to be set to its correct size. Just in case we
                    // also insert 'this.getWidth() > 1' condition in case when the window/frame size wasn't set in time,
                    // so that we although get approximately size.
                    if(this.getWidth() > 1 && visualizingTime > secInNanosec)
                    {
                        frameWidth = this.getWidth();
                        frameHeight = this.getHeight();

                        // When we get size of frame we change status.
                        gameState = GameState.STARTING;
                    }
                    else
                    {
                        visualizingTime += System.nanoTime() - lastVisualizingTime;
                        lastVisualizingTime = System.nanoTime();
                    }
                break;
            }
            
            // Repaint the screen.
            repaint();
            
            // Here we calculate the time that defines for how long we should put threat to sleep to meet the GAME_FPS.
            timeTaken = System.nanoTime() - beginTime;
            timeLeft = (GAME_UPDATE_PERIOD - timeTaken) / milisecInNanosec; // In milliseconds
            
            // If the time is less than 10 milliseconds, then we will put thread to sleep for 10 millisecond so that some other thread can do some work.
            if (timeLeft < 10) 
                timeLeft = 10; //set a minimum
            
            try {
                 //Provides the necessary delay and also yields control so that other thread can do work.
                 Thread.sleep(timeLeft);
            } catch (InterruptedException ex) { }
        }
    }
   
   
    /**
     * Draw the game to the screen. It is called through repaint() method in GameLoop() method.
     */
    @Override
    public void Draw(Graphics2D g2d)
    {
        switch (gameState)
        {
            case PLAYING:
            	StageBase stagebase = new StageBase();
    			stagebase.call_BackgroungImage();
    			
            	for(int i=2;i<=5;i++)
            	{
            		for(int j=2;j<=5;j++)
            			if(StageBase.stage_count==j)
            			{
            				double speed=1.2;
            		        game.playerRocket.setSpeedStopping(speed);
            		        speed += 0.2;	
            			}
            	}
            	if(StageBase.stage_count==6)
    				game.playerRocket.setSpeedStopping(3);
                StageBase.Draw(g2d, mousePosition());
               
            break;
            case PRODUCER_PAGE:
            	image.ButtonHidden();
            	image.gotoMain.setVisible(true);
            	g2d.drawImage(image.ProduerPageImg, 0, 0, frameWidth, frameHeight, null);
            	
            break;
            
            case HELP:
            	image.ButtonHidden();
            	image.gotoMain.setVisible(true);
            	g2d.drawImage(image.Help_pageImg, 0, 0, frameWidth, frameHeight, null);
            	
            	break;
            case PLAYER_SELECT:
            	image.ButtonHidden();
            	image.gotoMain.setVisible(true);
            	image.ONE_button.setVisible(true);
            	image.TWO_button.setVisible(true);
            	image.Music_start_button.setVisible(true);
            	image.Music_stop_button.setVisible(true);
            	image.gotoMain.setVisible(true);
            	image.setMusicButton();
            	image.SetPlayerButton();
            	g2d.drawImage(image.PLAYER_SELECTPAGEImg, 0, 0, frameWidth, frameHeight, null);
   
            	break;
            case CHARACTER_SELECT:
            	image.ButtonHidden();
            	image.rocket1select.setVisible(true);
            	image.rocket2select.setVisible(true);
            	image.rocket3select.setVisible(true);
            	image.SetRocketButton();
            	g2d.drawImage(image.Character_SELECTPAGEImg, 0, 0, frameWidth, frameHeight, null);
            	
            break;	
            case GAMEOVER:
            	image.gotoMain.setVisible(true);
            	if(game.playerRocket.landed == true)
            	image.gotoNextStage.setVisible(true);
            	image.Music_start_button.setVisible(true);
            	image.Music_stop_button.setVisible(true);
            	image.SetGameOverButton();
            	image.setMusicButton();
            	StageBase.DrawGameOver(g2d, mousePosition(), gameTime,pauseTime);
            	
            break;
            case MAIN_MENU:
            	image.ButtonHidden();
            	image.MainButtonVisible();
            	image.SetMainButton();
            	image.setMusicButton();
                g2d.drawImage(image.moonLanderMenuImg, 0, 0, frameWidth, frameHeight, null);
                g2d.setColor(Color.green);
                g2d.drawString("WWW.GAMETUTORIAL.NET", 7, frameHeight-120);
                g2d.drawString("Best Score:"+" "+ RankingCalculation.Final_Score[0][1], 7, frameHeight - 140);
               
            break;

            case GAME_CONTENT_LOADING:
            	image.ButtonHidden();
                g2d.setColor(Color.white);
                g2d.drawString("GAME is LOADING", frameWidth / 2 - 50, frameHeight / 2);
            break;
        }
    }
   
    
    /**
     * Starts new game.
     */
    public static void newGame()  {
        // We set gameTime to zero and lastTime to current time for later calculations.
        gameTime = 0;
        pauseTime = 0;
        lastTime = System.nanoTime();
        
        game = new Game();
    }
    
    /**
     *  Restart game - reset game time and call RestartGame() method of game object so that reset some variables.
     */
    private void restartGame()
    {
        // We set gameTime to zero and lastTime to current time for later calculations.
        gameTime = 0;
        lastTime = System.nanoTime();
        pauseTime=0;
        game.RestartGame();
        
        // We change game status so that the game can start.
        gameState = GameState.PLAYING;
        
    }
    
    /**
     * Returns the position of the mouse pointer in game frame/window.
     * If mouse position is null than this method return 0,0 coordinate.
     * 
     * @return Point of mouse coordinates.
     */
    private Point mousePosition()
    {
        try
        {
            Point mp = this.getMousePosition();
            
            if(mp != null)
                return this.getMousePosition();
            else
                return new Point(0, 0);
        }
        catch (Exception e)
        {
            return new Point(0, 0);
        }
    }
    
    /**
     * This method is called when keyboard key is released.
     * 
     * @param e KeyEvent
     */
        
    /**
     * This method is called when mouse button is clicked.
     * 
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        
    }
	@Override
	public void keyReleasedFramework(KeyEvent e) {
		// TODO 자동 생성된 메소드 스텁
		
	}
}
