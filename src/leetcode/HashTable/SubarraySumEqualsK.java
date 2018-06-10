package leetcode.HashTable;


import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public static void main(String[] args) {

    }

    /**
     * 优化了空间复杂度的brutal false 算法（另外的方法是将sum[0] ~ sum[n]存储，然后用sum（i,j) = sum(0, j) - sum(0, i)来求
     * @param nums
     * @param k
     * @return
     */
    public int solve1(int nums[], int k) {
        int count = 0;
        for(int i = 0; i < nums.length; i++){
            int sum = 0;
            for(int j = i; j < nums.length; j++) {
                sum += nums[j];
                if(sum == k)
                    count ++;
            }
        }
        return count;
    }

    public int solve2(int nums[], int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(map.containsKey(sum - k))
                count += map.get(sum -k);
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
