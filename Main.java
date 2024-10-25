import com.example.numplay.BaseballGame;
import com.example.numplay.GameRecordManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameRecordManager recordManager = new GameRecordManager();

        while (true) {
            //게임시작
            //정답생성 정답입력 스트라이크 볼 개수 출력 BaseballClass
            System.out.println("<환영합니다! 원하시는 번호를 입력해주세요>\n[1.게임시작하기 ] [2.게임기록 보기] [3.종료하기]");
            BaseballGame baseballgame = new BaseballGame();

            int choice = sc.nextInt();
            sc.nextLine();
            if (choice == 1) {
                baseballgame.BaseballGamePlay(recordManager);
            } else if (choice == 2) {
                recordManager.showRecords();
            } else if (choice == 3) {
                System.out.println("<게임을 종료합니다!>");
                break;
            } else {
                System.out.println("<올바른 숫자를 입력해주세요!>");
            }
        }
        sc.close();
    }
}