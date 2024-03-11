/**

 @author 한규준
 @since 2023-08-15
 @see https://www.acmicpc.net/problem/5639
 @git https://github.com/cbnu2017038040/SSAFY_AL/blob/main/algo/src/algo/BJ_G5_5639.java
 @youtube
 @performance 18468KB, 408ms
 @category 트리, 파일처리
 @note

 preorder로 입력받은 숫자를 그대로 트리를 만들면 됩니다.
 배열로 만들 시 빠를 수는 있지만, size worst case가 상당히 커서
 연결리스트 형태로 만드는 것이 좋습니다(희소행렬 발생)

 이외에는, 입력을 받을 때 문제 발생 가능성이 큽니다.
 -> while로 받고, eof판단하기

 */

package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;

public class BJ_G5_5639 {

    static class Node{
        int val;
        Node left = null;
        Node right = null;

        public Node(int val){
            this.val = val;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Deque<Node> deque = new ArrayDeque<>();

        String temp = " ";
        //while((temp = br.readLine()) != null && !temp.isEmpty()){
        while(true){
            temp = br.readLine();
            if(temp.equals("q")) break;
            deque.add(new Node(Integer.parseInt(temp)));
        }

        int deqSize = deque.size() - 1;

        Node root = deque.poll();

        for(int i = 0; i < deqSize; i++){
            Node t = deque.poll();
            Node search = root;
            while(true){
                if(search.val > t.val){
                    if(search.left == null){
                        search.left = t;
                        break;
                    } else {
                        search = search.left;
                    }
                }
                else {
                    if(search.right == null){
                        search.right = t;
                        break;
                    } else {
                        search = search.right;
                    }
                }
            }
        }

        // 트리 초기화

        postOrder(root, bw);

        bw.flush();
        bw.close();

    }

    static void postOrder(Node root, BufferedWriter bw) throws Exception{
        if(root.left != null) postOrder(root.left, bw);
        if(root.right != null) postOrder(root.right, bw);
        bw.write(root.val + "\n");
    }
}
