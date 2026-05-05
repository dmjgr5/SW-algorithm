//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////       DFS      /////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////

package Test;

import java.util.Stack;

public class Test {
	// {{}, {2,3,8}, {1,6,8}, {1,5}, {5,7}, {3,4,7}, {2}, {4,5}, {1,2}};

	static int[][] arr = { {}, { 2, 3, 8 }, { 1, 6, 8 }, { 1, 5 }, { 5, 7 }, { 3, 4, 7 }, { 2 }, { 4, 5 }, { 1, 2 } };
	static Stack<Integer> stack = new Stack<Integer>();
	static boolean[] visited = new boolean[9];

	public static void main(String[] args) throws Exception {
 
		stack.push(1);

		while (stack.isEmpty() == false) {
			int s = stack.pop();
			if (visited[s] == true)
				continue;
			visited[s] = true;

			System.out.println("==> " + s);

			for (int next : arr[s]) {
				if (visited[next] == false) {

					stack.push(next);
				}
			}

		}
	}

}

//result
==> 1
==> 8
==> 2
==> 6
==> 3
==> 5
==> 7
==> 4


//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////       BFS      /////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
package Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Test {
	// {{}, {2,3,8}, {1,6,8}, {1,5}, {5,7}, {3,4,7}, {2}, {4,5}, {1,2}};

	static int[][] arr = { {}, { 2, 3, 8 }, { 1, 6, 8 }, { 1, 5 }, { 5, 7 }, { 3, 4, 7 }, { 2 }, { 4, 5 }, { 1, 2 } };
	static Stack<Integer> stack = new Stack<Integer>();
	static Queue<Integer> queue = new LinkedList<Integer>();
	static boolean[] visited = new boolean[9];

	public static void main(String[] args) throws Exception {
 
		queue.offer(6);

		while (queue.isEmpty() == false) {
			int s = queue.poll();
			if (visited[s] == true)
				continue;
			visited[s] = true;

			System.out.println("==> " + s);

			for (int next : arr[s]) {
				if (visited[next] == false) {

					queue.offer(next);
					 
				}
			}

		}
	}

}

//result
==> 6
==> 2
==> 1
==> 8
==> 3
==> 5
==> 4
==> 7


//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////       Union-Find      ///////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
package Test;

import java.util.Arrays;

public class Test {
	 
	static int[] parent = new int[10+1];
	
	static int find(int x) {
		if(parent[x] == x) return x;
		
		return parent[x] = find(parent[x]);
	}
	
	static boolean union(int x, int y) {
		int a = find(x);
		int b = find(y);
		
		if(a == b) return false;
		
		if(a <= b) parent[b]=a;
		else parent[a]=b;
		return true;
		 
	}
    
     // parent 출력
	public static void parentPrint() {
		System.out.println(Arrays.toString(parent));
	}

	public static void main(String[] args) {
		 for(int i = 1; i <= 10; i++) parent[i] = i;
		 parentPrint();
		 
		 union(1,2);
		 parentPrint();
		 
		 union(2,3);
		 parentPrint();
		 
		 union(4,5);
		 parentPrint();
		 
		 
		 union(4,3);
		 parentPrint();
		 
		 System.out.println(find(3));
		 System.out.println(find(5));
		 
		 
	}
}

//result
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
[0, 1, 1, 3, 4, 5, 6, 7, 8, 9, 10]
[0, 1, 1, 1, 4, 5, 6, 7, 8, 9, 10]
[0, 1, 1, 1, 4, 4, 6, 7, 8, 9, 10]
[0, 1, 1, 1, 1, 4, 6, 7, 8, 9, 10]
1
1


//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////    Priority Queue - basic  /////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////


package Test;

import java.util.Collections;
import java.util.PriorityQueue;

public class Test {
	
	public static void main(String[] args) {
		PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		pq.offer(1);
		pq.offer(6);
		pq.offer(2);
		pq.offer(8);
		pq.offer(7);
		
		while(!pq.isEmpty()) {
			System.out.println(pq.poll());

		}
		
	}
	  
}

//result
8
7
6
2
1

	
//////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////    ## PriorityQueue - object & comparable //////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////


package Test;

import java.util.PriorityQueue;

class Student implements Comparable<Student>{
	int mathScore;
	int engScore;
	public Student(int mathScore, int engScore) {
		this.mathScore = mathScore;
		this.engScore = engScore;
	}
	@Override
	public int compareTo(Student o) {
		if (this.mathScore == o.mathScore)
			return o.engScore - this.engScore;
		else
			return o.mathScore - this.mathScore;
	}
}
  
 

public class Test {
 
    public static void main(String[] args) {
 
    	PriorityQueue<Student> pq  = new PriorityQueue<Student>();
    	
    	pq.offer(new Student(70,50));
    	pq.offer(new Student(60,50));
    	pq.offer(new Student(70,40));
    	pq.offer(new Student(70,40));
    	pq.offer(new Student(80,70));
    	pq.offer(new Student(20,70));
    	pq.offer(new Student(10,50));
    	pq.offer(new Student(70,50));
    	
    	System.out.println("mathScore" + " : " + "engScore");
    	while(!pq.isEmpty()) {
    		Student s = pq.poll();
    		System.out.println(s.mathScore + " : " + s.engScore);
    	}
 
    }
}

//result
mathScore : engScore
80 : 70
70 : 50
70 : 50
70 : 40
70 : 40
60 : 50
20 : 70
10 : 50

