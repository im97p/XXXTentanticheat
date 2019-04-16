package me.im97penis.shitnoob.util;

import java.util.List;

public class ListUtil {

	public static long getTotal(List<Long> list) {
		long total = 0;
	
		for(long in : list) {
			total += in;
		}
		
		return total;
	}
	
}
