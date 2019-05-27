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
package leetcode;

/**
 * Power of Three
 * Given an integer, write a function to determine if it is a power of three.
 *   Follow up:
 * Could you do it without using any loop / recursion?
 * @author chenkedi chenkedi@baidu.com
 * @date 2019/5/26
 **/
public class PowerOfThree {

    /**
     * 循环解法，当前的n可以整除3时，就继续除。若n为3的幂，则最后n一定会变为1（兼容了n直接等于1时，n也是3的幂）
     * 注意将n = 0的情况排除在外，否则会成为死循环
     * 此法时间复杂度O(log3(n))
     *
     * 此法可推广至任意数的幂
     * @param n
     * @return
     */
    public boolean solve_1(int n) {
        if (n < 1)
            return false;
        while(n % 3 == 0) {
            n = n / 3;
        }
        return n == 1;
    }


    /**
     * 利用java的Integer.toString(n, 指定进制）函数，将10进制转为三进制。任何该进制的数，要为该进制的次幂，只能是1打头，其余都是0
     * 此法时间复杂度为O(log3(n)) 进制转换使用第一种方法实现的
     * 空间复杂度也是O(log3(n)),因为字符串长度程对数增长
     *
     * 此法也可推广至任意整数的幂
     * @param n
     * @return
     */
    public boolean solve_2(int n) {
        String base3 = Integer.toString(n, 3);
        return base3.matches("^10*$");
    }

    /**
     * 对数公式法，注意要使用log10，而不能用自然对数（会存在浮点数精度带来的问题）
     * n = 3 ^ i
     * i = log3(n) = log10(3) / log10(n)
     * 如果i的小数位数为0，即上述除法可被整除，则n就是3的次幂
     *
     * 此法的复杂度由log运算的复杂度决定，与具体的语言实现和编译器有关
     * 此法也可推广至任意整数的幂
     * @param n
     * @return
     */
    public boolean solve_3(int n) {
        return (Math.log10(3) / Math.log10(n)) % 1 == 0;
    }

    /**
     * 由于输入的n为整型，所以最大值为2^31 - 1
     * 可以通过log3(max_value)取下整求得不超过整型最大值的3的次幂
     * 只要n能被次数整除，则n一定是3的次幂
     * 若将概述硬编码，则复杂度为O（1）
     *
     * 改法可推广值任意数的次幂
     * @param n
     * @return
     */
    public boolean solve_4(int n) {
        int maxPowerOf3 = (int) Math.pow(3, (int) (Math.log10(Integer.MAX_VALUE) / Math.log10(3)));
        return n > 0 && maxPowerOf3 % n == 0;
    }


}
