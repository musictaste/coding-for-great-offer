package class32;

public class Problem_0191_NumberOf1Bits {
    
	// n的二进制形式，有几个1？
	// 方法一：
	public static int hammingWeight1(int n) {
		int bits = 0;
		int rightOne = 0;
		while(n != 0) {
			bits++;
			// 最末尾的1
			rightOne = n & (-n);
			// 异或运算，n去掉最末尾的1
			n ^= rightOne;	
		}
		return bits;
	}

	// 方法二 ：常考，但是比较难
	public static int hammingWeight2(int n) {
		n = (n & 0x55555555) + ((n >>> 1) & 0x55555555);
		n = (n & 0x33333333) + ((n >>> 2) & 0x33333333);
		n = (n & 0x0f0f0f0f) + ((n >>> 4) & 0x0f0f0f0f);
		n = (n & 0x00ff00ff) + ((n >>> 8) & 0x00ff00ff);
		n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
		return n;
	}
	
}
