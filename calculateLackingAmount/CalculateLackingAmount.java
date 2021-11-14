package calculateLackingAmount;

public class CalculateLackingAmount {
	public static void main(String[] argc) {
		int price = 3;
		int money = 20;
		int count = 4;
		long result;
		
		result = solution(price, money, count);
		
		System.out.println("Answer: " + result);
	}
	
	public static long solution(int price, int money, int count) {
		long cost = 0;
		
		for(int i=1; i<=count; i++) {
			cost += price*i;
		}
		
		return cost > money ? cost-money : 0;
	}
}
