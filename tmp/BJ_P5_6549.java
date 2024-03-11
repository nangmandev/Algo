package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_P5_6549 {
    static int n, size;
    static int[] arr, tree;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int height = (int)Math.ceil(Math.log(n) / Math.log(2));
        size = (int)Math.pow(2, height + 1);

        tree = new int[size];

        initTree(1, 0, n - 1);

        bw.write(getMax(0, n - 1) + "\n");
        bw.flush();
        bw.close();
    }

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

    public static long getMax(int left, int right){
        // 쿼리를 던져 해당 구간의 최소값을 가진 인덱스를 알아낸다.
        int m = query(0, n - 1, left, right, 1);

        // 구간의 최소값을 알아냈으면, 넓이를 계산한다.
        long area = (long)(right - left + 1) * (long)arr[m];
        // 왼, 오 재귀
        if(left < m){
            // 구간 시작점이 최소값 인덱스보다 작으면 왼쪽도 탐색하러 간다.
            long temp = getMax(left, m - 1);

            // 왼쪽구간 최대넓이가 호출전 최대넓이보다 넓으면 갱신
            if(area < temp) area = temp;
        }
        if(right > m){
            // 구간 끝점이 최소값 인덱스보다 크면 오른쪽도 탐색하러 간다.
            long temp = getMax(m + 1, right);
            if(area < temp) area = temp;
        }
        return area;
    }

    // 해당 범위의 최소노드 인덱스 반환
    public static int query(int start, int end, int left, int right, int node){
        // 범위 벗어나면 아웃
        if(left > end || right < start) return -1;
        // start, end -> 해당 범위 내로 들어오면 최소값 인덱스 반환
        if(start >= left && end <= right) {
            return tree[node];
        }

        // start - end : 전체 탐색범위
        // left - right : 현재 탐색범위


        // 아니면 찾으러 간다
        // -> 범위가 걸쳐져 있는경우
        int mid = (start + end) / 2;
        // 왼쪽을 탐색해본다. 왼쪽에 걸쳐져있으면 여기서 결과가 나올것
        int leftNode = query(start, mid, left, right, node * 2);
        // 오른쪽을 탐색해본다. 오른쪽에 걸쳐져있으면 여기서 결과가 나올것
        int rightNode = query(mid + 1, end, left, right, node * 2 + 1);
        // leftnode가 -1이면 없는거 -> right노드 반환. 반대도 마찬가지
        if(leftNode == -1){
            return rightNode;
        } else if(rightNode == -1){
            return leftNode;
        } else {

            // 예외가 없는경우, 더 작은 쪽 노드의 인덱스를 반환한다.
            if(arr[leftNode] <= arr[rightNode]){
                return leftNode;
            } else {
                return rightNode;
            }
        }
    }
}
