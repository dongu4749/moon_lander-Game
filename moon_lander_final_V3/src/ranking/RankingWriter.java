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
	
	// �迭�� 0���� �����ؼ� ������ ���� -1���� ����.

   JButton login_Button = new JButton("���");
   JButton exit_Button = new JButton("������");

   TextField textField = new TextField();
//�ѹ� ����Ǿ��� �� �г��� ������ ���� ��
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
      
      // �г��� ���������� ���ο� �÷��̾� ��ü?? ����
      new RankingCalculator();
      

      login_Button.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
        	 
             // ���� ���� �� ��� ° �÷��̾����� ǥ�� / �г��� ���������ٷ� ���ľ���
             RankingWriter.Player_num++;
        	 
        	 // �÷��̾� �̸��� �÷��̾� ��ü�� ���
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
