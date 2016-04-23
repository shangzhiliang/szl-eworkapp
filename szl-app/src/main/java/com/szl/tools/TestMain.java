package com.szl.tools;

import java.text.DecimalFormat;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DecimalFormat df = new DecimalFormat("###0.00#");
		Double db = 999999999999999999999999.0d;
		String dbStr = db.toString();
		System.out.println(dbStr);
		System.out.println();
		System.out.println(df.format(Double.valueOf(dbStr).doubleValue()));
		System.out.println(String.valueOf(df.format(db.doubleValue())));
	}

}
