/**
 * Copyright 2018 Baidu, Inc. All Rights Reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package leetcode.array;

/**
 * 已知一个整型数组，数组相邻两个元素之间的差值始终是1或者-1(或者说绝对值为1）
 * 给定一个数n，在数组中查找n第一次出现的位置
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/25
 **/
public class FindNumberInAbsOneArray {

    /**
     * 该题的核心点在于：利用当前位置的数与待查找数的差来实现跨越式搜索
     * number 减去数组中第一个数的差，表明这个差代表的位置之前的数，一定比number小，所以直接查看该位置数是否等于number
     * 不等于则直接将number 再减去该位置的数，得到的差再加入到之前的差上，以此跳跃查找
     * @param nums
     * @param number
     * @return
     */
    int solve(int[] nums, int number) {
        int nextSearchIndex = Math.abs(number - nums[0]);

        while(nextSearchIndex < nums.length) {
            if (nums[nextSearchIndex] == number)
                return nextSearchIndex;
            nextSearchIndex += Math.abs(number - nums[nextSearchIndex]);
        }
        return -1;
    }
}
