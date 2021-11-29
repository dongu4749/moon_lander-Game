package ranking;

import java.awt.Container;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import moon_lander.Framework;

public class RankingWriter extends JFrame {
	
	// 배열이 0부터 시작해서 편리성을 위해 -1부터 시작.

   JButton login_Button = new JButton("등록");
   JButton exit_Button = new JButton("나가기");

   TextField textField = new TextField();
//한번 실행되었을 때 닉네임 설정한 유저 수
public static Integer Player_num = 0;
private int frameWidth = 800;
private int frameHeight = 1000;


   public RankingWriter() {

      Container contain = getContentPane();

      contain.add(login_Button);
      contain.add(textField);
      contain.add(exit_Button);

      contain.setLayout(null);

      textField.setBounds(30, 30, 100, 30);
      login_Button.setBounds(180, 30, 80, 30);
      exit_Button.setBounds(180, 80, 80, 30);
      
      // 닉네임 생성때마다 새로운 플레이어 객체?? 생성
      new RankingCalculator();
      

      login_Button.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
        	 
             // 게임 시작 후 몇번 째 플레이어인지 표시 / 닉네임 생성때마다로 고쳐야함
             RankingWriter.Player_num++;
        	 
        	 // 플레이어 이름을 플레이어 객체에 담기
             RankingCalculator.hm.put(RankingWriter.Player_num, textField.getText());
             setVisible(false);
             Framework.gameState = Framework.GameState.GAME_START;
             
        	 
				/*
				 * try { BufferedWriter out = new BufferedWriter(new FileWriter("Ranking.txt",
				 * true)); out.newLine(); out.write(tf.getText() + "\t"); out.close();
				 * setVisible(false); } catch (IOException e1) { System.err.println(e1);
				 * System.exit(1); }
				 */
         }
      });

      exit_Button.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            System.exit(0);
         }
      });

      setTitle("Record");
      setBounds(frameWidth/2,frameHeight/2, 300, 200);
      setResizable(false);
      setVisible(true);

   }
   public static void update_Ranking() {
       try {
           
           File deleteFile = new File("Ranking.txt");

           deleteFile.delete(); 
           
    	   BufferedWriter out = new BufferedWriter(new FileWriter("Ranking.txt", true));
    	   out.newLine();
    	   for(int i=0 ; i<300 ; i++) {
       			if(RankingCalculator.final_Score[i][1] != 0) {
       				out.write(RankingCalculator.hm.get(RankingCalculator.final_Score[i][0]) + "\t" + RankingCalculator.final_Score[i][1] + "\n");
       			}
       		}
    	   out.close();
          
       	} catch (IOException e1) {
          System.err.println(e1);
          System.exit(1);
       	}
   }
}
