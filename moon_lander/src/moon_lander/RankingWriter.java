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
	
	// �迭�� 0���� �����ؼ� ������ ���� -1���� ����.

   JButton b1 = new JButton("���");
   JButton b3 = new JButton("������");

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
      
      // �г��� ���������� ���ο� �÷��̾� ��ü?? ����
      new RankingCalculation();
      

      b1.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub
        	 
             // ���� ���� �� ��� ° �÷��̾����� ǥ�� / �г��� ���������ٷ� ���ľ���
             Framework.Player_num++;
        	 
        	 // �÷��̾� �̸��� �÷��̾� ��ü�� ���
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
