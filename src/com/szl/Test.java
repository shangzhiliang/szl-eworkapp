package com.szl;


public class Test {

	public static void main(String[] args) {

	 System.out.println(new Test().test());
	}
	static int test()  
	{  
	        int x = 1;  
	    try{  
	        return x;  
	    }  
	    finally{  
	       x++;  
	       System.out.println("----"+x);
	    }  
	} 
}
