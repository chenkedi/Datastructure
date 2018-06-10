package leetcode.HashTable;

public class MaximalRectangle {
    public static void main(String[] args) {
        char[][] matrix = new char[][] {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
  {'1','1','1','1','1'},
  {'1','0','0','1','0'}};
        System.out.println(maximalRectangle(matrix));

        System.out.println(maximalRectangle(new char[][]{{'1'}}));
    }
        public static int maximalRectangle(char[][] matrix) {
            if(matrix.length == 1 && matrix[0].length == 1 && matrix[0][0] == 49)
                return 1;
            int[][] longestIndexOfRow = new int[matrix.length][2];
            for (int i = 0; i < matrix.length; i++) {
                longestIndexOfRow[i] = findMaxSubArray(matrix[i]);
            }
            int start = 0, end = 0, max = 0;
            int index = 0;
            for(int i = 1; i < matrix.length; i++) {
                int lastStart = longestIndexOfRow[i - 1][0];
                int lastEnd = longestIndexOfRow[i - 1][1];
                int rowStart = longestIndexOfRow[i][0];
                int rowEnd = longestIndexOfRow[i][1];
                if(rowStart != lastStart || rowEnd != lastEnd || i == matrix.length - 1) {
                    if(i - index > max) {
                        max = (i - index) * (lastEnd - lastStart + 1);
                        start = index + 1;
                        end = i;
                    }
                } else {
                    index = i;
                }
            }
            return max;

        }
        private static int[] findMaxSubArray(char[] row) {
            int start = 0, end = 0, max = 0;
            int index = -1;
            for(int i = 0; i < row.length; i++) {
                if(row[i] == '1'){
                    if (i - index > max){
                        max = i - index;
                        start = index + 1;
                        end = i;
                    }
                } else {
                    index = i;
                }
            }
            return new int[]{start, end};
        }
}
