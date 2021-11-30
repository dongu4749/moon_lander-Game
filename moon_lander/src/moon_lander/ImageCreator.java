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
import player.PlayerRocket;
import ranking.RankingWriter;

public class ImageCreator extends JFrame {
	public BufferedImage moonLanderMenuImg;
	public BufferedImage produerPageImg;
	public BufferedImage helpImg;
	public BufferedImage helpPageImg;
	public BufferedImage playerSelectPageImg;
	public BufferedImage characterSelectPageImg;
	public BufferedImage startPageImg;
	private ImageIcon main_startImg = new ImageIcon(this.getClass().getResource("/resources/images/start_button.png"));
	private ImageIcon game_startImg = new ImageIcon(
			this.getClass().getResource("/resources/images/gamestart_button.png"));
	private ImageIcon statistics_buttonImg = new ImageIcon(
			this.getClass().getResource("/resources/images/statistics_button.png"));
	private ImageIcon help_ButtonImg = new ImageIcon(this.getClass().getResource("/resources/images/Help_button.png"));
	private ImageIcon producer_buttonImg = new ImageIcon(
			this.getClass().getResource("/resources/images/producer_button.png"));
	private ImageIcon hidden_ButtonImg = new ImageIcon(
			this.getClass().getResource("/resources/images/Hidden_button.png"));
	private ImageIcon one_ButtonImg = new ImageIcon(this.getClass().getResource("/resources/images/1P_button.png"));
	private ImageIcon two_ButtonImg = new ImageIcon(this.getClass().getResource("/resources/images/2P_button.png"));
	private ImageIcon music_StartImg = new ImageIcon(this.getClass().getResource("/resources/images/audio_start.png"));
	private ImageIcon music_PauseImg = new ImageIcon(this.getClass().getResource("/resources/images/audio_stop.png"));
	private ImageIcon rocket_Select1Img = new ImageIcon(
			this.getClass().getResource("/resources/images/rocketselect1.png"));
	private ImageIcon rocket_Select2Img = new ImageIcon(
			this.getClass().getResource("/resources/images/rocketselect2.png"));
	private ImageIcon rocket_Select3Img = new ImageIcon(
			this.getClass().getResource("/resources/images/rocketselect3.png"));

	JButton main_Start_Button = new JButton(main_startImg);
	JButton game_Start_Button = new JButton(game_startImg);
	JButton statistics_Button = new JButton(statistics_buttonImg);
	JButton producer_Button = new JButton(producer_buttonImg);
	JButton help_Button = new JButton(help_ButtonImg);
	JButton hidden_Button = new JButton(hidden_ButtonImg);
	JButton music_Start_Button = new JButton(music_StartImg);
	JButton music_Stop_Button = new JButton(music_PauseImg);
	JButton one_Button = new JButton(one_ButtonImg);
	JButton two_Button = new JButton(two_ButtonImg);
	JButton goToMain_Button = new JButton("Go to Main Manu");
	JButton restart_Button = new JButton("Restart");
	JButton goToNextStage_Button = new JButton("Go to Next Stage");
	JButton rocket1Select_Button = new JButton(rocket_Select1Img);
	JButton rocket2Select_Button = new JButton(rocket_Select2Img);
	JButton rocket3Select_Button = new JButton(rocket_Select3Img);

	public void ImageLoadContent() // 페이지 이미지를 받아와 저장한다.
	{
		try {
			URL moonLanderMenuImgUrl = this.getClass().getResource("/resources/images/menu.jpg");
			moonLanderMenuImg = ImageIO.read(moonLanderMenuImgUrl);

			URL ProduerPageImgUrl = this.getClass().getResource("/resources/images/produer_page.PNG.jpg");
			produerPageImg = ImageIO.read(ProduerPageImgUrl);

			URL Help_pageImgUrl = this.getClass().getResource("/resources/images/Help_page.PNG.jpg");
			helpPageImg = ImageIO.read(Help_pageImgUrl);

			URL PLAYER_SELECTPAGEImgUrl = this.getClass().getResource("/resources/images/player.select.jpg");
			playerSelectPageImg = ImageIO.read(PLAYER_SELECTPAGEImgUrl);

			URL Character_SELECTPAGEImgUrl = this.getClass().getResource("/resources/images/Character_select.jpg");
			characterSelectPageImg = ImageIO.read(Character_SELECTPAGEImgUrl);

			URL startPageImgUrl = this.getClass().getResource("/resources/images/Startbackground.jpg");
			startPageImg = ImageIO.read(startPageImgUrl);
		} catch (IOException ex) {
			Logger.getLogger(Framework.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public void buttonHidden() // 버튼을 숨긴다
	{
		main_Start_Button.setVisible(false);
		game_Start_Button.setVisible(false);
		producer_Button.setVisible(false);
		statistics_Button.setVisible(false);
		help_Button.setVisible(false);
		goToMain_Button.setVisible(false);
		goToNextStage_Button.setVisible(false);
		hidden_Button.setVisible(false);
		music_Start_Button.setVisible(false);
		music_Stop_Button.setVisible(false);
		one_Button.setVisible(false);
		two_Button.setVisible(false);
		restart_Button.setVisible(false);
		rocket1Select_Button.setVisible(false);
		rocket2Select_Button.setVisible(false);
		rocket3Select_Button.setVisible(false);
	}

	public void setMusicButton() // 음악버튼 위치를 설정한다.
	{
		music_Start_Button.setBounds(700, 30, music_Start_Button.getWidth(), music_Start_Button.getHeight());
		music_Start_Button.setContentAreaFilled(false);
		music_Start_Button.setBorderPainted(false);
		music_Stop_Button.setBounds(750, 30, music_Start_Button.getWidth(), music_Start_Button.getHeight());
		music_Stop_Button.setContentAreaFilled(false);
		music_Stop_Button.setBorderPainted(false);
	}

	public void setMainButton()// 메인메뉴 화면에 버튼 위치를 설정한다.
	{
		main_Start_Button.setBounds(Framework.frameWidth / 2 - 250, Framework.frameHeight / 2 + 150,
				main_Start_Button.getWidth(), main_Start_Button.getHeight());
		main_Start_Button.setContentAreaFilled(false);
		main_Start_Button.setBorderPainted(false);
		producer_Button.setBounds(Framework.frameWidth / 2 - 150, Framework.frameHeight / 2 + 150,
				producer_Button.getWidth(), producer_Button.getHeight());
		producer_Button.setContentAreaFilled(false);
		producer_Button.setBorderPainted(false);
		statistics_Button.setBounds(Framework.frameWidth / 2 - 50, Framework.frameHeight / 2 + 150,
				statistics_Button.getWidth(), statistics_Button.getHeight());
		statistics_Button.setContentAreaFilled(false);
		statistics_Button.setBorderPainted(false);
		help_Button.setBounds(Framework.frameWidth / 2 + 50, Framework.frameHeight / 2 + 150, help_Button.getWidth(),
				help_Button.getHeight());
		help_Button.setContentAreaFilled(false);
		help_Button.setBorderPainted(false);
		hidden_Button.setBounds(Framework.frameWidth / 2 + 150, Framework.frameHeight / 2 + 150,
				hidden_Button.getWidth(), hidden_Button.getHeight());
		hidden_Button.setContentAreaFilled(false);
		hidden_Button.setBorderPainted(false);
	}

	public void setRocketButton() // 로켓 버튼 위치를 설정한다.
	{
		rocket1Select_Button.setBounds(Framework.frameWidth / 2 - 40, Framework.frameHeight / 2 - 320,
				rocket1Select_Button.getWidth(), rocket1Select_Button.getHeight());
		rocket1Select_Button.setContentAreaFilled(false);
		rocket1Select_Button.setBorderPainted(false);
		rocket2Select_Button.setBounds(Framework.frameWidth / 2 - 80, Framework.frameHeight / 2 - 90,
				rocket2Select_Button.getWidth(), rocket2Select_Button.getHeight());
		rocket2Select_Button.setContentAreaFilled(false);
		rocket2Select_Button.setBorderPainted(false);
		rocket3Select_Button.setBounds(Framework.frameWidth / 2 - 40, Framework.frameHeight / 2 + 130,
				rocket3Select_Button.getWidth(), rocket3Select_Button.getHeight());
		rocket3Select_Button.setContentAreaFilled(false);
		rocket3Select_Button.setBorderPainted(false);
	}

	public void setPlayerButton() // 플레이어 선택 화면에 버튼 위치를 설정한다.
	{
		one_Button.setBounds(Framework.frameWidth / 2 - 150, Framework.frameHeight / 2 + 150, one_Button.getWidth(),
				one_Button.getHeight());
		one_Button.setContentAreaFilled(false);
		one_Button.setBorderPainted(false);
		two_Button.setBounds(Framework.frameWidth / 2, Framework.frameHeight / 2 + 150, two_Button.getWidth(),
				two_Button.getHeight());
		two_Button.setContentAreaFilled(false);
		two_Button.setBorderPainted(false);
	}

	public void setGameOverButton() // 게임오버 화면에 버튼 위치를 설정한다.
	{
		goToMain_Button.setBounds(Framework.frameWidth / 2 - 117, Framework.frameHeight / 2 + 80, 200, 100);
		goToMain_Button.setContentAreaFilled(false);
		goToMain_Button.setBorderPainted(false);
		goToMain_Button.setForeground(Color.white);
		restart_Button.setBounds(Framework.frameWidth / 2 - 117, Framework.frameHeight / 2, 200, 100);
		restart_Button.setContentAreaFilled(false);
		restart_Button.setBorderPainted(false);
		restart_Button.setForeground(Color.white);
		goToNextStage_Button.setBounds(Framework.frameWidth / 2 - 117, Framework.frameHeight / 2, 200, 100);
		goToNextStage_Button.setContentAreaFilled(false);
		goToNextStage_Button.setBorderPainted(false);
		goToNextStage_Button.setForeground(Color.white);
	}

	public void mainButtonVisible() // 메인 화면에 버튼을 보인다
	{
		main_Start_Button.setVisible(true);
		producer_Button.setVisible(true);
		help_Button.setVisible(true);
		statistics_Button.setVisible(true);
		hidden_Button.setVisible(true);
		music_Start_Button.setVisible(true);
		music_Stop_Button.setVisible(true);
	}

	public void setGameStartButton() {
		game_Start_Button.setBounds(Framework.frameWidth / 2 - 100, Framework.frameHeight / 2 - 50,
				game_Start_Button.getWidth(), game_Start_Button.getHeight());
		game_Start_Button.setContentAreaFilled(false);
		game_Start_Button.setBorderPainted(false);
	}

	void rocketButton(final Framework framework) {
		rocket1Select_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {

				PlayerRocket.character_num = 1;
				if (RankingWriter.checkLogin == 1)
					new RankingWriter();
			}
		});
		rocket2Select_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PlayerRocket.character_num = 2;
				new RankingWriter();
			}

		});
		rocket3Select_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				PlayerRocket.character_num = 3;
				new RankingWriter();
			}
		});
	}

	void menuButton(final Framework framework) {
		statistics_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Framework.gameState = GameState.STATISTICS;
			}

		});
		main_Start_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Framework.gameState = GameState.PLAYER_SELECT;
			}

		});
		producer_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Framework.gameState = GameState.PRODUCER_PAGE;
			}

		});
		help_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Framework.gameState = GameState.HELP;
			}

		});
		hidden_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				StageBase.stage_count = 6;
				Framework.newGame();
			}

		});
		music_Start_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				framework.backgroundMusic.musicResume();
			}

		});
		music_Stop_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				framework.backgroundMusic.musicPause();
			}
		});
	}

	void ingameButton(final Framework framework) {
		game_Start_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Framework.newGame();
			}

		});
		one_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Game.isMode = false;
				Framework.gameState = GameState.CHARACTER_SELECT;
			}
		});
		two_Button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				Game.isMode = true;
				Framework.newGame();
			}

		});
		goToMain_Button.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				StageBase.stage_count = 1;
				RankingWriter.checkLogin = 1;
				Framework.gameState = GameState.MAIN_MENU;
			}
		});
		goToNextStage_Button.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (Framework.game.playerRocket.isLanded == true && StageBase.stage_count != 6)
					StageBase.stage_count++;
				framework.restartGame();
			}
		});
		restart_Button.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				framework.restartGame();
			}
		});
	}

}
