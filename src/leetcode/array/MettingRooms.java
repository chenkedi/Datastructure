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

import java.util.Collections;
import java.util.List;

/**
 * [LeetCode] Meeting Rooms 会议室


 Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

 For example,
 Given [[0, 30],[5, 10],[15, 20]],
 return false.
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/15
 **/
public class MettingRooms {
    // 先排序，再遍历，看前一个interval的终点是否大于下一个interval的起点
    // 如果大于，则表示时间有重叠，一个人不可能参加会议
    boolean solve1(List<Interval> intervals) {
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));
        for (int i = 1; i < intervals.size(); ++i) {
            if (intervals.get(i).start < intervals.get(i - 1).end) {
                return false;
            }
        }
        return true;
    }
}
