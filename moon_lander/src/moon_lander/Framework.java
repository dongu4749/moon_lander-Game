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

    /**
     * Width of the frame.
     */
    public static int frameWidth;
    /**
     * Height of the frame.
     */
    public static int frameHeight;
    private Audio backgroundMusic;
    private PlayerRocket playerrocket;
    /**
     * Time of one second in nanoseconds.
     * 1 second = 1 000 000 000 nanoseconds
     */
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
    public static enum GameState{STARTING, VISUALIZING, GAME_CONTENT_LOADING, MAIN_MENU,PAUSE, OPTIONS, PLAYING, GAMEOVER,PLAYER_SELECT, DESTROYED,STATISTICS,HIDDEN_STAGE,PRODUCER_PAGE,HELP}
    /**
     * Current state of the game
     */
    public static GameState gameState;
    
    /**
     * Elapsed game time in nanoseconds.
     */
    public static long gameTime;
    public static long PauseTime;
    // It is used for calculating elapsed time.
    public static long lastTime;
    
    // The actual game
    private static Game game;
   
    private void AddButton()
    {
    	add(main_start);	
    	add(exit_button);
    	add(statistics_button);
    	add(producer_button);
    	add(Help_button);
    	add(Hidden_button);
    	add(gotoMain);
    	add(Restart);
    	add(Music_start_button);
    	add(Music_stop_button);
    	add(ONE_button);
    	add(TWO_button);
    }
    private void Initialize()
    {
    	AddButton();
    	statistics_button.addMouseListener(new MouseAdapter() {
   		 @Override
         	public void mousePressed(MouseEvent e) {
   		    gameState = GameState.STATISTICS;
         	}
         	@Override
         	public void mouseEntered(MouseEvent e) {
         		statistics_button.setCursor(new Cursor(Cursor.HAND_CURSOR));	
         	}
         	@Override
         	public void mouseExited(MouseEvent e) {
         		statistics_button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         	}
   	 	}); 
    	main_start.addMouseListener(new MouseAdapter() {
         	@Override
         	public void mousePressed(MouseEvent e) {
         		Playerselect();
         	}
         	@Override
         	public void mouseEntered(MouseEvent e) {
         		main_start.setCursor(new Cursor(Cursor.HAND_CURSOR));	
         	}
         	@Override
         	public void mouseExited(MouseEvent e) {
         		main_start.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         	}
         });
    	 ONE_button.addMouseListener(new MouseAdapter() {
          	@Override
          	public void mousePressed(MouseEvent e) {
          		Game.Mode = false;
          		new Ranking();
          	}
          	@Override
          	public void mouseEntered(MouseEvent e) {
          		 ONE_button.setCursor(new Cursor(Cursor.HAND_CURSOR));	
          	}
          	@Override
          	public void mouseExited(MouseEvent e) {
          		 ONE_button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
          	}
          });
    	 TWO_button.addMouseListener(new MouseAdapter() {
          	@Override
          	public void mousePressed(MouseEvent e) {
          	    Game.Mode=true;	
          		newGame();
          	}
          	@Override
          	public void mouseEntered(MouseEvent e) {
          		TWO_button.setCursor(new Cursor(Cursor.HAND_CURSOR));	
          	}
          	@Override
          	public void mouseExited(MouseEvent e) {
          		TWO_button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
          	}
          });
    	 exit_button.addMouseListener(new MouseAdapter() {
         	@Override
         	public void mouseEntered(MouseEvent e) {
         		exit_button.setCursor(new Cursor(Cursor.HAND_CURSOR));	
         	}
         	@Override
         	public void mouseExited(MouseEvent e) {
         		exit_button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
         	}
         	@Override
         	public void mousePressed(MouseEvent e) {
         		System.exit(0);
         	}
         });
    	 producer_button.addMouseListener(new MouseAdapter() {
          	@Override
          	public void mousePressed(MouseEvent e) {
          		Producer();
          	}
          	@Override
          	public void mouseEntered(MouseEvent e) {
          		producer_button.setCursor(new Cursor(Cursor.HAND_CURSOR));	
          	}
          	@Override
          	public void mouseExited(MouseEvent e) {
          		producer_button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
          	}
          });
    	 Help_button.addMouseListener(new MouseAdapter() {
           	@Override
           	public void mousePressed(MouseEvent e) {
           		Help();
           	}
           	@Override
           	public void mouseEntered(MouseEvent e) {
           	 Help_button.setCursor(new Cursor(Cursor.HAND_CURSOR));	
           	}
           	@Override
           	public void mouseExited(MouseEvent e) {
           	 Help_button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
           	}
           });
    	 Hidden_button.addMouseListener(new MouseAdapter() {
            	@Override
            	public void mousePressed(MouseEvent e) {
            		Hidden();
            	}
            	@Override
            	public void mouseEntered(MouseEvent e) {
            		Hidden_button.setCursor(new Cursor(Cursor.HAND_CURSOR));	
            	}
            	@Override
            	public void mouseExited(MouseEvent e) {
            		Hidden_button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            	}
            });
    	 gotoMain.addMouseListener(new MouseAdapter() {
          	@Override
          	public void mouseEntered(MouseEvent e) {
          		gotoMain.setCursor(new Cursor(Cursor.HAND_CURSOR));	
          	}
          	@Override
          	public void mouseExited(MouseEvent e) {
          		gotoMain.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
          	}
          	@Override
          	public void mousePressed(MouseEvent e) {
          		mainMenu();
          	}
          });
    	Restart.addMouseListener(new MouseAdapter() {
           	@Override
           	public void mouseEntered(MouseEvent e) {
           		Restart.setCursor(new Cursor(Cursor.HAND_CURSOR));	
           	}
           	@Override
           	public void mouseExited(MouseEvent e) {
           		Restart.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
           	}
           	@Override
           	public void mousePressed(MouseEvent e) {
           		Restartgame();
           	}
           });
    	Music_start_button.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		backgroundMusic.MusicResume();
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		Music_start_button.setCursor(new Cursor(Cursor.HAND_CURSOR));	
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		Music_start_button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        	}
        });
    	Music_stop_button.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mousePressed(MouseEvent e) {
        		backgroundMusic.Musicpause();
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		Music_stop_button.setCursor(new Cursor(Cursor.HAND_CURSOR));	
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		Music_stop_button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        	}
        });
    	 
    }
   
    
    /**
     * Image for menu.
     */
    private BufferedImage moonLanderMenuImg;
    private BufferedImage main_startImg;
    private BufferedImage exit_buttonImg;
    private BufferedImage statistics_buttonImg;
    private BufferedImage Help_buttonImg;
    private BufferedImage producer_buttonImg;
    private BufferedImage ProduerPageImg;
    private BufferedImage HelpImg;
    private BufferedImage Help_pageImg;
    private BufferedImage Hidden_buttonImg;
    
    private BufferedImage ONE_buttonImg;
    private BufferedImage TWO_buttonImg;
    private BufferedImage PLAYER_SELECTPAGEImg;
    private BufferedImage MusicPauseImg;
    private BufferedImage MusicPause2Img;
    JButton main_start = new JButton(new ImageIcon(this.getClass().getResource("/resources/images/start_button.png")));
    JButton exit_button = new JButton(new ImageIcon(this.getClass().getResource("/resources/images/exit_button.png")));
    JButton statistics_button = new JButton(new ImageIcon(this.getClass().getResource("/resources/images/statistics_button.png")));
    JButton producer_button = new JButton(new ImageIcon(this.getClass().getResource("/resources/images/producer_button.png")));
    JButton Help_button = new JButton(new ImageIcon(this.getClass().getResource("/resources/images/Help_button.png")));
    JButton Hidden_button = new JButton(new ImageIcon(this.getClass().getResource("/resources/images/Hidden_button.png")));
   
    JButton Music_start_button = new JButton(new ImageIcon(this.getClass().getResource("/resources/images/audio_start.png")));
    JButton Music_stop_button = new JButton(new ImageIcon(this.getClass().getResource("/resources/images/audio_stop.png")));
    JButton ONE_button = new JButton(new ImageIcon(this.getClass().getResource("/resources/images/1P_button.png")));
    JButton TWO_button = new JButton(new ImageIcon(this.getClass().getResource("/resources/images/2P_button.png")));
    JButton gotoMain = new JButton("go to Main Manu");
    
    JButton Restart = new JButton("Restart");
    
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
        try
        {
            URL moonLanderMenuImgUrl = this.getClass().getResource("/resources/images/menu.jpg");
            moonLanderMenuImg = ImageIO.read(moonLanderMenuImgUrl);
            
            URL main_startImgUrl = this.getClass().getResource("/resources/images/start_button.png");
            main_startImg = ImageIO.read(main_startImgUrl);
            
            URL exit_buttonImgUrl = this.getClass().getResource("/resources/images/exit_button.png");
            exit_buttonImg = ImageIO.read(exit_buttonImgUrl);
            
            URL statistics_buttonImgUrl = this.getClass().getResource("/resources/images/statistics_button.png");
            statistics_buttonImg = ImageIO.read(statistics_buttonImgUrl);
            
            URL producer_buttonImgUrl = this.getClass().getResource("/resources/images/producer_button.png");
            producer_buttonImg = ImageIO.read(producer_buttonImgUrl);
            
            URL Help_buttonImgUrl = this.getClass().getResource("/resources/images/Help_button.png");
            Help_buttonImg = ImageIO.read(Help_buttonImgUrl);
            
            URL ProduerPageImgUrl = this.getClass().getResource("/resources/images/produer_page.png");
        	ProduerPageImg = ImageIO.read(ProduerPageImgUrl);
        	
        	URL Help_pageImgUrl = this.getClass().getResource("/resources/images/Help_page.PNG");
        	Help_pageImg = ImageIO.read(Help_pageImgUrl);
        	
        	URL Hidden_buttonImgUrl = this.getClass().getResource("/resources/images/Hidden_button.png");
        	Hidden_buttonImg = ImageIO.read(Hidden_buttonImgUrl);
        	
        	
        	
        	URL Music_start_buttonImgUrl = this.getClass().getResource("/resources/images/audio_start.png");
        	MusicPauseImg = ImageIO.read(Music_start_buttonImgUrl);
            
        	URL Music_stop_buttonImgUrl = this.getClass().getResource("/resources/images/audio_stop.png");
        	MusicPause2Img = ImageIO.read(Music_stop_buttonImgUrl);
        	
        	
        	URL ONE_buttonImgUrl = this.getClass().getResource("/resources/images/1P_button.png");
        	ONE_buttonImg = ImageIO.read(ONE_buttonImgUrl);
            
        	URL TWO_buttonImgUrl = this.getClass().getResource("/resources/images/2P_button.png");
        	TWO_buttonImg = ImageIO.read(TWO_buttonImgUrl);
        	
        	URL PLAYER_SELECTPAGEImgUrl = this.getClass().getResource("/resources/images/background1.jpg");
        	PLAYER_SELECTPAGEImg = ImageIO.read(PLAYER_SELECTPAGEImgUrl);
        	
        	backgroundMusic = new Audio("src/audio/bgm2.wav",true);
        	if(backgroundMusic.AudioPlayingTrue())
            backgroundMusic.start();
            
        }
        catch (IOException ex) {
            Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        int num = 0;
        
        while(true)
        {
            beginTime = System.nanoTime();
            
            switch (gameState)
            {
                case PLAYING:
                    
                    if(PlayerRocket.pause == false)
                    {
                     gameTime += System.nanoTime() - lastTime;
                     game.UpdateGame(gameTime, mousePosition());
                    
                     lastTime = System.nanoTime();
                    }
                    else if(PlayerRocket.pause == true)
                    {
                    PauseTime += System.nanoTime() - lastTime;
                    game.UpdateGame(gameTime, mousePosition());
                    
                    lastTime = System.nanoTime();
                    }
                    
                    
                   
                    main_start.setVisible(false);
                    exit_button.setVisible(false);
                    producer_button.setVisible(false);
                    statistics_button.setVisible(false);
                    Help_button.setVisible(false);
                    gotoMain.setVisible(false);
                    Hidden_button.setVisible(false);
                    Music_start_button.setVisible(false);
                    Music_stop_button.setVisible(false);
                    ONE_button.setVisible(false);
                    TWO_button.setVisible(false);
                    Restart.setVisible(false);
                    
                    num = 0;
                break;
                case GAMEOVER:
                	gotoMain.setVisible(true);
                	if(PlayerRocket.landed==false)
                	 Restart.setVisible(true);
                	 if(StageBase.stage_count==6)
                	 {
                		 if(PlayerRocket.landed==true)
                		 {
                			 Restart.setVisible(false);
                		 }
                	 }
                	 Music_start_button.setVisible(true);
                     Music_stop_button.setVisible(true);
                     num++;
                 	if(num == 1 && PlayerRocket.landed == true) {
                 		Player.Save_Score(StageBase.stage_count, StageBase.Score); 
                 		StageBase.stage_count++;
                 		// 5레벨에서 깨도 한번 ++되어서 ==6으로 바꿈.
                 		if(StageBase.stage_count == 6) {
                 			Player.Add_Score();
                 			Player.Cal_Ranking();
                 			Ranking.Update_Ranking();
                 		}
                 	}
                break;
                case STATISTICS:
                	new RankingViewer();
                    gameState = GameState.MAIN_MENU;
                break;
                case PAUSE:
                	
                case HELP:
                	main_start.setVisible(false);
                    exit_button.setVisible(false);
                    producer_button.setVisible(false);
                    statistics_button.setVisible(false);
                    Help_button.setVisible(false);
                    gotoMain.setVisible(true);	
                    Hidden_button.setVisible(false);
                    Music_start_button.setVisible(false);
                    Music_stop_button.setVisible(false);
                    ONE_button.setVisible(false);
                    TWO_button.setVisible(false);
                    Restart.setVisible(false);
                	break;
                case HIDDEN_STAGE:
                	
                case PRODUCER_PAGE:
                	main_start.setVisible(false);
                    exit_button.setVisible(false);
                    producer_button.setVisible(false);
                    statistics_button.setVisible(false);
                    Help_button.setVisible(false);
                    Hidden_button.setVisible(false);
                    Music_start_button.setVisible(false);
                    Music_stop_button.setVisible(false);
                    ONE_button.setVisible(false);
                    TWO_button.setVisible(false);
                    Restart.setVisible(false);
                    break;
                
                case MAIN_MENU:
                	main_start.setVisible(true);
                	exit_button.setVisible(true);
                	producer_button.setVisible(true);
                	Help_button.setVisible(true);
                	statistics_button.setVisible(true);
                	gotoMain.setVisible(false);
                	Restart.setVisible(false);
                	Hidden_button.setVisible(true);
                	Music_start_button.setVisible(true);
                    Music_stop_button.setVisible(true);
                    ONE_button.setVisible(false);
                    TWO_button.setVisible(false);
                    //...
                break;
                case OPTIONS:
                    //...
                break;
                case GAME_CONTENT_LOADING:
                    //...
                break;
                case PLAYER_SELECT:
                	ONE_button.setVisible(true);
                    TWO_button.setVisible(true);
                  
                    main_start.setVisible(false);
                    exit_button.setVisible(false);
                    producer_button.setVisible(false);
                    statistics_button.setVisible(false);
                    Help_button.setVisible(false);
                    Hidden_button.setVisible(false);
                    Restart.setVisible(false);
                    Music_start_button.setVisible(true);
                    Music_stop_button.setVisible(true);
                break;
                case STARTING:
                    // Sets variables and objects.
                    Initialize();
                    // Load files - images, sounds, ...
                    LoadContent();
                    
                    // When all things that are called above finished, we change game status to main menu.
                    gameState = GameState.MAIN_MENU;
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
   
    
    public void Producer()
    {
    	gameState = GameState.PRODUCER_PAGE;
    }
    public void Restartgame()
    {
    	restartGame();
    }
    
    public void Help()
    {
    	gameState = GameState.HELP;
    }
    public void mainMenu()
    {
    	StageBase.stage_count = 1;
    	gameState = GameState.MAIN_MENU;
    }
    public void Playerselect()
    {
    	gameState = GameState.PLAYER_SELECT;
    }
	    public void Hidden()
	    {
    	StageBase.stage_count = 99;
    	newGame();
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
            	if(StageBase.stage_count==2) {
            		playerrocket.speedStopping= 1.2;
    				Stage2 stage2 = new Stage2();
    				stage2.BackgroundImage();
            	}
            	else if(StageBase.stage_count==3) {
            		playerrocket.speedStopping= 1.4;
            		Stage3 stage3 = new Stage3();
    				stage3.BackgroundImage();
            	}
            	else if(StageBase.stage_count==4) {
            		playerrocket.speedStopping= 1.6;
            		Stage4 stage4 = new Stage4();
    				stage4.BackgroundImage();
            	}
            	else if(StageBase.stage_count==5) {
            		playerrocket.speedStopping= 1.8;
            		Stage5 stage5 = new Stage5();
    				stage5.BackgroundImage();
            	}
            	else if(StageBase.stage_count==99) {
            		playerrocket.speedStopping= 7;
            		Stage6 stage6 = new Stage6();
    				stage6.BackgroundImage();
            	}
            
                StageBase.Draw(g2d, mousePosition());
                Music_start_button.setBounds(700,30,Music_start_button.getWidth(),Music_start_button.getHeight());
     			Music_start_button.setContentAreaFilled(false);
     			Music_start_button.setBorderPainted(false);
     			Music_stop_button.setBounds(750,30,Music_start_button.getWidth(),Music_start_button.getHeight());
     			Music_stop_button.setContentAreaFilled(false);
     			Music_stop_button.setBorderPainted(false);
            break;
            case PRODUCER_PAGE:
            	g2d.drawImage(ProduerPageImg, 0, 0, frameWidth, frameHeight, null);
            	gotoMain.setVisible(true);
            break;
            
           
            
            case HELP:
            	g2d.drawImage(Help_pageImg, 0, 0, frameWidth, frameHeight, null);
            	gotoMain.setVisible(true);
            	break;
            case PLAYER_SELECT:
            	g2d.drawImage(PLAYER_SELECTPAGEImg, 0, 0, frameWidth, frameHeight, null);
            	ONE_button.setBounds(frameWidth/2-150,frameHeight/2+150,ONE_button.getWidth(),ONE_button.getHeight());
            	ONE_button.setContentAreaFilled(false);
            	ONE_button.setBorderPainted(false);
            	TWO_button.setBounds(frameWidth/2,frameHeight/2+150,TWO_button.getWidth(),TWO_button.getHeight());
            	TWO_button.setContentAreaFilled(false);
            	TWO_button.setBorderPainted(false);
            	gotoMain.setVisible(true);
            	Music_start_button.setBounds(700,30,Music_start_button.getWidth(),Music_start_button.getHeight());
     			Music_start_button.setContentAreaFilled(false);
     			Music_start_button.setBorderPainted(false);
     			Music_stop_button.setBounds(750,30,Music_start_button.getWidth(),Music_start_button.getHeight());
     			Music_stop_button.setContentAreaFilled(false);
     			Music_stop_button.setBorderPainted(false);
            	
            	break;
            case GAMEOVER:
            	StageBase.DrawGameOver(g2d, mousePosition(), gameTime,PauseTime);
            	gotoMain.setBounds(frameWidth / 2 - 117, frameHeight / 2 + 80, 200, 100);
                gotoMain.setContentAreaFilled(false);
                gotoMain.setBorderPainted(false);
                gotoMain.setForeground(Color.white);
                Restart.setBounds(frameWidth / 2 - 117, frameHeight / 2, 200, 100);
                Restart.setContentAreaFilled(false);
                Restart.setBorderPainted(false);
                Restart.setForeground(Color.white);
                Music_start_button.setBounds(700,30,Music_start_button.getWidth(),Music_start_button.getHeight());
     			Music_start_button.setContentAreaFilled(false);
     			Music_start_button.setBorderPainted(false);
     			Music_stop_button.setBounds(750,30,Music_start_button.getWidth(),Music_start_button.getHeight());
     			Music_stop_button.setContentAreaFilled(false);
     			Music_stop_button.setBorderPainted(false);
            break;
            case MAIN_MENU:
            	
            	main_start.setBounds(frameWidth/2-300,frameHeight/2+150,main_start.getWidth(),main_start.getHeight());
     			main_start.setContentAreaFilled(false);
     			main_start.setBorderPainted(false);
     			exit_button.setBounds(frameWidth/2-200,frameHeight/2+150,exit_button.getWidth(),exit_button.getHeight());
     			exit_button.setContentAreaFilled(false);
     			exit_button.setBorderPainted(false);
     			producer_button.setBounds(frameWidth/2-100,frameHeight/2+150,producer_button.getWidth(),producer_button.getHeight());
     			producer_button.setContentAreaFilled(false);
     			producer_button.setBorderPainted(false);
     			statistics_button.setBounds(frameWidth/2,frameHeight/2+150,statistics_button.getWidth(),statistics_button.getHeight());
     			statistics_button.setContentAreaFilled(false);
     			statistics_button.setBorderPainted(false);
     			Help_button.setBounds(frameWidth/2+100,frameHeight/2+150,Help_button.getWidth(),Help_button.getHeight());
     			Help_button.setContentAreaFilled(false);
     			Help_button.setBorderPainted(false);
     			Hidden_button.setBounds(frameWidth/2+200,frameHeight/2+150,Hidden_button.getWidth(),Hidden_button.getHeight());
     			Hidden_button.setContentAreaFilled(false);
     			Hidden_button.setBorderPainted(false);
     			Music_start_button.setBounds(700,30,Music_start_button.getWidth(),Music_start_button.getHeight());
     			Music_start_button.setContentAreaFilled(false);
     			Music_start_button.setBorderPainted(false);
     			Music_stop_button.setBounds(750,30,Music_start_button.getWidth(),Music_start_button.getHeight());
     			Music_stop_button.setContentAreaFilled(false);
     			Music_stop_button.setBorderPainted(false);
     			
                g2d.drawImage(moonLanderMenuImg, 0, 0, frameWidth, frameHeight, null);
                g2d.setColor(Color.white);
                g2d.drawString("Use w a d keys to controle the rocket.", frameWidth / 2 - 117, frameHeight / 2);
                g2d.drawString("시작하기 버튼을 눌러 게임을 시작하세요..", frameWidth / 2 - 120, frameHeight / 2 + 30);
                g2d.drawString("WWW.GAMETUTORIAL.NET", 7, frameHeight - 20);
                g2d.drawString("Best Score:"+" "+ Player.Final_Score[0][1], Framework.frameWidth / 2 - 100, Framework.frameHeight / 3+20);
            break;
            case OPTIONS:
                //...
            break;
            case GAME_CONTENT_LOADING:
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
        PauseTime = 0;
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
        PauseTime=0;
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
    @Override
    public void keyReleasedFramework(KeyEvent e)
    {
        switch (gameState)
        {
            case MAIN_MENU:
                
            break;
            case GAMEOVER:  
                if(PlayerRocket.landed==true && StageBase.stage_count!=6) {
                    if(e.getKeyCode() == KeyEvent.VK_SPACE)
                    {	
                    	restartGame();
                    }
                 }
            break;
        }
    }
    
    /**
     * This method is called when mouse button is clicked.
     * 
     * @param e MouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        
    }
}
