package class34;

public class Problem_0287_FindTheDuplicateNumber {

	public static int findDuplicate(int[] nums) {
		if (nums == null || nums.length < 2) {
			return -1;
		}
		int slow = nums[0];
		int fast = nums[nums[0]];
		// 快指针走两步，慢指针走一步
		while (slow != fast) {
			slow = nums[slow];
			fast = nums[nums[fast]];
		}
		// 快慢指针相遇，快指针到头结点，然后快慢指针各走一步
		fast = 0;
		while (slow != fast) {
			fast = nums[fast];
			slow = nums[slow];
		}
		// 再次相遇就是入环节点，也是重复数字
		return slow;
	}

}
