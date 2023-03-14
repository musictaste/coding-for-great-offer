package class14;

// 测试链接：https://leetcode.com/problems/first-missing-positive/
public class Code06_MissingNumber {

	public static int firstMissingPositive(int[] arr) {
		// l是盯着的位置
		// 0 ~ L-1有效区
		int L = 0;
		int R = arr.length;
		while (L != R) {
			// L位置的数==L+1，是想要的数
			if (arr[L] == L + 1) {
				L++;
			} else if (arr[L] <= L || arr[L] > R || arr[arr[L] - 1] == arr[L]) { // 垃圾的情况
				// L=17， R=31
				// arr[L]=17
				// arr[L]=32
				// arr[L]=19,但是18位置已经填充了数
				// 当前位置的数进垃圾区，垃圾区扩充
				swap(arr, L, --R);
			} else {
				// L < arr[L] <= R
				//  L=17， R=31  arr[L]=19, L位置的数到它该去的位置上，交换数组再次判断
				swap(arr, L, arr[L] - 1);
			}
		}
		return L + 1;
	}

	public static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
