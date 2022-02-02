package drawStar;

import java.util.ArrayList;
import java.util.List;

public class DrawStar_refac {
	public static void main(String[] argc) {
		//BFS
		
		int[][] line = {{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}};
//		int[][] line = {{0, 1, -1}, {1, 0, -1}, {1, 0, 1}};
		
		String[] answer = solution(line);
		
		System.out.println("answer: ");
		
		for(String str : answer)
			System.out.println(str);
	}
	
	class Point {
		long x;
		long y;
		
		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static String[] solution(int[][] line) {
		String[] answer = drawStar(getIntersectPoint(line));
		
		return answer;
	}
	
	public static List<Point> getIntersectPoint(int[][] line) {
		List<Point> points = new ArrayList<>();
		
		for(int i=0; i<line.length; i++) {
			long a = line[i][0];
			long b = line[i][1];
			long e = line[i][2];
			
			for(int j=i+1; j<line.length; j++) {
				long c = line[j][0];
				long d = line[j][1];
				long f = line[j][2];
				
				long gradient = (a * d) - (b * c);
				long newX = (b * f) - (e * d);
				long newY = (e * c) - (a * f);
				
				// 기울기(gradient)가 0이 아니라면 == 평행이 아니라면
				if(gradient != 0 ) {
					if(newX % gradient == 0 && newY % gradient == 0) {
						newX = newX / gradient;
						newY = newY / gradient;
						
						points.add(new DrawStar_refac().new Point(newX, newY));
					}
				}
			}
		}
		
		return points;
	}
	
	public static String[] drawStar(List<Point> points) {
		String[] answer;
		
		long minX = Long.MAX_VALUE;
		long minY = Long.MAX_VALUE;
		long maxX = Long.MIN_VALUE;
		long maxY = Long.MIN_VALUE;
		
		for(int i=0; i<points.size(); i++) {
			Point tPoint = points.get(i);
			
			minX = Math.min(minX, tPoint.x);
			minY = Math.min(minY, tPoint.y);
			maxX = Math.max(maxX, tPoint.x);
			maxY = Math.max(maxY, tPoint.y);
		}
		
		//격자판의 크기
		int width = (int)(maxX - minX + 1);
		int height = (int)(maxY - minY + 1);
				
		//init 격자판 and 교점 위치 표시하기
		boolean[][] tAnswer = new boolean[height][width];
		
		for(int i=0; i<points.size(); i++) {
			int xIdx = (int)(points.get(i).x - minX);
			int yIdx = (int)(maxY - points.get(i).y);
		
			tAnswer[yIdx][xIdx] = true;
		}		
		
		//교점에 별 그리기
		answer = new String[height];
		
		int i=0;
		for(boolean[] bool : tAnswer) {
			StringBuilder sb = new StringBuilder();
			
			for(boolean b : bool) {
				if(b) {
					sb.append("*");
				} else {
					sb.append(".");
				}
			}
			
			answer[i++] = sb.toString();
		}
		
		return answer;
	}
}
