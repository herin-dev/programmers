package pickItem;

import java.util.LinkedList;
import java.util.Queue;

public class PickItem {
	public final static int boardSize = 50*2 + 2;		// 왜 +2일까
	public final static int[] nextXDirection = {1, 0, -1, 0};
	public final static int[] nextYDirection = {0, -1, 0, 1};
	
	public static void main(String argc[]) {
		
//		int[][] rectangle = {{1,1,7,4},{3,2,5,5},{4,3,6,9},{2,6,8,8}};
//		int characterX = 1;
//		int characterY = 3;
//		int itemX = 7;
//		int itemY = 8;
		
		int[][] rectangle = {{1, 1, 8, 4}, {2, 2, 4, 9}, {3, 6, 9, 8}, {6, 3, 7, 7}};
		int characterX = 9;
		int characterY = 7;
		int itemX = 6;
		int itemY = 1;
		
		int answer = solution(rectangle, characterX, characterY, itemX, itemY);
		
		System.out.println("answer: " + answer);
	}
	
	class Point {
		int x;
		int y;
		int distance;
		
		public Point(int x, int y, int distance) {
			this.x = x;
			this.y = y;
			this.distance = distance;
		}
	}
	
    public static int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        
        //이동경로 표시
        int[][] board = makeBoard(rectangle);
        
        //bfs
        answer = bfs(board, characterX, characterY, itemX, itemY);
        
        return answer;
    }
    
    public static int[][] makeBoard(int[][] rectangle) {
    	//사각형 채우기
        int[][] board = new int[boardSize][boardSize];
        
        //사각형 전부 1로 채우기
        for(int i=0; i<rectangle.length; i++) {
        	int[] rect = rectangle[i];
        	
        	for(int x=rect[0]*2; x<=rect[2]*2; x++) {
        		for(int y=rect[1]*2; y<=rect[3]*2; y++) {
        			board[x][y] = 1;
        		}
        	}
        }
        
        //사각형 안쪽 0으로 채우기
        for(int i=0; i<rectangle.length; i++) {
        	int[] rect = rectangle[i];
        	
        	for(int x=rect[0]*2+1; x<=rect[2]*2-1; x++) {
        		for(int y=rect[1]*2+1; y<=rect[3]*2-1; y++) {
        			board[x][y] = 0;
        		}
        	}
        }
        
        return board;
    }
    
    public static int bfs(int[][] board, int characterX, int characterY, int itemX, int itemY) {
    	int distance = 0;
    	boolean[][] isVisited = new boolean[boardSize][boardSize];
    	Queue<Point> queue = new LinkedList<>();
    	
    	int doubleCharX = characterX * 2;
    	int doubleCharY = characterY * 2;
    	int doubleItemX = itemX * 2;
    	int doubleItemY = itemY * 2;
    	
    	//시작지점
    	Point characterStartPoint = new PickItem().new Point(doubleCharX, doubleCharY, 0);
    	queue.add(characterStartPoint);
    	isVisited[characterStartPoint.x][characterStartPoint.y] = true;
    	
    	while(!queue.isEmpty()) {
    		// 캐릭터 현재 위치
    		Point currentPoint = queue.poll();
    		
    		// 아이템 위치면 stop
    		if(currentPoint.x == doubleItemX && currentPoint.y == doubleItemY) {
    			distance = currentPoint.distance;
    			break;
    		}
    		
    		// 하나씩 조회하면서 길 찾기
    		for(int i=0; i<4; i++) {
    			int nextX = currentPoint.x + nextXDirection[i];
    			int nextY = currentPoint.y + nextYDirection[i];
    			
    			if(!isVisited[nextX][nextY] && board[nextX][nextY] == 1) {
    				Point nextPoint = new PickItem(). new Point(nextX, nextY, currentPoint.distance+1);
    				
    				queue.add(nextPoint);
    				isVisited[nextPoint.x][nextPoint.y] = true;
    			}
    		}
    		
    	}
    	
    	return distance / 2;
    }
}
