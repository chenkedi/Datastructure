package leetcode.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.sun.tools.javah.Gen;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 For example, given n = 3, a solution set is:

 [
 "((()))",
 "(()())",
 "(())()",
 "()(())",
 "()()()"
 ]
 */
public class GenerateParentheses {
  /**
   * 回溯法解法，模拟求国际象棋用马走遍所有网格的解法
   * @param n
   * @return
   */
  public List<String> solve_1(int n) {
      List<String> res = new ArrayList<>();
      // 下一步走法的全集，对于国际象棋中的马来说是8个可能性，对于括号生成是2种可能
      char[] move = new char[]{'(', ')'};
      generateParentheses(res, move, n, new char[2 * n], 0);
      return res;

  }

  private void generateParentheses(List<String> res, char[] move, int n, char[] component, int index) {
      if(index == 2*n) {
          if(check(component)) {
              res.add(new String(component));
          }
         return;
      }

      for(int i = 0; i < move.length; i++) {
          // 由于此处使用字符数组存储，index控制写入位置的方式，所以任何位置可以直接覆写，而不必考虑使用list时, 每次递归调用
          // 完成后需要回溯的删除最后一个元素
         component[index] = move[i];
         generateParentheses(res, move, n, component, index + 1);
      }

  }

  private boolean check(char[] component) {
      // 使用cnt统计左括号和右括号的个数
      int cnt = 0;
      for(char c : component) {
          if(c == '(')
              cnt++;
          else
              cnt--;
          // 当发现cnt小于0时，即存在右括号在前（此时即使有左括号与之配对，也是不合法情况）,一定不符合，立即返回false
          if(cnt < 0)
              return false;
      }
      // 左右括号相等，则表示组合合法
      return cnt == 0;
  }


    /**
     * 循环解法
     * 该循环解法由于没能在生成结果时就避免重复和不可能，所以速度比较慢
     * @param n
     * @return
     */
  public List<String> solve_2(int n) {
      Set<List<Character>> res = new HashSet<>();
      char[] candidates = new char[2*n];

      for(int i = 0; i < n; i++) {
         candidates[i] = '(';
      }
      for(int i = n; i < 2 * n; i++) {
          candidates[i] = ')';
      }
      List<Character> l0 = new LinkedList<>();
      l0.add(candidates[0]);

      res.add(l0);

      for(int i = 1; i < 2 * n; i++) {
          Set<List<Character>> tmpRes = new HashSet<>();
          int start = candidates[i] == ')' ? 1 : 0;
          for(int j = start; j <= i; j++) {
              for(List<Character> component : res) {
                  List<Character> tmp = new LinkedList<>(component);
                  tmp.add(j, candidates[i]);
                  if(i == 2 * n - 1){
                      if(check(tmp)) {
                          tmpRes.add(tmp);
                      }
                  } else {
                      tmpRes.add(tmp);
                  }
              }
          }
          res = tmpRes;
      }
      return res.parallelStream().map(this::transform).collect(Collectors.toList());

  }

    private boolean check(List<Character> component) {
        // 使用cnt统计左括号和右括号的个数
        int cnt = 0;
        for(char c : component) {
            if(c == '(')
                cnt++;
            else
                cnt--;
            // 当发现cnt小于0时，即存在右括号在前（此时即使有左括号与之配对，也是不合法情况）,一定不符合，立即返回false
            if(cnt < 0)
                return false;
        }
        // 左右括号相等，则表示组合合法
        return cnt == 0;
    }

    private String transform(List<Character> component) {
        StringBuilder builder = new StringBuilder(component.size());
        for(char c : component){
            builder.append(c);
        }
        return builder.toString();
    }


    /**
     * 尾递归解法, 目前最优
     * @param n
     * @return
     */
    public List<String> solve_3(int n) {
        List<String> res = new ArrayList<>();
        generate(res, n, "",0, 0);
        return res;
    }

    public void generate(List<String> res, int n, String str, int open, int close) {
        if(str.length() == 2 * n)
            res.add(str);
        if(open < n)
            generate(res, n, str + "(", open + 1, close);
        if(close < open)
            generate(res, n, str + ")", open, close + 1);

    }
    public static void main(String[] args) {
        GenerateParentheses generate = new GenerateParentheses();
        System.out.println(generate.solve_1(3));
        System.out.println(generate.solve_2(3));
        System.out.println(generate.solve_3(3));
    }
}
