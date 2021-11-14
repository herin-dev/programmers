package minRectangular;

public class MinRectangular {
	public static void main(String argc[]) {
//		int[][] sizes = {{60, 50}, {30, 70}, {60, 30}, {80, 40}};
//		int[][] sizes = {{10, 7}, {12, 3}, {8, 15}, {14, 7}, {5, 15}};		
		int[][] sizes = {{14, 4}, {19, 6}, {6, 16}, {18, 7}, {7, 11}};
		
		int answer = solution(sizes);
		
		System.out.println("\n\tAnswer : " + answer);
	}
	
	public static int solution(int[][] sizes) {
		int answer = 0;
		
		for(int i=0; i<sizes.length; i++) {
			if(sizes[i][0] < sizes[i][1]) {
				rotateCard(i, sizes);
			}
		}
		
		int maxWidthIdx = getMaxWidthIdx(sizes);
		int maxHeightIdx = getMaxHeightIdx(sizes);
		
		answer = sizes[maxWidthIdx][0] * sizes[maxHeightIdx][1];
		
		return answer;
	}
	
	public static int getMaxWidthIdx(int[][] sizes) {
		int widthIdx = 0;
		
		for(int i=0; i<sizes.length; i++) {
			if(sizes[i][0] > sizes[widthIdx][0]) {
				widthIdx = i;
			}
		}
		
		return widthIdx;
	}
	
	public static int getMaxHeightIdx(int[][] sizes) {
		int heightIdx = 0;
		
		for(int i=0; i<sizes.length; i++) {
			if(sizes[i][1] > sizes[heightIdx][1]) {
				heightIdx = i;
			}
		}
		
		return heightIdx;
	}

	
	
	public static void rotateCard(int idx, int[][] sizes) {
		int temp = sizes[idx][0];
		sizes[idx][0] = sizes[idx][1];
		sizes[idx][1] = temp;
	}
}
