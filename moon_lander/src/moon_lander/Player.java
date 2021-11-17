package moon_lander;

import java.util.HashMap;

public class Player {
	
	public static int  Score[] = new int[5];
	public static int  Total_Score[][] = new int[300][2];
	public static int  Final_Score[][] = new int[300][2];
	public static HashMap<Integer, String> hm = new HashMap<Integer, String>();

	public Player() {
		
	}
	
	public static void Save_Score(int stage_count, int score) {
		
		Score[stage_count-1] = score;
		
	}
	
	// 5레벨까지 깼을 때만 이 함수 작성
	public static void Add_Score() {
		Total_Score[Framework.Player_num][0] = Framework.Player_num;
		for(int i=0 ; i<5 ; i++) {
			Total_Score[Framework.Player_num][1] += Score[i];
		}
	}
	
	// 누군가가 5레벨까지 다 깻을때 순위표 메인메뉴 가기 전에 정렬 
	public static void Cal_Ranking() {				
		
		//오름차순 정렬
		for(int i=0; i<299; i++) {
			if(Total_Score[i][1] > Total_Score[i+1][1]) {
				int tmp_1 = Total_Score[i][0];
				int tmp_2 = Total_Score[i][1];
				
				Total_Score[i][0] = Total_Score[i+1][0];
				Total_Score[i][1] = Total_Score[i+1][1];
				
				Total_Score[i+1][0] = tmp_1;
				Total_Score[i+1][1] = tmp_2;
			}
		}
		
		int j = 0;
		for(int i=1 ; i<300 ; i++) {
			if(Total_Score[i][0] != 0) {
			Final_Score[j][0] = Total_Score[i][0];
			Final_Score[j][1] = Total_Score[i][1];
			j++;
			}
		}
		
	}
		
}