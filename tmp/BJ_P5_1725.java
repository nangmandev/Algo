package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_P5_1725 {
    static int n, size;
    static int[] arr, tree;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());

        int height = (int)Math.ceil(Math.log(n) / Math.log(2));
        size = (int)Math.pow(2, height + 1);
        arr = new int[n];
        tree = new int[size];

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        initTree(1, 0, n - 1);

        System.out.println(getMax(0, n - 1));
    }

    // top down식 트리 초기화
    public static void initTree(int node, int start, int end){
        if(start == end) tree[node] = start;
        else {
            int mid = (start + end) / 2;
            initTree(node * 2, start, mid);
            initTree(node * 2 + 1, mid + 1, end);

            if(arr[tree[node * 2]] < arr[tree[node * 2 + 1]]){
                tree[node] = tree[node * 2];
            } else {
                tree[node] = tree[node * 2 + 1];
            }
        }
    }

    // max 찾기
    // getMax에서는 최대 검색범위만 지정한다.
    // 최대 검색범위를 계속 바꾸면서 검색한다.
    public static long getMax(int start, int end){
        // 최소 인덱스를 받아온다.
        // 처음 부를때는 최대범위, 최대 부분범위이므로 노드 최대값을 같이 보낸다.
        int minIdx = getMinIdx(start, end, 0, n - 1, 1);

        // 구해온 인덱스로 넓이 구하기
        long result = (long)(end - start + 1) * (long)arr[minIdx];

        // 최대 검색범위를 이분하여 다시 검색해본다 -> 중간 인덱스가 아닌 최소 인덱스라서
        if(start < minIdx){
            long temp = getMax(start, minIdx - 1);
            if(result < temp) result = temp;
        }
        if(end > minIdx){
            long temp = getMax(minIdx + 1, end);
            if(result < temp) result = temp;
        }

        return result;
    }

    // 최소 인덱스를 받아오는 함수 -> 범위를 잘 설정해야 한다.
    // 1. 전체 탐색 가능한 구간
    // 2. 탐색해야하는 부분구간
    // 3. 해당 구간이 공유하는 최소값 노드
    // 위의 3가지 정보를 계속 보내서, 재귀적으로 탐색해야 한다.
    // 탐색해야 하는 부분구간은 전체 탐색 가능한 구간에 포함되어야 한다.
    public static int getMinIdx(int start, int end, int left, int right, int node){
        // 탐색범위를 완벽하게 벗어나면 안된다. 조금이라도 겹쳐야 한다.
        if(start > right || end < left) return -1;
        // 탐색범위 내부에 완벽하게 들어오면 최소노드를 그대로 반환한다.
        if(left >= start && right <= end) return tree[node];



        // 탐색범위에 조금 걸쳐있는 경우 처리



        int mid = (left + right) / 2;
        // 탐색범위가 바뀌면 안된다.
        // 전체 탐색 가능한 범위를 좁혀야 한다.
        int leftNode = getMinIdx(start, end, left, mid, node * 2);
        int rightNode = getMinIdx(start, end, mid + 1, right, node * 2 + 1);

        // 재귀호출했는데 -1이면 완벽하게 벗어남.
        // 벗어나지 않은 부분을 나눴는데 한쪽이 완전히 벗어나면
        // 다른쪽은 벗어나지 않은 부분이다.
        if(leftNode == -1){
            return rightNode;
        } else if(rightNode == -1){
            return leftNode;
        } else {
            // 둘다 -1이 아니라면? 분리됨.
            // 양쪽 노드를 검사해보고, 가장 작은 노드를 반환한다
            // -> 분리된 트리의 부분합을 구함
            if(arr[leftNode] <= arr[rightNode]){
                return leftNode;
            } else return  rightNode;
        }
    }
}
