package BOJ.implement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BJ_G5_14891 {
    static String[] gears;
    static GearStatus[] gearStatuses;
    static Deque<int[]> deq;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        gears = new String[4];
        for(int i = 0; i < 4; i++){
            gears[i] = br.readLine();
        }

        deq = new ArrayDeque<>();
        gearStatuses = new GearStatus[4];

        gearStatuses[0] = new GearStatus(6, 2);
        gearStatuses[1] = new GearStatus(6, 2);
        gearStatuses[2] = new GearStatus(6, 2);
        gearStatuses[3] = new GearStatus(6, 2);

        int K = Integer.parseInt(br.readLine());

        String[] str;
        for(int i = 0; i < K; i++){
            str = br.readLine().split(" ");
            int gearNumber = Integer.parseInt(str[0]) - 1;
            int direction = Integer.parseInt(str[1]);

            // 1이면 시계방향(우측)
            // -1이면 반시계반향(좌측)

            deq.offer(new int[]{gearNumber, direction});

            checkGear(gearNumber, direction);
            while(!deq.isEmpty()){
                int[] now = deq.poll();
                rotateGear(now[0], now[1]);
            }
        }

        int result = 0;
        for(int i = 0; i < 4; i++){
            int ptr = gearStatuses[i].rightPointer - 2;
            if(ptr < 0) ptr += 8;
            if(gears[i].charAt(ptr) == '1') result += Math.pow(2, i);
        }
        System.out.println(result);

    }

    public static void checkGear(int thisGear, int direction){
        int leftGear = thisGear - 1;
        int rightStandard = thisGear;
        int rightdirection = direction;
        while(leftGear >= 0){
            if(gears[thisGear].charAt(gearStatuses[thisGear].leftPointer) != gears[leftGear].charAt(gearStatuses[leftGear].rightPointer)){
                deq.offer(new int[]{leftGear, direction * -1});
                direction *= -1;
                thisGear--;
                leftGear--;
            } else break;
        }
        int rightGear = rightStandard + 1;
        while(rightGear < 4){
            if(gears[rightStandard].charAt(gearStatuses[rightStandard].rightPointer) != gears[rightGear].charAt(gearStatuses[rightGear].leftPointer)){
                deq.offer(new int[]{rightGear, rightdirection * -1});
                rightdirection *= -1;
                rightStandard++;
                rightGear++;
            } else break;
        }
    }

    public static void rotateGear(int gearNumber, int direction){
        if(direction == -1){
            gearStatuses[gearNumber].leftPointer = (gearStatuses[gearNumber].leftPointer + 1) % 8;
            gearStatuses[gearNumber].rightPointer = (gearStatuses[gearNumber].rightPointer + 1) % 8;
        } else {
            gearStatuses[gearNumber].leftPointer = gearStatuses[gearNumber].leftPointer - 1;
            gearStatuses[gearNumber].rightPointer = gearStatuses[gearNumber].rightPointer - 1;
            if(gearStatuses[gearNumber].leftPointer == -1) gearStatuses[gearNumber].leftPointer = 7;
            if(gearStatuses[gearNumber].rightPointer == -1) gearStatuses[gearNumber].rightPointer = 7;
        }
    }

    public static class GearStatus{
        int leftPointer;
        int rightPointer;
        GearStatus(int leftPointer, int rightPointer){
            this.leftPointer = leftPointer;
            this.rightPointer = rightPointer;
        }
    }
}
