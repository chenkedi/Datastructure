package leetcode;

import java.util.Scanner;

/**
 * You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first turn to remove the stones.

Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win the game given the number of stones in the heap.

For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend.
 * @author chenkedi
 *题目简而言之就是两个人玩取石子游戏，每次仅可以取1、2或3个石子，最后一个石子被谁取走算谁赢。默认自己先取石子。
 *输入石子的个数，返回true或者false表名自己是输还是赢
 *4个石子，先取的人必输，因为最多取3个。
 *5、6、7个石子，先取的人必赢，因为可以分别取1、2、3个石子来将情况转化为4个石子，但是对方先取的局面，所以对方必输。
 *8个石子时，无论怎么取，都转化为对方取5、6、7个石子的情况，所以对方必赢。
 *依次类推，发现，当石子个数为4的整数倍的时候，先取的人必输
 */
public class NimGame{
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextInt()){
			int n = sc.nextInt();
			System.out.println(judge(n));
		}
		sc.close();
	}

	private static boolean judge(int n) {	
		return n%4!=0;
		//注意，此处4刚好是2的幂，可以模仿HashMap中使用位与方式取余来提高效率
		//return n&(4-1)!=0;
	}

}
