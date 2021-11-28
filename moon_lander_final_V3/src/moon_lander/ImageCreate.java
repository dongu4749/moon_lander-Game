package moon_lander;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
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

import moon_lander.Framework.GameState;

class ImageCreate extends JFrame {
	public BufferedImage moonLanderMenuImg;
    public BufferedImage ProduerPageImg;
    public BufferedImage HelpImg;
    public BufferedImage Help_pageImg;
    public BufferedImage PLAYER_SELECTPAGEImg;
    public BufferedImage Character_SELECTPAGEImg;
    private ImageIcon main_startImg = new ImageIcon(this.getClass().getResource("/resources/images/start_button.png"));
    
    private ImageIcon statistics_buttonImg = new ImageIcon(this.getClass().getResource("/resources/images/statistics_button.png"));
    private ImageIcon Help_buttonImg = new ImageIcon(this.getClass().getResource("/resources/images/Help_button.png"));
    private ImageIcon producer_buttonImg = new ImageIcon(this.getClass().getResource("/resources/images/producer_button.png"));
    private ImageIcon Hidden_buttonImg = new ImageIcon(this.getClass().getResource("/resources/images/Hidden_button.png"));
    private ImageIcon ONE_buttonImg = new ImageIcon(this.getClass().getResource("/resources/images/1P_button.png"));
    private ImageIcon TWO_buttonImg = new ImageIcon(this.getClass().getResource("/resources/images/2P_button.png"));
    private ImageIcon MusicStartImg = new ImageIcon(this.getClass().getResource("/resources/images/audio_start.png"));
    private ImageIcon MusicPauseImg = new ImageIcon(this.getClass().getResource("/resources/images/audio_stop.png"));
   
	private ImageIcon rocketselect1Img = new ImageIcon(this.getClass().getResource("/resources/images/rocketselect1.png"));
	private ImageIcon rocketselect2Img = new ImageIcon(this.getClass().getResource("/resources/images/rocketselect2.png"));
	private ImageIcon rocketselect3Img = new ImageIcon(this.getClass().getResource("/resources/images/rocketselect3.png"));
    JButton main_start = new JButton(main_startImg);
    
    JButton statistics_button = new JButton(statistics_buttonImg);
    JButton producer_button = new JButton(producer_buttonImg);
    JButton Help_button = new JButton(Help_buttonImg);
    JButton Hidden_button = new JButton(Hidden_buttonImg);
    JButton Music_start_button = new JButton(MusicStartImg);
    JButton Music_stop_button = new JButton(MusicPauseImg);
    JButton ONE_button = new JButton(ONE_buttonImg);
    JButton TWO_button = new JButton(TWO_buttonImg);
    JButton gotoMain = new JButton("Go to Main Manu");
    JButton Restart = new JButton("Restart");
	JButton gotoNextStage = new JButton("Go to Next Stage");
    JButton rocket1select = new JButton(rocketselect1Img);
    JButton rocket2select = new JButton(rocketselect2Img);
    JButton rocket3select = new JButton(rocketselect3Img);
    public void ImageLoadContent() // 페이지 이미지를 받아와 저장한다.
    {
    	try
    	{
    	URL moonLanderMenuImgUrl = this.getClass().getResource("/resources/images/menu.jpg");
        moonLanderMenuImg = ImageIO.read(moonLanderMenuImgUrl);
        
        URL ProduerPageImgUrl = this.getClass().getResource("/resources/images/produer_page.PNG.jpg");
    	ProduerPageImg = ImageIO.read(ProduerPageImgUrl);
    	
    	URL Help_pageImgUrl = this.getClass().getResource("/resources/images/Help_page.PNG.jpg");
    	Help_pageImg = ImageIO.read(Help_pageImgUrl);
    	
    	URL PLAYER_SELECTPAGEImgUrl = this.getClass().getResource("/resources/images/player.select.jpg");
    	PLAYER_SELECTPAGEImg = ImageIO.read(PLAYER_SELECTPAGEImgUrl);
    	
    	URL Character_SELECTPAGEImgUrl = this.getClass().getResource("/resources/images/Character_select.jpg");
    	Character_SELECTPAGEImg = ImageIO.read(Character_SELECTPAGEImgUrl);
    	}
    	catch (IOException ex) {
            Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void ButtonHidden() // 버튼을 숨긴다
    {
    	main_start.setVisible(false);
        producer_button.setVisible(false);
        statistics_button.setVisible(false);
        Help_button.setVisible(false);
        gotoMain.setVisible(false);
        gotoNextStage.setVisible(false);
        Hidden_button.setVisible(false);
        Music_start_button.setVisible(false);
        Music_stop_button.setVisible(false);
        ONE_button.setVisible(false);
        TWO_button.setVisible(false);
        Restart.setVisible(false);
        rocket1select.setVisible(false);
        rocket2select.setVisible(false);
        rocket3select.setVisible(false);
    }
    
    public void setMusicButton() // 음악버튼 위치를 설정한다.
	{
		    Music_start_button.setBounds(700,30,Music_start_button.getWidth(),Music_start_button.getHeight());
			Music_start_button.setContentAreaFilled(false);
			Music_start_button.setBorderPainted(false);
			Music_stop_button.setBounds(750,30,Music_start_button.getWidth(),Music_start_button.getHeight());
			Music_stop_button.setContentAreaFilled(false);
			Music_stop_button.setBorderPainted(false);
	}
    public void SetMainButton()//  메인메뉴 화면에 버튼 위치를 설정한다.
    {
    	main_start.setBounds(Framework.frameWidth/2-250,Framework.frameHeight/2+150,main_start.getWidth(),main_start.getHeight());
        main_start.setContentAreaFilled(false);
    	main_start.setBorderPainted(false);
    	producer_button.setBounds(Framework.frameWidth/2-150,Framework.frameHeight/2+150,producer_button.getWidth(),producer_button.getHeight());
    	producer_button.setContentAreaFilled(false);
    	producer_button.setBorderPainted(false);
    	statistics_button.setBounds(Framework.frameWidth/2-50,Framework.frameHeight/2+150,statistics_button.getWidth(),statistics_button.getHeight());
    	statistics_button.setContentAreaFilled(false);
    	statistics_button.setBorderPainted(false);
        Help_button.setBounds(Framework.frameWidth/2+50,Framework.frameHeight/2+150,Help_button.getWidth(),Help_button.getHeight());
    	Help_button.setContentAreaFilled(false);
    	Help_button.setBorderPainted(false);
    	Hidden_button.setBounds(Framework.frameWidth/2+150,Framework.frameHeight/2+150,Hidden_button.getWidth(),Hidden_button.getHeight());
    	Hidden_button.setContentAreaFilled(false);
    	Hidden_button.setBorderPainted(false);
    }
    
    public void SetRocketButton() //  로켓 버튼 위치를 설정한다.
    {
    	rocket1select.setBounds(Framework.frameWidth/2-40,Framework.frameHeight/2-320,rocket1select.getWidth(),rocket1select.getHeight());
    	rocket1select.setContentAreaFilled(false);
    	rocket1select.setBorderPainted(false);
    	rocket2select.setBounds(Framework.frameWidth/2-80,Framework.frameHeight/2-90,rocket2select.getWidth(),rocket2select.getHeight());
    	rocket2select.setContentAreaFilled(false);
    	rocket2select.setBorderPainted(false);
    	rocket3select.setBounds(Framework.frameWidth/2-40,Framework.frameHeight/2+130,rocket3select.getWidth(),rocket3select.getHeight());
    	rocket3select.setContentAreaFilled(false);
    	rocket3select.setBorderPainted(false);
    }
    public void SetPlayerButton() // 플레이어 선택 화면에 버튼 위치를 설정한다.
    {
    	ONE_button.setBounds(Framework.frameWidth/2-150,Framework.frameHeight/2+150,ONE_button.getWidth(),ONE_button.getHeight());
    	ONE_button.setContentAreaFilled(false);
    	ONE_button.setBorderPainted(false);
    	TWO_button.setBounds(Framework.frameWidth/2,Framework.frameHeight/2+150,TWO_button.getWidth(),TWO_button.getHeight());
    	TWO_button.setContentAreaFilled(false);
    	TWO_button.setBorderPainted(false);
    }
    public void SetGameOverButton() // 게임오버 화면에 버튼 위치를 설정한다.
    {
    	gotoMain.setBounds(Framework.frameWidth / 2 - 117, Framework.frameHeight / 2 + 80, 200, 100);
    	gotoMain.setContentAreaFilled(false);
    	gotoMain.setBorderPainted(false);
    	gotoMain.setForeground(Color.white);
    	Restart.setBounds(Framework.frameWidth / 2 - 117,Framework.frameHeight / 2, 200, 100);
    	Restart.setContentAreaFilled(false);
    	Restart.setBorderPainted(false);
    	Restart.setForeground(Color.white);
    	gotoNextStage.setBounds(Framework.frameWidth / 2 - 117, Framework.frameHeight / 2, 200, 100);
    	gotoNextStage.setContentAreaFilled(false);
    	gotoNextStage.setBorderPainted(false);
    	gotoNextStage.setForeground(Color.white);
    }
    public void MainButtonVisible() // 메인 화면에 버튼을 보인다
    {
    	main_start.setVisible(true);
    	producer_button.setVisible(true);
    	Help_button.setVisible(true);
    	statistics_button.setVisible(true);
    	Hidden_button.setVisible(true);
    	Music_start_button.setVisible(true);
    	Music_stop_button.setVisible(true);
    }
    
}
