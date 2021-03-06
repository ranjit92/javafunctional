package com.me.functionalinterface;

import java.util.function.Function;

public class _Function {

	public static void main(String[] args) {
		int increment = increment(0);
		System.out.println(increment);
		
		int increment2 = incrementByOne.apply(2);
		System.out.println(increment2);
	}
	
	
	static Function<Integer, Integer> incrementByOne = number -> number + 1;
	
	private static int increment(int number) {
		return number + 1;
	}
	
	
	
}
