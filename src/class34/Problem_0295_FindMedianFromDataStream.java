package class34;

import java.util.PriorityQueue;

public class Problem_0295_FindMedianFromDataStream {

	class MedianFinder {

		private PriorityQueue<Integer> maxh;
		private PriorityQueue<Integer> minh;

		public MedianFinder() {
			// 大根堆
			maxh = new PriorityQueue<>((a, b) -> b - a);
			// 小根堆
			minh = new PriorityQueue<>((a, b) -> a - b);
		}

		public void addNum(int num) {
			// 大根堆为空，或 大根堆堆定元素 >= 当前数字，例如 9 > 7,进大根堆
			if (maxh.isEmpty() || maxh.peek() >= num) {
				maxh.add(num);
			} else {
				minh.add(num);
			}
			balance();
		}

		public double findMedian() {
			// 如果大根堆和小根堆数量一样，各弹出一个元素，求平均
			if (maxh.size() == minh.size()) {
				return (double) (maxh.peek() + minh.peek()) / 2;
			} else {
				// 哪个对的数量多，就哪个对弹出一个元素，该元素就是中位数
				return maxh.size() > minh.size() ? maxh.peek() : minh.peek();
			}
		}

		private void balance() {
			// 如果大根堆和小根堆，数量差=2，数量多的堆弹出一个元素，放到另一个堆中
			if (Math.abs(maxh.size() - minh.size()) == 2) {
				if (maxh.size() > minh.size()) {
					minh.add(maxh.poll());
				} else {
					maxh.add(minh.poll());
				}
			}
		}

	}

}