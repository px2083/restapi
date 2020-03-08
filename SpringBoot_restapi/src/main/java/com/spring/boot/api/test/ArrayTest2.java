package com.spring.boot.api.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ArrayTest2 {
	
	static class Student {
		String name;
		int core;
		
		Student(String name, int core) {
			this.name = name;
			this.core = core;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getCore() {
			return core;
		}

		public void setCore(int core) {
			this.core = core;
		}
	}
	
	static class CompareStudent implements Comparator<Student> {
		@Override
		public int compare(Student first, Student seconde) {
			int firstValue = first.getCore();
			int secondeValue = seconde.getCore();
			
			if(firstValue > secondeValue) {
				return 0;
			} else {
				return -1;
			}
		}
	}
	
	public static void main(String[] args) {
		List<Student> studentList = new ArrayList<>();
		studentList.add(new Student("a", 5));
		studentList.add(new Student("b", 3));
		studentList.add(new Student("c", 4));
		studentList.add(new Student("d", 2));
		
		CompareStudent compareStudent = new CompareStudent();
		
		Collections.sort(studentList, compareStudent);
		//studentList.sort(Collections.reverseOrder());
		
		for(Student st : studentList) {
			System.out.println(st.getName());
		}
	}

}

