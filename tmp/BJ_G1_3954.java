package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_G1_3954 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());

        for(int TC = 1; TC <= t; TC++){
            st = new StringTokenizer(br.readLine());
            // m : 메모리크기. c : 프로그램 코드 크기. i : 입력크기
            int m = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());

            int[] memory = new int[m];
            char[] code = new char[c];
            char[] input = new char[i];
            String temp;

            temp = br.readLine();
            for(int j = 0; j < c; j++){
                code[j] = temp.charAt(j);
            }
            temp = br.readLine();
            for(int j = 0; j < i; j++){
                input[j] = temp.charAt(j);
            }
            // 초기화 완료

            int instCount = 0;
            int inputIdx = 0;
            int instIdx = 0;
            int pointer = 0;
            int firstIdx = 0;
            int lastIdx = 0;
            Stack<Integer> stack = new Stack<>();

            while(true) {
                if (instCount >= 50000000) break;
                if(instIdx == c) break;
                instCount++;
                if(pointer == m) pointer = 0;
                else if(pointer == -1) pointer = m - 1;
                char nowInst = code[instIdx];

                if (nowInst == '-') {
                    memory[pointer] = memory[pointer] % 256 - 1;
                    instIdx++;
                } else if (nowInst == '+') {
                    memory[pointer] = memory[pointer] % 256 + 1;
                    instIdx++;
                } else if (nowInst == '<') {
                    pointer--;
                    instIdx++;
                } else if (nowInst == '>') {
                    pointer++;
                    instIdx++;
                } else if (nowInst == '[') {
                    if (memory[pointer] == 0) {
                        while (true) {
                            if (code[instIdx] == ']') {
                                instIdx++;
                                break;
                            }
                            instIdx++;
                        }
                    } else {
                        firstIdx = instIdx;
                        stack.push(instIdx++);
                    }
                } else if (nowInst == ']') {
                    if (memory[pointer] == 0) {
                        instIdx++;
                    } else {
                        lastIdx = instIdx;
                        int backJump = stack.pop();
                        instIdx = backJump;
                    }
                } else if (nowInst == '.') {
                    instIdx++;
                } else if (nowInst == ',') {
                    if (inputIdx == i) memory[pointer] = 255;
                    else memory[pointer] = input[inputIdx++];
                    instIdx++;
                }
            }

            if(instCount >= 50000000){
                bw.write("Loops " + firstIdx + " " + lastIdx + "\n");
            }
            else {
                bw.write("Terminates\n");
            }
            bw.flush();
        }

        bw.close();
    }
}
