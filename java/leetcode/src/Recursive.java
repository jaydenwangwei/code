import java.util.ArrayList;

/**
 * 数学递归函数
 *
 * 假设有四种面额的钱币，1 元、2 元、5 元和 10 元，而您一共给我 10 元，那您可以奖赏我 1 张 10 元，或者 10 张 1 元，或者 5 张
 * 1 元外加 1 张 5 元等等。如果考虑每次奖赏的金额和先后顺序，那么最终一共有多少种不同的奖赏方式呢？”
 *
 */
public class Recursive {

	// 四种面额的纸币
	public static long[] rewards = { 1, 2, 5, 10 };



	/**
	 * @Description 使用函数的递归（嵌套）调用，找出所有可能得奖赏组合
	 * @param totalReward
	 *            奖赏总金额
	 * @param result
	 *            保存当前解
	 */
	public static void get(long totalReward, ArrayList<Long> result) {
		// 当totalReward等于0时，证明它是满足条件的解，结束嵌套调用，输出解
		if (totalReward == 0) {
			System.out.println(result);
			return;
		} else if (totalReward < 0) {
			// 当totalReward小于0时，证明它不是满足条件的解，不输出
			return;
		} else {
			for (int i = 0; i < rewards.length; i++) {
				// 由于有4种情况，需要clone当前的解并传入被调用的函数
				ArrayList<Long> newResult = (ArrayList<Long>) (result.clone());
				// 记录当前的选择，解决一点问题
				newResult.add(rewards[i]);
				get(totalReward - rewards[i], newResult);
			}
		}
	}



	public static void main(String[] args) {
		// 测试一下总金额为10元的时候
		int totalReward = 10;
		get(totalReward, new ArrayList<Long>());
	}

}
