package drawStar;

import java.util.ArrayList;
import java.util.List;

public class DrawStar {
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
				
				// 기울기(gradient)가 0이 아니라면 == 평행이 아니라면
				if(gradient != 0) {
					// 교점이 정수라면
					long newX = ((b * f) - (e * d));
					if(newX % gradient == 0) {
						newX = newX / gradient;
						
						long newY = ((e * c) - (a * f));
						if(newY % gradient == 0) {
							newY = newY / gradient;
														
							points.add(new DrawStar().new Point(newX, newY));
						}
					}
				}
			}
		}
		
		return points;
	}
	
	public static String[] drawStar(List<Point> points) {
		String[] answer;
		String xString;
		String[][] tAnswer;
		
		long minX = points.get(0).x;
		long minY = points.get(0).y;
		long maxX = points.get(0).x;
		long maxY = points.get(0).y;
		
		//교점의 최소, 최대값 구하기
		for(int i=0; i<points.size(); i++) {
			Point tPoint = points.get(i);
			
			minX = Math.min(minX, tPoint.x);
			minY = Math.min(minY, tPoint.y);
			maxX = Math.max(maxX, tPoint.x);
			maxY = Math.max(maxY, tPoint.y);
		}
		
		//격자판의 크기
		long width = maxX - minX + 1;
		long height = maxY - minY + 1;
		
		answer = new String[(int)height];
		tAnswer = new String[(int)height][(int)width];
		
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				tAnswer[i][j] = ".";
			}
		}
		
		//교점에 별 그리기
		for(int i=0; i<points.size(); i++) {
			long xIdx = points.get(i).x - minX;
			long yIdx = maxY - points.get(i).y;
			
			tAnswer[(int)yIdx][(int)xIdx] = "*";
		}
		
		xString = "";
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				xString += tAnswer[i][j]; 
			}
			
			answer[i] = xString;
			xString = "";
		}
		
		return answer;
	}
}
