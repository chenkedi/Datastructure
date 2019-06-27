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
package leetcode.string;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 20. Valid Parentheses
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

 An input string is valid if:

 Open brackets must be closed by the same type of brackets.
 Open brackets must be closed in the correct order.
 Note that an empty string is also considered valid.

 Example 1:

 Input: "()"
 Output: true
 Example 2:

 Input: "()[]{}"
 Output: true
 Example 3:

 Input: "(]"
 Output: false
 Example 4:

 Input: "([)]"
 Output: false
 Example 5:

 Input: "{[]}"
 Output: true

 * @author chenkedi chenkedi@baidu.com
 * @date 2019/6/15
 **/
public class ValidParentheses {

    // 使用栈（注意java中的stack是jdk 1.2中的老古董了，java文档不建议使用）
    // 每遇到字符串中一个左偏旁时，就压入一个右偏旁
    // 当遇到右偏旁，则检查栈顶是否对应的就是该右偏旁，如果栈为空或者不是，则肯定无法配对
    // 遍历完字符串后，栈中为空，则表示完全匹配完成（与仅有()时，循环完判断count==0的做法一致）
    public boolean isValid(String s) {
        Deque<Character> deque = new ArrayDeque<>();

        for(char c : s.toCharArray()) {
            if (c == '(')
                deque.push(')');
            else if (c == '{')
                deque.push('}');
            else if (c == '[')
                deque.push(']');
            else if (deque.isEmpty() || deque.pop() != c)
                return false;
        }
        return deque.isEmpty();
    }
}
