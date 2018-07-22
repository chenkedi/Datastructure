package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Debug {
    public boolean exist(char[][] board, String word) {
        int[] xMove = new int[] {0, -1, 0, 1};
        int[] yMove = new int[] {-1, 0, 1, 0};
        char[] words = word.toCharArray();
        for(int i = 0; i < board.length; i++)
            for(int j = 0; j < board[0].length; j++) {
                if(board[i][j] == words[0]) {
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    visited[i][j] = true;
                    List<Character> res = new ArrayList<>();
                    res.add(words[0]);
                    search(board, visited, words, i, j, xMove, yMove, 1, res);
                    if(res.size() == words.length)
                        return true;
                }
            }
        return false;

    }

    public void search(char[][] board, boolean[][] visited, char[] words, int x, int y, int[] xMove, int[] yMove, int index, List<Character> res) {
        if(index == words.length)
            return;
        for(int i = 0; i < xMove.length; i++) {
            int xNext = x + xMove[i];
            int yNext = y + yMove[i];
            if(xNext >=0 && xNext <= board.length - 1 && yNext >= 0 && yNext <= board[0].length - 1) {
                if(board[xNext][yNext] == words[index] && !visited[xNext][yNext]) {
                    visited[xNext][yNext] = true;
                    res.add(words[index]);
                    search(board, visited, words, xNext, yNext, xMove, yMove, index + 1, res);
                    visited[xNext][yNext] = false;
                    res.remove(res.size() - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Debug de = new Debug();
//        System.out.println(de.exist(3));
    }
}
