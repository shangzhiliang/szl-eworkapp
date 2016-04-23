package com.szl.common.utils;

public class StrUtils {
	
	
	public static boolean isBlank(String str) {
		if(null == str || "".equals(str)){
			return true;
		}
		return false;
	}

	public static String escape(String src) {  
        int i;  
        char j;  
        StringBuffer tmp = new StringBuffer();  
        tmp.ensureCapacity(src.length() * 6);  
        for (i = 0; i < src.length(); i++) {  
            j = src.charAt(i);  
            if (Character.isDigit(j) || Character.isLowerCase(j)  
                    || Character.isUpperCase(j))  
                tmp.append(j);  
            else if (j < 256) {  
                tmp.append("%");  
                if (j < 16)  
                    tmp.append("0");  
                tmp.append(Integer.toString(j, 16));  
            } else {  
                tmp.append("%u");  
                tmp.append(Integer.toString(j, 16));  
            }  
        }  
        return tmp.toString();  
    }  
 
    public static String unescape(String src) {  
        StringBuffer tmp = new StringBuffer();  
        tmp.ensureCapacity(src.length());  
        int lastPos = 0, pos = 0;  
        char ch;  
        while (lastPos < src.length()) {  
            pos = src.indexOf("%", lastPos);  
            if (pos == lastPos) {  
                if (src.charAt(pos + 1) == 'u') {  
                    ch = (char) Integer.parseInt(src  
                            .substring(pos + 2, pos + 6), 16);  
                    tmp.append(ch);  
                    lastPos = pos + 6;  
                } else {  
                    ch = (char) Integer.parseInt(src  
                            .substring(pos + 1, pos + 3), 16);  
                    tmp.append(ch);  
                    lastPos = pos + 3;  
                }  
            } else {  
                if (pos == -1) {  
                    tmp.append(src.substring(lastPos));  
                    lastPos = src.length();  
                } else {  
                    tmp.append(src.substring(lastPos, pos));  
                    lastPos = pos;  
                }  
            }  
        }  
        return tmp.toString();  
    } 
	
//	public static boolean isNotBlank(String str) {
//		return ! isBlank(str);
//	}
//	
//	public static String escapeXml(String buffer) {
//		int start = 0;
//		int length = buffer.length();
//		char[] arrayBuffer = buffer.toCharArray();
//		StringBuffer escapedBuffer = null;
//
//		for (int i = 0; i < length; i++) {
//			char c = arrayBuffer[i];
//			if (c <= '>') {
//				char[] escaped = Util.specialCharactersRepresentation[c];
//				if (escaped == null)
//					continue;
//				if (start == 0) {
//					escapedBuffer = new StringBuffer(length + 5);
//				}
//				if (start < i) {
//					escapedBuffer.append(arrayBuffer, start, i - start);
//				}
//				start = i + 1;
//				escapedBuffer.append(escaped);
//			}
//		}
//		if (start == 0) {
//			return buffer;
//		}
//		if (start < length) {
//			escapedBuffer.append(arrayBuffer, start, length - start);
//		}
//		return escapedBuffer.toString();
//	}
    
    public static void main(String[] args) {
    	String str = escape("龙锦苑东四区");
    	String srcStr = unescape("%u970D%u8425");
    	System.out.println(str);
    	System.out.println(srcStr);
	}
}
