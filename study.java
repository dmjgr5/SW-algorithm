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


	

//////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////    ## MST - 프림 알고리즘 //////////////////////
// 최소 가중치 합으로 구성된 트리 만들기
// 특정 시작점부터 시작하여 연결된 노드를 연결하면서 작은 가중치를 가진 애들을 pq 를 이용해 꺼낸다.
// https://loosie.tistory.com/159
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

// V E
// s e w
//		7 9
//		1 2 3
//		1 4 5
//		2 4 1
//		2 5 2
//		3 4 3
//		4 5 6
//		4 7 4
//		5 7 5
//		7 6 3
public class Test {

	static BufferedReader br;
	static StringTokenizer st;
	static int V, E;
	static boolean[] visited;
	static List<Node>[] edge;
	static int total;

	static class Node {
		public Node(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}

		int end;
		int weight;

	}

	static void prim(int start) {

		PriorityQueue<Node> pq = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				// TODO Auto-generated method stub
				return o1.weight - o2.weight;
			}
		});

		pq.add(new Node(start, 0));

		while (!pq.isEmpty()) {

			Node now = pq.poll();
			int node = now.end;
			int weight = now.weight;

			if (visited[node])
				continue;
			visited[node] = true;

			total += weight;

			for (Node n : edge[node]) {
				if (!visited[n.end]) {
					pq.add(new Node(n.end, n.weight));
				}
			}

		}

	}

	public static void main(String[] args) throws Exception {

		br = new BufferedReader(
				new InputStreamReader(new FileInputStream("/home/dcpark/eclipse-workspace/SWtest/src/Test/prim-test")));

		// 초기화
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		// 간선 배열 초기화
		edge = new LinkedList[E + 1];
		visited = new boolean[V + 1];
		total = 0;

		// 출발 노드별 간선 배열 초기화
		for (int i = 1; i <= V; i++) {
			edge[i] = new LinkedList<Node>();
		}

		// 간선 입력

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			edge[s].add(new Node(e, w));
			edge[e].add(new Node(s, w));
		}

		// pq 돌리기
		prim(1);

		System.out.println("minimum cost : " + total);

	}
}


입력
node edge
start end weight

7 9
1 2 3
1 4 5
2 4 1
2 5 2
3 4 3
4 5 6
4 7 4
5 7 5
7 6 3

출력
16

	
//////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////    ## 이진탐색 binary search //////////////////////
// 정렬되어 있는 배열에서 데이터를 검색할 때, 탐색 범위를 절반씩 줄여가며 
// 값이 있는 위치를 찾아가는 알고리즘이다.
//출처: https://gangintheremark.tistory.com/174 [갱ㅎr:티스토리]
//////////////////////////////////////////////////////////////////////////////////////////////////////
package Test;

public class Test {
	
	public static void main(String[] args) throws Exception {
		
		int arr[] = {1,2,3,4,5,6,7,8,9,10}; // 정렬되어 있는 배열
		int answer = 0;
		int left = 0;
		int right = arr.length - 1;
		int mid = 0;
		int target = 7; // 찾고자 하는 값
		
		while(left <= right) {	 // <= 조건 중요!!
			mid = (left+right)/2;
			System.out.println(arr[mid]);
			
			if(arr[mid] == target) {
				answer = mid;
				break;
			} 
			
			if(arr[mid] > target) {  // 찾는값이 mid 보다 작으면 right 값 조정
				right = mid - 1; //  mid 보다 하나 적은 값으로 변경
			} else {
				left = mid + 1; //  mid보다 크면 left 값 조정
			}
		}	
		System.out.println( target + "의 값이 있는 위치는 " + answer + "입니다." );
		
	}
}



//////////////////////////////////////////////////////////////////////////////////////////////////////
////////////////    ## 이진탐색 binary search //////////////////////
// 정렬되어 있는 배열에서 데이터를 검색할 때, 탐색 범위를 절반씩 줄여가며 
// 값이 있는 위치를 찾아가는 알고리즘이다.
//출처: https://gangintheremark.tistory.com/174 [갱ㅎr:티스토리]
//////////////////////////////////////////////////////////////////////////////////////////////////////
package Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Test {

//	예제 입력 115
//	1 2
//	1 3
//	2 4
//	3 7
//	6 2
//	3 8
//	4 9
//	2 5
//	5 11
//	7 13
//	10 4
//	11 15
//	12 5
//	14 7
//	6
//	6 11
//	10 9
//	2 6
//	7 6
//	8 13
//	8 15
//	예제 출력 12
//	4
//	2
//	1
//	3
//	1

	static BufferedReader br;
	static StringTokenizer st;
	static int N, M;

	static ArrayList<Integer>[] tree; // Integer 노드별 자식 정보들

	// 노드별 정보
	static boolean[] visited;
	static int[] parent;
	static int[] depth;

	static void dfs(int node, int d) {

		if (visited[node])
			return;
		visited[node] = true;

		for (int child : tree[node]) {

			if (!visited[child]) {
				parent[child] = node;
				depth[child] = d + 1;

				dfs(child, depth[child]);
			}
		}
	}

	static int lca(int a, int b) {
		if (depth[a] < depth[b]) {
			int temp = a;
			a = b;
			b = temp;
		}
		while (depth[a] != depth[b]) {
			a = parent[a]; // a 가 깊이가 깊은거라 전제하에 같은떄까지 끌어올림
		}

		while (a != b) {
			a = parent[a];
			b = parent[b];
		}

		return a;
	}

	public static void main(String[] args) throws Exception {
		// LCA 해보기
		// https://all-i-want.tistory.com/230

		// 입력 받기
		br = new BufferedReader(new InputStreamReader(
				new FileInputStream("/home/dcpark/Downloads/CodingAlgorithmClass/lca-sample-input")));

		// 노드갯수
		N = Integer.parseInt(br.readLine());

		tree = new ArrayList[N + 1];
		visited = new boolean[N + 1];
		parent = new int[N + 1];
		depth = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			tree[i] = new ArrayList<Integer>();
		}

		// 트리 정보 입력
		for (int i = 1; i <= N-1; i++) {

			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			System.out.println(i + "번째");
			System.out.println(a);
			System.out.println(b);

			tree[a].add(b);
			tree[b].add(a);
		}

		// dfs 구현 -> 부모정보, 깊이 정보 만들기
		dfs(1, 0); // 노드번호, 해당 노드의 깊이

		System.out.println("===========");
		// 두 노드 입력 받기
		M = Integer.parseInt(br.readLine());

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			// lca 구현
			System.out.println(a + "와 " + b + "의 LCA 값은 " + lca(a, b));

		}

	}
}
