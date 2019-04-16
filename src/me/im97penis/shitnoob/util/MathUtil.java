package me.im97penis.shitnoob.util;

import java.util.Set;

import me.im97penis.shitnoob.check.checks.reach.Pair;

public class MathUtil {

	public static int addAllSet(Set<Pair<Long, Double>> values) {
		int total = 0;

		for (Pair<Long, Double> value : values) {
			total += value.getValue();
		}

		return total;
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

}
