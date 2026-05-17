

## 코딩테스트 자주 나오는 자료구조 총정리 (Java 기준) <i>[참고자료](https://opentutorials.org/module/782/6083)</i>

| 자료구조	|  주요 특징	| 대표 사용처| 
| --- |---  |---  | 
| ArrayList	| 순차 저장, 인덱스 접근	| 투 포인터, 누적합| 
| Stack	| LIFO 구조	| 괄호, 백트래킹| 
| Queue	| FIFO 구조 | 	BFS, 시뮬레이션| 
| PriorityQueue	| 우선순위 정렬 | 	최소 비용, 힙| 
| Deque	| 양방향 삽입/제거	| 슬라이딩 윈도우| 
| HashMap / Set	| 빠른 검색/저장	| 중복 체크, 매핑| 
 

 
<details>
<summary>DFS</summary>

```java
package Test;

import java.util.Stack;

public class Test {
	
	// DFS 구현하기
	static Stack<Integer> stack;
	static boolean[] visited ;
	static int[][] arr 
	= { {}, { 2, 3, 8 }, { 1, 6, 8 }, { 1, 5 }, { 5, 7 }, { 3, 4, 7 }, { 2 }, { 4, 5 }, { 1, 2 } };
	  
	static void dfs(int start) {
		visited = new boolean[9];
		stack = new Stack<Integer>();
		stack.push(start);
		
		while(!stack.isEmpty()) {
			int node = stack.pop();
			if(visited[node]) continue;
			visited[node] = true;
			
			System.out.println("여기에서 " + node + " 를 방문했습니다. ");
			
			for(int next : arr[node]) {
				if(visited[next]) continue;
				stack.push(next);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		
		dfs(1);
	}
	 
}
```
```sh
//result
여기에서 1 를 방문했습니다. 
여기에서 8 를 방문했습니다. 
여기에서 2 를 방문했습니다. 
여기에서 6 를 방문했습니다. 
여기에서 3 를 방문했습니다. 
여기에서 5 를 방문했습니다. 
여기에서 7 를 방문했습니다. 
여기에서 4 를 방문했습니다. 
```
</details>
 



  

 
<details>
<summary>BFS</summary>


```java
package Test;

import java.util.LinkedList;
import java.util.Queue;

public class Test {
	
	// BFS 구현하기
	static Queue<Integer> queue;
	static boolean[] visited ;
	static int[][] arr 
	= { {}, { 2, 3, 8 }, { 1, 6, 8 }, { 1, 5 }, { 5, 7 }, { 3, 4, 7 }, { 2 }, { 4, 5 }, { 1, 2 } };
	  
	static void bfs(int start) {
		visited = new boolean[9];
		queue = new LinkedList<Integer>(); //Stack과 다르게 전용 구현체가 존재하지 않는다. LinkedList를 구현체로 사용할 수 있다.
		
		queue.offer(start);
		while(!queue.isEmpty()) {
			int node = queue.poll();
			
			if(visited[node]) continue;
			visited[node] = true;
			
			System.out.println("여기에서 " + node + " 를 방문하였습니다.");
			
			for(int next : arr[node]) {
				if(visited[next]) continue;
				queue.offer(next);
			}
			
		}
 
	}

	public static void main(String[] args) throws Exception {
		
		bfs(1);
		 
	}
}
```
```sh
//result
여기에서 1 를 방문하였습니다.
여기에서 2 를 방문하였습니다.
여기에서 3 를 방문하였습니다.
여기에서 8 를 방문하였습니다.
여기에서 6 를 방문하였습니다.
여기에서 5 를 방문하였습니다.
여기에서 4 를 방문하였습니다.
여기에서 7 를 방문하였습니다.
```
</details>

<details>
<summary>Union-Find </summary>


```java
package Test;

import java.util.Arrays;

public class Test {
	
	// 10 개의 노드가 있다고 하자. 노드쌍 유니온하는것과 파인드하는 메서드를 만들어 보고
	//상태 변화에 따라 출력해 보자.
	
	static int N = 10; // 노드 갯수
	static int[] parent;
	
	static int find(int a) {
		if(parent[a] == a) {
			System.out.println(a + " 의 부모노드는 " + parent[a] + " 입니다.");
			return a;
		}
		System.out.println(a + " 의 부모노드는 " + parent[a] + " 입니다.");
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		System.out.println("## union " + a + " and " + b);
		
		a = parent[a];
		b = parent[b];
		
		if(parent[a] == parent[b]) return false;
		if(a > b) { // 작은 값을 parent 하기 위해 swap 처리
			int temp = a;
			a = b;
			b = temp;
		}
		parent[b] = a;
		System.out.println(Arrays.toString(parent));
		return true;
	}
	 
	public static void main(String[] args) throws Exception {
		parent = new int[N+1];
		for(int i = 1; i <= N; i++) {
			parent[i] = i; // 자기 자신을 부모로 한다.
		}
		
		union(1,3);
		union(7,9);
		union(5,3);
		find(3);
		find(9);
		union(8,1);
		find(8);
 
		 
	}
}
```
```sh
//result
## union 1 and 3
[0, 1, 2, 1, 4, 5, 6, 7, 8, 9, 10]
## union 7 and 9
[0, 1, 2, 1, 4, 5, 6, 7, 8, 7, 10]
## union 5 and 3
[0, 1, 2, 1, 4, 1, 6, 7, 8, 7, 10]
3 의 부모노드는 1 입니다.
1 의 부모노드는 1 입니다.
9 의 부모노드는 7 입니다.
7 의 부모노드는 7 입니다.
## union 8 and 1
[0, 1, 2, 1, 4, 1, 6, 7, 1, 7, 10]
8 의 부모노드는 1 입니다.
1 의 부모노드는 1 입니다.
```
</details>
 
<details>
<summary>Priority Queue - basic</summary>

```java
package Test;

import java.util.Collections;
import java.util.PriorityQueue;

public class Test {
	
	static PriorityQueue<Integer> pq;
	
	static void setPq() {
		pq.offer(3);
		pq.offer(1);
		pq.offer(5);
		pq.offer(4);
		pq.offer(2);
	}
	
	static void getPqValue() {
		while(!pq.isEmpty()) {
			int size = pq.size();
			int a = pq.poll(); 
			System.out.println("PriorityQueue Size : " + size + " , Poll value: " + a);
		}
	}
	 
	public static void main(String[] args) throws Exception {
		
		pq = new PriorityQueue<Integer>(Collections.reverseOrder());
		setPq();
		getPqValue();
		
		System.out.println("===========");

		pq = new PriorityQueue<Integer>();
		setPq();
		getPqValue();
	
	}
 
}
```
```sh
//result
PriorityQueue Size : 5 , Poll value: 5
PriorityQueue Size : 4 , Poll value: 4
PriorityQueue Size : 3 , Poll value: 3
PriorityQueue Size : 2 , Poll value: 2
PriorityQueue Size : 1 , Poll value: 1
===========
PriorityQueue Size : 5 , Poll value: 1
PriorityQueue Size : 4 , Poll value: 2
PriorityQueue Size : 3 , Poll value: 3
PriorityQueue Size : 2 , Poll value: 4
PriorityQueue Size : 1 , Poll value: 5
```
</details>
	 
<details>
<summary>PriorityQueue - object & comparable </summary>

```java
package Test;

import java.util.Comparator;
import java.util.PriorityQueue;

// 5 명의 수학, 영어 점수를 받아 수학점수가 높은 순서로 정렬하라. 
// 학생 클래스에 점수를 담아라.
//수학 점수가 동일하다면 영어점수가 높은 순서로 정렬하자.

public class Test {
	 
	static PriorityQueue<Student> pq;
	static int[][] score = {{30,50},{100,40},{80,70},{80,80}, {50,40}};
	static class Student{
		public Student(int math, int eng) {
			this.math = math;
			this.eng = eng;
		}
		int math;
		int eng;
		
		@Override
		public String toString() { // toString 추가하면 디버그에서 바로 볼수 있다!!
			return "Student [math=" + math + ", eng=" + eng + "]";
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		// 오름차순 정리
    	// PriorityQueue 에서 쓰이기 위해서는 반드시 compare 로 우선순위 기준을 정해줘야함. 
    	// Integer, String, Double 등: 자바가 이미 비교 방법을 알고 있으므로 그냥 써도 됨.
        // Student, Item 등 사용자 정의 클래스: 자바가 비교 방법을 모르므로 반드시 알려줘야 함.
		pq = new PriorityQueue<Student>(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				if(o2.math == o1.math) return o2.eng - o1.eng;
				return o2.math - o1.math;
			}
		});
		
		// Student 객체에 담기
		for(int i=0; i<score.length; i++) {
			pq.offer(new Student(score[i][0], score[i][1]));
		}
		
		// 정렬된 순서로 출력하기
		int order = 0;
		while(!pq.isEmpty()) {
			Student s = pq.poll();
			System.out.println("Rank : " + (++order)+ ", Math : " + s.math + ", Eng : "+ s.eng );
		}
	}
}
```
```sh
//result
Rank : 1, Math : 100, Eng : 40
Rank : 2, Math : 80, Eng : 80
Rank : 3, Math : 80, Eng : 70
Rank : 4, Math : 50, Eng : 40
Rank : 5, Math : 30, Eng : 50
```
</details>
 
<details>
<summary>Dijkstra</summary>
	
```java
package Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// 출발지, 목적지, 가중치가 각각 아래와 같은 정보가 있다.
// 출발지로부터 각 노드의 최단 거리를 구하라.
// 입력 정보는 아래 파일에 저장되어 있다
// /home/dcpark/Downloads/CodingAlgorithmClass/dijkstra-input.txt
//V E 노드갯수, 간선 갯수
//K 출발지 노드
//s e w
// 	5 6 
// 	1
// 	5 1 1
// 	1 2 2
// 	1 3 3
// 	2 3 4
// 	2 4 5
// 	3 4 6

public class Test {
	
	static BufferedReader br;
	static StringTokenizer st;
	static int V,E,K;
	static int INF = Integer.MAX_VALUE;
	
	// 다익스트라 기본 자료
	static int[] distance;
	static boolean[] visited;
	static List<Edge>[] edgeList;
	static PriorityQueue<Node> pq;
	static class Edge {
		@Override
		public String toString() {
			return "Edge [dest=" + dest + ", cost=" + cost + "]";
		}
		public Edge(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
		int dest;
		int cost;
	}
	
	static class Node { // Edge와 사실상 같으나 헷갈리므로 별도 생성(pq 에서 활용)
		@Override
		public String toString() {
			return "Node [node=" + node + ", cost=" + cost + "]";
		}
		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}
		int node;
		int cost;
		
	}
	
	static void dijkstra(int start, int cost) {
		// pq 초기화(정렬 포함)
		pq = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				return o1.cost - o2.cost;
			}
	 
		});
		// start 부터 시작
		pq.offer(new Node(start,cost)); // Node 클래스에 넣었다 빼자!!
		distance[start] = 0;
		
		// pq 에서 꺼내고, 간선들 추가
		while(!pq.isEmpty()) {
			Node node = pq.poll();
			int cur = node.node;
			
			if(visited[cur]) continue;
			visited[cur] = true;
			
			System.out.println("node 방문 : " + cur);
			 
			for(Edge next : edgeList[cur]) {
				System.out.println(cur + " ==> " + next.dest);
				int nextNode = next.dest;
				int nextCost = next.cost;
				
				if(!visited[nextNode] && distance[nextNode] > distance[cur] + nextCost) {
					System.out.println(nextNode + "번 노드 distance 갱신 ");
					distance[nextNode] = distance[cur] + nextCost;
					pq.offer(new Node(nextNode, nextCost)); // 갱신한것에 대해서 pq 에 넣어주자!!
				}
			}
		}
	}
 
	
	
	public static void main(String[] args) throws Exception {
		
		// 입력 자료
		br = new BufferedReader(new InputStreamReader(new FileInputStream("/home/dcpark/Downloads/CodingAlgorithmClass/dijkstra-input.txt")));
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		
		// 자료 초기화
		distance = new int[V+1];
		for(int i = 1; i <= V; i++) distance[i] = INF;
		visited = new boolean[V+1];
		edgeList = new ArrayList[V+1];  // new ArrayList !!
		for(int i = 1; i <= V; i++) edgeList[i] = new ArrayList<Edge>(); // new ArrayList !!
		
		// 간선 정보 입력
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			edgeList[s].add(new Edge(e,w));
		}
		
		// dijkstra 구현
		dijkstra(1,0);
		
		// 출력
		for(int i = 1; i<=V; i++) {
			System.out.println(i + " 의 최단거리 : " + distance[i]);
		}
		 
	}
}
```

```sh
//result 
node 방문 : 1
1 ==> 2
2번 노드 distance 갱신 
1 ==> 3
3번 노드 distance 갱신 
node 방문 : 2
2 ==> 3
2 ==> 4
4번 노드 distance 갱신 
node 방문 : 3
3 ==> 4
node 방문 : 4
1 의 최단거리 : 0
2 의 최단거리 : 2
3 의 최단거리 : 3
4 의 최단거리 : 7
5 의 최단거리 : 2147483647

```
</details>



<details>
<summary>MST - 크루스칼 알고리즘</summary>

- 최소 가중치 합으로 구성된 트리 만들기
- MST - 크루스칼 알고리즘: 모든 간선을 pq 에 넣고, 작은 가중치부터 꺼내어 union 아니면 추가, union 이면 스킵
- [https://loosie.tistory.com/159](https://sjh9708.tistory.com/244#google_vignette)

```java
package Test;
 
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
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
	static PriorityQueue<Edge> pq;
	static int total;
	static int[] parent;
    static int MAX = Integer.MAX_VALUE;
    
	static class Edge {
 
		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		int start;
		int end;
		int weight;

	}
	
	static int find(int a) {
		if(parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}

	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a == b) return false;
		if(a > b) {
			int temp = a;
			a = b;
			b = temp;
		}
		parent[b] = a;
	    return true;
	}

	static void kruskal() {
		
		while(!pq.isEmpty()) {
			Edge cur = pq.poll();
			int s = cur.start;
			int e = cur.end;
			int w = cur.weight;
			
			if(find(s) == find(e)) continue;
			
			total += w;
			union(s,e);
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
		total = 0;
		parent = new int[V+1];
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}
		
		pq = new PriorityQueue<Edge>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				// TODO Auto-generated method stub
				return o1.weight - o2.weight;
			}
		});


		// 간선 입력

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			pq.offer(new Edge(s,e,w)); 
		}

		// pq 돌리기
		kruskal();

		System.out.println("minimum cost : " + total);

	}
}
```
```sh
minimum cost : 16
```


</details>



<details>
<summary>MST - 프림 알고리즘</summary>

- 최소 가중치 합으로 구성된 트리 만들기
- 특정 시작점부터 시작하여 연결된 노드를 연결하면서 작은 가중치를 가진 애들을 pq 를 이용해 꺼낸다.
- https://loosie.tistory.com/159


```java
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
```

```sh
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
```
```sh
출력
16
```
</details>
	 
<details>
<summary>이진탐색 binary search </summary>
	
- 정렬되어 있는 배열에서 데이터를 검색할 때, 탐색 범위를 절반씩 줄여가며
- 값이 있는 위치를 찾아가는 알고리즘이다.
- 출처: https://gangintheremark.tistory.com/174 [갱ㅎr:티스토리]

```java
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
```
</details>

<details>
<summary>LCA</summary>
	
- https://all-i-want.tistory.com/230


```java
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
```
</details>

<details>
<summary>인덱스 트리 - 구간 합 </summary>
	
- https://attenti-on.tistory.com/2

```java
package Test;

import java.util.Arrays;

public class Test {
	
	static int N = 7;
	static int[] numbers = {1,2,3,4,5,6,7};
	static int[] indexTree;
	static int leafSize;
	static int treeSize;
	static int baseSize;
	
	
	static void makeIndexTree(){
		// 리프 사이즈 구하기
		leafSize = 1;
		while(leafSize < N) {
			leafSize = leafSize << 1;  ////////// 중요!!!!!!!!!
		}
		treeSize = leafSize * 2;       ///////// 중요!!!!!!!!!
		indexTree = new int[treeSize];
		baseSize = leafSize - 1;
		printIndexTree();
	}
	
	static void getSum(int start, int end) {
		printIndexTree();
		System.out.println(start + "와 " + end + " 사이의 부분합은 ");		
		start = baseSize + start;
		end = baseSize + end;

		int result = 0;
		while(start <= end) {
			if(start%2 == 1) result += indexTree[start]; ///////// 중요!!!!!!!!!
			if(end%2 == 0) result += indexTree[end];    ///////// 중요!!!!!!!!!
			
			start = (start+1) / 2;
			end = (end-1) / 2;
		}
		System.out.print(  result + " 입니다.");
	}
 
	static void update(int idx, int val) {
		idx = baseSize + idx; 
		indexTree[idx] = val;
		while(idx > 1) {  ///////// 중요!!!!!!!!!
			idx = idx/2;
			indexTree[idx] = indexTree[idx*2] + indexTree[(idx*2) +1];
		}
		printIndexTree();
	}
	
	static void printIndexTree() {
		int[] iTree = new int[indexTree.length];
		for(int i = 0; i < indexTree.length; i++) iTree[i]=i;
		System.out.println("------------------------------------");
		System.out.println(Arrays.toString(iTree));
		System.out.println(Arrays.toString(indexTree));
	}
 

	public static void main(String[] args) throws Exception {
		
		// 인덱스 트리 생성
		makeIndexTree(); 
		
		// 트리에 값 채우기
		for(int i = 1; i <= numbers.length; i++) {
			// 단말 노드 채우기
			System.out.println(baseSize+i + "노드 업데이트!!");
			indexTree[baseSize+i] = numbers[i-1];
			
			
			// 상위 노드 구간합 구하기
			int current = baseSize+i;
			current = current/2;
			while(current > 0) {
				indexTree[current] = indexTree[current*2] + indexTree[current*2 + 1];
				current = current/2;
			}
		} 
		getSum(2,4);
		
		update(2,5);
		
		getSum(2,4);

		
	}
	 
}

```

</details>





