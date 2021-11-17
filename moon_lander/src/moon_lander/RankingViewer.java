package moon_lander;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class RankingViewer extends JFrame {
   // ���� ����
   BufferedReader br = null;

   // Input ��Ʈ�� ����
   InputStreamReader isr = null;

   // File Input ��Ʈ�� ����
   FileInputStream fis = null;

   // File ���
   File file = new File("Ranking.txt");

   // ���۷� �о���� �ӽ� ����
   String temp = "";

   // ���� ���� ����� ���� ����
   String content = "";

   JButton b1 = new JButton("end");
   
   JTextArea l1 = new JTextArea();

   public RankingViewer() {

      Container c = getContentPane();
     

      c.add(b1, BorderLayout.SOUTH);
      c.add(l1, BorderLayout.CENTER);

      try {

        
         fis = new FileInputStream(file);
         isr = new InputStreamReader(fis, "euc-kr");
         br = new BufferedReader(isr);

     
         while ((temp = br.readLine()) != null) {
            content += temp + "\n";
         }

      } catch (FileNotFoundException e) {
         e.printStackTrace();

      } catch (Exception e) {
         e.printStackTrace();

      } finally {

         try {
            fis.close();
         } catch (IOException e) {
            e.printStackTrace();
         }

         try {
            isr.close();
         } catch (IOException e) {
            e.printStackTrace();
         }

         try {
            br.close();
         } catch (IOException e) {
            e.printStackTrace();
         }

      }

      l1.setText(content);
      l1.setFocusable(false);
      b1.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            setVisible(false);
         }
      });

      setSize(500, 500);
      setVisible(true);
      setLocationRelativeTo(null);
      setResizable(false);

   }

}