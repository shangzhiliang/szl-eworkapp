package com.szl.tools.numberFormat;

import java.text.DecimalFormat;

public class NumberFormatUtils {

	public static String formatNumber2Str(Double num){
		if(num == null){
			throw new NullPointerException();
		}
		
		DecimalFormat df = new DecimalFormat("###0.00#");
		return df.format(num);
	}
}
