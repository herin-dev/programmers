package minRectangular;

public class MinRectangular_refac {
	public static void main(String argc[]) {
		int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
//		int[][] sizes = {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}};		
//		int[][] sizes = {{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}};
		
		int answer = solution(sizes);
		
		System.out.println("\n\tAnswer : " + answer);
	}
	
	public static int solution(int[][] sizes) {
		int answer = 0;
		
		int width = 0;
		int height = 0;
		
		for(int[] card : sizes) {
			width = Math.max(width, Math.max(card[0], card[1]));
			height = Math.max(height, Math.min(card[0], card[1]));
		}
		
		answer = width * height;
		
		return answer;
	}
}
