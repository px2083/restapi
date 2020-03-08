package com.spring.boot.api.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class ArrayTest {
	
	public static void main(String[] args) {
//		퀵정렬
//		int[] data = {1,10,3,6,4,8,7,2,5,9};
//		quickSort(data, 0, data.length - 1);
//		
//		for (int i = 0; i < data.length; i++) {
//			System.out.print(data[i] + " ");
//		}
		
		Scanner sc = new Scanner(System.in);
		int number = sc.nextInt();
		Integer[] arr = new Integer[number];
		for (int i = 0; i < number; i++) {
			arr[i] = sc.nextInt();
		}
		
		List<Integer> list = new ArrayList();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		
		Arrays.sort(arr, new java.util.Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                String s1 = String.valueOf(a), s2 = String.valueOf(b);
                return Integer.parseInt(s1 + s2) + Integer.parseInt(s2 + s1);
            }
        });
		// quickSort(arr, 0, number - 1);
		
		for (int i = 0; i < arr.length - 1; i++) {
			System.out.printf("%d ", arr[i] + arr[i+1]);
		}
	}

	public static void quickSort(int[] data, int start, int end) {
		if(start >= end) {
			return;
		}
		
		int key = start;
		int i = start+1;
		int j = end;
		int temp;
		
		while (i <= j) {
			while(data[i] <= data[key] && i < end) {
				i++;
			}
			while(data[j] >= data[key] && j > start) {
				j--;
			}
			
			if(i >= j) {
				temp = data[j];
				data[j] = data[key];
				data[key] = temp;
			} else {
				temp = data[i];
				data[i] = data[j];
				data[j] = temp;
			}
		}
		
		
		quickSort(data, start, j-1);
		quickSort(data, j+1, end);
	}
}

