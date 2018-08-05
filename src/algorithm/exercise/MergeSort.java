package algorithm.exercise;

import java.util.Arrays;

public class MergeSort {

    public void sort(int[] arr) {
       mergSort(arr, new int[arr.length], 0, arr.length - 1);
    }

    private void mergSort(int[] arr, int[] help, int start, int end) {
        if(start < end) {
           int mid = (start + end) / 2;
            mergSort(arr, help, start, mid);
            mergSort(arr, help,mid + 1, end);
            merge(arr, help, start, mid, mid + 1, end);
        }
    }

    private void merge(int[] arr, int[] help, int p, int q, int m, int n) {
       for(int i = p; i <= n; i++)
           help[i] = arr[i];

       for(int j = p; j <= n; j++) {
          if(p > q)
              arr[j] = help[m++];
          else if(m > n)
              arr[j] = help[p++];
          else if(help[p] < help[m])
              arr[j] = help[p++];
          else
              arr[j] = help[m++];
       }

    }

    public static void main(String[] args) {
        int[] num = new int[] {4, 7,1, 3 ,2};
        MergeSort mergeSort = new MergeSort();
        mergeSort.sort(num);
        System.out.println(Arrays.toString(num));
    }
}
