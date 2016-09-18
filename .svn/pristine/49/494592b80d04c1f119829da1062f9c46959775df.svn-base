package 滴滴;
/**
3 5
2 4 2
1 3
3 5
3 7
5 9
1 10
 */
import java.io.*;
import java.util.*;
public class Main {
    public static void main(String[] args) throws Exception{
        //使用缓冲加快IO
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        String[] num = reader.readLine().split(" ");
        //桌子数
        int N = Integer.valueOf(num[0]);
        //客人批数
        int M = Integer.valueOf(num[1]);
        //使用数组存储每桌的最大人数,从小到大排列
        int[] deskCapacity = new int[N];
        String[] deskStr = reader.readLine().split(" ");
        for(int i = 0; i < N; i++)
            deskCapacity[i] = Integer.valueOf(deskStr[i]);
        Arrays.sort(deskCapacity);
        
        //使用treemap以预计消费金额为key降序存储每批客人的键值对
        Map<Integer,Integer> guestMap = new TreeMap<>(new Comparator<Integer>(){
            public int compare(Integer a, Integer b){
                return b - a;
            }
        });
        
        for(int j = 0; j < M; j++){
            String[] guest = reader.readLine().split(" ");
            guestMap.put(Integer.valueOf(guest[1]), Integer.valueOf(guest[0]));
        }
        
        long maxCash = 0;
        for(Map.Entry<Integer,Integer> entry : guestMap.entrySet()){
            int cash = entry.getKey();
            int people = entry.getValue();
            for(int i = 0; i < N; i++){
                if(deskCapacity[i] >= people){
                    maxCash += cash;
                    deskCapacity[i] = -1;
                    break;
                }
            }
        }
        System.out.print(maxCash);
        
    
        
    }
}
