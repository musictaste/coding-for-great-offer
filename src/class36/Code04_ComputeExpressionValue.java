package class36;

// 来自美团
// () 分值为2
// (()) 分值为3
// ((())) 分值为4
// 也就是说，每包裹一层，分数就是里面的分值+1
// ()() 分值为2 * 2
// (())() 分值为3 * 2
// 也就是说，每连接一段，分数就是各部分相乘，以下是一个结合起来的例子
// (()())()(()) -> (2 * 2 + 1) * 2 * 3 -> 30
// 给定一个括号字符串str，已知str一定是正确的括号结合，不会有违规嵌套
// 返回分数
public class Code04_ComputeExpressionValue {

	// 思路：计算器的递归套路，递归函数返回从i位置以后计算的结果+计算到哪个位置
	public static int sores(String s) {
		return compute(s.toCharArray(), 0)[0];
	}

	// s[i.....] 遇到 ')' 或者 终止位置  停！
	// 返回值：int[]  长度就是2
	// 0 ：分数是多少
	// 1 : 来到了什么位置停的！
	public static int[] compute(char[] s, int i) {
		if (s[i] == ')') {
			// 如果上来就遇到有括号，结果=1
			return new int[] { 1, i };
		}
		int ans = 1; // ans = 1 * a * b * c
		while (i < s.length && s[i] != ')') {
			// 说明当前位置是(,遇到左括号直接调递归
			int[] info = compute(s, i + 1);
			// 递归函数返回的结果+1
			ans *= info[0] + 1;
			i = info[1] + 1;
		}
		return new int[] { ans, i };
	}

	public static void main(String[] args) {

		String str1 = "(()())()(())";
		System.out.println(sores(str1));

		// (()()) + (((()))) + ((())())
		// (()()) -> 2 * 2 + 1 -> 5
		// (((()))) -> 5
		// ((())()) -> ((2 + 1) * 2) + 1 -> 7
		// 所以下面的结果应该是175
		String str2 = "(()())(((())))((())())";
		System.out.println(sores(str2));

		// (()()()) + (()(()))
		// (()()()) -> 2 * 2 * 2 + 1 -> 9
		// (()(())) -> 2 * 3 + 1 -> 7
		// 所以下面的结果应该是63
		String str3 = "(()()())(()(()))";
		System.out.println(sores(str3));
	}

}
