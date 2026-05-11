package Test;

//https://bloodstrawberry.tistory.com/161
	
import java.util.Arrays;
import java.util.Scanner;
public class Pretest {
    // 상수 정의
    static final int LEFT = 0;
    static final int UP = 1;
    static final int RIGHT = 2;
    static final int DOWN = 3;

    static final int MAP_SIZE = 6;
    static final int PRIME = 52967;

    // 전역 변수 선언
    static int[][] MAP = new int[10][10];
    static int[] hashMap = new int[PRIME];

    // C의 struct를 static class로 변경
    static class CAR {
        @Override
		public String toString() {
			return "CAR [r=" + r + ", c=" + c + ", dir=" + dir + ", length=" + length + "]";
		}
		int r;
        int c;
        int dir;
        int length;
    }

    static CAR[] car = new CAR[11];
    static int ccnt = 0;

    // 이동 방향 배열
    static int[] dr = { 0, -1, 0, 1 };
    static int[] dc = { -1, 0, 1, 0 };
    static int MIN = 0x7fff0000;

    public static void input() {
        // 자동차 배열 객체 초기화
        for (int i = 0; i < 11; i++) {
            car[i] = new CAR();
        }

        /* 주변을 벽으로 만든다. */
        for (int r = 0; r <= MAP_SIZE + 1; r++) {
            for (int c = 0; c <= MAP_SIZE + 1; c++) {
                MAP[r][c] = -1;
            }
        }

        printMap();
        /* 
         * [분석 기반 예제 퍼즐] 6x6 러시아워 보드
         * 0: 빈칸
         * 1: 주인공 자동차 (무조건 가로, 3번째 줄에 위치하여 우측 탈출 목적)
         * 2~5: 장애물 자동차 (길이 2 또는 3)
         */
        int[][] sampleInput = {
            {0, 2, 0, 6, 6, 0},
            {0, 2, 0, 0, 7, 0},
            {0, 3, 1, 1, 7, 0},
            {0, 3, 4, 4, 8, 0},
            {0, 5, 5, 5, 8, 0},
            {0, 0, 0, 0, 0, 0}
        };

        
        /* MAP에 예제 데이터 복사 */
        for (int r = 1; r <= MAP_SIZE; r++) {
            for (int c = 1; c <= MAP_SIZE; c++) {
                MAP[r][c] = sampleInput[r - 1][c - 1];
            }
        }
   
        printMap();


        /* 원래 소스의 자동차 정보 파싱 및 변환 로직 (동일 유지) */
        for (int r = 1; r <= MAP_SIZE; r++) {
            for (int c = 1; c <= MAP_SIZE; c++) {
                if (MAP[r][c] != 0 && MAP[r][c] != -1) {
                    int carNum = MAP[r][c];

                    /* hash를 위해 1로 변경 */
                    MAP[r][c] = 1;

                    if (car[carNum].length != 0) continue; /* 이미 입력 받은 차라면 continue */

                    /* 자동차 정보 저장 */
                    ccnt++;
                    if (c + 1 <= MAP_SIZE && r <= MAP_SIZE && MAP[r][c + 1] == carNum) { /* 가로로 놓인 차*/
                        car[carNum].r = r;
                        car[carNum].c = c;
                        car[carNum].dir = LEFT; /* 가로 표시 */

                        if (c + 2 <= MAP_SIZE && MAP[r][c + 2] == carNum) car[carNum].length = 3;
                        else car[carNum].length = 2;
                    } else {
                        car[carNum].r = r;
                        car[carNum].c = c;
                        car[carNum].dir = UP; /* 세로 표시 */

                        if (r + 2 <= MAP_SIZE && MAP[r + 2][c] == carNum) car[carNum].length = 3;
                        else car[carNum].length = 2;
                    }
                }
            }
        }
        
        printMap();
    }
    
    static void printMap() {
        System.out.println("++++++++++++++++++++++++++++++++++++++");
        System.out.print("    |");
        int i = 0;
        for(int j = 0; j < MAP.length; j++) {
        	System.out.printf("%3d", j);
        }
        System.out.println("\n---------------------------------------");
        for (int[] row : MAP) {
        	System.out.printf("%3d", i++); 
        	System.out.printf(" |");
            for (int item : row) {
                // %-15s 는 15자리를 확보하고 왼쪽(-) 정렬한다는 뜻입니다.
                System.out.printf("%3d", item);
            }
            System.out.println();
        }
        System.out.println("++++++++++++++++++++++++++++++++++++++");

    }

    public static void output() {
        int[][] tmpMAP = new int[10][10];

        for (int i = 1; i <= 10; i++) {
            int length = car[i].length;

            for (int k = 0; k < length; k++) {
                int r, c;

                if (car[i].dir == LEFT) {
                    r = car[i].r;
                    c = car[i].c + k;
                    tmpMAP[r][c] = i;
                } else {
                    r = car[i].r + k;
                    c = car[i].c;
                    tmpMAP[r][c] = i;
                }
            }
        }

        for (int r = 1; r <= MAP_SIZE; r++) {
            for (int c = 1; c <= MAP_SIZE; c++) {
                System.out.print(tmpMAP[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static long getHash(int[][] MAP) {
        long hash = 0;
        int count = 0;

        for (int r = 1; r <= MAP_SIZE; r++) {
            for (int c = 1; c <= MAP_SIZE; c++) {
                hash |= (((long) MAP[r][c]) << count++);
            }
        }
        return hash;
    }

    public static int isMoveRight(CAR carObj, int[][] MAP) {
        if (MAP[carObj.r + dr[RIGHT]][carObj.c + carObj.length - 1 + dc[RIGHT]] != 0) return 0;

        int length = carObj.length;

        for (int i = length - 1; i >= 0; i--) {
            int nr, nc;

            nr = carObj.r + dr[RIGHT];
            nc = carObj.c + i + dc[RIGHT];

            MAP[carObj.r][carObj.c + i] = 0;
            MAP[nr][nc] = 1;
        }

        return 1;
    }

    public static int isMoveLeft(CAR carObj, int[][] MAP) {
        if (MAP[carObj.r + dr[LEFT]][carObj.c + dc[LEFT]] != 0) return 0;

        int length = carObj.length;

        for (int i = 0; i < length; i++) {
            int nr, nc;

            nr = carObj.r + dr[LEFT];
            nc = carObj.c + i + dc[LEFT];

            MAP[carObj.r][carObj.c + i] = 0;
            MAP[nr][nc] = 1;
        }

        return 1;
    }

    public static int isMoveUp(CAR carObj, int[][] MAP) {
        if (MAP[carObj.r + dr[UP]][carObj.c + dc[UP]] != 0) return 0;

        int length = carObj.length;

        for (int i = 0; i < length; i++) {
            int nr, nc;

            nr = carObj.r + i + dr[UP];
            nc = carObj.c + dc[UP];

            MAP[carObj.r + i][carObj.c] = 0;
            MAP[nr][nc] = 1;
        }

        return 1;
    }

    public static int isMoveDown(CAR carObj, int[][] MAP) {
        if (MAP[carObj.r + carObj.length - 1 + dr[DOWN]][carObj.c + dc[DOWN]] != 0) return 0;

        int length = carObj.length;

        for (int i = length - 1; i >= 0; i--) {
            int nr, nc;

            nr = carObj.r + i + dr[DOWN];
            nc = carObj.c + dc[DOWN];

            MAP[carObj.r + i][carObj.c] = 0;
            MAP[nr][nc] = 1;
        }

        return 1;
    }

    public static void DFS(int L) {
        if (MIN <= L) return;

        long h = getHash(MAP);
        int p = (int) (h % PRIME);
        if (p < 0) p += PRIME;

        if (hashMap[p] > L) hashMap[p] = L;
        else return;

        // 1번 차의 열 위치(c)가 5에 도달하면 탈출 성공
        if (car[1].c == 5) {
            if (L < MIN) MIN = L;
            return;
        }

        if (L > 7) return;

        for (int i = 1; i <= ccnt; i++) {
            if (car[i].dir == LEFT) {
                if (isMoveRight(car[i], MAP) == 1) {
                    car[i].c++;
                    DFS(L + 1);

                    isMoveLeft(car[i], MAP);
                    car[i].c--;
                }

                if (isMoveLeft(car[i], MAP) == 1) {
                    car[i].c--;
                    DFS(L + 1);

                    isMoveRight(car[i], MAP);
                    car[i].c++;
                }
            } else {
                if (isMoveUp(car[i], MAP) == 1) {
                    car[i].r--;
                    DFS(L + 1);

                    isMoveDown(car[i], MAP);
                    car[i].r++;
                }

                if (isMoveDown(car[i], MAP) == 1) {
                    car[i].r++;
                    DFS(L + 1);

                    isMoveUp(car[i], MAP);
                    car[i].r--;
                }
            }
        }
    }

    public static void main(String[] args) {
        input();

        for (int i = 0; i < PRIME; i++) {
            hashMap[i] = 0x7fff0000;
        }

        DFS(0);

        if (MIN == 0x7fff0000) {
            System.out.println("-1");
        } else {
            System.out.println(MIN + 2);
        }
    }
}
