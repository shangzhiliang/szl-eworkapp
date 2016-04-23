package com.szl.web.servlet;

import com.szl.common.utils.StrUtils;

public class TestMain {

	public static void main(String[] args) {
		String escapeName = StrUtils.escape("上海");
		System.out.println(escapeName);
	}
}
