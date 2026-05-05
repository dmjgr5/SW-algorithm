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
	//Stack과 다르게 전용 구현체가 존재하지 않는다. LinkedList를 구현체로 사용할 수 있다.
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

import java.util.Comparator;
import java.util.PriorityQueue;

class Student {
	int mathScore;
	int engScore;
	public Student(int mathScore, int engScore) {
		this.mathScore = mathScore;
		this.engScore = engScore;
	}
}
  
 

public class Test {
 
    public static void main(String[] args) {
 
    	// PriorityQueue 에서 쓰이기 위해서는 반드시 compare 로 우선순위 기준을 정해줘야함. 
    	// Integer, String, Double 등: 자바가 이미 비교 방법을 알고 있으므로 그냥 써도 됨.
        // Student, Item 등 사용자 정의 클래스: 자바가 비교 방법을 모르므로 반드시 알려줘야 함.
    	PriorityQueue<Student> pq  = new PriorityQueue<>(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				if (o2.mathScore == o1.mathScore)
					return o2.engScore - o1.engScore;
				else
					return o2.mathScore - o1.mathScore;
			}
    		
    	});
    	
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

//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////       Dijkstra      /////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
package Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Test {

	static int V, E; // 정점, 간선
	static int K; // 시작 정점
	static boolean[] visited;
	static int[] distance; // 최단 거리값 배열
	static List<Node>[] edge; // 그래프 표현하는 인접 리스트
	static int INF = 10000000;

	static class Node {
		int v; // 목적지
		int w; // 가중치

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}

	static void dijkstra(int start) {
		// pq 정의
		PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				return o1.w - o2.w;
			}
		});

		// 시작점 정하기
		pq.offer(new Node(start, 0));
		distance[start] = 0;

		// pq 돌리기
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			int now_v = now.v;

			// 방문 안한거만 체크
			if (!visited[now_v]) {
				visited[now_v] = true;

				// 목적지별 최단거리 갱신 후 pq 에 넣기
				for (Node sub : edge[now_v]) {
					int sub_dest = sub.v;
					int sub_weight = sub.w;

					if (!visited[sub_dest] && distance[now_v] + sub_weight < distance[sub_dest]) {
						distance[sub_dest] = distance[now_v] + sub_weight;
						pq.add(new Node(sub_dest, distance[sub_dest]));
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {

// V E
// K
// s e w
//    	5 6 
//    	1
//    	5 1 1
//    	1 2 2
//    	1 3 3
//    	2 3 4
//    	2 4 5
//    	3 4 6

		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("/home/dcpark/Downloads/CodingAlgorithmClass/dijkstra-input.txt")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		int K = Integer.parseInt(br.readLine());

		// 간선 정보 u,v,w
		edge = new LinkedList[V + 1]; // static List<Node>[] edge; => edge 배열 선언
		visited = new boolean[V + 1];
		distance = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			distance[i] = INF;
			edge[i] = new LinkedList<Node>(); // 각 정점에서의 edge 정보들을 LinkedList 로 정의
		}

		for (int i = 0; i < E - 1; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edge[u].add(new Node(v, w));
		}

		dijkstra(K);

		// 출발지로부터 각 정점 최단거리 출력
		for (int i = 1; i <= V; i++) {
			if (distance[i] == INF) {
				System.out.println("INF");
			} else {
				System.out.println(distance[i]);
			}
		}

	}

}

//result 
0
2
3
7
INF
