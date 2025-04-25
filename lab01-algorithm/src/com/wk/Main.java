package com.wk;

public class Main {
	
	int binarySearch(int[] data,int target) {
		int low = 0;
		int high = data.length-1;
		
		while (low <= high ) {
			// Dividing data in half
			int mid = (low + high) / 2;
			if (data[mid] == target) {
				return mid;
			} else if (data[mid] < target) {
				low = mid + 1;
			} else {
				high = mid -1;
			}
				
						
		}
		
		// -1 for not found
		return -1;
	}

	public static void main(String[] args) {
		System.out.println("Start binary search");
		
		Main m = new Main();
		
		// Create sorted array with data 0 to 1_000_000
		int[] data = new int[1_000_001];
		for (int i = 0; i < data.length; i++) {
			data[i] = i;
		}
		
		System.out.println("First number: " + data[0]); // 0
        System.out.println("Last number: " + data[data.length-1]); // 1_000_000
		
		// Find index of 900_000
		int index = m.binarySearch(data,900_000);
		
		System.out.printf("Found Target at Index %d \n",index); // should print 900_000
		
		
		// Another Test
		int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15};
		System.out.println(m.binarySearch(sortedArray, 7)); // should print 3
		System.out.println(m.binarySearch(sortedArray, 16)); // should print -1
		
		
	}

}
