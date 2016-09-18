package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.
 * @author chenkedi
 * 简而言之，判断一个整数数组中是否至少有一个数出现了两次，如果有返回true，否则返回false
 */
public class ContainsDuplicate{
   public void main(String[] args){
	   
   }
   
   /**
    * 使用位图记录的方式，由于整数的范围到达了20亿以上，所以如果没有指定这个整数数组中整数的范围，此方法会超过内存限制
    * @param nums
    * @return
    */
   public static boolean isRepeated_a(int[] nums){
	   byte[] a = new byte[10240];
	   for(int i=0;i<nums.length;i++){
		   int div = nums[i]/8;
		   int mod = nums[i]%8;
		   if((a[div]&(1<<mod))!=0)
			   return true;
		   else
			   a[div]|=(1<<mod);
	   }
	   return false;
   }
   
   /**
    * 此方法使用hashmap记录每个数字是否出现过，由于每次均要判断哈希表中是否已经存在该数字的key，所以会超过时间限制
    * @param nums
    * @return
    */
   public static boolean isReapeated_b(int[] nums){
	   if(nums.length==0)
           return true;
       Map<Integer,Boolean> map = new HashMap<>(2048);
       
       for(int i=0;i<nums.length;i++){
           if(!map.containsKey(nums[i])){
               map.put(nums[i],true);
           }else{
               return false;
           }
       }
       return true;
   }
   
   /**
    * 此方法利用了HashSet天然不包含重复元素的特性，将所有数字加入set后，判断set.size()与原数组的大小，不相等则表示原数组有重复数字
    * @param nums
    * @return
    */
   public static boolean isRepeated(int[] nums){
	   Set<Integer> set = new HashSet<>();
       for(int i=0;i<nums.length;i++)
           set.add(nums[i]);
       return nums.length!=set.size();
   }
}
