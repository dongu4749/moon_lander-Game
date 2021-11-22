package moon_lander;

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

public class RankingWriter extends JFrame {
	
	// 배열이 0부터 시작해서 편리성을 위해 -1부터 시작.

   JButton b1 = new JButton("등록");
   JButton b3 = new JButton("나가기");

   TextField tf = new TextField();

   public RankingWriter() {

      Container c = getContentPane();

      c.add(b1);
      c.add(tf);
      c.add(b3);

      c.setLayout(null);

      tf.setBounds(30, 30, 100, 30);
      b1.setBounds(180, 30, 80, 30);
      b3.setBounds(180, 80, 80, 30);
      
      // 닉네임 생성때마다 새로운 플레이어 객체?? 생성
      new RankingCalculation();
      

      b1.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
        	 
             // 게임 시작 후 몇번 째 플레이어인지 표시 / 닉네임 생성때마다로 고쳐야함
             Framework.Player_num++;
        	 
        	 // 플레이어 이름을 플레이어 객체에 담기
             RankingCalculation.hm.put(Framework.Player_num, tf.getText());
             setVisible(false);
             
             Framework.newGame();
        	 
				/*
				 * try { BufferedWriter out = new BufferedWriter(new FileWriter("Ranking.txt",
				 * true)); out.newLine(); out.write(tf.getText() + "\t"); out.close();
				 * setVisible(false); } catch (IOException e1) { System.err.println(e1);
				 * System.exit(1); }
				 */
         }
      });

      b3.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
            System.exit(0);
         }
      });

      setTitle("Record");
      setBounds(Framework.frameWidth/2, Framework.frameHeight/2, 300, 200);
      setResizable(false);
      setVisible(true);

   }
   public static void Update_Ranking() {
       try {
          String filePath = "Ranking.txt";
           
           File deleteFile = new File(filePath);
           
           if(deleteFile.exists()) {
               deleteFile.delete(); 
           }
    	   BufferedWriter out = new BufferedWriter(new FileWriter("Ranking.txt", true));
       	out.newLine();
       	for(int i=0 ; i<300 ; i++) {
       		if(RankingCalculation.Final_Score[i][1] != 0) {
       			out.write(RankingCalculation.hm.get(RankingCalculation.Final_Score[i][0]) + "\t" + RankingCalculation.Final_Score[i][1] + "\n");
       		}
       	}
          out.close();
          
       } catch (IOException e1) {
          System.err.println(e1);
          System.exit(1);
       }
   }
}
