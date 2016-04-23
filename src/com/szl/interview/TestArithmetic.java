package com.szl.interview;

/**
 * 测试算法使用
 * @author shangzhiliang
 *
 */
public class TestArithmetic {

	/**
	 *  需求：给定一个整数，判定这个数是否是一个素数（质数）
	 *  质数定义：一个自然数除了能被一和它本身外不能被别得整数整除。
	 *  换句话说该数除了1和它本身外没有其它因数，否则为合数。
	 *  思路：从2开始依次对num取余。直到这个数开平方的整数。提高效率
	 */
	
	public static boolean isPrime(int num){
		boolean  isPrime = false;
		if(num < 2){
			isPrime = false;
			return isPrime;
		}
		
		for(int i = 2 ; i <= Math.sqrt(num); i++){
			if(num%i == 0){
				isPrime =  false;
				break;
			}
		}
		
		return isPrime;
	}
	
	
	public static void main(String[] args) {
		isPrime(5);
		System.out.println(isPrime(5));
	}
}
