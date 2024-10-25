package com.example.numplay;


import java.util.ArrayList;
import java.util.List;

public class GameRecordManager {

    static List<Integer> attemptsList = new ArrayList<>(); // 시도 횟수를 저장할 리스트

    public void addRecord(int attempts) {
        attemptsList.add(attempts); // 시도 횟수 기록
    }

    public void showRecords() {

        System.out.println("<게임 기록>\n-------------------------");
        if (attemptsList.isEmpty()) {
            System.out.println("<기록이 없습니다.>\n-------------------------");
        } else {
            for (int i = 0; i < attemptsList.size(); i++) {
                System.out.println("[" + (i + 1) + "번째 게임  | " + attemptsList.get(i) + "번 시도]");
            }
            System.out.println("-------------------------");
        }
    }
}