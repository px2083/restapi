package com.spring.boot.api.test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Prob {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		
		int paramInt = sc.nextInt();
		
		// 방법3
		long start = System.nanoTime();
		String[] paramStrArray = Integer.toString(paramInt).split("");
		Stream<String> streamStr = Arrays.stream(paramStrArray);
		List<String> result = streamStr.sorted().collect(Collectors.toList());
		long finish = System.nanoTime();
		System.out.println(finish - start);
		
		// 방법1
		
		
		
		start = System.nanoTime();
		String paramStr = Integer.toString(paramInt);
		String noZeroStr = paramStr.replace("0", "");
		
		int pLength = paramStr.length();
		int nzLength = noZeroStr.length();
		
		String resultStr = "";
		for (int i = 0; i < pLength - nzLength; i++) {
			resultStr += "0";
		}
		finish = System.nanoTime();
		System.out.println(finish - start);
		
		
		// 방법2
		start = System.nanoTime();
		int[] paramArray = Stream.of(String.valueOf(paramInt).split("")).mapToInt(Integer::parseInt).sorted().toArray();
		finish = System.nanoTime();
		System.out.println(finish - start);
		
		int[] zeros = {};
		int[] numbers = {};

		for (int i : paramArray) {
			if(i == 0) {
				zeros = Arrays.copyOf(zeros, zeros.length +1);
				zeros[zeros.length - 1] = i;
			} else {
				numbers = Arrays.copyOf(numbers, numbers.length +1);
				numbers[numbers.length - 1] = i;
			}
		}
		
		Arrays.sort(numbers);
		
		
		/*
		 * int arrayInt = sc.nextInt();
		 * 
		 * int commandInt = sc.nextInt(); int[] commandAy =
		 * Stream.of(String.valueOf(commandInt).split("")).mapToInt(Integer::parseInt).
		 * toArray();
		 * 
		 * String arrayString = Integer.toString(arrayInt);
		 */
        
	}
}
