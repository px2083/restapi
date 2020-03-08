package com.spring.boot.api.test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.mysema.commons.lang.Pair;


public class ArrayTest3 {
		
	public static void main(String[] args) {
		List<Pair<Integer, String>> studentList = new ArrayList<>();
		studentList.add(new Pair<Integer, String>(5,"a"));
		studentList.add(new Pair<Integer, String>(4,"b"));
		studentList.add(new Pair<Integer, String>(3,"c"));
		studentList.add(new Pair<Integer, String>(2,"d"));
		studentList.add(new Pair<Integer, String>(1,"e"));
	
		studentList.sort(new Comparator<Pair<Integer, String>>() {
			@Override
	        public int compare(Pair<Integer, String> o1, Pair<Integer, String> o2) {
	            if (o1.getFirst() > o2.getFirst()) {
	                return 1;
	            } else if (o1.getFirst().equals(o2.getFirst())) {
	                return 0; // You can change this to make it then look at the
	                          //words alphabetical order
	            } else {
	                return -1;
	            }
	        }
		});
		
		for (Pair<Integer, String> pair : studentList) {
			System.out.println(pair.getFirst());
		}
	}
}

