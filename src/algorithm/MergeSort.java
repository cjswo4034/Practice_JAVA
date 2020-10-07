package algorithm;

import java.util.Arrays;

public class MergeSort {
	public static void main(String[] args) {
		Integer[] array = {0,5,2,3,4,8,7,9,1,6};
		Integer[] tmp = new Integer[array.length];
		
		int left = 0;
		int right = array.length - 1;

		System.out.println(Arrays.toString(array));
		merge(array, tmp, left, right);
		System.out.println(Arrays.toString(array));
	}
	
	static void merge(Integer[] array, Integer[] tmp, int left, int right) {
		if(array == null || array.length == 0 || left >= right)
			return;
		
		int middle = (left + right) / 2;
		merge(array, tmp, left, middle);
		merge(array, tmp, middle + 1, right);
		mergesort(array, tmp, left, middle + 1, right);
	}
	
	static void mergesort(Integer[] array, Integer[] tmp, int left, int right, int rightEnd) {
		int leftEnd = right - 1;
		int tmpIndex = left;

		while(left <= leftEnd && right <= rightEnd) {
			if(array[left] < array[right]) tmp[tmpIndex++] = array[left++];
			else tmp[tmpIndex++] = array[right++];
		}
		
		while(left <= leftEnd) {
			tmp[tmpIndex++] = array[left++];
		}
		
		while(right <= rightEnd) {
			tmp[tmpIndex++] = array[right++];
		}
		
		while(0 <= rightEnd) {
			array[rightEnd] = tmp[rightEnd--];
		}
	}
}
