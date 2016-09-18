package leetcode;

/**
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
 * @author chenkedi
 *此题当时没有发现i与0的相对位置的规律。
 *此题要求将数组中所有的0移动到数组的尾部，并不改变其他元素的相对位置。要进行原址移动，即占用空间为O(1),时间z最好为O(N)
 *此题的关键在于找出规律：用一个变量记住0的个数，则碰到不是0的数时与第一个零互换位置即可。而第一个0的位置刚好等于当前数字的索引减去前面0的个数
 *1、i当前位置如果不是0，且前面有0的话，则要将该元素与第一个0的位置互换
 *2、当做到第一点后，不难发现，如果i的前方有0，i往后移动一步后，前面一定是连续的0，且0的数目刚好等于i扫描过的0的数目。
 *3、所以当i遇到非0元素，且i已经遇到过0的话，第一个0的位置就是i-zeroCounts.
 *4、交换当前非0元素与0时，不需要第三个临时变量，因为0这个数是确定的
 */
public class __MoveZeros{
	 public void moveZeroes(int[] nums) {
		 int zeroCount=0;
		 for(int i=0;i<nums.length;i++){
			 if(nums[i]==0){
				 zeroCount++;
			 }else if(zeroCount>0){
				 nums[i-zeroCount]=nums[i];
				 nums[i]=0;
			 }
		 }
	 }

}
