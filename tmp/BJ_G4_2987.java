package src.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_G4_2987 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] tmp = br.readLine().split(" ");
        Point a = new Point(Double.parseDouble(tmp[0]), Double.parseDouble(tmp[1]));
        tmp = br.readLine().split(" ");
        Point b = new Point(Double.parseDouble(tmp[0]), Double.parseDouble(tmp[1]));
        tmp = br.readLine().split(" ");
        Point c = new Point(Double.parseDouble(tmp[0]), Double.parseDouble(tmp[1]));

        int N = Integer.parseInt(br.readLine());

        int count = 0;

        for(int i = 0; i < N; i++){
            tmp = br.readLine().split(" ");
            Point tree = new Point(Double.parseDouble(tmp[0]), Double.parseDouble(tmp[1]));
            if(myTree(a, b, c, tree)){
                count++;
            }
        }

        double ta = Math.abs(a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y)) / 2;

        System.out.println(ta + "\n" + count);
    }

    public static boolean myTree(Point a, Point b, Point c, Point tree){
        double origin = Math.abs(a.x * (b.y - c.y) + b.x * (c.y - a.y) + c.x * (a.y - b.y));

        double one = Math.abs(a.x * (b.y - tree.y) + b.x * (tree.y - a.y) + tree.x * (a.y - b.y));
        double two = Math.abs(a.x * (tree.y - c.y) + tree.x * (c.y - a.y) + c.x * (a.y - tree.y));
        double three = Math.abs(tree.x * (b.y - c.y) + b.x * (c.y - tree.y) + c.x * (tree.y - b.y));

        if(origin == one + two + three){
            return true;
        } else {
            return false;
        }
    }

    public static class Point{
        double x;
        double y;
        Point(double x, double y){
            this.x = x;
            this.y = y;
        }
    }

}
