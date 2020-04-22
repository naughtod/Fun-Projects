import java.util.*;
import java.lang.*;

public class TwentyFourSolver
{
	private Scanner in = new Scanner(System.in);
	public static int cards[] = new int[4];
	private final char operators[] = {'+','-','*','/'};
	
	public static void main(String[] args) {
		TwentyFourSolver newGame = new TwentyFourSolver(); 
		while (true) {
			System.out.print("$");
			System.out.println(newGame.twentyFour());
			//System.out.println(Arrays.toString(cards));	 
		}
	}
	
	private String twentyFour() {
		/* read in array */
		for (int i=0;i<4;i++) { 
			cards[i] = in.nextInt();
		}
		
		/* sort array */
		Arrays.sort(cards);

		/* try loop through operators */
		int arr1[] = new int[64];int arr2[] = new int[64];
		int comb1[] = new int[4];int comb2[] = new int[4]; 
		do {
			arr1[0] = cards[0];
			int n=1;
			int counter=0;
			/* linear combination */
			while (counter<3) { 
				arr1 = findPossibleVals(arr1, n, wrapp(cards[++counter]), 1);
				n = arr1.length;	
			}
			/* non linear */
			comb1 = findPossibleVals(wrapp(cards[0]), 1, wrapp(cards[1]), 1);
			comb2 = findPossibleVals(wrapp(cards[2]), 1, wrapp(cards[3]), 1);
			arr2 = findPossibleVals(comb1,4,comb2,4);
			/* check for winner and if found return final operation */
			int index=0;
			if ((index=isWin(arr1,n))>=0){
				return String.valueOf(operate(arr1[index],switchOP(operators[index%4]),cards[counter])) + operators[index%4] + cards[counter];
			} else if ((index=isWin(arr2,arr2.length))>=0) {
				return String.valueOf(comb1[index/16]) + operators[index%4] + comb2[index%16/4];
			}
		} while (findNextPermutation(cards));

		return "no solution";
	}

	private int[] wrapp(int a) {
		return new int[]{a};
	}

	private int isWin(int[] arr, int n) {
		for (int i=0;i<n;i++) {
			if (arr[i]==24) 
				return i;
		}
		return -1;
	}

	private int[] findPossibleVals(int[] arr1, int n, int[] arr2, int m) {
		int result, newArr[] = new int[4*n*m];
		for (int i=0;i<n;i++){
			for (int j=0;j<m;j++) {
				for (int k=0;k<4;k++) {
					result = operate(arr1[i], operators[k], arr2[j]);
					newArr[i*m*4+j*4+k] = result;
				}
			}
		}	
		// System.out.println(Arrays.toString(newArr));
		return newArr;
	}

	private char switchOP(char operator) {
		if (operator=='+') {
			return '-';
		} else if (operator=='-') {
			return '+'; 
		} else if (operator=='*') {
			return '/';
		} else {
			return '*';
		}
	}

	private int operate(int a, char operator,int b) {
		if (a==0||b==0) {
			return 0;
		} else if (operator=='+') {
			return a+b;
		} else if (operator=='-') {
			return Math.abs(a-b);
		} else if (operator=='*') {
			return a*b;
		} else {
			if (a % b > 0) return 0;
			return a/b;
		}
	}
		
	private int[] swap(int data[], int left, int right) {  
		int temp = data[left]; 
		data[left] = data[right]; 
		data[right] = temp; 
		return data; 
	} 
	
	private int[] reverse(int data[], int left, int right) 
	{ 
		// Reverse the sub-array 
		while (left < right) { 
			int temp = data[left]; 
			data[left++] = data[right]; 
			data[right--] = temp; 
		}  
		return data; 
	} 
	
	// Function to find the next permutation 
	// of the given integer array 
	private boolean findNextPermutation(int data[]) { 
		// If the given dataset is empty 
		// or contains only one element 
		// next_permutation is not possible 
		if (data.length <= 1) 
			return false; 
	
		int last = data.length - 2; 
	
		// find the longest non-increasing suffix 
		// and find the pivot 
		while (last >= 0) { 
			if (data[last] < data[last + 1]) { 
				break; 
			} 
			last--; 
		} 
	
		// If there is no increasing pair 
		// there is no higher order permutation 
		if (last < 0) 
			return false; 
	
		int nextGreater = data.length - 1; 
	
		// Find the rightmost successor to the pivot 
		for (int i = data.length - 1; i > last; i--) { 
			if (data[i] > data[last]) { 
				nextGreater = i; 
				break; 
			} 
		} 
	
		// Swap the successor and the pivot 
		data = swap(data, nextGreater, last); 
	
		// Reverse the suffix 
		data = reverse(data, last + 1, data.length - 1); 
	
		// Return true as the next_permutation is done 
		return true; 
	} 

}
