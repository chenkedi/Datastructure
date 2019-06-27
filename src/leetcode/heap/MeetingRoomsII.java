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
package leetcode.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import leetcode.array.Interval;

/**
 *Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

 Example 1:

 Input: [[0, 30],[5, 10],[15, 20]]
 Output: 2
 Example 2:

 Input: [[7,10],[2,4]]
 Output: 1
 NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/15
 **/
public class MeetingRoomsII {

    /**
     * 先将intervals按起始时间从小到大排序
     * 然后与小顶堆的第一个元素（即所有会议结束时间中最小的结束时间），若当前的开始时间（也是最小的开始时间）比
     * 之前的最早会议结束时间要大，则表明这两个会议无冲突，可安排至同一会议室。此时我们应该将堆中这个最早的结束时间去除，然后将
     * 当前遍历的interval的结束时间压入堆。即这个会议室，结束时间变为当前遍历的interval的结束时间了
     * 这个替换一定是最优的，因为是使用当前最早的开始时间，与当前最早的结束时间进行的对比
     * @param intervals
     * @return
     */
    int solve1(Interval[] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < intervals.length; i++) {
            if (!queue.isEmpty() && queue.peek() <= intervals[i].start)
                queue.poll();
            queue.offer(intervals[i].end);
        }
        return queue.size();

    }
}
