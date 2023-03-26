package class34;

import java.util.HashMap;

// 测试链接 : https://leetcode.com/problems/insert-delete-getrandom-o1/
public class Problem_0380_InsertDeleteGetRandom {

	public class RandomizedSet {
		// key，下标
		private HashMap<Integer, Integer> keyIndexMap;
		// 下标，key
		private HashMap<Integer, Integer> indexKeyMap;
		private int size;

		public RandomizedSet() {
			keyIndexMap = new HashMap<Integer, Integer>();
			indexKeyMap = new HashMap<Integer, Integer>();
			size = 0;
		}

		public boolean insert(int val) {
			// 相同数字不插入
			if (!keyIndexMap.containsKey(val)) {
				keyIndexMap.put(val, size);
				indexKeyMap.put(size++, val);
				return true;
			}
			return false;
		}

		// 移除的时候，需要将indexKeyMap最后一个元素放到要移除的元素，防止出现㓊
		public boolean remove(int val) {
			if (keyIndexMap.containsKey(val)) {
				int deleteIndex = keyIndexMap.get(val);
				int lastIndex = --size;
				int lastKey = indexKeyMap.get(lastIndex);
				keyIndexMap.put(lastKey, deleteIndex);
				// 将最后一个位置放到要移除的位置
				indexKeyMap.put(deleteIndex, lastKey);
				// 移除掉元素和对应的下标
				keyIndexMap.remove(val);
				indexKeyMap.remove(lastIndex);
				return true;
			}
			return false;
		}

		// 随机：数组长度
		public int getRandom() {
			if (size == 0) {
				return -1;
			}
			int randomIndex = (int) (Math.random() * size);
			return indexKeyMap.get(randomIndex);
		}
	}

}
